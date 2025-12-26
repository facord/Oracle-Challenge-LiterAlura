package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livros, Long> {
    List<Livros> findByTituloContainingIgnoreCase(String titulo);
    List<Livros> findByIdiomasContainingIgnoreCase(String idiomas);
    boolean existsByTituloContainingIgnoreCase(String titulo);
    long countByIdiomasIgnoreCase(String idiomas);
    List<Livros> findById(Integer id);
}