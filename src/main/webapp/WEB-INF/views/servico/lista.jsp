<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tags:pageTemplate titulo="Lista Serviços">


	<div class="container">
		<h4 class="mt-4 color-font">Lista de Servicos</h4>
		<hr />

		<div class="form-group">
			<label class="mt-3 color-font-label" for="nome-filter">Filtrar
				por descricao</label> <input type="text" onkeyup="filtrar()"
				class="form-control" id="nome-filter">
		</div>

		<table id="servico-table" class="mt-5 table table-striped">
			<thead>
				<tr>
					<th scope="col">Descricao</th>
					<th scope="col">Preço</th>
					<th class="text-center" scope="col">Detalhe</th>
				</tr>
			</thead>
			<tbody>

				<tr id="msg-nenhum-registro-encontrado" hidden>
					<td>
						<p>
							<i>Nenhum serviço encontrado</i>
						</p>
					</td>
				</tr>

				<c:if test="${lista.size()==0}">
					<tr>
						<p>
							<i>Nenhum serviço cadastrado</i>
						</p>
					</tr>
				</c:if>

				<c:forEach items="${lista}" var="servico">
					<tr>
						<td>${servico.descricao}</td>
						<td>${servico.preco}</td>
						<td class="text-center"><a type="button"
							class="fas fa-search btn btn-info"
							onclick="detalharCliente(${servico.id});"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>


	<script src="../resources/js/servico/catalogo.js"></script>

</tags:pageTemplate>
