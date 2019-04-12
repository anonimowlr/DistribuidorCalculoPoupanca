/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import endidades.IdPoupanca;
import endidades.Poupanca;
import javax.persistence.EntityManager;
import jpa.EntityManagerUtil;




/**
 *
 * @author f5078775
 */
public class PoupancaDAO {
    
    private String mensagem;
   
    
    private EntityManager em = EntityManagerUtil.getEntityManager();

    
    public void salvar(Poupanca poupanca){
        
      
        try{
        
         em.getTransaction().begin();
         em.persist(poupanca);
         em.getTransaction().commit();  
        }catch(Exception e){
          em.getTransaction().rollback();
        }
        
        
        
       
        
        
        
    }
    
    
    
    
     public Poupanca localizarObjeto(IdPoupanca idPoupanca){
            
            Poupanca objeto = (Poupanca) em.find(Poupanca.class, idPoupanca);
            return objeto;
            
        }
    
    
    
    
    
    
    
    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

   
    
    
   
    
    
    
    
    
}
