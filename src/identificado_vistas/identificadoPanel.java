package identificado_vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import olimpo.CCDTyE;
import olimpo.Identificado;
import olimpo.Testigo;
import olimpo_DAO.CCDTyE_DAO;
import olimpo_DAO.Identificado_DAO;
import olimpo_DAO.Testigo_DAO;

@SuppressWarnings("serial")
public class identificadoPanel extends JPanel {
	public identificadoPanel() {
	}

	Testigo_DAO tdao = new Testigo_DAO();
	CCDTyE_DAO cdao = new CCDTyE_DAO();

	private JTextField textField_dni;
	private JTextField textField_nombre;
	private JTextField textField_LugarSecuestro;
	private JTextField textField_ultimaVezVisto;
	private JTextField textField_biografia;
	private JTextField textField_material;
	@SuppressWarnings("rawtypes")
	private JList list_ccdtyes;
	@SuppressWarnings("rawtypes")
	private JComboBox combobox_dnitestigo;
	@SuppressWarnings("rawtypes")
	private JComboBox combobox_profesion;

	@SuppressWarnings("unchecked")
	public void agregarIdentificado() {
		setBackground(new Color(24, 61, 61));
		setLayout(null);

		textField_nombre = new JTextField();
		textField_nombre.setBounds(106, 80, 86, 20);
		add(textField_nombre);
		textField_nombre.setColumns(10);

		textField_dni = new JTextField();
		textField_dni.setBounds(106, 110, 86, 20);
		add(textField_dni);
		textField_dni.setColumns(10);

		textField_LugarSecuestro = new JTextField();
		textField_LugarSecuestro.setBounds(106, 140, 86, 20);
		add(textField_LugarSecuestro);
		textField_LugarSecuestro.setColumns(10);

		textField_ultimaVezVisto = new JTextField();
		textField_ultimaVezVisto.setBounds(106, 170, 86, 20);
		add(textField_ultimaVezVisto);
		textField_ultimaVezVisto.setColumns(10);

		textField_biografia = new JTextField();
		textField_biografia.setBounds(106, 200, 86, 20);
		add(textField_biografia);
		textField_biografia.setColumns(10);

		textField_material = new JTextField();
		textField_material.setBounds(106, 230, 86, 20);
		add(textField_material);
		textField_material.setColumns(10);

		list_ccdtyes = new JList<String>();
		list_ccdtyes.setBounds(300, 80, 80, 100);
		list_ccdtyes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		try {
			ArrayList<CCDTyE> centros = new ArrayList<>();
			centros = cdao.selectAllCCDTyEs();

			DefaultListModel<String> listModel = new DefaultListModel<>();
			for (CCDTyE c : centros) {
				listModel.addElement(c.getNombre());

			}
			list_ccdtyes.setModel(listModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		add(list_ccdtyes);

		combobox_dnitestigo = new JComboBox<Integer>();
		combobox_dnitestigo.setBounds(106, 290, 86, 20);
		try {
			ArrayList<Testigo> testigos = new ArrayList<>();
			testigos = tdao.selectAllTestigos();

			DefaultComboBoxModel<Integer> listModel = new DefaultComboBoxModel<>();

			for (Testigo c : testigos) {
				listModel.addElement(c.getDNI());
			}
			combobox_dnitestigo.setModel(listModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		add(combobox_dnitestigo);

		combobox_profesion = new JComboBox<Integer>();
		combobox_profesion.setBounds(106, 330, 86, 20);
		try {
			Identificado_DAO idao = new Identificado_DAO();
			ArrayList<String> profesiones = new ArrayList<>();
			profesiones = idao.selectAllProfesiones();

			DefaultComboBoxModel<String> listModel = new DefaultComboBoxModel<>();

			for (String c : profesiones) {
				listModel.addElement(c);
			}
			combobox_profesion.setModel(listModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		add(combobox_profesion);

		JButton guardar = new JButton("Agregar");

		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Identificado_DAO idao = new Identificado_DAO();
				Testigo testigo = tdao.selectTestigo(
						tdao.selectIdTestigo(Integer.valueOf(combobox_dnitestigo.getSelectedItem().toString())));

				String profesion = idao
						.selectProfesion(idao.selectIdProfesion(combobox_profesion.getSelectedItem().toString()));

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate a = LocalDate.parse(textField_ultimaVezVisto.getText(), formatter);

				List<String> selectedValuesList = list_ccdtyes.getSelectedValuesList();
				ArrayList<CCDTyE> centrosAsociados = new ArrayList<>();

				for (String nombreCentro : selectedValuesList) {
					CCDTyE c = new CCDTyE(nombreCentro);
					centrosAsociados.add(c);
				}

				Identificado identificado = new Identificado(textField_nombre.getText(),
						Integer.parseInt(textField_dni.getText()), textField_LugarSecuestro.getText(), a,
						textField_biografia.getText(), textField_material.getText(), centrosAsociados, testigo,
						profesion);

				idao.insertIdentificado(identificado, testigo.getDNI());

			}
		});
		guardar.setBounds(103, 354, 89, 23);

		add(guardar);

		JLabel lblNewLabel = new JLabel("Nombre Completo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(29, 80, 46, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(29, 110, 67, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Lugar Secuestro");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(29, 140, 67, 14);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Ultima Vez Visto");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setBounds(29, 170, 67, 14);
		add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Biografia");
		lblNewLabel_1_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_3.setBounds(29, 200, 67, 14);
		add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Material");
		lblNewLabel_1_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_4.setBounds(29, 230, 67, 14);
		add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("CCDTyEs");
		lblNewLabel_1_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_5.setBounds(200, 80, 67, 14);
		add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("DNI Testigo");
		lblNewLabel_1_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_6.setBounds(29, 290, 67, 14);
		add(lblNewLabel_1_6);

		JLabel lblNewLabel_1_7 = new JLabel("Profesion");
		lblNewLabel_1_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_7.setBounds(29, 330, 67, 14);
		add(lblNewLabel_1_7);

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Desaparecidos Identificados");
		lblNewJgoodiesTitle.setForeground(new Color(255, 255, 255));
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewJgoodiesTitle.setBounds(21, 11, 346, 58);
		add(lblNewJgoodiesTitle);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setContentPane(new identificadoMenu());
				j.setBounds(0, 0, 1200, 1200);
				j.setVisible(true);
				j.validate();

			}
		});
		btnVolver.setBounds(103, 404, 89, 23);
		add(btnVolver);

	}

