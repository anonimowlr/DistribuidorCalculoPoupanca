$(document).ready(function () {
    $('#loading').hide();
    
    
    
    
       $('#tabelaCalculo').DataTable();
           
          
       
      
      
 
        
        


    
      
      
      
});


function  avaliarExlusao(param){
   
 swal("Press a button!");



    
   swal({
  title: "Tem certeza?",
  text: "Uma vez excluído, não poderá mais acessar este registro",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((willDelete) => {
  if (willDelete) {
      
        $.ajax({
        url: 'teste'
    }).done(function (resposta) {
        
          swal("registro-código  " + param + "  excluído com sucesso! --- valor retornado do controller :" + resposta , {
      icon: "success",
    });
     //  $('#conteudo').html(resposta);
       
       
   });
      
      
      
  
  } else {
    swal("Cancelada a exclusão");
  }
});
     
  



 
}

 




$(document)
    .ajaxStart(function () {
        $('#loading').show();
    })
    .ajaxStop(function () {
        $('#loading').hide();
    });