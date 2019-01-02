package eu.stefanangelov.chatbot.supplychainservice.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import eu.stefanangelov.chatbot.supplychainservice.entities.OrderStatus;
import eu.stefanangelov.chatbot.supplychainservice.entities.SalesOrder;
import eu.stefanangelov.chatbot.supplychainservice.repositories.SalesOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 29.12.18.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class GraphQLQuery implements GraphQLQueryResolver {

    private final SalesOrderRepository salesOrderRepository;

    public SalesOrder getSalesOrderById(Long id){
        log.info("Get sales order by id {}", id);
        return salesOrderRepository.findById(id).orElseGet(null);
    }

    public List<SalesOrder> getSalesOrdersByOrderStatus(OrderStatus orderStatus){
        log.info("Get sales order by order status {}", orderStatus);
        List<SalesOrder> list = new ArrayList<>();
        salesOrderRepository.findByOrderStatus(orderStatus).forEach(x -> list.add(x));
        return list;
    }

    public List<SalesOrder> getSalesOrders(){
        log.info("Get all sales orders");
        List<SalesOrder> list = new ArrayList<>();
         salesOrderRepository.findAll().forEach(x -> list.add(x));
         return list;
    }
}
