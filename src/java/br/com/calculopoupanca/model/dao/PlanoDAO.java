/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.model.dao;

import entidades.IdPoupanca;
import entidades.Observacao;
import entidades.Plano;


/**
 *
 * @author f5078775
 */
public class PlanoDAO<T,D> extends DAOGenerico<Observacao, IdPoupanca>{

    public PlanoDAO() {
       super();
       classePersistente = Plano.class;
       ordem = "plano";
       
       
       
        
    }
   
    
    
    
}
