/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.model.dao;

import endidades.IdPoupanca;
import endidades.Observacao;


/**
 *
 * @author f5078775
 */
public class ObservacaoDAO<T,D> extends DAOGenerico<Observacao, IdPoupanca>{

    public ObservacaoDAO() {
       super();
       classePersistente = Observacao.class;
       ordem = "obs";
       maximoObjeto = 1;
       
       
        
    }
   
    
    
    
}
