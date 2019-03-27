/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversores;

import br.com.calculopoupanca.jpa.EntityManagerUtil;
import endidades.Observacao;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author PC_LENOVO
 */
@FacesConverter(value = "converterObs")
public class ConverterObs implements  Serializable,Converter{

    
    @Override // da tela para o objeto
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
   
        if(string == null || string.equals("Selecione uma opção")){
            return null;
        }
        
        return EntityManagerUtil.getEntityManager().find(Observacao.class, Integer.parseInt(string));
        
    
    }
 
    @Override   // do objeto para a tela;
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        
            if(o==null){
                return null;
            }
            
            Observacao obj = (Observacao) o;
            return obj.getId().toString();
      
    } 
    
}
