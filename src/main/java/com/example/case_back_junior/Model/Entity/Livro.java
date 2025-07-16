package com.example.case_back_junior.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
public class Livro {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Categoria categoria;
    @NotEmpty
    private String titulo;
    @Size(min = 10 ,  max = 13 )
    private String isbn;
    private Integer anoPublicacao;
    @Positive
    private BigDecimal preco;

    public Livro(Long id, Autor autor, Categoria categoria, String titulo, String isbn, Integer anoPublicacao, BigDecimal preco) {
        this.id = id;
        this.autor = autor;
        this.categoria = categoria;
        this.titulo = titulo;
        this.isbn = isbn;
        if (anoPublicacao > LocalDateTime.now().getYear()){
            throw new RuntimeException("A data não pode ser futura");// fazer as pastas de exceção do projeto
        }
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
    }

    public Livro(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
