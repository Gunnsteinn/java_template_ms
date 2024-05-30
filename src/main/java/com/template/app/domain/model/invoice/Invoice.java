package com.template.app.domain.model.invoice;

import com.template.app.domain.model.item.Item;
import com.template.app.domain.model.user.User;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Invoice {
    private Long id;
    private String reference;
    private User user;
    private double total;
    private List<Item> items;
    private ZonedDateTime invoiceDate;
    private ZonedDateTime updatedAt;
    private ZonedDateTime createdAt;
}
