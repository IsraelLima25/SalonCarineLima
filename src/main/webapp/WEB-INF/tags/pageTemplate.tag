
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulo" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" charset="UTF-8"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>${titulo}-SalãoCarine</title>

<c:url value="/resources" var="rsc" />

<link rel="stylesheet"
	href="${rsc}/bootstrap-4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="${rsc}/css/style.css" />

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

<script src="${rsc}/jquery-3.5.1/jquery-3.5.1.js"></script>

</head>

<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp"%>

	<!-- Conteudo da pagina -->

	<!--Botão voltar para o topo -->
	<div class="back-to-top" href="#">
		<i class="fas fa-chevron-up"></i>
	</div>

	<!-- Modal Global -->
	<div class="modal fade" id="modalPage" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<img src="../resources/img/logomarca-removebg-preview.png"
								width="50" height="50" alt="logomarca">
						<h5 class="modal-title" id="title"></h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
				</div>
				<div id="body" class="modal-body"></div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>

	<jsp:doBody />

	<!-- Futuro Rodapé  -->

	<%-- 	<script charset="UTF-8" src="${rsc}/js/global.js"></script> --%>
	<script src="../resources/js/global.js"></script>
	<script src="${rsc}/bootstrap-4.5.2/js/bootstrap.min.js"></script>
	<script src="${rsc}/popper/popper.js"></script>
	<script src="${rsc}/js/filter-table.js"></script>

</body>
</html>


