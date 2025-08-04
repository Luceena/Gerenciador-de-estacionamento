package negocio;

public class ControladorEstacionamento {

    private static ControladorEstacionamento instance;

    private ControladorEstacionamento() {

    }

    public static ControladorEstacionamento getInstance() {
        if (instance == null) {
            synchronized (ControladorEstacionamento.class) {
                if (instance == null) {
                    instance = new ControladorEstacionamento();
                }
            }
        }
        return instance;
    }

    public static void abrirCancela() {
        System.out.println("Abrir cancela");
    }

    public static void fecharCancela() {
        System.out.println("Fechar cancela");
    }

}
