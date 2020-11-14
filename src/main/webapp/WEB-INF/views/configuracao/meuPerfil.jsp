<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%><%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<tags:pageTemplate titulo="Meu Perfil">
	<main>
		<section>
			<div class="container">
				<h4 class="color-font">Meu Perfil</h4>
				<hr>
				<c:if test="${not empty sucesso}">
					<div class="alert alert-warning alert-dismissible fade show mt-3"
						role="alert">
						${sucesso}
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</c:if>

				<form:form method="post" enctype="UTF-8"
					action="${s:mvcUrl('alterarPerfil').build()}"
					modelAttribute="usuario">
					
					<div class="form-group">
						<label for="email" class="col-lg-3 form-control-label">
							Email <span class="obrigatorio">*</span>
						</label>
						<div class="col-lg-12">
							<form:input disabled="true" id="email" type="text" class="form-control"
								path="email" />
							<small><form:errors cssClass="obrigatorio" path="email" /></small>
						</div>
					</div>
					
					<div class="form-group">
						<label for="nome" class="col-lg-3 form-control-label">
							Nome <span class="obrigatorio">*</span>
						</label>
						<div class="col-lg-12">
							<form:input path="nome" placeholder="examplo: xxxx_xxxx"
								cssClass="form-control" />
							<small><form:errors cssClass="obrigatorio" path="nome" /></small>
						</div>
					</div>


					<div class="form-group">
						<label for="cep" class="col-lg-3 form-control-label">Cep</label>
						<div class="col-lg-12">
							<form:input id="cep" type="number" class="form-control"
								path="endereco.cep" />
							<small hidden id="cepMsgValid" class="form-text obrigatorio"></small>

						</div>
					</div>

					<div class="form-group">
						<label for="bairro" class="col-lg-3 form-control-label">
							Bairro <span class="obrigatorio">*</span>
						</label>
						<div class="col-lg-12">
							<form:input id="bairro" type="text" class="form-control"
								path="endereco.bairro" />
							<small><form:errors cssClass="obrigatorio"
									path="endereco.bairro" /></small>
						</div>
					</div>

					<div class="form-group">
						<label for="logradouro" class="col-lg-3 form-control-label">
							Logradouro <span class="obrigatorio">*</span>
						</label>
						<div class="col-lg-12">
							<form:input id="logradouro" type="text" class="form-control"
								path="endereco.logradouro" />
						</div>
					</div>

					<div class="form-group">
						<label for="numero" class="col-lg-3 form-control-label">Numero</label>
						<div class="col-lg-12">
							<form:input id="numero" type="text" class="form-control"
								path="endereco.numero" />
						</div>
					</div>

					<div class="form-group">
						<label for="complemento" class="col-lg-3 form-control-label">
							Complemento </label>
						<div class="col-lg-12">
							<form:input id="complemento" type="text" class="form-control"
								path="endereco.complemento" />
						</div>
					</div>

					<div class="form-group">
						<label for="ponto-referencia" class="col-lg-3 form-control-label">
							Ponto Referencia </label>
						<div class="col-lg-12">
							<form:input id="ponto-referencia" type="text"
								class="form-control" path="endereco.pontoReferencia" />
						</div>
					</div>

					<div class="form-group">
						<!-- Button -->
						<div class="col-lg-12" style="margin-bottom: 80px;">
							<button id="btn-signup" type="submit"
								class="btn btn-color-salon btn-block loader">
								<i class="icon-hand-right"></i> Alterar
							</button>
						</div>
					</div>

				</form:form>

			</div>
		</section>
	</main>
	
<%-- 	<script type="text/javascript" charset="UTF-8" src="${rsc}/js/via-cep.js"></script> --%>
</tags:pageTemplate>


