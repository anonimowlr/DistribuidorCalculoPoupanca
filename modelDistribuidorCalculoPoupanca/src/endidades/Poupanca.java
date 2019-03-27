/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author f5078775
 */
@Entity

public class Poupanca implements Serializable{

   

  

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
   
    
    
    @OneToOne(cascade = CascadeType.ALL)
    private Plano plano;
    
   
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.getIdPoupanca());
        hash = 59 * hash + Objects.hashCode(this.getEscritorioBB());
        hash = 59 * hash + Objects.hashCode(this.getAdvogadoAdverso());
        hash = 59 * hash + Objects.hashCode(this.getOrigem());
        hash = 59 * hash + Objects.hashCode(this.getDataUop());
        hash = 59 * hash + Objects.hashCode(this.getListaComplementoPoupanca());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Poupanca other = (Poupanca) obj;
        if (!Objects.equals(this.escritorioBB, other.escritorioBB)) {
            return false;
        }
        if (!Objects.equals(this.advogadoAdverso, other.advogadoAdverso)) {
            return false;
        }
        if (!Objects.equals(this.origem, other.origem)) {
            return false;
        }
        if (!Objects.equals(this.idPoupanca, other.idPoupanca)) {
            return false;
        }
        if (!Objects.equals(this.dataUop, other.dataUop)) {
            return false;
        }
        if (!Objects.equals(this.listaComplementoPoupanca, other.listaComplementoPoupanca)) {
            return false;
        }
        return true;
    }

    /**
     * @return the observacao
     */
   

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

   
    
    
    
    
    
    
}
