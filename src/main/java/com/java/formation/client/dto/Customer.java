package com.java.formation.client.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.java.formation.client.util.Constants;

/**
 * Application de démonstration. Classe DTO (DATA TRANSFERT OBJECT)
 * 
 * @author Tarik EL AKKANI (tarik.el.akkani@gmail.com)
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"id", "lastName", "firstName", "category", "birthDay"})
public class Customer {

    Long id;
    String firstName;
    String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_PATTERN)
    Date birthDay;
    @JsonProperty("email-address")
    String mailAddress;
    String category;
    List<Product> products;

    public void add(Product product) {
        products.add(product);
    }

}