	@SuppressWarnings("unchecked")
	public void actualizarIdentificado(Identificado identificadoaux, int dni) {
		setBackground(new Color(24, 61, 61));
		setLayout(null);
		textField_nombre = new JTextField();
		textField_nombre.setBounds(106, 80, 86, 20);
		textField_nombre.setText(identificadoaux.getNombreCompleto());
		add(textField_nombre);

		textField_nombre.setColumns(10);

		textField_dni = new JTextField();
		textField_dni.setBounds(106, 110, 86, 20);
		textField_dni.setText(Integer.toString(identificadoaux.getDNI()));
		add(textField_dni);

		textField_dni.setColumns(10);

		textField_LugarSecuestro = new JTextField();
		textField_LugarSecuestro.setBounds(106, 140, 86, 20);
		textField_LugarSecuestro.setText(identificadoaux.getLugarSecuestro());
		add(textField_LugarSecuestro);

		textField_LugarSecuestro.setColumns(10);

		textField_ultimaVezVisto = new JTextField();
		textField_ultimaVezVisto.setBounds(106, 170, 86, 20);
		textField_ultimaVezVisto.setText(identificadoaux.getUltVezVisto().toString());
		add(textField_ultimaVezVisto);

		textField_ultimaVezVisto.setColumns(10);

		textField_biografia = new JTextField();
		textField_biografia.setBounds(106, 200, 86, 20);
		textField_biografia.setText(identificadoaux.getBiografia());
		add(textField_biografia);

		textField_biografia.setColumns(10);

		textField_material = new JTextField();
		textField_material.setBounds(106, 230, 86, 20);
		textField_material.setText(identificadoaux.getMaterial());
		add(textField_material);
		textField_material.setColumns(10);

		list_ccdtyes = new JList<String>();
		list_ccdtyes.setBounds(300, 80, 80, 100);
		list_ccdtyes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		try {
			ArrayList<CCDTyE> centros = new ArrayList<>();
			centros = cdao.selectAllCCDTyEs();

			DefaultListModel<String> listModel = new DefaultListModel<>();
			for (CCDTyE c : centros) {
				listModel.addElement(c.getNombre());

			}
			list_ccdtyes.setModel(listModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		add(list_ccdtyes);

		combobox_dnitestigo = new JComboBox<Integer>();
		combobox_dnitestigo.setBounds(106, 290, 86, 20);
		combobox_dnitestigo.setToolTipText(Integer.toString(identificadoaux.getTestigoAsociado().getDNI()));
		try {
			ArrayList<Testigo> testigos = new ArrayList<>();
			testigos = tdao.selectAllTestigos();

			DefaultComboBoxModel<Integer> listModel = new DefaultComboBoxModel<>();

			for (Testigo c : testigos) {
				listModel.addElement(c.getDNI());
			}
			combobox_dnitestigo.setModel(listModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		add(combobox_dnitestigo);

		combobox_profesion = new JComboBox<Integer>();
		combobox_profesion.setBounds(106, 330, 86, 20);
		combobox_profesion.setToolTipText(identificadoaux.getProfesion());
		try {
			Identificado_DAO idao = new Identificado_DAO();
			ArrayList<String> profesiones = new ArrayList<>();
			profesiones = idao.selectAllProfesiones();

			DefaultComboBoxModel<String> listModel = new DefaultComboBoxModel<>();

			for (String c : profesiones) {
				listModel.addElement(c);
			}
			combobox_profesion.setModel(listModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		add(combobox_profesion);

		JLabel lblNewLabel = new JLabel("Nombre Completo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(29, 80, 46, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(29, 110, 67, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Lugar Secuestro");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(29, 140, 67, 14);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Ultima Vez Visto");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setBounds(29, 170, 67, 14);
		add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Biografia");
		lblNewLabel_1_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_3.setBounds(29, 200, 67, 14);
		add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Material");
		lblNewLabel_1_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_4.setBounds(29, 230, 67, 14);
		add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("CCDTyEs");
		lblNewLabel_1_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_5.setBounds(29, 260, 67, 14);
		add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("DNI Testigo");
		lblNewLabel_1_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_6.setBounds(29, 290, 67, 14);
		add(lblNewLabel_1_6);

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Desaparecidos Identificados");
		lblNewJgoodiesTitle.setForeground(new Color(255, 255, 255));
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewJgoodiesTitle.setBounds(21, 11, 346, 58);
		add(lblNewJgoodiesTitle);

		JButton btnModificar = new JButton("modificar");

		btnModificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Identificado_DAO idao = new Identificado_DAO();
				Testigo testigo = tdao.selectTestigo(
						tdao.selectIdTestigo(Integer.valueOf(combobox_dnitestigo.getSelectedItem().toString())));

				String profesion = idao
						.selectProfesion(idao.selectIdProfesion(combobox_profesion.getSelectedItem().toString()));

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate a = LocalDate.parse(textField_ultimaVezVisto.getText(), formatter);

				List<String> selectedValuesList = list_ccdtyes.getSelectedValuesList();
				ArrayList<CCDTyE> centrosAsociados = new ArrayList<>();

				for (String nombreCentro : selectedValuesList) {
					CCDTyE c = new CCDTyE(nombreCentro);
					centrosAsociados.add(c);
				}

				Identificado identificado = new Identificado(textField_nombre.getText(),
						Integer.parseInt(textField_dni.getText()), textField_LugarSecuestro.getText(), a,
						textField_biografia.getText(), textField_material.getText(), centrosAsociados, testigo,
						profesion);

				idao.updateIdentificado(identificado, identificado.getDNI(), testigo.getDNI());

			}

		});
		btnModificar.setBounds(106, 388, 89, 23);
		add(btnModificar);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setContentPane(new identificadoMenu());
				j.setBounds(150, 150, 800, 800);
				j.setVisible(true);
				j.validate();

			}
		});
		btnNewButton.setBounds(106, 447, 89, 23);
		add(btnNewButton);

	}
}
