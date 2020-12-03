package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.Category;
import com.codegym.service.BookService;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @GetMapping("/categories")
    public ModelAndView listCategory(){
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }
    @GetMapping("/create-category")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
    @PostMapping("/create-category")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);

        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message","New category created successfully");
        return modelAndView;
    }
//    @GetMapping("/edit-province/{id}")
//    public ModelAndView showEditForm(@PathVariable Long id){
//        Province province = provinceService.findById(id);
//        ModelAndView modelAndView;
//        if(province != null) {
//            modelAndView = new ModelAndView("/province/edit");
//            modelAndView.addObject("province", province);
//        }else {
//            modelAndView = new ModelAndView("/error.404");
//        }
//        return modelAndView;
//    }
//    @PostMapping("/edit-province")
//    public ModelAndView updateProvince(@ModelAttribute("province") Province province) {
//        provinceService.save(province);
//        ModelAndView modelAndView = new ModelAndView("/province/edit");
//        modelAndView.addObject("province", province);
//        modelAndView.addObject("message", "Province updated successfully");
//        return modelAndView;
//    }
//    @GetMapping("/delete-province/{id}")
//    public ModelAndView showDeleteForm(@PathVariable Long id){
//        Province province = provinceService.findById(id);
//        ModelAndView modelAndView;
//        if(province != null) {
//            modelAndView = new ModelAndView("/province/delete");
//            modelAndView.addObject("province",province);
//        }else {
//            modelAndView = new ModelAndView("/error.404");
//        }
//        return modelAndView;
//    }
//    @PostMapping("/delete-province")
//    public String deleteProvince(@ModelAttribute("province") Province province) {
//        provinceService.remove(province.getId());
//        return "redirect:provinces";
//    }

    @GetMapping("/view-province/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        if(category == null){
            return new ModelAndView("/error.404");
        }

        Iterable<Book> books = bookService.findAllByCategory(category);

        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", category);
        modelAndView.addObject("books", books);
        return modelAndView;
    }



}
