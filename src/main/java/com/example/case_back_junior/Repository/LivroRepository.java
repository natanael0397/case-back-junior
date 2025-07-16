package com.example.case_back_junior.Repository;

import com.example.case_back_junior.Model.Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l " +
            "WHERE (:ano IS NULL OR l.anoPublicacao = :ano) " +
            "AND (:autor IS NULL OR l.autor = :autor) " +
            "AND (:categoria IS NULL OR l.categoria = :categoria)")
    List<Livro> buscarPorFiltro(@Param("ano") Integer ano,
                                @Param("autor") String autor,
                                @Param("categoria") String categoria);

    @Query("SELECT l FROM Livro l " +
            "WHERE (:titulo IS NULL OR l.titulo = :titulo) ")
    Livro buscarPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT l FROM Livro l " +
            "JOIN l.autor a  " +
            "WHERE (:autor IS NULL OR a.id = :autor) ")
    List<Livro> buscarPorAutor(@Param("autor") Long idAutor);

    @Query("SELECT l FROM Livro l " +
            " JOIN l.categoria c " +
            "WHERE (:categoria IS NULL OR c.id = :categoria) ")
    List<Livro> buscarPorCategoria(@Param("categoria") Long idCategoria);

    boolean existsByIsbn(String isbn);
}



