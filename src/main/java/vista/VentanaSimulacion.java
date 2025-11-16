package vista;

import controlador.Controlador;
import modelo.modeloAplicacion.NotificacionSimulacion;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Ventana para la simulación de ambulancias.
 * Muestra notificaciones en una lista y permite finalizar la simulación.
 * Implementa la interfaz IVistaSimulacion.
 */
public class VentanaSimulacion extends JFrame implements IVistaSimulacion {
    private JPanel panelPrincipal;
    private JTextPane logSimulacion;
    private JButton finalizarButton;
    private JLabel iconoAmbulanciaLabel;
    private Controlador controlador;

    /**
     * Constructor de la ventana de simulación.
     * <b>post:</b> se crea una ventana con lista de notificaciones, botón de finalizar y estilo aplicado.
     */
    public VentanaSimulacion() {
        inicializarComponentes();
        setContentPane(panelPrincipal);
        setTitle("Simulacion Ambulancia");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false);
        // Configurar el botón
        finalizarButton.setActionCommand(FINALIZAR_SIMULACION);
        aplicarEstilos();
    }

    /**
     * Inicializa los componentes de la ventana.
     * <b>post:</b> se crean y configuran los componentes gráficos de la ventana.
     */
    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Lista de notificaciones (izquierda)
        this.logSimulacion = new JTextPane();
        this.logSimulacion.setEditable(false);
        this.logSimulacion.setFont(new Font("Monospaced", Font.PLAIN, 12)); // O la fuente que quieras
        JScrollPane scrollPane = new JScrollPane(this.logSimulacion);
        scrollPane.setPreferredSize(new Dimension(400, 0));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Panel derecho con icono y botón
        JPanel panelDerecho = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);

        // Panel para el icono de ambulancia (arriba)
        JPanel panelIcono = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelIcono.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        iconoAmbulanciaLabel = new JLabel();

        // Cargar y configurar la imagen de la ambulancia
        cargarImagenAmbulancia();

        panelIcono.add(iconoAmbulanciaLabel);
        panelDerecho.add(panelIcono, BorderLayout.CENTER);

        // Panel para el botón (abajo)
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        finalizarButton = new JButton("Finalizar");
        panelBoton.add(finalizarButton);
        panelDerecho.add(panelBoton, BorderLayout.SOUTH);
    }

    /**
     * Carga la imagen de la ambulancia y la configura en el JLabel.
     * <b>post:</b> se carga y muestra la imagen de la ambulancia en el JLabel correspondiente.
     */
    private void cargarImagenAmbulancia() {
        try {
            ImageIcon icono = new ImageIcon("src/main/java/vista/Imagenes/Ambulancia.png");

            // Verificar si la imagen se cargó correctamente
            if (icono.getIconWidth() == -1) {
                throw new Exception("No se pudo encontrar la imagen en la ruta especificada");
            }

            // Escalar la imagen manteniendo la relación de aspecto
            Image imagen = icono.getImage();
            int nuevoAncho = 120;
            int nuevoAlto = 120;

            // Calcular dimensiones manteniendo proporción
            int anchoOriginal = icono.getIconWidth();
            int altoOriginal = icono.getIconHeight();

            // Escalar manteniendo relación de aspecto
            if (anchoOriginal > altoOriginal) {
                nuevoAlto = (nuevoAncho * altoOriginal) / anchoOriginal;
            } else {
                nuevoAncho = (nuevoAlto * anchoOriginal) / altoOriginal;
            }

            Image imagenEscalada = imagen.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
            iconoAmbulanciaLabel.setIcon(new ImageIcon(imagenEscalada));
            iconoAmbulanciaLabel.setText(""); // Eliminar el texto

            // Centrar la imagen
            iconoAmbulanciaLabel.setHorizontalAlignment(SwingConstants.CENTER);
            iconoAmbulanciaLabel.setVerticalAlignment(SwingConstants.CENTER);

        } catch (Exception e) {
            System.err.println("Error al cargar la imagen de la ambulancia: " + e.getMessage());
            System.err.println("Ruta intentada: Imagenes/Ambulancia.png");
            System.err.println("Directorio de trabajo: " + System.getProperty("user.dir"));

            // En caso de error, mostrar texto como fallback
            iconoAmbulanciaLabel.setText("🚑 Ambulancia");
            iconoAmbulanciaLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            iconoAmbulanciaLabel.setIcon(null); // Asegurarse de que no hay icono
        }
    }

    /**
     * Aplica estilos personalizados a los componentes de la ventana.
     * <b>post:</b> se aplican colores, fuentes y efectos visuales a los componentes.
     */
    private void aplicarEstilos() {
        // Paleta de colores
        Color fondoPrincipal = new Color(245, 247, 250);
        Color panelColor = Color.WHITE;
        Color botonColor = new Color(220, 53, 69); // Rojo para "Finalizar"
        Color botonHover = new Color(200, 35, 51);

        // Fondo principal
        panelPrincipal.setBackground(fondoPrincipal);

        // Estilo de la lista
        this.logSimulacion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        this.logSimulacion.setBackground(new Color(250, 250, 250));
        // this.logSimulacion.setSelectionBackground(new Color(70, 130, 180, 80));
        // this.logSimulacion.setSelectionForeground(Color.BLACK);

        // Estilo del label del icono (ahora con imagen)
        iconoAmbulanciaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        iconoAmbulanciaLabel.setForeground(new Color(50, 50, 50));
        iconoAmbulanciaLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        iconoAmbulanciaLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Estilo del botón Finalizar
        if (finalizarButton != null) {
            finalizarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
            finalizarButton.setBackground(botonColor);
            finalizarButton.setForeground(Color.WHITE);
            finalizarButton.setFocusPainted(false);
            finalizarButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            finalizarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Hover effect
            finalizarButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    finalizarButton.setBackground(botonHover);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    finalizarButton.setBackground(botonColor);
                }
            });
        }

        // Hacer que los paneles sean transparentes para mostrar el fondo
        Component[] components = panelPrincipal.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                ((JPanel) comp).setOpaque(false);
            }
        }

        // Panel derecho también transparente
        Container container = (Container) ((JPanel) panelPrincipal.getComponent(1)).getComponent(0);
        if (container instanceof JPanel) {
            ((JPanel) container).setOpaque(false);
        }
    }

    /**
     * Inicia la simulación mostrando la ventana.
     * <b>post:</b> se muestra la ventana de simulación y se limpia la lista de notificaciones.
     */
    @Override
    public void iniciarSimulacion() {
        // Limpiar la lista al iniciar nueva simulación
        //listModel.clear();
        setVisible(true);
        // Llevar la ventana al frente
        toFront();
        requestFocus();
    }

    /**
     * Configura el ActionListener para los botones de la ventana.
     * <b>post:</b> se asigna el controlador como listener para los eventos de los botones.
     * @param controlador El controlador que manejará los eventos.
     */
    @Override
    public void setActionListener(Controlador controlador) {
        assert controlador != null;
        this.controlador = controlador;
        this.finalizarButton.addActionListener(controlador);

        // También agregar listener para Enter en el botón
        finalizarButton.addActionListener(e -> {
            System.out.println("Botón Finalizar presionado - Comando: " + e.getActionCommand());
        });
    }

    /**
     * Actualiza el estado de la simulación mostrando una notificación en la lista.
     * <b>post:</b> se añade una notificación a la lista con el color correspondiente.
     * @param estado La notificación de la simulación a mostrar.
     */
    @Override
    public void actualizarEstadoSimulacion(NotificacionSimulacion estado) {
        assert  estado != null;
        // Agregar el mensaje a la lista
        // 1. Decide el color basado en la notificación
        Color colorParaEsteMensaje = Color.BLACK; // Color por defecto

        switch (estado.getTipo())
        {
            case "INFO":
                colorParaEsteMensaje = Color.BLUE;
                break;
            case "NO_AMBULANCIA":
                colorParaEsteMensaje = Color.ORANGE.darker();
                break;
            case "OK":
                colorParaEsteMensaje = new Color(0, 128, 0); // Verde
                break;
        }


        // 3. Llama al método de ayuda
        agregarTextoConColor(estado.getMensaje(), colorParaEsteMensaje);

    }

    /**
     * Agrega texto al JTextPane con un color específico.
     * <b>post:</b> se añade el texto al JTextPane con el color indicado.
     * @param texto El texto a agregar.
     * @param color El color del texto.
     */
    private void agregarTextoConColor(String texto, Color color)
    {
        StyledDocument doc = this.logSimulacion.getStyledDocument();
        SimpleAttributeSet estilo = new SimpleAttributeSet();
        StyleConstants.setForeground(estilo, color);

        try {
            doc.insertString(doc.getLength(), texto + "\n", estilo);
            // Auto-scroll
            this.logSimulacion.setCaretPosition(doc.getLength());

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finaliza la simulación deshabilitando el botón y mostrando un mensaje final.
     * <b>post:</b> se deshabilita el botón de finalizar y se actualiza su texto.
     */
    @Override
    public void FinalizarSimulacion() {
        // Agregar mensaje final
        //listModel.addElement("=== SIMULACIÓN FINALIZADA ===");

        // Deshabilitar el botón o cambiar su texto
        finalizarButton.setEnabled(false);
        finalizarButton.setText("Finalizado");
        finalizarButton.setBackground(Color.GRAY);
    }
}