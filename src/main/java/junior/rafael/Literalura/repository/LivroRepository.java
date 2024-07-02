package junior.rafael.Literalura.repository;


import junior.rafael.Literalura.model.Autor;
import junior.rafael.Literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdiomaContainingIgnoreCase(String idioma);
    List<Livro> findTop5ByOrderByDownloadsDesc();
    @Query("SELECT a from Autor a WHERE a.anoFalecimento > :ano AND a.anoNascimento <= :ano")
    List<Autor> buscaAutorPorAno(int ano);


}
