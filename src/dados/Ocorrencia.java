package dados;

import java.util.ArrayList;
import java.util.Objects;


public class Ocorrencia {
    private String tipo;
    private ArrayList<Veiculo> veiculos_envolvidos;
    private ArrayList<Cliente> envolvidos;

    public Ocorrencia(String tipo, ArrayList<Veiculo> veiculos_envolvidos, ArrayList<Cliente> envolvidos) {
        this.tipo = tipo;
        this.veiculos_envolvidos = veiculos_envolvidos;
        this.envolvidos = envolvidos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Veiculo> getVeiculos_envolvidos() {
        return veiculos_envolvidos;
    }

    public void setVeiculos_envolvidos(ArrayList<Veiculo> veiculos_envolvidos) {
        this.veiculos_envolvidos = veiculos_envolvidos;
    }

    public ArrayList<Cliente> getEnvolvidos() {
        return envolvidos;
    }

    public void setEnvolvidos(ArrayList<Cliente> envolvidos) {
        this.envolvidos = envolvidos;
    }

    @Override
    public String toString() {
        return "Ocorrencia{" + "tipo=" + tipo + ", veiculos_envolvidos=" + veiculos_envolvidos + ", envolvidos=" + envolvidos + '}';
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ocorrencia other = (Ocorrencia) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.veiculos_envolvidos, other.veiculos_envolvidos)) {
            return false;
        }
        return Objects.equals(this.envolvidos, other.envolvidos);
    }
    
    public String gerarRelatorio(){
        return null;
    }
    
}
