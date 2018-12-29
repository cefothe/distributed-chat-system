package eu.stefanangelov.chatbot.supplychainservice.repositories;

import eu.stefanangelov.chatbot.supplychainservice.entities.SalesOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 29.12.18.
 */
@Repository
public interface SalesOrderRepository extends CrudRepository<SalesOrder, Long> {
}
