<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulo" required="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>${titulo}-SalãoCarine</title>
<link rel="stylesheet"
	href="${contextPath}resources/bootstrap-4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="${contextPath}resources/css/style.css" />

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
        integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>

<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp"%>

	<!-- Conteudo da pagina -->

	<jsp:doBody />

	<!-- Futuro Rodapé  -->

	<script src="${contextPath}resources/jquery-3.5.1/jquery-3.5.1.js"></script>
	<script
		src="${contextPath}resources/bootstrap-4.5.2/js/bootstrap.min.js"></script>
	<script src="${contextPath}resources/popper/popper.js"></script>

</body>
</html>


