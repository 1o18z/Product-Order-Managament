package com.example.gccoffee.controller.api;

import com.example.gccoffee.dto.product.ProductCreateRequest;
import com.example.gccoffee.dto.product.ProductResponse;
import com.example.gccoffee.dto.product.ProductUpdateRequest;
import com.example.gccoffee.model.Category;
import com.example.gccoffee.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

  private final ProductService productService;

  public ProductRestController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<ProductResponse> create(@RequestBody ProductCreateRequest createProductRequest) {
    ProductResponse responseDto = productService.create(createProductRequest);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @PatchMapping("/{productId}")
  public ResponseEntity<ProductResponse> update(@PathVariable UUID productId, @RequestBody ProductUpdateRequest productUpdateDto) {
    ProductResponse responseDto = productService.update(productUpdateDto);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> findAll() {
    List<ProductResponse> responseDtoList = productService.findAll();
    return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> findById(@PathVariable UUID productId) {
    ProductResponse responseDto = productService.findById(productId);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @GetMapping("/name/{productName}")
  public ResponseEntity<ProductResponse> findByName(@PathVariable String productName) {
    ProductResponse responseDto = productService.findByName(productName);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @GetMapping("/category/{category}")
  public List<ProductResponse> findByCategory(@PathVariable Category category) {
    return productService.findByCategory(category);
  }

  @DeleteMapping
  public void deleteAll() {
    productService.deleteAll();
  }

  @DeleteMapping("/{productId}")
  public void deleteById(@PathVariable UUID productId) {
    productService.deleteById(productId);
  }

}
