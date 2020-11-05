<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Loja">

	<main>
		<section>
			<div class="container">
				<div class="row">
					<div class="col-6 col-lg-6">
						<h4 class="mt-4 color-font">Loja de Serviços</h4>
					</div>
					<div class="col-6 col-lg-6 text-right">
						<a class="btn btn-color-salon fas fa-cart-plus"
						href="../carrinho/itensCarrinho"> 
						</a>
					</div>
				</div>
				<hr />

				<div class="row">
					<c:forEach items="${servicos}" var="servico">
						<div class="col-12 col-lg-3">
							<div class="container container-card pb-5">
								<div class="justify-content-md-around justify-content-center">
									<article class="card card-imagem">
										<div class="card-body">
											<h5 class="card-title">${servico.descricao}</h5>
											<p class="card-text">Preço: R$ ${servico.preco}</p>
											<a type="button" onclick="adicionarItemCarrinho(${servico.id})"
												class="btn btn-color-salon-card
										fas fa-cart-plus">
												Adicionar</a>
										</div>
									</article>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

			</div>
		</section>
	</main>

	<script type="text/javascript" charset="UTF-8" src="../resources/js/loja/loja.js"></script>

</tags:pageTemplate>