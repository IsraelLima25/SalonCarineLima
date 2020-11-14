<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%> --%>
<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<title>SAG - Registrar</title>
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
        	  <c:url value="../SalonCarineLima" var="context" />
          	  <div id="signupbox" class="mt-4 mb-4 container mainbox col-lg-6 offset-md-3 col-md-8 offset-sm-2">
			        	<div class="card card-inverse card-info">
				            <div class="card-header">
				        	<h3 class="text-center mt-3 color-font" >SAG - Agendamentos</h3>
				                <div class="card-title">
				                	<h5 class="text-center">Registrar Usuário</h5>
				                </div>
				                <div class="text-right">
				                	<a href="${context}/login">Login</a>
				                </div>
				            </div>
			        	</div>
			        	
			   			<c:if test="${not empty atencao}">
							<div class="alert alert-warning alert-dismissible fade show mt-3" role="alert">
							  ${atencao}: <a href="${context}/esqueciSenha">recuperar senha</a>
							  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
							    <span aria-hidden="true">&times;</span>
							  </button>
							</div>
						</c:if>
			        
			            
			            <!--Botão voltar para o topo -->
						<div class="back-to-top" href="#" style="margin-bottom: 50px;">
							<i class="fas fa-chevron-up"></i>
						</div>
			            
			            <div class="card-block mt-4">
			                <form:form method="post" enctype="UTF-8" action="${s:mvcUrl('registerUser').build()}" 
			                  role="form" modelAttribute="usuario" >
			                
			             	    <div class="form-group">
			                        <label for="nome" class="col-lg-3 form-control-label">
			                        	Nome
			                        	<span class="obrigatorio">*</span>
			                        </label>
			                        <div class="col-lg-12">
			                            <form:input path="nome" placeholder="examplo: xxxx_xxxx" cssClass="form-control"/>
			                            <small><form:errors cssClass="obrigatorio" path="nome"/></small>
			                        </div>
			                    </div>
			                    
			                    <div class="form-group">
			                        <label for="email" class="col-lg-3 form-control-label">
			                        	Email
			                        	<span class="obrigatorio">*</span>
			                        </label>
			                        <div class="col-lg-12">
			                            <form:input id="email" type="text" 
			                            	class="form-control" path="email"/>
			                            <small><form:errors cssClass="obrigatorio" path="email"/></small>
			                        </div>
			                    </div>
			                    
			                	<div class="form-group">
			                        <label for="cep" class="col-lg-3 form-control-label">Cep</label>
			                        <div class="col-lg-12">
			                            <form:input id="cep" type="number" 
			                            class="form-control" path="endereco.cep"/>
			                            <small hidden id="cepMsgValid" class="form-text obrigatorio"></small>
			                            
			                        </div>
			                    </div>
			                    
			                    <div class="form-group">
			                        <label for="bairro" class="col-lg-3 form-control-label">
			                       		 Bairro
			                       		 <span class="obrigatorio">*</span>
			                        </label>
			                        <div class="col-lg-12">
			                            <form:input id="bairro" type="text" 
			                            class="form-control" path="endereco.bairro"/>
			                            <small><form:errors cssClass="obrigatorio" path="endereco.bairro"/></small>
			                        </div>
			                    </div>
			                    
			                    <div class="form-group">
			                        <label for="logradouro" class="col-lg-3 form-control-label">
			                       		 Logradouro
			                       		 <span class="obrigatorio">*</span>
			                        </label>
			                        <div class="col-lg-12">
			                            <form:input id="logradouro" type="text" 
			                            	class="form-control" path="endereco.logradouro"/>
			                        </div>
			                    </div>
			                    
			                    <div class="form-group">
			                        <label for="numero" class="col-lg-3 form-control-label">Numero</label>
			                        <div class="col-lg-12">
			                            <form:input id="numero" type="text" 
			                            	class="form-control" path="endereco.numero"/>
			                        </div>
			                    </div>
			                    
			                    <div class="form-group">
			                        <label for="complemento" class="col-lg-3 form-control-label">
			                       		 Complemento
			                        </label>
			                        <div class="col-lg-12">
			                            <form:input id="complemento" type="text" 
			                            class="form-control" path="endereco.complemento"/>
			                        </div>
			                    </div>
			                    
			                    <div class="form-group">
			                        <label for="ponto-referencia" class="col-lg-3 form-control-label">
			                        	Ponto Referencia
			                        </label>
			                        <div class="col-lg-12">
			                            <form:input id="ponto-referencia" type="text" 
			                            class="form-control" path="endereco.pontoReferencia"/>
			                        </div>
			                    </div>
			                    
			                    <div class="form-group">
			                        <label for="senha" class="col-lg-3 form-control-label">
				                        Senha
			                        <span class="obrigatorio">*</span>
			                        </label>
			                        <div class="col-lg-12">
			                            <form:password id="senha"
			                            class="form-control" path="password.senha"/>
			                            <small><form:errors cssClass="obrigatorio" path="password.senha" /></small>
			                        </div>
			                    </div>
			                    
			                    <div class="form-group">
			                        <label for="confirmar-senha" class="col-lg-3 form-control-label">
			                       		 Confirmar Senha
			                       		 <span class="obrigatorio">*</span>
			                        </label>
			                        <div class="col-lg-12">
			                            <form:password id="confirmar-senha"
			                             class="form-control" path="password.confirmacaoSenha"/>
			                             <small>
			                            	 <form:errors cssClass="obrigatorio" path="password.confirmacaoSenha" />
			                             	 <form:errors cssClass="obrigatorio" path="password" />
			                             </small>
			                        </div>
			                    </div>
			                  
			                    <div class="form-group">
			                        <label for="chave-acesso" class="col-lg-3 form-control-label">
			                        	Chave de Acesso
			                        	<span class="obrigatorio">*</span>
			                        </label>
			                        <div class="col-lg-12">
			                            <form:input id="chave-acesso" type="text" 
			                            	class="form-control" path="chave"/>
			                           <small><form:errors cssClass="obrigatorio" path="chave" /></small>
			                            	
			                        </div>
			                    </div>
			                    
			                    <div class="form-group">
			                        <!-- Button -->
			                        <div class="col-lg-12 mt-5">
			                            <button id="btn-signup" type="submit" 
			                            class="btn btn-color-salon btn-block loader">
			                            <i class="icon-hand-right"></i> 
			                            	Cadastrar
			                            </button> 
			                        </div>
			                    </div>
			                    
			                </form:form>
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
</main>

	<script src="${rsc}/js/global.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${rsc}/js/via-cep.js"></script>
	<script src="${rsc}/bootstrap-4.5.2/js/bootstrap.min.js"></script>
	<script src="${rsc}/popper/popper.js"></script>

</body>
</html>