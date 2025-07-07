package dados;

import java.util.Objects;

public class Vaga {
    private int numero;
    private String tipo;
    private boolean preferencial;
    private boolean ocupada;

    public Vaga(int numero, String tipo, boolean preferencial, boolean ocupada) {
        setNumero(numero);
        setTipo(tipo);
        this.preferencial = preferencial;
        this.ocupada = ocupada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("O número da vaga deve ser maior que zero.");
        }
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo da vaga não pode ser nulo ou vazio.");
        }
        this.tipo = tipo;
    }

    public boolean isPreferencial() {
        return preferencial;
    }

    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "Vaga{" +
               "numero=" + numero +
               ", tipo=" + tipo +
               ", preferencial=" + preferencial +
               ", ocupada=" + ocupada +
               '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.numero;
        hash = 47 * hash + Objects.hashCode(this.tipo);
        hash = 47 * hash + (this.ocupada ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final Vaga other = (Vaga) obj;
        return this.numero == other.numero &&
               this.preferencial == other.preferencial &&
               this.ocupada == other.ocupada &&
               Objects.equals(this.tipo, other.tipo);
    }
}
