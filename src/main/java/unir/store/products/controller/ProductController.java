package unir.store.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import unir.store.products.dto.ProductDTO;
import unir.store.products.entity.Product;
import unir.store.products.exception.GenericException;
import unir.store.products.service.Interface.IProductService;
import unir.store.products.utils.GenericBasicResponse;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    private GenericBasicResponse<ProductDTO> response;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getProducts() throws GenericException {
        return this.productService.getAllProducts();
    }
    @GetMapping("id/{idProduct}")
    public Product getProductById(@RequestParam String idProduct) throws GenericException {
        return this.productService.getProductById(idProduct);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericBasicResponse<ProductDTO> createProduct(@RequestBody ProductDTO request) throws GenericException {
        response = new GenericBasicResponse<>();
        response.setData(this.productService.createProduct(request));
        return response;
    }
    
    

}
