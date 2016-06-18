/*
 * Created on 26/11/2005
 * */
package br.usjt.bd2.sgbd.xml;


/**
 * Interface utilizada para a geração e leitura de arquivos Externos
 * @author ricardocury
 * 
 * Esta classe corresponde ao caso de uso: UC33.
 */
public abstract class TransferenciaCobolItem extends TransferenciaBean {
    private String codigoRegistro;
    
    /**
     * @return
     */
    public String getCodigoRegistro() {
        return codigoRegistro;
    }
    /**
     * @param codigoRegistro
     */
    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }
}
