<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Detalhar Serviço">

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<div class="container container-space-default">
	<span id="contextPage" hidden> ${contextPath}</span>
		<div class="row">
			<div class="text-left col-8 col-md-8">
				<h4 class="text-left color-font">Dados Serviço</h4>
			</div>
			<div class="text-right col-4 col-md-4">
				<a type="button" href="../servico/listar"
					class="btn btn-info fas fa-fast-backward loader">
					Voltar	
				</a>
			</div>
		</div>

		<hr />

		<div class="mt-2 row">
			<div class="text-left col-6 col-md-6">
				<span>
					<a class="btn btn-success fas fa-chevron-left" 
	                	href="${contextPath}/servico/previous?idServicoAtual=${servico.id}">
	                </a>
				</span>
			</div>
			<div class="text-right col-6 col-md-6">
				<a	class="btn btn-success fas fa-chevron-right" 
	                	href="${contextPath}/servico/next?idServicoAtual=${servico.id}">
	            </a>
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
				<small hidden class="form-text obrigatorio descricao"></small>
			</div>
			
			<div class="form-group">
				<label class="color-font-label moeda" for="preco">Preço <span class="obrigatorio">*</span></label> 
				
				<fmt:formatNumber var="precoFormatado" pattern="#,##0.00" type="currency" value="${servico.preco}"/>
				
				<input type="text" value="${precoFormatado}" name="preco" class="form-control moeda" id="preco">
				<small hidden class="form-text obrigatorio preco"></small>
			</div>

			<div class="text-center alinhamento">
				<button id="btn-confirmar-alteracao" type="submit"
					class="btn btn-color-salon btn-block fas fa-check">
						Alterar Serviço
				</button>
			</div>
			<div class="text-center alinhamento mt-2" style="margin-bottom: 80px;">
				<a type="button" data-toggle="modal" data-target="#modalExcluir"
					class="btn btn-danger fas fa-trash-alt btn-block"> Excluir</a>
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
		
		<!-- Modal Message exclusão inválida -->
		<div class="modal fade" id="modalMessageExlusaoInvalida" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
							<h5 class="modal-title" id="title">Atenção!!</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                                <span aria-hidden="true">&times;</span>
	                            </button>
					</div>
					<div id="body" class="modal-body">
						Exclusão inválida. Este Serviço possuí atendimentos vinculados.
					</div>
					<div class="modal-footer"></div>
				</div>
			</div>
		</div>
		
		<!-- Modal Message exclusão válida -->
		<div class="modal fade" id="modalMessageExclusaoValida" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
							<h5 class="modal-title" id="title">Serviço</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                                <span aria-hidden="true">&times;</span>
	                            </button>
					</div>
					<div id="body" class="modal-body">
						Serviço excluído com sucesso
					</div>
					<div class="modal-footer"></div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" charset="UTF-8" src="../resources/js/servico/detalhe.js"></script>

</tags:pageTemplate>