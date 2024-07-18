package hhplus.e_commerce.domain.customer.controller.dto.mapper;

import hhplus.e_commerce.domain.customer.controller.dto.CustomerResponseDto;
import hhplus.e_commerce.domain.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponseDto toDto(Customer customer);
}
