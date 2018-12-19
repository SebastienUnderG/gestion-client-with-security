package com.java.formation.client.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.formation.client.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
   
    List<Client> findByCategorieOrNomIgnoreCase(String categorie, String nom);
    
    //ces deux methodes sont identiques
    @Query("FROM Client client WHERE lower(client.categorie)=lower(:cat) OR lower(client.nom)=lower(:nom)")
    List<Client> getClientByParams(@Param(value = "cat") String categorie, 
                                   @Param(value = "nom") String nom);
    
    
    
    
    
    
    
    
    
    
    

}
