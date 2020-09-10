<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Detalhar Cliente">
	<main>
		<section>

			<div class="container">

				<h4 class="mt-4 color-font">Detalhar Cliente</h4>
				<hr />

				<div class="text-right">
				
					<span> 
						<a onclick="habilitarCampos();">
							<i id="unlock" class="fas fa-unlock-alt tamanho-icone-padlock color-font-label">
							</i>
						</a>
					</span>
					
					<span>
						<a href="../cliente/listar">
							<i id="unlock" class="fas fa-backward tamanho-icone-padlock color-font-label">
							</i>
						</a>
					</span>
					
					<span>
						<a type="button" data-toggle="modal" data-target="#modalExcluir">
							<i id="unlock" class="fas fa-forward tamanho-icone-padlock color-font-label">
							</i>
						</a>
					</span>
					
					<span>
						<a type="button" data-toggle="modal" data-target="#modalExcluir">
							<i id="unlock" class="fas fa-trash-alt tamanho-icone-padlock color-font-label">
							</i>
						</a>
					</span>
					
					<span>
						<a type="button" data-toggle="modal" data-target="#modalExcluir">
							<i id="unlock" class="fas fa-fast-backward tamanho-icone-padlock color-font-label">
							</i>
						</a>
					</span>
					
				</div>

				<form name="formDetalhar">

					<div class="form-group">
						<label class="color-font-label" hidden for="id">Id</label> <input
							type="text" hidden value="${cliente.id}" name="id"
							class="form-control" id="id">
						<!-- <small id="emailHelp" class="form-text text-muted">We'll 
						never share your email with anyone else.</small> -->
					</div>

					<div class="form-group">
						<label class="color-font-label" for="nome">Nome *</label> <input
							type="text" disabled value="${cliente.nome}" name="nome"
							class="form-control" id="nome">
						<!-- <small id="emailHelp" class="form-text text-muted">We'll 
						never share your email with anyone else.</small> -->
					</div>

					<div class="form-group">
						<label class="color-font-label" for="email">Email</label> <input
							type="email" disabled value="${cliente.email}"
							class="form-control" id="email" name="email">
					</div>

					<div class="form-group">
						<label class="color-font-label" for="telefone">Telefone</label> <input
							type="tel" disabled value="${cliente.telefone}"
							class="form-control" id="telefone" name="telefone">
					</div>

					<div class="form-group">
						<label class="color-font-label" hidden for="idEndereco">Id
							Endereco</label> <input type="number" hidden
							value="${cliente.endereco.idEndereco}" class="form-control"
							id="idEndereco" name="endereco.idEndereco">
					</div>

					<div class="form-group">
						<label class="color-font-label" for="cep">Cep</label> <input
							type="number" disabled value="${cliente.endereco.cep}"
							class="form-control" id="cep" name="endereco.cep">
					</div>

					<div class="form-group">
						<label class="color-font-label" for="logradouro">Logradouro</label>
						<input type="text" disabled value="${cliente.endereco.logradouro}"
							class="form-control" id="logradouro" name="endereco.logradouro">
					</div>

					<div class="form-group">
						<label class="color-font-label" for="bairro">Bairro *</label> <input
							type="text" disabled value="${cliente.endereco.bairro}"
							class="form-control" id="bairro" name="endereco.bairro">
					</div>

					<div class="form-group">
						<label class="color-font-label" for="numero">Numero</label> <input
							type="number" disabled value="${cliente.endereco.numero}"
							class="form-control" id="numero" name="endereco.numero">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="ponto-referencia">Ponto
							Referência</label> <input type="text" disabled
							value="${cliente.endereco.pontoReferencia}" class="form-control"
							id="ponto-referencia" name="endereco.pontoReferencia">
					</div>

					<div class="form-group">
						<label class="color-font-label" for="complemento">Complemento</label>
						<input type="text" disabled
							value="${cliente.endereco.complemento}" class="form-control"
							id="complemento" name="endereco.complemento">
					</div>

					<div class="text-center alinhamento">
						<button id="btn-confirmar-alteracao" type="submit" disabled
							class="btn btn-color-salon btn-block">Confirmar
							Alteração</button>
						<hr />
					</div>

				</form>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="modalExcluir" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<img src="../resources/img/logomarca-removebg-preview.png"
								width="50" height="50" alt="logomarca">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">Deseja realmente excluir este
							cliente?</div>
						<div class="modal-footer">
							<button type="button" data-dismiss="modal" style="width: 100px;"
								class="btn btn-color-salon" onclick="removerCliente(${cliente.id});">Sim</button>
						</div>
					</div>
				</div>
			</div>

		</section>
	</main>

	<script type="text/javascript" charset="UTF-8" src="../resources/js/cliente/detalhe.js"></script>
	<script type="text/javascript" charset="UTF-8" src="../resources/js/via-cep.js"></script>


</tags:pageTemplate>


