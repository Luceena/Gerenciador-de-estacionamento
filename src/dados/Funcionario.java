package dados;

import java.util.Objects;

public class Funcionario extends Pessoa {

    private double salario;
    private double avaliacao;
    private String tempoAtendimento;

    public Funcionario(double salario, double avaliacao, String tempoAtendimento, String nome, String cpf) {
        super(Objects.requireNonNull(nome, "Nome não pode ser nulo."),
              Objects.requireNonNull(cpf, "CPF não pode ser nulo."));

        if (salario < 0) {
            throw new IllegalArgumentException("Salário não pode ser negativo.");
        }

        if (avaliacao < 0.0 || avaliacao > 10.0) {
            throw new IllegalArgumentException("Avaliação deve estar entre 0.0 e 10.0.");
        }

        this.salario = salario;
        this.avaliacao = avaliacao;
        this.tempoAtendimento = Objects.requireNonNull(tempoAtendimento, "Tempo de atendimento não pode ser nulo.");
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario < 0) {
            throw new IllegalArgumentException("Salário não pode ser negativo.");
        }
        this.salario = salario;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        if (avaliacao < 0.0 || avaliacao > 10.0){
            throw new IllegalArgumentException("Avaliação deve estar entre 0.0 e 10.0.");
        }
        this.avaliacao = avaliacao;
    }

    public String getTempoAtendimento() {
        return tempoAtendimento;
    }

    public void setTempoAtendimento(String tempoAtendimento) {
        this.tempoAtendimento = Objects.requireNonNull(tempoAtendimento, "Tempo de atendimento não pode ser nulo.");
    }

    @Override
    public String toString() {
        return "Funcionario{" + "salario=" + salario + ", avaliacao=" + avaliacao + ", tempoAtendimento=" + tempoAtendimento + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.salario) ^ (Double.doubleToLongBits(this.salario) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.avaliacao) ^ (Double.doubleToLongBits(this.avaliacao) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.tempoAtendimento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        final Funcionario other = (Funcionario) obj;
        return Double.doubleToLongBits(this.salario) == Double.doubleToLongBits(other.salario)
            && Double.doubleToLongBits(this.avaliacao) == Double.doubleToLongBits(other.avaliacao)
            && Objects.equals(this.tempoAtendimento, other.tempoAtendimento);
    }

    public void crudFuncionario() {
        // lógica de CRUD será adicionada aqui
    }
}
