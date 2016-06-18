/*
 * Created on 17/10/2005
 * */
package br.usjt.bd2.sgbd.xml;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;


/**
 * @author ricardocury
 */
public class GerenciadorObjeto {

    /**
     * Cria um objeto utilizando uma coleção com os nomes dos atributos da classe e outra com os valores dos mesmos
     * @param classeBean
     * @param nomeCampos
     * @param valores
     * @return TransferenciaInternaBean
     * @throws EstruturaException, Exception 
     * @throws Exception
     */
    public TransferenciaBean criarObjeto( Class classeBean, Collection nomeCampos, Collection valores ) throws EstruturaException, Exception {
        return criarObjeto( classeBean, nomeCampos, valores, null, false );
    }
    
    /**
     * Cria um objeto utilizando uma coleção com os nomes dos atributos da classe e outra com os valores dos mesmos
     * @param classeBean
     * @param nomeCampos
     * @param valores
     * @param mapCampoFormato
     * @param padraoCobol
     * @return
     * @throws EstruturaException
     * @throws Exception
     */
    public TransferenciaBean criarObjeto( Class classeBean, Collection nomeCampos, Collection valores, Map mapCampoFormato, boolean padraoCobol ) throws EstruturaException, Exception {
        
        TransferenciaBean bean = null;
        
        try {
            bean = (TransferenciaBean)classeBean.newInstance();
        } catch (Exception e) {
            throw new Exception( "Erro ao instanciar objeto do tipo ".concat( classeBean.toString() ));
        }
        
        Class classeCampo = null;
        String nomeCampo = null;
        String valorCampo = null;
        
        Iterator iteradorCampos = nomeCampos.iterator();
        for (Iterator iteradorValores = valores.iterator(); iteradorValores.hasNext();) {
            
            nomeCampo = (String)iteradorCampos.next();
            valorCampo = (String) iteradorValores.next();
            
            try {
                Field campo = classeBean.getDeclaredField( nomeCampo );
                classeCampo = campo.getType();
            } catch (Exception e) {
                throw new Exception( 
                        "Erro ao verificar o tipo do atributo '".concat( nomeCampo ).
                        concat( "' da classe " ).concat( classeBean.toString() ));
            }
            
            Object valor = null;
        	
            try {
				if( valorCampo.trim().length() > 0 ){
					if (classeCampo == String.class) {
						valor = valorCampo;
					} else if (classeCampo == Integer.class || classeCampo == int.class) {
						Object objetoNumerico = NumberFormat.getInstance(new Locale("pt", "BR")).parseObject( valorCampo );
			            valor = new Integer( ((Long)objetoNumerico).intValue() );
					} else if (classeCampo == Long.class || classeCampo == long.class) {
						Object objetoNumerico = NumberFormat.getInstance(new Locale("pt", "BR")).parseObject( valorCampo );
			            valor = (Long)objetoNumerico;
					} else if (classeCampo == Float.class || classeCampo == float.class) {
					    DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols( new Locale( "pt", "BR" ) );
					    DecimalFormat formatador = null;
					    if( mapCampoFormato != null && mapCampoFormato.containsKey( nomeCampo ) ){
					        formatador = new DecimalFormat( (String)mapCampoFormato.get( nomeCampo ) );
					        if( padraoCobol )
						        valorCampo = tratarValorRecebidoCobol( valorCampo, (String)mapCampoFormato.get( nomeCampo ) );
					    } else {
					        formatador = new DecimalFormat( "#,###,##0.00" );
					    }
					    formatador.setDecimalFormatSymbols( formatSymbols );
					    Object objetoNumerico = formatador.parse( valorCampo );
					    if( objetoNumerico instanceof Double )
				            valor = new Float( ((Double)objetoNumerico).floatValue() );
				        else
				            valor = new Float( ((Long)objetoNumerico).floatValue() );
					} else if (classeCampo == Double.class || classeCampo == double.class) {
					    DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols( new Locale( "pt", "BR" ) );
					    DecimalFormat formatador = null;
					    if( mapCampoFormato != null && mapCampoFormato.containsKey( nomeCampo ) ){
					        formatador = new DecimalFormat( (String)mapCampoFormato.get( nomeCampo ) );
					        if( padraoCobol )
						        valorCampo = tratarValorRecebidoCobol( valorCampo, (String)mapCampoFormato.get( nomeCampo ) );
					    } else {
					        formatador = new DecimalFormat( "#,###,##0.00" );
					    }
					    formatador.setDecimalFormatSymbols( formatSymbols );
					    Object objetoNumerico = formatador.parse( valorCampo );
					    if( objetoNumerico instanceof Double )
				            valor = objetoNumerico;
				        else
				            valor = new Double( ((Long)objetoNumerico).doubleValue() );
					} else if (classeCampo == java.util.Date.class) {
					    SimpleDateFormat formatador = null;
					    if( mapCampoFormato != null && mapCampoFormato.containsKey( nomeCampo ) )
					        formatador = new SimpleDateFormat( (String)mapCampoFormato.get( nomeCampo ) );
					    else
					        formatador = new SimpleDateFormat("ddMMyyyy");
			            valor = formatador.parse( valorCampo );
					} else if (classeCampo == Boolean.class || classeCampo == boolean.class) {
						valor = new Boolean(valorCampo);
					} else if (classeCampo == BigDecimal.class) {
					    DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols( new Locale( "pt", "BR" ) );
					    DecimalFormat formatador = null;
					    if( mapCampoFormato != null && mapCampoFormato.containsKey( nomeCampo ) ){
					        formatador = new DecimalFormat( (String)mapCampoFormato.get( nomeCampo ) );
					        if( padraoCobol )
						        valorCampo = tratarValorRecebidoCobol( valorCampo, (String)mapCampoFormato.get( nomeCampo ) );
					    } else {
					        formatador = new DecimalFormat( "#,###,##0.00" );
					    }
					    formatador.setDecimalFormatSymbols( formatSymbols );
					    Object objetoNumerico = formatador.parse( valorCampo );
					    if( objetoNumerico instanceof Double )
					    	valor = new BigDecimal( ((Long)objetoNumerico).doubleValue() );
				        else
				            valor = new BigDecimal( ((Long)objetoNumerico).doubleValue() );
					}
				}
			} catch (Exception e) {
				throw new EstruturaException( 
	                    "Erro ao transformar o valor '".concat( valorCampo )
	                    .concat( "' em " ).concat( classeCampo.toString() )
	                    .concat(  " para o atributo '" ).concat( nomeCampo )
	                    .concat( "' da classe " ).concat( classeBean.toString() ) , e, 0 );
			}
            
            invocarMetodoSet( bean, nomeCampo, classeCampo, valor );

        }
        
        return bean;
        
    }
    
