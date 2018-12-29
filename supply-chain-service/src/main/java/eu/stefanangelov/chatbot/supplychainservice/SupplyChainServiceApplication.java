package eu.stefanangelov.chatbot.supplychainservice;

import eu.stefanangelov.chatbot.supplychainservice.entities.OrderStatus;
import eu.stefanangelov.chatbot.supplychainservice.entities.SalesOrder;
import eu.stefanangelov.chatbot.supplychainservice.repositories.SalesOrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class SupplyChainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyChainServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(SalesOrderRepository salesOrderRepository){
		return args -> {
			salesOrderRepository.save(new SalesOrder(1L, LocalDateTime.now(), 1L, LocalDateTime.now(), OrderStatus.CONFIRMED, LocalDateTime.now(), LocalDateTime.now()));
			salesOrderRepository.save(new SalesOrder(5L, LocalDateTime.now(), 5L, LocalDateTime.now(), OrderStatus.CLOSED, LocalDateTime.now(), LocalDateTime.now()));
		};
	}

}

