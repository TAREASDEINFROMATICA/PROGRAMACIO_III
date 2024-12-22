/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;
import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 *
 * @author LENOVO
 */
public class nivel1 extends javax.swing.JFrame {
    /**
     * Creates new form nivel1
     */
    //initComponents();
    // Declaración del tablero gráfico (botones) y su representación lógica (matriz)
    private JButton[][] tablero; // Botones que se mostrarán en la ventana
    private int[][] matrizTablero = { // Representación lógica del tablero
            {0, 0, 1, 0, 0, 0, 1, 3}, // Gato en (0,7)
            {1, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {2, 0, 0, 0, 0, 0, 0, 4} // Ratón en (7,0), salida en (7,7)
    };
    // Posiciones iniciales del ratón y el gato
    private int ratonX = 7, ratonY = 0; // Coordenadas del ratón
    private int gatoX = 0, gatoY = 7;   // Coordenadas del gato
    // Variable para alternar turnos
    private boolean turnoRaton = true; // `true` significa turno del ratón, `false` turno del gato
    // Método principal (punto de inicio)
    // Constructor: inicializa la ventana y comienza el juego
    public nivel1() {
        setTitle("Gato vs. Ratón PvP"); 				// Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar al presionar "X"
        setSize(600, 600); 								// Tamaño de la ventana
        setLayout(new BorderLayout()); 					// Layout para organizar componentes

        crearTablero(); 								// Crear el tablero de botones
        actualizarTablero(); 							// Mostrar las posiciones iniciales
        setVisible(true); 								// Hacer visible la ventana

        iniciarJuego(); 								// Comenzar la lógica del juego
    }
    // Método para crear los botones del tablero
    private void crearTablero() {
        tablero = new JButton[8][8]; // Crear una matriz de botones de 8x8
        JPanel panelTablero = new JPanel(new GridLayout(8, 8)); // Usar un diseño de cuadrícula

        // Crear cada botón y agregarlo al panel
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = new JButton();
                tablero[i][j].setEnabled(false); // Deshabilitar para evitar clics accidentales
                tablero[i][j].setBackground(Color.WHITE); // Fondo blanco por defecto
                panelTablero.add(tablero[i][j]);
            }
        }
        // Agregar el panel con los botones al centro de la ventana
        add(panelTablero, BorderLayout.CENTER);
    }
    // Método para actualizar las posiciones en el tablero
  // Método para actualizar las posiciones en el tablero

private ImageIcon iconoRaton;
private ImageIcon iconoGato;

