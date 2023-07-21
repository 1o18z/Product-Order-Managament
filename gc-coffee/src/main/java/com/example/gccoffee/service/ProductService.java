package com.example.gccoffee.service;

import com.example.gccoffee.dto.product.ProductCreateDto;
import com.example.gccoffee.dto.product.ProductMapper;
import com.example.gccoffee.dto.product.ProductResponseDto;
import com.example.gccoffee.dto.product.ProductUpdateDto;
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
  public ProductResponseDto create(ProductCreateDto productCreateDto) {
    productValidator.validName(productCreateDto.productName());
    productValidator.validPrice(productCreateDto.price());

    Product createdProduct = productMapper.toProduct(productCreateDto);
    Product savedProduct = productRepository.insert(createdProduct);
    return productMapper.mapToResponse(savedProduct);
  }

  @Transactional
  public ProductResponseDto update(ProductUpdateDto productUpdateDto) {
    Optional<Product> product = productRepository.findById(productUpdateDto.productId());
    productValidator.validProduct(product);

    productValidator.validName(productUpdateDto.productName());
    productValidator.validPrice(productUpdateDto.price());

    Product updatedProduct = productRepository.update(productUpdateDto);
    return productMapper.mapToResponse(updatedProduct);
  }

  public List<ProductResponseDto> findAll() {
    return productRepository.findAll()
            .stream()
            .map(productMapper::mapToResponse)
            .toList();
  }

  public ProductResponseDto findById(UUID productId) {
    Optional<Product> product = productRepository.findById(productId);
    productValidator.validProduct(product);
    ProductResponseDto productResponseDto = productMapper.mapToResponse(product.get());
    return productResponseDto;
  }

  public ProductResponseDto findByName(String productName) {
    Optional<Product> product = productRepository.findByName(productName);
    productValidator.validProduct(product);
    ProductResponseDto productResponseDto = productMapper.mapToResponse(product.get());
    return productResponseDto;
  }

  public List<ProductResponseDto> findByCategory(Category category) {
    return productRepository.findByCategory(category)
            .stream()
            .map(productMapper::mapToResponse)
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
