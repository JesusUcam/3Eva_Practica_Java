package practica.libreria;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VentanaProducto extends JFrame implements ActionListener{

	private JPanel panelVP;
	private JLabel imagenP;
	
	public VentanaProducto(Producto p) {
		
		setName(p.getNombre());
		
		panelVP = new JPanel();		
		panelVP.setBackground(Principal.colorFondo);
		add(panelVP);
		setContentPane(panelVP);
		setLayout(null);
		setSize(640, 360);
		
		//Componentes
		JTextArea pDescripcion = new JTextArea(p.getDescripcion());
		JButton pComprar = new JButton("Comprar 1 por "+p.getPrecioUnidad());
		JComboBox<Integer> pUnidades = new JComboBox<Integer>();
		JLabel pImagen = new JLabel();
		
		
		add(pDescripcion);
		add(pComprar);
		add(pUnidades);
		add(pImagen);
		
		//JComboBox
		for (Integer i = 0; i<p.getStock(); i++) {
			pUnidades.addItem(i+1);
		}
		
		pUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				pComprar.setText("Comprar "+pUnidades.getSelectedItem()+" por "+((Double)p.getPrecioUnidad()*(Integer)pUnidades.getSelectedItem())+"€");
				
			}
		});
		
		//JButton
		pComprar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Integer unidadesSeleccionadas = (int) pUnidades.getSelectedItem();
				
				if (Cliente.c!=null) {
					
					for (int i = 0; i < unidadesSeleccionadas; i++) {
	
						Cliente.c.getCesta().add(p);
						p.setStock(p.getStock()-1);
						
					}
					
					dispose();
					JOptionPane.showMessageDialog(null, "Gracias por su compra. Su pedido se encuentra ahora en su cesta");
					
				} else {
					
					JOptionPane.showMessageDialog(null, "ERROR. Necesitas iniciar sesión antes de comprar productos", "No se ha iniciado sesion", JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});
		//JTextArea config
		pDescripcion.setBackground(Principal.colorFondo);
		pDescripcion.setLineWrap(true);
		pDescripcion.setWrapStyleWord(true);
		pDescripcion.setEditable(false);
		
		//Imagen
		ImageIcon iconoProducto = Producto.aplicarImagen(p.getRutaImagen(), 300, 175);
		pImagen.setIcon(iconoProducto);
		
		//Coordenadas (640x360. 300x para la foto)
		pUnidades.setBounds(200,180,70,25);
		pComprar.setBounds(270,180,200,25);
		pDescripcion.setBounds(20,210,600,100);
		pImagen.setBounds(170,0,300,175);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent arg0) {

		
		
	}

}
