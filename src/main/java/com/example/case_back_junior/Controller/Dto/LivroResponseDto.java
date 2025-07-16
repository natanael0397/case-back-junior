package com.example.case_back_junior.Controller.Dto;

import java.math.BigDecimal;

public record LivroResponseDto(Long id,
                               String titulo,
                               String isbn,
                               Integer anoPublicacao,
                               BigDecimal preco) {

}
