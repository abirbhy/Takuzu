import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import takuzu.jeu.console.RemplissageException;
import takuzu.jeu.console.Takuzu;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class Interface4x4 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8854302421532918463L;
	private JPanel contentPane;
	private JTable table;
	private Timer timer;
	private JLabel label = new JLabel("      Timer :00 : 00 : 00");
	private Takuzu takuzu=new Takuzu(4);
	private int counter=0;
	private Date date_debut ;

	/**
	 * Create the frame.
	 */
	
	public Interface4x4() {
		date_debut = new Date();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 385);

		JPanel conteneurGrille = new JPanel();
		conteneurGrille.setBounds(45, 48, 541, 206);
		

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.setRowHeight(40);
		

		
		 ActionListener action = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                label.setText(TimeFormat(counter));
	                counter++;
	            }
	        };

	        timer = new Timer(1000, action);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnVerifier = new JButton(" Verifier");
		btnVerifier.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVerifier.setBounds(258, 270, 111, 27);
		btnVerifier.setForeground(Color.pink);
		btnVerifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on va affecter l matrice l objet takuzu puis on verifie

				Takuzu takuzu = new Takuzu(4);
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						takuzu.getGrille()[i][j] = (int) table.getValueAt(i, j);
					}
				}
				takuzu.toString();
				if(takuzu.estValide()) {
				     JOptionPane.showMessageDialog(null, "Félicitations ! Vous avez terminé en " + takuzu.calcul_duree(date_debut, new Date()));
				     takuzu.enreg_meilleurs_temps();
				     File f1=new File("les_dix.txt");
				 	File f2=new File("les_dix_meilleurs.txt");
				 	
				 	if(f1.exists())
				 	{
				 		f2.delete();
				 		f1.renameTo(f2);
				 		
				 	       }
				}
				else
					   JOptionPane.showMessageDialog(null, "  Dommage ! Grille invalide\n   "+"Veuillez vous réessayer ");
				
			}
		});
		contentPane.add(btnVerifier);
		conteneurGrille.add(table);
		contentPane.add(conteneurGrille);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 596, 22);
		contentPane.add(menuBar);
		
				JMenu mnJeu = new JMenu("Jeu");
				menuBar.add(mnJeu);
				
						JMenuItem mntmNouveau = new JMenuItem("Nouveau");
						mnJeu.add(mntmNouveau);
						mntmNouveau.setIcon(new ImageIcon("nouveau.png"));
						mntmNouveau.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								counter=0;
								timer.start();
								takuzu=new Takuzu(4);
								try {
								   takuzu=takuzu.generate();
								   }
								catch(RemplissageException excep) {}
								for (int i = 0; i < 4; i++) {
									for (int j = 0; j < 4; j++) {
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
										file=new FileInputStream("takuzu4x4.serial");
										ObjectInputStream stream =new ObjectInputStream(file);
						                takuzu=takuzu.readObject(stream);
						                stream.close();
						                }
									catch(IOException e1) { System.out.println("Erreur de lecture ! "); }
									catch(ClassNotFoundException e2) { System.out.println("Erreur de lecture ! "); }
								 date_debut=takuzu.charger_temps();
								 int[][] grille=takuzu.getGrille();
								 for (int i = 0; i < 4; i++) {
										for (int j = 0; j < 4; j++) {
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
								for (int i = 0; i < 4; i++) {
									for (int j = 0; j < 4; j++) {
										if(table.getValueAt(i, j)==null)
											takuzu.getGrille()[i][j]=-1;
										else
										    takuzu.getGrille()[i][j] = (int) table.getValueAt(i, j);
									}
								}
								takuzu.toString();
								FileOutputStream f;
								try {
									f=new FileOutputStream("takuzu4x4.serial");
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
	}
	
	 private String TimeFormat(int count) {

	        int hours = count / 3600;
	        int minutes = (count - hours * 3600) / 60;
	        int seconds = count - minutes * 60;

	        return String.format("      Timer :" + "%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
	    }
}
