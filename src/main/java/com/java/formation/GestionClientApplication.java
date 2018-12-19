package com.java.formation;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.java.formation.client.entity.Client;
import com.java.formation.client.service.ClientService;

@SpringBootApplication
public class GestionClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionClientApplication.class, args);
	}
	
//	
//	
//	
//
//        @Bean
//        public CommandLineRunner demo(ClientService clientService ) {
//                return (args) -> {
//                    
//                    System.out.println("STEP");
//                    Client client = new Client();
//                     client.setNom("ABCD");
//                     client.setPrenom("Mohamed");
//                     client.setDateNaissance(new Date());
//                     client.setEmail("abcd@gmail.com");
//                     client.setCategorie("senior");
//                     
//                     
//                     Client client2 = new Client();
//                     client2.setNom("DUBON");
//                     client2.setPrenom("LOIC");
//                     client2.setDateNaissance(new Date());
//                     client2.setEmail("LOLO@gmail.com");
//                     client2.setCategorie("senior");
//                     
//                     Client client3 = new Client();
//                     client3.setNom("YVES");
//                     client3.setPrenom("ANTOINE");
//                     client3.setDateNaissance(new Date());
//                     client3.setEmail("YVES@gmail.com");
//                     client3.setCategorie("parent");
//                     
//                     
//                     clientService.add(client);
//                     clientService.add(client2);
//                     clientService.add(client3);
//                     List<Client> list = clientService.getAllClient();
//                     System.out.println("Step " +list.size());
//                };
//        }
//	
//	
//	
	
	
}
