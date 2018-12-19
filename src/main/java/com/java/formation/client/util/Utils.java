package com.java.formation.client.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.java.formation.client.dto.Customer;
import com.java.formation.client.dto.Product;
import com.java.formation.client.entity.Client;
import com.java.formation.client.entity.Produit;

public class Utils {

    public static Client mapToClient(Customer customer) {
        Client client = new Client();
        client.setId(customer.getId());
        client.setNom(customer.getLastName());
        client.setPrenom(customer.getFirstName());
        client.setEmail(customer.getMailAddress());
        client.setDateNaissance(customer.getBirthDay());
        client.setCategorie(customer.getCategory());
        
        if (customer.getProducts() != null && !customer.getProducts() .isEmpty()) {
            Set<Produit> produits = new HashSet<Produit>();
            for (Product product : customer.getProducts()) {
                produits.add(mapToProduit(product));
            }
            client.setProduits(produits);
        }
        return client;
    }

    public static Customer mapToCustomer(Client client) {
        Customer customer = new Customer();
        customer.setId(client.getId());
        customer.setBirthDay(client.getDateNaissance());
        customer.setCategory(client.getCategorie());
        customer.setLastName(client.getNom());
        customer.setFirstName(client.getPrenom());
        customer.setMailAddress(client.getEmail());

        if (client.getProduits() != null && !client.getProduits().isEmpty()) {
            List<Product> products = new ArrayList();
            for (Produit produit : client.getProduits()) {
                products.add(mapToProduct(produit));
            }
            customer.setProducts(products);
        }
        return customer;

    }

    public static Product mapToProduct(Produit produit) {
        Product product = new Product();
        product.setId(produit.getId());
        product.setLabel(produit.getTitre());
        product.setDescription(produit.getDescription());
        return product;
    }

    public static Produit mapToProduit(Product product) {
        Produit produit = new Produit();
        produit.setId(product.getId());
        produit.setTitre(product.getLabel());
        produit.setDescription(product.getDescription());
        return produit;
    }

    public static String[] getNullPropertyNames(Object source) {

        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
