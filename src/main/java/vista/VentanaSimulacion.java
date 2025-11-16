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

public class VentanaSimulacion extends JFrame implements IVistaSimulacion {
    private JPanel panelPrincipal;
    //private JList<String> list1;
    private JTextPane logSimulacion;
    private JButton finalizarButton;
    // private DefaultListModel<String> listModel;
    private JLabel iconoAmbulanciaLabel;
    private Controlador controlador;

    public VentanaSimulacion() {
        inicializarComponentes();
        setContentPane(panelPrincipal);
        setTitle("Simulacion Ambulancia");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false);

//        listModel = new DefaultListModel<>();
//        list1.setModel(listModel);

        // Configurar el botón
        finalizarButton.setActionCommand(FINALIZAR_SIMULACION);

        aplicarEstilos();
    }

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

    private void cargarImagenAmbulancia() {
        try {
            // Opción 1: Cargar imagen desde archivo (ruta relativa)
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

    private ImageIcon crearIconoAmbulanciaPlaceholder() {
        // Crear una imagen simple como placeholder
        int width = 120;
        int height = 120;
        BufferedImage imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagen.createGraphics();

        // Configurar calidad de renderizado
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo blanco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Cuerpo de la ambulancia (rojo)
        g2d.setColor(Color.RED);
        g2d.fillRoundRect(20, 40, 80, 40, 10, 10);

        // Cabina (azul)
        g2d.setColor(Color.BLUE);
        g2d.fillRect(20, 30, 30, 30);

        // Ventanas (azul claro)
        g2d.setColor(new Color(173, 216, 230));
        g2d.fillRect(25, 35, 20, 15);

        // Luces (amarillo)
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(15, 45, 10, 10); // Luz delantera
        g2d.fillOval(95, 45, 10, 10); // Luz trasera

        // Cruz (blanca)
        g2d.setColor(Color.WHITE);
        g2d.fillRect(55, 45, 10, 30); // Línea vertical
        g2d.fillRect(45, 55, 30, 10); // Línea horizontal

        // Ruedas (negras)
        g2d.setColor(Color.BLACK);
        g2d.fillOval(25, 75, 15, 15);
        g2d.fillOval(70, 75, 15, 15);

        g2d.dispose();

        return new ImageIcon(imagen);
    }

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

    @Override
    public void iniciarSimulacion() {
        // Limpiar la lista al iniciar nueva simulación
        //listModel.clear();
        setVisible(true);
        // Llevar la ventana al frente
        toFront();
        requestFocus();
    }

    @Override
    public void setActionListener(Controlador controlador) {
        this.controlador = controlador;
        this.finalizarButton.addActionListener(controlador);

        // También agregar listener para Enter en el botón
        finalizarButton.addActionListener(e -> {
            System.out.println("Botón Finalizar presionado - Comando: " + e.getActionCommand());
        });
    }

    @Override
    public void actualizarEstadoSimulacion(NotificacionSimulacion estado) {
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
     * Método de ayuda para añadir texto coloreado al final del JTextPane.
     * (Pon esto en tu clase de la ventana)
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


    @Override
    public void FinalizarSimulacion() {
        // Agregar mensaje final
        //listModel.addElement("=== SIMULACIÓN FINALIZADA ===");

        // Deshabilitar el botón o cambiar su texto
        finalizarButton.setEnabled(false);
        finalizarButton.setText("Finalizado");
        finalizarButton.setBackground(Color.GRAY);
    }

    // Método para limpiar la lista
//    public void limpiarLista() {
//        listModel.clear();
//    }
//
//    // Método para obtener el modelo (útil para testing)
//    public DefaultListModel<String> getListModel() {
//        return listModel;
//    }

    // Método para debug
    public void mostrarEstado() {
        System.out.println("VentanaSimulacion estado:");
        System.out.println("Visible: " + isVisible());
        //System.out.println("Elementos en lista: " + listModel.getSize());
        System.out.println("Botón Finalizar - Habilitado: " + finalizarButton.isEnabled() +
                ", Comando: " + finalizarButton.getActionCommand());
        System.out.println("Controlador: " + (controlador != null ? "Presente" : "Null"));
    }
}