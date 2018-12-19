package com.java.formation.client.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.formation.client.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>{

}
