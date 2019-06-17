/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.controller;

import br.com.calculopoupanca.model.dao.ObservacaoDAO;
import br.com.calculopoupanca.model.dao.PlanoDAO;
import br.com.calculopoupanca.model.dao.PoupancaDAO;
import entidades.ComplementoPoupanca;
import entidades.IdPoupanca;
import entidades.Observacao;
import entidades.Plano;
import entidades.Poupanca;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.Util;

/**
 *
 * @author f5078775
 */
@ManagedBean
@ViewScoped
public class ControlePlano implements Serializable {

    
    private Plano plano;
    private PlanoDAO<Plano, IdPoupanca> daoPlano;
    private List<Plano> listaPlano = new ArrayList<>();

    public ControlePlano() {
        daoPlano = new PlanoDAO<>();
    }

    
    
    
    
    
    
    /**
     * @return the plano
     */
    public Plano getPlano() {
        return plano;
    }

    /**
     * @param plano the plano to set
     */
    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    /**
     * @return the daoPlano
     */
    public PlanoDAO<Plano, IdPoupanca> getDaoPlano() {
        return daoPlano;
    }

    /**
     * @param daoPlano the daoPlano to set
     */
    public void setDaoPlano(PlanoDAO<Plano, IdPoupanca> daoPlano) {
        this.daoPlano = daoPlano;
    }

    /**
     * @return the listaPlano
     */
    public List<Plano> getListaPlano() {
        return listaPlano;
    }

    /**
     * @param listaPlano the listaPlano to set
     */
    public void setListaPlano(List<Plano> listaPlano) {
        this.listaPlano = listaPlano;
    }
    
   
}
