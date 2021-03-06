package is.hotelzargo.presentacion.book.gui;

import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;
import is.hotelzargo.presentacion.maingui.RenderList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class BookFormFind extends JDialog {

	private JLabel checkinLabel;
	private JLabel checkoutLabel;
	
	private JTextField checkinText;
	private JTextField checkoutText;
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	public BookFormFind(JFrame owner,boolean mod) {
		super(owner,mod);
		
		this.setTitle("Buscar disponibilidad");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		checkinLabel =  new JLabel("Fecha de entrada: ");
		checkoutLabel = new JLabel("Fecha de salida:  ");
		
		checkinText = new JTextField(10);
		checkoutText = new JTextField(10);
		
		acceptButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");
		
		addListener();
		
		resetForm();
		
		this.pack();
	}
	
	private void exit(){
		checkinText.setText("");
		checkoutText.setText("");
		this.getContentPane().removeAll();
		resetForm();
		this.pack();
		this.invalidate();
		this.validate();
		this.repaint();
		this.setVisible(false);
	}
	
	private void resetForm(){
		
		JPanel panelIn = new JPanel();
		panelIn.setLayout(new GridLayout(1,2));
		panelIn.add(checkinLabel);
		panelIn.add(checkinText);
		
		JPanel panelOut = new JPanel();
		panelOut.setLayout(new GridLayout(1,2));
		panelOut.add(checkoutLabel);
		panelOut.add(checkoutText);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(1,2));
		panelButtons.add(acceptButton);
		panelButtons.add(cancelButton);
		
		JPanel checkinInfoPanel = new JPanel();
		checkinInfoPanel.setLayout(new GridLayout(1,2));
		checkinInfoPanel.add(Box.createVerticalGlue());
		//checkinInfoPanel.add(new JLabel("Formato dd/mm/yy HH:mm:ss"));
		checkinInfoPanel.add(new JLabel("Ejemplo: 01/06/12 12:00:00"));
		
		JPanel checkoutInfoPanel = new JPanel();
		checkoutInfoPanel.setLayout(new GridLayout(1,2));
		checkoutInfoPanel.add(Box.createVerticalGlue());
		//checkinInfoPanel.add(new JLabel("Formato dd/mm/yy HH:mm:ss"));
		checkoutInfoPanel.add(new JLabel("Ejemplo: 01/06/12 12:00:00"));
		
		this.getContentPane().setLayout(new GridLayout(5,1));
		this.getContentPane().add(panelIn);
		this.getContentPane().add(checkinInfoPanel);
		this.getContentPane().add(panelOut);
		this.getContentPane().add(checkoutInfoPanel);
		this.getContentPane().add(panelButtons);
	}
	
	private void accept(){
		Vector<String> dates = new Vector<String>();
		dates.add(checkinText.getText());
		dates.add(checkoutText.getText());
		@SuppressWarnings("unchecked")
		Vector<Integer> data = (Vector<Integer>) Controller.getInstance().event(Event.FIND_BOOK,dates,null);
		if(data != null)showList(data);
	}
	
	private void showList(Vector<Integer> rooms){
		JList list = new JList();
		DefaultListModel model = new DefaultListModel();
		this.getContentPane().removeAll();
		if(rooms != null){
			for(int i = 0; i < rooms.size(); i++){
				if(!model.contains(rooms.get(i))) model.addElement(rooms.get(i));
			}
		}else{
			model.addElement("No hay habitaciones disponibles");
		}
		JPanel renderPanel = new JPanel();
		renderPanel.setLayout(new BorderLayout());

		RenderList renderList = new RenderList();
		
		list.setModel(model);
		list.setCellRenderer(renderList);
		renderPanel.add(list, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(renderPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.getContentPane().removeAll();
		
		this.getContentPane().add(scrollPane);
		
		this.setSize(500,300);
		
		this.invalidate();
		this.validate();
		this.repaint();
	}
	
	private void addListener(){
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
		
		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accept();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
	}
}
