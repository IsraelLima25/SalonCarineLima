<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Marcar Atendimento">

	<main>
		<section>
			<div class="container">
				<h4 class="mt-4 color-font">Marcar Atendimento</h4>
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
						<label class="color-font-label" for="valorTotal"> Valor
							Total </label> <input type="number" class="form-control" disabled
							id="valorTotal" value="${valorTotalCarrinho}">

					</div>

					<div class="form-group">
						<label class="color-font-label" for="desconto">Desconto</label> <input
							type="number" class="form-control" id="desconto" name="desconto">
					</div>

					<div class="form-group">
						<label class="color-font-label" for="taxa">Taxa</label> <input
							type="number" class="form-control" id="taxa" name="taxa">
					</div>

					<div class="form-group">
						<label for="slcFormasPagamento">Forma de Pagamento</label> <select
							class="form-control" id="slcFormasPagamento" name="formaPagamento">
							<option value="">Selecionar Pagamento</option>							
							<option value="carteira">Carteira</option>
							<option value="credito">Crédito</option>
							<option value="debito" class="pagamento">Débito</option>
						</select>
						<small hidden class="form-text obrigatorio formaPagamento"></small>
					</div>

					<div hidden id="groupBandeiras" class="form-group">
						<label for="slcBandeira">Bandeira Cartão</label> <select
							class="form-control" id="slcBandeira" name="bandeiraCartao">
							<option value="elo">Elo</option>
							<option value="master-card">Mastercard</option>
							<option value="visa">Visa</option>
						</select>
						<small hidden class="form-text obrigatorio bandeiraCartao"></small>
					</div>

					<div hidden id="groupQuantidadeParcelas" class="form-group">
						<label class="color-font-label" for="quantidadeParcelas">Quantidade
							Parcelas</label> <input type="number" class="form-control"
							id="quantidadeParcelas" name="quantidadeParcelas">
					</div>

					<div class="form-group">
				
						<label class="color-font-label" for="slcTiposEndereco">Endereço</label>
						<select class="form-control" id="slcTiposEndereco" name="tipoEndereco">
							<option value="" selected>Selecionar Endereço</option>
							<option value="casa">Casa</option>
							<option value="endereco-cliente">Cliente</option>
							<option value="outro-endereco" id="outro">Outro</option>
						</select>
						<small hidden class="form-text obrigatorio tipoEndereco"></small>
					
					</div>

					<div hidden id="outroEndereco">

						<div class="form-group mt-3">
							<label class="color-font-label" for="cep">Cep</label> <input
								type="number" class="form-control" id="cep" name="endereco.cep">
						</div>
						<div class="form-group">
							<label class="color-font-label" for="logradouro">Logradouro</label>
							<input type="text" class="form-control" id="logradouro" name="endereco.logradouro">
						</div>
						<div class="form-group">
							<label class="color-font-label" for="numero">Numero</label> <input
								type="number" class="form-control" id="numero" name="endereco.numero">
						</div>
						<div class="form-group">
							<label class="color-font-label" for="ponto-referencia">Ponto
								Referência</label> 
								<input type="text" name="endereco.pontoReferencia" 
								class="form-control" id="ponto-referencia">
						</div>
						<div class="form-group">
							<label class="color-font-label" for="complemento">Complemento</label>
							<input type="text" name="endereco.complemento"
							class="form-control" id="complemento">
						</div>
						<div class="form-group">
							<label class="color-font-label" for="bairro">Bairro</label> 
							<input type="text" name="endereco.bairro"
							class="form-control" id="bairro">
						</div>
					</div>

					<div class="form-group">
						<label class="color-font-label" for="dataAtendimento">Data</label> 
						<input type="date" class="form-control" id="dataAtendimento" name="data">
						<small hidden class="form-text obrigatorio data"></small>
					</div>

					<div class="form-group">
						<label class="color-font-label" for="horaAtendimento">Hora</label> 
						<input type="time" class="form-control" name="hora" id="horaAtendimento">
						<small hidden class="form-text obrigatorio hora"></small>
					</div>

					<div class="text-center alinhamento">
						<button id="btnMarcar" style="margin-bottom: 16px;" type="submit"
							class="btn btn-color-salon btn-block">Finalizar</button>
					</div>

				</form>

			</div>

			<div class="back-to-top" href="#">
				<i class="fas fa-chevron-up"></i>
			</div>

		</section>

	</main>

	<script type="text/javascript" charset="UTF-8" src="../resources/js/atendimento/marcar.js"></script>
	<script type="text/javascript" charset="UTF-8" src="../resources/js/via-cep.js"></script>

</tags:pageTemplate>
