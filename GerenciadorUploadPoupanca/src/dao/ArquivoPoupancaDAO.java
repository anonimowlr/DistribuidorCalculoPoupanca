/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import endidades.ArquivoPoupanca;
import javax.persistence.EntityManager;
import jpa.EntityManagerUtil;

/**
 *
 * @author f5078775
 */
public class ArquivoPoupancaDAO {
    
    
     private String mensagem;
   
    
    private EntityManager em = EntityManagerUtil.getEntityManager();

    
    public void salvar(ArquivoPoupanca arquivoPoupanca){
        
        em.getTransaction().begin();
        em.persist(arquivoPoupanca);
        em.getTransaction().commit();
        
    }
    
    
}
