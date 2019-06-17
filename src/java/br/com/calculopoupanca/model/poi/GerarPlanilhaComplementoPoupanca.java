/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.calculopoupanca.model.poi;


import br.com.calculopoupanca.util.Utils;
import entidades.ComplementoPoupanca;
import entidades.Poupanca;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.Util;

/**
 *
 * @author jocimar
 */
public class GerarPlanilhaComplementoPoupanca {
    private static String fileName;
    private  static String[] columns = {"NPJ", "CNJ","AGENCIA","CONTA","POUPADOR","CPF/CNPJ","OBSERVAÇÃO","COMPLEMENTO", "PLANO","FAZ JUS?","DATA BASE","VALOR BASE", "CORREÇÃO ESPERADA"};
    
    public void criaPlanilha(List<ComplementoPoupanca> listaComplementoPoupanca) throws IOException {

	
		
		
		
		Workbook workbook = new XSSFWorkbook();
		
		
		Sheet sheet = workbook.createSheet("Lista_Acordo_Febraban");
		
		
		Font headerFont = workbook.createFont();
		
		headerFont.setBold(true);
		
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		CellStyle headerCellStyle = workbook.createCellStyle();
		
		headerCellStyle.setFont(headerFont);
		
		
		
		Row headerRow = sheet.createRow(0);
		
		
		for(int i = 0;i<columns.length;i++) {
			
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		
		
		
	  int rowNum = 1;
	  
	  	for (ComplementoPoupanca c : listaComplementoPoupanca) {
	  		
	  		Row row = sheet.createRow(rowNum ++);
	  		row.createCell(0).setCellValue(c.getPoupanca().getIdPoupanca().getNpj().toString());
	  		row.createCell(1).setCellValue(c.getPoupanca().getIdPoupanca().getCnj());
	  		if(c.getAgencia() !=null){
                        row.createCell(2).setCellValue(c.getAgencia().toString());
                            
                        }else{
                            row.createCell(2).setCellValue("");
                        }
	  		
                        if(c.getConta()!=null){
                        row.createCell(3).setCellValue(Utils.tratarConta(c.getConta().toString()));
                            
                        }else{
                            row.createCell(3).setCellValue("");
                        }
                        if(c.getPoupador()!=null){
                        row.createCell(4).setCellValue(c.getPoupador());
                            
                        }else{
                            row.createCell(4).setCellValue("");
                        }
                        if(c.getCpf()!=null){
                        row.createCell(5).setCellValue(c.getCpf());
                            
                        }else{
                            row.createCell(5).setCellValue("");
                        }
                       if(c.getObservacao()!=null){
                        row.createCell(6).setCellValue(c.getObservacao());
                            
                        }else{
                            row.createCell(6).setCellValue("");
                        }
                      
                       if(c.getComplementoObs()!=null){
                        row.createCell(7).setCellValue(c.getComplementoObs());
                            
                        }else{
                            row.createCell(7).setCellValue("");
                        }
                       
                       if(c.getObservacao()!=null){
                        row.createCell(8).setCellValue(c.getPlano());
                            
                        }else{
                            row.createCell(8).setCellValue("");
                        }
                       if(c.getFazJus()!=null){
                        row.createCell(9).setCellValue(c.getFazJus());
                            
                        }else{
                            row.createCell(9).setCellValue("");
                        }
                       if(c.getDataBase()!=null){
                        row.createCell(10).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(c.getDataBase().getTime()));
                            
                        }else{
                            row.createCell(10).setCellValue("");
                        }
                      
                       if(c.getValorBase()!=null){
                        row.createCell(11).setCellValue(Utils.converterToMoney(c.getValorBase().toString()));
                        row.getCell(11).setCellValue(Utils.converteParaDouble(Utils.converterToMoney(c.getValorBase().toString())));
                            
                        }else{
                            row.createCell(11).setCellValue("");
                        }
                      
                       if(c.getCorrecaoEsperada()!=null){
                        row.createCell(12).setCellValue(Utils.converterToMoney(c.getCorrecaoEsperada().toString()));
                        row.getCell(12).setCellValue(Utils.converteParaDouble(Utils.converterToMoney(c.getCorrecaoEsperada().toString())));
                        }else{
                            row.createCell(12).setCellValue("");
                        }
	  		
	  		
	  		
			
		}
		
		
	  	for(int i = 0; i< columns.length;i++) {
	  	
	  		sheet.autoSizeColumn(i);
	  		
	  	}
	  	
	  	
	  	FileOutputStream fileOut = new FileOutputStream("/home/jocimar/Área de Trabalho/TestePlanilha/Relatorio_Acordo_Febraban.xlsx");
                workbook.write(fileOut);
               
                        
	  	fileOut.close();
                
	  	workbook.close();
               
                
              
                
                
	  	
		System.out.println("realizado com sucesso");
		
		
    }

   
    
    
}
