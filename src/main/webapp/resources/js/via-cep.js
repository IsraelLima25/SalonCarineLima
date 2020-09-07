$(document).ready(
		function() {

			function limpa_formulario_cep() {
				// Limpa valores do formulario de cep.
				
				$("#logradouro").val("");
				$("#bairro").val("");
			}

			$("#cep").blur(
					function() {
						// capturando cep
						var cep = $(this).val();
						
						// Verifica se campo cep possui valor informado.
						if (cep != "") {
							
							// Extrair somente digitos da variavel 'cep'
							var cep = $(this).val().replace(/\D/g, '');
							
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
										$('#cepMsgValid').text("Este cep não existe")
									}
								});
							} // end if.
							else {
								// cep é inválido.
								$('#cepMsgValid').prop("hidden", false);
								$('#cepMsgValid').text("cep inválido");
							}
						}  // end if.
						else{
							// Limpa o formulário
							limpa_formulario_cep();
							$('#cepMsgValid').text("");
						}
					});
		});
