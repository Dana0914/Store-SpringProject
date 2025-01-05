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
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final UsersService usersService;
    private final BasketService basketService;
    private final OrderService orderService;

    @Autowired
    public ProductController(ProductService productService,
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

    @GetMapping(value = "/")
    public String homePage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

//    @GetMapping(value = "/page/{pageNo}/{pageSize}")
//    public String findPaginated(Model model,
//                                @PathVariable("pageNo") int pageNo,
//                                @PathVariable("pageSize") int pageSize) {
//
//        Page<Product> pages = productService.findPaginated(pageNo, pageSize);
//        List<Product> products = pages.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", pages.getTotalPages());
//        model.addAttribute("totalItems", pages.getTotalElements());
//        model.addAttribute("product", products);
//
//        return "products";
//    }

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


    @GetMapping("/cart/form")
    public Object getCartForm(Model model) {
        model.addAttribute("cart", new Basket());

        return "cart_form";

    }

    @PostMapping("/cart/products")
    public Object addItemToCart(@ModelAttribute
            Basket basket, Product product, Users user, Model model) {

        basketService.save(basket);

        model.addAttribute("cart", basket);
        model.addAttribute("products", product);
        model.addAttribute("user", user);

        return "redirect:/products/carts";

    }


    @GetMapping("/carts")
    public Object getAllBasketProducts(Model model) {
        List<Basket> all = basketService.findAll();
        model.addAttribute("cart", all);

        long totalAmount = 0L;

        for (Basket b : all) {
            totalAmount += b.getProduct().getPrice() * b.getQuantity();
        }

        model.addAttribute("totalAmount", totalAmount);

        return "cart_list";
    }

    @PostMapping("/cart/{id}/inc")
    public String incrementQuantity(@PathVariable("id") Long id, Model model) {

        Basket basket = basketService.incrementQuantityOfProductsInBasket(id);
        model.addAttribute("basket", basket);

        return "cart_result";

    }

    @PostMapping("/cart/{id}/dec")
    public String decrementQuantity(@PathVariable("id") Long id, Model model) {

        Basket basket = basketService.decrementQuantityOfProductsInBasket(id);
        model.addAttribute("cart", basket);

        return "cart_result";
    }


    @GetMapping(value = "/cart/{id}")
    public Object clearCartItems(@PathVariable("id") long id) {
        basketService.clearCart(id);
        return "redirect:/products/carts";
    }




}

