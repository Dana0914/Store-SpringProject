package kz.runtime.storespringproject.controller;

import kz.runtime.storespringproject.entities.Category;
import kz.runtime.storespringproject.entities.Product;
import kz.runtime.storespringproject.service.CategoryService;
import kz.runtime.storespringproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;



@Controller
@RequiredArgsConstructor
@RequestMapping("/data_controller")
public class DataController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping(value = "/resource_1")
    public Object firstResource(Model model, @RequestParam(name = "name", required = false) String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            List<Category> categoryList = categoryService.findAll();
            model.addAttribute("categories", categoryList);
            model.addAttribute("form", "categories");
        } else {
            Category categoryByName = categoryService.findCategoryByName(name);
            model.addAttribute("categories", categoryByName);
            model.addAttribute("form", "categories");
        }
        return "resource_view_page_1";
    }


    @GetMapping(value = "/resource_2")
    public Object secondResource(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("form", "categories");
        return "resource_view_page_1";
    }
    @GetMapping(value = "/resource_3")
    public Object thirdResource(Model model, @RequestParam(name = "id", required = false) Long id) {
        Category categoryById = categoryService.findCategoryById(id);
        model.addAttribute("categories", categoryById);
        model.addAttribute("form", "categories");
        return "resource_view_page_1";
    }
    @GetMapping(value = "/resource_4")
    public Object forthResource(Model model, @RequestParam(name = "name", required = false) String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            List<Product> productList = productService.findAll();
            model.addAttribute("products", productList);
            model.addAttribute("form", "products");
        } else {
            Product productByName = productService.findProductByName(name);
            model.addAttribute("products", productByName);
            model.addAttribute("form", "products");
        }
        return "resource_view_page_1";
    }
    @GetMapping(value = "/resource_5")
    public Object fifthResource(Model model, @RequestParam(name = "id", required = false) Long id) {
        Product productById = productService.findProductById(id);
        model.addAttribute("products", productById);
        model.addAttribute("form", "products");
        return "resource_view_page_1";
    }
    @GetMapping(value = "/resource_6")
    public Object sixthResource(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        model.addAttribute("form", "products");
        return "resource_view_page_1";
    }


}
