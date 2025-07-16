package com.example.case_back_junior.Controller;

import com.example.case_back_junior.Model.Entity.Categoria;
import com.example.case_back_junior.Controller.Dto.CategoriaCreateDto;
import com.example.case_back_junior.Controller.Dto.CategoriaResponseDto;
import com.example.case_back_junior.Controller.Dto.LivroResponseDto;
import com.example.case_back_junior.Service.AutorService;
import com.example.case_back_junior.Service.CategoriaService;
import com.example.case_back_junior.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriasController {
    @Autowired
    public CategoriaService service;
    @Autowired
    public LivroService livroService;
    @Autowired
    public AutorService autorService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listar(){
        return ResponseEntity.ok(service.listar().stream().map(categoria -> convertToResponseDto(categoria)).toList());
    }

    @GetMapping("/{id}/livros")
    public ResponseEntity<List<LivroResponseDto>> livrosCategoria(@PathVariable("id") Long idCategoria){
        return ResponseEntity.ok(livroService.buscarPorCategoria(idCategoria).stream().map(livro ->
                new LivroResponseDto(livro.getId(),livro.getTitulo(),livro.getIsbn(),livro.getAnoPublicacao(),livro.getPreco())).toList());
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDto>criarCategoria(@RequestBody CategoriaCreateDto categoriadto){
        Categoria categoria = convertToCategoria(categoriadto);
        Categoria categoriaCriada = service.criar(categoria);
        CategoriaResponseDto responseDto = convertToResponseDto(categoriaCriada);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    private CategoriaResponseDto convertToResponseDto(Categoria categoriaCriada) {
        return new CategoriaResponseDto(categoriaCriada.getId(), categoriaCriada.getNome(), categoriaCriada.getDescricao());
    }

    private Categoria convertToCategoria(CategoriaCreateDto createDto) {
        return new Categoria(
                createDto.id(),
                createDto.nome(),
                createDto.descricao()
        );
    }


}
