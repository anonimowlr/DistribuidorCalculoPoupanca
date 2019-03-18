/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import dao.PoupancaDAO;
import endidades.ComplementoPoupanca;
import endidades.IdPoupanca;
import endidades.Poupanca;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author f5078775
 */
public class TestePersistirPoupanca {
    
    
    public static void main(String[] args) {
        
        List<ComplementoPoupanca> listaComplemento = new ArrayList<>();
        PoupancaDAO poupancaDAO = new PoupancaDAO();
        Poupanca poupanca  = new Poupanca();
        ComplementoPoupanca complementoPoupanca = new ComplementoPoupanca();
        IdPoupanca idPoupanca = new IdPoupanca();
        idPoupanca.setNpj(new Long("123456645"));
        idPoupanca.setCnj("2020124546666");
        
       poupanca.setAdvogadoAdverso("advogado adverso");
       poupanca.setIdPoupanca(idPoupanca);
       complementoPoupanca.setAgencia(3848);
       
       
       poupanca.adicionarComplementoPoupanca(complementoPoupanca);
      
       
       poupancaDAO.salvar(poupanca);
       
       
        
        
        
        
    }
    
}
