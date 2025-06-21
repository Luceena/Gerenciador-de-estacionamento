package gerenciador_de_estacionamentos;

import java.util.Objects;


public class Funcionario extends Pessoa {

    private double salario;
    private double avaliacao;
    private String tempoAtendimento;

    public Funcionario(double salario, double avaliacao, String tempoAtendimento, String nome, String cpf) {
        super(nome, cpf);
        this.salario = salario;
        this.avaliacao = avaliacao;
        this.tempoAtendimento = tempoAtendimento;
    }
    
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getTempoAtendimento() {
        return tempoAtendimento;
    }

    public void setTempoAtendimento(String tempoAtendimento) {
        this.tempoAtendimento = tempoAtendimento;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        if (Double.doubleToLongBits(this.salario) != Double.doubleToLongBits(other.salario)) {
            return false;
        }
        if (Double.doubleToLongBits(this.avaliacao) != Double.doubleToLongBits(other.avaliacao)) {
            return false;
        }
        return Objects.equals(this.tempoAtendimento, other.tempoAtendimento);
    }
    
    
    
    public void crudFuncionario(){}
    
}
