/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.model.dao;

import br.com.calculopoupanca.util.ErroSistema;
import java.util.List;


/**
 *
 * @author PC_LENOVO
 */
public interface CrudDAO<E> {//E representa a entidade
    
    
    public void salvar(E entidade)throws ErroSistema;
   
    public void deletar(E entidade)throws ErroSistema;
    public List<E> buscar(E entidade)throws ErroSistema;
   
    
    
    
    
}
