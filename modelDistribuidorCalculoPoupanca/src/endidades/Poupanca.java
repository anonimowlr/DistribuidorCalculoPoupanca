/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

  @EmbeddedId
  private IdPoupanca idPoupanca;
    
   @Column(name = "escritorio_bb")
   private String escritorioBB;
   @Column(name = "advogado_bb")
   private String advogadoAdverso;
   @Column(name = "origem")
   private String origem;
  
   @Column(name = "data_uop")
   private Date dataUop;
   
   @ManyToOne()
   @JoinColumn(name = "id_arquivo", referencedColumnName = "id")
   private ArquivoPoupanca arquivoPoupanca;
    
   
    @OneToMany(mappedBy = "poupanca",cascade = CascadeType.ALL,orphanRemoval = false)
   private List<ComplementoPoupanca> listaComplementoPoupanca =  new ArrayList<>();
    
    
    public Poupanca() {
    }

    
    
    public void adicionarComplementoPoupanca(ComplementoPoupanca complementoPoupanca){
        complementoPoupanca.setPoupanca(this);
        getListaComplementoPoupanca().add(complementoPoupanca);
        
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
     * @return the escritorioBB
     */
    public String getEscritorioBB() {
        return escritorioBB;
    }

    /**
     * @param escritorioBB the escritorioBB to set
     */
    public void setEscritorioBB(String escritorioBB) {
        this.escritorioBB = escritorioBB;
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
