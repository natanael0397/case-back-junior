package com.example.case_back_junior.model;

import com.example.case_back_junior.Model.Entity.Categoria;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import java.util.Set;

@Profile("test")
class CategoriaTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Criando uma busca válida")
    void valido() {
        Long id = 1L;
        String nome = "Aventura";
        String descricao = "Livro de aventura de um grupo de hérois";

        Categoria categoria = new Categoria(id, nome, descricao);
        Set<ConstraintViolation<Categoria>> validate = validator.validate(categoria);

        Assertions.assertTrue(validate.isEmpty());
        Assertions.assertEquals(id, categoria.getId());
        Assertions.assertEquals(nome, categoria.getNome());
        Assertions.assertEquals(descricao, categoria.getDescricao());
    }

}
