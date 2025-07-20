package dados;

import java.util.Objects;

public class Veiculo {
    private String marcaModelo;
    private String ano;
    private String placa;
    private String tipo;

    public Veiculo(String marca_modelo, String ano, String placa, String tipo) {
        setMarcaModelo(marca_modelo);
        setAno(ano);
        setPlaca(placa);
        setTipo(tipo);
    }

    public String getMarcaModelo() {
        return marcaModelo;
    }

    public void setMarcaModelo(String marcaModelo) {
        if (marcaModelo == null || marcaModelo.trim().isEmpty()) {
            throw new IllegalArgumentException("Marca/modelo não pode ser nulo ou vazio.");
        }
        this.marcaModelo = marcaModelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        if (ano == null || ano.trim().isEmpty()) {
            throw new IllegalArgumentException("Ano não pode ser nulo ou vazio.");
        }
        if (ano.length() > 4) {
            throw new IllegalArgumentException("Ano deve conter 4 dígitos numéricos.");
        }
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("Placa não pode ser nula ou vazia.");
        }
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de veículo não pode ser nulo ou vazio.");
        }
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "marcaModelo=" + marcaModelo + ", ano=" + ano + ", placa=" + placa + ", tipo=" + tipo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.marcaModelo);
        hash = 59 * hash + Objects.hashCode(this.ano);
        hash = 59 * hash + Objects.hashCode(this.placa);
        hash = 59 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        final Veiculo other = (Veiculo) obj;
        return Objects.equals(this.marcaModelo, other.marcaModelo) &&
                Objects.equals(this.ano, other.ano) &&
                Objects.equals(this.placa, other.placa) &&
                Objects.equals(this.tipo, other.tipo);
    }
}
