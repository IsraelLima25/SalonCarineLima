<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<tags:pageTemplate titulo="Lista Cliente">

	<div class="container">
		<h4 class="mt-4 color-font">Lista de Clientes</h4>
		<hr />

		<div class="form-group">
			<label class="mt-3 color-font-label" for="nome-filter">Filtrar por nome</label> 
			<input type="text" onkeyup="filtrar()" class="form-control" id="nome-filter">
			<!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
		</div>

		<table id="cliente-table" class="mt-5 table table-striped">
			<thead>
				<tr>
					<th scope="col">Código</th>
					<th scope="col">Nome</th>
					<th class="text-center" scope="col">Detalhe</th>
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
						<p>
							<i>Nenhum cliente cadastrado</i>
						</p>
					</tr>
				</c:if>

				<c:forEach items="${lista}" var="cliente">
					<tr>
						<td>${cliente.id}</td>
						<td> 
							${cliente.nome}
						</td>
						<td class="text-center">
							<a type="button" class="fas fa-search btn btn-info" 
								onclick="detalharCliente(${cliente.id});"></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<script src="../resources/js/cliente/catalogo.js"></script>

</tags:pageTemplate>


