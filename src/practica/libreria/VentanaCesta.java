package practica.libreria;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VentanaCesta extends JFrame {

	private int contPP = 0;
	private double total = 0;

	private JPanel jp, jpScroll;

	private JScrollPane jsp;

	private JButton comprar;

	public VentanaCesta() {

		Cliente c = Cliente.c;

		setSize(640, 360);

//Añado el panel para los productos
		jp = new JPanel();
		jp.setBackground(new Color(215, 215, 250));
		add(jp);
		setContentPane(jp);

		jpScroll = new JPanel();
		jpScroll.setLayout(null);

		add(jpScroll);

		jsp = new JScrollPane(jpScroll);

		if (contPP < 10) {

			jsp.setBounds(20, 20, 350, contPP * 25);

		} else {

			jsp.setBounds(20, 20, 350, 250);

		}
		add(jsp);

		comprar = new JButton("Pagar");
		comprar.setFont(new Font("Arial", Font.BOLD, 16));
		add(comprar);

		comprar.setBounds(425, 240, 150, 50);

		pintarProductosCesta();

		if (contPP < 10) {

			jsp.setBounds(20, 20, 350, (contPP * 25) + 3);

		} else {

			jsp.setBounds(20, 20, 365, 250);

		}

		comprar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int nuevoCliente = JOptionPane.showConfirmDialog(null,
						"Esta a punto de hacer un pago de " + String.format("%.2f", total) + "€, ¿Desea continuar?",
						"Realizar pago", JOptionPane.YES_NO_OPTION);
				if (nuevoCliente == JOptionPane.YES_OPTION) {
					c.getCesta().clear();
					JOptionPane.showMessageDialog(null, "Gracias por su compra");
					dispose();
				}
			}
		});

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);

	}

	public void pintarProductosCesta() {

		for (Producto cesta : Cliente.c.getCesta()) {

			crearProductosCesta(cesta);
			total += cesta.getPrecioUnidad();

		}

		jpScroll.setPreferredSize(new Dimension(0, contPP * 25));
		JLabel jlTotal = new JLabel(String.format("Precio total: %.2f", total) + "€");
		add(jlTotal);
		jlTotal.setBounds(435, 200, 200, 30);
		jlTotal.setFont(new Font("Arial", Font.BOLD, 16));

	}

	public void crearProductosCesta(Producto p) {

		int y = 0 + (25 * contPP);

		JButton jbProducto = new JButton(p.getNombre()+" - "+p.getPrecioUnidad()+"€");
		JButton jbEliminar = new JButton("Eliminar");
		jpScroll.add(jbProducto);
		jpScroll.add(jbEliminar);

		jbProducto.setBounds(0, y, 250, 25);
		jbProducto.setFont(new Font("Arial", Font.BOLD, 15));
		jbEliminar.setBounds((250), y, 100, 25);
		jbEliminar.setFont(new Font("Arial", Font.BOLD, 15));

		contPP++;

		jbProducto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				VentanaProducto vp = new VentanaProducto(p);

			}

		});

		jbEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Cliente.c.getCesta().remove(p);
				p.setStock(p.getStock() + 1);
				jsp.remove(jbProducto);
				dispose();

				if (Cliente.c.getCesta().size() > 0) {

					VentanaCesta nVC = new VentanaCesta();

				}

			}

		});

	}

}