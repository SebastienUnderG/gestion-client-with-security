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
import com.java.formation.client.dto.Product;
import com.java.formation.client.entity.Client;
import com.java.formation.client.entity.Produit;
import com.java.formation.client.service.ClientService;
import com.java.formation.client.service.ProduitService;
import com.java.formation.client.util.Constants;
import com.java.formation.client.util.Utils;

@Controller
@RequestMapping("/v1")
public class ProductController {

    @Autowired
    ProduitService produitService;

    @GetMapping("/produit/{id}")
    public ResponseEntity<Client> getProduct(@PathVariable("id") Long id) {

        Produit produit = produitService.find(id);

        if (produit != null) {
            Product product = Utils.mapToProduct(produit);
            return new ResponseEntity(product, HttpStatus.OK);
        }

        return new ResponseEntity(new Message(Constants.ERROR_MESSAGE_NOT_FOUND), HttpStatus.NOT_FOUND);

    }

    @PostMapping("/produit")
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {

        Produit produit = Utils.mapToProduit(product);
        if (produitService.add(produit)) {
            Message message = new Message(produit.getId().intValue(), Constants.SUCCES_MESSAGE_ADD);

            return new ResponseEntity(message, HttpStatus.OK);
        }

        return new ResponseEntity(new Message(Constants.ERROR_MESSAGE_DATABASE), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/produit/{id}")
    ResponseEntity<Object> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {

        Produit produit = Utils.mapToProduit(product);
        produit.setId(id);
        if (produitService.updateProduct(produit)) {
            return new ResponseEntity(new Message(Constants.SUCCES_MESSAGE_UPDATE), HttpStatus.OK);
        }
        return new ResponseEntity(new Message(Constants.ERROR_MESSAGE_NOT_FOUND), HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/produit/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {

        if (produitService.deleteProduit(id) == true) {
            return new ResponseEntity(new Message(HttpStatus.OK.value(), Constants.SUCCES_MESSAGE_DELETE), HttpStatus.OK);
        }

        return new ResponseEntity(new Message(HttpStatus.NOT_FOUND.value(), Constants.ERROR_MESSAGE_NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/produits")
    public ResponseEntity<Object> getAllProducts(@RequestParam(value = "page", required = false) Integer  page,
                                                              @RequestParam(value = "size", required = false) Integer size) {
        List<Produit> listProduits = produitService.getAllProducts(page, size);
        List<Product> listProducts = new ArrayList<Product>();
        for (Produit produit : listProduits) {
            listProducts.add(Utils.mapToProduct(produit));
        }

        return new ResponseEntity(listProducts, HttpStatus.OK);
    }

}
