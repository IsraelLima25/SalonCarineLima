<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Carrinho">

	<main>
		<section>
			<div class="container">

				<h4 class="mt-4 color-font">Carrinho de Serviços</h4>
				<hr />

				<div class="total-carrinho mt-3">
					<div class="row">
						<div class="col-6 col-md-6 text-left">
							<p>
								<button class="btn btn-success">Atender</button>
							</p>
						</div>
						<div class="col-6 col-md-6 text-right">
							<p>
								<b>Total Carrinho</b>
							</p>
							<p>
								<b>R$ 50000,00</b>
							</p>
						</div>
					</div>
				</div>
				<hr />
				<div class="item-carrinho mt-4">
					<div class="row">
						<div class="col-4 col-md-4 text-center">
							<p>Preço</p>
							<p>R$ 20,00</p>
						</div>
						<div class="col-4 col-md-4 text-center">
							<p>Descricao do Servico</p>
						</div>
						<div class="col-4 col-md-4 text-center">
							<p>Total</p>
							<p>R$ 20,00</p>
						</div>
					</div>
					<div class="mt-2 row">
						<div class="col-4 col-md-4 text-center">
							<a href="#" class="text-center btn btn-color-salon-card"><i
								class="fas fa-minus-circle"></i></a>
						</div>
						<div class="col-4 col-md-4 text-center">
							<p>Quantidade</p>
							<p>1</p>
						</div>
						<div class="col-4 col-md-4 text-center">
							<a href="#" class="btn btn-color-salon-card"><i
								class="fas fa-plus-circle"></i></a>
						</div>
					</div>

					<div class="row mt-2">
						<div class="col-12 col-md-12 text-center">
							<button class="btn btn-danger">
								<i class="fas fa-trash-alt"></i> Remover item do carrinho
							</button>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>

	<script type="text/javascript" charset="UTF-8"
		src="../resources/js/carrinho/carrinho-itens.js"></script>

</tags:pageTemplate>
