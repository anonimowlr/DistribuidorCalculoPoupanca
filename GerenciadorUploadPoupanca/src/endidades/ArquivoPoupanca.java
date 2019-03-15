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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author f5078775
 */
@Entity
public class ArquivoPoupanca implements Serializable{
    
   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @Column(name = "data_arquivo")
   private Date dataArquivo;
   
   @OneToMany(mappedBy ="arquivoPoupanca",cascade = CascadeType.ALL,orphanRemoval = false )
   private List<Poupanca> listaPoupanca = new ArrayList<>();

    public ArquivoPoupanca() {
    }

   
    public void adicionarPoupanca(Poupanca poupanca){
        poupanca.setArquivoPoupanca(this);
        listaPoupanca.add(poupanca);
        
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
     * @return the dataArquivo
     */
    public Date getDataArquivo() {
        return dataArquivo;
    }

    /**
     * @param dataArquivo the dataArquivo to set
     */
    public void setDataArquivo(Date dataArquivo) {
        this.dataArquivo = dataArquivo;
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
