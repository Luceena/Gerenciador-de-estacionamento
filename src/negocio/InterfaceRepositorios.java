package negocio;

import dados.DadosInterface;

public interface InterfaceRepositorios {

    public void adicionar(DadosInterface dado);

    public DadosInterface buscar(String identificador);

    private void salvarDados(){

    }

    private void carregarDados(){

    }

    public void remover(String identificador);

    public void atualizar(DadosInterface dado);

}