    /**
     * Monta um Map onde a chave é o nome do atributo e o valor é o valor do atributo transformado para String
     * Caso o atributo seja uma coleção então ele é ignorado pois se trata de uma Bean aninhado
     * @param bean
     * @return Map
     * @throws Exception 
     */
    public Map getValoresAtributosBean( TransferenciaBean bean ) throws Exception {
        return getValoresAtributosBean( bean, null, false );
    }
    
    /**
     * Monta um Map onde a chave é o nome do atributo e o valor é o valor do atributo transformado para String
     * Caso o atributo seja uma coleção então ele é ignorado pois se trata de uma Bean aninhado
     * @param bean
     * @param mapCampoFormato
     * @param padraoCobol
     * @return
     * @throws Exception
     */
    public Map getValoresAtributosBean( TransferenciaBean bean, Map mapCampoFormato, boolean padraoCobol ) throws Exception {
        
        Map<String,Object> mapCampoValor = new HashMap<String,Object>();
        
        Class classeBean = bean.getClass();
        Field campo = null;
        String nomeCampo = null;
        Object valor = null;
        Field[] campos = classeBean.getDeclaredFields();
        for (int i = 0; i < campos.length; i++) {
            campo = campos[i];
            nomeCampo = campo.getName();
            
            valor = invocarMetodoGet( bean, nomeCampo );
                   
            if( valor == null )
                valor = "";
            
            Class classeCampo = campo.getType();
            if (classeCampo == String.class ||
                classeCampo == Integer.class || classeCampo == int.class ||
                classeCampo == Long.class || classeCampo == long.class ||
                classeCampo == Boolean.class || classeCampo == boolean.class ) {
                	
                	mapCampoValor.put( nomeCampo, valor.toString() );
			
            } else if ( classeCampo == Float.class || classeCampo == float.class ) {
				if( valor.getClass() == Float.class || valor.getClass() == float.class ){
				    Double valorDouble = new Double(((Float)valor).doubleValue());
				    DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols( new Locale( "pt", "BR" ) );
				    DecimalFormat formatador = null;
				    if( mapCampoFormato != null && mapCampoFormato.containsKey( campo.getName() ) )
				        formatador = new DecimalFormat( (String)mapCampoFormato.get( campo.getName() ) );
				    else
				        formatador = new DecimalFormat( "#,###,##0.00" );
				    
				    formatador.setDecimalFormatSymbols( formatSymbols );
				    if( padraoCobol )
					    mapCampoValor.put(
					            nomeCampo,
					            substitua( substitua( formatador.format( valorDouble ), ",", "" ), ".", "" ) );
				    else
				        mapCampoValor.put(
					            nomeCampo,
					            formatador.format( valorDouble ) );
				} else {
				    mapCampoValor.put( nomeCampo, "" );
				}
			} else if ( classeCampo == Double.class || classeCampo == double.class ) {
			    if( valor.getClass() == Double.class ||  valor.getClass() == double.class ){
			        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols( new Locale( "pt", "BR" ) );
				    DecimalFormat formatador = null;
				    if( mapCampoFormato != null && mapCampoFormato.containsKey( campo.getName() ) )
				        formatador = new DecimalFormat( (String)mapCampoFormato.get( campo.getName() ) );
				    else
				        formatador = new DecimalFormat( "#,###,##0.00" );
				    
				    formatador.setDecimalFormatSymbols( formatSymbols );
				    if( padraoCobol )
					    mapCampoValor.put(
					            nomeCampo,
					            substitua( substitua( formatador.format( (Double)valor ), ",", "" ), ".", "" ) );
				    else
				        mapCampoValor.put(
					            nomeCampo,
					            formatador.format( (Double)valor ) );
				} else {
				    mapCampoValor.put( nomeCampo, "" );
				}
			    
			} else if ( classeCampo == java.util.Date.class ) {
			    if( valor.getClass() == java.util.Date.class || valor.getClass() == java.sql.Date.class ) {
				    SimpleDateFormat formatador = null;
				    if( mapCampoFormato != null && mapCampoFormato.containsKey( campo.getName() ) )
				        formatador = new SimpleDateFormat( (String)mapCampoFormato.get( campo.getName() ) );
				    else
				        formatador = new SimpleDateFormat("ddMMyyyy");
                    mapCampoValor.put( nomeCampo, formatador.format( (Date)valor ) );
			    }else{
			        mapCampoValor.put( nomeCampo, "" );
			    }
			} else if ( classeCampo == Collection.class ) {
			    if( padraoCobol )
			        mapCampoValor.put( nomeCampo, valor );
			}
            
        }
        
        return mapCampoValor;
        
    }
    
