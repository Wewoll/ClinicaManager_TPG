package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controlador.Controlador;
import modelo.modeloDominio.personas.asociado.Asociado;
import persistencia.AsociadoDTO;

import java.util.ArrayList;
import java.util.Locale;

public class VentanaPrincipal extends JFrame implements IVistaPrincipal {
    private JTabbedPane pestañas;
    private JPanel altaAsociado;
    private JTextField Nombre_Alta;
    private JTextField Apellido_Baja;
    private JTextField DNI_Alta;
    private JTextField Ciudad_Alta;
    private JTextField Calle_Alta;
    private JTextField Numero_Alta;
    private JTextField Telefono_Alta;
    private JPanel bajaAsociado;
    private JPanel listaAsociado;
    private JPanel panelPrincipal;
    private JButton darDeAltaButton;
    private JTextField DNI_Baja;
    private JButton darDeBajaButton;
    private JList listaAsociados;
    private JButton aceptarConfiguracionButton;
    private JTextField cantAsociados;
    private JTextField cantSolicitudes;
    private JPanel panelConfiguracion;

    private DefaultListModel<String> listaModel;


    public VentanaPrincipal() {
        setContentPane(panelPrincipal);
        setTitle("Gestion de Asociados");
        setSize(800, 690);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        darDeAltaButton.setActionCommand(DAR_ALTA);
        darDeBajaButton.setActionCommand(DAR_BAJA);
        setVisible(true);
        this.listaModel = new DefaultListModel<>();
        listaAsociados.setModel(this.listaModel);
        aplicarEstilos();
    }

    @Override
    public void setActionListener(Controlador controlador) {
        this.darDeAltaButton.addActionListener(controlador);
        this.darDeBajaButton.addActionListener(controlador);
        this.aceptarConfiguracionButton.addActionListener(controlador);
    }

    @Override
    public AsociadoDTO getNuevoAsociado() throws datosAsociadoDTOIncorrectoException {
        String nombre = Nombre_Alta.getText();
        String apellido = Apellido_Baja.getText();
        String dni = DNI_Alta.getText();
        String ciudad = Ciudad_Alta.getText();
        String calle = Calle_Alta.getText();
        String numero = Numero_Alta.getText();
        String telefono = Telefono_Alta.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || ciudad.isEmpty() || calle.isEmpty() || telefono.isEmpty()) {
            throw new datosAsociadoDTOIncorrectoException("Debe completar todos los campos.");
        }

        int numeroReal = Integer.parseInt(numero);
        if (numeroReal <= 0) {
            throw new datosAsociadoDTOIncorrectoException("El numero de la calle debe ser un valor positivo.");
        }

