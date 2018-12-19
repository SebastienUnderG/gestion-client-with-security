package com.java.formation.client.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Application de démonstration.
 * 
 * Classe utilitaire qui représente un Produit
 * 
 * @author Tarik EL AKKANI (tarik.el.akkani@gmail.com)
 */


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String titre;
    String description;
}
