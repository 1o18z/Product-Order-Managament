package com.example.gccoffee.controller.api;

import com.example.gccoffee.dto.product.ProductCreateDto;
import com.example.gccoffee.dto.product.ProductResponseDto;
import com.example.gccoffee.dto.product.ProductUpdateDto;
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
  public ResponseEntity<ProductResponseDto> create(@RequestBody ProductCreateDto createProductRequest) {
    ProductResponseDto responseDto = productService.create(createProductRequest);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @PatchMapping("/{productId}")
  public ResponseEntity<ProductResponseDto> update(@PathVariable UUID productId, @RequestBody ProductUpdateDto productUpdateDto) {
    ProductResponseDto responseDto = productService.update(productUpdateDto);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<ProductResponseDto>> findAll() {
    List<ProductResponseDto> responseDtoList = productService.findAll();
    return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponseDto> findById(@PathVariable UUID productId) {
    ProductResponseDto responseDto = productService.findById(productId);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @GetMapping("/name/{productName}")
  public ResponseEntity<ProductResponseDto> findByName(@PathVariable String productName) {
    ProductResponseDto responseDto = productService.findByName(productName);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @GetMapping("/category/{category}")
  public List<ProductResponseDto> findByCategory(@PathVariable Category category) {
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
