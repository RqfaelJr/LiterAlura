package junior.rafael.Literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;
    private Integer downloads;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    public Livro() {
    }

    public Livro(DadosLivro dadoslivro) {
        this.titulo = dadoslivro.titulo();
        this.idioma = dadoslivro.idioma()[0];
        this.downloads = dadoslivro.downloads();
        this.autor = new Autor(dadoslivro.autor().getFirst());
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDowloads() {
        return downloads;
    }

    public void setDowloads(Integer dowloads) {
        this.downloads = dowloads;
    }
    public String informaTitulo() {
        return this.titulo;
    }
    @Override
    public String toString() {
        return "----- LIVRO -----" +
                "\nTítulo: " + titulo +
                "\nAutor: " + autor.getNome() +
                "\nIdioma: " + idioma +
                "\nNúmero de downloads: " + downloads +
                "\n----------------";
    }
}
