//F5078775


package br.com.calculopoupanca.model.dao;

import br.com.calculopoupanca.util.ErroSistema;
import java.util.List;




// DAO GERENICO PARA JDBC

public interface CrudDAO<E> {//E representa a entidade
    
    
    public void salvar(E entidade)throws ErroSistema;
   
    public void deletar(E entidade)throws ErroSistema;
    public List<E> buscar(E entidade)throws ErroSistema;
   
    
    
    
    
}
