import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class VentanaExcepcionesDialogos extends JFrame {

    public VentanaExcepcionesDialogos() {

        setTitle("Demostración de Excepciones con Diálogos");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JButton botonEjecutar = new JButton("Iniciar Flujo con JOptionPane");
        botonEjecutar.addActionListener(e -> ejecutarFlujoConDialogos());

        add(botonEjecutar, BorderLayout.CENTER);
    }

    private void ejecutarFlujoConDialogos() {
        try {
            JOptionPane.showMessageDialog(this, "Ingresando al primer try", "Información", JOptionPane.INFORMATION_MESSAGE);
            
            int cociente = 10000 / 0;
            
            JOptionPane.showMessageDialog(this, "Después de la división", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(this, "División por cero", "Error Capturado", JOptionPane.ERROR_MESSAGE);
        } finally {
            JOptionPane.showMessageDialog(this, "Ingresando al primer finally", "Bloque Finally", JOptionPane.WARNING_MESSAGE);
        }

        try {
            JOptionPane.showMessageDialog(this, "Ingresando al segundo try", "Información", JOptionPane.INFORMATION_MESSAGE);
            
            Object objeto = null;
            objeto.toString();
            
            JOptionPane.showMessageDialog(this, "Imprimiendo objeto", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(this, "División por cero", "Error Capturado", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió una excepción: " + e.getClass().getSimpleName(), "Error Capturado", JOptionPane.ERROR_MESSAGE);
        } finally {
            JOptionPane.showMessageDialog(this, "Ingresando al segundo finally", "Bloque Finally", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaExcepcionesDialogos().setVisible(true);
        });
    }
}