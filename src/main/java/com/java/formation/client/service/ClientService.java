package com.java.formation.client.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.java.formation.client.dao.ClientRepository;
import com.java.formation.client.dao.ProduitRepository;
import com.java.formation.client.entity.Client;
import com.java.formation.client.entity.Produit;
import com.java.formation.client.util.Utils;
import com.java.formation.client.util.Constants;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProduitRepository produitRepository;
    
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(ClientService.class);

    public boolean add(Client client) {

        logger.info("ClientService.add: insert Client into BDD with ID=" + client.getId());        
        boolean result = false;
        if (client != null) {
            result = clientRepository.save(client) != null;
        }
        return result;
    }

    public List<Client> getClientByCategorieOrNom(String categorie, String nom) {
        return clientRepository.findByCategorieOrNomIgnoreCase(categorie, nom);
    }

    public List<Client> getAllClient(int page, int size) {

        if (size > Constants.MAX_SIZE) {
            size = Constants.MAX_SIZE;
        }

        Page<Client> maPage = clientRepository.findAll(new PageRequest(page, size));
        return maPage.getContent();
    }

    public Client find(Long id) {
        return clientRepository.findOne(id);
    }

    public boolean updateClient(Client client) {
        boolean result = false;
        Client clientToUpdate = clientRepository.findOne(client.getId());
        if (clientToUpdate != null) {

            // copier toutes les propriétés non null
            BeanUtils.copyProperties(client, clientToUpdate, Utils.getNullPropertyNames(client));
            result = clientRepository.save(clientToUpdate) != null;

        }
        return result;
    }

    public boolean updateClientWithProducts(Long id, List<Long> productsId) {
        boolean result = false;
        Client clientToUpdate = clientRepository.findOne(id);
        if (clientToUpdate != null) {
            for (Long  produitId : productsId) {
                Produit produitInDB = produitRepository.findOne(produitId);
                if (produitInDB != null) {
                    clientToUpdate.add(produitInDB);
                }
            }
            result = clientRepository.save(clientToUpdate) != null;

        }
        return result;
    }

    public boolean deleteClient(Long id) {
        boolean success = false;
        Client client = clientRepository.findOne(id);
        if (client != null) {
            clientRepository.delete(client);
            success = true;
        }
        return success;
    }

}
