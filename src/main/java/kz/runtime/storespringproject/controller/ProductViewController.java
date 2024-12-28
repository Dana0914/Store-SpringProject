package kz.runtime.storespringproject.controller;


import kz.runtime.storespringproject.entities.*;
import kz.runtime.storespringproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;



@Controller
@RequestMapping("/products")
public class ProductViewController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final UsersService usersService;
    private final BasketService basketService;
    private final OrderService orderService;

    @Autowired
    public ProductViewController(ProductService productService,
                                 ReviewService reviewService,
                                 UsersService usersService,
                                 BasketService basketService,
                                 OrderService orderService) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.usersService = usersService;
        this.basketService = basketService;
        this.orderService = orderService;
    }

    @GetMapping("/info/{id}")
    public Object getProductInfo(@PathVariable("id") Long id, Model model) {
        Product productById = productService.findProductById(id);
        model.addAttribute("products", productById);

        Category category = productById.getCategory();
        model.addAttribute("category", category);

        List<Options> options = productById.getCategory().getOptions();
        model.addAttribute("options", options);

        Set<Values> values = productById.getValues();
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

    @GetMapping("/basket/form")
    public Object getBasketForm(Model model) {
        model.addAttribute("basket", new Basket());

        return "basket_form";

    }

    @PostMapping("/basket/products")
    public Object addProductToBasket(@ModelAttribute
            Basket basket, Product product, Users user, Model model) {

        basketService.save(basket);

        model.addAttribute("basket", basket);
        model.addAttribute("products", product);
        model.addAttribute("user", user);

        return "basket_info";

    }


    @GetMapping("/basket/list")
    public Object getAllBasketProducts(Model model) {
        List<Basket> all = basketService.findAll();
        model.addAttribute("basket", all);

        long totalAmount = 0L;

        for (Basket b : all) {
            totalAmount += b.getProduct().getPrice() * b.getQuantity();
        }

        model.addAttribute("totalAmount", totalAmount);

        return "basket_list";
    }

    @PostMapping("/basket/{id}/quantity")
    public String incrementQuantity(@PathVariable("id") Long id, Model model) {

        Basket basket = basketService.incrementQuantityOfProductsInBasket(id);
        model.addAttribute("basket", basket);

        return "basket_result";

    }

    @PostMapping("/basket/{id}")
    public String decrementQuantity(@PathVariable("id") Long id, Model model) {

        Basket basket = basketService.decrementQuantityOfProductsInBasket(id);
        model.addAttribute("basket", basket);

        return "basket_result";
    }


    @GetMapping(value = "/cart/{id}")
    public Object clearCartItems(@PathVariable("id") long id) {
        basketService.clearCart(id);
        return "redirect:/products/basket/list";
    }




}