    /**
     * Monta um Map onde a chave é o nome do atributo e o valor é o tipo do atributo
     * Caso o atributo seja uma coleção então ele é ignorado pois se trata de uma Bean aninhado
     * @param bean
     * @param mapCampoFormato
     * @return
     * @throws Exception
     */
    public Map getTiposAtributosBean( TransferenciaBean bean ) throws Exception {
        
        Map<String,Object> mapCampoValor = new HashMap<String,Object>();
        
        Class classeBean = bean.getClass();
        Field campo = null;
        String nomeCampo = null;
        Field[] campos = classeBean.getDeclaredFields();
        for (int i = 0; i < campos.length; i++) {
            campo = campos[i];
            nomeCampo = campo.getName();
            Class classeCampo = campo.getType();
            mapCampoValor.put( nomeCampo, classeCampo );
            
        }
        
        return mapCampoValor;
        
    }
    
    /**
     * @param classeBean
     * @param nomeAtributo
     * @return
     * @throws Exception
     */
    public Class getTipoAtributo( Class classeBean, String nomeAtributo ) throws Exception {
    	try {
			return classeBean.getDeclaredField( nomeAtributo ).getType();
		} catch (Exception e) {
			throw new Exception(
					"Erro ao tentar verificar o tipo do atributo ".concat( nomeAtributo )
					.concat( " da classe " ).concat( classeBean.toString() ) );
		}
    }
    
