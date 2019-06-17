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
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entidades.ComplementoPoupanca;
import entidades.Poupanca;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
            img.setAbsolutePosition(72, 765);
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
    
    
    
    
     public void gerarDocumentoIndividual(ComplementoPoupanca complementoPoupanca) throws IOException {

        //  Util.mensagemInformacao(poupanca.getIdPoupanca().getNpj().toString());
        Document document = new Document(PageSize.A4, 10, 10, 70, 70);
        Font font = new Font(FontFamily.TIMES_ROMAN);
        Font font14pt = new Font(FontFamily.TIMES_ROMAN, 14);
        Font font10pt = new Font(FontFamily.HELVETICA, 10);
        Font font8Boldpt = new Font(FontFamily.HELVETICA, 8,font.BOLD);
        Font font7pt = new Font(FontFamily.HELVETICA, 7);
        Font font5pt = new Font(FontFamily.HELVETICA, 5);

        try {
            PdfWriter.getInstance(document, new FileOutputStream("/usr/local/apache-tomcat-8.0.15/webapps/docsPoupanca/Resumo Poupador CPF - " + Utils.tratarVariavel(complementoPoupanca.getCpf())+  ".pdf"));
            //PdfWriter.getInstance(document, new FileOutputStream("/opt/apache-tomcat-8.5.39/webapps/utilitario/Resumo Poupador CPF - " + complementoPoupanca.getCpf()+ ".pdf"));
            // PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\f5078775\\Desktop\\DistribuidorPoupancaTeste\\Resumo Poupador CPF - " + Utils.tratarVariavel(complementoPoupanca.getCpf())+  ".pdf"));

            document.open();

            Image img = Image.getInstance("/usr/local/apache-tomcat-8.0.15/webapps/docsPoupanca/img/LogoRetangular.png");
           // Image img = Image.getInstance("/opt/apache-tomcat-8.5.39/webapps/utilitario/LogoRetangular.png");
            //Image img = Image.getInstance("C:\\Users\\f5078775\\Desktop\\DistribuidorPoupancaTeste\\LogoRetangular.png");
            img.setAbsolutePosition(72, 765);
            document.add(img);

            PdfPTable table = new PdfPTable(new float[]{6f,6f,6f,6f,6f,6f,6f,6f,6f});
            PdfPTable tabelaResumoValor = new PdfPTable(new float[]{48f,6f});
            PdfPTable tabelaResumoDesconto = new PdfPTable(new float[]{36f,6f,6f,6f});
            PdfPTable tabelaResumoValorApurado = new PdfPTable(new float[]{48f,6f});
            PdfPTable tabelaResumoHonorario = new PdfPTable(new float[]{42f,6f,6f});
            PdfPTable tabelaResumoFinal = new PdfPTable(new float[]{48f,6f});
           
            PdfPCell celulaAgencia = new PdfPCell(new Phrase("Agência",font7pt));
            celulaAgencia.setVerticalAlignment(Element.ALIGN_CENTER);
            
            
            PdfPCell celulaConta = new PdfPCell(new Phrase("Contas vinculadas ao CPF/CNPJ",font7pt));
            celulaConta.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            
            PdfPCell celulaDataSaldo = new PdfPCell(new Phrase("Data do Saldo",font7pt));
            celulaDataSaldo.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell celulaDataBase = new PdfPCell(new Phrase("Data Base",font7pt));
            celulaDataBase.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            
            PdfPCell celulaSaldo = new PdfPCell(new Phrase("Saldo",font7pt));
            celulaSaldo.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell celulaCorrecaoEsperada = new PdfPCell(new Phrase("Correção Monetária esperada",font7pt));
            celulaCorrecaoEsperada.setHorizontalAlignment(Element.ALIGN_CENTER);
           
            PdfPCell celulaPlano = new PdfPCell(new Phrase("PLANO ECONÔMICO",font7pt));
            celulaPlano.setHorizontalAlignment(Element.ALIGN_CENTER);
           
            PdfPCell celulaIndice = new PdfPCell(new Phrase("ÍNDICE",font7pt));
            celulaIndice.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell celulaValorApurado = new PdfPCell(new Phrase("VALOR BASE CALCULADO A PARTIR DO SALDO",font5pt));
            celulaValorApurado.setHorizontalAlignment(Element.ALIGN_CENTER);
           
            PdfPCell celulaTotalBruto= new PdfPCell(new Phrase("Total Bruto",font8Boldpt));
            celulaTotalBruto.setHorizontalAlignment(Element.ALIGN_RIGHT);
           
            PdfPCell celulaFatorDesconto= new PdfPCell(new Phrase("Fator Desconto",font8Boldpt));
            celulaFatorDesconto.setHorizontalAlignment(Element.ALIGN_RIGHT);
           
            PdfPCell celulaValorApuradoLiquido= new PdfPCell(new Phrase("Valor Apurado",font8Boldpt));
            celulaValorApuradoLiquido.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            PdfPCell celulaHonorario= new PdfPCell(new Phrase("Honorários Advocatícios",font8Boldpt));
            celulaHonorario.setHorizontalAlignment(Element.ALIGN_RIGHT);
           
            PdfPCell celulaResumoFinal= new PdfPCell(new Phrase("TOTAL FINAL (VALOR APURADO + HONORÁRIOS)",font8Boldpt));
            celulaResumoFinal.setHorizontalAlignment(Element.ALIGN_RIGHT);
           
           

            table.addCell(celulaAgencia);
            table.addCell(celulaConta);
            table.addCell(celulaDataSaldo);
            table.addCell(celulaDataBase);
            table.addCell(celulaSaldo);
            table.addCell(celulaCorrecaoEsperada);
            table.addCell(celulaPlano);
            table.addCell(celulaIndice);
            table.addCell(celulaValorApurado);
            tabelaResumoValor.addCell(celulaTotalBruto);
            tabelaResumoDesconto.addCell(celulaFatorDesconto);
            tabelaResumoValorApurado.addCell(celulaValorApuradoLiquido);
            tabelaResumoHonorario.addCell(celulaHonorario);
            tabelaResumoFinal.addCell(celulaResumoFinal);
            
            PdfPTable tabelaTitulo = new PdfPTable(new float[]{48f});
            
            PdfPCell celulaTitulo= new PdfPCell(new Phrase("ATUALIZAÇÃO DAS DIFERENÇAS COM BASE NOS PARÂMETROS DO ACORDO DA FEBRABAN",font8Boldpt));
            celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabelaTitulo.addCell(celulaTitulo);
           
            Paragraph p = new Paragraph();
            p.setIndentationLeft(47f);
            Paragraph p2 = new Paragraph();
            p2.setIndentationLeft(47f);
           
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
           
           
            
            p2.add(new Phrase("TITULAR: " + complementoPoupanca.getPoupador() + " - CPF/CNPJ: " + complementoPoupanca.getCpf(),font7pt));
            p.add(new Phrase("PROCESSO/NPJ: " + complementoPoupanca.getPoupanca().getIdPoupanca().getNpj().toString(),font7pt));
            //p.setAlignment(Element.ALIGN_RIGHT);
             document.add(tabelaTitulo);
            
            document.add(new Paragraph(" "));
           
            document.add(p2);
            document.add(new Paragraph(" "));
           
            document.add(p);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            for (ComplementoPoupanca c : complementoPoupanca.getPoupanca().getListaComplementoPoupanca()) {

                if (!c.getCpf().equals(complementoPoupanca.getCpf())) {
                    continue;
                }
                
                if(c.getValorAcordo() == null){
                  continue;
                }

                PdfPCell celula1 = new PdfPCell(new Phrase(c.getAgencia().toString(),font7pt));
                celula1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celula2 = new PdfPCell(new Phrase(Utils.tratarConta(c.getConta().toString()),font7pt));
                celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celula3 = new PdfPCell(new Phrase(Utils.formatDataTexto(c.getDataBase().toString()),font7pt));
                 celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celula4 = new PdfPCell(new Phrase(Utils.formatDataTexto(c.getDataBase().toString()).subSequence(0, 2).toString(),font7pt));
                celula4.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celula5 = new PdfPCell(new Phrase(Utils.converterToMoney(c.getValorBase().toString()),font7pt));
                celula5.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celula6 = new PdfPCell(new Phrase(Utils.converterToMoney(c.getCorrecaoEsperada().toString()),font7pt));
                 celula6.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celula7 = new PdfPCell(new Phrase(c.getPlano(),font7pt));
               
                PdfPCell celula8 = null;
                if(c.getPlano().equals("BRESSER")){
                    celula8 = new PdfPCell(new Phrase("0,04277",font7pt));  
                     celula8.setHorizontalAlignment(Element.ALIGN_RIGHT);
                } else if(c.getPlano().equals("COLLOR I")){
                      celula8 = new PdfPCell(new Phrase("000000",font7pt));  
                      celula8.setHorizontalAlignment(Element.ALIGN_RIGHT);
                }else if(c.getPlano().equals("COLLOR II")){
                     celula8 = new PdfPCell(new Phrase("0,2235907655",font7pt));  
                     celula8.setHorizontalAlignment(Element.ALIGN_RIGHT);
                }else if(c.getPlano().equals("VERAO")){
                      celula8 = new PdfPCell(new Phrase("4,09818",font7pt));  
                      celula8.setHorizontalAlignment(Element.ALIGN_RIGHT);
                }
               
               
                
                PdfPCell celula9 = new PdfPCell(new Phrase(Utils.converterToMoney(c.getValorAcordo().toString()),font7pt));
                celula9.setHorizontalAlignment(Element.ALIGN_RIGHT);

                table.addCell(celula1);
                table.addCell(celula2);
                table.addCell(celula3);
                table.addCell(celula4);
                table.addCell(celula5);
                table.addCell(celula6);
                table.addCell(celula7);
                table.addCell(celula8);
                table.addCell(celula9);
                if(c.getSomatorioPoupador()!=null){
                    
                    PdfPCell celulaSomatorio = new PdfPCell(new Phrase(Utils.converterToMoney(c.getSomatorioPoupador().toString()),font8Boldpt));
                     celulaSomatorio.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tabelaResumoValor.addCell(celulaSomatorio);
                    
                     PdfPCell celulaFaixaDesconto = new PdfPCell(new Phrase(c.getFaixaDesconto(),font8Boldpt));
                     tabelaResumoDesconto.addCell(celulaFaixaDesconto);
                     
                     PdfPCell celulaPercentualDesconto = new PdfPCell(new Phrase((c.getPercentualDesconto().multiply(new BigDecimal("100")).toString() + "%"),font8Boldpt));
                     celulaPercentualDesconto.setHorizontalAlignment(Element.ALIGN_RIGHT);
                     tabelaResumoDesconto.addCell(celulaPercentualDesconto);
                    
                     PdfPCell celulaValorDesconto = new PdfPCell(new Phrase(Utils.converterToMoney(c.getValorDesconto().toString()),font8Boldpt));
                     celulaValorDesconto.setHorizontalAlignment(Element.ALIGN_RIGHT);
                     tabelaResumoDesconto.addCell(celulaValorDesconto);
                    
                     PdfPCell celulaValorLiquido = new PdfPCell(new Phrase(Utils.converterToMoney(c.getValorApurado().toString()),font8Boldpt));
                     celulaValorLiquido.setHorizontalAlignment(Element.ALIGN_RIGHT);
                     tabelaResumoValorApurado.addCell(celulaValorLiquido);
                     
                     PdfPCell celulaPercentualHonor = new PdfPCell(new Phrase("10%",font8Boldpt));
                     celulaPercentualHonor.setHorizontalAlignment(Element.ALIGN_RIGHT);
                     tabelaResumoHonorario.addCell(celulaPercentualHonor);
                    
                     PdfPCell celulaValorHonor = new PdfPCell(new Phrase(Utils.converterToMoney(c.getValorHonorario().toString()),font8Boldpt));
                     celulaValorHonor.setHorizontalAlignment(Element.ALIGN_RIGHT);
                     tabelaResumoHonorario.addCell(celulaValorHonor);
                     
                     PdfPCell celulaValorFinal = new PdfPCell(new Phrase(Utils.converterToMoney(c.getValorDespendidoBB().toString()),font8Boldpt));
                     celulaValorFinal.setHorizontalAlignment(Element.ALIGN_RIGHT);
                     tabelaResumoFinal.addCell(celulaValorFinal);
                     
                }

            }

            document.add(table);
            document.add(tabelaResumoValor);
            document.add(tabelaResumoDesconto);
            document.add(tabelaResumoValorApurado);
            document.add(tabelaResumoHonorario);
            document.add(tabelaResumoFinal);
            

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
        //FileInputStream inputStream = new FileInputStream(new File("/opt/apache-tomcat-8.5.39/webapps/utilitario/Resumo Poupadores NPJ - " + poupanca.getIdPoupanca().getNpj() + ".pdf"));
        //FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\f5078775\\Desktop\\DistribuidorPoupancaTeste\\Resumo Poupadores NPJ - " + poupanca.getIdPoupanca().getNpj() + ".pdf"));
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
    
    
     public void downloadIndividual(ComplementoPoupanca complemento)  {

      
      try{  
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();

        externalContext.responseReset();
        externalContext.setResponseContentType("application/pdf");
        externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"Resumo Poupador CPF -" + " " + Utils.tratarVariavel(complemento.getCpf())+  ".pdf\"");
        
        String nomeArquivo = "Resumo Poupador CPF - " + complemento.getCpf() + ".pdf";
        
        FileInputStream inputStream = new FileInputStream(new File("/usr/local/apache-tomcat-8.0.15/webapps/docsPoupanca/Resumo Poupador CPF - " + Utils.tratarVariavel(complemento.getCpf()) + ".pdf"));
        //FileInputStream inputStream = new FileInputStream(new File("/opt/apache-tomcat-8.5.39/webapps/utilitario/Resumo Poupador CPF - " + complemento.getCpf() + ".pdf"));
        //FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\f5078775\\Desktop\\DistribuidorPoupancaTeste\\Resumo Poupador CPF - " + Utils.tratarVariavel(complemento.getCpf()) + ".pdf"));
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
