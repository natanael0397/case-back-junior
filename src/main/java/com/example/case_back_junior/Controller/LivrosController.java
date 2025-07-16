package com.example.case_back_junior.Controller;

import com.example.case_back_junior.Model.Entity.Autor;
import com.example.case_back_junior.Model.Entity.Categoria;
import com.example.case_back_junior.Model.Entity.Livro;
import com.example.case_back_junior.Model.Filtro.LivroFiltro;
import com.example.case_back_junior.Controller.Dto.ImportDto;
import com.example.case_back_junior.Controller.Dto.LivroCreateDto;
import com.example.case_back_junior.Controller.Dto.LivroResponseDto;
import com.example.case_back_junior.Client.AmazonClient;
import com.example.case_back_junior.Service.AutorService;
import com.example.case_back_junior.Service.CategoriaService;
import com.example.case_back_junior.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivrosController {
    @Autowired
    public LivroService service;

    @Autowired
    private CategoriaService catservice;
    @Autowired
    private AutorService autservice;
    @Autowired
    private AmazonClient amazonClient;

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> buscarPorId(@PathVariable Long id){
        Livro livro = service.buscarPorId(id);
        LivroResponseDto responseDto = converteToResponse(livro);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/importar")
    public ResponseEntity<LivroResponseDto> importarLivro(@RequestBody ImportDto importDto)  {
        Livro livro = null;
        try {
            livro = amazonClient.importarApiAmazon(importDto.url());
            if (service.existePorIsbn(livro.getIsbn())){
                throw new RuntimeException("O livro já existe");
            }
            livro = service.criar(livro);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new LivroResponseDto(livro.getId(),
                livro.getTitulo(),livro.getIsbn(),livro.getAnoPublicacao(),livro.getPreco()));
    }
    @GetMapping()
    public ResponseEntity<List<LivroResponseDto>> listarTodos(@RequestBody LivroFiltro filtro){
        List<Livro> livros = service.listar(filtro);
        return ResponseEntity.ok(livros.stream().map(livro -> converteToResponse(livro)).toList());
    }

    @GetMapping("/search")
    public ResponseEntity<LivroResponseDto> buscarPorTitulo(@RequestParam String titulo){
        Livro livro = service.buscarPorTitulo(titulo);
        LivroResponseDto responseDto = converteToResponse(livro);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<LivroResponseDto> criarNovoLivro(@RequestBody LivroCreateDto livrodto){
        Categoria categoria = catservice.buscarPorId(livrodto.idCategoria());
        Autor autor = autservice.listarPorId(livrodto.idAutor());
        Livro livro = new Livro(livrodto.id(), autor,categoria,livrodto.titulo(),livrodto.isbn(),livrodto.anoPublicacao(),livrodto.preco());
        Livro livroCriado = service.criar(livro);
        LivroResponseDto responseDto = converteToResponse(livroCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping
    public ResponseEntity<LivroResponseDto> atualizarLivro(@RequestBody LivroCreateDto livrodto){
        Categoria categoria = catservice.buscarPorId(livrodto.idCategoria());
        Autor autor = autservice.listarPorId(livrodto.idAutor());
        Livro livro = new Livro(livrodto.id(), autor,categoria,livrodto.titulo(),livrodto.isbn(),livrodto.anoPublicacao(),livrodto.preco());
        Livro livroAtualizado = service.atualizar(livro);
        LivroResponseDto responseDto = converteToResponse(livroAtualizado);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLivro(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private  LivroResponseDto converteToResponse(Livro livro) {
        return new LivroResponseDto(livro.getId(), livro.getTitulo(), livro.getIsbn(), livro.getAnoPublicacao(), livro.getPreco());
    }

}
