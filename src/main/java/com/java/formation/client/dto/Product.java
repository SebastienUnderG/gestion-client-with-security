package com.java.formation.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Application de démonstration. Classe DTO (DATA TRANSFERT OBJECT)
 * 
 * @author Tarik EL AKKANI (tarik.el.akkani@gmail.com)
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    Long id;
    String label;
    String description;

}
