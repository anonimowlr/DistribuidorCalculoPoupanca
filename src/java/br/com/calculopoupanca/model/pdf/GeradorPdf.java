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
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Temp\\Resumo Poupadores NPJ - " + poupanca.getIdPoupanca().getNpj().toString() + ".pdf"));

            document.open();

            //Image img = Image.getInstance("/usr/local/apache-tomcat-8.0.15/webapps/docsfiscal/REJUD/LogoRetangular.png");
            Image img = Image.getInstance("\\\\172.20.0.33\\jsp$\\docsfiscal\\REJUD\\LogoRetangular.png");
            //Image img = Image.getInstance("/usr/local/apache-tomcat-8.0.15/webapps/docsPoupanca/img/LogoRetangular.png");
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

            //Desktop.getDesktop().open(new File("/usr/local/apache-tomcat-8.0.15/webapps/docsPoupanca/Resumo Poupadores NPJ - " + poupanca.getIdPoupanca().getNpj().toString() + ".pdf"));
            download(poupanca);

        } catch (FileNotFoundException | DocumentException ex) {
            Util.mensagemErro(Util.getMensagemErro(ex));
        } finally {
            document.close();
        }

    }

    private void download(Poupanca poupanca) throws IOException {
     final FacesContext fc = FacesContext.getCurrentInstance();
    final ExternalContext externalContext = fc.getExternalContext();

    final File file = new File("C:\\Temp\\LogoRetangular.png");
    
    externalContext.responseReset();
    externalContext.setResponseContentType("image/png");
    externalContext.setResponseContentLength(Long.valueOf(file.lastModified()).intValue());
    externalContext.setResponseHeader("Content-Disposition", "attachment;filename=" + file.getName());

    final HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

    FileInputStream input = new FileInputStream(file);
    byte[] buffer = new byte[1024];
    final ServletOutputStream out = response.getOutputStream();

    while ((input.read(buffer)) != -1) {
        out.write(buffer);
    }

    out.flush();
    fc.responseComplete();

    }

}
