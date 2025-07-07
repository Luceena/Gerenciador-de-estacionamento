package dados;

import java.util.ArrayList;
import java.util.Objects;

public class Ocorrencia {
    private String tipo;
    private ArrayList<Veiculo> veiculos_envolvidos;
    private ArrayList<Cliente> envolvidos;

    public Ocorrencia(String tipo, ArrayList<Veiculo> veiculos_envolvidos, ArrayList<Cliente> envolvidos) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo da ocorrência não pode ser nulo ou vazio.");
        }
        if (veiculos_envolvidos == null) {
            throw new IllegalArgumentException("A lista de veículos envolvidos não pode ser nula.");
        }
        if (envolvidos == null) {
            throw new IllegalArgumentException("A lista de clientes envolvidos não pode ser nula.");
        }

        this.tipo = tipo;
        this.veiculos_envolvidos = veiculos_envolvidos;
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

    public ArrayList<Veiculo> getVeiculos_envolvidos() {
        return veiculos_envolvidos;
    }

    public void setVeiculos_envolvidos(ArrayList<Veiculo> veiculos_envolvidos) {
        if (veiculos_envolvidos == null) {
            throw new IllegalArgumentException("A lista de veículos envolvidos não pode ser nula.");
        }
        this.veiculos_envolvidos = veiculos_envolvidos;
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
                ", veiculos_envolvidos=" + veiculos_envolvidos +
                ", envolvidos=" + envolvidos +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.tipo);
        hash = 89 * hash + Objects.hashCode(this.veiculos_envolvidos);
        hash = 89 * hash + Objects.hashCode(this.envolvidos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final Ocorrencia other = (Ocorrencia) obj;
        return Objects.equals(this.tipo, other.tipo) &&
               Objects.equals(this.veiculos_envolvidos, other.veiculos_envolvidos) &&
               Objects.equals(this.envolvidos, other.envolvidos);
    }

    public String gerarRelatorio() {
        // Aqui você pode futuramente gerar um relatório da ocorrência
        return null;
    }
}
