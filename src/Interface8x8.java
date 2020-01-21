import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import takuzu.jeu.console.RemplissageException;
import takuzu.jeu.console.Takuzu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.JTree;
import javax.swing.Timer;

import java.awt.Choice;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Interface8x8 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7129722685266774200L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnJeu;
	private JMenuItem mntmNouveau;
	private JMenuItem mntmEnregistrer;
	private JMenuItem mntmQuitter;
	private JMenu mnHelp;
	private JTable table;
	private JButton btnVerifier;
	private Timer timer;
	private JLabel label = new JLabel("      Timer :00 : 00 : 00");
	private Takuzu takuzu=new Takuzu(8);
	private int counter=0;
	private JMenuItem mntmMeilleurScore;
	private Date date_debut;

	/**
	 * Create the frame.
	 */
	public Interface8x8() {
	    date_debut=new Date();
		setTitle("Takuzu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 385);
		JPanel conteneurGrille = new JPanel();
		conteneurGrille.setBounds(45, 37, 541, 206);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 200));

		table.setRowHeight(20);
		
		 ActionListener action = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                label.setText(TimeFormat(counter));
	                counter++;
	            }
	        };

	        timer = new Timer(1000, action);
		
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnJeu = new JMenu("Jeu");
		menuBar.add(mnJeu);
		
		mntmNouveau = new JMenuItem("Nouveau");
		mnJeu.add(mntmNouveau);
		mntmNouveau.setIcon(new ImageIcon("nouveau.png"));
		mntmNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter=0;
				timer.start();
				takuzu=new Takuzu(8);
				try {
				   takuzu=takuzu.generate();
				   }
				catch(RemplissageException excep) {}
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
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
						file=new FileInputStream("takuzu8x8.serial");
						ObjectInputStream stream =new ObjectInputStream(file);
		                takuzu=takuzu.readObject(stream);
		                stream.close();
		                }
					catch(IOException e1) { System.out.println("Erreur de lecture ! "); }
					catch(ClassNotFoundException e2) { System.out.println("Erreur de lecture ! "); }
				 date_debut=takuzu.charger_temps();
				 int[][] grille=takuzu.getGrille();
				 for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							if(grille[i][j]==-1)
								table.setValueAt(null, i, j);
							else
								table.setValueAt(grille[i][j],i,j);
						}
					}
				 takuzu.toString();
			}
		});
		
		
		
		mntmEnregistrer = new JMenuItem("Enregistrer");
		mnJeu.add(mntmEnregistrer);
		mntmEnregistrer.setIcon(new ImageIcon("mini_save.png"));
		mntmEnregistrer.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				takuzu.calcul_duree(date_debut, new Date());
				takuzu.enreg_temps();
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if(table.getValueAt(i, j)==null)
							takuzu.getGrille()[i][j]=-1;
						else
						    takuzu.getGrille()[i][j] = (int) table.getValueAt(i, j);
					}
				}
				takuzu.toString();
				FileOutputStream f;
				try {
					f=new FileOutputStream("takuzu8x8.serial");
					ObjectOutputStream stream =new ObjectOutputStream(f);
	                takuzu.writeObject(stream);
	                stream.close();
	                }
				catch(IOException excep) { System.out.println("Erreur d'ecriture ! "); }			
				 JOptionPane.showMessageDialog(null, "      Partie enregistrée   ");
			}
		});
		
		
		mntmQuitter = new JMenuItem("Quitter");
		mnJeu.add(mntmQuitter);
		mntmQuitter.setIcon(new ImageIcon("mini_quitter.png"));
		mntmQuitter.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);;
			}
		});
		
		
		JMenu mnScore = new JMenu("Score");
		menuBar.add(mnScore);		
		mntmMeilleurScore = new JMenuItem("Meilleur Score");
		mntmMeilleurScore.setIcon(new ImageIcon("score.jpg"));
		mnScore.add(mntmMeilleurScore);
		mntmMeilleurScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			     JOptionPane.showMessageDialog(null, "   Meilleur Score  :  "+ takuzu.min_temps()+" s");
			} 
		} );
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		btnVerifier = new JButton("verifier");
		btnVerifier.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVerifier.setBounds(257, 253, 124, 27);
		btnVerifier.setForeground(Color.pink);
		btnVerifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on va affecter l matrice l objet takuzu puis on verifie

				Takuzu takuzu = new Takuzu(8);
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
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
		
	
		contentPane.add(conteneurGrille);
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
