package eu.stefanangelov.chatbot.supplychainservice.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 29.12.18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SalesOrder extends  BaseEntity{

    private Long orderValue;
    private LocalDateTime recordCreated;
    private Long quantity;
    private LocalDateTime requestedShipDate;
    private OrderStatus orderStatus;
    private LocalDateTime estimatedDeliveryDate;
    private LocalDateTime plannedShipDate;

}
