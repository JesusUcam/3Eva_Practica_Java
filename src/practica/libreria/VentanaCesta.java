package practica.libreria;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaCesta extends JFrame implements ActionListener{

	private int contPP=0;
	private double total = 0;
	
	private JPanel jp;
	private JButton comprar;
	
	public VentanaCesta() {
		Cliente c = Cliente.c;
		jp = new JPanel();
		jp.setBackground(new Color(215, 215, 250));
		
		add(jp);
		setSize(640, 360);
		setContentPane(jp);
		setLayout(null);
		
		comprar = new JButton("Comprar todo");
		add(comprar);
		
		comprar.setBounds(450,50,150,50);
		
		pintarProductosCesta(c);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public void pintarProductosCesta(Cliente c) {
		
		for (Producto cesta : c.getCesta()) {
		
			JButton jbProducto = crearProductosCesta(cesta);
			total += cesta.getPrecioUnidad();
			
		}
		
		if (total==0) {
			
			//JLabel
			JOptionPane.showMessageDialog(null, "Todavía no cuenta con ningún producto en su cesta. Puede agregar productos en la cesta");
			
		} else {
			
			JLabel jlTotal = new JLabel(String.format("%.2f", total)+"€");
			add(jlTotal);
			jlTotal.setBounds(300,200,120,30);
			
		}
		

	}
	
	public JButton crearProductosCesta(Producto p) {
		
		/*
		 * 640x360
		 */
	
		int x = 20;
		int y = 20 + (25*contPP); 
				
		JButton jbProducto = new JButton(p.getNombre());
		JButton jbEliminar = new JButton("Eliminar");
		add(jbProducto);
		add(jbEliminar);
		
		jbProducto.setBounds(x,y,250,25);
		jbEliminar.setBounds((x+250),y,100,25);
		
		contPP++;
		
		jbProducto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				JFrame ventanaDeProducto = new JFrame(p.getNombre());
				JPanel panelVP = new JPanel();
				
				panelVP.setBackground(new Color(210, 210, 255));
				panelVP.setLayout(null);
				
				//Ventana
				ventanaDeProducto.setSize(640, 360);
				ventanaDeProducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        ventanaDeProducto.setLocationRelativeTo(null);
				ventanaDeProducto.setLayout(null);
				//Componentes
				JLabel pDescripcion = new JLabel(p.getDescripcion());
				JLabel pImagen = new JLabel();
				JButton pComprar = new JButton("Comprar");
				JComboBox<Integer> pUnidades = new JComboBox<Integer>();
				
				panelVP.add(pDescripcion);
				panelVP.add(pComprar);
				panelVP.add(pUnidades);
				
				//JComboBox
				for (Integer i = 0; i<p.getStock(); i++) {
					pUnidades.addItem(i);
				}
				//JButton
				pComprar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						
						
					}
					
				});
				
				//Coordenadas (640x360. 300x para la foto)
				pUnidades.setBounds(270,180,100,25);
				pComprar.setBounds(370,180,25,25);
				pDescripcion.setBounds(30,210,300,25);
		        
				ventanaDeProducto.setContentPane(panelVP);
				ventanaDeProducto.setVisible(true);
			}
		});
		
		jbEliminar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Cliente.c.getCesta().remove(p);
		
				jp.remove(jbProducto);
				
				dispose();
				
				if (Cliente.c.getCesta().size()>0) {
					
					VentanaCesta nVC = new VentanaCesta();
					
				}
				
			}
		
		});
		
		return jbProducto; //No necesario
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		
		
	}

}
