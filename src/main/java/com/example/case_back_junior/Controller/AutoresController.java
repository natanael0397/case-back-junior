package com.example.case_back_junior.Controller;

import com.example.case_back_junior.Model.Entity.Autor;
import com.example.case_back_junior.Controller.Dto.AutorCreateDto;
import com.example.case_back_junior.Controller.Dto.AutorResponseDto;
import com.example.case_back_junior.Controller.Dto.LivroResponseDto;
import com.example.case_back_junior.Service.AutorService;
import com.example.case_back_junior.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/autores")
public class AutoresController {

    @Autowired
    private AutorService service;
    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<Page<AutorResponseDto>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending){

        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size,sort);
        Page<Autor> pageAutor = service.listar(pageable);
        Page<AutorResponseDto> autorResponseDtos = pageAutor.map(autor -> new AutorResponseDto(autor.getId(), autor.getNome(), autor.getEmail(), autor.getDataNascimento()));
        return ResponseEntity.ok(autorResponseDtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDto> buscarPorId(@PathVariable Long id){
        Autor autor = service.listarPorId(id);
        AutorResponseDto responseDto = convertToResponseDto(autor);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}/livros")
    public ResponseEntity<List<LivroResponseDto>> livrosAutores(@PathVariable("id") Long idCategoria){
        return ResponseEntity.ok(livroService.buscarPorAutor(idCategoria).stream().map(livro ->
                new LivroResponseDto(livro.getId(),livro.getTitulo(),livro.getIsbn(),livro.getAnoPublicacao(),livro.getPreco())).toList());
    }

    @PostMapping
    public ResponseEntity<AutorResponseDto> criarNovoAutor(@RequestBody AutorCreateDto autorDto) {
        Autor autor = convertToAutor(autorDto);
        Autor autorCriado = service.criar(autor);
        AutorResponseDto responseDto = convertToResponseDto(autorCriado);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping
    public ResponseEntity<AutorResponseDto> atualizarAutor(@RequestBody AutorCreateDto autorDto){
        Autor autor = convertToAutor(autorDto);
        Autor autorAtualizado = service.atualizar(autor);
        AutorResponseDto responseDto = convertToResponseDto(autorAtualizado);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarAutor(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private Autor convertToAutor(AutorCreateDto autorDto) {
        return new Autor(
                autorDto.id(),
                autorDto.nome(),
                autorDto.email(),
                autorDto.dataNascimento()
        );
    }
    private  AutorResponseDto convertToResponseDto(Autor autorCriado) {
        return new AutorResponseDto(autorCriado.getId(), autorCriado.getNome(), autorCriado.getEmail(), autorCriado.getDataNascimento());
    }
}
