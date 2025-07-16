package com.example.case_back_junior.Service.Impl;


import com.example.case_back_junior.Model.Entity.Livro;
import com.example.case_back_junior.Model.Filtro.LivroFiltro;
import com.example.case_back_junior.Repository.LivroRepository;
import com.example.case_back_junior.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LivroServiceImpl implements LivroService {
    @Autowired
    LivroRepository repository;
    @Override
    public Livro buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Livro buscarPorTitulo(String titulo) {
        return repository.buscarPorTitulo(titulo);
    }

    @Override
    public List<Livro> listar(LivroFiltro filtro) {
        return repository.buscarPorFiltro(filtro.getAno(), filtro.getAutor(),filtro.getCategoria());
    }

    @Override
    public Livro criar(Livro livro) {
        return repository.save(livro);
    }

    @Override
    public Livro atualizar(Livro livro) {
        return repository.save(livro);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Livro> buscarPorAutor(Long idAutor) {
        return repository.buscarPorAutor(idAutor);
    }

    @Override
    public boolean existePorIsbn(String isbn) {
        return repository.existsByIsbn(isbn);
    }

    @Override
    public List<Livro> buscarPorCategoria(Long idCategoria) {
        return repository.buscarPorCategoria(idCategoria);
    }
}
