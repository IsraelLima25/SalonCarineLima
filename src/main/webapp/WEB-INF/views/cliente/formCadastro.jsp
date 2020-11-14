<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro Cliente">
	<main>
		<section>
			<div class="container container-space-default">
				<h4 class="color-font">Cadastrar Cliente</h4>
				<hr />
				<form:form name="formCadastro">
					<div class="form-group">
						<label class="color-font-label" for="nome">Nome <span class="obrigatorio">*</span></label> <input
							type="text" class="form-control" id="nome" name="nome">
						<small hidden class="form-text obrigatorio nome"></small>
					</div>
					<div class="form-group">
						<label class="color-font-label" for="email">E-mail</label> <input
							type="text" class="form-control" id="email" name="email">
					<small hidden class="form-text obrigatorio email"></small>
							
					</div>
					<div class="form-group">
						<label class="color-font-label" for="telefone">Telefone</label> 
						<input type="text" placeholder="(ddd)99999-9999" class="form-control tel" 
							id="telefone" name="telefone">
							<small hidden class="form-text obrigatorio telefone"></small>
					</div>
					<div class="form-group">
						<label class="color-font-label" for="cep">Cep</label> 
						<input type="text" class="form-control" id="cep" name="endereco.cep">
						<small hidden id="cepMsgValid" class="form-text obrigatorio"></small>
					</div>
					
					<div class="form-group">
						<label class="color-font-label" for="logradouro">Logradouro</label>
						<input type="text" class="form-control" id="logradouro"
							name="endereco.logradouro">
						<small hidden class="form-text obrigatorio logradouro"></small>
					</div>		
					
					<div class="form-group">
						<label class="color-font-label" for="bairro">Bairro <span class="obrigatorio">*</span></label> <input
							type="text" class="form-control" id="bairro"
							name="endereco.bairro">
							<small hidden class="form-text obrigatorio bairro"></small>
					</div>
					
					<div class="form-group">
						<label class="color-font-label" for="complemento">Complemento</label>
						<input type="text" class="form-control" id="complemento"
							name="endereco.complemento">
						<small hidden class="form-text obrigatorio complemento"></small>
					</div>
					
					<div class="form-group">
						<label class="color-font-label" for="numero">Número</label> <input
							type="number" class="form-control" id="numero"
							name="endereco.numero">
						<small hidden class="form-text obrigatorio numero"></small>
					</div>
					
					<div class="form-group">
						<label class="color-font-label" for="ponto-referencia">Ponto
							Referência</label> <input type="text" class="form-control"
							name="endereco.pontoReferencia" id="ponto-referencia">
						<small hidden class="form-text obrigatorio pontoReferencia"></small>
					</div>
					
					<div class="text-center alinhamento">
						<button id="btnCadastrar" style="margin-bottom: 100px;"
							data-toggle="modal" type="submit"
							class="btn btn-color-salon btn-block">Cadastrar</button>
					</div>
				</form:form>
			</div>
		</section>
		
	</main>
	
	<script type="text/javascript" charset="UTF-8" src="../resources/js/cliente/cadastro.js"></script>	
	<script type="text/javascript" charset="UTF-8" src="../resources/js/via-cep.js"></script>
	
</tags:pageTemplate>


