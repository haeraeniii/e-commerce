package hhplus.e_commerce.domain.product.controller;

import hhplus.e_commerce.data.ApiOneResult;
import hhplus.e_commerce.domain.product.controller.dto.ProductOptionResponseDto;
import hhplus.e_commerce.domain.product.controller.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    /**
     * 1. 상품 리스트 조회
     * 2. 상품 조회
     * 3. 인기 상품 Top5 조회 (최근 3일)
     */

    @GetMapping("/")
    public ApiOneResult<List<ProductResponseDto>> showProducts () {

        return new ApiOneResult(new ArrayList<ProductResponseDto>());
    }

    @GetMapping("/productDetail/{id}")
    public ApiOneResult<ProductOptionResponseDto> showProductDetail (@PathVariable String id) {

        return new ApiOneResult(new ProductOptionResponseDto(1, "블라우스", "pink", "M", 30000, 20));
    }

    @GetMapping("/productTop5")
    public ApiOneResult<List<ProductResponseDto>> showTop5 () {

        return new ApiOneResult(new ArrayList<ProductResponseDto>());
    }
}
