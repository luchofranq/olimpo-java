package no_identificado_vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import olimpo.NoIdentificado;
import olimpo.Testigo;
import olimpo_DAO.CCDTyE_DAO;
import olimpo_DAO.NoIdentificado_DAO;
import olimpo_DAO.Testigo_DAO;

@SuppressWarnings("serial")
public class no_identificadoPanel extends JPanel {

	public no_identificadoPanel() {
	}

	Testigo_DAO tdao = new Testigo_DAO();
	CCDTyE_DAO cdao = new CCDTyE_DAO();

	JTextField textField_apodo;
	JTextField textField_descripciones;
	@SuppressWarnings("rawtypes")
	JList list_ccdtyes;
	@SuppressWarnings("rawtypes")
	JComboBox combobox_dnitestigo;

	@SuppressWarnings("unchecked")
	public void agregarNoIdentificado() {

		setBackground(new Color(24, 61, 61));
		setLayout(null);

		textField_descripciones = new JTextField();
		textField_descripciones.setBounds(106, 110, 86, 20);
		add(textField_descripciones);
		textField_descripciones.setColumns(10);

		textField_apodo = new JTextField();
		textField_apodo.setBounds(106, 80, 86, 20);
		add(textField_apodo);
		textField_apodo.setColumns(10);

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
		combobox_dnitestigo.setBounds(106, 140, 86, 20);

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

		JButton guardar = new JButton("Agregar");

		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NoIdentificado_DAO nidao = new NoIdentificado_DAO();
				Testigo testigo = tdao.selectTestigo(
						tdao.selectIdTestigo(Integer.valueOf(combobox_dnitestigo.getSelectedItem().toString())));

				List<String> selectedValuesList = list_ccdtyes.getSelectedValuesList();
				ArrayList<CCDTyE> centrosAsociados = new ArrayList<>();

				for (String nombreCentro : selectedValuesList) {
					CCDTyE c = new CCDTyE(nombreCentro);
					centrosAsociados.add(c);
				}

				NoIdentificado noidentificado = new NoIdentificado(textField_apodo.getText(),
						textField_descripciones.getText(), centrosAsociados, testigo);

				nidao.insertNoIdentificado(noidentificado, testigo.getDNI());

			}
		});
		guardar.setBounds(103, 354, 89, 23);

		add(guardar);

		JLabel lblNewLabel = new JLabel("Apodo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(29, 80, 46, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Descripciones");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(29, 110, 67, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_5 = new JLabel("CCDTyEs");
		lblNewLabel_1_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_5.setBounds(200, 80, 67, 14);
		add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("DNI Testigo");
		lblNewLabel_1_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_6.setBounds(29, 140, 67, 14);
		add(lblNewLabel_1_6);

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance()
				.createTitle("Desaparecidos No Identificados");
		lblNewJgoodiesTitle.setForeground(new Color(255, 255, 255));
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewJgoodiesTitle.setBounds(21, 11, 346, 58);
		add(lblNewJgoodiesTitle);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setContentPane(new no_identificadoMenu());
				j.setBounds(0, 0, 1200, 1200);
				j.setVisible(true);
				j.validate();

			}
		});
		btnVolver.setBounds(103, 404, 89, 23);
		add(btnVolver);

	}

	@SuppressWarnings("unchecked")
	public void actualizarNoIdentificado(NoIdentificado noidentificadoaux, String apodo) {
		setBackground(new Color(24, 61, 61));
		setLayout(null);

		textField_apodo = new JTextField();
		textField_apodo.setText(noidentificadoaux.getApodo());
		textField_apodo.setBounds(106, 80, 86, 20);
		add(textField_apodo);
		textField_apodo.setColumns(10);

		textField_descripciones = new JTextField();
		textField_descripciones.setText(noidentificadoaux.getDescripciones());
		textField_descripciones.setBounds(106, 110, 86, 20);
		add(textField_descripciones);
		textField_descripciones.setColumns(10);

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
		combobox_dnitestigo.setBounds(106, 140, 86, 20);

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

		JLabel lblNewLabel = new JLabel("Apodo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(29, 80, 46, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Descripciones");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(29, 110, 67, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_5 = new JLabel("CCDTyEs");
		lblNewLabel_1_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_5.setBounds(200, 80, 67, 14);
		add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("DNI Testigo");
		lblNewLabel_1_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_6.setBounds(29, 140, 67, 14);
		add(lblNewLabel_1_6);

		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance()
				.createTitle("Desaparecidos No Identificados");
		lblNewJgoodiesTitle.setForeground(new Color(255, 255, 255));
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblNewJgoodiesTitle.setBounds(21, 11, 346, 58);
		add(lblNewJgoodiesTitle);

		JButton btnModificar = new JButton("modificar");

		btnModificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				NoIdentificado_DAO nidao = new NoIdentificado_DAO();
				Testigo testigo = tdao.selectTestigo(
						tdao.selectIdTestigo(Integer.valueOf(combobox_dnitestigo.getSelectedItem().toString())));

				List<String> selectedValuesList = list_ccdtyes.getSelectedValuesList();
				ArrayList<CCDTyE> centrosAsociados = new ArrayList<>();

				for (String nombreCentro : selectedValuesList) {
					CCDTyE c = new CCDTyE(nombreCentro);
					centrosAsociados.add(c);
				}

				NoIdentificado identificado = new NoIdentificado(textField_apodo.getText(),
						textField_descripciones.getText(), centrosAsociados, testigo);

				nidao.updateNoIdentificado(identificado, identificado.getApodo(), testigo.getDNI());

			}

		});
		btnModificar.setBounds(106, 388, 89, 23);
		add(btnModificar);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setContentPane(new no_identificadoMenu());
				j.setBounds(150, 150, 800, 800);
				j.setVisible(true);
				j.validate();

			}
		});
		btnNewButton.setBounds(106, 447, 89, 23);
		add(btnNewButton);

	}

}
