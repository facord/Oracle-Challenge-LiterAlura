package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoas, Long> {
    List<Pessoas> findByNome(String nome);
    List<Pessoas> findByNomeContainingIgnoreCase(String nome);
    // Lógica: nascimento <= ano E (falecimento é nulo OU falecimento >= ano)
    @Query("SELECT p FROM Pessoas p WHERE p.anoNascimento <= :ano AND (p.anoFalecimento IS NULL OR p.anoFalecimento >= :ano)")
    List<Pessoas> buscarAutoresVivosNoAno(Integer ano);
}
