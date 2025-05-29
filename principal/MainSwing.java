package principal;

import gui.BibliotecaSwingInterface;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

/**
 * Classe principal para executar a interface gráfica do sistema
 */
public class MainSwing {
    
    public static void main(String[] args) {
        // Executar na thread de eventos do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Criar e exibir a interface gráfica
                    BibliotecaSwingInterface sistema = new BibliotecaSwingInterface();
                    sistema.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, 
                        "Erro ao inicializar o sistema: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}