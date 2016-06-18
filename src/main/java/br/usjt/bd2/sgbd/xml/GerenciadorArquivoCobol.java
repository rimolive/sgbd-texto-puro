/*
 * Created on 26/11/2005
 */
package br.usjt.bd2.sgbd.xml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.xml.sax.SAXException;

import br.usjt.bd2.sgbd.xml.LayoutArquivoExterno.Campo;

/**
 * @author ricardocury
 * Classa que manipula um arquivo Cobol.
 * Esta classe corresponde aos casos de uso: UC08, UC32 e UC33.
 */
public class GerenciadorArquivoCobol {

    private long line = 0;
    private final String COD_REGISTRO_ITEM = "ITEM";
    
    private Map mapLayoutArquivoCobol;
   
    /**
     * Converte um arquivo para uma coleção de objetos
     * @param caminhoCompletoArquivo
     * @param nomeCompletoXmlDescritor
     * @return
     * @throws FileNotFoundException
     * @throws EstruturaException
     * @throws SAXException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public Collection converteArquivoParaBean(String caminhoCompletoArquivo, String nomeCompletoXmlDescritor )
    	throws FileNotFoundException, EstruturaException, SAXException, IOException, ClassNotFoundException, Exception  {
        	return converteArquivoParaBean( new FileInputStream( caminhoCompletoArquivo ), GerenciadorArquivoCobol.class.getClassLoader().getResourceAsStream( nomeCompletoXmlDescritor ) );
    }
    
    /**
     * Converte um arquivo para uma coleção de objetos
     * @param arquivo
     * @param nomeCompletoXmlDescritor
     * @return
     * @throws EstruturaException
     * @throws SAXException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public Collection converteArquivoParaBean(InputStream arquivo, String nomeCompletoXmlDescritor )
    	throws EstruturaException, SAXException, IOException, ClassNotFoundException, Exception  {
        	return converteArquivoParaBean( arquivo, GerenciadorArquivoCobol.class.getClassLoader().getResourceAsStream( nomeCompletoXmlDescritor ) );
    }
    
    /**
     * Converte um arquivo para uma coleção de objetos
     * @param caminhoCompletoArquivo
     * @param xmlDescritor
     * @return
     * @throws FileNotFoundException
     * @throws EstruturaException
     * @throws SAXException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public Collection converteArquivoParaBean(String caminhoCompletoArquivo, InputStream xmlDescritor )
    	throws FileNotFoundException, EstruturaException, SAXException, IOException, ClassNotFoundException, Exception  {
        	return converteArquivoParaBean( new FileInputStream( caminhoCompletoArquivo ), xmlDescritor );
    }
    
    
    /**
     * Cria um Arquivo onde cada linha representa os valores dos atributos de um elemento da coleção
     * @param collectionBean
     * @param caminhoCompletoArquivo
     * @param nomeCompletoXmlDescritor
     * @throws FileNotFoundException
     * @throws SAXException
     * @throws IOException
     * @throws EstruturaException
     * @throws Exception
     */
    public void converteBeanParaArquivo(Collection collectionBean, String caminhoCompletoArquivo, String nomeCompletoXmlDescritor )
    	throws FileNotFoundException, SAXException, IOException, EstruturaException, Exception  {
        	converteBeanParaArquivo( collectionBean, new FileOutputStream( caminhoCompletoArquivo ), GerenciadorArquivoCobol.class.getClassLoader().getResourceAsStream( nomeCompletoXmlDescritor ) );
    }
    
   
    /**
     * Cria um Arquivo onde cada linha representa os valores dos atributos de um elemento da coleção
     * @param collectionBean
     * @param arquivo
     * @param nomeCompletoXmlDescritor
     * @throws SAXException
     * @throws IOException
     * @throws EstruturaException
     * @throws Exception
     */
    public void converteBeanParaArquivo(Collection collectionBean, OutputStream arquivo, String nomeCompletoXmlDescritor )
    	throws SAXException, IOException, EstruturaException, Exception  {
    }
    
   
    /**
     * Cria um Arquivo onde cada linha representa os valores dos atributos de um elemento da coleção
     * @param collectionBean
     * @param caminhoCompletoArquivo
     * @param xmlDescritor
     * @throws FileNotFoundException
     * @throws SAXException
     * @throws IOException
     * @throws EstruturaException
     * @throws Exception
     */
    public void converteBeanParaArquivo(Collection collectionBean, String caminhoCompletoArquivo, InputStream xmlDescritor )
    	throws FileNotFoundException, SAXException, IOException, EstruturaException, Exception  {
        	converteBeanParaArquivo( collectionBean, new FileOutputStream( caminhoCompletoArquivo ), xmlDescritor );
    }
    
