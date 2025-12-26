package br.com.alura.LiterAlura.service;

import br.com.alura.LiterAlura.model.*;
import br.com.alura.LiterAlura.repository.LivroRepository;
import br.com.alura.LiterAlura.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class LivroService {
    @Autowired
    private final LivroRepository livroRepositorio;
    private final PessoaRepository pessoaRepositorio;

    public LivroService(LivroRepository livroRepository,
                        PessoaRepository pessoaRepository) {
        this.livroRepositorio = livroRepository;
        this.pessoaRepositorio = pessoaRepository;
    }
    public void verificarLivro(InfoLivro dados){
        boolean jaExiste = livroRepositorio.existsByTituloContainingIgnoreCase(dados.titulo());
        if (jaExiste) {
            System.out.println("Este livro já está cadastrado no banco de dados.");
        } else {
            salvarLivro(dados);
        }
    }

    public void listarTodosOsLivros() {
        List<Livros> todosLivros = livroRepositorio.findAll();
        if (todosLivros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado no banco de dados.");
        } else {
            todosLivros.forEach(this::exibirDetalhes);
        }
    }

    public void buscarLivroPorTitulo(String nomeLivro) {
        List<Livros> livrosEncontrados = livroRepositorio.findByTituloContainingIgnoreCase(nomeLivro);
        if (!livrosEncontrados.isEmpty()) {
            livrosEncontrados.forEach(this::exibirDetalhes);
        } else {
            System.out.println("Livro não encontrado com o título: " + nomeLivro);
        }
    }

    public void buscarLivroPorIdioma(String idiomaLivro) {
        List<Livros> livrosEncontrados = livroRepositorio.findByIdiomasContainingIgnoreCase(idiomaLivro);
        if (!livrosEncontrados.isEmpty()) {
            livrosEncontrados.forEach(this::exibirDetalhes);
        } else {
            System.out.println("Não encontrado livro com o idioma: " + idiomaLivro);
        }
    }

    public void buscarLivroPorID(String livroID) {
        Integer id = Integer.valueOf(livroID);
        List<Livros> livrosEncontrados = livroRepositorio.findById(id);
        if (!livrosEncontrados.isEmpty()) {
            livrosEncontrados.forEach(this::exibirDetalhes);
        } else {
            System.out.println("Não encontrado livro com o ID: " + livroID);
        }
    }


    public void contarLivrosPorIdioma(String idiomas) {
        long total = livroRepositorio.countByIdiomasIgnoreCase(idiomas);
        if (total > 0) {
            System.out.println("Total de livros no idioma " + idiomas + ": " + total);
        } else {
            System.out.println("Nenhum livro encontrado no idioma " + idiomas + ".");
        }
    }

    public void buscarAutorPorNome(String nome) {
        List<Pessoas> autores = pessoaRepositorio.findByNomeContainingIgnoreCase(nome);
        if (!autores.isEmpty()) {
            autores.forEach(this::exibirDetalhesAutor);
        } else {
            System.out.println("Autor não encontrado com o nome: " + nome);
        }
    }

    public void listarAutoresVivosNoAno(Integer ano) {
        List<Pessoas> autoresVivos = pessoaRepositorio.buscarAutoresVivosNoAno(ano);
        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo no ano de " + ano);
        } else {
            System.out.println("\n--- AUTORES VIVOS EM " + ano + " ---");
            autoresVivos.forEach(this::exibirDetalhesAutor);
        }
    }

    private void exibirDetalhesAutor(Pessoas p) {
        System.out.println("Autor: " + p.getNome());
        System.out.println("Nascimento: " + (p.getAnoNascimento() != null ? p.getAnoNascimento() : "Desconhecido"));
        System.out.println("Falecimento: " + (p.getAnoFalecimento() != null ? p.getAnoFalecimento() : "Ainda vivo/Desconhecido"));
        System.out.print("Livros: ");
        p.getLivros().forEach(pl -> System.out.print("[" + pl.getLivro().getTitulo() + "] "));
        System.out.println("\n-----------------------");
    }

    private void exibirDetalhes(Livros livro) {
        System.out.println("---------- LIVRO ----------");
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Idioma: " + livro.getIdiomas());
        System.out.println("Downloads: " + livro.getTotalDownloads());
        // Mostrando os autores através da lista de PessoaLivro
        System.out.print("Autores: ");
        livro.getPessoas().forEach(pl ->
                System.out.print(pl.getPessoa().getNome() + " "));
        System.out.println("\n---------------------------\n");
    }

    public void salvarLivro(InfoLivro dados) {
        Livros novoLivro = new Livros();
        novoLivro.setId(dados.id());
        novoLivro.setTitulo(dados.titulo());
        novoLivro.setIdiomas(dados.idiomas());
        novoLivro.setAssuntos(dados.assuntos());
        novoLivro.setEstantes(dados.estantes());
        novoLivro.setResumos(dados.resumos());
        novoLivro.setTotalDownloads(dados.totalDownloads());
        novoLivro.setCopyright(dados.copyright());
        novoLivro.setTipoMidia(dados.tipoMidia());
        novoLivro.setFormatos(dados.formatos());

        dados.autores().forEach(a -> {
            Pessoas pessoa = pessoaRepositorio
                    .findByNome(a.nome())
                    .stream().findFirst()
                    .orElseGet(() -> {
                        Pessoas p = new Pessoas();
                        p.setNome(a.nome());
                        p.setAnoNascimento(a.anoNascimento());
                        p.setAnoFalecimento(a.anoFalecimento());
                        return pessoaRepositorio.save(p);
                    });
            PessoaLivro pl = new PessoaLivro();
            pl.setLivro(novoLivro);
            pl.setPessoa(pessoa);
            pl.setPapel(PapelPessoa.AUTOR);
            novoLivro.getPessoas().add(pl);
        });

        livroRepositorio.save(novoLivro);
        System.out.println("Livro salvo com sucesso!");
    }
}
