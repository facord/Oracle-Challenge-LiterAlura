package br.com.alura.LiterAlura.principal;

import br.com.alura.LiterAlura.model.DadosLivros;
import br.com.alura.LiterAlura.model.Livros;
import br.com.alura.LiterAlura.model.Pessoas;
import br.com.alura.LiterAlura.repository.LivroRepository;
import br.com.alura.LiterAlura.service.ConsumoAPI;
import br.com.alura.LiterAlura.service.ConverteDados;
import br.com.alura.LiterAlura.service.LivroService;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private LivroService livroService;
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo;
    private ConverteDados conversor;
    private static final String ENDERECO = "https://gutendex.com/books/";
    private LivroRepository repositorio;
    private List<Livros> livro = new ArrayList<>();
    private List<Pessoas>  pessoas = new ArrayList<>();

    public Principal(LivroService livroService, ConsumoAPI consumo, ConverteDados conversor, LivroRepository repositorio) {
        this.livroService = livroService;
        this.consumo = consumo;
        this.conversor = conversor;
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        int opcao = -1;

        while (opcao != 15) {
            System.out.println("""
                    *** Challenge LiterAlura ***
                     1 - Buscar livro por título
                     2 - Buscar livro por autor
                     3 - Buscar livro por assunto
                     4 - Buscar livro por determinado período
                     5 - Buscar livro por linguagem
                     6 - Buscar livros por popularidade
                     7 - Buscar livro por id
                     8 - Lista de todos os livros
                     9 - Lista de livros por titulo
                    10 - Lista de livros por idioma
                    11 - Lista de autores
                    12 - Lista de autores vivos em determinado ano
                    13 - Exibir a quantidade de livros em um determinado idioma.
                    14 - Exibir livro por ID
                    15 - Sair
                    """);

            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarTitulo();
                case 2 -> buscarAutor();
                case 3 -> buscarAssunto();
                case 4 -> buscarPeriodo();
                case 5 -> buscarLingua();
                case 6 -> buscarPopular();
                case 7 -> buscarID();
                case 8 -> livroService.listarTodosOsLivros();
                case 9 -> listarTitulo();
                case 10 -> listarIdioma();
                case 11 -> listarAutor();
                case 12 -> listarAutorVivo();
                case 13 -> listarQuantidadeIdioma();
                case 14 -> listarPorID();
                case 15 -> System.out.println("Encerrando a aplicação!");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void listarPorID() {
        System.out.print("Informe a ID do livro: ");
        String livroID = leitura.nextLine().trim();
        if (livroID.matches("^\\d+$")) {
            livroService.buscarLivroPorID(livroID);
        } else {
            System.out.println("Entrada inválida!");
        }
    }

    private void listarQuantidadeIdioma() {
        System.out.print("Informe o idioma (pt, en, es, fr): ");
        String idioma = leitura.nextLine().toLowerCase().trim();
        if (idioma.matches("^(pt|en|es|fr)$")) {
            livroService.contarLivrosPorIdioma(idioma);
        } else {
            System.out.println("Idioma inválido!");
        }
    }

    private void listarAutorVivo() {
        System.out.println("Digite o ano que deseja pesquisar:");
        String entrada = leitura.nextLine().trim();
        try {
            Integer valor = Integer.valueOf(entrada);
            if (valor >= 1000 && valor <= 9999) {
                livroService.listarAutoresVivosNoAno(valor);
            } else {
                System.out.println("O número precisa ter exatamente 4 dígitos");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite apenas números.");
        }
    }

    private void listarAutor() {
        System.out.println("Digite o nome do autor:");
        String nomeAutor = leitura.nextLine();
        livroService.buscarAutorPorNome(nomeAutor);
    }

    private void listarIdioma() {
        System.out.print("Informe o idioma (pt, en, es, fr): ");
        String idioma = leitura.nextLine().toLowerCase().trim();
        if (idioma.matches("^(pt|en|es|fr)$")) {
            livroService.buscarLivroPorIdioma(idioma);
        } else {
            System.out.println("Idioma inválido!");
        }
    }

    private void listarTitulo() {
        System.out.println("Digite o nome do livro para buscar:");
        var nome = leitura.nextLine();
        livroService.buscarLivroPorTitulo(nome);
    }

    private void buscarID() {
        System.out.print("Informe a id do livro: ");
        String livroID = leitura.nextLine().trim();
        if (livroID.matches("^\\d+$")) {
            var json = consumo.obterDados(ENDERECO + "?ids=" + livroID);
            verificarJson(json);
        } else {
            System.out.println("Entrada inválida!");
        }
    }

    private void buscarPopular() {
        verificarJson(consumo.obterDados(ENDERECO + "?sort=popular"));
    }

    private void buscarLingua() {
        System.out.print("Informe o idioma (pt, en, es, fr): ");
        String idioma = leitura.nextLine().toLowerCase().trim();

        if (idioma.matches("^(pt|en|es|fr)$")) {
            verificarJson(consumo.obterDados(ENDERECO + "?languages=" + idioma));
        } else {
            System.out.println("Idioma inválido!");
        }
    }

    private void buscarPeriodo() {
        System.out.print("Ano inicial: ");
        String inicio = leitura.nextLine().trim();
        if (inicio.matches("^\\d{4}$")) {
            System.out.print("Ano final: ");
            String fim = leitura.nextLine();
            if (fim.matches("^\\d{4}$")) {
                verificarJson(consumo.obterDados(
                        ENDERECO + "?author_year_start=" + inicio + "&author_year_end=" + fim
                ));
            }else {
                System.out.println("Entrada inválida! Digite exatamente 4 números.");
            }
        } else {
            System.out.println("Entrada inválida! Digite exatamente 4 números.");
        }
    }

    private void buscarAssunto() {
        System.out.print("Informe o assunto: ");
        String assunto = leitura.nextLine().replace(" ", "%20");

        verificarJson(consumo.obterDados(ENDERECO + "?topic=" + assunto));
    }

    private void buscarAutor() {
        System.out.print("Informe o autor: ");
        String autor = leitura.nextLine().replace(" ", "%20");

        verificarJson(consumo.obterDados(ENDERECO + "?search=" + autor));
    }

    private void buscarTitulo() {
        System.out.print("Informe o título ou palavra-chave: ");
        String titulo = leitura.nextLine().replace(" ", "%20");
        verificarJson(consumo.obterDados(ENDERECO + "?search=" + titulo));
    }

    private void verificarJson(String json) {
        if (json == null || json.isBlank()) {
            System.out.println("Erro: resposta vazia da API");
            return;
        }
            DadosLivros dados = conversor.obterDados(json, DadosLivros.class);
            dados.resultados().forEach(livroService::verificarLivro);
    }
}