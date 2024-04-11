package testigo_vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import olimpo.Testigo;
import olimpo_DAO.Testigo_DAO;

@SuppressWarnings("serial")
public class panelTestigo extends JPanel {

	private JTextField textField_dni;
	private JTextField textField_nombre;
	private JTextField textField_testimonio;

	public void agregarTestigo() {
		setBackground(new Color(24, 61, 61));
		setLayout(null);

		textField_nombre = new JTextField();
		textField_nombre.setBounds(106, 71, 86, 20);
		add(textField_nombre);
		textField_nombre.setColumns(10);

		textField_dni = new JTextField();
		textField_dni.setBounds(106, 117, 86, 20);
		add(textField_dni);
		textField_dni.setColumns(10);

		textField_testimonio = new JTextField();
		textField_testimonio.setBounds(106, 167, 86, 20);
		add(textField_testimonio);
		textField_testimonio.setColumns(10);

		JButton guardar = new JButton("Agregar");

		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Testigo testigo = new Testigo(textField_nombre.getText(), Integer.parseInt(textField_dni.getText()),
						textField_testimonio.getText());
				Testigo_DAO dao = new Testigo_DAO();

				dao.insertTestigo(testigo);

			}
		});
		guardar.setBounds(103, 354, 89, 23);

		add(guardar);

		JLabel lblNewLabel = new JLabel("Nombre Completo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(29, 74, 46, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(29, 120, 67, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Testimonio");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(29, 170, 67, 14);
		add(lblNewLabel_1_1);

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Testigo");
		lblNewJgoodiesTitle.setForeground(new Color(255, 255, 255));
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewJgoodiesTitle.setBounds(21, 11, 346, 58);
		add(lblNewJgoodiesTitle);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setContentPane(new menuTestigo());
				j.setBounds(0, 0, 800, 800);
				j.setVisible(true);
				j.validate();

			}
		});
		btnVolver.setBounds(103, 404, 89, 23);
		add(btnVolver);

	}

	public void actualizarTestigo(Testigo testigoaux, int dni) {

		textField_nombre = new JTextField();
		textField_nombre.setBounds(106, 71, 86, 20);
		add(textField_nombre);
		textField_nombre.setColumns(10);

		textField_dni = new JTextField();
		textField_dni.setBounds(106, 117, 86, 20);
		add(textField_dni);
		textField_dni.setColumns(10);

		textField_testimonio = new JTextField();
		textField_testimonio.setBounds(106, 167, 86, 20);
		add(textField_testimonio);
		textField_testimonio.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre Completo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(29, 74, 46, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(29, 120, 67, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Testimonio");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(29, 170, 67, 14);
		add(lblNewLabel_1_1);

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("CCDTYE");
		lblNewJgoodiesTitle.setForeground(new Color(255, 255, 255));
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewJgoodiesTitle.setBounds(21, 11, 346, 58);
		add(lblNewJgoodiesTitle);
		setBackground(new Color(24, 61, 61));
		setLayout(null);

		textField_testimonio.setText(testigoaux.getTestimonio());
		add(textField_testimonio);
		textField_dni.setText(Integer.toString(testigoaux.getDNI()));
		add(textField_dni);

		textField_nombre.setText(testigoaux.getNombreCompleto());
		add(textField_nombre);

		JButton btnModificar = new JButton("modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Testigo testigo = new Testigo(textField_nombre.getText(), Integer.parseInt(textField_dni.getText()),
						textField_testimonio.getText());
				Testigo_DAO dao = new Testigo_DAO();

				dao.updateTestigo(testigo, dni);

			}

		});
		btnModificar.setBounds(106, 388, 89, 23);
		add(btnModificar);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setContentPane(new menuTestigo());
				j.setBounds(150, 150, 800, 800);
				j.setVisible(true);
				j.validate();

			}
		});
		btnNewButton.setBounds(106, 447, 89, 23);
		add(btnNewButton);

	}
}
