/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.model.dao;

import endidades.ComplementoPoupanca;
import endidades.IdPoupanca;
import endidades.Poupanca;

/**
 *
 * @author f5078775
 */
public class ComplementoDAO<T,D> extends DAOGenerico<ComplementoPoupanca, IdPoupanca>{

    public ComplementoDAO() {
       super();
       classePersistente = ComplementoPoupanca.class;
       ordem = "npj";
       maximoObjeto = 3000;
       
      
       
        
    }
   
    
    
    
}
