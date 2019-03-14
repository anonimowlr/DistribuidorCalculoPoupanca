/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author f5078775
 */
@Entity

public class Poupanca implements Serializable{

    /**
     * @return the arquivoPoupanca
     */
    public ArquivoPoupanca getArquivoPoupanca()  {
        return arquivoPoupanca;
    }

    /**
     * @param arquivoPoupanca the arquivoPoupanca to set
     */
    public void setArquivoPoupanca(ArquivoPoupanca arquivoPoupanca) {
        this.arquivoPoupanca = arquivoPoupanca;
    }

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @Column(name = "npj")
   private String npj;
   @Column(name = "cnj")
   private String cnj;
   @Column(name = "poupador")
   private String poupador;
   @Column(name = "escritorio_bb")
   private String EscritorioBB;
   @Column(name = "advogado_bb")
   private String advogadoAdverso;
   @Column(name = "origem")
   private String origem;
   @Column(name = "cpf")
   private String cpf;
   @Column(name = "data_uop")
   private Date dataUop;
   @Column(name = "agencia")
   private Integer agencia;
   @Column(name = "conta")
   private Integer conta;
   @Column(name = "data_base")
   @Temporal(TemporalType.DATE)
   private Date dataBase;
   @Column(name = "valor_saldo")
   private BigDecimal valorSaldo;
   @Column(name = "valor_acordo")
   private BigDecimal valorAcordo;
   @Column(name = "observacao")
   private String observacao;
   @Column(name = "complemento_obs")
   private String complementoObs;
   @Column(name = "faz_juz")
   private String fazJuz;
   @Column(name = "funci")
   private String funci;
   @Column(name = "data_execucao")
   @Temporal(TemporalType.DATE)
   private Date dataExecucao; 
   
   @ManyToOne()
   @JoinColumn(name = "id_arquivo", referencedColumnName = "id")
   private ArquivoPoupanca arquivoPoupanca;
    
    
    
    public Poupanca() {
    }

    
    
    
    
    
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
     * @return the npj
     */
    public String getNpj() {
        return npj;
    }

    /**
     * @param npj the npj to set
     */
    public void setNpj(String npj) {
        this.npj = npj;
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
     * @return the EscritorioBB
     */
    public String getEscritorioBB() {
        return EscritorioBB;
    }

    /**
     * @param EscritorioBB the EscritorioBB to set
     */
    public void setEscritorioBB(String EscritorioBB) {
        this.EscritorioBB = EscritorioBB;
    }

    /**
     * @return the advogadoAdverso
     */
    public String getAdvogadoAdverso() {
        return advogadoAdverso;
    }

    /**
     * @param advogadoAdverso the advogadoAdverso to set
     */
    public void setAdvogadoAdverso(String advogadoAdverso) {
        this.advogadoAdverso = advogadoAdverso;
    }

    /**
     * @return the origem
     */
    public String getOrigem() {
        return origem;
    }

    /**
     * @param origem the origem to set
     */
    public void setOrigem(String origem) {
        this.origem = origem;
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
    public Integer getConta() {
        return conta;
    }

    /**
     * @param conta the conta to set
     */
    public void setConta(Integer conta) {
        this.conta = conta;
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
     * @return the valorSaldo
     */
    public BigDecimal getValorSaldo() {
        return valorSaldo;
    }

    /**
     * @param valorSaldo the valorSaldo to set
     */
    public void setValorSaldo(BigDecimal valorSaldo) {
        this.valorSaldo = valorSaldo;
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
     * @return the fazJuz
     */
    public String getFazJuz() {
        return fazJuz;
    }

    /**
     * @param fazJuz the fazJuz to set
     */
    public void setFazJuz(String fazJuz) {
        this.fazJuz = fazJuz;
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
     * @return the cnj
     */
    public String getCnj() {
        return cnj;
    }

    /**
     * @param cnj the cnj to set
     */
    public void setCnj(String cnj) {
        this.cnj = cnj;
    }
 
   
   
   
   
    
    
    
}
