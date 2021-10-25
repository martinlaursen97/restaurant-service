package com.project.restaurantservice.controllers;


import com.project.restaurantservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.restaurantservice.services.ProductService;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Objects;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/addProduct")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @RequestMapping(value = "/removeProduct", method = RequestMethod.GET)
    public String removeProduct(@RequestParam(name="productId") Long productId) {
        productService.removeProductById(productId);
        return "redirect:/menuAdmin";
    }

    @PostMapping("/productVerify")
    public String productVerify(Product product) {
        if (product.getProductName() == null ||  product.getPrice() == null || product.getProductDescription() == null) {
            return "addProduct";
        }

        if (product.getProductName().length() < 1 || product.getPrice() <= 0 || product.getProductDescription().length() < 1) {
            return "addProduct";
        }

        if (product.getImageUrl().length() == 0) {
            product.setImageUrl("https://baregomad.dk/wp-content/uploads/2020/11/pasta-alla-norma.jpg");
        }
        productService.addNewProduct(product);
        return "redirect:/menuAdmin";
    }

    @PostMapping("/productConfigVerify")
    public String productConfigVerify(Product product) {
        if (product.getProductName() == null ||  product.getPrice() == null || product.getProductDescription() == null) {
            return "productConfig";
        }

        if (product.getProductName().length() < 1 || product.getPrice() <= 0 || product.getProductDescription().length() < 1) {
            return "productConfig";
        }

        if (product.getImageUrl().length() == 0) {
            product.setImageUrl("https://baregomad.dk/wp-content/uploads/2020/11/pasta-alla-norma.jpg");
        }

        product.setProductId(hack);
        productService.saveConfig(product);
        return "redirect:/menuAdmin";
    }

    // ... :/
    private Long hack = 0L;
    @RequestMapping(value = "/productConfig", method = RequestMethod.GET)
    public String productConfig(WebRequest request, Model model) {
        Long productId = Long.parseLong(Objects.requireNonNull(request.getParameter("productId")));
        Product product = productService.getById(productId);
        hack = product.getProductId();
        model.addAttribute("product", product);
        return "productConfig";
    }

    @RequestMapping("/menuAdmin")
    public String menuAdmin(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "menuAdmin";
    }

    @RequestMapping("/menuCustomer")
    public String menuCustomer(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "menuCustomer";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String productSearch(WebRequest request, Model model) {
        String keyword = request.getParameter("keyword");
        List<Product> products = productService.searchFor(keyword);
        model.addAttribute("products2", products);
        return "productSearch";
    }

    @RequestMapping("/finish1")
    public String finishOrder(Model model) {
        model.addAttribute("products", hack2);
        return "finishOrder";
    }

    List<Product> hack2;
    @RequestMapping(value = "/finish2", method = RequestMethod.POST)
    public void chosenProducts(@RequestBody(required = false) String[] data) {
        hack2 = productService.getProductsName(data);
    }
}
