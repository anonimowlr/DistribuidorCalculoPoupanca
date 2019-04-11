/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.model.dao;

import endidades.ComplementoPoupanca;
import endidades.Funcionario;
import endidades.IdPoupanca;
import endidades.Poupanca;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import util.Util;

/**
 *
 * @author f5078775
 */
public class PoupancaDAO<T, D> extends DAOGenerico<Poupanca, IdPoupanca> {

    private List<T> listaPoupanca;
    private List<T> listaPoupancaPendencias;

    public PoupancaDAO() {
        super();
        classePersistente = Poupanca.class;
        ordem = "npj";
        maximoObjeto = 3000000;
        chaveComposta = IdPoupanca.class;
        em.clear();

    }

    /**
     * @return the listaPoupanca
     */
    public List<T> getListaPoupanca() {

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

        Funcionario usuario = (Funcionario) session.getAttribute("usuarioLogado");
        String jpql = "From  " + classePersistente.getSimpleName() + " c " + " where " + " (c.status = null and c.avocado = null)  or (c.avocado = 'SIM'  and c.funciAvocado = '" + usuario.getChave() + "')" + "  order by " + ordem;

        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(1).getResultList();
    }

    /**
     * @param listaPoupanca the listaPoupanca to set
     */
    public void setListaPoupanca(List<T> listaPoupanca) {
        this.listaPoupanca = listaPoupanca;
    }

    public void salvarAvocado(T objeto) {
        try {
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();

        } catch (Exception e) {
            rollback();

            mensagem = "Erro ao avocar - " + Util.getMensagemErro(e);

        }

    }

    /**
     * @return the listaPoupancaPendencias
     */
    public List<T> getListaPoupancaPendencias() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

        Funcionario usuario = (Funcionario) session.getAttribute("usuarioLogado");
        String jpql = "From  " + classePersistente.getSimpleName() + " c " + " where " + " (c.status = null and c.avocado = null)  or (c.avocado = 'SIM'  and c.funciAvocado = '" + usuario.getChave() + "')" + "  order by " + ordem;

        return em.createQuery(jpql).getResultList();
    }

    /**
     * @param listaPoupancaPendencias the listaPoupancaPendencias to set
     */
    public void setListaPoupancaPendencias(List<T> listaPoupancaPendencias) {
        this.listaPoupancaPendencias = listaPoupancaPendencias;
    }

    public void somarValorPoupador(Poupanca poupanca) {
       

        if(poupanca.getListaComplementoPoupanca().size()<=1){
            poupanca.getListaComplementoPoupanca().get(0).setSomatorioPoupador(poupanca.getListaComplementoPoupanca().get(0).getValorAcordo());
            return ;
        } 
        
//           for (ComplementoPoupanca complementoPoupanca : poupanca.getListaComplementoPoupanca()) {//limpar todos os  somatoriosPoupador
//                    complementoPoupanca.setValorDireito(null);
//                    
//                }
        
      
            int i = 0;
          
            while(i< poupanca.getListaComplementoPoupanca().size()){
                 BigDecimal somatorio = new BigDecimal("0.00");
                
                    poupanca.getListaComplementoPoupanca().get(i).setSomatorioPoupador(poupanca.getListaComplementoPoupanca().get(i).getValorAcordo());
                        
                        int j =0;
                        while(j<poupanca.getListaComplementoPoupanca().size()){
                            
                            
                            
                          
                          
                            if(poupanca.getListaComplementoPoupanca().get(i).getCpf().equals(poupanca.getListaComplementoPoupanca().get(j).getCpf()) && poupanca.getListaComplementoPoupanca().get(i).getValorAcordo()!=null && poupanca.getListaComplementoPoupanca().get(j).getValorAcordo()!= null && i!=j){
                              
                                 somatorio =somatorio.add( poupanca.getListaComplementoPoupanca().get(j).getValorAcordo());
                                
                                
                               if(i!=j) {
                                    poupanca.getListaComplementoPoupanca().get(j).setSomatorioPoupador(null);
                               }
                               
                               
                               // somatorio = somatorio.add(poupanca.getListaComplementoPoupanca().get(j).getValorAcordo());
                            
                                 poupanca.getListaComplementoPoupanca().get(i).setSomatorioPoupador(poupanca.getListaComplementoPoupanca().get(i).getValorAcordo().add(somatorio));
                                 
                                
                            } 
                            j++;
                        }
                
                
                i++;
            }
       
            
        
        
        
        
       
        



    }

}
