package com.example.case_back_junior.Controller.Dto;


import java.time.LocalDate;

public record AutorCreateDto(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento) {

}
