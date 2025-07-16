package com.example.case_back_junior.Model.Filtro;

import com.example.case_back_junior.Model.Entity.Autor;
import com.example.case_back_junior.Model.Entity.Categoria;

public class LivroFiltro {
    private String autor;
    private String categoria;
    private Integer ano;

    public LivroFiltro(String autor, String categoria, Integer ano) {
        this.autor = autor;
        this.categoria = categoria;
        this.ano = ano;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}
