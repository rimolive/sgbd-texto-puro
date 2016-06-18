package br.usjt.bd2.sgbd;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class ConsultaClienteFrame extends JInternalFrame {
	private static final long serialVersionUID = 6393189751505874472L;
	private JPanel jContentPane = null;
	private JPanel pControls = null;
	private JLabel lCliente = null;
	private JTextField tfCliente = null;
	private JButton bConsultar = null;
	private JScrollPane spClientes = null;
	private JTable tClientes = null;
	private JPanel pStatusBar = null;
	private JPanel pStatus = null;
	private JLabel lStatusBar = null;
	private JPanel jPanel = null;

	/**
	 * This is the default constructor
	 */
	public ConsultaClienteFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(600, 400);
		this.setTitle("Consulta de Clientes");
		this.setClosable(true);
		this.setMaximizable(true);
		this.setVisible(true);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPControls(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getSpClientes(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getPStatusBar(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pControls	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPControls() {
		if (pControls == null) {
			lCliente = new JLabel();
			lCliente.setText("Cliente: ");
			pControls = new JPanel();
			pControls.add(lCliente, null);
			pControls.add(getTfCliente(), null);
			pControls.add(getJPanel(), null);
			pControls.add(getBConsultar(), null);
		}
		return pControls;
	}

	/**
	 * This method initializes tfCliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfCliente() {
		if (tfCliente == null) {
			tfCliente = new JTextField();
			tfCliente.setColumns(10);
			tfCliente.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					lStatusBar.setText("Digite o código do cliente");
				}
			});
		}
		return tfCliente;
	}

	/**
	 * This method initializes bConsultar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBConsultar() {
		if (bConsultar == null) {
			bConsultar = new JButton();
			bConsultar.setText("Consultar");
			bConsultar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Integer idCliente = new Integer(Integer.parseInt(tfCliente.getText()));
					TableModel model = Main.sgbd.consultaCliente(idCliente, lStatusBar);
					tClientes.setModel(model);
				}
			});
		}
		return bConsultar;
	}

	/**
	 * This method initializes spClientes	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpClientes() {
		if (spClientes == null) {
			spClientes = new JScrollPane();
			spClientes.setViewportView(getTClientes());
		}
		return spClientes;
	}

	/**
	 * This method initializes tClientes	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTClientes() {
		if (tClientes == null) {
			tClientes = new JTable();
		}
		return tClientes;
	}

	/**
	 * This method initializes pStatusBar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPStatusBar() {
		if (pStatusBar == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new java.awt.Insets(0,0,0,0);
			gridBagConstraints.gridy = 0;
			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints.weightx = 10.0D;
			gridBagConstraints.gridx = 0;
			pStatusBar = new JPanel();
			pStatusBar.setLayout(new GridBagLayout());
			pStatusBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			pStatusBar.add(getPStatus(), gridBagConstraints);
		}
		return pStatusBar;
	}

	/**
	 * This method initializes pStatus	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPStatus() {
		if (pStatus == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints1.weightx = 10.0D;
			gridBagConstraints1.gridy = 0;
			lStatusBar = new JLabel();
			lStatusBar.setText(" ");
			pStatus = new JPanel();
			pStatus.setLayout(new GridBagLayout());
			pStatus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			pStatus.add(lStatusBar, gridBagConstraints1);
		}
		return pStatus;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setPreferredSize(new java.awt.Dimension(300,10));
		}
		return jPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="79,8"
