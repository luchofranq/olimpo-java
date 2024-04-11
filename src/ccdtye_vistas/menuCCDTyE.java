package ccdtye_vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import olimpo.CCDTyE;
import olimpo_DAO.CCDTyE_DAO;
import olimpo_vistas_principal.panelPrincipal;

@SuppressWarnings("serial")
public class menuCCDTyE extends JPanel {
	private JTable table;
	private ArrayList<CCDTyE> centros = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public menuCCDTyE() {

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				j.setContentPane(new panelPrincipal());
				j.setBounds(150, 150, 800, 800);
				j.setVisible(true);
				j.validate();

			}
		});
		btnVolver.setBounds(522, 235, 89, 23);
		add(btnVolver);
		setBackground(new Color(24, 61, 61));
		setLayout(null);

		JLabel errorFila = new JLabel("No seleccion\u00F3 una fila!");
		errorFila.setBackground(new Color(0, 0, 0));
		errorFila.setForeground(new Color(255, 255, 255));
		errorFila.setBounds(496, 79, 198, 14);
		errorFila.setVisible(false);
		add(errorFila);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 14, 425, 329);
		add(scrollPane);

		CCDTyE_DAO p = new CCDTyE_DAO();
		table = new JTable();

		scrollPane.setViewportView(table);
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.fireTableDataChanged();
		modelo.addColumn("Nombre");
		modelo.addColumn("Ubicacion");
		modelo.addColumn("Fecha Inicio");
		modelo.addColumn("Fecha Cierre");
		modelo.addColumn("Responsable");

		table.setModel(modelo);
		Object[] fila = new Object[modelo.getColumnCount()];

		centros = p.selectAllCCDTyEs();

		JButton btnNuevo = new JButton("Mostrar Todos");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.validate();
				for (CCDTyE p : centros) {
					fila[0] = p.getNombre();
					fila[1] = p.getUbicacion();
					fila[2] = p.getFechaInicio();
					fila[3] = p.getFechaCierre();
					String fuerzas = "";
					for (String fuerza : p.getFuerzas()) {
						fuerzas += fuerza + " ";
					}
					fila[4] = fuerzas;
					modelo.addRow(fila);
					table.validate();
				}

				table.validate();
			}
		});
		btnNuevo.setBounds(472, 11, 174, 23);
		add(btnNuevo);
		CCDTyE_DAO dao = new CCDTyE_DAO();
		JButton btnNewButton = new JButton("Eliminar");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() >= 0) {
					int fila = table.getSelectedRow();
					CCDTyE ccdTyE = centros.get(fila);
					dao.deleteCCDTyE(ccdTyE);
				} else {
					errorFila.setVisible(true);
				}

			}
		});
		btnNewButton.setBounds(472, 45, 174, 23);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Agregar CCDTyE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				panelCCDTyE b = new panelCCDTyE();
				j.setContentPane(b);
				b.AgregarCCDTyE();
				j.setBounds(0, 0, 500, 500);
				j.setVisible(true);
				j.validate();

				table.validate();
			}
		});
		btnNewButton_1.setBounds(37, 354, 180, 23);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Actualizar CCDTyE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					int fila = table.getSelectedRow();
					CCDTyE ccdTyE = centros.get(fila);
					String nombre = ccdTyE.getNombre();
					JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					panelCCDTyE b = new panelCCDTyE();
					j.setContentPane(b);
					b.ActualizarCCDTyE(ccdTyE, nombre);
					j.setBounds(0, 0, 500, 500);
					j.setVisible(true);
					j.validate();

					table.validate();

				} else {
					errorFila.setVisible(true);
				}
			}
		});
		btnNewButton_2.setBounds(282, 354, 180, 23);
		add(btnNewButton_2);
	}
}
