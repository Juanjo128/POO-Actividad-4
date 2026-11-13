import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class CalculosNumericosGUI extends JFrame {

    private JTextField campoValor;
    private JTextArea areaResultados;
    private JButton botonCalcular;

    public CalculosNumericosGUI() {

        setTitle("Cálculos Numéricos - Excepciones");
        setSize(480, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JPanel panelEntrada = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelEntrada.add(new JLabel("Valor numérico:"));
        campoValor = new JTextField(12);
        panelEntrada.add(campoValor);

        botonCalcular = new JButton("Calcular");
        panelEntrada.add(botonCalcular);


        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(areaResultados);

        add(panelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);


        botonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarCalculos();
            }
        });
    }

    private void procesarCalculos() {
        areaResultados.setText("");
        String textoIngresado = campoValor.getText().trim();

        double valor = 0;
        boolean entradaValida = true;

        try {
            valor = Double.parseDouble(textoIngresado);
        } catch (NumberFormatException e) {
            entradaValida = false;
        }


        calcularLogaritmoNeperiano(valor, entradaValida);
        areaResultados.append("---------------------------------------\n");
        calcularRaizCuadrada(valor, entradaValida);
    }

    private void calcularLogaritmoNeperiano(double valor, boolean entradaValida) {
        try {
            if (!entradaValida) {
                throw new InputMismatchException();
            }
            if (valor < 0) {
                throw new ArithmeticException("El valor debe ser un número positivo");
            }
            double resultado = Math.log(valor);
            areaResultados.append("Logaritmo Neperiano:\nResultado = " + resultado + "\n");
        } catch (ArithmeticException e) {
            areaResultados.append("El valor debe ser un número positivo para calcular el logaritmo\n");
            JOptionPane.showMessageDialog(this, 
                "El valor debe ser un número positivo para calcular el logaritmo", 
                "Error de Cálculo", JOptionPane.ERROR_MESSAGE);
        } catch (InputMismatchException e) {
            areaResultados.append("El valor debe ser numérico para calcular el logaritmo\n");
            JOptionPane.showMessageDialog(this, 
                "El valor debe ser numérico para calcular el logaritmo", 
                "Error de Entrada", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void calcularRaizCuadrada(double valor, boolean entradaValida) {
        try {
            if (!entradaValida) {
                throw new InputMismatchException();
            }
            if (valor < 0) {
                throw new ArithmeticException("El valor debe ser un número positivo");
            }
            double resultado = Math.sqrt(valor);
            areaResultados.append("Raíz Cuadrada:\nResultado = " + resultado + "\n");
        } catch (ArithmeticException e) {
            areaResultados.append("El valor debe ser un número positivo para calcular la raíz cuadrada\n");
            JOptionPane.showMessageDialog(this, 
                "El valor debe ser un número positivo para calcular la raíz cuadrada", 
                "Error de Cálculo", JOptionPane.ERROR_MESSAGE);
        } catch (InputMismatchException e) {
            areaResultados.append("El valor debe ser numérico para calcular la raíz cuadrada\n");
            JOptionPane.showMessageDialog(this, 
                "El valor debe ser numérico para calcular la raíz cuadrada", 
                "Error de Entrada", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculosNumericosGUI().setVisible(true);
        });
    }
}