<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Relatórios Cliente">

	<main> <c:set var="contextPath"
		value="${pageContext.request.contextPath}" />

	<section id="section-cadastro-cliente">

		<span id="contextPage" hidden> ${contextPath}</span>

		<div class="container container-space-default">
			<h4 class="color-font">Emitir relatórios dos clientes</h4>
			<hr />
			<div class="row">
				<div class="col-md-6">
					<label class="color-font-label" for="slcTipoRelatorio">Selecionar relatório</label>
					<select class="form-control" id="slcTipoRelatorio">
						<option value="cadastrados">Relatório de clientes cadastrados</option>
						<option value="cadastrados-agrupados-bairro">Relatório de clientes cadastrados agrupados por bairro</option>
					</select>
				</div>
			</div>
			<div class="mt-1">
				<button type="button" onclick="gerarRelatorio('o')" class="btn btn-color-salon btn-sm">
					Abrir Relatório
				</button>
				<button type="button" onclick ="gerarRelatorio('d')" class="btn btn-color-salon btn-sm">
					Download Relatório
				</button>
			</div>
		</div>
	</section>

	</main>
	
	<script src="../resources/js/cliente/report.js"></script>

</tags:pageTemplate>