    /**
     * Converte um arquivo para uma coleção de objetos
     * @param arquivo
     * @param xmlDescritor
     * @return
     * @throws EstruturaException
     * @throws SAXException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public Collection converteArquivoParaBean(InputStream arquivo, InputStream xmlDescritor )
    	throws EstruturaException, SAXException, IOException, ClassNotFoundException, Exception {

    	Collection<Object> collectionBean = new ArrayList<Object>();

    	BufferedReader arquivoLeitura = null;
    	
        try{
        	
        	GerenciadorXml gerenciadorXml = new GerenciadorXml( xmlDescritor );
            setMapLayoutArquivoCobol( gerenciadorXml.interpretarXml() );
        	
        	GerenciadorObjeto gerenciadorObjeto = new GerenciadorObjeto();
        	
        	LayoutArquivoExterno layoutArquivoCobol = null;
        	
	        Collection nomeCampos = null;
	        Collection valores = null;
	        Object beanGerado = null;
	        Class classeBean = null;
	        	        
	        arquivoLeitura = new BufferedReader(new InputStreamReader( arquivo ));
	        
	        String linha = null;
	        while ((linha = arquivoLeitura.readLine()) != null) {
	        	addLine();
	        	if( linha.length() == 0 )
	        		continue;
       	        layoutArquivoCobol = getLayoutArquivoCobol( COD_REGISTRO_ITEM );
	        	nomeCampos = getCamposLayout( layoutArquivoCobol );
	        	valores = transformaParaColecao( layoutArquivoCobol, linha );
	        	classeBean = Class.forName( layoutArquivoCobol.getTipoClasse() );
	        	
	        	try {
					beanGerado = gerenciadorObjeto.criarObjeto(classeBean, nomeCampos, valores, layoutArquivoCobol.getMapCampoFormato(), true);
				} catch (EstruturaException e) {
					throw new EstruturaException( e.getMessage(), e, getCurrentLine() );
				}
        		collectionBean.add( beanGerado );
	            ((TransferenciaBean)beanGerado).setLinhaNoArquivo( getCurrentLine() );
	        }
	        
        } finally {
	    	if(arquivoLeitura != null) arquivoLeitura.close();    		
	    }
        
        return collectionBean;
    
    }
    
    
    /**
     * Cria um Arquivo onde cada linha representa os valores dos atributos de um elemento da coleção
     * @param collectionBean
     * @param arquivoSaida
     * @param xmlDescritor
     * @throws SAXException
     * @throws IOException
     * @throws EstruturaException
     * @throws Exception
     */
    public void converteBeanParaArquivo(Collection collectionBean, OutputStream arquivoSaida, InputStream xmlDescritor )
    	throws SAXException, IOException, EstruturaException, Exception {
        if( collectionBean == null )
        	return;
    	
        GerenciadorXml gerenciadorXml = new GerenciadorXml( xmlDescritor );
        setMapLayoutArquivoCobol( gerenciadorXml.interpretarXml() );
        
        BufferedWriter arquivo = null;
        try {
            arquivo = new BufferedWriter( new OutputStreamWriter( arquivoSaida ) );
            String linha = null;
            
            int contador = 0;
            for (Iterator iterador = collectionBean.iterator(); iterador.hasNext();contador++) {
                if( contador > 0 )
                    arquivo.newLine();
                TransferenciaBean transferenciaBean = null;
                try {
                    transferenciaBean = (TransferenciaBean) iterador.next();
                } catch (RuntimeException e) {
                    throw new EstruturaException( 
                            "Os elementos da Coleção devem ser dor tipo TransferenciaCobolBean ", e, getCurrentLine() );
                }
                linha = montarLinhasDoBean( transferenciaBean );
                arquivo.write( linha );
                arquivo.flush();
            }
       
        } finally {
            if( arquivo != null ){
                arquivo.flush();
                arquivo.close();
            }
        }
        
	}
    
