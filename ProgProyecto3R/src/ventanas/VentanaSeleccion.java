package ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaSeleccion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentpane = new JPanel();
	private JLabel labelfondo = new JLabel();
	private JButton btnCombate = new JButton();
	private JButton btnPkdex = new JButton();
	private JButton btnMovimientos = new JButton();
	private JButton btnOpciones = new JButton();
	private JButton btnSalir = new JButton();

	public VentanaSeleccion(int altura, int anchura) {
		contentpane = new JPanel();

		contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);

		btnCombate.setText("COMBATE");
		btnCombate.setBackground(new Color(255, 175, 175));
		btnCombate.setBounds(150, 100, 100, 20);
		contentpane.add(btnCombate);

		btnPkdex.setText("DEUSTMON");
		btnPkdex.setBackground(new Color(255, 175, 175));
		btnPkdex.setBounds(300, 100, 100, 20);
		contentpane.add(btnPkdex);

		btnMovimientos.setText("MOVIMIENTOS");
		btnMovimientos.setBackground(new Color(255, 175, 175));
		btnMovimientos.setBounds(150, 200, 100, 20);
		contentpane.add(btnMovimientos);

		btnOpciones.setText("OPCIONES");
		btnOpciones.setBackground(new Color(255, 175, 175));
		btnOpciones.setBounds(300, 200, 100, 20);
		contentpane.add(btnOpciones);

		btnSalir.setText("SALIR");
		btnSalir.setBackground(new Color(255, 175, 175));
		btnSalir.setBounds(400, 300, 100, 20);
		contentpane.add(btnSalir);

		labelfondo.setIcon(new ImageIcon(VentanaInicioJuego.class.getResource("/images/back2.jpg")));
		labelfondo.setBounds(0, 0, altura, anchura);
		contentpane.add(labelfondo);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(altura, anchura);
		setTitle("DeustMon");

		// Eventos
		btnCombate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaNiveles ventana3 = new VentanaNiveles(750, 422);
				ventana3.setVisible(true);
				VentanaSeleccion.this.dispose();

			}
		});

		btnPkdex.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPkdex ventana4 = new VentanaPkdex(750, 422);
				ventana4.setVisible(true);

			}
		});

		btnMovimientos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMovimientos ventana5 = new VentanaMovimientos(750, 422);
				ventana5.setVisible(true);

			}
		});

		btnOpciones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaOpciones ventana6 = new VentanaOpciones(750, 422);
				ventana6.setVisible(true);

			}
		});

		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				;
				VentanaSeleccion.this.dispose();
				System.exit(0);

			}
		});

	}
}
