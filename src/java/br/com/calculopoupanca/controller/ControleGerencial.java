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
public class ControleGerencial implements Serializable {

    /**
     * @return the daoObservacao
     */
    private String estadoTela = "buscar";
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

   

    public void mostrarConteudo() {
        System.out.println(getObservacao());
    }

    public void alterarTamanhoLetra() {

        getComplementoPoupanca().setPoupador(getComplementoPoupanca().getPoupador().toUpperCase());
    }
    public void alterarTamanhoLetraEscritorio() {

       getPoupanca().setEscritorioBB(getPoupanca().getEscritorioBB().toUpperCase());
    }
    public void alterarTamanhoLetraAdvogadoAdv() {

       getPoupanca().setAdvogadoAdverso(getPoupanca().getAdvogadoAdverso().toUpperCase());
    }

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
            } else if ((getComplementoPoupanca().getDataBase().before(data4) || getComplementoPoupanca().getDataBase().equals(data4)) && (getComplementoPoupanca().getDataBase().after(data3) || getComplementoPoupanca().getDataBase().equals(data3))) {
                getComplementoPoupanca().setPlano("VERAO");
                this.getComplementoPoupanca().setValorAcordo(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("4.09818")).setScale(2, RoundingMode.HALF_EVEN));
                getComplementoPoupanca().setCorrecaoEsperada(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("0.2235907655")).setScale(2, RoundingMode.HALF_EVEN));
                getComplementoPoupanca().setFazJus("SIM");
            } else if ((getComplementoPoupanca().getDataBase().before(data6) || getComplementoPoupanca().getDataBase().equals(data6)) && (getComplementoPoupanca().getDataBase().after(data5) || getComplementoPoupanca().getDataBase().equals(data5))) {
                getComplementoPoupanca().setPlano("COLOR II");
                this.getComplementoPoupanca().setValorAcordo(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("0.0014")).setScale(2, RoundingMode.HALF_EVEN));
                getComplementoPoupanca().setCorrecaoEsperada(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("0.2235907655")).setScale(2, RoundingMode.HALF_EVEN));
                getComplementoPoupanca().setFazJus("SIM");
            } else if ((getComplementoPoupanca().getDataBase().before(data8) || getComplementoPoupanca().getDataBase().equals(data8)) && (getComplementoPoupanca().getDataBase().after(data7) || getComplementoPoupanca().getDataBase().equals(data7))) {
                getComplementoPoupanca().setPlano("COLOR I");
                getComplementoPoupanca().setFazJus("NAO");
                getComplementoPoupanca().setValorAcordo(null);
                getComplementoPoupanca().setObservacao(null);
                
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

    public ControleGerencial() {
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
    public void novoGerencial() {
        
        setEstadoTela("editarGerencial");
        setPoupanca(new Poupanca());
        setIdPoupanca(new IdPoupanca());
        getPoupanca().setIdPoupanca(getIdPoupanca());
      
        setComplementoPoupanca(new ComplementoPoupanca());
        getPoupanca().adicionarComplementoPoupanca(getComplementoPoupanca());
       
      
       
    }
    
    
    public void teste(){
        complementoPoupanca = getComplementoPoupanca();
        poupanca = getPoupanca();
        idPoupanca = getIdPoupanca();
        listaComplementoPoupanca = getListaComplementoPoupanca();
        Util.mensagemInformacao(getIdPoupanca().getCnj() + " - " +  getIdPoupanca().getNpj()+  getComplementoPoupanca().getPoupador());
       
    }

    public void novoComplemento() {
        setEstadoTela("editarComplemento");
        setComplementoPoupanca(new ComplementoPoupanca());
        getPoupanca().adicionarComplementoPoupanca(getComplementoPoupanca());
    }

    public String listar() {
        return "tratamento?faces-redirect=true";
    }

    public void validarParaSalvar(){
        
       if(getPoupanca().getIdPoupanca().getNpj() == null || getPoupanca().getIdPoupanca().getNpj().toString().length()<11 ){
           Util.mensagemErro("Npj com menos de 11 digitos");
           return;
       }
       if(getPoupanca().getIdPoupanca().getCnj()== null || getPoupanca().getIdPoupanca().getCnj().toString().length()<20 ){
           Util.mensagemErro("Cnj com menos de 20 digitos");
           return;
       }
       if(getPoupanca().getIdentificacaoDemanda()== null || getPoupanca().getIdentificacaoDemanda().equals("")){
           Util.mensagemErro("Identifique a demanda");
           return;
       }
        
        
        
       
        complementarDados();
        salvar();
    }
    
    public void complementarDados(){
        
         getComplementoPoupanca().setIdArquivo(getPoupanca().getIdentificacaoDemanda());
        getComplementoPoupanca().setDataImportacaoArquivo(Utils.getDataHoraAtualMysqlDate());
        getPoupanca().setStatus(null);
        getPoupanca().setDataStatus(null);
        
    }
    
    
    public void salvar() {
        boolean persistiu = false;

        if (isEditarGerencial()) {
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
      
    }

    public void mudarParaEditar() {
        setEstadoTela("editar");
    }

     public void mudarParaEditarGerencial() {
        setEstadoTela("editarGerencial");
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
    public boolean isEditarGerencial() {

        return "editarGerencial".equals(getEstadoTela());

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

            for (ComplementoPoupanca complemento : getPoupanca().getListaComplementoPoupanca()) {

                if (complemento.getPlano()== null || complemento.getPlano().equals("")) {

                    Util.mensagemErro("Não é possível salvar, item sem calcular");
                    podeSalvar = false;
                    break;

                }
            }

            if (podeSalvar) {
                FacesContext fc = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

                Funcionario usuario = (Funcionario) session.getAttribute("usuarioLogado");

                getPoupanca().setStatus("TRATADO");
                getPoupanca().setDataStatus(Utils.getDataAtualFormatoMysql());
                getPoupanca().setAvocado(null);
                getPoupanca().setDataAvocacao(null);
                getPoupanca().setFunciAvocado(null);
                getPoupanca().setDataAvocacao(null);

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

            setPoupanca(getListaPoupanca().get(0));
            getPoupanca().setAvocado("SIM");
            getPoupanca().setFunciAvocado(usuario.getChave());
            getPoupanca().setDataAvocacao(new Date());

            salvarAvocado();

        } catch (Exception e) {

            Util.mensagemErro(Util.getMensagemErro(e));

        }

    }

    private void salvarAvocado() {

        if (getPoupanca().getAvocado().equals("SIM")) {
            getDaoPoupanca().salvarAvocado(getPoupanca());
        }

    }

    public void complementar() {

        try {
            
            if(getComplementoPoupanca().getAgencia() == null || getComplementoPoupanca().getAgencia().equals("") || getComplementoPoupanca().getConta()==null || getComplementoPoupanca().getConta().equals("")){
               Util.mensagemErro("Conta ou agência inválidos");
               return;
            }
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

            Funcionario usuario = (Funcionario) session.getAttribute("usuarioLogado");
            getComplementoPoupanca().setDataExecucao(Utils.getDataAtualFormatoMysql());
            getComplementoPoupanca().setFunci(usuario.getChave());
           
            calcularValorAcordo();
            mudarParaEditar();
        } catch (Exception e) {

            Util.mensagemErro(Util.getMensagemErro(e));
        }

    }

    public void limpar() {
        getComplementoPoupanca().setAgencia(null);
        getComplementoPoupanca().setConta(null);
        getComplementoPoupanca().setDataBase(null);
        getComplementoPoupanca().setValorAcordo(null);
        getComplementoPoupanca().setValorBase(null);
        getComplementoPoupanca().setObservacao(null);
        getComplementoPoupanca().setPlano(null);
        getComplementoPoupanca().setComplementoObs(null);
    }

    
    
    
    public void voltar(){
        mudarParaEditar();
        limpar();
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
    
    
   
}
