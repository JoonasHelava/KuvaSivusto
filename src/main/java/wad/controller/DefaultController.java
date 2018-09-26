package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import wad.repository.FileObjectRepository;
//For directing you to main page
@Controller
public class DefaultController {

    @Autowired
    private FileObjectRepository fileObjectRepository;
    
    //Sets TOP3 in main page
    @GetMapping("/")
    public String redirect(Model model) {
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.ASC, "score");
        model.addAttribute("pictures", this.fileObjectRepository.findAll(pageable));
        return "main";
    }
//    @GetMapping("/login")
//    public String login(){
//        return"login";
//    }
    
}
