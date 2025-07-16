package com.example.case_back_junior.Service.Impl;

import com.example.case_back_junior.Model.Entity.Autor;
import com.example.case_back_junior.Repository.AutorRepository;
import com.example.case_back_junior.Service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AutorServiceImpl implements AutorService {
    @Autowired
    private AutorRepository repository;
    @Override
    public Page<Autor> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Autor listarPorId(Long id) {
        return repository.findById(id).get();//melhora depois
    }

    @Override
    public Autor criar(Autor autor) {
        return repository.save(autor);
    }

    @Override
    public Autor atualizar(Autor autor) {
        return repository.save(autor);
    }

    @Override
    public void deletar(Long id) {
        this.repository.deleteById(id);
    }

}
