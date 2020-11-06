<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Carrinho">

	<main>
		<section>
			<div class="container">

				<div class="row">
					<div class="col-6 col-lg-6">
						<h4 class="color-font mt-4">Carrinho de Serviços</h4>
					</div>
					<div class="col-6 col-lg-6 text-right">
						<a class="btn btn-color-salon fas fa-store loader"
						href="../loja/itensLoja"> 
						</a>
					</div>
				</div>
				
				<hr />

				<div class="total-carrinho mt-3">
					<div class="row">
						<div class="col-6 col-md-6 text-left mt-4"></div>
						<div class="col-6 col-md-6 text-right">
							<p>
								<b>Total Carrinho</b>
							</p>
							<p>
								<b id="total">R$ ${total}</b>
							</p>
						</div>
					</div>
				</div>
				<hr />
				<div>
					<c:choose>
						<c:when test="${itens.size() == 0}">
							<tr>
								<p>
									<i>Nenhum serviço adicionado ao carrinho</i>
								</p>
							</tr>

						</c:when>
						<c:otherwise>
							<c:forEach items="${itens}" var="item">
								<div class="item-carrinho mt-4">
									<div class="row">
										<div class="col-4 col-md-4 text-center">
											<p>Preço</p>
											<p>R$ ${item.servicoDTO.preco}</p>
										</div>
										<div class="col-4 col-md-4 text-center">
											<p>${item.servicoDTO.descricao}</p>
										</div>
										<div class="col-4 col-md-4 text-center">
											<p>Total</p>
											<p id="total-${item.servicoDTO.id}">R$ ${item.precoTotal}</p>
										</div>
									</div>
									<div class="mt-2 row">

										<div class="col-4 col-md-4 text-center">
											<a type="button" class="text-center btn btn-color-salon-card"
												onclick="removerItem(${item.servicoDTO.id})"> <i
												class="fas fa-minus-circle"></i>
											</a>
										</div>

										<div class="col-4 col-md-4 text-center">
											<p>Quantidade</p>
											<p id="quantidade-${item.servicoDTO.id}">${item.quantidade}</p>
										</div>
										<div class="col-4 col-md-4 text-center">
											<a class="btn btn-color-salon-card"
												onclick="incrementarItem(${item.servicoDTO.id})"> <i
												class="fas fa-plus-circle"></i>
											</a>
										</div>
									</div>

									<div class="row mt-2">
										<div class="col-12 col-md-12 text-center">
											<button class="btn btn-danger"
												onclick="removerServico(${item.servicoDTO.id})">
												<i class="fas fa-trash-alt"></i> Remover item do carrinho
											</button>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>

				<div class="text-center alinhamento">
					<button id="btnMarcarAtendimento" spellcheck="false" style="margin-bottom: 16px;"
						class="btn btn-color-salon btn-block" onclick="marcarAtendimento()">
							Marcar Atendimento
						</button>
				</div>

			</div>
		</section>
	</main>

	<script type="text/javascript" charset="UTF-8"
		src="../resources/js/carrinho/carrinho-itens.js"></script>

</tags:pageTemplate>
