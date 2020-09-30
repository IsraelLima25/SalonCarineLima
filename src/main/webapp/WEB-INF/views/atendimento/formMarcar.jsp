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

						<label for="exampleFormControlSelect1">Cliente
							<span class="obrigatorio">*</span>
						</label> <select class="form-control" id="exampleFormControlSelect1"
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
						<label for="slcFormaPagamento">Forma de Pagamento</label> <select
							class="form-control" id="slcFormaPagamento" name="formaPagamento">
							<option value="">Selecionar Pagamento</option>							
							<option value="carteira">Carteira</option>
							<option value="credito">Crédito</option>
							<option value="debito" class="pagamento">Débito</option>
						</select>
					</div>

					<div hidden id="groupBandeiras" class="form-group">
						<label for="slcBandeira">Bandeira Cartão</label> <select
							class="form-control" id="slcBandeira" name="bandeiraCartao">
							<option value="">Selecionar Bandeira</option>
							<option value="elo">Elo</option>
							<option value="master-card">Mastercard</option>
							<option value="visa">Visa</option>
						</select>
					</div>

					<div hidden id="groupQuantidadeParcelas" class="form-group">
						<label class="color-font-label" for="quantidadeParcelas">Quantidade
							Parcelas</label> <input type="number" class="form-control"
							id="quantidadeParcelas" name="quantidadeParcelas">
					</div>

					<div class="form-group">
				
						<label class="color-font-label" for="slcTipoEndereco">Endereço</label>
						<select class="form-control" id="slcTipoEndereco" name="tipoEndereco">
							<option value="casa">Casa</option>
							<option value="endereco-cliente" selected>Cliente</option>
							<option value="outro-endereco" id="outro">Outro</option>
						</select>
					
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
						<label class="color-font-label" for="data">Data</label> 
						<input type="date" class="form-control" id="data" name="data">
					</div>

					<div class="form-group">
						<label class="color-font-label" for="hora">Hora</label> <input
							type="time" class="form-control" name="hora" id="hora">
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