    /**
     * Busca no Map de layout's o Layout do registro
     * Seguindo a regra de negócio, o primeiro char de cada linha de registro representa o código do registro
     * @param codigoRegistro
     * @return
     * @throws EstruturaException
     */
    private LayoutArquivoExterno getLayoutArquivoCobol( String codigoRegistro ) throws EstruturaException {
    	if( getMapLayoutArquivoCobol().containsKey( codigoRegistro ) ){
    		return (LayoutArquivoExterno)getMapLayoutArquivoCobol().get( codigoRegistro );
    	}else{
    		throw new EstruturaException(
    				"O código de Registro '".concat( codigoRegistro ).concat( "' não foi encontrado no arquivo .xml descritor" ), getCurrentLine());
    	}
    }
    
    /**
     * Monta uma coleção com os nomes dos campos do bean necessários para a geração do registro
     * @param layoutArquivoCobol
     * @return
     */
    private Collection getCamposLayout( LayoutArquivoExterno layoutArquivoCobol ){
    	Collection<String> collectionCampos = new ArrayList<String>();
    	for (Iterator iterador = layoutArquivoCobol.getCampos().iterator(); iterador.hasNext();) {
			Campo campo = (Campo) iterador.next();
			collectionCampos.add( campo.getNome() );
		}
    	return collectionCampos;
    }
    
	/**
	 * Tranforma um registro em uma coleção onde cada elemento da coleção é um valor
	 * @param layoutArquivoCobol
	 * @param registro
	 * @return
	 * @throws EstruturaException
	 */
	private Collection transformaParaColecao( LayoutArquivoExterno layoutArquivoCobol, String registro ) throws EstruturaException  {
		Collection<String> collectionValoresRegistro = new ArrayList<String>();
		try {
			int posicaoNoRegistro = 0;
			
			String valorCampo = null;
			
			for (Iterator iterador = layoutArquivoCobol.getCampos().iterator(); iterador.hasNext();) {
				Campo campo = (Campo) iterador.next();
				valorCampo = registro.substring( posicaoNoRegistro, posicaoNoRegistro + campo.getTamanho() );
				posicaoNoRegistro += campo.getTamanho();
				collectionValoresRegistro.add( valorCampo.trim() );
			}
			if( registro.length() > posicaoNoRegistro ){
			    throw new IndexOutOfBoundsException( "Tamanho de registro incorreto." );
			}
		} catch ( RuntimeException e ) {
		    throw new EstruturaException(
					"O tamanho do regitro de código '".concat( layoutArquivoCobol.getCodigoRegistro() ).concat( "' do arquivo, " )
					.concat( "não está batendo com o tamanho do registro definido no .xml descritor" ), getCurrentLine());
		}
		return collectionValoresRegistro;
	}
    
    /**
     * Monta uma linha com os valores dos atributos do objeto
     * será criada uma nova linha para cada Objeto agregado no bean
     * @param transferenciaBean
     * @return
     * @throws Exception
     */
    private String montarLinhasDoBean( TransferenciaBean transferenciaBean ) throws Exception  {
        
        StringBuffer linha = new StringBuffer();
        
        GerenciadorObjeto gerenciadorObjeto = new GerenciadorObjeto();
        Map mapCampoValor = null;
        Map mapCampoTipo = null;
        if( transferenciaBean instanceof TransferenciaCobolItem ){
            LayoutArquivoExterno layoutArquivoCobol = getLayoutArquivoCobol( COD_REGISTRO_ITEM );
            mapCampoValor = gerenciadorObjeto.getValoresAtributosBean( transferenciaBean, layoutArquivoCobol.getMapCampoFormato(), true );
            mapCampoTipo = gerenciadorObjeto.getTiposAtributosBean( transferenciaBean );
            linha.append( montarLinha( mapCampoValor, COD_REGISTRO_ITEM, mapCampoTipo ) );
        }
        return linha.toString();
        
    }

