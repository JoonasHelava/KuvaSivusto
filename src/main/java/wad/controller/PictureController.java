
package wad.controller;

import wad.repository.FileObjectRepository;
import javax.transaction.Transactional;
import wad.domain.FileObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wad.repository.CommentRepository;



/**
 *
 * @author Joonas
 */

//"Picture" controller for routes GET( /pictures , /addpicture, /pictures/{id}, /pictures/{id}/content ) POST ( /pictures )
@Controller
public class PictureController {
    
    //Needed to get all info in FileObject
    @Autowired
    private FileObjectRepository fileObjectRepository;
    //In future all info is divided to their own classes
    @Autowired
    private CommentRepository commentRepository;
    
    //directs you to first picture
    @GetMapping("/pictures")
    public String redirect(){
        return "redirect:/pictures/1";
    }
    //directs you to page where you can add picture
    @GetMapping("/addpicture")
    public String addpic(){
        return "addpicture";
    }
    
    //directs you to picture
    @Transactional
    @GetMapping("/pictures/{id}")
    public String get(@PathVariable Long id,Model model) {
        Long imageCount = this.fileObjectRepository.count();
        Map<String, Object> map = new HashMap<>();
        map.put("count",this.fileObjectRepository.findAll().size());
        //For browsing setting ids of next and previous picture
        if (id < imageCount && id > 0L) {
            map.put("next",id + 1);
        }
        if (id > 1L) {
            map.put("previous",id - 1);
        }
        //To see the picture id is set to current
        if (id >= 1L && id <= imageCount) {
            map.put("current",id);
            //Information about pictures votes and comments
            map.put("upvotes",this.fileObjectRepository.getOne(id).getUpvote());
            map.put("downvotes",this.fileObjectRepository.getOne(id).getDownvote());
            map.put("comments",this.commentRepository.findByOb(id));
        }
        
        model.addAllAttributes(map);
        
        return "pictures";
        
    }
    //This gives you the picture
    @Transactional
    @GetMapping(path = "/pictures/{id}/content", produces = "image/png")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return fileObjectRepository.getOne(id).getContent();
    }
    //Saving the picture that is added
    @Transactional
    @PostMapping("/pictures")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
    FileObject fo = new FileObject();
    fo.setContent(file.getBytes());
    this.fileObjectRepository.save(fo);
    return "redirect:/pictures";
    
}

}

