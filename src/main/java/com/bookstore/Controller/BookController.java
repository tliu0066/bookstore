package com.bookstore.Controller;

import com.bookstore.Entity.Book;
import com.bookstore.Entity.MyBookList;
import com.bookstore.Service.BookService;
import com.bookstore.Service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookListService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister(){
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView GetAllBook(){
        List<Book>list=service.getAllBook();
        ModelAndView m = new ModelAndView();
        m.setViewName("bookList");
        m.addObject("book",list);
        return new ModelAndView("BookList","book",list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b){
        service.save(b);
        return "redirect:/available_books";
    }

    @GetMapping("/my_books")
    public  String getMyBooks(Model model){
        List<MyBookList>list = myBookListService.getAllMyBook();
        model.addAttribute("book",list);
        return "myBooks";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id){
        Book b = service.getBookId(id);
        MyBookList myBookList = new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookListService.saveMyBooks(myBookList);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book b = service.getBookId(id);
        model.addAttribute("book",b);
        return "bookEdit";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:/available_books";
    }

}
