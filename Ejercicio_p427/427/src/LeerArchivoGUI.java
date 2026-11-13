import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LeerArchivoGUI extends JFrame {

    private JTextField campoRuta;
    private JButton botonExaminar;
    private JButton botonLeer;
    private JTextArea areaContenido;

    public LeerArchivoGUI() {

        setTitle("Lector de Archivos de Texto");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Selección de Archivo"));

        campoRuta = new JTextField("C:/prueba.txt");
        botonExaminar = new JButton("Examinar...");
        botonLeer = new JButton("Leer Archivo");

        JPanel panelBotonesRuta = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        panelBotonesRuta.add(botonExaminar);
        panelBotonesRuta.add(botonLeer);

        panelSuperior.add(campoRuta, BorderLayout.CENTER);
        panelSuperior.add(panelBotonesRuta, BorderLayout.EAST);

        areaContenido = new JTextArea();
        areaContenido.setEditable(false);
        areaContenido.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaContenido);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Contenido del Archivo"));


        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        botonExaminar.addActionListener(e -> seleccionarArchivo());
        botonLeer.addActionListener(e -> leerArchivo(campoRuta.getText().trim()));
    }

    private void seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            campoRuta.setText(archivoSeleccionado.getAbsolutePath());
        }
    }

    private void leerArchivo(String nombreArchivo) {
        areaContenido.setText("");

        if (nombreArchivo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, ingrese o seleccione una ruta de archivo válida.",
                "Ruta Vacía", JOptionPane.WARNING_MESSAGE);
            return;
        }

        FileInputStream archivo;
        InputStreamReader conversor;
        BufferedReader filtro;
        String línea;

        try {
            archivo = new FileInputStream(nombreArchivo);
            conversor = new InputStreamReader(archivo);
            filtro = new BufferedReader(conversor);

            línea = filtro.readLine();
            while (línea != null) {
                areaContenido.append(línea + "\n");
                línea = filtro.readLine();
            }

            filtro.close();

        } catch (IOException e) {
            areaContenido.setText("No se pudo leer el archivo.");
            JOptionPane.showMessageDialog(this,
                "No se pudo leer el archivo especificado:\n" + nombreArchivo,
                "Error de Lectura", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LeerArchivoGUI().setVisible(true);
        });
    }
}