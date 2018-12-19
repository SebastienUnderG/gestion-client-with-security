package com.java.formation.client.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.java.formation.client.dao.ProduitRepository;
import com.java.formation.client.entity.Produit;
import com.java.formation.client.util.Constants;
import com.java.formation.client.util.Utils;

@Service
public class ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ProduitService.class);

    public boolean add(Produit produit) {

        logger.info("ProduitService.add: insert product into BDD with ID=" + produit.getId());
        boolean result = false;
        if (produit != null) {
            result = produitRepository.save(produit) != null;
        }
        return result;
    }

    public List<Produit> getAllProducts(Integer page, Integer size) {

        logger.info("ProduitService.getAllProducts: get All products");
        int newPage = 0;
        int newSize = Constants.MAX_SIZE;

        if (size != null && size < Constants.MAX_SIZE) {
            newSize = size;
        }

        if (page != null) {
            newPage = page;
        }

        Page<Produit> myPage = produitRepository.findAll(new PageRequest(newPage, newSize));
        System.out.println("STEP " + myPage.getContent().size());
        return myPage.getContent();
    }

    public Produit find(Long id) {
        logger.info("ProduitService.find: find product wich ID=" + id);
        return produitRepository.findOne(id);
    }

    public boolean updateProduct(Produit produit) {

        logger.info("ProduitService.updateProduct: update product wich ID=" + produit.getId());
        boolean result = false;
        Produit produitToUpdate = produitRepository.findOne(produit.getId());
        if (produitToUpdate != null) {

            // copier toutes les propriétés non null
            BeanUtils.copyProperties(produit, produitToUpdate, Utils.getNullPropertyNames(produit));
            result = produitRepository.save(produitToUpdate) != null;

        }
        return result;
    }

    public boolean deleteProduit(Long id) {

        logger.info("ProduitService.deleteProduit: delete product wich ID=" + id);
        boolean success = false;
        Produit produit = produitRepository.findOne(id);
        if (produit != null) {
            produitRepository.delete(produit);
            success = true;
        }
        return success;
    }

}
