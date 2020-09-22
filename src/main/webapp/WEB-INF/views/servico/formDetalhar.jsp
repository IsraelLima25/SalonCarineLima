<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Detalhar Serviço">

	<div class="container">
		<div class="row mt-4">
			<div class="text-left col-8 col-md-8">
				<h4 class="text-left color-font">Dados Serviço</h4>
			</div>
			<div class="text-right col-4 col-md-4">
				<a type="button" href="../servico/listar"
					class="btn btn-info fas fa-fast-backward"></a>
			</div>
		</div>

		<hr />

		<div class="mt-2 row">
			<div class="text-left col-6 col-md-6">
				<span>
					<button onclick="servicoAnterior(${servico.id});"
						class="btn btn-success fas fa-arrow-left"></button>
				</span>
			</div>
			<div class="text-right col-6 col-md-6">
				<button onclick="servicoProximo(${servico.id});"
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
				<input type="text" hidden value="${servico.id}" 
				name="id" class="form-control" id="id">
			</div>

			<div class="form-group">
				<label class="color-font-label" for="descricao">Descrição <span class="obrigatorio">*</span></label> 
				<input type="text" value="${servico.descricao}" name="descricao" class="form-control" id="descricao">
			</div>
			
			<div class="form-group">
				<label class="color-font-label" for="preco">Preço <span class="obrigatorio">*</span></label> 
				<input type="text" value="${servico.preco}" name="preco" class="form-control" id="preco">
			</div>

			<div class="text-center alinhamento">
				<button id="btn-confirmar-alteracao" type="submit"
					class="btn btn-color-salon btn-block">Confirmar Alteração
				</button>
				<hr />
			</div>
		</form>

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
					<div class="modal-body">Deseja realmente excluir este
						serviço?</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" style="width: 100px;"
							class="btn btn-color-salon"
							onclick="removerServico(${servico.id});">Sim</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" charset="UTF-8" src="../resources/js/servico/detalhe.js"></script>

</tags:pageTemplate>