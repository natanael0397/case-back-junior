package com.example.case_back_junior.Service;


import com.example.case_back_junior.Model.Entity.Livro;
import com.example.case_back_junior.Model.Filtro.LivroFiltro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LivroService {
    Livro buscarPorId(Long id);
    Livro buscarPorTitulo(String titulo);
    List<Livro> listar(LivroFiltro filtro);
    Livro criar(Livro livro);
    Livro atualizar(Livro livro);
    void deletar(Long id);
    List<Livro> buscarPorAutor(Long idAutor);
    boolean existePorIsbn(String isbn);
    List<Livro> buscarPorCategoria(Long idCategoria);
}
