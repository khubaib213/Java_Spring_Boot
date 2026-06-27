package com.example.myJar;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public List<Product> getAll() {
        return productRepository.findAll();
    }


    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        return productRepository.findById(id).orElse(null);
    }


    @PostMapping("/add")
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }


    @PutMapping("/{id}")
    public Product update(
            @PathVariable int id,
            @RequestBody Product updated
    ) {

        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            product.setName(updated.getName());
            product.setPrice(updated.getPrice());

            return productRepository.save(product);
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {

        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Product " + id + " deleted";
        }

        return "Product not found";
    }
}