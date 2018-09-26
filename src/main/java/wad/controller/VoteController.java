
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
//Vote controller for routes /voteplus/{id} and /voteminus/{id}
@Controller
public class VoteController {
    
    //FileObject contains the information about votes
    @Autowired
    private FileObjectRepository fileObjectRepository;
    
    //Using method upVo / downVo to give upvote /downvote and then valueting new score for FileObject
    @Transactional
    @GetMapping("/voteplus/{id}")
    public String upvote(@PathVariable Long id,Model model){
        this.fileObjectRepository.getOne(id).upVo();
        this.fileObjectRepository.getOne(id).score();
        return "redirect:/pictures";
    }
    
    @Transactional
    @GetMapping("/voteminus/{id}")
    public String downvote(@PathVariable Long id,Model model){
        this.fileObjectRepository.getOne(id).downVo();
        this.fileObjectRepository.getOne(id).score();
        return "redirect:/pictures";
    }
}
