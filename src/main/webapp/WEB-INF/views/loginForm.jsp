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
			                <div class="card-title"><h5>Login</h5></div>
			                <div style="float: right; font-size: 80%; position: relative; top: -10px;"><a href="#">Esqueceu a senha?</a>
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
			                    <div class="form-group">
			                        <div class="col-lg-12 control text-center">
			                            <div style="padding-top: 15px; font-size: 85%;">Não tem conta? 
			                            <a href="#" onclick="$('#loginbox').hide(); $('#signupbox').show()">
			                                 cadastre-se agora
			                            </a>
			                            </div>
			                        </div>
			                    </div>
			                </form:form>
			            </div>
			        </div>
			    </div>
			    <div id="signupbox" style="display: none; margin-top: 50px;" class="mainbox col-lg-6 offset-md-3 col-md-8 offset-sm-2">
			        <div class="card card-inverse card-info">
			            <div class="card-header">
			                <div class="card-title">Sign Up</div>
			                <div style="float: right; font-size: 85%; position: relative; top: -10px;"><a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign In</a>
			                </div>
			            </div>
			            <div class="card-block">
			                <form id="signupform" class="" role="form">
			                    <div id="signupalert" style="display: none;" class="alert alert-danger">
			                        <p>Error:</p> <span></span>
			                    </div>
			                    <div class="form-group">
			                        <label for="email" class="col-lg-3 form-control-label">Email</label>
			                        <div class="col-lg-9">
			                            <input type="text" class="form-control" name="email" placeholder="Email Address"
			                            />
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label for="firstname" class="col-lg-3 form-control-label">First Name</label>
			                        <div class="col-lg-9">
			                            <input type="text" class="form-control" name="firstname" placeholder="First Name"
			                            />
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label for="lastname" class="col-lg-3 form-control-label">Last Name</label>
			                        <div class="col-lg-9">
			                            <input type="text" class="form-control" name="lastname" placeholder="Last Name"
			                            />
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label for="password" class="col-lg-3 form-control-label">Password</label>
			                        <div class="col-lg-9">
			                            <input type="password" class="form-control" name="passwd" placeholder="Password"
			                            />
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label for="icode" class="col-lg-3 form-control-label">Invitation Code</label>
			                        <div class="col-lg-9">
			                            <input type="text" class="form-control" name="icode" placeholder="" />
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <!-- Button -->
			                        <div class="offset-md-3 col-lg-9">
			                            <button id="btn-signup" type="button" class="btn btn-info"><i class="icon-hand-right"></i> &nbsp Sign Up</button> <span style="margin-left: 8px;">or</span> 
			                        </div>
			                    </div>
			                    <div style="border-top: 1px solid #999; padding-top: 20px;" class="form-group">
			                        <div class="offset-md-3 col-lg-9">
			                            <button id="btn-fbsignup" type="button" class="btn btn-primary"><i class="icon-facebook"></i>   Sign Up with Facebook</button>
			                        </div>
			                    </div>
			                </form>
			            </div>
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