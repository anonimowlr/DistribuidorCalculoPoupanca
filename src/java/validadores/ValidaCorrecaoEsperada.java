/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validadores;

import br.com.calculopoupanca.controller.ControleListaCompleta;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author jocimar
 */
@FacesValidator(value = "validaCorrecaoEsperada")
public class ValidaCorrecaoEsperada extends ControleListaCompleta implements Validator{

   
    
    
    
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
       if (value == null) {
            return;
        }
      
      
       
       
        throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Estou validando", "Estou validando " ));
        
        
        
    }
    
    
    private String calcularDigitoVerificador(String num) {


            Integer primDig, segDig;
            int soma = 0;
            int peso = 10;
            for (int i = 0; i < num.length(); i++) {
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
            }
            if (soma % 11 == 0 | soma % 11 == 1) {
                primDig = new Integer(0);
            } else {
                primDig = new Integer(11 - (soma % 11));
            }
            soma = 0;
            peso = 11;
            for (int i = 0; i < num.length(); i++) {
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
            }
            soma += primDig.intValue() * 2;
            if (soma % 11 == 0 | soma % 11 == 1) {
                segDig = new Integer(0);
            } else {
                segDig = new Integer(11 - (soma % 11));
            }
            return primDig.toString() + segDig.toString();
        }

    
    
}
