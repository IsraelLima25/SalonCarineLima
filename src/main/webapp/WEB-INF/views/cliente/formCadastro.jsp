<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro Cliente">
	<main>
		<section>
			<div class="container">
				<h4 class="mt-4 color-font">Cadastrar Cliente</h4>
				<hr />
				<form action="/SalonCarineLima/cliente" method="POST">
					<div class="form-group">
						<label class="color-font-label" for="nome">Nome</label> <input
							type="text" class="form-control" id="nome" name="nome">
						<!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
					</div>
					<div class="form-group">
						<label class="color-font-label" for="email">Email</label> <input
							type="email" class="form-control" id="email" name="email">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="telefone">Telefone</label> <input
							type="tel" class="form-control" id="telefone" name="telefone">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="cep">Cep</label> <input
							type="number" class="form-control" id="cep" name="endereco.cep">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="logradouro">Logradouro</label>
						<input type="text" class="form-control" id="logradouro"
							name="endereco.logradouro">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="numero">Numero</label> <input
							type="number" class="form-control" id="numero"
							name="endereco.numero">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="ponto-referencia">Ponto
							Referência</label> <input type="text" class="form-control"
							name="endereco.pontoReferencia" id="ponto-referencia">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="complemento">Complemento</label>
						<input type="text" class="form-control" id="complemento"
							name="endereco.complemento">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="bairro">Bairro</label> <input
							type="text" class="form-control" id="bairro"
							name="endereco.bairro">
					</div>
					<div class="text-center alinhamento">
						<button id="btnCadastrar" style="margin-bottom: 16px;"
							data-toggle="modal" type="submit"
							class="btn btn-color-salon btn-block">Cadastrar</button>
					</div>

				</form>
			</div>

			<div class="back-to-top" href="#">
				<i class="fas fa-chevron-up"></i>
			</div>

		</section>
	</main>

	<script src="resources/js/global.js"></script>
</tags:pageTemplate>


