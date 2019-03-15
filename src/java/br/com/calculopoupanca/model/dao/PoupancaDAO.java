/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.model.dao;

import endidades.Poupanca;

/**
 *
 * @author f5078775
 */
public class PoupancaDAO<T> extends DAOGenerico<Poupanca, T>{

    public PoupancaDAO() {
       super();
       classePersistente = Poupanca.class;
       ordem = "npj";
       maximoObjeto = 1;
       
        
    }
   
    
    
    
}
