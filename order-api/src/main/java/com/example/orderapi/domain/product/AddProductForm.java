package com.example.orderapi.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProductForm {

    private String name;
    private String description;
    private List<AddProductItemForm> items;
}
