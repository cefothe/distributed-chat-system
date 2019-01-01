package eu.stefanangelov.chatbot.supplychainservice.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import eu.stefanangelov.chatbot.supplychainservice.entities.OrderStatus;
import eu.stefanangelov.chatbot.supplychainservice.entities.SalesOrder;
import eu.stefanangelov.chatbot.supplychainservice.repositories.SalesOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 29.12.18.
 */
@RequiredArgsConstructor
@Component
public class GraphQLQuery implements GraphQLQueryResolver {

    private final SalesOrderRepository salesOrderRepository;

    public SalesOrder getSaleOrderById(Long id){
        return salesOrderRepository.findById(id).orElseGet(null);
    }

    public List<SalesOrder> getSaleOrdersByOrderStatus(OrderStatus orderStatus){
        List<SalesOrder> list = new ArrayList<>();
        salesOrderRepository.findByOrderStatus(orderStatus).forEach(x -> list.add(x));
        return list;
    }

    public List<SalesOrder> getSaleOrders(){
        List<SalesOrder> list = new ArrayList<>();
         salesOrderRepository.findAll().forEach(x -> list.add(x));
         return list;
    }
}
