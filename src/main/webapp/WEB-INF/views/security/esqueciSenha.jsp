<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<html lang="pt-br">
<head>
<meta charset="utf-8" />
<title>SSC - Recuperar Senha</title>
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
            <div class="container">
            	<div class="row">
            		<div class="text-left col-8 col-md-8">
		                <h4 class="color-font">Recuperar Senha</h4>
            		</div>
		            <div class="text-right col-4 col-md-4">
						<a type="button" href="../SalonCarineLima/login" 
							class="loader">
							voltar
						</a>
					</div>
            	</div>
                <hr/>
                <form:form method="POST" enctype="UTF-8" modelAttribute="usuarioRecovery"
                	action="${s:mvcUrl('solicitarSenha').build()}" role="form">
	                <label for="email">Email</label>
	                <div class="form-group">
	                    <form:input class="form-control" path="email" 
	                    placeholder="Ex: salaobycarine@gmail.com" />
	                </div>
                    <small><form:errors cssClass="obrigatorio" path="email" /></small>
	                <div class="text-center alinhamento">
	                    <button class="btn btn-color-salon loader btn-lg btn-block fas fa-key"
	                    	type="submit">
	                        Recuperar Senha
	                    </button>
	                    <hr />
	                </div>
                </form:form>
            </div>
        </section>
        
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
    </main>					
</main>

	<script src="${rsc}/js/global.js"></script>
	<script src="${rsc}/bootstrap-4.5.2/js/bootstrap.min.js"></script>
	<script src="${rsc}/popper/popper.js"></script>

</body>
</html>