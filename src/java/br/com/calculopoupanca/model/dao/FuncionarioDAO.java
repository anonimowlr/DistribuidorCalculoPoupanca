package br.com.calculopoupanca.model.dao;

import br.com.calculopoupanca.conexao.ConnectionFactory;
import br.com.calculopoupanca.util.ErroSistema;
import endidades.Funcionario;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FuncionarioDAO implements  CrudDAO<Funcionario>{
    
    
    
    
    

   public Funcionario getFuncionario(String chave, String uor) throws ClassNotFoundException, SQLException, ErroSistema {
       
        
        
        
        Connection con = ConnectionFactory.conectar("aof");
        Statement busca = con.createStatement();
        String sql = "SELECT A.*,B.cd_area,C.Quadro, spf_dados.nm_gerente_funci(A.matriculaF, '4750') AS gerente FROM arh.v_funcionarios A " +
                        "LEFT JOIN gestao_equipes.tb_uor_area B " +
                        "ON A.UOR_Posicao=B.uor " +
                        "LEFT JOIN spf_dados.tb_arh_base C " +
                        "ON A.matriculaF = C.tx_mtc_fun " +
                        "WHERE A.matriculaF=TRIM('" + chave + "')";

        ResultSet tabela = busca.executeQuery(sql);

              Funcionario funci = new Funcionario();

        try {
            if (tabela.next()) {
                funci.setChave(tabela.getString("matriculaF"));
                funci.setNome(tabela.getString("nome"));
                funci.setNomeGuerra(tabela.getString("nome_guerra"));
                funci.setFuncao(tabela.getInt("funcao"));
                funci.setNomeFuncao(tabela.getString("nfuncao"));
                funci.setUORHabitual(tabela.getInt("UORHabitual"));
                funci.setUORPosicao(tabela.getInt("UORPosicao"));
                funci.setGerente(tabela.getString("gerente"));
                funci.setSecao(tabela.getString("secao"));
                funci.setQuadro(tabela.getString("Quadro"));
            }
            tabela.close();
        } catch (SQLException e) {
        } finally{
            try{tabela.close();} catch(SQLException e){}
            try{busca.close();} catch(SQLException e){}
            try{con.close();} catch(SQLException e){}
        }
        
        
              
    

//    funci.setChave("F5078775");
//    funci.setNome("JOCIMAR WALTER");
//    funci.setFuncao(4750);
//    funci.setUORHabitual(286409);
//    funci.setUORPosicao(286409);

    return funci;
    }

    @Override
    public void salvar(Funcionario entidade) throws ErroSistema {
    }

    @Override
    public void deletar(Funcionario entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
    @Override
    public List<Funcionario> buscar(Funcionario funci) throws ErroSistema {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      
    }


}
