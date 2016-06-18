package br.usjt.bd2.sgbd.to;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.usjt.bd2.sgbd.bean.ClienteBean;
import br.usjt.bd2.sgbd.bean.EquipamentoBean;
import br.usjt.bd2.sgbd.bean.OsBean;
import br.usjt.bd2.sgbd.bean.PecaBean;
import br.usjt.bd2.sgbd.bean.PecaOsBean;
import br.usjt.bd2.sgbd.bean.ServicoBean;
import br.usjt.bd2.sgbd.xml.GerenciadorArquivoCobol;

public class IndexLoader {
	private static final String BASE_OUTPUT_DIR = IndexLoader.class.getResource("/data/").getPath();
	private static final String BASE_INPUT_DIR = IndexLoader.class.getResource("/xml/").getPath();
	public Map<Integer,OsBean> mapOs = new HashMap<Integer,OsBean>();
	public Map<Integer,ClienteBean> mapCliente = new HashMap<Integer,ClienteBean>();
	public Map<String,PecaBean> mapPeca = new HashMap<String,PecaBean>();
	public Map<String,EquipamentoBean> mapEquipamento = new HashMap<String,EquipamentoBean>();
	
	@SuppressWarnings("unchecked")
	public IndexLoader() {
		try{
			GerenciadorArquivoCobol gerenciadorArquivoCobol = new GerenciadorArquivoCobol();
			// Vari�veis de sa�da de dados (Arquivos TXT)
			FileInputStream clienteInput = new FileInputStream( BASE_OUTPUT_DIR + "cliente.txt");
			FileInputStream equipamentoInput = new FileInputStream( BASE_OUTPUT_DIR + "equipamento.txt");
			FileInputStream pecaInput = new FileInputStream( BASE_OUTPUT_DIR + "peca.txt");
			FileInputStream osInput = new FileInputStream( BASE_OUTPUT_DIR + "os.txt");
			FileInputStream pecaOsInput = new FileInputStream( BASE_OUTPUT_DIR + "os_peca.txt");
			FileInputStream servicoInput = new FileInputStream( BASE_OUTPUT_DIR + "servico.txt");

			// Vari�veis de entrada de dados (Descritores XML)
			FileInputStream clienteXml = new FileInputStream( BASE_INPUT_DIR + "cliente.xml");
			FileInputStream equipamentoXml = new FileInputStream( BASE_INPUT_DIR + "equipamento.xml");
			FileInputStream pecaXml = new FileInputStream( BASE_INPUT_DIR + "peca.xml");
			FileInputStream osXml = new FileInputStream( BASE_INPUT_DIR + "os.xml");
			FileInputStream pecaOsXml = new FileInputStream( BASE_INPUT_DIR + "os_peca.xml");
			FileInputStream servicoXml = new FileInputStream( BASE_INPUT_DIR + "servico.xml");
			
			List<ClienteTO> listaClientes = (List<ClienteTO>) gerenciadorArquivoCobol.converteArquivoParaBean(clienteInput, clienteXml);
			List<EquipamentoTO> listaEquipamentos = (List<EquipamentoTO>) gerenciadorArquivoCobol.converteArquivoParaBean(equipamentoInput, equipamentoXml);
			List<PecaTO> listaPecas = (List<PecaTO>) gerenciadorArquivoCobol.converteArquivoParaBean(pecaInput, pecaXml);
			List<OsTO> listaOSs = (List<OsTO>) gerenciadorArquivoCobol.converteArquivoParaBean(osInput, osXml);
			List<PecaOsTO> listaPecasOs = (List<PecaOsTO>) gerenciadorArquivoCobol.converteArquivoParaBean(pecaOsInput, pecaOsXml);
			List<ServicoTO> listaServicos = (List<ServicoTO>) gerenciadorArquivoCobol.converteArquivoParaBean(servicoInput, servicoXml);
			
			for(ClienteTO clienteTO: listaClientes) {
				// Cria um Bean para o cliente
				ClienteBean clienteBean = new ClienteBean();
				clienteBean.setCodigo(clienteTO.getCodigo());
				clienteBean.setNome(clienteTO.getNome());
				clienteBean.setTelefone(clienteTO.getTelefone());
				clienteBean.setDataUltimoContato(clienteTO.getDataUltimoContato());
				// Armazena no �ndice o registro
				mapCliente.put(clienteBean.getCodigo(),clienteBean);
			}
			
			for(EquipamentoTO equipamentoTO: listaEquipamentos) {
				EquipamentoBean equipamentoBean = new EquipamentoBean();
				equipamentoBean.setCodigo(equipamentoTO.getCodigo());
				equipamentoBean.setDescricao(equipamentoTO.getDescricao());
				equipamentoBean.setDataGarantia(equipamentoTO.getDataGarantia());
				
				ClienteBean clienteBean = mapCliente.get(equipamentoTO.getClienteCodigo());
				clienteBean.addEquipamentoBean(equipamentoBean);
				equipamentoBean.setCliente(clienteBean);
				
				mapEquipamento.put(equipamentoBean.getCodigo(),equipamentoBean);
			}

			for(OsTO osTO: listaOSs) {
				OsBean osBean = new OsBean();
				osBean.setNumero(osTO.getNumero());
				osBean.setData(osTO.getData());
				
				EquipamentoBean equipamentoBean = mapEquipamento.get(osTO.getEquipamentoCodigo());
				equipamentoBean.addOS(osBean);
				osBean.setEquipamento(equipamentoBean);
				
				osBean.setValorTotal(osTO.getValorTotal());
				mapOs.put(osBean.getNumero(),osBean);
			}
			
			for(PecaTO pecaTO: listaPecas) {
				PecaBean pecaBean = new PecaBean();
				pecaBean.setCodigo(pecaTO.getCodigo());
				pecaBean.setDescricao(pecaTO.getDescricao());
				pecaBean.setPrecoUnitario(pecaTO.getPrecoUnitario());
				
				mapPeca.put(pecaBean.getCodigo(),pecaBean);
			}
			
			for(PecaOsTO pecaOsTO: listaPecasOs) {
				PecaOsBean pecaOsBean = new PecaOsBean();
				
				OsBean osBean = mapOs.get(pecaOsTO.getNumeroOS());
				osBean.addPecaOs(pecaOsBean);
				pecaOsBean.setOs(osBean);	
				
				PecaBean pecaBean = mapPeca.get(pecaOsTO.getCodigoPeca());
				pecaBean.addPecaOs(pecaOsBean);
				pecaOsBean.setPeca(pecaBean);
				
				pecaOsBean.setQuantidade(pecaOsTO.getQuantidade());
			}
			
			for(ServicoTO servicoTO: listaServicos) {
				ServicoBean servicoBean = new ServicoBean();
				servicoBean.setSeqItem(servicoTO.getSeqItem());
				servicoBean.setDescricao(servicoTO.getDescricao());
				
				OsBean osBean = mapOs.get(servicoTO.getNumeroOS());
				osBean.addServico(servicoBean);
				servicoBean.setOsBean(osBean);	
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
