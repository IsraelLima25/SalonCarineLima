<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Marcar Atendimento">

	<main>
		<section>
			<div class="container container-space-default">
				<h4 class="color-font">Marcar Atendimento</h4>
				<hr />
				<form name="formMarcar">
					<div class="form-group">

						<label for="slcClientes">Cliente
							<span class="obrigatorio">*</span>
						</label> 
						<select class="form-control" id="slcClientes"
							name="cliente">
							<option value=0>Selecionar Cliente</option>
							<c:forEach items="${clientes}" var="cliente">
								<option value="${cliente.id}">${cliente.nome}</option>
							</c:forEach>
						</select>
						<small hidden class="form-text obrigatorio cliente"></small>
					</div>

					<div class="form-group">
						<label class="color-font-label" for="valorTotal"> Valor Total</label>
						<fmt:formatNumber var="fmtTotalCarrinho" type="currency" pattern="#,##0.00" 
                    			value="${valorTotalCarrinho}" />
						<input type="text" class="form-control" disabled
							id="valorTotal" value="${fmtTotalCarrinho}">

					</div>

					<div class="form-group">
						<label class="color-font-label" for="desconto">
							Desconto
						</label> 
						<input type="text" class="form-control moeda" 
							id="desconto" name="desconto">
					</div>

					<div class="form-group">
						<label class="color-font-label" for="taxa">Taxa</label> 
						<input type="text" class="form-control moeda" id="taxa" name="taxa">
					</div>

					<div class="form-group">
						<label for="slcFormasPagamento">Forma de Pagamento
							<span class="obrigatorio">*</span> </label> <select
							class="form-control" id="slcFormasPagamento" name="tipoPagamento">
							<option value="" selected>Selecionar Pagamento</option>							
							<option value="${pagamentos[0]}">Crédito</option>
							<option value="${pagamentos[1]}">Débito</option>
							<option value="${pagamentos[2]}" class="pagamento">Espécie</option>
						</select>
						<small hidden class="form-text obrigatorio tipoPagamento"></small>
					</div>

					<div hidden id="groupBandeiras" class="form-group">
						<label for="slcBandeira">Bandeira Cartão
						   <span class="obrigatorio">*</span></label> <select
							class="form-control" id="slcBandeira" name="bandeiraCartao">
							<option value="" selected>Selecionar Bandeira</option>
							<option value="${bandeiras[0]}">Elo</option>
							<option value="${bandeiras[1]}">Mastercard</option>
							<option value="${bandeiras[2]}">Visa</option>
						</select>
						<small hidden class="form-text obrigatorio bandeiraCartao"></small>
					</div>

					<div hidden id="groupQuantidadeParcelas" class="form-group">
						<label class="color-font-label" for="quantidadeParcela">Quantidade
							Parcelas <span class="obrigatorio">*</span></label> 
							<input type="number" class="form-control"
							id="quantidadeParcela" name="quantidadeParcelas">
						<small hidden class="form-text obrigatorio quantidadeParcelas"></small>
							
					</div>

					<div class="form-group">
				
						<label class="color-font-label" for="slcTiposEndereco">Endereço
						<span class="obrigatorio">*</span></label>
						<select class="form-control" id="slcTiposEndereco" name="tipoEndereco">
							<option value="" selected>Selecionar Endereço</option>
							<option value="${enderecos[0]}">Casa</option>
							<option value="${enderecos[1]}">Cliente</option>
							<option value="${enderecos[2]}" id="outro">Outro</option>
						</select>
						<small hidden class="form-text obrigatorio tipoEndereco"></small>
					
					</div>

					<div hidden id="outroEndereco">

						<div class="form-group mt-3">
							<label class="color-font-label" for="cep">Cep</label> 
							<input type="number" class="form-control" 
							id="cep" name="enderecoDTOAtendimento.cep">
								<small hidden id="cepMsgValid" class="form-text obrigatorio"></small>
						</div>
						<div class="form-group">
							<label class="color-font-label" for="logradouro">Logradouro</label>
							<input type="text" class="form-control" id="logradouro" 
							name="enderecoDTOAtendimento.logradouro">
						</div>
						<div class="form-group">
							<label class="color-font-label" for="bairro">Bairro 
							<span class="obrigatorio">*</span></label> 
							<input type="text" name="enderecoDTOAtendimento.bairro"
							class="form-control" id="bairro">
							<small hidden class="form-text obrigatorio bairro"></small>
						</div>
						<div class="form-group">
							<label class="color-font-label" for="numero">Numero</label> <input
								type="number" class="form-control" id="numero" 
								name="enderecoDTOAtendimento.numero">
						</div>
						<div class="form-group">
							<label class="color-font-label" for="ponto-referencia">Ponto
								Referência</label> 
								<input type="text" name="enderecoDTOAtendimento.pontoReferencia" 
								class="form-control" id="ponto-referencia">
						</div>
						<div class="form-group">
							<label class="color-font-label" for="complemento">Complemento</label>
							<input type="text" name="enderecoDTOAtendimento.complemento"
							class="form-control" id="complemento">
						</div>
					</div>

					<div class="form-group">
						<label class="color-font-label" for="dataAtendimento">Data
						<span class="obrigatorio">*</span></label> 
						<input type="date" class="form-control" id="dataAtendimento" name="data">
						<small hidden class="form-text obrigatorio data"></small>
					</div>

					<div class="form-group">
						<label class="color-font-label" for="horaAtendimento">Hora
						<span class="obrigatorio">*</span></label> 
						<input type="time" class="form-control" name="hora" id="horaAtendimento">
						<small hidden class="form-text obrigatorio hora"></small>
					</div>

					<div class="text-center alinhamento">
						<button id="btnMarcar" style="margin-bottom: 16px;" type="submit"
							class="btn btn-color-salon btn-block">Marcar</button>
					</div>

				</form>

			</div>

			<div class="back-to-top" href="#">
				<i class="fas fa-chevron-up"></i>
			</div>

		</section>
		
		<!-- Global -->
		<div class="modal fade" id="modalPageMarcar" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
							<h5 class="modal-title" id="title">Atendimento</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                                <span aria-hidden="true">&times;</span>
	                            </button>
					</div>
					<div id="body" class="modal-body">
						Atendimento marcado com sucesso
					</div>
					<div class="modal-footer"></div>
				</div>
			</div>
		</div>
	</main>

	<script type="text/javascript" charset="UTF-8" src="../resources/js/atendimento/marcar.js"></script>
	<script type="text/javascript" charset="UTF-8" src="../resources/js/via-cep.js"></script>

</tags:pageTemplate>
