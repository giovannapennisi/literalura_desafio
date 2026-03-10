package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO_API = "https://gutendex.com/books/?search=";

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ***************************************************
                    LITERALURA - BUSCADOR DE LIVROS
                    
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair
                    ***************************************************
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarLivroWeb();
                case 2 -> listarLivrosSalvos();
                case 3 -> listarAutoresSalvos();
                case 4 -> listarAutoresVivosPorAno();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivroWeb() {
        System.out.println("Digite o nome do livro:");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO_API + nomeLivro.replace(" ", "%20"));
        DadosResultado dadosBusca = conversor.obterDados(json, DadosResultado.class);

        if (dadosBusca.resultados() != null && !dadosBusca.resultados().isEmpty()) {
            DadosLivro dadosLivro = dadosBusca.resultados().get(0);

            if (livroRepository.findByTituloContainsIgnoreCase(dadosLivro.titulo()).isPresent()) {
                System.out.println("Livro já cadastrado!");
                return;
            }

            Livro livro = new Livro(dadosLivro);

            DadosAutor dadosAutor = dadosLivro.autores().get(0);
            Autor autor = new Autor(dadosAutor);
            livro.setAutor(autor);

            livroRepository.save(livro);
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void listarLivrosSalvos() {
        livroRepository.findAll().forEach(System.out::println);
    }

    private void listarAutoresSalvos() {
        autorRepository.findAll().forEach(System.out::println);
    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Digite o ano para pesquisa:");
        var ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autoresVivos = autorRepository.buscarAutoresVivosNoAno(ano);
        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado para esse ano.");
        } else {
            autoresVivos.forEach(System.out::println);
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Digite o idioma (ex: en, pt, fr, es):");
        var idioma = leitura.nextLine();
        List<Livro> livros = livroRepository.findByIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro nesse idioma.");
        } else {
            livros.forEach(System.out::println);
        }
    }
}