package com.template.app.domain.model.organization;

import com.template.app.domain.model.invoice.Invoice;
import com.template.app.domain.model.item.Item;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Organization {
    private Long id;
    private String reference;
    private String organizationName;
    private String location;
    private List<Item> items;
    private List<Invoice> invoices;
    private boolean isActive;
    private ZonedDateTime updatedAt;
    private ZonedDateTime createdAt;

}
