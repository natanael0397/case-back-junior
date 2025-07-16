package com.example.case_back_junior.Service.Impl;

import com.example.case_back_junior.Model.Entity.Categoria;
import com.example.case_back_junior.Repository.CategoriaRepository;
import com.example.case_back_junior.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<Categoria> listar() {
        return repository.findAll();
    }

    @Override
    public Categoria criar(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return repository.findById(id).get();
    }
}
