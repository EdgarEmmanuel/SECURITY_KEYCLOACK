package core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "productList";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String ref, @RequestParam String name) {
        Product product = new Product();
        product.setRef(ref);
        product.setName(name);
        productRepository.save(product);
        return "redirect:/products";
    }

}
