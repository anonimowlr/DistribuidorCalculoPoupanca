/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.controller;

import br.com.calculopoupanca.model.dao.PoupancaDAO;
import endidades.Poupanca;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.Util;

/**
 *
 * @author f5078775
 */
@ManagedBean
@SessionScoped
public class ControlePoupanca implements  Serializable{
    
    private String estadoTela = "";
    private Poupanca poupanca;
    private PoupancaDAO<Poupanca> daoPoupanca;

    public ControlePoupanca() {
    daoPoupanca = new PoupancaDAO<>();
    }
    
    
    public void  novo(){
        setEstadoTela("");
        setPoupanca(new Poupanca());
    }
    
    
    public String listar(){
         return  "tratamento?faces-redirect=true";
    }
    
    
    
    public void salvar(){
        boolean persistiu = false;
        
        if(isEditar()){
           persistiu = getDaoPoupanca().atualizar(getPoupanca());
        } else{
            persistiu = getDaoPoupanca().salvar(getPoupanca());
        }
        
        if(persistiu){
            Util.mensagemInformacao(getDaoPoupanca().getMensagem());
        } else{
            Util.mensagemErro(getDaoPoupanca().getMensagem());
        }
        
        
        
    }
    
    
    
    
     public void mudarParaEditar(){
         setEstadoTela("editar");
     }
     
     
     
     public boolean isEditar(){
         
         return "editar".equals(getEstadoTela());
     }
    
     
     
     public void editar(Poupanca poupanca){
         mudarParaEditar();
         poupanca = getDaoPoupanca().localizar(poupanca.getId());
     }

    /**
     * @return the estadoTela
     */
    public String getEstadoTela() {
        return estadoTela;
    }

    /**
     * @param estadoTela the estadoTela to set
     */
    public void setEstadoTela(String estadoTela) {
        this.estadoTela = estadoTela;
    }

    /**
     * @return the poupanca
     */
    public Poupanca getPoupanca() {
        return poupanca;
    }

    /**
     * @param poupanca the poupanca to set
     */
    public void setPoupanca(Poupanca poupanca) {
        this.poupanca = poupanca;
    }

    /**
     * @return the daoPoupanca
     */
    public PoupancaDAO<Poupanca> getDaoPoupanca() {
        return daoPoupanca;
    }

    /**
     * @param daoPoupanca the daoPoupanca to set
     */
    public void setDaoPoupanca(PoupancaDAO<Poupanca> daoPoupanca) {
        this.daoPoupanca = daoPoupanca;
    }
    
     
     
     
}





