<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro Servico">
	<main>
		<section>
			<div class="container container-space-default">
				<h4 class="color-font">Cadastrar Serviço</h4>
				<hr />
				<form name="formCadastro">
					<div class="form-group">
						<label class="color-font-label" for="descricao">
							Descrição
							<span class="obrigatorio">*</span>
						</label>
						<input name="descricao" type="text" class="form-control" id="descricao">
						<small hidden class="form-text obrigatorio descricao"></small>
					</div>

					<div class="form-group">
						<label class="color-font-label" for="preco">
						Preço
						<span class="obrigatorio">*</span>
						</label> 
						<input type="text" name="preco" class="form-control moeda" id="preco">
						<small hidden class="form-text obrigatorio preco"></small>
					</div>

					<div class="text-center alinhamento">
						<button id="btnCadastrar" style="margin-bottom: 16px;"
								type="submit" class="btn btn-color-salon btn-block">Cadastrar</button>
					</div>
				</form>
			</div>

			<div class="back-to-top" href="#">
				<i class="fas fa-chevron-up"></i>
			</div>

		</section>
	</main>

	<script type="text/javascript" charset="UTF-8" src="../resources/js/servico/cadastro.js"></script>

</tags:pageTemplate>


