package testigo_vistas;

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

import olimpo.Testigo;
import olimpo_DAO.Testigo_DAO;
import olimpo_vistas_principal.panelPrincipal;

@SuppressWarnings("serial")
public class menuTestigo extends JPanel {
	private ArrayList<Testigo> testigos;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public menuTestigo() {
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

		Testigo_DAO p = new Testigo_DAO();
		table = new JTable();

		scrollPane.setViewportView(table);
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.fireTableDataChanged();
		modelo.addColumn("Nombre Completo");
		modelo.addColumn("DNI");
		modelo.addColumn("Testimonio");

		table.setModel(modelo);
		Object[] fila = new Object[modelo.getColumnCount()];

		testigos = p.selectAllTestigos();
		JButton btnNuevo = new JButton("Mostrar Todos");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.validate();
				for (Testigo p : testigos) {
					fila[0] = p.getNombreCompleto();
					fila[1] = p.getDNI();
					fila[2] = p.getTestimonio();
					modelo.addRow(fila);
					table.validate();
				}

				table.validate();
			}
		});
		btnNuevo.setBounds(472, 11, 174, 23);
		add(btnNuevo);

		Testigo_DAO dao = new Testigo_DAO();

		JButton btnNewButton = new JButton("Eliminar");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() >= 0) {
					int fila = table.getSelectedRow();
					Testigo testigo = testigos.get(fila);
					dao.deleteTestigo(testigo);
				} else {
					errorFila.setVisible(true);
				}

			}
		});
		btnNewButton.setBounds(472, 45, 174, 23);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Agregar Testigo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				panelTestigo b = new panelTestigo();
				j.setContentPane(b);

				b.agregarTestigo();

				j.setBounds(0, 0, 500, 500);
				j.setVisible(true);
				j.validate();

				table.validate();
			}
		});
		btnNewButton_1.setBounds(37, 354, 180, 23);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Actualizar Testigo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					int fila = table.getSelectedRow();
					Testigo testigo = testigos.get(fila);
					int dni = testigo.getDNI();
					JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());

					panelTestigo b = new panelTestigo();

					j.setContentPane(b);

					b.actualizarTestigo(testigo, dni);

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
