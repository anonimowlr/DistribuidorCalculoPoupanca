
package br.com.calculopoupanca.controller;

import br.com.calculopoupanca.model.dao.FuncionarioDAO;
import entidades.Funcionario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@SessionScoped
@ManagedBean
public class FuncionarioBean extends CrudBean<Funcionario, FuncionarioDAO>{
    
    private FuncionarioDAO funcionarioDAO;
    
 
    
    
    
    
    
    
    
    
   
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    
    Funcionario usuario = (Funcionario) session.getAttribute("usuarioLogado");
    
    
    @PostConstruct // teste para executar instrução ao iniciar ete bean
    public void init(){
        
            if(usuario.getFuncao()== 4438 || usuario.getFuncao()== 4750 || usuario.getFuncao()== 4438 || usuario.getFuncao()== 4665
                    || usuario.getFuncao()== 7001){
                
                mudarParaCargoGerencial();
                
            }
            
            
            
            
                 
    }
    
    
    
    
    
    
    
    
    
    
    public String getPermissao(){
      return null;  
    }
    
    
    
    
    
    
    public String getFunciLogado() {
    
    String matricula = usuario.getChave();
    return "http://connections.bb.com.br/profiles/photo.do?uid=" + matricula;
        

 }
    
    public String getNomeInteiro(){
        
         String nome = usuario.getNome();
        return  nome;
    }
    
    
   
    public String getNomeAbreviado(){
        
         String nomeGerra = usuario.getNomeGuerra();
         return  nomeGerra;
    }
    
    
    public String getChave(){
        String chave = usuario.getChave();
        return chave;
    }
    
    
    public String getGerente(){
        String gerente = usuario.getGerente();
        return gerente;
    }

    
    public int getUor(){
        int uor =  usuario.getUORHabitual();
         return uor;
        
    }
    
    
    public String getFuncao(){
        String funcao = usuario.getNomeFuncao();
        
        return funcao;
    }
    
    public int codigoFuncao(){
        int codigoFuncao = usuario.getFuncao();
        return codigoFuncao;
    }
   

    @Override
    public Funcionario criarNovaEntidade() {
       return new Funcionario();
    }

    @Override
    public FuncionarioDAO getDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    


    
    
    
    
    
    
    
    
    
}
