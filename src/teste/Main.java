package teste;

import graficos.JanelaPrincipal;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                System.err.println("Erro ao configurar Look and Feel: " + e.getMessage());
            }
            
            try {
                JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
                janelaPrincipal.setVisible(true);
                System.out.println("Sistema de Gerenciamento de Estacionamento iniciado com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao inicializar o sistema: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Erro ao inicializar o sistema:\n" + e.getMessage(), 
                    "Erro de Inicialização", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}