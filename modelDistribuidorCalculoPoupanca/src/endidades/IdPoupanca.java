/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author PC_LENOVO
 */
@Embeddable
public class IdPoupanca implements  Serializable{
    
    @Column(name = "npj", nullable = false)
    private Long npj;
    @Column(name = "cnj", nullable = false)
    private String cnj;

    public IdPoupanca() {
    }

    public IdPoupanca(Long npj, String cnj) {
        this.npj = npj;
        this.cnj = cnj;
    }

    
    
    
    
    /**
     * @return the npj
     */
    public Long getNpj() {
        return npj;
    }

    /**
     * @param npj the npj to set
     */
    public void setNpj(Long npj) {
        this.npj = npj;
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
