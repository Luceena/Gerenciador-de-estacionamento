package dados;

import java.util.ArrayList;
import java.util.Objects;


public class Cliente extends Pessoa {
    private ArrayList<Veiculo> veiculos;
    private int frequencia;
    private String classificacao;
    private int idade;
    private boolean adimplente;

    public Cliente(ArrayList<Veiculo> veiculos, String nome, String cpf, int idade) {
        super(nome, cpf);
        this.veiculos = veiculos;
        this.frequencia = 0;
        this.classificacao = "neutra";
        this.idade = idade;
        this.adimplente = true;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean isAdimplente() {
        return adimplente;
    }

    public void setAdimplente(boolean adimplente) {
        this.adimplente = adimplente;
    }

    @Override
    public String toString() {
        return "Cliente{" + "veiculos=" + veiculos + ", frequencia=" + frequencia + ", classificacao=" + classificacao + ", idade=" + idade + ", adimplente=" + adimplente + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.veiculos);
        hash = 97 * hash + this.frequencia;
        hash = 97 * hash + Objects.hashCode(this.classificacao);
        hash = 97 * hash + this.idade;
        hash = 97 * hash + (this.adimplente ? 1 : 0);
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
        final Cliente other = (Cliente) obj;
        if (this.frequencia != other.frequencia) {
            return false;
        }
        if (this.idade != other.idade) {
            return false;
        }
        if (this.adimplente != other.adimplente) {
            return false;
        }
        if (!Objects.equals(this.classificacao, other.classificacao)) {
            return false;
        }
        return Objects.equals(this.veiculos, other.veiculos);
    }
    
 }
