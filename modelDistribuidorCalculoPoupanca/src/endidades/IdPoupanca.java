/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endidades;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author PC_LENOVO
 */
@Embeddable
public class IdPoupanca {
    
    @Column(name = "npj", nullable = false)
    private Long npj;
    @Column(name = "cnj", nullable = false)
    private Long cnj;

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
    public Long getCnj() {
        return cnj;
    }

    /**
     * @param cnj the cnj to set
     */
    public void setCnj(Long cnj) {
        this.cnj = cnj;
    }
    
}
