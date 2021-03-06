package is.hotelzargo.presentacion.client.gui;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.client.transfer.ClientTransferIndividual;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;
import is.hotelzargo.presentacion.maingui.RenderList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class ClientFormList extends JDialog {
	
	private JList list;
	
	private JScrollPane scrollPane;
	
	private JPanel renderPanel;
	
	private JButton exitButton;
	
	private RenderList renderList;
	
	public ClientFormList(JFrame owner, boolean mod){
		super(owner,mod);
		this.setTitle("Lista de clientes");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		renderList = new RenderList();
		
		renderPanel = new JPanel();
		
		scrollPane = new JScrollPane(renderPanel,
									ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
									ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		exitButton = new JButton("Salir");
		
		list = new JList();
		
		addListener();
		
		this.setLayout(new GridLayout(1, 1));
		this.add(scrollPane);
		//this.add(exitButton);
		
		this.setSize(500,300);
		
		//this.pack();
		
	}
	
	private void exit(){
		this.setVisible(false);
	}
	
	private void setText(){
		@SuppressWarnings("unchecked")
		Vector<ClientTransfer> clientList =  
				(Vector<ClientTransfer>) Controller.getInstance().event(Event.LIST_CLIENT,null,null);
				
				DefaultListModel model = new DefaultListModel();
				String text[] = new String[clientList.size()];
				if(!clientList.isEmpty()){
					for(int i = 0; i < clientList.size(); i++){
						ClientTransfer t = clientList.elementAt(i);
						if (t instanceof ClientTransferIndividual){
							text[i] = "Individual"+System.getProperty("line.separator")+
							          "ID: "+((ClientTransferIndividual) t).getID()+System.getProperty("line.separator")+
									  "Nombre: "+((ClientTransferIndividual) t).getName()+System.getProperty("line.separator")+
									  "Apellidos: "+((ClientTransferIndividual) t).getSurname()+System.getProperty("line.separator")+
									  "DNI: "+((ClientTransferIndividual) t).getDNI()+System.getProperty("line.separator")+
									  "Telefono: "+((ClientTransferIndividual) t).getPhone()+System.getProperty("line.separator")+
									  "Tarjeta de Credito: "+((ClientTransferIndividual) t).getCreditCard()+System.getProperty("line.separator")+
									  "Direccion: "+((ClientTransferIndividual) t).getAddress();
						model.addElement(text[i]);
						}else{
							text[i] ="Empresa"+System.getProperty("line.separator")+
									 "ID: "+((ClientTransferCompany) t).getID()+System.getProperty("line.separator")+
									 "Empresa: "+((ClientTransferCompany) t).getCompany()+System.getProperty("line.separator")+
									 "CIF: "+((ClientTransferCompany) t).getCIF()+System.getProperty("line.separator")+
									 "Telefono: "+((ClientTransferCompany) t).getPhone()+System.getProperty("line.separator")+
									 "Tarjeta de Credito: "+((ClientTransferCompany) t).getCreditCard()+System.getProperty("line.separator")+
									 "Direccion: "+((ClientTransferCompany) t).getAddress();
						model.addElement(text[i]);
						}
					}
				}
				else{
					model.addElement("No hay clientes");
				}
				
				renderPanel.setLayout(new BorderLayout());
				
				//JList list = new JList(text);
				list.setModel(model);
		        list.setCellRenderer(renderList);
		        renderPanel.add(list, BorderLayout.CENTER);
		        //renderPanel.add(list);
				
		        //this.pack();
		        
				//setTextArea(text);
	}
	
	private void addListener(){
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				exit();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}
		});
		
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				setText();
			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
				
			}
		});
				
		exitButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
	}

}
