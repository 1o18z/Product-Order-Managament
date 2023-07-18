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
@RequestMapping("/product")
public class ProductRestController {

  private final ProductService productService;

  public ProductRestController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping("/create")
  public ResponseEntity<ProductResponseDto> create(@RequestBody ProductCreateDto createProductRequest) {
    ProductResponseDto responseDto = productService.create(createProductRequest);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @GetMapping("/{productId}/update")
  public String updateForm(@PathVariable UUID productId) {
    return "products/update-product";
  }

  @PutMapping("/{productId}/update")
  public ResponseEntity<ProductResponseDto> update(@PathVariable UUID productId, ProductUpdateDto productUpdateDto) {
    ProductResponseDto responseDto = productService.update(productUpdateDto);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @GetMapping("/list")
  public ResponseEntity<List<ProductResponseDto>> findAll() {
    List<ProductResponseDto> responseDtoList = productService.findAll();
    return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponseDto> findById(@PathVariable UUID productId) {
    ProductResponseDto responseDto = productService.findById(productId);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponseDto> findByName(@RequestParam(value = "productId") String name) {
    ProductResponseDto responseDto = productService.findByName(name);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @GetMapping("/list")
  public List<ProductResponseDto> findByCategory(Category category) {
    return productService.findByCategory(category);
  }

  @DeleteMapping("/list")
  public void deleteAll() {
    productService.deleteAll();
  }

  @DeleteMapping("/{productId}")
  public void deleteById(UUID productId) {
    productService.deleteById(productId);
  }

}
