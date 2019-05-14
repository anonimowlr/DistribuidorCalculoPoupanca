/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.model.dao;

import endidades.ComplementoPoupanca;
import endidades.Funcionario;
import endidades.IdPoupanca;
import endidades.Poupanca;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author f5078775
 */
public class ComplementoDAO<T,D> extends DAOGenerico<ComplementoPoupanca, IdPoupanca>{

    
    private List<T> listaComplementoPendencias;
    
    public ComplementoDAO() {
       super();
       classePersistente = ComplementoPoupanca.class;
       ordem = "npj";
       maximoObjeto = 300000000;
       
       
      
       
        
    }

    /**
     * @return the listaComplementoPendencias
     */
    public List<T> getListaComplementoPendencias() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

        Funcionario usuario = (Funcionario) session.getAttribute("usuarioLogado");
        String jpql = "From  " + classePersistente.getSimpleName() + " c " + " where " + " (c.plano = null )" + "  order by " + ordem;

        return em.createQuery(jpql).getResultList();
        
    }

    /**
     * @param listaComplementoPendencias the listaComplementoPendencias to set
     */
    public void setListaComplementoPendencias(List<T> listaComplementoPendencias) {
        this.listaComplementoPendencias = listaComplementoPendencias;
    }
   
    
    
    
}
