package com.example.case_back_junior.model;

import com.example.case_back_junior.Model.Entity.Autor;
import com.example.case_back_junior.Model.Entity.Categoria;
import com.example.case_back_junior.Model.Entity.Livro;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Profile("test")
class LivroTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Criando um livro válido")
    void valido(){
        Long id = 2L;
        String titulo = "O segredo dos animais";
        String isbn = "0123456789";
        Integer anoPublicacao = 1984;
        BigDecimal preco = new BigDecimal("15.50");
        Autor autor = new Autor(1L,"George Onwel","email@gmail.com",LocalDate.of(2000, 1,2));
        Categoria categoria = new Categoria(1L,"Aventura","Historia de uma revolta liderada por animais");

        Livro livro = new Livro(id,autor,categoria,titulo,isbn,anoPublicacao,preco);
        Set<ConstraintViolation<Livro>> validate = validator.validate(livro);


        Assertions.assertTrue(validate.isEmpty());
        Assertions.assertEquals(id,livro.getId());
        Assertions.assertEquals(titulo,livro.getTitulo());
        Assertions.assertEquals(isbn,livro.getIsbn());
        Assertions.assertEquals(anoPublicacao,livro.getAnoPublicacao());
        Assertions.assertEquals(preco,livro.getPreco());
    }

    @Test
    @DisplayName("Criando um livro inválido")
    void invalido_Preco_Titulo(){
        Long id = 2L;
        String titulo = null;
        String isbn = "0123456789";
        Integer anoPublicacao = 1984;
        BigDecimal preco = new BigDecimal("-15.50");
        Autor autor = new Autor(1L,"George Onwel","email@gmail.com",LocalDate.of(2000, 1,2));
        Categoria categoria = new Categoria(1L,"Aventura","Historia de uma revolta liderada por animais");

        Livro livro = new Livro(id,autor, categoria,titulo,isbn,anoPublicacao,preco);
        Set<ConstraintViolation<Livro>> validate = validator.validate(livro);

        Assertions.assertFalse(validate.isEmpty());
        Assertions.assertEquals(2, validate.size());
        Assertions.assertEquals(id,livro.getId());
        Assertions.assertEquals(titulo,livro.getTitulo());
        Assertions.assertEquals(isbn,livro.getIsbn());
        Assertions.assertEquals(anoPublicacao,livro.getAnoPublicacao());
        Assertions.assertEquals(preco,livro.getPreco());
    }

    @Test
    @DisplayName("Criando um livro com ano invalido")
    void invalido_Ano(){
        Long id = 2L;
        String titulo = "O segredo dos animais";
        String isbn = "0123456789";
        Integer anoPublicacao = LocalDate.now().getYear()+1;
        BigDecimal preco = new BigDecimal("15.50");
        Autor autor = new Autor(1L,"George Onwel","email@gmail.com",LocalDate.of(2000, 1,2));
        Categoria categoria = new Categoria(1L,"Aventura","Historia de uma revolta liderada por animais");


        Assertions.assertThrows(RuntimeException.class,()-> new Livro(id,autor,categoria,titulo,isbn,anoPublicacao,preco));
    }

}