        if (telefono.startsWith("-")) {
            throw new datosAsociadoDTOIncorrectoException("El telefono no puede ser un valor negativo.");
        }
        return new AsociadoDTO(nombre, apellido, dni, ciudad, calle, numeroReal, telefono);
    }

    @Override
    public int getCantAsociados() {
        if (cantAsociados.getText().isEmpty()) {
            throw new datosSimulacionIncorrectosException("La cantidad de asociados no puede estar vacia.");
        }

        int cant = Integer.parseInt(cantAsociados.getText());
        if (cant <= 0)
            throw new datosSimulacionIncorrectosException("La cantidad de asociados debe ser un valor positivo.");
        return cant;
    }


    @Override
    public int getCantSolicitudes() {
        if (cantSolicitudes.getText().isEmpty()) {
            throw new datosSimulacionIncorrectosException("La cantidad de solicitudes no puede estar vacia.");
        }

        int cant = Integer.parseInt(cantSolicitudes.getText());
        if (cant <= 0)
            throw new datosSimulacionIncorrectosException("La cantidad de solicitudes debe ser un valor positivo.");
        return cant;
    }

    @Override
    public void mostrarMensaje(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void limpiarFormularioAlta() {
        Nombre_Alta.setText("");
        Apellido_Baja.setText("");
        DNI_Alta.setText("");
        Ciudad_Alta.setText("");
        Calle_Alta.setText("");
        Numero_Alta.setText("");
        Telefono_Alta.setText("");
    }

    @Override
    public void limpiarFormularioBaja() {
        DNI_Baja.setText("");
    }

    @Override
    public void actualizarListaAsociados(ArrayList<AsociadoDTO> asociados) {
        this.listaModel.clear();
        String aux;
        for (AsociadoDTO a : asociados) {
            aux = "Nombre: " + a.getNombre() + ", Apellido: " + a.getApellido() + ", DNI: " + a.getDni();
            this.listaModel.addElement(aux);
        }
    }

    @Override
    public String getDNI() throws datosAsociadoDTOIncorrectoException {
        if (DNI_Baja.getText().isEmpty()) {
            throw new datosAsociadoDTOIncorrectoException("El campo DNI no puede estar vacio.");
        }

        if(Integer.parseInt(DNI_Baja.getText()) <= 0)
            throw new datosAsociadoDTOIncorrectoException("El DNI debe ser un valor positivo.");

        return DNI_Baja.getText();
    }


    // Método para aplicar estilo a los componentes creados por $$$setupUI$$$
    private void aplicarEstilos() {
        // Paleta de colores
        Color fondoPrincipal = new Color(245, 247, 250);
        Color panelColor = Color.WHITE;
        Color bordePanel = new Color(220, 220, 220);
        Color botonColor = new Color(70, 130, 180);
        Color botonHover = new Color(60, 110, 160);
        Color labelColor = new Color(50, 50, 50);

        // Fondo principal
        panelPrincipal.setBackground(fondoPrincipal);

        // Quitar bordes pesados y suavizar paneles (menos líneas)
        JPanel[] panels = {altaAsociado, bajaAsociado, listaAsociado, panelConfiguracion};
        for (JPanel p : panels) {
            if (p != null) {
                p.setBackground(panelColor);
                // remove titled/line borders created by GUI designer
                p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
                p.setOpaque(true);
            }
        }

        // Botones modernos (alta / baja)
        JButton[] buttons = {darDeAltaButton, darDeBajaButton};
        for (JButton b : buttons) {
            if (b == null) continue;
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setBackground(botonColor);
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Hover effect
            b.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    b.setBackground(botonHover);
                }

                public void mouseExited(MouseEvent evt) {
                    b.setBackground(botonColor);
                }
            });
        }

        // Estilo para el boton Aceptar (Simulacion)
        if (aceptarConfiguracionButton != null) {
            JButton b = aceptarConfiguracionButton;
            b.setFont(new Font("Segoe UI", Font.BOLD, 13));
            b.setBackground(new Color(88, 101, 242)); // slightly different accent
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            b.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    b.setBackground(botonHover);
                }

                public void mouseExited(MouseEvent evt) {
                    b.setBackground(new Color(88, 101, 242));
                }
            });
        }

        // JList: cleaner background, subtle border removed
        listaAsociados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listaAsociados.setBackground(new Color(250, 250, 250));
        listaAsociados.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        listaAsociados.setSelectionBackground(new Color(70, 130, 180, 80));
        listaAsociados.setSelectionForeground(Color.BLACK);

        // Pestañas: cleaner, no heavy border
        pestañas.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pestañas.setBackground(fondoPrincipal);
        pestañas.setForeground(labelColor);
        pestañas.setBorder(BorderFactory.createEmptyBorder());
        pestañas.setOpaque(false);

        // Panel de configuracion (Simulacion): centrar contenido y reducir elementos visuales
        if (panelConfiguracion != null) {
            panelConfiguracion.setBackground(fondoPrincipal);
            panelConfiguracion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }

        // Añadir scroll limpio para la lista
        listaAsociado.setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(listaAsociados);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(),
                BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));
        listaAsociado.removeAll();
        listaAsociado.add(scroll, BorderLayout.CENTER);

        // Hacer que los textFields se vean más modernos (si existen)
        JTextField[] fields = {Nombre_Alta, Apellido_Baja, DNI_Alta, Ciudad_Alta, Calle_Alta, Numero_Alta, Telefono_Alta, DNI_Baja, cantAsociados, cantSolicitudes};
        for (JTextField tf : fields) {
            if (tf == null) continue;
            tf.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, bordePanel));
            tf.setBackground(new Color(250, 250, 250));
            tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            tf.setPreferredSize(new Dimension(150, 26));
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        pestañas = new JTabbedPane();
        panelPrincipal.add(pestañas, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        pestañas.addTab("Gestion de Asociados", panel1);
        altaAsociado = new JPanel();
        altaAsociado.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(15, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(altaAsociado, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        altaAsociado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Alta Asociado", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        altaAsociado.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Nombre");
        panel2.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Nombre_Alta = new JTextField();
        panel2.add(Nombre_Alta, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        altaAsociado.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Apellido");
        panel3.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Apellido_Baja = new JTextField();
        panel3.add(Apellido_Baja, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        altaAsociado.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("DNI");
        panel4.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DNI_Alta = new JTextField();
        panel4.add(DNI_Alta, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        altaAsociado.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Ciudad");
        panel5.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Ciudad_Alta = new JTextField();
        panel5.add(Ciudad_Alta, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        altaAsociado.add(panel6, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Calle");
        panel6.add(label5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Calle_Alta = new JTextField();
        panel6.add(Calle_Alta, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        altaAsociado.add(panel7, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Numero");
        panel7.add(label6, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Numero_Alta = new JTextField();
        panel7.add(Numero_Alta, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        altaAsociado.add(panel8, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Telefono");
        panel8.add(label7, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Telefono_Alta = new JTextField();
        panel8.add(Telefono_Alta, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        darDeAltaButton = new JButton();
        darDeAltaButton.setActionCommand("DAR_ALTA");
        darDeAltaButton.setText("Dar de alta");
        altaAsociado.add(darDeAltaButton, new com.intellij.uiDesigner.core.GridConstraints(14, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bajaAsociado = new JPanel();
        bajaAsociado.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(bajaAsociado, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bajaAsociado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Baja Asociado", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label8 = new JLabel();
        label8.setText("DNI");
        bajaAsociado.add(label8, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DNI_Baja = new JTextField();
        bajaAsociado.add(DNI_Baja, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        darDeBajaButton = new JButton();
        darDeBajaButton.setActionCommand("DAR_BAJA");
        darDeBajaButton.setText("Dar de baja");
        bajaAsociado.add(darDeBajaButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listaAsociado = new JPanel();
        listaAsociado.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(listaAsociado, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        listaAsociado.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Lista Asociados", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        listaAsociados = new JList();
        listaAsociado.add(listaAsociados, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pestañas.addTab("Simulacion", panel9);
        panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        Font panelConfiguracionFont = this.$$$getFont$$$(null, -1, 40, panelConfiguracion.getFont());
        if (panelConfiguracionFont != null) panelConfiguracion.setFont(panelConfiguracionFont);
        panel9.add(panelConfiguracion, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(40, 0, 40, 0), -1, -1));
        panelConfiguracion.add(panel10, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        Font label9Font = this.$$$getFont$$$(null, -1, 24, label9.getFont());
        if (label9Font != null) label9.setFont(label9Font);
        label9.setText("Cantidad de asociados");
        panel10.add(label9, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cantAsociados = new JTextField();
        cantAsociados.setText("");
        panel10.add(cantAsociados, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(40, 0, 40, 0), -1, -1));
        panelConfiguracion.add(panel11, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel11.add(panel12, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        aceptarConfiguracionButton = new JButton();
        aceptarConfiguracionButton.setActionCommand("ACEPTAR");
        Font aceptarConfiguracionButtonFont = this.$$$getFont$$$(null, -1, 24, aceptarConfiguracionButton.getFont());
        if (aceptarConfiguracionButtonFont != null) aceptarConfiguracionButton.setFont(aceptarConfiguracionButtonFont);
        aceptarConfiguracionButton.setText("Aceptar");
        panel12.add(aceptarConfiguracionButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel12.add(panel13, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        Font label10Font = this.$$$getFont$$$(null, -1, 24, label10.getFont());
        if (label10Font != null) label10.setFont(label10Font);
        label10.setText("Cantidad maxima de solicitudes");
        panel13.add(label10, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cantSolicitudes = new JTextField();
        cantSolicitudes.setText("");
        panel13.add(cantSolicitudes, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelConfiguracion.add(panel14, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        Font label11Font = this.$$$getFont$$$(null, -1, 72, label11.getFont());
        if (label11Font != null) label11.setFont(label11Font);
        label11.setText("Configuracion");
        panel14.add(label11, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelPrincipal;
    }

}