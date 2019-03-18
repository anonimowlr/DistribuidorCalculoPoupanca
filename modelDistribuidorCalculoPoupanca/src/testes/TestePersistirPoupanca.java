/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import dao.PoupancaDAO;
import endidades.Poupanca;

/**
 *
 * @author f5078775
 */
public class TestePersistirPoupanca {
    
    
    public static void main(String[] args) {
        PoupancaDAO poupancaDAO = new PoupancaDAO();
        Poupanca poupanca  = new Poupanca();
        
       poupanca.setAdvogadoAdverso("advogado adverso");
       
       
       
       
       poupancaDAO.salvar(poupanca);
       
       
        
        
        
        
    }
    
}
