package kz.runtime.storespringproject.controller;


import kz.runtime.storespringproject.entities.*;
import kz.runtime.storespringproject.service.*;
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
    private final OptionsService optionsService;
    private final ValuesService valuesService;
    private final ReviewService reviewService;
    private final UsersService usersService;
    private final BasketService basketService;

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

        Category category = categoryService.findCategoryById(productById.getCategory().getId());
        model.addAttribute("category", category);

        List<Options> options = optionsService.findOptionsById(category.getId());
        model.addAttribute("options", options);

        List<Values> values = valuesService.findValuesByOptionId(productById.getId());
        model.addAttribute("values", values);

        Double averageRating = reviewService.getReviewsWithAverageRating();
        model.addAttribute("averageRating", averageRating);

        List<Review> reviewByProductId = reviewService.findReviewByProductId(productById.getId());
        model.addAttribute("reviews", reviewByProductId);


        return "product_info";
    }


    @GetMapping(value = "/all")
    public Object getAllProducts(Model model) {
        List<Product> all = productService.findAll();
        model.addAttribute("product", all);

        return "products";
    }


    @GetMapping("/review/form")
    public String getReviewForm(Model model) {

        model.addAttribute("review", new Review());

        return "review_form";
    }

    @PostMapping("/review/info")
    public Object createReview(@ModelAttribute("review") Review review,
                               Model model) {


        Product existingProduct = productService.findProductById(review.getProduct().getId());
        Users existingUser = usersService.findUserById(review.getUsers().getId());

        review.setProduct(existingProduct);
        review.setUsers(existingUser);



        reviewService.saveReview(review);


        model.addAttribute("review", review);
        model.addAttribute("product", existingProduct);
        model.addAttribute("user", existingUser);


        return "review_info";
    }

    @PostMapping("/basket/products")
    public Object addProductToBasket(@ModelAttribute
            Product product, Basket basket, Users users, Model model) {

        basket.setProduct(product);
        basket.setUsers(users);

        basketService.save(basket);

        model.addAttribute("basket", basket);
        model.addAttribute("products", product);
        model.addAttribute("user", users);

        return "basket_info";

    }

    @GetMapping("/basket/form")
    public Object getBasketForm(Model model) {
        model.addAttribute("basket", new Basket());

        return "basket_form";

    }

    @GetMapping("/basket/list")
    public Object getAllBasketProducts(Model model) {
        List<Basket> all = basketService.findAll();

        model.addAttribute("basket", all);
        return "basket_result";
    }
}
