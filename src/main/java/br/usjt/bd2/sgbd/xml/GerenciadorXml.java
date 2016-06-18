/*
 * Created on 18/10/2005
 * */
package br.usjt.bd2.sgbd.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author ricardocury
 */
public class GerenciadorXml {

    private final String TAG_REGISTRO = "registro";
    
    private final String ATRIBUTO_CODIGO_REGISTRO = "codigoRegistro";
    private final String ATRIBUTO_TIPO_CLASSE = "tipoClasse";
    private final String ATRIBUTO_CODIGO_REGISTRO_PAI = "codigoRegistroPai";
    private final String ATRIBUTO_NOME_ATRIBUTO_REGISTRO_PAI = "nomeAtributoRegistroPai";
    
    private final String TAG_CAMPO = "campo";
    private final String ATRIBUTO_NOME = "nome";
    private final String ATRIBUTO_TAMANHO = "tamanho";
    private final String ATRIBUTO_FORMATO = "formato";
    private final String ATRIBUTO_CODIGO_REGISTRO_ANINHADO = "codRegistroAninhado";
    private final String ATRIBUTO_OCORRENCIA = "ocorrencia";
    
    private InputSource inputSource;
    
    /**
     * @param nomeCompletoXmlDescritor
     * @throws Exception
     */
    public GerenciadorXml( String nomeCompletoXmlDescritor )
    	throws Exception {
    		this( GerenciadorXml.class.getClassLoader().getResourceAsStream( nomeCompletoXmlDescritor ) );
    }
    
    /**
     * @param inputStream
     * @throws Exception
     */
    public GerenciadorXml( InputStream inputStream ) 
    	throws Exception {
	    	try {
	            setInputSource( new InputSource( inputStream ) );
	        } catch (Exception e) {
	            throw new Exception(
	                    "Erro ao ler o arquivo xml descritor");
	        }
	}
    
    /**
     * Lê o xml que descreve o layout do arquivo e monta um Map onde a chave é o código do registro
     * e o valor é um Objeto do tipo LayoutArquivoExterno que descreve um registro
     * @return Map
     * @throws IOException
     * @throws SAXException
     * @throws Exception
     */
    public Map interpretarXml() throws SAXException, IOException{
        
        Map<String,LayoutArquivoExterno> mapLayoutArquivoExterno = new HashMap<String,LayoutArquivoExterno>();
        
        DOMParser parser = new DOMParser();

        parser.parse( getInputSource() );
        
        DocumentImpl document = (DocumentImpl)parser.getDocument();
        
        NodeList listaRegistros = document.getElementsByTagName( TAG_REGISTRO );
        
        for (int i = 0; i < listaRegistros.getLength(); i++) {
            Node registro = listaRegistros.item(i);
            LayoutArquivoExterno layoutArquivoExterno = createLayoutArquivoExterno( registro );
            mapLayoutArquivoExterno.put(
                    layoutArquivoExterno.getCodigoRegistro(),
                    layoutArquivoExterno);
        }
        
        return mapLayoutArquivoExterno;
        
    }

    /**
     * Tranforma um Node do xml que representa um registro em um Ojjeto to tipo LayoutArquivoExterno
     * @param registro
     * @return LayoutArquivoExterno
     */
    private LayoutArquivoExterno createLayoutArquivoExterno( Node registro ) {
        LayoutArquivoExterno layoutArquivoExterno = new LayoutArquivoExterno();
        NodeList elementos = registro.getChildNodes();

		layoutArquivoExterno.setCodigoRegistro( 
		        registro.getAttributes().getNamedItem( ATRIBUTO_CODIGO_REGISTRO ).getNodeValue().toUpperCase() );
		
		layoutArquivoExterno.setTipoClasse( 
		        registro.getAttributes().getNamedItem( ATRIBUTO_TIPO_CLASSE ).getNodeValue() );
        
		Node node = registro.getAttributes().getNamedItem( ATRIBUTO_CODIGO_REGISTRO_PAI );
		if( node != null )
			layoutArquivoExterno.setCodigoRegistroPai( node.getNodeValue() );
        
		
		node = registro.getAttributes().getNamedItem( ATRIBUTO_NOME_ATRIBUTO_REGISTRO_PAI );
		if( node != null )
			layoutArquivoExterno.setNomeAtributoRegistroPai( node.getNodeValue() );
		
        String nomeElemento = null;
        
        for (int i = 0; i < elementos.getLength(); i++) {
            Node elemento = elementos.item(i);
            nomeElemento = elemento.getNodeName();
            if( nomeElemento.equals( TAG_CAMPO ) ) {
                NamedNodeMap atributos = elemento.getAttributes();
                String nome = atributos.getNamedItem( ATRIBUTO_NOME ).getNodeValue();
                
                int tamanho = 0;
                Node nodeTamanho = atributos.getNamedItem( ATRIBUTO_TAMANHO );
                if( nodeTamanho != null )
                    tamanho = Integer.parseInt( nodeTamanho.getNodeValue() );
                
                String codRegistroAninhado = "";
                Node nodeRegistroAninhado = atributos.getNamedItem( ATRIBUTO_CODIGO_REGISTRO_ANINHADO );
                if( nodeRegistroAninhado != null )
                    codRegistroAninhado = nodeRegistroAninhado.getNodeValue();
                
                int ocorrencia = 0;
                Node nodeOcorrencia = atributos.getNamedItem( ATRIBUTO_OCORRENCIA );
                if( nodeOcorrencia != null )
                    ocorrencia = Integer.parseInt( nodeOcorrencia.getNodeValue() );
                
                layoutArquivoExterno.addCampo( nome, tamanho, codRegistroAninhado, ocorrencia );
                    
                Node nodeCampo = atributos.getNamedItem( ATRIBUTO_FORMATO );
                if( nodeCampo != null )
        			layoutArquivoExterno.putFormato( nome, nodeCampo.getNodeValue() );
            }
        }
        return layoutArquivoExterno;
    }

	/**
	 * @return
	 */
	private InputSource getInputSource() {
		return inputSource;
	}
	/**
	 * @param inputSource
	 */
	private void setInputSource(InputSource inputSource) {
		this.inputSource = inputSource;
	}
}
