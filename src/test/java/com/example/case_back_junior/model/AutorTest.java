package com.example.case_back_junior.model;

import com.example.case_back_junior.Model.Entity.Autor;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.time.LocalDate;
import java.util.Set;

@Profile("test")
class AutorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Criando um autor válido")
    void valido(){
        Long id = 1L;
        String nome = "Natanael";
        String email = "email@gmail.com";
        LocalDate dataNascimento = LocalDate.of(2000,1, 1);

        Autor autor = new Autor(id,nome,email,dataNascimento);
        Set<ConstraintViolation<Autor>> validate = validator.validate(autor);

        Assertions.assertTrue(validate.isEmpty());
        Assertions.assertEquals(id,autor.getId());
        Assertions.assertEquals(nome, autor.getNome());
        Assertions.assertEquals(email, autor.getEmail());
        Assertions.assertEquals(dataNascimento, autor.getDataNascimento());
    }

    @Test
    @DisplayName("Criando um autor inválido")
    void emailInvalido(){
        Long id = 1L;
        String nome = "Natanael";
        String email = "email@@gmail.com";
        LocalDate dataNascimento = LocalDate.of(2000,1,1);

        Autor autor = new Autor(id,nome,email,dataNascimento);
        Set<ConstraintViolation<Autor>> validate = validator.validate(autor);

        Assertions.assertFalse(validate.isEmpty());
        Assertions.assertEquals(id,autor.getId());
        Assertions.assertEquals(nome, autor.getNome());
        Assertions.assertEquals(email, autor.getEmail());
        Assertions.assertEquals(dataNascimento, autor.getDataNascimento());
    }


}
