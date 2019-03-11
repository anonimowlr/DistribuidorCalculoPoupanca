$(document).ready(function () {
    $('#loading').hide();
    
    
    
    
        $('#tabelaGsv').DataTable();
           
          
       
      
      
 
        
        


    
      
      
      
});




 


 




$(document)
    .ajaxStart(function () {
        $('#loading').show();
    })
    .ajaxStop(function () {
        $('#loading').hide();
    });