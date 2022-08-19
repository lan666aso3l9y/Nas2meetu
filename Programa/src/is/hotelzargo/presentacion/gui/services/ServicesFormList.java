package is.hotelzargo.presentacion.gui.services;

import is.hotelzargo.negocio.transfer.ClientTransfer;
import is.hotelzargo.negocio.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.transfer.ClientTransferIndividual;
import is.hotelzargo.negocio.transfer.ServiceTransfer;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class ServicesFormList extends JDialog {
	
	private JTextArea listTextArea;
	private JScrollPane scrollPane;
	
	private JButton exitButton;
	
	public ServicesFormList(JFrame owner, boolean mod){
		super(owner,mod);
		this.setTitle("Listar Servicios");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		initTextArea();
		
		exitButton = new JButton("Salir");
		
		addListener();
		
		this.setLayout(new GridLayout(2, 1));
		this.add(scrollPane);
		this.add(exitButton);
		
		this.pack();
		
	}
	
	
	private void exit(){
		this.setVisible(false);
		listTextArea.setText("");
	}
	
	private void addListener(){
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				/*Controller.getInstance().event(Event.LIST_CLIENT,null,null);
				setTextArea("holas" + "\n" + "holas 2");*/
				Vector<ServiceTransfer> servicesList = 
				(Vector<ServiceTransfer>) Controller.getInstance().event(Event.LIST_SERVICE,null,null);
				String text = "";
				if(servicesList != null){
					for(int i = 0; i < servicesList.size(); i++){
						ServiceTransfer t = servicesList.elementAt(i);
							text += ((ServiceTransfer) t).getServices();
					}
				}
				else{
					listTextArea.setText("No services");
				}
				setTextArea(text);
				
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
				
		exitButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
	}
	
	
	@SuppressWarnings("unused")
	private void initTextArea(){
		listTextArea = new JTextArea();
		listTextArea.setEditable(false);
		//listTextArea.setText("hola");
		listTextArea.setSize(500, 500);
		scrollPane = new JScrollPane(listTextArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		//scrollPaneVertical.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	}
	
	private void setTextArea(String text){
		listTextArea.setText(text);
	}
	

}
