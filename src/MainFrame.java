import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class MainFrame {

	private JFrame frmTitrep;
	private JTextField taille_de_la_grille;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmTitrep.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTitrep = new JFrame();
		frmTitrep.setTitle("Jeu Takuzu");
		frmTitrep.setBounds(100, 100, 605, 417);
		frmTitrep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTitrep.getContentPane().setLayout(null);
		
		taille_de_la_grille = new JTextField();
		taille_de_la_grille.setText("");
		taille_de_la_grille.setFont(new Font("Tahoma", Font.BOLD, 16));
		taille_de_la_grille.setBounds(378, 83, 69, 35);
		frmTitrep.getContentPane().add(taille_de_la_grille);
		taille_de_la_grille.setColumns(10);
		
		JLabel lblDonnerLaTaill = new JLabel("      Donner la taille de la grille :");
		lblDonnerLaTaill.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblDonnerLaTaill.setBounds(10, 81, 304, 35);
		lblDonnerLaTaill.setForeground(Color.pink);
		frmTitrep.getContentPane().add(lblDonnerLaTaill);
		
		
		JButton btnCommencer = new JButton("Commencer");
		btnCommencer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCommencer.setForeground(Color.pink);
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//hetha pour les alertes
				//JOptionPane.showMessageDialog(null, "hne t'affichi l'interface eli t7eb 3liha");
				if(Integer.parseInt(taille_de_la_grille.getText())==4) {				
			      	Interface4x4 interface4x4 = new Interface4x4();
			    	interface4x4.setTitle("interface4x4");			
				    interface4x4.setVisible(true);
				}
				else if(Integer.parseInt(taille_de_la_grille.getText())==6)
				{
					Interface6x6 interface6x6 = new Interface6x6();
			    	interface6x6.setTitle("interface6x6");			
				    interface6x6.setVisible(true);
				}
				else if(Integer.parseInt(taille_de_la_grille.getText())==8) {
					Interface8x8 interface8x8 = new Interface8x8();
			    	interface8x8.setTitle("interface8x8");			
				    interface8x8.setVisible(true);
				}
				//hetha bech t5abi l main frame eli fih taille
				frmTitrep.setVisible(false);
				
				
			}
		});
		
		
		btnCommencer.setBounds(244, 171, 132, 43);
		frmTitrep.getContentPane().add(btnCommencer);
		
		
	}
}