// Método para inicializar y redimensionar las imágenes manteniendo el color original
private void inicializarImagenes() {
    // Cargar las imágenes desde los recursos
    ImageIcon ratonOriginal = new ImageIcon(getClass().getResource("/imagenes/raton.jpg"));
    ImageIcon gatoOriginal = new ImageIcon(getClass().getResource("/imagenes/gato.jpg"));
    
    // Tamaño del botón (ajusta este valor según el tamaño de tu tablero)
    int buttonSize = 50;
    
    // Redimensionar las imágenes al tamaño del botón, conservando el color original
    Image ratonEscalado = ratonOriginal.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
    Image gatoEscalado = gatoOriginal.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
    
    // Asignar las imágenes escaladas a los iconos
    iconoRaton = new ImageIcon(ratonEscalado);
    iconoGato = new ImageIcon(gatoEscalado);
}

    private void actualizarTablero() {
    inicializarImagenes(); // Inicializar las imágenes con el tamaño adecuado
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            JButton boton = tablero[i][j];
            boton.setText(""); // Limpiar el texto
            boton.setIcon(null); // Limpiar cualquier icono anterior

            // Configurar imágenes y colores según la posición
            if (i == ratonX && j == ratonY) { // Ratón
                boton.setIcon(iconoRaton); // Colocar imagen del ratón
                boton.setBackground(Color.WHITE); // Fondo blanco para el ratón
            } else if (i == gatoX && j == gatoY) { // Gato
                boton.setIcon(iconoGato); // Colocar imagen del gato
                boton.setBackground(Color.WHITE); // Fondo blanco para el gato
            } else if (matrizTablero[i][j] == 1) { // Obstáculos
                boton.setBackground(Color.BLACK);
            } else if (matrizTablero[i][j] == 4) { // Salida
                boton.setBackground(Color.YELLOW);
            } else { // Espacios vacíos
                boton.setBackground(Color.WHITE);
            }

            // Asegurarse de que el color de fondo cubra completamente el botón sin bordes
            boton.setOpaque(true);
            boton.setBorderPainted(false);
            boton.setFocusPainted(false);
            boton.setContentAreaFilled(true);
        }
    }
}



    // Método que inicia el juego
    private void iniciarJuego() {
        alternarTurno(); // Alternar entre los turnos del ratón y el gato
    }
    // Alternar turnos entre el ratón y el gato
    private void alternarTurno() {
        if (turnoRaton) { // Turno del ratón
            if (moverRaton()) return; // Si el ratón gana, termina el juego
        } else { // Turno del gato
            if (moverGato()) return; // Si el gato gana, termina el juego
        }
        turnoRaton = !turnoRaton; // Cambiar de turno
        alternarTurno(); // Llamada recursiva para el siguiente turno
    }
    // Lógica para mover el ratón
    private boolean moverRaton() {
        while (true) { // Ciclo para solicitar movimientos válidos
            String movimiento = JOptionPane.showInputDialog(this, "Turno del Ratón (WASD):", "Movimiento Ratón", JOptionPane.QUESTION_MESSAGE);
            if (movimiento == null || movimiento.isEmpty()) continue; // Ignorar entradas vacías

            int nuevoX = ratonX, nuevoY = ratonY; // Posiciones temporales
            switch (movimiento.toUpperCase().charAt(0)) { // Detectar dirección
                case 'W': nuevoX--; break; // Arriba
                case 'S': nuevoX++; break; // Abajo
                case 'A': nuevoY--; break; // Izquierda
                case 'D': nuevoY++; break; // Derecha
                default:
                    JOptionPane.showMessageDialog(this, "Movimiento no válido. Intenta de nuevo.");
                    continue;
            }
            // Validar si la posición es válida
            if (esMovimientoValido(nuevoX, nuevoY)) {
                ratonX = nuevoX;
                ratonY = nuevoY;
                actualizarTablero();

                // Verificar si llegó a la salida
                if (matrizTablero[ratonX][ratonY] == 4) {
                    JOptionPane.showMessageDialog(this, "¡El ratón ha ganado!");
                    System.exit(0); // Cerrar el programa
                    return true;
                }
                break;
            } else {
                JOptionPane.showMessageDialog(this, "Movimiento inválido. Intenta de nuevo.");
            }
        }
        return false;
    }

    // Lógica para mover el gato (similar al ratón)
    private boolean moverGato() {
        while (true) {
            String movimiento = JOptionPane.showInputDialog(this, "Turno del Gato (WASD):", "Movimiento Gato", JOptionPane.QUESTION_MESSAGE);
            if (movimiento == null || movimiento.isEmpty()) continue;

            int nuevoX = gatoX, nuevoY = gatoY;
            switch (movimiento.toUpperCase().charAt(0)) {
                case 'W': nuevoX--; break;
                case 'S': nuevoX++; break;
                case 'A': nuevoY--; break;
                case 'D': nuevoY++; break;
                default:
                    JOptionPane.showMessageDialog(this, "Movimiento no válido. Intenta de nuevo.");
                    continue;
            }

            if (esMovimientoValido(nuevoX, nuevoY)) {
                gatoX = nuevoX;
                gatoY = nuevoY;
                actualizarTablero();

                // Verificar si atrapó al ratón
                if (gatoX == ratonX && gatoY == ratonY) {
                    JOptionPane.showMessageDialog(this, "¡El gato ha atrapado al ratón!");
                    System.exit(0);
                    return true;
                }
                break;
            } else {
                JOptionPane.showMessageDialog(this, "Movimiento inválido. Intenta de nuevo.");
            }
        }
        return false;
    }
    // Método para validar movimientos
    private boolean esMovimientoValido(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8 && matrizTablero[x][y] != 1; // Dentro de los límites y no es un obstáculo
    }
    nivel1(principal aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Atras");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(526, 526, 526)
                .addComponent(jButton1)
                .addContainerGap(549, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(726, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      principal p=new principal();
            p.setVisible(true);
            this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(nivel1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nivel1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nivel1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nivel1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         SwingUtilities.invokeLater(() -> new nivel1()); // Crear y ejecutar la interfaz gráfica
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nivel1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
