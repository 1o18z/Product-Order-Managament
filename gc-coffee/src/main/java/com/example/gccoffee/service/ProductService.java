package com.example.gccoffee.service;

import com.example.gccoffee.dto.product.ProductCreateRequest;
import com.example.gccoffee.dto.product.ProductMapper;
import com.example.gccoffee.dto.product.ProductResponse;
import com.example.gccoffee.dto.product.ProductUpdateRequest;
import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.repository.ProductRepository;
import com.example.gccoffee.validator.ProductValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
public class ProductService {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;
  private final ProductValidator productValidator;

  public ProductService(ProductMapper productMapper, ProductRepository productRepository, ProductValidator productValidator) {
    this.productMapper = productMapper;
    this.productRepository = productRepository;
    this.productValidator = productValidator;
  }

  @Transactional
  public ProductResponse create(ProductCreateRequest productCreateRequest) {
    productValidator.validName(productCreateRequest.productName());
    productValidator.validPrice(productCreateRequest.price());
    Category.findCategory(productCreateRequest.category());

    Product createdProduct = productMapper.toProduct(productCreateRequest);
    Product savedProduct = productRepository.insert(createdProduct);
    Product createdProduct = productMapper.toProduct(productCreateDto);
    Product savedProduct = productRepository.save(createdProduct);
    return productMapper.toResponse(savedProduct);
  }

  @Transactional
  public ProductResponse update(ProductUpdateRequest productUpdateDto) {
    Optional<Product> product = productRepository.findById(productUpdateDto.productId());
    productValidator.validProduct(product);

    productValidator.validName(productUpdateDto.productName());
    productValidator.validPrice(productUpdateDto.price());

    Product updatedProduct = productRepository.update(productUpdateDto);
    return productMapper.toResponse(updatedProduct);
  }

  public List<ProductResponse> findAll() {
    return productRepository.findAll()
            .stream()
            .map(productMapper::toResponse)
            .toList();
  }

  public ProductResponse findById(UUID productId) {
    Optional<Product> product = productRepository.findById(productId);
    productValidator.validProduct(product);
    ProductResponse productResponseDto = productMapper.toResponse(product.get());
    return productResponseDto;
  }

  public ProductResponse findByName(String productName) {
    Optional<Product> product = productRepository.findByName(productName);
    productValidator.validProduct(product);
    ProductResponse productResponseDto = productMapper.toResponse(product.get());
    return productResponseDto;
  }

  public List<ProductResponse> findByCategory(Category category) {
    return productRepository.findByCategory(category)
            .stream()
            .map(productMapper::toResponse)
            .toList();
  }

  @Transactional
  public void deleteAll() {
    productRepository.deleteAll();
  }

  @Transactional
  public void deleteById(UUID productId) {
    productRepository.deleteById(productId);
  }

}
