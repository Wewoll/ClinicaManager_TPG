package vista;

import controlador.Controlador;
import modelo.modeloAplicacion.NotificacionSimulacion;

import javax.swing.*;
import java.awt.*;

public class VentanaSimulacion extends JFrame implements IVistaSimulacion {
    private JPanel panelPrincipal;
    private JList<String> list1;
    private JButton finalizarButton;
    private DefaultListModel<String> listModel;
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

        listModel = new DefaultListModel<>();
        list1.setModel(listModel);

        // Configurar el botón
        finalizarButton.setActionCommand(FINALIZAR_SIMULACION);

        aplicarEstilos();
    }

    private void inicializarComponentes() {
        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Lista de notificaciones (izquierda)
        list1 = new JList<>();
        JScrollPane scrollPane = new JScrollPane(list1);
        scrollPane.setPreferredSize(new Dimension(400, 0));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Panel derecho con icono y botón
        JPanel panelDerecho = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);

        // Panel para el icono de ambulancia (arriba)
        JPanel panelIcono = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelIcono.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        iconoAmbulanciaLabel = new JLabel("Icono de la Ambulancia");
        panelIcono.add(iconoAmbulanciaLabel);
        panelDerecho.add(panelIcono, BorderLayout.CENTER);

        // Panel para el botón (abajo)
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        finalizarButton = new JButton("Finalizar");
        panelBoton.add(finalizarButton);
        panelDerecho.add(panelBoton, BorderLayout.SOUTH);
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
        list1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        list1.setBackground(new Color(250, 250, 250));
        list1.setSelectionBackground(new Color(70, 130, 180, 80));
        list1.setSelectionForeground(Color.BLACK);

        // Estilo del label del icono
        iconoAmbulanciaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        iconoAmbulanciaLabel.setForeground(new Color(50, 50, 50));
        iconoAmbulanciaLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

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
        listModel.clear();
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
        listModel.addElement(estado.getMensaje());

        // Auto-scroll al final de la lista
        int lastIndex = listModel.getSize() - 1;
        if (lastIndex >= 0) {
            list1.ensureIndexIsVisible(lastIndex);
        }

        // Forzar actualización de la interfaz
        list1.repaint();

        // Debug opcional
        System.out.println("Simulación: " + estado.getMensaje());
    }

    @Override
    public void FinalizarSimulacion() {
        // Agregar mensaje final
        listModel.addElement("=== SIMULACIÓN FINALIZADA ===");

        // Deshabilitar el botón o cambiar su texto
        finalizarButton.setEnabled(false);
        finalizarButton.setText("Finalizado");
        finalizarButton.setBackground(Color.GRAY);

        // Opcional: cerrar la ventana después de un tiempo
        /*
        Timer timer = new Timer(3000, e -> {
            setVisible(false);
            dispose();
        });
        timer.setRepeats(false);
        timer.start();
        */
    }

    // Método para limpiar la lista
    public void limpiarLista() {
        listModel.clear();
    }

    // Método para obtener el modelo (útil para testing)
    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    // Método para debug
    public void mostrarEstado() {
        System.out.println("VentanaSimulacion estado:");
        System.out.println("Visible: " + isVisible());
        System.out.println("Elementos en lista: " + listModel.getSize());
        System.out.println("Botón Finalizar - Habilitado: " + finalizarButton.isEnabled() +
                ", Comando: " + finalizarButton.getActionCommand());
        System.out.println("Controlador: " + (controlador != null ? "Presente" : "Null"));
    }
}