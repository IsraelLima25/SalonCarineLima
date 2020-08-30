<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulo" required="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>${titulo}-SalãoCarine</title>
<link rel="stylesheet"
	href="resources/bootstrap-4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/css/styles.css" />
</head>

<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp"%>

	<!-- Conteudo da pagina -->

	<jsp:doBody />

	<!-- Futuro Rodapé  -->

</body>
</html>


