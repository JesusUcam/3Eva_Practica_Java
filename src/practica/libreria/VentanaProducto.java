package practica.libreria;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaProducto extends JFrame implements ActionListener{

	private JPanel panelVP;
	
	public VentanaProducto(Producto p) {
		
		setName(p.getNombre());
		
		panelVP = new JPanel();		
		panelVP.setBackground(new Color(210, 210, 255));
		add(panelVP);
		setContentPane(panelVP);
		setLayout(null);
		setSize(640, 360);
		
		//Componentes
		JLabel pDescripcion = new JLabel(p.getDescripcion());
		JLabel pImagen = new JLabel();
		JButton pComprar = new JButton("Comprar");
		JComboBox<Integer> pUnidades = new JComboBox<Integer>();
		
		add(pDescripcion);
		add(pComprar);
		add(pUnidades);
		
		//JComboBox
		for (Integer i = 0; i<p.getStock(); i++) {
			pUnidades.addItem(i+1);
		}
		
		pUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				pComprar.setText("Comprar "+pUnidades.getSelectedItem());
				
			}
		});
		
		//JButton
		pComprar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Integer unidadesSeleccionadas = (int) pUnidades.getSelectedItem();
				
				for (int i = 0; i < unidadesSeleccionadas; i++) {

					Cliente.c.getCesta().add(p);
					p.setStock(p.getStock()-1);
					
				}
				
				dispose();
				JOptionPane.showMessageDialog(null, "Gracias por su compra. Su pedido se encuentra ahora en su cesta");
			
			}
		});
		
		//Coordenadas (640x360. 300x para la foto)
		pUnidades.setBounds(270,180,100,25);
		pComprar.setBounds(375,175,125,35);
		pDescripcion.setBounds(30,210,300,25);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent arg0) {

		
		
	}

}
