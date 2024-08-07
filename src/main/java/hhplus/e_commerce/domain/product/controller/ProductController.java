package hhplus.e_commerce.domain.product.controller;

import hhplus.e_commerce.support.base.data.ApiOneResult;
import hhplus.e_commerce.domain.product.controller.dto.ProductRequestDto;
import hhplus.e_commerce.domain.product.controller.dto.ProductResponseDto;
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
    public ApiOneResult<ProductResponseDto> registerProduct (@RequestBody ProductRequestDto productResponseDto) {


        Product product = productService.registerProduct(productResponseDto.toProductCreateCommand());

        return new ApiOneResult<>(true, "상품이 등록되었습니다.", productMapper.toDto(product));
    }

    @GetMapping("/")
    public ApiOneResult<List<ProductResponseDto>> getProducts () {
        List<Product> response = productService.getProductList();

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        response.forEach(product -> productResponseDtoList.add(productMapper.toDto(product)));

        return new ApiOneResult<>(true, "", productResponseDtoList);
    }

    @GetMapping("/{id}")
    public ApiOneResult<ProductResponseDto> getProductDetail (@PathVariable long id) {
        Product response = productService.getProductDetail(id);

        return new ApiOneResult<>(productMapper.toDto(response));
    }

    @GetMapping("/topProduct")
    public ApiOneResult<List<ProductResponseDto>> getTopProduct () {
        List<Product> topProduct = productService.findTopProduct(LocalDateTime.now().minusDays(3), LocalDateTime.now());

        List<ProductResponseDto> response = new ArrayList<>();

        topProduct.forEach(product -> {
            response.add(productMapper.toDto(product));
        });

        return new ApiOneResult<>(true, "", response);
    }
}
