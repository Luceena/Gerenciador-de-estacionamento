package dados;

import java.util.ArrayList;
import java.util.Objects;

public class Ocorrencia {
    private String tipo;
    private ArrayList<Veiculo> veiculosEnvolvidos;
    private ArrayList<Cliente> envolvidos;

    public Ocorrencia(String tipo, ArrayList<Veiculo> veiculosEnvolvidos, ArrayList<Cliente> envolvidos) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo da ocorrência não pode ser nulo ou vazio.");
        }
        if (veiculosEnvolvidos == null) {
            throw new IllegalArgumentException("A lista de veículos envolvidos não pode ser nula.");
        }
        if (envolvidos == null) {
            throw new IllegalArgumentException("A lista de clientes envolvidos não pode ser nula.");
        }

        this.tipo = tipo;
        this.veiculosEnvolvidos = veiculosEnvolvidos;
        this.envolvidos = envolvidos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo da ocorrência não pode ser nulo ou vazio.");
        }
        this.tipo = tipo;
    }

    public ArrayList<Veiculo> getVeiculosEnvolvidos() {
        return veiculosEnvolvidos;
    }

    public void setVeiculosEnvolvidos(ArrayList<Veiculo> veiculosEnvolvidos) {
        if (veiculosEnvolvidos == null) {
            throw new IllegalArgumentException("A lista de veículos envolvidos não pode ser nula.");
        }
        this.veiculosEnvolvidos = veiculosEnvolvidos;
    }

    public ArrayList<Cliente> getEnvolvidos() {
        return envolvidos;
    }

    public void setEnvolvidos(ArrayList<Cliente> envolvidos) {
        if (envolvidos == null) {
            throw new IllegalArgumentException("A lista de clientes envolvidos não pode ser nula.");
        }
        this.envolvidos = envolvidos;
    }

    @Override
    public String toString() {
        return "Ocorrencia{" +
                "tipo='" + tipo + '\'' +
                ", veiculosEnvolvidos=" + veiculosEnvolvidos +
                ", envolvidos=" + envolvidos +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.tipo);
        hash = 89 * hash + Objects.hashCode(this.veiculosEnvolvidos);
        hash = 89 * hash + Objects.hashCode(this.envolvidos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final Ocorrencia other = (Ocorrencia) obj;
        return Objects.equals(this.tipo, other.tipo) &&
               Objects.equals(this.veiculosEnvolvidos, other.veiculosEnvolvidos) &&
               Objects.equals(this.envolvidos, other.envolvidos);
    }

    public String gerarRelatorio() {
        // Aqui você pode futuramente gerar um relatório da ocorrência
        return null;
    }
}
