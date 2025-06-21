package gerenciador_de_estacionamentos;

import java.util.Objects;


public class Vaga {
    private int numero;
    private String tipo;
    private boolean pcd;
    private boolean idoso;
    private boolean ocupada;

    public Vaga(int numero, String tipo, boolean pcd, boolean idoso, boolean ocupada) {
        this.numero = numero;
        this.tipo = tipo;
        this.pcd = pcd;
        this.idoso = idoso;
        this.ocupada = ocupada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isPcd() {
        return pcd;
    }

    public void setPcd(boolean pcd) {
        this.pcd = pcd;
    }

    public boolean isIdoso() {
        return idoso;
    }

    public void setIdoso(boolean idoso) {
        this.idoso = idoso;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "Vaga{" + "numero=" + numero + ", tipo=" + tipo + ", pcd=" + pcd + ", idoso=" + idoso + ", ocupada=" + ocupada + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.numero;
        hash = 79 * hash + Objects.hashCode(this.tipo);
        hash = 79 * hash + (this.pcd ? 1 : 0);
        hash = 79 * hash + (this.idoso ? 1 : 0);
        hash = 79 * hash + (this.ocupada ? 1 : 0);
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
        final Vaga other = (Vaga) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (this.pcd != other.pcd) {
            return false;
        }
        if (this.idoso != other.idoso) {
            return false;
        }
        if (this.ocupada != other.ocupada) {
            return false;
        }
        return Objects.equals(this.tipo, other.tipo);
    }
    
    
    
    
    
    
    }
