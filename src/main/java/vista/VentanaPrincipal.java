package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controlador.Controlador;
import modelo.modeloDominio.personas.asociado.Asociado;
import persistencia.AsociadoDTO;

public class VentanaPrincipal extends JFrame implements IVistaPrincipal {
    private JTabbedPane pestañas;
    private JPanel altaAsociado;
    private JTextField Nombre_Alta;
    private JTextField Apellido_Alta;
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
    private JList<String> listaAsociados;
    private JButton aceptarConfiguracionButton;
    private JTextField cantAsociados;
    private JTextField cantSolicitudes;
    private JPanel panelConfiguracion;

    private DefaultListModel<String> listaModel;

    public VentanaPrincipal() {
        inicializarComponentes();
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

    private void inicializarComponentes() {
        // Panel principal
        panelPrincipal = new JPanel(new BorderLayout());

        // Pestañas
        pestañas = new JTabbedPane();
        panelPrincipal.add(pestañas, BorderLayout.CENTER);

        // Crear las pestañas
        crearPestanaGestionAsociados();
        crearPestanaSimulacion();
    }

    private void crearPestanaGestionAsociados() {
        JPanel panelGestion = new JPanel(new GridLayout(2, 2, 10, 10));
        panelGestion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de Alta Asociado
        altaAsociado = crearPanelAltaAsociado();

        // Panel de Baja Asociado
        bajaAsociado = crearPanelBajaAsociado();

        // Panel de Lista Asociados
        listaAsociado = crearPanelListaAsociados();

        // Organizar en el layout
        panelGestion.add(altaAsociado);
        panelGestion.add(listaAsociado);
        panelGestion.add(bajaAsociado);
        panelGestion.add(new JPanel()); // Espacio vacío para completar la cuadrícula

        pestañas.addTab("Gestion de Asociados", panelGestion);
    }

    private JPanel crearPanelAltaAsociado() {
        JPanel panel = new JPanel(new GridLayout(8, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black),
                "Alta Asociado",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION
        ));

        // Campos del formulario
        panel.add(crearCampoConEtiqueta("Nombre", Nombre_Alta = new JTextField()));
        panel.add(crearCampoConEtiqueta("Apellido", Apellido_Alta = new JTextField()));
        panel.add(crearCampoConEtiqueta("DNI", DNI_Alta = new JTextField()));
        panel.add(crearCampoConEtiqueta("Ciudad", Ciudad_Alta = new JTextField()));
        panel.add(crearCampoConEtiqueta("Calle", Calle_Alta = new JTextField()));
        panel.add(crearCampoConEtiqueta("Numero", Numero_Alta = new JTextField()));
        panel.add(crearCampoConEtiqueta("Telefono", Telefono_Alta = new JTextField()));

        // Botón Dar de Alta
        darDeAltaButton = new JButton("Dar de alta");
        darDeAltaButton.setActionCommand("DAR_ALTA");
        panel.add(darDeAltaButton);

        return panel;
    }

