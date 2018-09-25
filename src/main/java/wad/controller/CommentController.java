/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.util.List;
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
@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private FileObjectRepository fileObjectRepository;
    
    @PostMapping("/comments/{id}")
    public String comment(@RequestParam String title, @RequestParam String content,@PathVariable Long id){
        Comment comment = new Comment();
        comment.setTitle(title);
        comment.setContent(content);
        comment.setOb(this.fileObjectRepository.getOne(id).getId());
        comment = this.commentRepository.save(comment);
        List<Comment> comments = this.fileObjectRepository.getOne(id).getComments();
        comments.add(comment);
        this.fileObjectRepository.getOne(id).setComments(comments);
        this.fileObjectRepository.getOne(id).score();
        return "redirect:/pictures";
    }
}
