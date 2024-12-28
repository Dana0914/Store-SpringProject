package kz.runtime.storespringproject.controller;

import kz.runtime.storespringproject.entities.*;
import kz.runtime.storespringproject.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final OrderService orderService;
    private final UsersService usersService;
    private final BasketService basketService;

    public AdminController(ProductService productService,
                           ReviewService reviewService,
                           OrderService orderService,
                           UsersService usersService,
                           BasketService basketService) {

        this.productService = productService;
        this.reviewService = reviewService;
        this.orderService = orderService;
        this.usersService = usersService;
        this.basketService = basketService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get/form")

    public Object getProductCreate(Model model) {
        model.addAttribute("products", new Product());

        return "product_view";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("create/form")
    public Object createProduct(@ModelAttribute Product product, Model model) {
        productService.createProduct(product);
        model.addAttribute("products", product);

        return "result";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get/edit/{id}")
    public Object getEditProduct(@PathVariable("id") Long id, Model model) {
        Product productById = productService.findProductById(id);
        model.addAttribute("products", productById);

        return "fill_out_products";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edit")
    public Object editProduct(@ModelAttribute Product product, Model model) {
        productService.updateProduct(product.getId(), product);
        model.addAttribute("products", product);

        return "result";

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/review/form")
    public String getReviewForm(Model model) {

        model.addAttribute("review", new Review());

        return "review_form";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/review/info")
    public Object createReview(@ModelAttribute("review") Review review,
                               Model model) {

        Product existingProduct = productService.findProductById(review.getProduct().getId());
        Users existingUser = usersService.findUsersById(review.getUsers().getId());

        review.setProduct(existingProduct);
        review.setUsers(existingUser);

        reviewService.createReview(review);

        model.addAttribute("review", review);
        model.addAttribute("product", existingProduct);
        model.addAttribute("user", existingUser);

        return "review_info";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/order/form")
    public String getOrderForm(Model model) {
        model.addAttribute("orders", new Orders());

        return "orders_form";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/order/address")
    public String addUserAddress(Model model, Orders orders) {
        List<Basket> baskets = basketService.findAll();
        List<Product> products = new ArrayList<>();

        for (Basket b : baskets) {
            products.add(b.getProduct());
            orders.setProduct(b.getProduct());
        }
        for (Basket b : baskets) {
            orders.setUsers(b.getUsers());
        }

        orders.setAddress(orders.getAddress());
        orders.setItems(products);

        orderService.save(orders);

        model.addAttribute("orders", orders);


        return "basket_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/orders")
    public String getOrderList(Model model) {
        List<Orders> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }



}
