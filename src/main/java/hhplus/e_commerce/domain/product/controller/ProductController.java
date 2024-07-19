package hhplus.e_commerce.domain.product.controller;

import hhplus.e_commerce.base.data.ApiOneResult;
import hhplus.e_commerce.base.data.ApiResult;
import hhplus.e_commerce.domain.order.service.OrderService;
import hhplus.e_commerce.domain.product.controller.dto.ProductDto;
import hhplus.e_commerce.domain.product.controller.dto.ProductOptionDto;
import hhplus.e_commerce.domain.product.controller.dto.mapper.ProductMapper;
import hhplus.e_commerce.domain.product.controller.dto.mapper.ProductOptionMapper;
import hhplus.e_commerce.domain.product.entity.Product;
import hhplus.e_commerce.domain.product.entity.ProductOption;
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


}
