$(document).ready(
		function() {

			function limpa_formulario_cep() {
				// Limpa valores do formulario de cep.
				
				$("#logradouro").val("");
				$("#bairro").val("");
			}

			$("#cep").blur(
					function() {
						// Nova variável "cep" somente com dígitos.
						var cep = $(this).val().replace(/\D/g, '');
						// Verifica se campo cep possui valor informado.
						if (cep != "") {

							// Expressão regular para validar o CEP.
							var validacep = /^[0-9]{8}$/;

							// Valida o formato do CEP.
							if (validacep.test(cep)) {

								// Preenche os campos com "..." enquanto
								// consulta webservice.
								$("#logradouro").val("...");
								$("#bairro").val("...");

								// Consulta o webservice viacep.com.br/
								$.getJSON("https://viacep.com.br/ws/" + cep
										+ "/json/?callback=?", function(dados) {

									if (!("erro" in dados)) {
										// Atualiza os campos com os valores da
										// consulta.
										$("#logradouro").val(dados.logradouro);
										$("#bairro").val(dados.bairro);
										$('#cepMsgValid').prop("hidden", true);
										$('#cepMsgValid').text("")
									} // end if.
									else {
										// CEP pesquisado não foi encontrado.
										limpa_formulario_cep();
										$('#cepMsgValid').prop("hidden", false);
										$('#cepMsgValid').text("Cep inexistente")
									}
								});
							} // end if.
							else {
								// cep é inválido.
								limpa_formulario_cep();
								$('#cepMsgValid').prop("hidden", false);
							}
						} // end if.
						else {
							// cep sem valor, limpa formulario.
							limpa_formulario_cep();
							$('#cepMsgValid').prop("hidden", false);
							$('#cepMsgValid').text("O cep está vazio ou possui caracteres inválidos." +
									" Não se precocupe, este campo não é obrigatório");
						
						}

					});
		});
