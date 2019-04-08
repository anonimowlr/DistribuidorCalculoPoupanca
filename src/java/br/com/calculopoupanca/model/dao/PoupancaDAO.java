/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.model.dao;

import endidades.Funcionario;
import endidades.IdPoupanca;
import endidades.Poupanca;
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

}
