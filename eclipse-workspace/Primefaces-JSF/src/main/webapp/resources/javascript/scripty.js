var arrayIdsElementsPage = new Array;
var idundefined = 'idundefined';
var classTypeString = 'java.lang.String';
var classTypeLong = 'java.lang.Long';
var classTypeDate = 'java.util.Date';
var classTypeBoolean = 'java.lang.Boolean';
var classTypeBigDecimal = 'java.math.BigDecimal';


function carregarIdElementosPagina() {
	 arrayIdsElementsPage = new Array;
	 for (form = 0 ; form <= document.forms.length; form++){
		 var formAtual = document.forms[form];
		 if (formAtual != undefined) {
			 for (i = 0; i< document.forms[form].elements.length; i++){
				 if(document.forms[form].elements[i].id != '') {
					 arrayIdsElementsPage[i] = document.forms[form].elements[i].id;
				 }
			 }	
		 }
	 }
}

function getValorElementPorId(id) {
	 carregarIdElementosPagina();
	 for (i = 0; i< arrayIdsElementsPage.length; i++){
		 var valor =  ""+arrayIdsElementsPage[i];
		 if (valor.indexOf(id) > -1) {
			return valor;
	}
  }	
	 return idundefined;
}


function logout(contextPath) {

	var post = 'invalidar_session';

	$.ajax({

		type: "POST",
		url: post
	}).always(function(resposta) {

		document.location = contextPath + '/j_spring_security_logout';
	});

}


function invalidarSession(context, pagina) {

	document.location = (context + pagina + ".jsf");
}

function validarSenhaLogin() {

	j_username = document.getElementById("j_username").value;
	j_username = document.getElementById("j_password").value;

	if (j_username === null || j_username.trim() === '') {

		alert("Informe o Login.");
		$('#j_username').focus();
		return false;
	}

	if (j_password === null || j_password.trim() === '') {

		alert("Informe a Senha.");
		$('#j_password').focus();
		return false;
	}

	return true;

}

function abrirMenupop() {

	$("#menupop").show("slow").mouseleave(function() {

		fecharMenupop();
	});

}

function fecharMenupop() {

	if ($("#menupop").is(":visible")) {

		$("#menupop").hide("slow");
	}

}

function redirecionarPagina(context, pagina) {

	pagina = pagina + ".jsf";

	alert(context + pagina);
	document.location = context + pagina;


}

function localeData_pt_br() {
	PrimeFaces.locales['pt'] = {
		closeText: 'Fechar',
		prevText: 'Anterior',
		nextText: 'Próximo',
		currentText: 'Começo',
		monthNames: ['Janeiro', 'Fevereiro', 'Marcio', 'Abril', 'Maio',
			'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro',
			'Dezembro'],
		monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul',
			'Ago', 'Set', 'Out', 'Nov', 'Dez'],
		dayNames: ['Domingo', 'Segunda', 'Ter�a', 'Quarta', 'Quinta',
			'Sexta', 'Sábado'],
		dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'S�b'],
		dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
		weekHeader: 'Semana',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: '',
		timeOnlyTitle: 'São Horas',
		timeText: 'Tempo',
		hourText: 'Hora',
		minuteText: 'Minuto',
		secondText: 'Segundo',
		ampm: false,
		month: 'Mês',
		week: 'Semana',
		day: 'Dia',
		allDayText: 'Todo o Dia'
	};

}

function initTamplete() {
$(document).ready(function() {
	  $('#menupop').hide();
	  $('#barramenu').hide();
	  $('#barramenu').css("left", "-200px");
	  $('#iniciarmenu').click(function() {
	  	if ($('#barramenu').is(':visible')) {
	  	  ocultarMenu();
	  	} else {
	  	  $('#barramenu').show();
	  	  $('#barramenu').animate({"left":"0px"}, "slow");	
	  	}
	  });
	});
}

function ocultarMenu() {
	$('#barramenu').animate({ "left": "-200px" }, "slow", function() {
		$('#barramenu').hide();
	});
}

function addFocoAocampo(campo){
	
	var id = getValorElementPorId(campo);
	if (id != undefined) {
		document.getElementById(id).focus();
	}
}

function gerenciaTeclaEnter(){
	
	
	$(document).ready(function() {
		$('input').keypress(function(e) {
			var code = null;
			code = (e.keyCode ? e.keyCode : e.which);
			return (code === 13) ? false : true;

		});

		$('input[type=text]').keydown(function(e) {
			// Obter o pr�ximo �ndice do elemento de entrada de texto
			var next_idx = $('input[type=text]').index(this) + 1;

			// Obter o n�mero de elemento de entrada de texto em um documento html
			var tot_idx = $('body').find('input[type=text]').length;

			// Entra na tecla no c�digo ASCII
			if (e.keyCode === 13) {
				if (tot_idx === next_idx)
					// V� para o primeiro elemento de texto
					$('input[type=text]:eq(0)').focus();
				else
					// V� para o elemento de entrada de texto seguinte
					$('input[type=text]:eq(' + next_idx + ')').focus();
			}
		});
	});

	
}

function permitNumber(e) {
	var unicode = e.charCode ? e.charCode : e.keyCode;
	if (unicode != 8 && unicode != 9) {
		if (unicode < 48 || unicode > 57) {
			return false;
		}
	}
}

function getValorElementPorIdJQuery(id) {
	var id = getValorElementPorId(id);
	if (id.trim() != idundefined) {
		 return PrimeFaces.escapeClientId(id);
	}
	
	 return idundefined;
}

function addFocoAoCampo(campo) {
	var id = getValorElementPorId(campo);
	if (id != idundefined) {
		document.getElementById(getValorElementPorId(id)).focus();
	}
}

function validarCampoPesquisa(valor) {
	if ( valor != undefined  &&  valor.value != undefined ) {
		if (valor.value.trim() === '') {
			valor.value = '';
		}else {
			valor.value = valor.value.trim();
		}
	}
}

function addMascaraPesquisa(elemento) {
	var id = getValorElementPorIdJQuery('valorPesquisa');
	var vals = elemento.split("*");
	var campoBanco = vals[0];
	var typeCampo = vals[1];
	
	$(id).unmask();
	$(id).unbind("keypress"); 
	$(id).unbind("keyup");
	$(id).unbind("focus");
	$(id).val('');
	if (id != idundefined) {
		jQuery(function($) {
			if (typeCampo === classTypeLong) {
				$(id).keypress(permitNumber);
			}
			else if (typeCampo === classTypeBigDecimal) {	
				$(id).maskMoney({precision:4, decimal:",", thousands:"."}); 
			}
			else if (typeCampo === classTypeDate) {
				$(id).mask('99/99/9999');
			}
			else {
				$(id).unmask();
				$(id).unbind("keypress");
				$(id).unbind("keyup");
				$(id).unbind("focus");
				$(id).val('');
			}
			addFocoAoCampo('valorPesquisa');
		});
	}
}

function pesquisaUserDestinoPerderFocoDialog(codUser){
	
	if(codUser.trim()!= ''){
		$("#user_destinoMsgDialog").val('');
		$("#LoginDestinoMsgDialog").val('');
		$.get("buscarUsuarioDestinoMsg?codEntidade=" + codUser,function(resposta){
			
			if(resposta.trim()!= ''){
				
				var entidade  =JSON.parse(resposta);
				$("#user_destinoMsgDialog").val(entidade.ent_codigo);
				$("#LoginDestinoMsgDialog").val(entidade.ent_login);
			}
		});
		
	}
}