    /**
     * Monta uma String com os valores
     * @param mapCampoValor
     * @param tipoRegistro
     * @param mapCampoTipo
     * @return
     * @throws EstruturaException
     * @throws Exception
     */
    private String montarLinha( Map mapCampoValor, String tipoRegistro, Map mapCampoTipo ) throws EstruturaException, Exception {
        
        StringBuffer linha = new StringBuffer();
        
        LayoutArquivoExterno layoutArquivoCobol = getLayoutArquivoCobol( tipoRegistro );
        
        if( layoutArquivoCobol != null ){
            for (Iterator iterador = layoutArquivoCobol.getCampos().iterator(); iterador.hasNext();) {
                Campo campo = (Campo)iterador.next();
                String valorCampo = null;
                
                if( campo.getCodRegistroAninhado() != null && campo.getCodRegistroAninhado().length() > 0 ){
                    Object objCol = mapCampoValor.get( campo.getNome() );
                    Collection collRegistrosAninhados = null;
                    if( objCol instanceof Collection )
                        collRegistrosAninhados = (Collection)objCol;
                    Object[] registrosAninhados = null;
                    if( collRegistrosAninhados != null )
                        registrosAninhados = collRegistrosAninhados.toArray();
                    for( int i=0; i<campo.getOcorrencia(); i++ ){
                        LayoutArquivoExterno layoutArquivoCobolAninhado = getLayoutArquivoCobol( campo.getCodRegistroAninhado() );
                        GerenciadorObjeto gerenciadorObjeto = new GerenciadorObjeto();
                        TransferenciaBean transferenciaBean = null;
                        
                        if( registrosAninhados != null && registrosAninhados.length -1 >= i ){
                            transferenciaBean = (TransferenciaBean)registrosAninhados[i];
                        } else {
                            Class classeBeanAninhado = Class.forName( layoutArquivoCobolAninhado.getTipoClasse() );
                            transferenciaBean = gerenciadorObjeto.criarObjeto( classeBeanAninhado, new ArrayList(), new ArrayList() );
                        }
                        
                        Map mapCampoValorAninhado = gerenciadorObjeto.getValoresAtributosBean( transferenciaBean, layoutArquivoCobolAninhado.getMapCampoFormato(), true );
                        Map mapCampoTipoAninhado = gerenciadorObjeto.getTiposAtributosBean( transferenciaBean );
                        
                        linha.append( montarLinha( mapCampoValorAninhado, layoutArquivoCobolAninhado.getCodigoRegistro(), mapCampoTipoAninhado ) );
                        
                    }
                    continue;
                }else{
                    if( mapCampoValor.containsKey( campo.getNome() ) ){
                        valorCampo = (String)mapCampoValor.get( campo.getNome() );
                    }else{
                        valorCampo = "";
                    }
                }
                
                
                linha.append( trataValor( valorCampo, campo.getTamanho(), (Class)mapCampoTipo.get( campo.getNome() ) ) );
            }
        }
        return linha.toString();
        
    }

    /**
     * Trata o tamanho de uma string
     * completa a string com " " no final até ficar do tamanho desejado
     * @param valor
     * @param tamanho
     * @param classeCampo
     * @return
     */
    private String trataValor( String valor, int tamanho, Class classeCampo ){
        if( valor == null )
            valor = "";

        boolean valorNumerico;
        
        if ( classeCampo == Float.class || classeCampo == float.class ||
             classeCampo == Double.class || classeCampo == double.class ||
             classeCampo == Integer.class || classeCampo == int.class ||
             classeCampo == Long.class || classeCampo == long.class){
            valorNumerico = true;
            valor = valor.trim();
        } else {
            valorNumerico = false;
            valor = valor.trim();
        }
            
        String valorTratado = null;
        if( valor.length() > tamanho ){
            valorTratado = valor.substring( 0, tamanho );
        }else if( valor.length() < tamanho ){
            StringBuffer sbValor = new StringBuffer( valor );
            while( sbValor.toString().length() < tamanho ){
                if( valorNumerico )
                    sbValor.insert( 0, "0" );
                else
                    sbValor.append( " " );
            }
            valorTratado = sbValor.toString();
        }else{
            valorTratado = valor;
        }
        return valorTratado.toString();
    }
    
    /**
     * @return
     */
    protected Map getMapLayoutArquivoCobol() {
        return mapLayoutArquivoCobol;
    }
    /**
     * @param mapLayoutArquivoCobol
     */
    protected void setMapLayoutArquivoCobol(Map mapLayoutArquivoCobol) {
        this.mapLayoutArquivoCobol = mapLayoutArquivoCobol;
    }
    
    /**
     * 
     */
    private void addLine(){
		this.line++;
	}

	/**
	 * @return
	 */
	private long getCurrentLine(){
		return this.line;
	}
    
}
