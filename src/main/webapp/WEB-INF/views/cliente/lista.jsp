<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<tags:pageTemplate titulo="Lista Cliente">

	<div class="container">
		<h4 class="mt-4 color-font">Catalogo Clientes</h4>
		<hr />
		<!-- Message Client -->
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
						<td>
							<a type="button" onclick="detalharCliente(${cliente.id});">
							${cliente.nome}
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<script src="../resources/js/cliente/catalogo.js"></script>

</tags:pageTemplate>


