import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarathonAppGUI extends JFrame {

  
    private JTextField campoNombreEquipo;
    private JTextField campoUniversidad;
    private JTextField campoLenguaje;
    private JButton botonCrearEquipo;
    private JTextField campoNombreProg;
    private JTextField campoApellidosProg;
    private JButton botonAgregarProg;
    private JTextArea areaSalida;
    private EquipoMaratonProgramacion equipo;

    public MarathonAppGUI() {
        setTitle("Registro - Equipo de Maratón de Programación");
        setSize(550, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        JPanel panelFormularios = new JPanel();
        panelFormularios.setLayout(new BoxLayout(panelFormularios, BoxLayout.Y_AXIS));
        JPanel panelEquipo = new JPanel(new GridLayout(3, 2, 5, 5));
        panelEquipo.setBorder(BorderFactory.createTitledBorder("1. Datos del Equipo"));

        panelEquipo.add(new JLabel("Nombre del Equipo:"));
        campoNombreEquipo = new JTextField();
        panelEquipo.add(campoNombreEquipo);

        panelEquipo.add(new JLabel("Universidad:"));
        campoUniversidad = new JTextField();
        panelEquipo.add(campoUniversidad);

        panelEquipo.add(new JLabel("Lenguaje de Programación:"));
        campoLenguaje = new JTextField();
        panelEquipo.add(campoLenguaje);

        botonCrearEquipo = new JButton("Crear Equipo");

        JPanel panelBotonEquipo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonEquipo.add(botonCrearEquipo);

        JPanel panelProgramador = new JPanel(new GridLayout(2, 2, 5, 5));
        panelProgramador.setBorder(BorderFactory.createTitledBorder("2. Agregar Integrante (Máx 3)"));

        panelProgramador.add(new JLabel("Nombre del Integrante:"));
        campoNombreProg = new JTextField();
        panelProgramador.add(campoNombreProg);

        panelProgramador.add(new JLabel("Apellidos del Integrante:"));
        campoApellidosProg = new JTextField();
        panelProgramador.add(campoApellidosProg);

        botonAgregarProg = new JButton("Agregar Integrante");
        botonAgregarProg.setEnabled(false);

        JPanel panelBotonProg = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonProg.add(botonAgregarProg);

        panelFormularios.add(panelEquipo);
        panelFormularios.add(panelBotonEquipo);
        panelFormularios.add(panelProgramador);
        panelFormularios.add(panelBotonProg);

        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaSalida);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Estado del Equipo"));

        add(panelFormularios, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        botonCrearEquipo.addActionListener(e -> crearEquipo());
        botonAgregarProg.addActionListener(e -> agregarIntegrante());
    }

    private void crearEquipo() {
        String nombre = campoNombreEquipo.getText().trim();
        String universidad = campoUniversidad.getText().trim();
        String lenguaje = campoLenguaje.getText().trim();

        if (nombre.isEmpty() || universidad.isEmpty() || lenguaje.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Todos los campos del equipo son obligatorios.", 
                "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            EquipoMaratonProgramacion.validarCampo(nombre);
            
            equipo = new EquipoMaratonProgramacion(nombre, universidad, lenguaje);
            
            areaSalida.setText("");
            areaSalida.append("=== EQUIPO CREADO EXITOSAMENTE ===\n");
            areaSalida.append("Equipo: " + equipo.nombreEquipo + "\n");
            areaSalida.append("Universidad: " + equipo.universidad + "\n");
            areaSalida.append("Lenguaje: " + equipo.lenguajeProgramacion + "\n");
            areaSalida.append("-----------------------------------\n");
            campoNombreEquipo.setEnabled(false);
            campoUniversidad.setEnabled(false);
            campoLenguaje.setEnabled(false);
            botonCrearEquipo.setEnabled(false);
            botonAgregarProg.setEnabled(true);

            JOptionPane.showMessageDialog(this, 
                "Equipo creado. Ahora agregue a los 3 integrantes.", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error en el nombre del equipo: " + ex.getMessage(), 
                "Validación Fallida", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarIntegrante() {
        String nombre = campoNombreProg.getText().trim();
        String apellidos = campoApellidosProg.getText().trim();

        if (nombre.isEmpty() || apellidos.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Debe ingresar el nombre y los apellidos del integrante.", 
                "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            EquipoMaratonProgramacion.validarCampo(nombre);
            EquipoMaratonProgramacion.validarCampo(apellidos);

            Programador p = new Programador(nombre, apellidos);
            equipo.añadir(p);

            areaSalida.append("Integrante " + equipo.tamañoEquipo + "/3: " + p.nombre + " " + p.apellidos + "\n");
            
            campoNombreProg.setText("");
            campoApellidosProg.setText("");

            if (equipo.estáLleno()) {
                areaSalida.append("-----------------------------------\n");
                areaSalida.append("¡EQUIPO COMPLETO REGISTRADO CON ÉXITO!\n");
                botonAgregarProg.setEnabled(false);
                campoNombreProg.setEnabled(false);
                campoApellidosProg.setEnabled(false);
                
                JOptionPane.showMessageDialog(this, 
                    "El equipo se ha completado con éxito con sus 3 integrantes.", 
                    "Registro Finalizado", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                ex.getMessage(), 
                "Error al Agregar Integrante", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MarathonAppGUI().setVisible(true);
        });
    }
}
