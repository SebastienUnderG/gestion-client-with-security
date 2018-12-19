package com.java.formation.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Application de démonstration. Classe DTO (DATA TRANSFERT OBJECT)
 * 
 * @author Tarik EL AKKANI (tarik.el.akkani@gmail.com)
 */

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    private Integer id;
    @NonNull
    private String message;
}
