package com.example.case_back_junior.Service;

import com.example.case_back_junior.Model.Entity.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AutorService  {
    Page<Autor> listar(Pageable pageable);
    Autor listarPorId(Long id);
    Autor criar(Autor autor);
    Autor atualizar(Autor autor);
    void deletar(Long id);
}
