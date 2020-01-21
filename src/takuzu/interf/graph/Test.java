package takuzu.interf.graph;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.layout.Border;
import takuzu.jeu.console.Takuzu;

public class Test extends JFrame{
	private JPanel pan=new JPanel();
	static public void main(String [] arg) {
		JFrame frame =new JFrame();
		frame.setTitle("Takuzu");
		frame.setSize(360,380);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(new Menu());
		//frame.getContentPane().add(g);
		frame.setVisible(true);
	}
	Test(){
		
	}
	public void initComposant() {
		
	}
}
