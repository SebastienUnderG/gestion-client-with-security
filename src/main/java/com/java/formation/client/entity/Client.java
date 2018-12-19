package com.java.formation.client.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Application de démonstration.
 * 
 * Classe utilitaire qui représente un client
 * 
 * @author Tarik EL AKKANI (tarik.el.akkani@gmail.com)
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;
    @Column(nullable = false)
    String prenom;
    String email;
    Date dateNaissance;
    String categorie;
    @OneToMany
    Set<Produit> produits;
    
    public void add(Produit produit){
        produits.add(produit);
    }
}
