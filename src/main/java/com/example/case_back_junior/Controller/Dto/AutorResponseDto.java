package com.example.case_back_junior.Controller.Dto;

import java.time.LocalDate;

public record AutorResponseDto(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento){
}
