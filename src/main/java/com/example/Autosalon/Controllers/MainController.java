package com.example.Autosalon.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String title(Model model) {
        model.addAttribute("title", "It's list of cars");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("about", "Страница про нас");
        return "about";
    }

}