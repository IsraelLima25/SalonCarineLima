<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<tags:pageTemplate titulo="Lista Cliente">

	<div class="container">
		<input type="number" id="registroAtual" hidden />
		<h4 class="mt-4 color-font">Catalogo Clientes</h4>
		<hr />

		<!-- Message Client-->
		<div id="mensagemClient" hidden role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<!-- Message Server-->
		<c:if test="${message != null}">
			<div id="mensagemServer" class="alert alert-${message.classe}"
				role="alert">${message.text}</div>
		</c:if>
		<div class="form-group">
			<label class="color-font-label" for="nome-filter">Filtrar por
				nome</label> <input type="text" onkeyup="filtrar()" class="form-control"
				id="nome-filter">
			<!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
		</div>

		<table id="cliente-table" class="table">
			<thead>
				<tr>
					<th scope="col">Lista de Clientes</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>

				<tr id="msg-nenhum-registro-encontrado" hidden>
					<td>
						<p>
							<i>Nenhum cliente encontrado</i>
						</p>
					</td>
				</tr>

				<c:if test="${lista.size()==0}">
					<tr>
						<td>
							<p>
								<i>Nenhum cliente cadastrado</i>
							</p>
						</td>
					</tr>
				</c:if>

				<c:forEach items="${lista}" var="cliente">
					<tr>
						<td hidden>${cliente.id}</td>
						<td>${cliente.nome}</td>
						<td class="text-center"><a type="button"
							onclick="atualRemover(${cliente.id});" data-toggle="modal"
							data-target="#modalExluir"> <i class="fas fa-trash-alt"></i>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="modalExluir" tabindex="-1"
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
				<div class="modal-body">Deseja realmente excluir este cliente?
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" style="width: 100px;"
						class="btn btn-color-salon" onclick="removerCliente();">Sim</button>
				</div>
			</div>
		</div>
	</div>

	<script src="../resources/js/catalogoCliente.js"></script>

</tags:pageTemplate>


