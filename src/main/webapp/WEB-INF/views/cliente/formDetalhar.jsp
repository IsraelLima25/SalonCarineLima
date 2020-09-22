<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Detalhar Cliente">

	<div class="container">

		<div class="row mt-4">
			<div class="text-left col-8 col-md-8">
				<h4 class="text-left color-font">Dados Cliente</h4>
			</div>
			<div class="text-right col-4 col-md-4">
				<a type="button" href="../cliente/listar" class="btn btn-info fas fa-fast-backward"></a>
			</div>
		</div>

		<hr />
		
		<div class="mt-2 row">
			<div class="text-left col-6 col-md-6">
				<span>
					<button onclick="clienteAnterior(${cliente.id});"  class="btn btn-success fas fa-arrow-left"></button>
				</span>
			</div>
			<div class="text-right col-6 col-md-6">
				<button onclick="clienteProximo(${cliente.id});" 
				class="btn btn-success fas fa-arrow-right"></button>
			</div>
		</div>
		
		<div class="row mt-4">
			<div class="col-12 col-md-12 text-right">
				<a type="button" data-toggle="modal" data-target="#modalExcluir"
					 class="btn btn-danger fas fa-trash-alt"></a>
			</div>
		</div>

		<form name="formDetalhar">

			<div class="form-group">
				<label class="color-font-label" hidden for="id">Id</label> 
				<input type="text" hidden value="${cliente.id}" name="id" class="form-control" id="id">
		
			</div>

			<div class="form-group">
				<label class="color-font-label" for="nome">Nome <span class="obrigatorio">*</span></label>
				<input type="text" value="${cliente.nome}" name="nome" class="form-control" id="nome">
				<small class="form-text nome obrigatorio"></small> 
			</div>

			<div class="form-group">
				<label class="color-font-label" for="email">Email</label> 
				<input type="text" value="${cliente.email}" class="form-control" id="email" name="email">
				<small hidden class="form-text obrigatorio email"></small>
			</div>

			<div class="form-group">
				<label class="color-font-label" for="telefone">Telefone</label> 
				<input type="tel" value="${cliente.telefone}" class="form-control" 
					id="telefone" name="telefone">
				<small hidden class="form-text obrigatorio telefone"></small>
			</div>

			<div class="form-group">
				<label class="color-font-label" hidden for="idEndereco">Id Endereco</label> 
				<input type="number" hidden value="${cliente.endereco.idEndereco}" 
					class="form-control" Id="idEndereco" name="endereco.idEndereco">
			</div>

			<div class="form-group">
				<label class="color-font-label" for="cep">Cep</label> 
				<input type="number" value="${cliente.endereco.cep}" 
					class="form-control" id="cep" name="endereco.cep">
				<small hidden id="cepMsgValid" class="form-text obrigatorio"></small>
					
			</div>

			<div class="form-group">
				<label class="color-font-label" for="logradouro">Logradouro</label>
				<input type="text" value="${cliente.endereco.logradouro}" 
					class="form-control" id="logradouro" name="endereco.logradouro">
				<small hidden class="form-text obrigatorio logradouro"></small>
			</div>

			<div class="form-group">
				<label class="color-font-label" for="bairro">Bairro <span class="obrigatorio">*</span></label>
				<input type="text" value="${cliente.endereco.bairro}" 
					class="form-control" id="bairro" name="endereco.bairro">
				<small hidden class="form-text obrigatorio bairro"></small>
			</div>

			<div class="form-group">
				<label class="color-font-label" for="numero">Numero</label> 
				<input type="number" value="${cliente.endereco.numero}"
					class="form-control" id="numero" name="endereco.numero">
				<small hidden class="form-text obrigatorio numero"></small>
			</div>
			
			<div class="form-group">
				<label class="color-font-label" for="ponto-referencia">Ponto Referência</label> 
				<input type="text" value="${cliente.endereco.pontoReferencia}" 
					class="form-control" id="ponto-referencia" name="endereco.pontoReferencia">
				<small hidden class="form-text obrigatorio pontoReferencia"></small>
			</div>

			<div class="form-group">
				<label class="color-font-label" for="complemento">Complemento</label>
				<input type="text" value="${cliente.endereco.complemento}"
					class="form-control" id="complemento" name="endereco.complemento">
				<small hidden class="form-text obrigatorio complemento"></small>
			</div>

			<div class="text-center alinhamento">
				<button id="btn-confirmar-alteracao" type="submit" class="btn btn-color-salon btn-block">
					Confirmar Alteração
				</button>
				<hr />
			</div>
		</form>
	</div>

	<!-- Modal Excluir -->
	<div class="modal fade" id="modalExcluir" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Deseja realmente excluir este cliente?</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" style="width: 100px;"
						class="btn btn-color-salon"
						onclick="removerCliente(${cliente.id});">Sim</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" charset="UTF-8" src="../resources/js/cliente/detalhe.js"></script>
	<script type="text/javascript" charset="UTF-8" src="../resources/js/via-cep.js"></script>
	
</tags:pageTemplate>


