package hhplus.e_commerce.domain.product.controller;

import hhplus.e_commerce.base.data.ApiOneResult;
import hhplus.e_commerce.domain.product.controller.dto.ProductDto;
import hhplus.e_commerce.domain.product.controller.dto.ProductOptionDto;
import hhplus.e_commerce.domain.product.controller.dto.mapper.ProductMapper;
import hhplus.e_commerce.domain.product.controller.dto.mapper.ProductOptionMapper;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.entity.ProductOption;
import hhplus.e_commerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final ProductMapper productMapper;

    private final ProductOptionMapper productOptionMapper;

    /**
     * 1. 상품 리스트 조회
     * 2. 상품 조회
     * 3. 인기 상품 Top5 조회 (최근 3일)
     */

    @GetMapping("/")
    public ApiOneResult<List<ProductDto>> getProducts () {
        List<Product> response = productService.getProductList();

        List<ProductDto> productDtoList = new ArrayList<>();

        response.forEach(product -> productDtoList.add(productMapper.toDto(product)));

        return new ApiOneResult<>(true, "", productDtoList);
    }

    @GetMapping("/{id}")
    public ApiOneResult<ProductDto> getProductDetail (@PathVariable long id) {
        Product response = productService.getProductDetail(id);

        return new ApiOneResult<>(productMapper.toDto(response));
    }

    @GetMapping("/Top5List")
    public ApiOneResult<List<ProductOptionDto>> showTop5 () {
        List<ProductOption> productTop5 = productService.getProductTop5();

        List<ProductOptionDto> response = new ArrayList<>();

        productTop5.forEach(productOption -> {
            response.add(productOptionMapper.toDto(productOption));
        });

        return new ApiOneResult<>(true, "", response);
    }
}
