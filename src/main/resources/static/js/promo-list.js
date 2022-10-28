// trabalhando a paginação com barra de rolagem.
var pageNumber=0;
$(document).ready(function(){
	$("#loader-img").hide();
	$("#fim-btn").hide();
});
$(window).scroll(function(){
	
	var scroollTop = Math.ceil($(this).scrollTop());
	var conteudo = Math.ceil($(document).height() - $(window).height());
	console.log('ScrollTop', scroollTop ,' | ', 'conteudo', conteudo)
	if(scroollTop >= conteudo){
		pageNumber ++;
		setTimeout(function(){
			loadBtScrollBar(pageNumber);
		},200)
	}
})

function loadBtScrollBar(pageNumber){
	$.ajax({
		method:"GET",
		url:"/promocao/list/ajax",
		data:{
			page:pageNumber
		},
		beforeSend: function() {
			$("#loader-img").show();
		},
		success:function(response){
			if (response.length > 150) {
				//console.log("resposta>", response);
			   // $('.row').append( $(response).hide().fadeIn(200));
			    $(".row").fadeIn(250, function() {
					$(this).append(response);
					//$('.row').append( $(response).hide().fadeIn(200));
				});
			} else {
				$("#fim-btn").show();
				$("#loader-img").removeClass("loader");
			}
			
		},
		error: function(xhr) {
			alert("Ops, ocorreu um erro: " + xhr.status + " - " + xhr.statusText);
		},
		complete: function() {
			$("#loader-img").hide();
			
		}
	})
}
//Adicionar Likes 
$(document).on("click","button[id*='likes-btn-']", function(){
	var id = $(this).attr("id").split("-")[2];
	console.log("id:", id);
	$.ajax({
		method:"POST",
		url: "/promocao/likes/" + id,
		success:function(response){
			
			$("#likes-count-" + id).text(response);// actualizamos ao front com o resultado do método da url,
		},
		error: function(xhr){
			alert("Ops, ocorreu um erro: " + xhr.status + "," + xhr.statusText);
		}
	});
});

