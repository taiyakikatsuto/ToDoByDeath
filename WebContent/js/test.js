/**
 *
 */

$(function() {
	$('#login-show').click(function() {
		$('#login-modal').fadeIn();
  	});



  	$('.login-show').click(function() {
  		 var id =  $(this).attr("id");
  		 $('#kanryo').val(id);
		$('#login-modal').fadeIn();
  	});




	$('.close-modal').click(function(){
   	 	$('#login-modal').fadeOut();
   	 	$('#signup-modal').fadeOut();
  	});
});