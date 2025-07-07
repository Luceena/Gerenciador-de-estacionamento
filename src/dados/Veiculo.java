package dados;

import java.util.Objects;


public class Veiculo {
    private String marca_modelo;
    private String ano;
    private String placa;
    private String tipo;

    public Veiculo(String marca_modelo, String ano, String placa, String tipo) {
        this.marca_modelo = marca_modelo;
        this.ano = ano;
        this.placa = placa;
        this.tipo = tipo;
    }

    public String getMarca_modelo() {
        return marca_modelo;
    }

    public void setMarca_modelo(String marca_modelo) {
        this.marca_modelo = marca_modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "marca_modelo=" + marca_modelo + ", ano=" + ano + ", placa=" + placa + ", tipo=" + tipo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.marca_modelo);
        hash = 59 * hash + Objects.hashCode(this.ano);
        hash = 59 * hash + Objects.hashCode(this.placa);
        hash = 59 * hash + Objects.hashCode(this.tipo);
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
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.marca_modelo, other.marca_modelo)) {
            return false;
        }
        if (!Objects.equals(this.ano, other.ano)) {
            return false;
        }
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        return Objects.equals(this.tipo, other.tipo);
    }
    
   
}
