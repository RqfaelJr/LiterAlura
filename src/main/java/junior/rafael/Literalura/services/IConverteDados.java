package junior.rafael.Literalura.services;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);
}
