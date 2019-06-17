/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.controller;

import br.com.calculopoupanca.model.dao.ComplementoDAO;
import br.com.calculopoupanca.model.dao.PoupancaDAO;
import br.com.calculopoupanca.model.poi.GerarPlanilhaComplementoPoupanca;
import entidades.ComplementoPoupanca;
import entidades.IdPoupanca;
import entidades.Poupanca;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import javax.validation.constraints.Email;
import util.Util;

/**
 *
 * @author f5078775
 */
@ManagedBean
@ViewScoped
public class ControleComplemento extends ControleGenerico implements Serializable {

    /**
     * @return the daoObservacao
     */
    private ComplementoPoupanca complementoPoupanca;
    private PoupancaDAO<Poupanca, IdPoupanca> daoPoupanca;
    private List<ComplementoPoupanca> listaComplemento = new ArrayList<>();
    private ComplementoDAO<ComplementoPoupanca, IdPoupanca> daoComplementoPoupanca;

    public ControleComplemento() {
       
        daoComplementoPoupanca = new ComplementoDAO<>();
        daoPoupanca = new PoupancaDAO<>();
        
        
        
    }

    /**
     * @return the complementoPoupanca
     */
    public ComplementoPoupanca getComplementoPoupanca() {
        return complementoPoupanca;
    }

    /**
     * @param complementoPoupanca the complementoPoupanca to set
     */
    public void setComplementoPoupanca(ComplementoPoupanca complementoPoupanca) {
        this.complementoPoupanca = complementoPoupanca;
    }

    /**
     * @return the listaComplemento
     */
    public List<ComplementoPoupanca> getListaComplemento() {
        return listaComplemento;
    }

    /**
     * @param listaComplemento the listaComplemento to set
     */
    public void setListaComplemento(List<ComplementoPoupanca> listaComplemento) {
        this.listaComplemento = listaComplemento;
    }

    /**
     * @return the daoComplementoPoupanca
     */
    public ComplementoDAO<ComplementoPoupanca, IdPoupanca> getDaoComplementoPoupanca() {
        return daoComplementoPoupanca;
    }

    /**
     * @param daoComplementoPoupanca the daoComplementoPoupanca to set
     */
    public void setDaoComplementoPoupanca(ComplementoDAO<ComplementoPoupanca, IdPoupanca> daoComplementoPoupanca) {
        this.daoComplementoPoupanca = daoComplementoPoupanca;
    }

    public void buscar() {
        // getDaoComplementoPoupanca().getEm().clear();
        setListaComplemento(getDaoComplementoPoupanca().getListaObjetos());
    }

    public void excluir(Integer id) {

        setComplementoPoupanca(getDaoComplementoPoupanca().localizar(id));
       
        getComplementoPoupanca().getPoupanca().getListaComplementoPoupanca().remove(getComplementoPoupanca());
        getDaoComplementoPoupanca().deletar(getComplementoPoupanca());

       
        if (getComplementoPoupanca().getPoupanca().getListaComplementoPoupanca().size() > 0) {
            getDaoPoupanca().somarValorPoupador(getComplementoPoupanca().getPoupanca());

            getDaoPoupanca().atribuirFaixas(getComplementoPoupanca().getPoupanca());

            getDaoPoupanca().salvar(getComplementoPoupanca().getPoupanca());

        }

    }

    public void excluirBaseGerencial(ComplementoPoupanca complementoPoupanca) {

        setComplementoPoupanca(complementoPoupanca);
        Poupanca poupanca = getComplementoPoupanca().getPoupanca();
        getDaoComplementoPoupanca().getListaObjetos().remove(getComplementoPoupanca());
        getDaoComplementoPoupanca().deletar(getComplementoPoupanca());

        if (poupanca.getListaComplementoPoupanca().size() > 0) {
            getDaoPoupanca().somarValorPoupador(poupanca);

            getDaoPoupanca().atribuirFaixas(getComplementoPoupanca().getPoupanca());

            getDaoPoupanca().salvar(poupanca);

        }
        
        getDaoPoupanca().getEm().clear();

    }

    public void informarValorAcima() {
        calcularValorAcordo();

        if (getComplementoPoupanca().getValorAcordo().compareTo(new BigDecimal("5000.00")) >= 1) {
            Util.mensagemErro("Favor gerar extrato para despacho");
        }
    }

    /**
     * @return the daoPoupanca
     */
    public PoupancaDAO<Poupanca, IdPoupanca> getDaoPoupanca() {
        return daoPoupanca;
    }

    /**
     * @param daoPoupanca the daoPoupanca to set
     */
    public void setDaoPoupanca(PoupancaDAO<Poupanca, IdPoupanca> daoPoupanca) {
        this.daoPoupanca = daoPoupanca;
    }
    
    
     public void gerarPlanilha(){
         try{
          
       
        GerarPlanilhaComplementoPoupanca g = new GerarPlanilhaComplementoPoupanca();
        g.criaPlanilha(getDaoComplementoPoupanca().getListaObjetos());
        download();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
         
        
    }

    private void download() throws FileNotFoundException, IOException {
       
        try{
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        externalContext.responseReset();
        externalContext.setResponseContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"teste.xlsx\"");
        
        
        FileInputStream inputStream = new FileInputStream(new File ("/home/jocimar/Área de Trabalho/TestePlanilha/Relatorio_Acordo_Febraban.xlsx"));
        
        OutputStream outPutStream = externalContext.getResponseOutputStream();
        byte[] buffer = new byte[1024];
        
        
        int lenght;
        
        while((lenght =inputStream.read(buffer))>0){
            outPutStream.write(buffer,0,lenght);
        }
        inputStream.close();
        context.responseComplete();
    }catch(Exception e){
        Util.mensagemErro(Util.getMensagemErro(e));
    }



    }
    

}
