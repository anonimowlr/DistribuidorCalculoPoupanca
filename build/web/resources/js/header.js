$(document).ready(function (){
     
   var favicon = document.querySelector('link[rel="shortcut icon"]');
    
  
        favicon = document.createElement('link');
        favicon.setAttribute('rel', 'shortcut icon');
        var head = document.querySelector('head');
        head.appendChild(favicon);
    
    
    
    favicon.setAttribute('type', 'image/png');
    favicon.setAttribute('href', "resources/img/bb-logo.png");
   
   
   
});


function  saltarConta(param){
    var parametro = param;
    
    if(parametro.length===3){
        
        $("#txtConta").focus();
    }
    
   
    
}