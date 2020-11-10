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
			    <div id="loginbox" style="margin-top: 50px;" class="mainbox col-lg-6 offset-md-3 col-md-8 offset-sm-2">
			        <div class="card card-inverse card-info">
			            <div class="card-header">
			        	<h3 class="text-center mt-3 color-font" >SAG - Agendamentos</h3>
			                <div class="card-title">
			                	<h5 class="text-center">Login</h5>
			                </div>
			                </div>
			            </div>
			            <div style="padding-top: 30px;" class="card-block">
			                <div style="display: none;" id="login-alert" class="alert alert-danger col-md-12"></div>
			                <form:form id="loginform" class="" role="form">
			                    <div style="margin-bottom: 25px;" class="input-group"> 
			                    	<span class="input-group-addon"></span>
			                        <input id="login-username" type="text" class="form-control" 
			                        name="username" value="" placeholder="email" />
			                    </div>
			                    <div style="margin-bottom: 25px;" class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			                        <input id="login-password" type="password" 
			                        class="form-control" name="password" placeholder="senha" />
			                    </div>
			                    <div class="input-group">
			                        <div class="form-check ml-3">
			                            <label for="remember-me">
											<input type="checkbox" id="remember-me" name="remember-me" class="form-check-input">
			                                 Lembrar-me
			                            </label>
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <!-- Button -->
			                        <div class="col-md-12 controls"> 
			                        	<button id="btn-login" type="submit" class="btn btn-color-salon btn-block">
			                        		Entrar
			                        	</button>
			                        </div>
			                    </div>
			                    
			                    <div class="text-center">
			                    	<a href="/SalonCarineLima/esqueciSenha">Esqueceu a senha?</a>
			                    </div>
			                    
			                    <div class="form-group">
			                        <div class="col-lg-12 control text-center mt-3">
			                            <div>Não tem conta? 
			                            <a href="../SalonCarineLima/registrar" style="display: block;">
			                                 cadastre-se agora
			                            </a>
			                            </div>
			                        </div>
			                    </div>
			                </form:form>
			            </div>
			        </div>
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