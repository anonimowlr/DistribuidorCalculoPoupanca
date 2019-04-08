/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.controller;

import br.com.calculopoupanca.model.dao.ObservacaoDAO;
import br.com.calculopoupanca.model.dao.PoupancaDAO;
import br.com.calculopoupanca.util.Utils;
import endidades.ComplementoPoupanca;
import endidades.Funcionario;
import endidades.IdPoupanca;
import endidades.Observacao;
import endidades.Plano;
import endidades.Poupanca;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import util.Util;

/**
 *
 * @author f5078775
 */
@ManagedBean
@ViewScoped
public class ControleGenerico implements Serializable {

    /**
     * @return the daoObservacao
     */
    private String estadoTela = "";
    private Poupanca poupanca;
    private ComplementoPoupanca complementoPoupanca;
    private Plano plano;
    private Observacao observacao;
    private IdPoupanca idPoupanca;
    private PoupancaDAO<Poupanca, IdPoupanca> daoPoupanca;
    private ObservacaoDAO<Observacao, IdPoupanca> daoObservacao;
    private List<Observacao> listaObservacao = new ArrayList<>();
    private List<ComplementoPoupanca> listaComplementoPoupanca= new ArrayList<>();

    private List<Poupanca> listaPoupanca = new ArrayList<>();

  

    
    public void calcularValorAcordo() {

        try {
            Date data1 = Utils.formataData("01/06/1987");
            Date data2 = Utils.formataData("15/06/1987");
            Date data3 = Utils.formataData("01/01/1989");
            Date data4 = Utils.formataData("15/01/1989");
            Date data5 = Utils.formataData("03/01/1991");
            Date data6 = Utils.formataData("31/01/1991");
            Date data7 = Utils.formataData("01/03/1990");
            Date data8 = Utils.formataData("15/03/1990");

            if ((getComplementoPoupanca().getDataBase().before(data2) || getComplementoPoupanca().getDataBase().equals(data2)) && (getComplementoPoupanca().getDataBase().after(data1) || getComplementoPoupanca().getDataBase().equals(data1))) {
                getComplementoPoupanca().setPlano("BRESSER");
                this.getComplementoPoupanca().setValorAcordo(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("0.04277")).setScale(2, RoundingMode.HALF_EVEN));
                getComplementoPoupanca().setCorrecaoEsperada(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("0.2235907655")).setScale(2, RoundingMode.HALF_EVEN));
                getComplementoPoupanca().setFazJus("SIM");
                getComplementoPoupanca().setObservacao("BRESSER");
            } else if ((getComplementoPoupanca().getDataBase().before(data4) || getComplementoPoupanca().getDataBase().equals(data4)) && (getComplementoPoupanca().getDataBase().after(data3) || getComplementoPoupanca().getDataBase().equals(data3))) {
                getComplementoPoupanca().setPlano("VERAO");
                this.getComplementoPoupanca().setValorAcordo(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("4.09818")).setScale(2, RoundingMode.HALF_EVEN));
                getComplementoPoupanca().setCorrecaoEsperada(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("0.2235907655")).setScale(2, RoundingMode.HALF_EVEN));
                getComplementoPoupanca().setFazJus("SIM");
                 getComplementoPoupanca().setObservacao("VERAO");
            } else if ((getComplementoPoupanca().getDataBase().before(data6) || getComplementoPoupanca().getDataBase().equals(data6)) && (getComplementoPoupanca().getDataBase().after(data5) || getComplementoPoupanca().getDataBase().equals(data5))) {
                getComplementoPoupanca().setPlano("COLOR II");
                this.getComplementoPoupanca().setValorAcordo(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("0.0014")).setScale(2, RoundingMode.HALF_EVEN));
                getComplementoPoupanca().setCorrecaoEsperada(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("0.2235907655")).setScale(2, RoundingMode.HALF_EVEN));
                getComplementoPoupanca().setFazJus("SIM");
                 getComplementoPoupanca().setObservacao("COLOR II");
            } else if ((getComplementoPoupanca().getDataBase().before(data8) || getComplementoPoupanca().getDataBase().equals(data8)) && (getComplementoPoupanca().getDataBase().after(data7) || getComplementoPoupanca().getDataBase().equals(data7))) {
                getComplementoPoupanca().setPlano("COLOR I");
                getComplementoPoupanca().setFazJus("NAO");
                getComplementoPoupanca().setValorAcordo(null);
                getComplementoPoupanca().setObservacao(null);
                  getComplementoPoupanca().setObservacao("COLOR I");
                
                getComplementoPoupanca().setComplementoObs(null);
                getComplementoPoupanca().setValorBase(null);
            } else {
                getComplementoPoupanca().setFazJus("NAO");
                getComplementoPoupanca().setValorAcordo(null);
                getComplementoPoupanca().setObservacao(null);
                getComplementoPoupanca().setPlano("NAO FAZ JUS");
                getComplementoPoupanca().setComplementoObs(null);
                getComplementoPoupanca().setValorBase(null);
            }

        } catch (Exception e) {

            Util.mensagemErro(Util.getMensagemErro(e));

        }

    }

    public ControleGenerico() {
        daoPoupanca = new PoupancaDAO<>();
        daoObservacao = new ObservacaoDAO<>();
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
     * @return the plano
     */
    public Plano getPlano() {
        return plano;
    }

    /**
     * @param plano the plano to set
     */
    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    /**
     * @return the observacao
     */
    public Observacao getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(Observacao observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the idPoupanca
     */
    public IdPoupanca getIdPoupanca() {
        return idPoupanca;
    }

    /**
     * @param idPoupanca the idPoupanca to set
     */
    public void setIdPoupanca(IdPoupanca idPoupanca) {
        this.idPoupanca = idPoupanca;
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

    /**
     * @return the daoObservacao
     */
    public ObservacaoDAO<Observacao, IdPoupanca> getDaoObservacao() {
        return daoObservacao;
    }

    /**
     * @param daoObservacao the daoObservacao to set
     */
    public void setDaoObservacao(ObservacaoDAO<Observacao, IdPoupanca> daoObservacao) {
        this.daoObservacao = daoObservacao;
    }

    /**
     * @return the listaObservacao
     */
    public List<Observacao> getListaObservacao() {
        return listaObservacao;
    }

    /**
     * @param listaObservacao the listaObservacao to set
     */
    public void setListaObservacao(List<Observacao> listaObservacao) {
        this.listaObservacao = listaObservacao;
    }

    /**
     * @return the listaComplementoPoupanca
     */
    public List<ComplementoPoupanca> getListaComplementoPoupanca() {
        return listaComplementoPoupanca;
    }

    /**
     * @param listaComplementoPoupanca the listaComplementoPoupanca to set
     */
    public void setListaComplementoPoupanca(List<ComplementoPoupanca> listaComplementoPoupanca) {
        this.listaComplementoPoupanca = listaComplementoPoupanca;
    }

    /**
     * @return the listaPoupanca
     */
    public List<Poupanca> getListaPoupanca() {
        return listaPoupanca;
    }

    /**
     * @param listaPoupanca the listaPoupanca to set
     */
    public void setListaPoupanca(List<Poupanca> listaPoupanca) {
        this.listaPoupanca = listaPoupanca;
    }

   
}