    private JPanel crearPanelBajaAsociado() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black),
                "Baja Asociado",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION
        ));

        JPanel contenido = new JPanel(new BorderLayout(5, 10));
        contenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiqueta
        JLabel etiqueta = new JLabel("DNI");
        contenido.add(etiqueta, BorderLayout.NORTH);

        // Campo DNI
        DNI_Baja = new JTextField();
        contenido.add(DNI_Baja, BorderLayout.CENTER);

        // Botón Dar de Baja
        darDeBajaButton = new JButton("Dar de baja");
        darDeBajaButton.setActionCommand("DAR_BAJA");
        contenido.add(darDeBajaButton, BorderLayout.SOUTH);

        panel.add(contenido, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelListaAsociados() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black),
                "Lista Asociados",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION
        ));

        listaAsociados = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listaAsociados);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void crearPestanaSimulacion() {
        JPanel panelSimulacion = new JPanel(new BorderLayout());
        panelSimulacion.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(new BoxLayout(panelConfiguracion, BoxLayout.Y_AXIS));

        // Título
        JLabel titulo = new JLabel("Configuracion");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelConfiguracion.add(titulo);

        panelConfiguracion.add(Box.createVerticalStrut(30));

        // Campo Cantidad de Asociados
        JPanel panelAsociados = new JPanel(new BorderLayout(10, 5));
        JLabel labelAsociados = new JLabel("Cantidad de asociados");
        labelAsociados.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cantAsociados = new JTextField();
        panelAsociados.add(labelAsociados, BorderLayout.NORTH);
        panelAsociados.add(cantAsociados, BorderLayout.CENTER);
        panelConfiguracion.add(panelAsociados);

        panelConfiguracion.add(Box.createVerticalStrut(20));

        // Campo Cantidad de Solicitudes
        JPanel panelSolicitudes = new JPanel(new BorderLayout(10, 5));
        JLabel labelSolicitudes = new JLabel("Cantidad maxima de solicitudes");
        labelSolicitudes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cantSolicitudes = new JTextField();
        panelSolicitudes.add(labelSolicitudes, BorderLayout.NORTH);
        panelSolicitudes.add(cantSolicitudes, BorderLayout.CENTER);
        panelConfiguracion.add(panelSolicitudes);

        panelConfiguracion.add(Box.createVerticalStrut(30));

        // Botón Aceptar
        aceptarConfiguracionButton = new JButton("Aceptar");
        aceptarConfiguracionButton.setActionCommand("ACEPTAR");
        aceptarConfiguracionButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        aceptarConfiguracionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelConfiguracion.add(aceptarConfiguracionButton);

        panelSimulacion.add(panelConfiguracion, BorderLayout.CENTER);
        pestañas.addTab("Simulacion", panelSimulacion);
    }

    private JPanel crearCampoConEtiqueta(String textoEtiqueta, JTextField campoTexto) {
        JPanel panel = new JPanel(new BorderLayout(5, 2));
        JLabel etiqueta = new JLabel(textoEtiqueta);
        panel.add(etiqueta, BorderLayout.NORTH);
        panel.add(campoTexto, BorderLayout.CENTER);
        return panel;
    }

    // Método para aplicar estilo a los componentes
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
                if (p.getBorder() instanceof TitledBorder) {
                    p.setBorder(BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(bordePanel),
                            ((TitledBorder) p.getBorder()).getTitle(),
                            TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION,
                            new Font("Segoe UI", Font.BOLD, 12),
                            labelColor
                    ));
                } else {
                    p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
                }
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

        // Hacer que los textFields se vean más modernos (si existen)
        JTextField[] fields = {Nombre_Alta, Apellido_Alta, DNI_Alta, Ciudad_Alta, Calle_Alta, Numero_Alta, Telefono_Alta, DNI_Baja, cantAsociados, cantSolicitudes};
        for (JTextField tf : fields) {
            if (tf == null) continue;
            tf.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, bordePanel));
            tf.setBackground(new Color(250, 250, 250));
            tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            tf.setPreferredSize(new Dimension(150, 26));
        }
    }

    // Los métodos de la interfaz IVistaPrincipal se mantienen igual
    @Override
    public void setActionListener(Controlador controlador) {
        this.darDeAltaButton.addActionListener(controlador);
        this.darDeBajaButton.addActionListener(controlador);
        this.aceptarConfiguracionButton.addActionListener(controlador);
    }

        @Override
        public AsociadoDTO getNuevoAsociado() throws datosAsociadoDTOIncorrectoException {
            String nombre = Nombre_Alta.getText();
            String apellido = Apellido_Alta.getText();
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
        Apellido_Alta.setText("");
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
            aux =  a.getNombre() + a.getApellido() + ", DNI: " + a.getDni();
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

    // Clases de excepción (debes tenerlas definidas en tu proyecto)
    public static class datosAsociadoDTOIncorrectoException extends RuntimeException {
        public datosAsociadoDTOIncorrectoException(String message) {
            super(message);
        }
    }

    public static class datosSimulacionIncorrectosException extends RuntimeException {
        public datosSimulacionIncorrectosException(String message) {
            super(message);
        }
    }
}