$(document).ready(function () {
    $('#loading').hide();
    
       
   
   
 
    
    
    $('#testeControler').on('click', function () {
         $.ajax({
        url: 'testeControler'
    }).done(function (resposta) {
        $('#conteudo').append(resposta);
    });
    });
    
    
    $('#jurosRem').on('focusin', function () {
         $.ajax({
        url: 'novo-calculo'
    }).done(function (resposta) {
        $('#conteudo').append(resposta);
    });
    });
    
    
  
    
    
    

});


 function  alerta(){
     alert("teste javascript");
 }


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
      
      
          swal("registro-código  " + param + "  excluído com sucesso! --- valor retornado do controller :"  , {
      icon: "success",
      
      
      
       
       
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