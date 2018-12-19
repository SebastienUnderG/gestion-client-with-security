package com.java.formation.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.formation.client.dto.Customer;
import com.java.formation.client.dto.Message;
import com.java.formation.client.entity.Client;
import com.java.formation.client.service.ClientService;
import com.java.formation.client.util.Utils;
import com.java.formation.client.util.Constants;

/**
 * Application de démonstration. Classe utilitaire qui met à disposition les
 * services d'un client
 * 
 * @author Tarik EL AKKANI (tarik.el.akkani@gmail.com)
 */
@Controller
@RequestMapping("/v1")
public class ClientController {

    @Autowired
    ClientService clientService;

    // ******************* Liste des srvices pour manipuler des users: *********************************//
    // Ajout, Supression, affichage ....
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {

        Client client = clientService.find(id);

        if (client != null) {
            Customer customer = Utils.mapToCustomer(client);
            return new ResponseEntity(customer, HttpStatus.OK);
        }

        return new ResponseEntity(new Message(HttpStatus.NOT_FOUND.value(), Constants.ERROR_MESSAGE_NOT_FOUND), HttpStatus.NOT_FOUND);

    }

    @PostMapping("/client")
    public ResponseEntity<Object> addClient(@RequestBody Customer customer) {

        customer.setProducts(null);
        Client client = Utils.mapToClient(customer);
        if (clientService.add(client)) {
            Message message = new Message(client.getId().intValue(), Constants.SUCCES_MESSAGE_ADD);

            return new ResponseEntity(message, HttpStatus.OK);
        }

        return new ResponseEntity(new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), Constants.ERROR_MESSAGE_DATABASE), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/client/{id}")
    ResponseEntity<Object> updateClient(@PathVariable("id") Long id, @RequestBody Customer customer) {

        customer.setProducts(null);
        Client client = Utils.mapToClient(customer);
        client.setId(id);
        if (clientService.updateClient(client)) {
            return new ResponseEntity(new Message(HttpStatus.OK.value(), Constants.SUCCES_MESSAGE_UPDATE), HttpStatus.OK);
        }
        return new ResponseEntity(new Message(HttpStatus.NOT_FOUND.value(), Constants.ERROR_MESSAGE_NOT_FOUND), HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable("id") Long id) {

        if (clientService.deleteClient(id) == true) {
            return new ResponseEntity(new Message(HttpStatus.OK.value(), Constants.SUCCES_MESSAGE_DELETE), HttpStatus.OK);
        }

        return new ResponseEntity(new Message(HttpStatus.NOT_FOUND.value(), Constants.ERROR_MESSAGE_NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/clients")
    public ResponseEntity<Object> getClientByCategoryOrByName(@RequestParam(value = "category", required = false) String category,
                                                              @RequestParam(value = "name", required = false) String name) {
        List<Client> listClients = clientService.getClientByCategorieOrNom(category, name);
        List<Customer> listCustomers = new ArrayList<Customer>();
        for (Client client : listClients) {
            listCustomers.add(Utils.mapToCustomer(client));
        }

        return new ResponseEntity(listCustomers, HttpStatus.OK);
    }

    // ******************* fin srvices users: *********************************//

    // ******************* Liste des srvices pour Associer les produits aux Users: *********************************//

    @PutMapping("/client/{id}/products")
    ResponseEntity<Object> addProductsToClient(@PathVariable("id") Long id, @RequestBody List<Long> productsId) {

        if (clientService.updateClientWithProducts(id, productsId)) {
            return new ResponseEntity(new Message(HttpStatus.OK.value(), Constants.SUCCES_MESSAGE_UPDATE), HttpStatus.OK);
        }
        return new ResponseEntity(new Message(HttpStatus.NOT_FOUND.value(), Constants.ERROR_MESSAGE_NOT_FOUND), HttpStatus.NOT_FOUND);

    }
}
