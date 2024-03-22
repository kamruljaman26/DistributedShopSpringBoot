package com.shop.order.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "t_order_item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderItem {
    private String skuCode;
    private String quantity;
}
