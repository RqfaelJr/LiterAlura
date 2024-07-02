package junior.rafael.Literalura.principal;

import junior.rafael.Literalura.model.DadosLivro;
import junior.rafael.Literalura.model.DadosResutado;
import junior.rafael.Literalura.model.Livro;
import junior.rafael.Literalura.repository.LivroRepository;
import junior.rafael.Literalura.services.APIRequest;
import junior.rafael.Literalura.services.ConverteDados;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);

    private LivroRepository repository;
    public Principal(LivroRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        var menu = """
                1 - Buscar livro
                2 - Listar livros buscados
                3 - Listar autores
                4 - Listar autores vivos no ano
                5 - Listar livros pelo idioma
                6 - Listar top 5 livros mais baixados
                                
                0 - Sair
                """;
        var opcao = -1;
        while (opcao != 0) {
            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroWeb();
                    break;
                case 2:
                    listarLivrosSalvos();
                    break;
                case 3:
                    listarAutoresSalvos();
                    break;
                case 4:
                    listarAutoresVivosNoAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 6:
                    listarTop5Dowloads();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    sc.close();
                    break;
                default:
                    System.out.println("Erro");
                    break;
            }
        }
    }

    private DadosResutado chamarAPI(String nomeLivro) {
        ConverteDados converteDados = new ConverteDados();
        APIRequest apiRequest = new APIRequest();
        var json = apiRequest.request(nomeLivro);
        return converteDados.obterDados(json, DadosResutado.class);
    }

    private void buscarLivroWeb() {
        System.out.println("Informe o nome do livro: ");
        String nomeLivro = sc.nextLine();
        DadosResutado dadosResutado = chamarAPI(nomeLivro);
        DadosLivro dadosLivro = dadosResutado.resultados().getFirst();
        Livro livro = new Livro(dadosLivro);
        try {
            repository.save(livro);
        } catch (Exception e) {
            System.out.println("Não foi possível salvar no banco");
            System.out.println(e.getMessage());
        }
        System.out.println(livro);
    }

    private void listarLivrosSalvos() {
        List<Livro> livros = repository.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutoresSalvos() {
        repository.findAll()
                .forEach(l -> System.out.println(l.getAutor()));
    }

    private void listarAutoresVivosNoAno() {
        System.out.println("Informe um ano: ");
        int ano = sc.nextInt();
        sc.nextLine();
        repository.buscaAutorPorAno(ano)
                .forEach(System.out::println);
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Idiomas disponíveis: (pt/en/es/fr) ");
        System.out.println("Informe o idioma para busca: ");
        String idioma = sc.nextLine();

        List<Livro> livrosEncontrados = repository.findByIdiomaContainingIgnoreCase(idioma);
        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro deste idioma encontrado!");
        } else {
            livrosEncontrados.forEach(System.out::println);
        }
    }

    private void listarTop5Dowloads() {
        repository.findTop5ByOrderByDownloadsDesc().forEach(System.out::println);
    }


}
