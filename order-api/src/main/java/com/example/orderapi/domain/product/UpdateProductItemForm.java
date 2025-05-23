package com.example.orderapi.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductItemForm {

    private Long id;
    private Long productId;
    private String name;
    private Integer price;
    private Integer count;
}
