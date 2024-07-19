package hhplus.e_commerce.domain.product.controller;

import hhplus.e_commerce.base.data.ApiOneResult;
import hhplus.e_commerce.base.data.ApiResult;
import hhplus.e_commerce.domain.product.controller.dto.ProductDto;
import hhplus.e_commerce.domain.product.controller.dto.mapper.ProductMapper;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final ProductMapper productMapper;

    /**
     * 1. 상품 리스트 조회
     * 2. 상품 조회
     * 3. 인기 상품 Top5 조회 (최근 3일)
     */

    @PostMapping("/register")
    public ApiResult registerProduct (@RequestBody ProductDto productDto) {
        Product response = productMapper.toEntity(productDto);

        productService.registerProduct(response);

        return new ApiResult(true, "상품이 등록되었습니다.");
    }

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

    @GetMapping("/topProduct")
    public ApiOneResult<List<ProductDto>> getTopProduct () {
        List<Product> topProduct = productService.findTopProduct(LocalDateTime.now().minusDays(3), LocalDateTime.now());

        List<ProductDto> response = new ArrayList<>();

        topProduct.forEach(product -> {
            response.add(productMapper.toDto(product));
        });

        return new ApiOneResult<>(true, "", response);
    }
}
