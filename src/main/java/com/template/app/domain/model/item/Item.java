package com.template.app.domain.model.item;

import lombok.Data;

@Data
public class Item {
    private Long id;
    private String reference;
    private String name;
    private int quantity;
    private double price;
}
