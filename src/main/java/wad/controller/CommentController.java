
package wad.controller;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Comment;
import wad.repository.CommentRepository;
import wad.repository.FileObjectRepository;

/**
 *
 * @author Joonas
 */
//Controller for posting commment
@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private FileObjectRepository fileObjectRepository;
    //Creating comment and connecting it to FileObject
    @Transactional
    @PostMapping("/comments/{id}")
    public String comment(@RequestParam String title, @RequestParam String content,@PathVariable Long id){
        Comment comment = new Comment(title,content,this.fileObjectRepository.getOne(id).getId());
        comment = this.commentRepository.save(comment);
        List<Comment> comments = this.fileObjectRepository.getOne(id).getComments();
        comments.add(comment);
        System.out.println(comments);
        this.fileObjectRepository.getOne(id).setComments(comments);
        this.fileObjectRepository.getOne(id).score();
        System.out.println(this.fileObjectRepository.getOne(id).getComments());
        return "redirect:/pictures";
    }
}
