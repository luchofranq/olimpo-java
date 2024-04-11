package olimpo_vistas_principal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import ccdtye_vistas.menuCCDTyE;
import identificado_vistas.identificadoMenu;
import no_identificado_vistas.no_identificadoMenu;
import testigo_vistas.menuTestigo;

@SuppressWarnings("serial")
public class panelPrincipal extends JPanel {

	/**
	 * Create the panel.
	 */
	public panelPrincipal() {
		
	
		setBackground(new Color(24, 61, 61));
		
		setLayout(null);

		JButton Testigosbtn = new JButton("TESTIGOS");
		Testigosbtn.setForeground(new Color(255, 255, 255));
		Testigosbtn.setBackground(new Color(0, 0, 0));
		Testigosbtn.setVerticalAlignment(SwingConstants.TOP);
		Testigosbtn.setBounds(235, 167, 352, 53);
		Testigosbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setBounds(0, 0, 800, 800);
				j.setVisible(true);
				j.setContentPane(new menuTestigo());
				j.validate();
			}
		});
		Testigosbtn.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 44));
		add(Testigosbtn);

		JButton Noidentificadosbtn = new JButton("NO IDENTIFICADOS");
		Noidentificadosbtn.setForeground(new Color(255, 255, 255));
		Noidentificadosbtn.setBackground(new Color(0, 0, 0));
		Noidentificadosbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setBounds(0, 0, 800, 800);
				j.setVisible(true);
				j.setContentPane(new no_identificadoMenu());
				j.validate();

			}
		});
		Noidentificadosbtn.setVerticalAlignment(SwingConstants.TOP);
		Noidentificadosbtn.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 44));
		Noidentificadosbtn.setBounds(235, 243, 352, 53);
		add(Noidentificadosbtn);

		JButton Identificadosbtn = new JButton("IDENTIFICADOS");
		Identificadosbtn.setForeground(new Color(255, 255, 255));
		Identificadosbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setBounds(0, 0, 800, 800);
				j.setVisible(true);
				j.setContentPane(new identificadoMenu());
				j.validate();
			}
		});
		Identificadosbtn.setBackground(new Color(0, 0, 0));
		Identificadosbtn.setVerticalAlignment(SwingConstants.TOP);
		Identificadosbtn.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 44));
		Identificadosbtn.setBounds(235, 322, 352, 53);
		add(Identificadosbtn);

		JButton CCDTyEbtn = new JButton("CCDTYE");
		CCDTyEbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setBounds(0, 0, 800, 800);
				j.setVisible(true);
				j.setContentPane(new menuCCDTyE());
				j.validate();
			}
		});
		CCDTyEbtn.setVerticalAlignment(SwingConstants.TOP);
		CCDTyEbtn.setForeground(Color.WHITE);
		CCDTyEbtn.setFont(new Font("Microsoft Himalaya", Font.PLAIN, 44));
		CCDTyEbtn.setBackground(Color.BLACK);
		CCDTyEbtn.setBounds(235, 84, 352, 53);
		add(CCDTyEbtn);
	}
}
