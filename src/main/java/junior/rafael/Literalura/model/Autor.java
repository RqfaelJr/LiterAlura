package junior.rafael.Literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor() {
    }

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivro() {
        return livros;
    }

    public void setLivro(List<Livro> livro) {
        livro.forEach(l -> l.setAutor(this));
        this.livros = livro;
    }

    public List<String> escreveLivro() {
        List<String> nomeLivro = new ArrayList<>();
        for (int i = 0; i < livros.size(); i++) {
            nomeLivro.add(livros.get(i).informaTitulo());
        }
        return nomeLivro;
    }
    @Override
    public String toString() {
        return "----- AUTOR -----" +
                "\nNome: " + nome +
                "\nAno de nascimento: " + anoNascimento +
                "\nAno de falecimento: " + anoFalecimento +
                "\nLivros: " + escreveLivro().toString() +
                "\n----------------";
    }
}
