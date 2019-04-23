/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.model.pdf;

import br.com.calculopoupanca.util.Utils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import endidades.ComplementoPoupanca;
import endidades.Poupanca;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import util.Util;

/**
 *
 * @author PC_LENOVO
 */
public class GeradorPdf {

    public void gerarDocumento(Poupanca poupanca) throws IOException {

        //  Util.mensagemInformacao(poupanca.getIdPoupanca().getNpj().toString());
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("/usr/local/apache-tomcat-8.0.15/webapps/docsPoupanca/Resumo Poupadores NPJ - " + poupanca.getIdPoupanca().getNpj().toString() + ".pdf"));

            document.open();

            //Image img = Image.getInstance("/usr/local/apache-tomcat-8.0.15/webapps/docsPoupanca/img/LogoRetangular.png");
            Image img = Image.getInstance("/usr/local/apache-tomcat-8.0.15/webapps/docsPoupanca/img/LogoRetangular.png");
            img.setAbsolutePosition(72, 775);
            document.add(img);

            PdfPTable table = new PdfPTable(new float[]{15f, 8f, 7f,7f});
            PdfPCell celulaPoupador = new PdfPCell(new Phrase("Poupador"));
            celulaPoupador.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell celulaCpf = new PdfPCell(new Phrase("CPF/CNPJ"));
            celulaCpf.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell celulaValorBruto = new PdfPCell(new Phrase("Valor Direito Bruto"));
            celulaValorBruto.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell celulaValorApurado = new PdfPCell(new Phrase("Valor Apurado"));
            celulaValorApurado.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(celulaPoupador);
            table.addCell(celulaCpf);
            table.addCell(celulaValorBruto);
            table.addCell(celulaValorApurado);

            Paragraph p = new Paragraph();
            p.add(new Phrase("NPJ: " + poupanca.getIdPoupanca().getNpj().toString()));
            p.setAlignment(Element.ALIGN_RIGHT);

            document.add(p);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            for (ComplementoPoupanca complementoPoupanca : poupanca.getListaComplementoPoupanca()) {

                if (complementoPoupanca.getSomatorioPoupador() == null) {
                    continue;
                }

                PdfPCell celula1 = new PdfPCell(new Phrase(complementoPoupanca.getPoupador()));
                PdfPCell celula2 = new PdfPCell(new Phrase(complementoPoupanca.getCpf()));
                PdfPCell celula3 = new PdfPCell(new Phrase(Utils.converterToMoney(complementoPoupanca.getSomatorioPoupador().toString())));
                PdfPCell celula4 = new PdfPCell(new Phrase(Utils.converterToMoney(complementoPoupanca.getValorApurado().toString())));

                table.addCell(celula1);
                table.addCell(celula2);
                table.addCell(celula3);
                table.addCell(celula4);

            }

            document.add(table);
            

        } catch (FileNotFoundException | DocumentException ex) {
            Util.mensagemErro(Util.getMensagemErro(ex));
        } finally {
            document.close();
        }

    }

    public void download(Poupanca poupanca)  {

      
      try{  
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();

        externalContext.responseReset();
        externalContext.setResponseContentType("application/pdf");
        externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"Resumo Poupadores NPJ -" + " " + poupanca.getIdPoupanca().getNpj().toString() +  ".pdf\"");
        
        String nomeArquivo = "Resumo Poupadores NPJ - " + poupanca.getIdPoupanca().getNpj().toString() + ".pdf";
        
        FileInputStream inputStream = new FileInputStream(new File("/usr/local/apache-tomcat-8.0.15/webapps/docsPoupanca/Resumo Poupadores NPJ - " + poupanca.getIdPoupanca().getNpj() + ".pdf"));
        OutputStream out = externalContext.getResponseOutputStream();
        byte[] buffer = new byte[1024];
        int lenght;

        while ((lenght = inputStream.read(buffer)) > 0) {
            out.write(buffer);
        }

        out.flush();
        fc.responseComplete();

        
      }catch(Exception e){
          Util.mensagemErro(Util.getMensagemErro(e));
      }
    }
    
    

}
