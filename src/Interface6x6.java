import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import takuzu.jeu.console.RemplissageException;
import takuzu.jeu.console.Takuzu;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Interface6x6 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Timer timer;
	private JLabel label = new JLabel("      Timer :00 : 00 : 00");
	private Takuzu takuzu=new Takuzu(6);
	private int counter=0;
	private Date date_debut;

	/**
	 * Create the frame.
	 */
	public Interface6x6() {
		date_debut=new Date();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 385);
		
		JPanel conteneurGrille = new JPanel();
		conteneurGrille.setBounds(45, 37, 541, 206);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.getColumnModel().getColumn(1).setPreferredWidth(55);
		table.getColumnModel().getColumn(2).setPreferredWidth(55);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.getColumnModel().getColumn(4).setPreferredWidth(55);
		table.getColumnModel().getColumn(5).setPreferredWidth(55);
		
		table.setRowHeight(30);
		
		
		 ActionListener action = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                label.setText(TimeFormat(counter));
	                counter++;
	            }
	        };

	        timer = new Timer(1000, action);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnJeu = new JMenu("Jeu");
		menuBar.add(mnJeu);
		
		JMenuItem mntmNouveau = new JMenuItem("Nouveau");
		mnJeu.add(mntmNouveau);
		mntmNouveau.setIcon(new ImageIcon("nouveau.png"));
		mntmNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter=0;
				timer.start();
				takuzu=new Takuzu(6);
				try {
				   takuzu=takuzu.generate();
				   }
				catch(RemplissageException excep) {}
				System.out.println(takuzu);
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						if(takuzu.getGrille()[i][j]==-1)
							table.setValueAt(null, i, j);
						else
							table.setValueAt(takuzu.getGrille()[i][j],i,j);
					}
				}
				takuzu.toString();
				
			}
		});
	
		
		JMenuItem mntmOuvrir = new JMenuItem("Ouvrir");
		mnJeu.add(mntmOuvrir);
		mntmOuvrir.setIcon(new ImageIcon("mini_charger.png"));
		mntmOuvrir.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				FileInputStream file;
				takuzu=new Takuzu('c');
				 try {
						file=new FileInputStream("takuzu6x6.serial");
						ObjectInputStream stream =new ObjectInputStream(file);
		                takuzu=takuzu.readObject(stream);
		                stream.close();
		                }
					catch(IOException e1) { System.out.println("Erreur de lecture ! "); }
					catch(ClassNotFoundException e2) { System.out.println("Erreur de lecture ! "); }
				 date_debut=takuzu.charger_temps();
				 int[][] grille=takuzu.getGrille();
				 for (int i = 0; i < 6; i++) {
						for (int j = 0; j < 6; j++) {
							if(grille[i][j]==-1)
								table.setValueAt(null, i, j);
							else
								table.setValueAt(grille[i][j],i,j);
						}
					}
				 takuzu.toString();
				
			}
		});
		
		
		JMenuItem mntmEnregistrer = new JMenuItem("Enregistrer");
		mnJeu.add(mntmEnregistrer);
		mntmEnregistrer.setIcon(new ImageIcon("mini_save.png"));
		mntmEnregistrer.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				takuzu.calcul_duree(date_debut, new Date());
				takuzu.enreg_temps();
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						if(table.getValueAt(i, j)==null)
							takuzu.getGrille()[i][j]=-1;
						else
						    takuzu.getGrille()[i][j] = (int) table.getValueAt(i, j);
					}
				}
				takuzu.toString();
				FileOutputStream f;
				try {
					f=new FileOutputStream("takuzu6x6.serial");
					ObjectOutputStream stream =new ObjectOutputStream(f);
	                takuzu.writeObject(stream);
	                stream.close();
	                }
				catch(IOException excep) { System.out.println("Erreur d'ecriture ! "); }			
				 JOptionPane.showMessageDialog(null, "      Partie enregistrée   ");
			}
		});
		
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mnJeu.add(mntmQuitter);
		mntmQuitter.setIcon(new ImageIcon("mini_quitter.png"));
		mntmQuitter.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);;
			}
		});
		
		
		JMenu mnScore = new JMenu("Score");
		menuBar.add(mnScore);
		
		JMenuItem mntmMeilleurScore = new JMenuItem("Meilleur Score");
		mnScore.add(mntmMeilleurScore);
		mntmMeilleurScore.setIcon(new ImageIcon("score.jpg"));
		mntmMeilleurScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "   Meilleur Score  :  "+ takuzu.min_temps()+" s");
			} 
		} );
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btnVerifier = new JButton("  Verifier");
		btnVerifier.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVerifier.setBounds(270, 263, 109, 27);
		btnVerifier.setForeground(Color.pink);
		btnVerifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on va affecter l matrice l objet takuzu puis on verifie

				Takuzu takuzu = new Takuzu(6);
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						takuzu.getGrille()[i][j] = (int) table.getValueAt(i, j);
					}
				}
				takuzu.toString();
				if(takuzu.estValide()) {
					JOptionPane.showMessageDialog(null, "Vous avez terminé en " + takuzu.calcul_duree(date_debut, new Date()));
					takuzu.enreg_meilleurs_temps();
				}
				    
			}
		});
		contentPane.add(btnVerifier);
		conteneurGrille.add(table);
		contentPane.add(conteneurGrille);
	
		
	}
	
	 private String TimeFormat(int count) {

	        int hours = count / 3600;
	        int minutes = (count - hours * 3600) / 60;
	        int seconds = count - minutes * 60;

	        return String.format("      Timer :" + "%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
	    }
}
