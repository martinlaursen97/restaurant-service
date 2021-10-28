package com.project.restaurantservice.controllers;

import com.project.restaurantservice.models.Product;
import com.project.restaurantservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.restaurantservice.services.ProductService;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
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
        return "redirect:/menu";
    }

    @PostMapping("/add")
    public String add(WebRequest request) {

        try {
            String name = request.getParameter("name");
            Double price = Double.parseDouble(request.getParameter("price"));
            String url = request.getParameter("url");
            String description = request.getParameter("description");

            if (name.length() == 0 || description.length() == 0 || price <= 0) {
                return "addProduct";
            }

            if (url.length() == 0) {
                url = ("https://baregomad.dk/wp-content/uploads/2020/11/pasta-alla-norma.jpg");
            }

            productService.addNewProduct(name, price, url, description);

            return "redirect:/menu";
        } catch (NumberFormatException e) {
            return "addProduct";
        }
    }

    // fix later
    private Long hack;
    @GetMapping("/productConfig")
    public String productConfig(@RequestParam(name="productId") Long productId, Model model) {
        model.addAttribute("productId", productId);
        hack = productId;
        return "productConfig";
    }

    @PostMapping("/productConfigVerify")
    public String productConfigVerify(WebRequest request) {
        Product product = productService.getById(hack);

        try {
            String name = request.getParameter("name");
            Double price = Double.parseDouble(Objects.requireNonNull(request.getParameter("price")));
            String url = request.getParameter("url");
            String description = request.getParameter("description");

            if (name.length() == 0 || description.length() == 0 || price <= 0) {
                return "productConfig";
            }

            assert url != null;
            if (url.length() == 0) {
                url = ("https://baregomad.dk/wp-content/uploads/2020/11/pasta-alla-norma.jpg");
            }

            product.setProductName(name);
            product.setPrice(price);
            product.setImageUrl(url);
            product.setProductDescription(description);
            productService.saveConfig(product);

            return "redirect:/menu";
        } catch (NumberFormatException e) {
            return "productConfig";
        }
    }

    @RequestMapping("/menu")
    public String menu(Model model, WebRequest request) {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        assert user != null;
        Long roleId = user.getUserRole();
        model.addAttribute("products", productService.getProducts());



        if (roleId == 1L) {
            if (request.getAttribute("chosen", WebRequest.SCOPE_SESSION) == null) {
                request.setAttribute("chosen", new ArrayList<Product>(), WebRequest.SCOPE_SESSION);
                request.setAttribute("test", new ArrayList<String>(), WebRequest.SCOPE_SESSION);
            }
            return "menuCustomer";
        }
        return "menuAdmin";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String productSearch(WebRequest request, Model model) {
        String keyword = request.getParameter("keyword");
        List<Product> products = productService.searchFor(keyword);
        model.addAttribute("products", products);

        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Long id = user.getUserRole();

        if (id == 1L) {
            return "menuCustomer";
        }
        return "menuAdmin";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String clear(WebRequest request) {
        request.removeAttribute("chosen", WebRequest.SCOPE_SESSION);
        request.removeAttribute("test", WebRequest.SCOPE_SESSION);
        return "redirect:/menu";
    }

    @RequestMapping("/show")
    public String showOrder(Model model, WebRequest request) {
        List<Product> chosenProducts = (List<Product>) request.getAttribute("chosen", WebRequest.SCOPE_SESSION);
        if (chosenProducts == null || chosenProducts.size() == 0) {
            return "redirect:/menu";
        }
        model.addAttribute("products", chosenProducts);
        model.addAttribute("total", productService.getTotal(chosenProducts));
        return "showOrder";
    }

    @RequestMapping("/test")
    public String chosenTest(@RequestBody(required = false) String[] data, WebRequest request) {
        request.setAttribute("test", data, WebRequest.SCOPE_SESSION);
        request.setAttribute("chosen", productService.getProducts(data), WebRequest.SCOPE_SESSION);
        return "menuCustomer";
    }
}
