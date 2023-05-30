package practica.libreria;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaInicioSesion extends JFrame{
	
	JPanel jpInicioSesion = new JPanel();
	
	public VentanaInicioSesion() {
		
		jpInicioSesion.setBackground(new Color(210, 210, 255));
		jpInicioSesion.setLayout(null);
		
		//Ventana
		setSize(200, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		JLabel jlNombre = new JLabel("Introduzca su nombre:");
		JLabel jlTelefono = new JLabel("Introduzca su telefono:");
		JTextField jtfNombre = new JTextField();
		JTextField jtfTelefono = new JTextField();
		JButton jbVerificar = new JButton("Verificar");
		
		jpInicioSesion.add(jlNombre);
		jpInicioSesion.add(jlTelefono);
		jpInicioSesion.add(jtfNombre);
		jpInicioSesion.add(jtfTelefono);
		jpInicioSesion.add(jbVerificar);
		
		//Coordenadas
		jlNombre.setBounds(25,10,130,25);
		jtfNombre.setBounds(25,35,130,25);
		jlTelefono.setBounds(25,60,130,25);
		jtfTelefono.setBounds(25,85,130,25);
		jbVerificar.setBounds(25,120,100,30);
		
		setContentPane(jpInicioSesion);
		setVisible(true);
		
		jbVerificar.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				
				
				
				if (jtfNombre.getText().isEmpty()||jtfTelefono.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "ERROR. No puedes dejar campos en blanco.\nRellena todos los campos y dale a enviar", null,	JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					String nombre = jtfNombre.getText();
					String telefono = jtfTelefono.getText();
					
					Cliente.c=null;
					
					for (Cliente clienteIS : Cliente.getListaCliente()) {
			
						if(clienteIS.getNombre().equalsIgnoreCase(nombre) && clienteIS.getTelefono().equals(telefono)) {
							
							Cliente.c = clienteIS;
								
						}
					
					}
					
					if (Cliente.c == null) {
						
						int nuevoCliente = JOptionPane.showConfirmDialog(null, "Los datos introducidos no corresponden con ninguno de nuestros clientes \n¿Desea registrarse?", "Crear usuario", JOptionPane.YES_NO_OPTION);
						if (nuevoCliente == JOptionPane.YES_OPTION) {
							
							Cliente.c = new Cliente(nombre, telefono);
							Cliente.getListaCliente().add(Cliente.c);
					
							System.out.println(Cliente.c.getNombre());
							
						    JOptionPane.showMessageDialog(null, "¡Cliente registrado! disfrute sus compras");
						    
						    //Ventana.cambioIniciarCerrarSesion();
						    
						    dispose();
						    Ventana.activarCerrarSesion();
						    
						} //else { JOptionPane.showMessageDialog(null, "Operacion cancelada");}
						
					} else {
						
						JOptionPane.showMessageDialog(null, "Bienvenid@ "+ Cliente.c.getNombre()+", esperamos que encuentre lo que busca");
						dispose();
						Ventana.activarCerrarSesion();
						
					}
				}
				
			}
		});
		
	}

}

