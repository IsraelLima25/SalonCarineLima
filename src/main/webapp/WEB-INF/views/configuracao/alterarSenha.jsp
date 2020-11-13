<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<tags:pageTemplate titulo="Alterar Senha">
	<main>
		<section>
			<div class="container">
				<h4 class="color-font">Alterar Senha</h4>
				<hr>
				<c:if test="${not empty atencao}">
						<div class="alert alert-warning alert-dismissible fade show mt-3" role="alert">
							  	${sucesso}
							  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
							    <span aria-hidden="true">&times;</span>
							  </button>
						</div>
				</c:if>
				<form:form method="post" role="form" enctype="UTF-8" 
			        modelAttribute="changePasswordForm" action="${s:mvcUrl('alterarSenhaAcesso').build()}">
			          
			          <div class="form-group">
			            <label for="senhaAtual" class="col-form-label">Senha atual</label>
			            <form:password path="senhaAtual" class="form-control"/>
			            <small><form:errors path="senhaAtual" cssClass="obrigatorio"></form:errors></small>
			          </div>
			          <div class="form-group">
			            <label for="passwordDTO.senha" class="col-form-label">Nova senha</label>
			            <form:password  path="passwordDTO.senha" class="form-control" />
			            <small><form:errors cssClass="obrigatorio" path="passwordDTO.senha" /></small>
			          </div>
			          <div class="form-group">
			            <label for="passwordDTO.confirmacaoSenha" class="col-form-label">Confirmar nova senha</label>
			            <form:password path="passwordDTO.confirmacaoSenha" class="form-control" />
			            <small><form:errors cssClass="obrigatorio" path="passwordDTO.confirmacaoSenha" /></small>
			            <small><form:errors cssClass="obrigatorio" path="passwordDTO" /></small>
			          </div>
				        <button type="submit" class="btn btn-color-salon btn-block">Alterar</button>
			   </form:form>
			</div>
		</section>
	</main>
</tags:pageTemplate>


