/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import wad.repository.FileObjectRepository;


/**
 *
 * @author Joonas
 */
@Controller
public class VoteController {
    
    @Autowired
    private FileObjectRepository fileObjectRepository;
    
    @Transactional
    @GetMapping("/voteplus/{id}")
    public String upvote(@PathVariable Long id,Model model){
        this.fileObjectRepository.getOne(id).upVo();
        return "redirect:/pictures";
    }
    
    @Transactional
    @GetMapping("/voteminus/{id}")
    public String downvote(@PathVariable Long id,Model model){
        this.fileObjectRepository.getOne(id).downVo();
        return "redirect:/pictures";
    }
}
