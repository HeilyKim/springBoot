package com.example3.Jul3.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int idx;
    private String name;
    private int price;
    private int quantity;
}