    /**
     * Cria uma coleção dos objetos que estão agregados no Bean
     * @param bean
     * @return Collection
     * @throws Exception
     * @throws Exception
     */
    public Collection getBeansAninhados( TransferenciaBean bean ) throws Exception {
        
        Collection<Collection> beansAninhados = new ArrayList<Collection>();
        
        Class classeBean = bean.getClass();
        Field campo = null;
        Object valor = null;
        String nomeCampo = null;
        
        Field[] campos = classeBean.getDeclaredFields();
        for (int i = campos.length -1; i >= 0; i--) {
            campo = campos[i];
            nomeCampo = campo.getName();
            
            valor = invocarMetodoGet( bean, nomeCampo );
            
            try {
	            Collection collection = (Collection)valor;
	            if( collection != null ){
	                beansAninhados.add( collection );
	            }
            }catch(ClassCastException e){
                try{
                    TransferenciaBean transferenciaBean = (TransferenciaBean)valor;
                    Collection<TransferenciaBean> collectionTransferenciaBean = new ArrayList<TransferenciaBean>();
                    collectionTransferenciaBean.add( transferenciaBean );
                    beansAninhados.add( collectionTransferenciaBean );
                 }catch(ClassCastException e1){}
            };
                        
        }
        
        return beansAninhados;
        
    }
    
    /**
     * Invoca o Método set de um campo de um objeto
     * @param bean
     * @param atributo
     * @param classoAtributo
     * @param valor
     * @throws Exception 
     * @throws Exception
     */
    public void invocarMetodoSet( Object bean, String atributo, Class classoAtributo, Object valor ) throws Exception {
        if( valor == null )
            return;
        
        String nomeMetodo = null;
        Class classeBean = null;
        
        try {
            classeBean = bean.getClass();
            nomeMetodo = "set".concat( atributo.substring( 0, 1 ).toUpperCase() ).concat( atributo.substring( 1 ) );
            Method metodo = classeBean.getMethod( nomeMetodo, new Class[]{classoAtributo} );
            metodo.invoke( bean, new Object[]{valor} );
        } catch (Exception e) {
            throw new Exception( 
                    "Erro ao invocar o método '".concat( nomeMetodo )
                    .concat( "' da classe " ).concat( classeBean.toString() ));
        }
        
    }
    
    /**
     * Invoca o Método get de um campo de um objeto
     * @param bean
     * @param atributo
     * @return
     * @throws Exception 
     * @throws Exception
     */
    public Object invocarMetodoGet( Object bean, String atributo ) throws Exception {
        
        String nomeMetodo = null;
        Class classeBean = null;
        Object valor = null;
        
        try {
            classeBean = bean.getClass();
            nomeMetodo = "get".concat( atributo.substring( 0, 1 ).toUpperCase() ).concat( atributo.substring( 1 ) );
            Method metodo = classeBean.getMethod( nomeMetodo, (Class[])null );
            valor = metodo.invoke( bean, (Object[])null );
        } catch (Exception e) {
            throw new Exception( 
                    "Erro ao invocar o método '".concat( nomeMetodo )
                    .concat( "' da classe " ).concat( classeBean.toString() ));
        }
        
        return valor;
        
    }
    
    /**
     * Substitui uma string por outra
     * @param string
     * @param valorAntigo
     * @param valorNovo
     * @return
     */
    public String substitua( String string, String valorAntigo, String valorNovo ) {
        if( valorAntigo == null || valorNovo == null || valorAntigo.equals( valorNovo ) )
            return string;
        
        String stringTransformada = " ".concat( string );
        for( int posicaoValorAntigo = stringTransformada.indexOf( valorAntigo ); posicaoValorAntigo != -1 ; posicaoValorAntigo = stringTransformada.indexOf( valorAntigo ) ){
            if( posicaoValorAntigo != -1 ){
                stringTransformada = stringTransformada.substring( 0, posicaoValorAntigo ).concat( valorNovo ).concat( stringTransformada.substring( posicaoValorAntigo + valorAntigo.length() ) );
            }
    	}
        return stringTransformada;
    }
    
    /**|Trata valor recebido do arquivo Cobol
     * @param valorCampo
     * @return
     */
    private String tratarValorRecebidoCobol(String valorCampo, String formato) {
        StringBuffer valorTratado = new StringBuffer();
        int posicaoCasaDecimal = formato.indexOf( '.' );
        if( formato.indexOf( '.' ) != -1 ){
            int qdtCasasDecimais = formato.substring( posicaoCasaDecimal ).length() - 1;
            valorTratado.append( valorCampo.substring( 0, valorCampo.length() - qdtCasasDecimais ) );
            valorTratado.append( "," );
            valorTratado.append( valorCampo.substring( valorCampo.length() - qdtCasasDecimais ) );
        }else{
            valorTratado.append( valorCampo );
        }
        return valorTratado.toString();
    }
    
}