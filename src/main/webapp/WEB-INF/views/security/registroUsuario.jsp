<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="pt-br">
<head>
<meta charset="utf-8" />
<title>SSC - Registrar</title>
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
	 <main>
        <section>
          	  <div id="signupbox" class="mt-4 mb-4 container mainbox col-lg-6 offset-md-3 col-md-8 offset-sm-2">
			        <div class="card card-inverse card-info">
			            <div class="card-header">
			                <div class="card-title"><h4>Cadastre-se</h4></div>
			                <div style="float: right; position: relative; top: -10px;">
			                <a id="signinlink" href="../SalonCarineLima/login">
			                	Login
			                </a>
			                </div>
			            </div>
			            <div class="card-block mt-3">
			                <form id="signupform" role="form">
			                    <div id="signupalert" style="display: none;" class="alert alert-danger">
			                        <p>Error:</p> <span></span>
			                    </div>
			                    <div class="form-group">
			                        <label for="email" class="col-lg-3 form-control-label">Email</label>
			                        <div class="col-lg-12">
			                            <input type="text" class="form-control" name="email" placeholder="Email Address"
			                            />
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label for="firstname" class="col-lg-3 form-control-label">First Name</label>
			                        <div class="col-lg-12">
			                            <input type="text" class="form-control" name="firstname" placeholder="First Name"
			                            />
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label for="lastname" class="col-lg-3 form-control-label">Last Name</label>
			                        <div class="col-lg-12">
			                            <input type="text" class="form-control" name="lastname" placeholder="Last Name"
			                            />
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label for="password" class="col-lg-3 form-control-label">Password</label>
			                        <div class="col-lg-12">
			                            <input type="password" class="form-control" name="passwd" placeholder="Password"
			                            />
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label for="icode" class="col-lg-3 form-control-label">Invitation Code</label>
			                        <div class="col-lg-12">
			                            <input type="text" class="form-control" name="icode" placeholder="" />
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <!-- Button -->
			                        <div class="col-lg-12">
			                            <button id="btn-signup" type="button" 
			                            class="btn btn-color-salon btn-block">
			                            <i class="icon-hand-right"></i> 
			                            	Cadastrar
			                            </button> 
			                        </div>
			                    </div>
			                </form>
			            </div>
			        </div>
				</div>
        </section>
    </main>					
</main>

	<script src="${rsc}/js/global.js"></script>
	<script src="${rsc}/bootstrap-4.5.2/js/bootstrap.min.js"></script>
	<script src="${rsc}/popper/popper.js"></script>

</body>
</html>