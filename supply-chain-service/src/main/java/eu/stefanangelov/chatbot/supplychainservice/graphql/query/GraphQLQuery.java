package eu.stefanangelov.chatbot.supplychainservice.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import eu.stefanangelov.chatbot.supplychainservice.entities.SalesOrder;
import eu.stefanangelov.chatbot.supplychainservice.repositories.SalesOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 29.12.18.
 */
@RequiredArgsConstructor
@Component
public class GraphQLQuery implements GraphQLQueryResolver {

    private final SalesOrderRepository salesOrderRepository;

    public SalesOrder getSalesOrderById(Long id){
        return salesOrderRepository.findById(id).orElseGet(null);
    }
}
