<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Marcar Atendimento">

	<main>
		<section>
			<div class="container">
				<h4 class="mt-4 color-font">Marcar Atendimento</h4>
				<hr />
				
				<div class="form-group">
				
					<label for="exampleFormControlSelect1">
						Selecionar Cliente
						<span class="obrigatorio">*</span>
					</label>
					 
					<select class="form-control" id="exampleFormControlSelect1">
						<option value=""></option>
						<c:forEach items="${clientes}" var="cliente">
							<option value="${cliente.id}">
								${cliente.nome}
							</option>						
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group">
				
					<label for="exampleFormControlSelect1">
						Selecionar Serviços
						<span class="obrigatorio">
							*
						</span>
					</label> 
					<select class="form-control" id="selectServicos">
						<option value=""></option>	
						<c:forEach items="${servicos}" var="servico">
							<option value="${servico.id}">
							 	${servico.id} - ${servico.descricao}
							</option>
						</c:forEach>					
					</select>
					
					<button id="btnCadastrar" type="button" onclick="adicionarItemSelecionadoAoCarrinho()" 
						class="btn btn-color-salon fas fa-cart-plus btn-sm mb-3">
						Adicionar ao Carrinho 
					</button>
				</div>

				<table id="carrinho" class="table table-striped">
					<thead>
						<tr>
							<th scope="col">Serviço</th>
							<th class="text-center" scope="col">Quantidade</th>
							<th class="text-center" scope="col">Remover</th>
						</tr>
					</thead>
					<tbody id="corpo">
						<tr id="msg-cesta-vazia" hidden>
							<td>
								<p>
									<i>Carrinho vazio</i>
								</p>
							</td>
						</tr>
					</tbody>
				</table>

				<div class="form-group">
					<label class="color-font-label" for="desconto">Desconto</label> <input
						type="number" class="form-control" id="desconto">
				</div>	
				
				<div class="form-group">
					<label class="color-font-label" for="taxa">Taxa</label> <input
						type="number" class="form-control" id="taxa">
				</div>	
				
				<div class="form-group">
					<label class="color-font-label" for="valorTotal">
						Valor Total
					</label> 
					<input type="number" class="form-control" disabled id="valorTotal">
				</div>				

				<div class="form-group">
					<label for="slcFormaPagamento">Forma de Pagamento</label> <select
						class="form-control" id="slcFormaPagamento">
						<option selected value="cart">Carteira</option>
						<option value="cred">Crédito</option>
						<option value="deb" class="pagamento">Débito</option>
					</select>
				</div>

				<div hidden id="groupBandeiras" class="form-group">
					<label for="slcBandeira">Bandeira Cartão</label> <select
						class="form-control" id="slcBandeira">
						<option>Elo</option>
						<option>Mastercard</option>
						<option>Visa</option>
					</select>
				</div>

				<div hidden id="groupQuantidadeParcelas" class="form-group">
					<label class="color-font-label" for="quantidadeParcelas">Quantidade
						Parcelas</label> <input type="number" class="form-control"
						id="quantidadeParcelas">
				</div>

				<label class="color-font-label" for="data">Endereço</label>

				<div class="form-check">
					<input class="form-check-input" type="radio" name="exampleRadios"
						id="rbEnderecoCasa" value="option1"> <label
						class="form-check-label" for="rbEnderecoCasa"> Casa </label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="exampleRadios"
						id="rbEnderecoCliente" value="option1" checked> <label
						class="form-check-label" for="rbEnderecoCliente"> Endereço
						Cliente </label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="exampleRadios"
						id="rbOutroEndereco" value="option2"> <label
						class="form-check-label" for="rbOutroEndereco"> Outro
						Endereço </label>
				</div>

				<div hidden id="outroEndereco">

					<div class="form-group mt-3">
						<label class="color-font-label" for="cep">Cep</label> <input
							type="number" class="form-control" id="cep">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="logradouro">Logradouro</label>
						<input type="text" class="form-control" id="logradouro">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="numero">Numero</label> <input
							type="number" class="form-control" id="numero">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="ponto-referencia">Ponto
							Referência</label> <input type="text" class="form-control"
							id="ponto-referencia">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="complemento">Complemento</label>
						<input type="text" class="form-control" id="complemento">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="bairro">Bairro</label> <input
							type="text" class="form-control" id="bairro">
					</div>
				</div>
				
				<div class="form-group">
					<label class="color-font-label" for="data">Data</label> <input
						type="date" class="form-control" id="data">
				</div>

				<div class="form-group">
					<label class="color-font-label" for="hora">Hora</label> <input
						type="time" class="form-control" id="hora">
				</div>

				<div class="text-center alinhamento">
					<button id="btnCadastrar" style="margin-bottom: 16px;"
						data-toggle="modal" data-target="#modalConfirmarmarcacao"
						type="button" class="btn btn-color-salon btn-block">Marcar</button>
				</div>
				
			</div>

			<div class="back-to-top" href="#">
				<i class="fas fa-chevron-up"></i>
			</div>

		</section>
		
		    <!-- Modal -->
        <div class="modal fade" id="modalAlertCarrinho" 
        tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
             <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Este serviço já foi adicionado ao carrinho.
                        </div>
                        <div class="modal-footer">
                    </div>
                    </div>
                </div>
            </div>
	</main>

	<script type="text/javascript" charset="UTF-8" src="../resources/js/atendimento/marcar.js"></script>
	<script type="text/javascript" charset="UTF-8" src="../resources/js/via-cep.js"></script>

</tags:pageTemplate>
