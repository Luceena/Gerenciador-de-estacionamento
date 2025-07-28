package dados;

public record DadosCliente(
    String nome,
    String cpf,
    int idade,
    String classificacao,
    int frequencia,
    boolean adimplente
) {}
