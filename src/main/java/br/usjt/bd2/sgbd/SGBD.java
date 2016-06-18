/*
 * Created on 20/09/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.usjt.bd2.sgbd;

import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.usjt.bd2.sgbd.bean.ClienteBean;
import br.usjt.bd2.sgbd.bean.EquipamentoBean;
import br.usjt.bd2.sgbd.bean.OsBean;
import br.usjt.bd2.sgbd.bean.PecaBean;
import br.usjt.bd2.sgbd.bean.PecaOsBean;
import br.usjt.bd2.sgbd.bean.ServicoBean;
import br.usjt.bd2.sgbd.to.IndexLoader;

public class SGBD {
	private static IndexLoader index = null;

	public SGBD() {
		index = new IndexLoader();
	}

	public TableModel consultaCliente(Integer idCliente, JLabel status) {
		DefaultTableModel tableModel = new DefaultTableModel();
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Nome Cliente");
		columnNames.add("Descrição Equipamento");
		columnNames.add("Número OS");
		columnNames.add("Data OS");
		columnNames.add("Valor Total");
		tableModel.setColumnIdentifiers(columnNames);
		TreeSet<ResultadoCliente> ordem = new TreeSet<ResultadoCliente>();
		long inicioCliente = System.currentTimeMillis();
		ClienteBean cliente = (ClienteBean) index.mapCliente.get(idCliente);
		if(cliente != null) {
			for (EquipamentoBean equipamento : cliente.getListaEquipamentoBean()) {
				for (OsBean os : equipamento.getListaOs()) {
					ResultadoCliente resultadoCliente = new ResultadoCliente();
					resultadoCliente.setDataOs(os.getData());
					resultadoCliente.setDescEquip(equipamento.getDescricao());
					resultadoCliente.setNomeCliente(cliente.getNome());
					resultadoCliente.setNumeroOs(os.getNumero());
					resultadoCliente.setValorTotal(os.getValorTotal());
					ordem.add(resultadoCliente);
				}
			}
			for (ResultadoCliente resultadoCliente : ordem) {
				Vector<Object> columnData = new Vector<Object>();
				columnData.add(resultadoCliente.getNomeCliente());
				columnData.add(resultadoCliente.getDescEquip());
				columnData.add(resultadoCliente.getNumeroOs());
				columnData.add(resultadoCliente.getDataOs());
				columnData.add(resultadoCliente.getValorTotal());
				tableModel.addRow(columnData);
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}

			status.setText("Tempo de execução: " + (System.currentTimeMillis() - inicioCliente) + "ms");
			return tableModel;
		}
		JOptionPane.showMessageDialog(null,"Não foi encontrado nenhum cliente");
		return null;		
	}

	public TableModel consultaPeca(String idPeca, JLabel status) {
		DefaultTableModel tableModel = new DefaultTableModel();
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Descrição Peça");
		columnNames.add("Código do Equipamento");
		columnNames.add("Descrição Equipamento");
		columnNames.add("Número OS");
		tableModel.setColumnIdentifiers(columnNames);
		TreeSet<ResultadoEquipamento> orderByEquipamento = new TreeSet<ResultadoEquipamento>();
		long inicioPeca = System.currentTimeMillis();
		PecaBean peca = index.mapPeca.get(idPeca);
		if(peca != null) {
			for (PecaOsBean pecaOs : peca.getPecaOS()) {
				OsBean os = pecaOs.getOs();
				EquipamentoBean equipamento = os.getEquipamento();
				ResultadoEquipamento resultadoEquipamento = new ResultadoEquipamento();
				resultadoEquipamento.setDescricaoPeca(peca.getDescricao());
				resultadoEquipamento.setNumeroOS(os.getNumero());
				resultadoEquipamento.setNumSerieEquip(equipamento.getCodigo());
				resultadoEquipamento.setDescricaoEquipamento(equipamento.getDescricao());
				orderByEquipamento.add(resultadoEquipamento);
			}
			for (ResultadoEquipamento resultadoEquipamento : orderByEquipamento) {
				Vector<Object> columnData = new Vector<Object>();
				columnData.add(resultadoEquipamento.getDescricaoPeca());
				columnData.add(resultadoEquipamento.getNumSerieEquip());
				columnData.add(resultadoEquipamento.getDescricaoEquipamento());
				columnData.add(resultadoEquipamento.getNumeroOS());
				tableModel.addRow(columnData);
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
			status.setText("Tempo de execução: " + (System.currentTimeMillis() - inicioPeca) + "ms");
			return tableModel;	
		}
		JOptionPane.showMessageDialog(null,"Peça não encontrada");
		return null;
	}

	public ResultadoOs consultaOs(Integer idOs, JTable tPecas, JTable tServicos, JLabel status) {
		DefaultTableModel pecaTableModel = new DefaultTableModel();
		Vector<String> pecaColumnNames = new Vector<String>();
		pecaColumnNames.add("Descrição da Peça");
		pecaColumnNames.add("Quantidade");
		pecaTableModel.setColumnIdentifiers(pecaColumnNames); 
		long inicioOs = System.currentTimeMillis();
		OsBean os = index.mapOs.get(idOs);
		if(os != null) {
			ResultadoOs resultadoOs = new ResultadoOs();
			resultadoOs.setNumeroOs(os.getNumero());
			resultadoOs.setDataOs(os.getData());
			resultadoOs.setNomeCliente(os.getEquipamento().getCliente().getNome());
			resultadoOs.setCodEquip(os.getEquipamento().getCodigo());
			resultadoOs.setDescrEquip(os.getEquipamento().getDescricao());
			for (PecaOsBean pecaOsBean : os.getListaPecaOs()) {
				PecaBean pecaBean = pecaOsBean.getPeca();
				Vector<Object> pecaColumnData = new Vector<Object>();
				pecaColumnData.add(pecaBean.getDescricao());
				pecaColumnData.add(pecaOsBean.getQuantidade());
				pecaTableModel.addRow(pecaColumnData);
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
			tPecas.setModel(pecaTableModel);
			DefaultTableModel servicoTableModel = new DefaultTableModel();
			Vector<String> servicoColumnNames = new Vector<String>();
			servicoColumnNames.add("Item");
			servicoColumnNames.add("Descrição do Serviço");
			servicoTableModel.setColumnIdentifiers(servicoColumnNames);
			for (ServicoBean servicoBean : os.getListaServico()) {
				Vector<Object> servicoColumnData = new Vector<Object>();
				servicoColumnData.add(servicoBean.getSeqItem());
				servicoColumnData.add(servicoBean.getDescricao());
				servicoTableModel.addRow(servicoColumnData);
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}			
			}
			tServicos.setModel(servicoTableModel);
			status.setText("Tempo de Execução: " + (System.currentTimeMillis() - inicioOs) + "ms");
			return resultadoOs;			
		}
		JOptionPane.showMessageDialog(null,"OS não encontrada");
		return null;
	}
}
