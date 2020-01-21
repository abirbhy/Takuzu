package takuzu.interf.graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	public Menu()
	{
		ActionListener a=new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Element de menu["+event.getActionCommand()+"] utilisé");
			}
		};
		JMenu fichierMenu =new JMenu("Jeu");
		JMenuItem item=new JMenuItem("Nouveau",'N');
		item.addActionListener(a);
		fichierMenu.add(item);
		item=new JMenuItem("Enregistrer",'E');
		item.addActionListener(a);
		fichierMenu.add(item);
		item=new JMenuItem("Quitter",'Q');
		item.addActionListener(a);
		fichierMenu.add(item);
		add(fichierMenu);
	}
}
