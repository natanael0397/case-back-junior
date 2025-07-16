package com.example.case_back_junior.Service;


import com.example.case_back_junior.Model.Entity.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriaService {
    List<Categoria> listar();

    Categoria criar(Categoria categoria);

    Categoria buscarPorId(Long id);

}
