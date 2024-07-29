package kz.runtime.storespringproject.controller;


import kz.runtime.storespringproject.entities.Category;
import kz.runtime.storespringproject.entities.Product;
import kz.runtime.storespringproject.service.CategoryService;
import kz.runtime.storespringproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductViewController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/get/form")
    public Object getProductCreate(Model model) {
        model.addAttribute("products", new Product());
        return "product_view";
    }

    @PostMapping("create/form")
    public Object createProduct(@ModelAttribute Product product, Model model) {
        productService.createProduct(product);
        model.addAttribute("products", product);
        return "result";
    }


    @GetMapping("/get/edit/{id}")
    public Object getEditProduct(@PathVariable("id") Long id, Model model) {
        Product productById = productService.findProductById(id);
        model.addAttribute("products", productById);
        return "fill_out_products";
    }

    @PostMapping("/edit")
    public Object editProduct(@ModelAttribute Product product, Model model) {
        productService.updateProduct(product.getId(), product);
        model.addAttribute("products", product);
        return "result";
    }

    @GetMapping("/info/{id}")
    public Object getProductInfo(@PathVariable("id") Long id, Model model) {
        Product productById = productService.findProductById(id);
        model.addAttribute("products", productById);
        return "product_info";
    }


    @GetMapping(value = "/all")
    public Object getAllProducts(Model model) {
        List<Product> all = productService.findAll();
        model.addAttribute("product", all);
        return "products";
    }


}
