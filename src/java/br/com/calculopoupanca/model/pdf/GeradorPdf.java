/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.model.pdf;

import br.com.calculopoupanca.util.Utils;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import endidades.ComplementoPoupanca;
import endidades.Poupanca;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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
            PdfWriter.getInstance(document, new FileOutputStream("/opt/apache-tomcat-9.0.8/webapps/utilitario/Resumo Poupadores NPJ - " + poupanca.getIdPoupanca().getNpj().toString() + ".pdf"));

            document.open();

            Image img = Image.getInstance("/opt/apache-tomcat-9.0.8/webapps/utilitario/LogoRetangular.png");
            img.setAbsolutePosition(72, 775);
            document.add(img);

            PdfPTable table = new PdfPTable(new float[]{15f, 8f, 7f});
            PdfPCell celulaNome = new PdfPCell(new Phrase("Poupador"));
            celulaNome.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell celulaDataNasc = new PdfPCell(new Phrase("CPF"));
            celulaDataNasc.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell celulaSexo = new PdfPCell(new Phrase("Valor Direito"));
            celulaSexo.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(celulaNome);
            table.addCell(celulaDataNasc);
            table.addCell(celulaSexo);

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

                table.addCell(celula1);
                table.addCell(celula2);
                table.addCell(celula3);

            }

            document.add(table);

        } catch (FileNotFoundException | DocumentException ex) {
            Util.mensagemErro(Util.getMensagemErro(ex));
        } finally {
            document.close();
        }
        try {
            Desktop.getDesktop().open(new File("/opt/apache-tomcat-9.0.8/webapps/utilitario/Resumo Poupadores NPJ - " + poupanca.getIdPoupanca().getNpj().toString() + ".pdf"));

//            ProcessBuilder p = new ProcessBuilder();
//            p.command("/opt/apache-tomcat-9.0.8/webapps/utilitario/Resumo Poupadores NPJ - " + poupanca.getIdPoupanca().getNpj().toString() + ".pdf");
//            p.start();
            

        } catch (IOException ex) {
            Util.mensagemErro(Util.getMensagemErro(ex));
        }

    }

}
