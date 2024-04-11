package ccdtye_vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import olimpo.CCDTyE;
import olimpo_DAO.CCDTyE_DAO;

@SuppressWarnings("serial")
public class panelCCDTyE extends JPanel {
	private JTextField textField_ubicacion;
	private JTextField textField_nombre;
	private JTextField textField_fechainicio;
	private JTextField textField_fechacierre;

	/**
	 * Create the panel.
	 */

	public void AgregarCCDTyE() {
		setBackground(new Color(24, 61, 61));
		setLayout(null);

		JLabel errorFecha = new JLabel("Error en las fechas");
		errorFecha.setForeground(new Color(255, 255, 255));
		errorFecha.setBounds(174, 249, 110, 14);
		add(errorFecha);
		errorFecha.setVisible(false);

		JLabel errorResponsable = new JLabel("Error responsables");
		errorResponsable.setForeground(new Color(255, 255, 255));
		errorResponsable.setBounds(174, 329, 147, 14);
		add(errorResponsable);
		errorResponsable.setVisible(false);

		textField_nombre = new JTextField();
		textField_nombre.setBounds(106, 71, 86, 20);
		add(textField_nombre);
		textField_nombre.setColumns(10);

		textField_ubicacion = new JTextField();
		textField_ubicacion.setBounds(106, 117, 86, 20);
		add(textField_ubicacion);
		textField_ubicacion.setColumns(10);

		textField_fechainicio = new JTextField();
		textField_fechainicio.setBounds(106, 167, 86, 20);
		add(textField_fechainicio);
		textField_fechainicio.setColumns(10);

		textField_fechacierre = new JTextField();
		textField_fechacierre.setBounds(106, 216, 86, 20);
		add(textField_fechacierre);
		textField_fechacierre.setColumns(10);

		JCheckBox checkPolicia = new JCheckBox("policia");
		checkPolicia.setBounds(21, 273, 97, 23);
		add(checkPolicia);

		JCheckBox checkGendarmeria = new JCheckBox("gendarmeria");
		checkGendarmeria.setBounds(21, 299, 97, 23);
		add(checkGendarmeria);

		JCheckBox checkEjercito = new JCheckBox("ejercito");
		checkEjercito.setBounds(21, 325, 97, 23);
		add(checkEjercito);

		JButton guardar = new JButton("Agregar");

		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkPolicia.isSelected() == false && checkGendarmeria.isSelected() == false
						&& checkEjercito.isSelected() == false) {
					errorResponsable.setVisible(true);
				} else {

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate a = LocalDate.parse(textField_fechainicio.getText(), formatter);
					LocalDate b = LocalDate.parse(textField_fechacierre.getText(), formatter);
					if (a.isAfter(b)) {
						errorFecha.setVisible(true);

					} else {

						errorFecha.setVisible(false);
						errorResponsable.setVisible(false);
						boolean[] aux = { checkPolicia.isSelected(), checkGendarmeria.isSelected(),
								checkEjercito.isSelected() };

						CCDTyE CCDTyE = new CCDTyE(textField_nombre.getText(), textField_ubicacion.getText(), a, b,
								aux);
						CCDTyE_DAO dao = new CCDTyE_DAO();

						dao.insertCCDTyE(CCDTyE);

					}
				}
			}
		});
		guardar.setBounds(103, 354, 89, 23);

		add(guardar);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(29, 74, 46, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ubicacion");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(29, 120, 67, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Fecha Inicio");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(29, 170, 67, 14);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha Cierre");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setBounds(29, 219, 67, 14);
		add(lblNewLabel_1_1_1);

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("CCDTYE");
		lblNewJgoodiesTitle.setForeground(new Color(255, 255, 255));
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewJgoodiesTitle.setBounds(21, 11, 346, 58);
		add(lblNewJgoodiesTitle);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setContentPane(new menuCCDTyE());
				j.setBounds(0, 0, 800, 800);
				j.setVisible(true);
				j.validate();

			}
		});
		btnVolver.setBounds(103, 404, 89, 23);
		add(btnVolver);

	}

	public void ActualizarCCDTyE(CCDTyE centroaux, String nombre) {
		JLabel errorFecha = new JLabel("Error en las fechas");
		errorFecha.setForeground(new Color(255, 255, 255));
		errorFecha.setBounds(174, 249, 110, 14);
		add(errorFecha);
		errorFecha.setVisible(false);

		JLabel errorResponsable = new JLabel("Error responsables");
		errorResponsable.setForeground(new Color(255, 255, 255));
		errorResponsable.setBounds(174, 329, 147, 14);
		add(errorResponsable);
		errorResponsable.setVisible(false);

		textField_nombre = new JTextField();
		textField_nombre.setBounds(106, 71, 86, 20);
		add(textField_nombre);
		textField_nombre.setColumns(10);

		textField_ubicacion = new JTextField();
		textField_ubicacion.setBounds(106, 117, 86, 20);
		add(textField_ubicacion);
		textField_ubicacion.setColumns(10);

		textField_fechainicio = new JTextField();
		textField_fechainicio.setBounds(106, 167, 86, 20);
		add(textField_fechainicio);
		textField_fechainicio.setColumns(10);

		textField_fechacierre = new JTextField();
		textField_fechacierre.setBounds(106, 216, 86, 20);
		add(textField_fechacierre);
		textField_fechacierre.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(29, 74, 46, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ubicacion");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(29, 120, 67, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Fecha Inicio");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(29, 170, 67, 14);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha Cierre");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setBounds(29, 219, 67, 14);
		add(lblNewLabel_1_1_1);

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("CCDTYE");
		lblNewJgoodiesTitle.setForeground(new Color(255, 255, 255));
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewJgoodiesTitle.setBounds(21, 11, 346, 58);
		add(lblNewJgoodiesTitle);
		setBackground(new Color(24, 61, 61));
		setLayout(null);

		textField_fechacierre.setText(centroaux.getFechaCierre().toString());
		add(textField_fechacierre);

		textField_fechainicio.setText(centroaux.getFechaInicio().toString());
		add(textField_fechainicio);

		textField_ubicacion.setText(centroaux.getUbicacion());
		add(textField_ubicacion);

		textField_nombre.setText(centroaux.getNombre());
		add(textField_nombre);

		JCheckBox checkPolicia = new JCheckBox("policia");
		checkPolicia.setBounds(21, 289, 97, 23);
		add(checkPolicia);

		JCheckBox checkGendarmeria = new JCheckBox("gendarmeria");
		checkGendarmeria.setBounds(21, 315, 97, 23);
		add(checkGendarmeria);

		JCheckBox checkEjercito = new JCheckBox("ejercito");
		checkEjercito.setBounds(21, 341, 97, 23);
		add(checkEjercito);

		JButton btnModificar = new JButton("modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkPolicia.isSelected() == false && checkGendarmeria.isSelected() == false
						&& checkEjercito.isSelected() == false) {
					errorResponsable.setVisible(true);
				} else {

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate a = LocalDate.parse(textField_fechainicio.getText(), formatter);
					LocalDate b = LocalDate.parse(textField_fechacierre.getText(), formatter);
					if (a.isAfter(b)) {
						errorFecha.setVisible(true);

					} else {

						errorFecha.setVisible(false);
						errorResponsable.setVisible(false);
						boolean[] aux = { checkPolicia.isSelected(), checkGendarmeria.isSelected(),
								checkEjercito.isSelected() };

						CCDTyE CCDTyE = new CCDTyE(textField_nombre.getText(), textField_ubicacion.getText(), a, b,
								aux);
						CCDTyE_DAO dao = new CCDTyE_DAO();

						dao.updateCCDTyE(CCDTyE, nombre);

					}
				}

			}
		});
		btnModificar.setBounds(106, 388, 89, 23);
		add(btnModificar);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setContentPane(new menuCCDTyE());
				j.setBounds(150, 150, 800, 800);
				j.setVisible(true);
				j.validate();

			}
		});
		btnNewButton.setBounds(106, 447, 89, 23);
		add(btnNewButton);

	}

}
