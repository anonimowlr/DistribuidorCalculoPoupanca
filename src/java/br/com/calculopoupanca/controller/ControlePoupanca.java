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
public class ControlePoupanca implements Serializable {

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

    private List<Poupanca> listaPoupanca = new ArrayList<>();

    @PostConstruct
    public void init() {

        buscar();

    }

    public void mostrarConteudo() {
        System.out.println(observacao);
    }

    public void alterarTamanhoLetra() {

        complementoPoupanca.setPoupador(complementoPoupanca.getPoupador().toUpperCase());
    }

    public void calcularValorAcordo() {

        try {
            Date data1 = Utils.formataData("01/06/1987");
            Date data2 = Utils.formataData("15/06/1987");
            Date data3 = Utils.formataData("01/01/1989");
            Date data4 = Utils.formataData("15/01/1989");
            Date data5 = Utils.formataData("03/01/1991");
            Date data6 = Utils.formataData("31/01/1991");

            if ((complementoPoupanca.getDataBase().before(data2) || complementoPoupanca.getDataBase().equals(data2)) && (complementoPoupanca.getDataBase().after(data1) || complementoPoupanca.getDataBase().equals(data1))) {
                complementoPoupanca.setPlano("BRESSER");
                complementoPoupanca.setFazJus("SIM");
            } else if ((complementoPoupanca.getDataBase().before(data4) || complementoPoupanca.getDataBase().equals(data4)) && (complementoPoupanca.getDataBase().after(data3) || complementoPoupanca.getDataBase().equals(data3))) {
                complementoPoupanca.setPlano("VERAO");
                complementoPoupanca.setFazJus("SIM");
            } else if ((complementoPoupanca.getDataBase().before(data6) || complementoPoupanca.getDataBase().equals(data6)) && (complementoPoupanca.getDataBase().after(data5) || complementoPoupanca.getDataBase().equals(data5))) {
                complementoPoupanca.setPlano("COLOR II");
                complementoPoupanca.setFazJus("SIM");
            } else {
                complementoPoupanca.setFazJus("NAO");
            }

            this.getComplementoPoupanca().setValorAcordo(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("3")));
        } catch (Exception e) {

        }

    }

    public ControlePoupanca() {
        daoPoupanca = new PoupancaDAO<>();
        daoObservacao = new ObservacaoDAO<>();
    }

    public void buscar() {
        setPoupanca(null);
        mudarParaBuscar();
        setListaPoupanca(getDaoPoupanca().getListaPoupanca());
        setListaPoupanca(getListaPoupanca());
        avocar();
    }

    public void novo() {
        setEstadoTela("");
        setPoupanca(new Poupanca());
    }

    public void novoComplemento() {
        setEstadoTela("editarComplemento");
        setComplementoPoupanca(new ComplementoPoupanca());
        getPoupanca().adicionarComplementoPoupanca(getComplementoPoupanca());
    }

    public String listar() {
        return "tratamento?faces-redirect=true";
    }

    public void salvar() {
        boolean persistiu = false;

        if (isEditar()) {
            persistiu = getDaoPoupanca().atualizar(getPoupanca());
        } else {
            persistiu = getDaoPoupanca().salvar(getPoupanca());
        }

        if (persistiu) {
            Util.mensagemInformacao(getDaoPoupanca().getMensagem());
        } else {
            Util.mensagemErro(getDaoPoupanca().getMensagem());
        }

        mudarParaBuscar();
        getDaoPoupanca().getEm().clear();
        novo();
        buscar();

        listar();
    }

    public void mudarParaEditar() {
        setEstadoTela("editar");
    }

    public void mudarParaEditarComplemento() {
        setEstadoTela("editarComplemento");
    }

    public void mudarParaBuscar() {
        setEstadoTela("buscar");
    }

    public boolean isEditar() {

        return "editar".equals(getEstadoTela());
    }

    public boolean isEditarComplemento() {

        return "editarComplemento".equals(getEstadoTela());

    }

    public boolean isBuscar() {

        return "buscar".equals(getEstadoTela());
    }

    public void editar(IdPoupanca idpoupanca) {
        mudarParaEditar();
        setPoupanca(getDaoPoupanca().localizarPorChaveComposta(idpoupanca));
    }

    public void editarComplemento(Integer index) {

        mudarParaEditarComplemento();

        for (ComplementoPoupanca c : getPoupanca().getListaComplementoPoupanca()) {
            if (c.getId().equals(index)) {
                setComplementoPoupanca(c);
                break;
            }

        }

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

    public void avaliarParaSalvar() {
   
        boolean podeSalvar = true;
        
        try {

            for (ComplementoPoupanca complemento : poupanca.getListaComplementoPoupanca()) {

                if ( complemento.getObservacao() == null) {

                    Util.mensagemErro("Não é possÍvel salvar, item sem calcular");
                    podeSalvar = false;
                    break;
                    
                } 
            }
            
            if(podeSalvar){
                   FacesContext fc = FacesContext.getCurrentInstance();
                    HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

                    Funcionario usuario = (Funcionario) session.getAttribute("usuarioLogado");

                    poupanca.setStatus("TRATADO");
                    poupanca.setDataStatus(Utils.getDataAtualFormatoMysql());
                    poupanca.setAvocado(null);
                    poupanca.setDataAvocacao(null);
                    poupanca.setFunciAvocado(null);
                    poupanca.setDataAvocacao(null);

                    salvar();

            }
            
                    
                
                  

            

        } catch (Exception e) {

            Util.mensagemErro(Util.getMensagemErro(e));

        }

    }

    public void avocar() {

        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

            Funcionario usuario = (Funcionario) session.getAttribute("usuarioLogado");

            poupanca = listaPoupanca.get(0);
            poupanca.setAvocado("SIM");
            poupanca.setFunciAvocado(usuario.getChave());
            poupanca.setDataAvocacao(new Date());

            salvarAvocado();

        } catch (Exception e) {

            Util.mensagemErro(Util.getMensagemErro(e));

        }

    }

    private void salvarAvocado() {

        if (poupanca.getAvocado().equals("SIM")) {
            getDaoPoupanca().salvarAvocado(poupanca);
        }

    }

    public void complementar() {

        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

            Funcionario usuario = (Funcionario) session.getAttribute("usuarioLogado");
            complementoPoupanca.setDataExecucao(Utils.getDataAtualFormatoMysql());
            complementoPoupanca.setFunci(usuario.getChave());
            mudarParaEditar();
        } catch (Exception e) {

            Util.mensagemErro(Util.getMensagemErro(e));
        }

    }
}
