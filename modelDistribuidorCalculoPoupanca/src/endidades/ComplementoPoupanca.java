/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PC_LENOVO
 */
@Entity
@Table(name = "tb_complemento_poupanca")
public class ComplementoPoupanca implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "agencia")
    private Integer agencia;
    @Column(name = "conta")
    private BigInteger conta;
    @Column(name = "poupador")
    private String poupador;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "plano")
    private String plano;
    @Column(name = "complemento_obs")
    private String complementoObs;
    @Column(name = "data_base")
    @Temporal(TemporalType.DATE)
    private Date dataBase;
    @Column(name = "valor_base")
    private BigDecimal valorBase;
    @Column(name = "valor_acordo")
    private BigDecimal valorAcordo;
    @Column(name = "faz_jus")
    private String fazJus;
    @Column(name = "funci")
    private String funci;
    @Column(name = "data_execucao")
    @Temporal(TemporalType.DATE)
    private Date dataExecucao;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_uop")       
    private Date dataUop;

    
    @ManyToOne()
     @JoinColumns( {
     @JoinColumn(name="cnj"), @JoinColumn(name="npj")})
      private Poupanca poupanca;
    
    
    
    
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the agencia
     */
    public Integer getAgencia() {
        return agencia;
    }

    /**
     * @param agencia the agencia to set
     */
    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    /**
     * @return the conta
     */
    public BigInteger getConta() {
        return conta;
    }

    /**
     * @param conta the conta to set
     */
    public void setConta(BigInteger conta) {
        this.conta = conta;
    }

    /**
     * @return the poupador
     */
    public String getPoupador() {
        return poupador;
    }

    /**
     * @param poupador the poupador to set
     */
    public void setPoupador(String poupador) {
        this.poupador = poupador;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the plano
     */
    public String getPlano() {
        return plano;
    }

    /**
     * @param plano the plano to set
     */
    public void setPlano(String plano) {
        this.plano = plano;
    }

    /**
     * @return the complementoObs
     */
    public String getComplementoObs() {
        return complementoObs;
    }

    /**
     * @param complementoObs the complementoObs to set
     */
    public void setComplementoObs(String complementoObs) {
        this.complementoObs = complementoObs;
    }

    /**
     * @return the dataBase
     */
    public Date getDataBase() {
        return dataBase;
    }

    /**
     * @param dataBase the dataBase to set
     */
    public void setDataBase(Date dataBase) {
        this.dataBase = dataBase;
    }

    /**
     * @return the valorBase
     */
    public BigDecimal getValorBase() {
        return valorBase;
    }

    /**
     * @param valorBase the valorBase to set
     */
    public void setValorBase(BigDecimal valorBase) {
        this.valorBase = valorBase;
    }

    /**
     * @return the valorAcordo
     */
    public BigDecimal getValorAcordo() {
        return valorAcordo;
    }

    /**
     * @param valorAcordo the valorAcordo to set
     */
    public void setValorAcordo(BigDecimal valorAcordo) {
        this.valorAcordo = valorAcordo;
    }

    /**
     * @return the fazJus
     */
    public String getFazJus() {
        return fazJus;
    }

    /**
     * @param fazJus the fazJus to set
     */
    public void setFazJus(String fazJus) {
        this.fazJus = fazJus;
    }

    /**
     * @return the funci
     */
    public String getFunci() {
        return funci;
    }

    /**
     * @param funci the funci to set
     */
    public void setFunci(String funci) {
        this.funci = funci;
    }

    /**
     * @return the dataExecucao
     */
    public Date getDataExecucao() {
        return dataExecucao;
    }

    /**
     * @param dataExecucao the dataExecucao to set
     */
    public void setDataExecucao(Date dataExecucao) {
        this.dataExecucao = dataExecucao;
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
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the dataUop
     */
    public Date getDataUop() {
        return dataUop;
    }

    /**
     * @param dataUop the dataUop to set
     */
    public void setDataUop(Date dataUop) {
        this.dataUop = dataUop;
    }

}