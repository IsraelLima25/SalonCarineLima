<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulo" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
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

	<jsp:doBody />

	<!-- Futuro Rodapé  -->

	<script src="${rsc}/bootstrap-4.5.2/js/bootstrap.min.js"></script>
	<script src="${rsc}/popper/popper.js"></script>
	<script src="${rsc}/js/global.js"></script>
	<script src="${rsc}/js/filter-table.js"></script>

</body>
</html>


