package dados;

public interface DadosInterface {

    public String nome();

    public String getIdentificador();

    public String getCaracteristicas();

    public DadosCliente getCaracteristicasReco(DadosInterface dado);

    public String getCaracteristicasSemFormatacao();
}