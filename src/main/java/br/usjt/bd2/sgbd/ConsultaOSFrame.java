package br.usjt.bd2.sgbd;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ConsultaOSFrame extends JInternalFrame {
	private static final long serialVersionUID = -4559079897181156640L;
	private JPanel jContentPane = null;
	private JPanel pControls = null;
	private JLabel lOs = null;
	private JTextField tfOs = null;
	private JPanel jPanel = null;
	private JButton bConsulta = null;
	private JSplitPane spConsultas = null;
	private JScrollPane spPecas = null;
	private JTable tPecas = null;
	private JScrollPane spServicos = null;
	private JTable tServicos = null;
	private JPanel pStatusBar = null;
	private JLabel lStatusBar = null;
	private JPanel pData = null;
	private JPanel pOs = null;
	private JLabel lNumOs = null;
	private JLabel lNumOsData = null;
	private JLabel lDataOs = null;
	private JLabel lDataOsData = null;
	private JLabel lCodigoEquip = null;
	private JLabel lCodigoEquipData = null;
	private JLabel lDescrEquip = null;
	private JLabel lDescrEquipData = null;
	private JLabel lNomeCliente = null;
	private JLabel lNomeClienteData = null;

	/**
	 * This is the default constructor
	 */
	public ConsultaOSFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Consulta de OS");
		this.setSize(600, 400);
		this.setMaximizable(true);
		this.setClosable(true);
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
			jContentPane.add(getPData(), java.awt.BorderLayout.CENTER);
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
			lOs = new JLabel();
			lOs.setText("Número da OS: ");
			pControls = new JPanel();
			pControls.add(lOs, null);
			pControls.add(getTfOs(), null);
			pControls.add(getJPanel(), null);
			pControls.add(getBConsulta(), null);
		}
		return pControls;
	}

	/**
	 * This method initializes tfOs	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfOs() {
		if (tfOs == null) {
			tfOs = new JTextField();
			tfOs.setColumns(10);
			tfOs.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					lStatusBar.setText("Digite o número da OS");
				}
			});
		}
		return tfOs;
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
	 * This method initializes bConsulta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBConsulta() {
		if (bConsulta == null) {
			bConsulta = new JButton();
			bConsulta.setText("Consultar");
			bConsulta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Integer numOs = new Integer(Integer.parseInt(tfOs.getText()));
					ResultadoOs os = Main.sgbd.consultaOs(numOs,tPecas,tServicos,lStatusBar);
					lNumOsData.setText(os.getNumeroOs().toString());
					lDataOsData.setText(os.getDataOs().toString());
					lNomeClienteData.setText(os.getNomeCliente());
					lCodigoEquipData.setText(os.getCodEquip());
					lDescrEquipData.setText(os.getDescrEquip());
					pData.setVisible(true);
				}
			});
		}
		return bConsulta;
	}

	/**
	 * This method initializes spConsultas	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getSpConsultas() {
		if (spConsultas == null) {
			spConsultas = new JSplitPane();
			spConsultas.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
			spConsultas.setDividerLocation(150);
			spConsultas.setTopComponent(getSpPecas());
			spConsultas.setBottomComponent(getSpServicos());
			spConsultas.setOneTouchExpandable(true);
			spConsultas.setVisible(true);
		}
		return spConsultas;
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
	 * This method initializes spServicos	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpServicos() {
		if (spServicos == null) {
			spServicos = new JScrollPane();
			spServicos.setViewportView(getTServicos());
		}
		return spServicos;
	}

	/**
	 * This method initializes tServicos	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTServicos() {
		if (tServicos == null) {
			tServicos = new JTable();
		}
		return tServicos;
	}

	/**
	 * This method initializes pStatusBar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPStatusBar() {
		if (pStatusBar == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 10.0D;
			gridBagConstraints.gridx = 0;
			lStatusBar = new JLabel();
			lStatusBar.setText(" ");
			pStatusBar = new JPanel();
			pStatusBar.setLayout(new GridBagLayout());
			pStatusBar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
			pStatusBar.add(lStatusBar, gridBagConstraints);
		}
		return pStatusBar;
	}

	/**
	 * This method initializes pData	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPData() {
		if (pData == null) {
			pData = new JPanel();
			pData.setLayout(new BorderLayout());
			pData.setVisible(false);
			pData.add(getPOs(), java.awt.BorderLayout.NORTH);
			pData.add(getSpConsultas(), java.awt.BorderLayout.CENTER);
		}
		return pData;
	}

	/**
	 * This method initializes pOs	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPOs() {
		if (pOs == null) {
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints10.insets = new java.awt.Insets(2,2,2,2);
			gridBagConstraints10.gridy = 2;
			lNomeClienteData = new JLabel();
			lNomeClienteData.setText(" ");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints9.insets = new java.awt.Insets(2,2,2,2);
			gridBagConstraints9.gridy = 2;
			lNomeCliente = new JLabel();
			lNomeCliente.setText("Nome Cliente: ");
			lNomeCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
			lNomeCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 3;
			gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints8.insets = new java.awt.Insets(2,2,2,2);
			gridBagConstraints8.gridy = 1;
			lDescrEquipData = new JLabel();
			lDescrEquipData.setText(" ");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 2;
			gridBagConstraints7.gridy = 1;
			lDescrEquip = new JLabel();
			lDescrEquip.setText("Descrição Equipamento: ");
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints6.insets = new java.awt.Insets(2,2,2,2);
			gridBagConstraints6.gridy = 1;
			lCodigoEquipData = new JLabel();
			lCodigoEquipData.setText(" ");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.insets = new java.awt.Insets(2,2,2,2);
			gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 1;
			lCodigoEquip = new JLabel();
			lCodigoEquip.setText("Código Equipamento: ");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 3;
			gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints4.insets = new java.awt.Insets(2,2,2,2);
			gridBagConstraints4.weightx = 2.0D;
			gridBagConstraints4.gridy = 0;
			lDataOsData = new JLabel();
			lDataOsData.setText(" ");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new java.awt.Insets(2,2,2,2);
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 0.0D;
			gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints3.gridx = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new java.awt.Insets(2,2,2,2);
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 2.0D;
			gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints2.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new java.awt.Insets(2,2,2,2);
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints1.gridx = 0;
			lDataOs = new JLabel();
			lDataOs.setText("Data: ");
			lDataOs.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			lDataOs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
			lNumOsData = new JLabel();
			lNumOsData.setText(" ");
			lNumOs = new JLabel();
			lNumOs.setText("Número da OS: ");
			lNumOs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
			lNumOs.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			pOs = new JPanel();
			pOs.setLayout(new GridBagLayout());
			pOs.add(lNumOs, gridBagConstraints1);
			pOs.add(lNumOsData, gridBagConstraints2);
			pOs.add(lDataOs, gridBagConstraints3);
			pOs.add(lDataOsData, gridBagConstraints4);
			pOs.add(lCodigoEquip, gridBagConstraints5);
			pOs.add(lCodigoEquipData, gridBagConstraints6);
			pOs.add(lDescrEquip, gridBagConstraints7);
			pOs.add(lDescrEquipData, gridBagConstraints8);
			pOs.add(lNomeCliente, gridBagConstraints9);
			pOs.add(lNomeClienteData, gridBagConstraints10);
		}
		return pOs;
	}

}
