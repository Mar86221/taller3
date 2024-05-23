package org.luismore.taller3.controllers;

import org.luismore.taller3.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library")
@Slf4j
public class LibraryController {
    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public String getAllBooks(Model model){
        model.addAttribute("books", bookService.findAll());
        return "book-list";
    }

    @GetMapping("/rest")
    public String mainRest(Model model){
        model.addAttribute("books", bookService.findAll());
        return "index-rest";
    }

    @GetMapping("/")
    public String getSaveForm(){
        return "save-book";
    }

//    @PostMapping("/save")
//    public String saveBook(@ModelAttribute @Valid SaveBookDTO info, BindingResult errors){
//
//        if (errors.hasErrors()){
//            log.error("ha habido un error al enviar el formulario: {}", errors.getAllErrors());
//            return "errorsito";
//        }
//
//        Category category = categoryService.findCategoryByCode(info.getCategory());
//        bookService.save(info);
//
//        return "redirect:/library/all";
//    }
}
