<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="pt-br">
<head>
<meta charset="utf-8" />
<title>SSC - Login</title>
<c:url value="/resources" var="rsc" />
<link rel="stylesheet"
	href="${rsc}/bootstrap-4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="${rsc}/css/style.css" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

<script src="${rsc}/jquery-3.5.1/jquery-3.5.1.js"></script>
<script src="${rsc}/jquery-3.5.1/jquery.mask.min.js"></script>
</head>
<body>
	<main>
		<section id="login">
			<div class="container">
				<h3 class="color-font text-center">SAG - Agendamentos</h3>
				<h4 class="color-font text-center mt-5">Login</h4>
				<form:form servletRelativeAction="/login" method="POST">
					<div class="form-group">
						<label class="color-font-label" for="email">Email</label> <input
							type="email" name="username" class="form-control" id="email"
							placeholder="Ex: salaobycarine@gmail.com">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="senha">Senha</label> <input
							type="password" name="password" class="form-control" id="senha">
					</div>
					<div class="text-center">
						<input type="checkbox" id="remember-me" name="remember-me" class="form-check-input">
	    				<label class="form-check-label" for="remember-me">Lembrar?</label>
	    			</div>
					<div class="text-center alinhamento-bottom">
						<button type="submit"
							class="btn btn-color-salon btn-lg btn-block fas fa-sign-in-alt
							loader">
							Entrar</button>
						<hr />
						<a href="./esqueci-senha.html">esqueceu a senha?</a>
					</div>
				</form:form>
			</div>
			
		<div class="modal" id="modalLoading" tabindex="-1" data-backdrop="static">
		  <div class="modal-dialog modal-loading">
		    <div class="modal-content">
		      <div class="modal-body text-center">
		        <div class="modal-body text-center">
					 Carregando..
					 <div class="spinner-border text-success" role="status">
		  				<span class="sr-only">Loading...</span>
					 </div>
		       </div>
		      </div>
		    </div>
	   </div>
	</div>
		</section>
	</main>

	<script src="${rsc}/js/global.js"></script>
	<script src="${rsc}/bootstrap-4.5.2/js/bootstrap.min.js"></script>
	<script src="${rsc}/popper/popper.js"></script>

</body>
</html>