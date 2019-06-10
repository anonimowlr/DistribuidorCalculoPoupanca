/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.controller;

import br.com.calculopoupanca.util.Utils;
import endidades.ComplementoPoupanca;
import java.awt.Dialog;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.Util;

/**
 *
 * @author f5078775
 */
@ManagedBean
@ViewScoped
public class ControleGenerico implements Serializable {

    /**
     * @return the daoObservacao
     */
    private String tipoPessoa = "fisica";
    private ComplementoPoupanca complementoPoupanca;

    private List<ComplementoPoupanca> listaComplementoPoupanca = new ArrayList<>();

    public void calcularValorAcordo() {
        
     
       
        

        try {
            Date data1 = Utils.formataData("01/06/1987");
            Date data2 = Utils.formataData("15/06/1987");
            Date data3 = Utils.formataData("01/01/1989");
            Date data4 = Utils.formataData("15/01/1989");
            Date data5 = Utils.formataData("03/01/1991");
            Date data6 = Utils.formataData("31/01/1991");
            Date data7 = Utils.formataData("01/03/1990");
            Date data8 = Utils.formataData("15/03/1990");

            if ((getComplementoPoupanca().getDataBase().before(data2) || getComplementoPoupanca().getDataBase().equals(data2)) && (getComplementoPoupanca().getDataBase().after(data1) || getComplementoPoupanca().getDataBase().equals(data1))) {
                getComplementoPoupanca().setPlano("BRESSER");
                this.getComplementoPoupanca().setValorAcordo(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("0.04277")).setScale(2, RoundingMode.HALF_EVEN));
                //getComplementoPoupanca().setCorrecaoEsperada(correcaoEsperada());
                getComplementoPoupanca().setFazJus("SIM");
                //getComplementoPoupanca().setObservacao(null);

            } else if ((getComplementoPoupanca().getDataBase().before(data4) || getComplementoPoupanca().getDataBase().equals(data4)) && (getComplementoPoupanca().getDataBase().after(data3) || getComplementoPoupanca().getDataBase().equals(data3))) {
                getComplementoPoupanca().setPlano("VERAO");
                this.getComplementoPoupanca().setValorAcordo(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("4.09818")).setScale(2, RoundingMode.HALF_EVEN));
                //getComplementoPoupanca().setCorrecaoEsperada(correcaoEsperada());
                getComplementoPoupanca().setFazJus("SIM");
                //getComplementoPoupanca().setObservacao(null);

            } else if ((getComplementoPoupanca().getDataBase().before(data6) || getComplementoPoupanca().getDataBase().equals(data6)) && (getComplementoPoupanca().getDataBase().after(data5) || getComplementoPoupanca().getDataBase().equals(data5))) {
                getComplementoPoupanca().setPlano("COLLOR II");
                this.getComplementoPoupanca().setValorAcordo(getComplementoPoupanca().getValorBase().multiply(new BigDecimal("0.0014")).setScale(2, RoundingMode.HALF_EVEN));
               // getComplementoPoupanca().setCorrecaoEsperada(correcaoEsperada());
                getComplementoPoupanca().setFazJus("SIM");
                //getComplementoPoupanca().setObservacao(null);

            } else if ((getComplementoPoupanca().getDataBase().before(data8) || getComplementoPoupanca().getDataBase().equals(data8)) && (getComplementoPoupanca().getDataBase().after(data7) || getComplementoPoupanca().getDataBase().equals(data7))) {
                getComplementoPoupanca().setPlano("COLLOR I");
                getComplementoPoupanca().setFazJus("NAO");
                getComplementoPoupanca().setValorAcordo(null);

                getComplementoPoupanca().setObservacao("COLLOR I");

                getComplementoPoupanca().setComplementoObs("COLLOR I");
                getComplementoPoupanca().setValorBase(null);
            } else {
                getComplementoPoupanca().setFazJus("NAO");
                getComplementoPoupanca().setValorAcordo(null);
                //getComplementoPoupanca().setObservacao(null);
                getComplementoPoupanca().setPlano("NAO FAZ JUS");
                //getComplementoPoupanca().setComplementoObs(null);
                getComplementoPoupanca().setValorBase(null);
                getComplementoPoupanca().setCorrecaoEsperada(null);
            }

        } catch (Exception e) {

            Util.mensagemErro(Util.getMensagemErro(e));

        }
        
        

    }

    /**
     * @return the complementoPoupanca
     */
    public ComplementoPoupanca getComplementoPoupanca() {
        return complementoPoupanca;
    }

    /**
     * @param complementoPoupanca the complementoPoupanca to set
     */
    public void setComplementoPoupanca(ComplementoPoupanca complementoPoupanca) {
        this.complementoPoupanca = complementoPoupanca;
    }

    /**
     * @return the listaComplementoPoupanca
     */
    public List<ComplementoPoupanca> getListaComplementoPoupanca() {
        return listaComplementoPoupanca;
    }

    /**
     * @param listaComplementoPoupanca the listaComplementoPoupanca to set
     */
    public void setListaComplementoPoupanca(List<ComplementoPoupanca> listaComplementoPoupanca) {
        this.listaComplementoPoupanca = listaComplementoPoupanca;
    }

    /**
     * @return the tipoPessoa
     */
    public String getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     * @param tipoPessoa the tipoPessoa to set
     */
    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public boolean isPessoaJuridica() {

        return "juridica".equals(getTipoPessoa());
    }

    public void mudarParaPessoaJuridica() {
        setTipoPessoa("juridica");
    }

    public BigDecimal correcaoEsperada() {
        
       

        BigDecimal indice = new BigDecimal(0.00);

        Integer diaBase = null;

        
        if(getComplementoPoupanca().getPlano().equals("COLLOR II")){
            
        
        
        diaBase = Integer.parseInt(Utils.formatDataTexto(getComplementoPoupanca().getDataBase().toString()).subSequence(0, 2).toString());
        switch (diaBase) {
            case 1:
                indice = new BigDecimal("20.2100");
                break;
            case 2:
                indice = new BigDecimal("20.2100");
                break;

            case 3:
                indice = new BigDecimal("19.4018");
                break;
            case 4:
                indice = new BigDecimal("18.5990");
                break;
            case 5:
                indice = new BigDecimal("18.1401");
                break;

            case 6:
                indice = new BigDecimal("18.4796");
                break;

            case 7:
                indice = new BigDecimal("18.8202");
                break;
            case 8:
                indice = new BigDecimal("18.3606");
                break;

            case 9:
                indice = new BigDecimal("17.9025");
                break;
            case 10:
                indice = new BigDecimal("17.1099");
                break;
            case 11:
                indice = new BigDecimal("16.3225");
                break;

            case 12:
                indice = new BigDecimal("15.5403");
                break;
            case 13:
                indice = new BigDecimal("15.5403");
                break;
            case 14:
                indice = new BigDecimal("15.9639");
                break;

            case 15:
                indice = new BigDecimal("15.6067");
                break;
            case 16:
                indice = new BigDecimal("15.3587");
                break;
            case 17:
                indice = new BigDecimal("14.5830");
                break;

            case 18:
                indice = new BigDecimal("13.8125");
                break;
            case 19:
                indice = new BigDecimal("13.5684");
                break;
            case 20:
                indice = new BigDecimal("14.0919");
                break;

            case 21:
                indice = new BigDecimal("14.6178");
                break;

            case 22:
                indice = new BigDecimal("14.3719");
                break;
            case 23:
                indice = new BigDecimal("14.1266");
                break;
            case 24:
                indice = new BigDecimal("13.1514");
                break;

            case 25:
                indice = new BigDecimal("12.1845");
                break;
            case 26:
                indice = new BigDecimal("11.7385");
                break;
            case 27:
                indice = new BigDecimal("12.2536");
                break;

            case 28:
                indice = new BigDecimal("12.7710");
                break;
        }
        } else {
            
             indice = new BigDecimal("0.2235907655");
        }
        
       
        return (getComplementoPoupanca().getValorBase().multiply((indice)).setScale(2, RoundingMode.HALF_EVEN));


    }
}
