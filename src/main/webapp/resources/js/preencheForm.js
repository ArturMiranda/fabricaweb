/*Funcao para preenchimento automatico de forma aleatoria do formulario 
* Parametros: 
*		- vazio => Os campos "text" sao preechidos com os proprios nomes e os campos "selects, radio e checkbox" sao preechidos aleatoriamente
*		- true => Os campos "text" sao preechidos com os proprios nomes + TEST_ID_(random) e os campos "selects, radio e checkbox" sao preechidos aleatoriamente
*		- string => Os campos "text" sao preechidos com os proprios nomes + TEST_ID_(string) e os campos "selects, radio e checkbox" sao preechidos aleatoriamente
*/
function fnPreencheTeste(usar_test_id = false){
	
	var test_id = "";
	/*Gerando uma ID para o teste*/
	if(usar_test_id === true){
		test_id = " TEST_ID_" + Math.floor(Math.random() * 1000);
	}else if(usar_test_id != false){
		test_id = " TEST_ID_" + usar_test_id;
	}
	
			
	$("form input").not("input[type=button]").each(function(){
	
		if($(this).attr("type") == "text" || $(this).attr("type") == "hidden"){/*Tratando os campos do tipo TEXT ou HIDDEN*/
			//console.log($(this).attr("name"));
			$(this).val( $(this).attr("name") + test_id  );
		} else if($(this).attr("type") == "radio"){	/*Tratando os campos do tipo RADIO*/
			var vetOpcoes = [];
			$( "input[name="+$(this).attr("name")+"]:radio").each(function(){
				vetOpcoes.push($(this).val());
			});
			/*Sorteando um item para preenchimento aleatorio*/
			var item = vetOpcoes[Math.floor(Math.random() * vetOpcoes.length)];
			$("input[name="+$(this).attr("name")+"][value=" + item + "]").attr('checked', 'checked');
		} else if($(this).attr("type") == "checkbox"){/*Tratando os campos do tipo CHECKBOX*/
			console.log($(this).attr("name"));
			/*Sorteando a selecao o nao do checkbox (aleatorio)*/
			var random = Math.floor((Math.random() * 10) + 1); /*entre 1 e 10*/
			var checked = false;
			if(random >= 5) checked = true;/*50% de chance de ser selecionado*/
			
			$("input[name="+$(this).attr("name")+"]").prop('checked', checked);
		}	
	});
	
	/*Tratando os campos do tipo SELECT*/
	$("form select").each(function(){
		var item = Math.floor(Math.random() * $("select[name="+$(this).attr("name")+"] option").length) + 1;/* +1 para nao pegar a primeira opcao */
		$("select[name="+$(this).attr("name")+"] option:eq("+item+")").attr("selected","selected");
	});
	
	console.info("Formulario preechido!");
}
