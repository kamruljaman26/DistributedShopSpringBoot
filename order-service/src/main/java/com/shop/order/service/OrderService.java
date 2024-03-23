package com.shop.order.service;


import com.shop.order.dto.OrderItemDto;
import com.shop.order.dto.OrderRequest;
import com.shop.order.dto.OrderResponse;
import com.shop.order.model.Order;
import com.shop.order.model.OrderItem;
import com.shop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
//    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItem> orderLineItems = orderRequest.getOrderItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderItemList(orderLineItems);

        List<String> skuCodes = order.getOrderItemList().stream()
                .map(OrderItem::getSkuCode)
                .toList();

        orderRepository.save(order);
    }

    private OrderItem mapToDto(OrderItemDto orderItemDto) {
        OrderItem orderLineItems = new OrderItem();
        orderLineItems.setSkuCode(orderItemDto.getSkuCode());
        orderLineItems.setQuantity(orderItemDto.getQuantity());
        return orderLineItems;
    }

    public List<OrderResponse> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderResponse mapToOrderResponse(Order order) {
        List<OrderItemDto> orderItemList = new ArrayList<>();
        for (OrderItem orderItem:order.getOrderItemList()){
            orderItemList.add(OrderItemDto.builder()
                    .skuCode(orderItem.getSkuCode())
                    .quantity(orderItem.getQuantity())
                    .build()
            );
        }

        return OrderResponse.builder()
                .orderNumber(order.getOrderNumber())
                .id(order.getId())
                .orderItemsList(orderItemList)
                .build();
    }

}
