import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendedorGUI extends JFrame {

    
    private JTextField campoNombre;
    private JTextField campoApellidos;
    private JTextField campoEdad;
    private JTextArea areaSalida;
    private JButton botonRegistrar;

    public VendedorGUI() {
        
        setTitle("Registro de Vendedor");
        setSize(450, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

       
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 8, 8));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Vendedor"));

        panelFormulario.add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        panelFormulario.add(campoNombre);

        panelFormulario.add(new JLabel("Apellidos:"));
        campoApellidos = new JTextField();
        panelFormulario.add(campoApellidos);

        panelFormulario.add(new JLabel("Edad:"));
        campoEdad = new JTextField();
        panelFormulario.add(campoEdad);

        
        botonRegistrar = new JButton("Registrar Vendedor");

        
        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(botonRegistrar, BorderLayout.SOUTH);

        
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(areaSalida);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Información Registrada"));

        
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVendedor();
            }
        });
    }

    private void registrarVendedor() {
        areaSalida.setText("");

        String nombre = campoNombre.getText().trim();
        String apellidos = campoApellidos.getText().trim();
        String textoEdad = campoEdad.getText().trim();

        
        if (nombre.isEmpty() || apellidos.isEmpty() || textoEdad.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios.", 
                "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            
            int edad = Integer.parseInt(textoEdad);

            
            Vendedor vendedor = new Vendedor(nombre, apellidos);
            vendedor.verificarEdad(edad);

            
            areaSalida.append("===================================\n");
            areaSalida.append("VENDEDOR REGISTRADO CON ÉXITO\n");
            areaSalida.append("===================================\n");
            areaSalida.append("Nombre del vendedor    = " + vendedor.nombre + "\n");
            areaSalida.append("Apellidos del vendedor = " + vendedor.apellidos + "\n");
            areaSalida.append("Edad del vendedor      = " + vendedor.edad + "\n");

            JOptionPane.showMessageDialog(this, 
                "Vendedor registrado correctamente.", 
                "Registro Éxitoso", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            
            areaSalida.append("Error: La edad debe ser un número entero válido.\n");
            JOptionPane.showMessageDialog(this, 
                "La edad ingresada no es un número válido.", 
                "Error de Formato", JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException e) {
            
            areaSalida.append("Excepción capturada: " + e.getMessage() + "\n");
            JOptionPane.showMessageDialog(this, 
                e.getMessage(), 
                "Error de Validación de Edad", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VendedorGUI().setVisible(true);
        });
    }
}


class Vendedor {
    String nombre;
    String apellidos;
    int edad;

    Vendedor(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    void verificarEdad(int edad) {
        if (edad < 18) {
            throw new IllegalArgumentException("El vendedor debe ser mayor de 18 años.");
        }
        if (edad >= 0 && edad < 120) {
            this.edad = edad;
        } else {
            throw new IllegalArgumentException("La edad no puede ser negativa ni mayor a 120.");
        }
    }
}