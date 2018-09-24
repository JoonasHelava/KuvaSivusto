/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

/**
 *
 * @author Joonas
 */

import wad.repository.FileObjectRepository;
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
@Controller
public class PictureController {
    
    @Autowired
    private FileObjectRepository fileObjectRepository;
    @Autowired
    private CommentRepository commentRepository;
    
    @GetMapping("/pictures")
    public String redirect(){
        return "redirect:/pictures/1";
    }
    @GetMapping("/addpicture")
    public String addpic(){
        return "addpicture";
    }
    @GetMapping("/pictures/{id}")
    public String get(@PathVariable Long id,Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("count",this.fileObjectRepository.findAll().size());
        if(this.fileObjectRepository.findById(id+1).isPresent()){
            map.put("next",this.fileObjectRepository.getOne(id+1).getId());
        }
        if(this.fileObjectRepository.findById(id-1).isPresent()){
            map.put("previous",this.fileObjectRepository.getOne(id-1).getId());
        }
        if(this.fileObjectRepository.findById(id).isPresent()){
            map.put("current",this.fileObjectRepository.getOne(id).getId());
            map.put("upvotes",this.fileObjectRepository.getOne(id).getUpvote());
            map.put("downvotes",this.fileObjectRepository.getOne(id).getDownvote());
            map.put("comments",this.commentRepository.findByOb(this.fileObjectRepository.getOne(id).getId()));
        }
        
        model.addAllAttributes(map);
        
        return "pictures";
        
    }
    @GetMapping(path = "/pictures/{id}/content", produces = "image/png")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return fileObjectRepository.getOne(id).getContent();
    }
    @PostMapping("/pictures")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
    FileObject fo = new FileObject();
    fo.setContent(file.getBytes());
    System.out.println(file.getContentType());
    if(file.getContentType().matches("image/png")){
        fileObjectRepository.save(fo);
    }
    return "redirect:/pictures";
    
}

}

