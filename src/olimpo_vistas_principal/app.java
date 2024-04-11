package olimpo_vistas_principal;

import javax.swing.JFrame;

public class app {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame j = new JFrame();
		j.setResizable(false);
		j.setBounds(150, 150, 800, 500);
		j.setUndecorated(true);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setContentPane(new panelPrincipal());
		j.validate();
	}

}
