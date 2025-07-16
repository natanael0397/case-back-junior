package com.example.case_back_junior.Controller.Dto;

import java.math.BigDecimal;

public record LivroCreateDto(Long id,
                             Long idAutor,
                             Long idCategoria,
                             String titulo,
                             String isbn,
                             Integer anoPublicacao,
                             BigDecimal preco) {
    ;
}
