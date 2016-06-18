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

public class ConsultaPecaFrame extends JInternalFrame {
	private static final long serialVersionUID = 6377027274824983413L;
	private JPanel jContentPane = null;
	private JPanel pControls = null;
	private JLabel lPeca = null;
	private JTextField tfPeca = null;
	private JPanel jPanel = null;
	private JButton bConsultar = null;
	private JScrollPane spPecas = null;
	private JTable tPecas = null;
	private JPanel pStatusBar = null;
	private JLabel lStatusBar = null;

	/**
	 * This is the default constructor
	 */
	public ConsultaPecaFrame() {
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
		this.setMaximizable(true);
		this.setClosable(true);
		this.setResizable(true);
		this.setTitle("Consulta de Peças");
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
			jContentPane.add(getSpPecas(), java.awt.BorderLayout.CENTER);
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
			lPeca = new JLabel();
			lPeca.setText("Código da Peça");
			pControls = new JPanel();
			pControls.add(lPeca, null);
			pControls.add(getTdPeca(), null);
			pControls.add(getJPanel(), null);
			pControls.add(getBConsultar(), null);
		}
		return pControls;
	}

	/**
	 * This method initializes tdPeca	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTdPeca() {
		if (tfPeca == null) {
			tfPeca = new JTextField();
			tfPeca.setColumns(10);
			tfPeca.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					lStatusBar.setText("Digite o número da peça");
				}
			});
		}
		return tfPeca;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setPreferredSize(new java.awt.Dimension(200,10));
		}
		return jPanel;
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
					TableModel model = Main.sgbd.consultaPeca(tfPeca.getText(),lStatusBar);
					tPecas.setModel(model);
				}
			});
		}
		return bConsultar;
	}

	/**
	 * This method initializes spPecas	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpPecas() {
		if (spPecas == null) {
			spPecas = new JScrollPane();
			spPecas.setViewportView(getTPecas());
		}
		return spPecas;
	}

	/**
	 * This method initializes tPecas	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTPecas() {
		if (tPecas == null) {
			tPecas = new JTable();
		}
		return tPecas;
	}

	/**
	 * This method initializes pStatusBar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPStatusBar() {
		if (pStatusBar == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.weightx = 10.0D;
			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			lStatusBar = new JLabel();
			lStatusBar.setText(" ");
			pStatusBar = new JPanel();
			pStatusBar.setLayout(new GridBagLayout());
			pStatusBar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
			pStatusBar.add(lStatusBar, gridBagConstraints);
		}
		return pStatusBar;
	}

}
