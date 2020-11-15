<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>SAG - Conta Acesso</title>
<c:url value="/resources" var="rsc" />
<link rel="stylesheet"
	href="${rsc}/bootstrap-4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="${rsc}/css/style.css" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="${rsc}/fontawesome-5.15.1/css/all.css" />

<script src="${rsc}/jquery-3.5.1/jquery-3.5.1.js"></script>
<script src="${rsc}/jquery-3.5.1/jquery.mask.min.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="color-font">SAG Informa</h1>
		
		<p>Atenção!! Esta sessão expirou (possivelmente devido à tentativa de vários logins 
				simultâneos com o mesmo usuário).</p>
				
		<a href="/SalonCarineLima/login">voltar para página de login</a>
	</div>

	<script src="${rsc}/js/global.js"></script>
	<script src="${rsc}/bootstrap-4.5.2/js/bootstrap.min.js"></script>
	<script src="${rsc}/popper/popper.js"></script>			
</body>
</html>