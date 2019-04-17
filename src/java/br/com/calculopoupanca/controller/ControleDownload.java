/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.controller;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author f5078775
 */
@ManagedBean
public class ControleDownload {
    
    
     private StreamedContent file;
     
    public ControleDownload() {        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("C:\\Temp\\Resumo Poupadores NPJ - 91510754935.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "downloaded_boromir.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
    
}
