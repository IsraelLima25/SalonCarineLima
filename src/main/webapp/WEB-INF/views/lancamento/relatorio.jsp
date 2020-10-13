<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Relatório Lançamento">

	<main>
		<section>
			<div class="container">
				<h4 class="mt-4 color-font">Relatório Lançamento</h4>
				<hr />

				<div id="groupFilterByData">
					<div class="form-group">
						<label class="color-font-label" for="data-inicio">Data Ínicio</label> 
						<input type="date" value="01/11/2020" class="form-control" id="data-inicio">
					</div>
					<div class="form-group">
						<label class="color-font-label" for="data-fim">Data Fim</label> 
						<input type="date" value="31/12/2020" class="form-control" id="data-fim">
					</div>
				</div>
				<div class="row">
					<div class="col-6 col-md-6">
						<button id="btnCadastrar"style="margin-bottom: 16px; width: 120px;"
							 type="button" class="btn btn-color-salon btn-block">
							<i class="fas fa-filter"></i> Filtrar </button>
					</div>
				</div>

				<div style="margin-bottom: 100px; margin-top: 20px;">
					<div class="mt-5">
						<table id="lancamento-table" class="table">
							<thead class="text-center">
								<tr>
									<th scope="col" class="text-left">Serviço</th>
									<th scope="col">Data</th>
									<th scope="col">Valor</th>
									<th class="text-center" scope="col">Detalhe</th>

								</tr>
							</thead>
							<tbody>
								<tr id="msg-nenhum-registro-encontrado" hidden>
									<td>
										<p>
											<i>Nenhum cliente encontrado</i>
										</p>
									</td>

								</tr>
								<tr>
									<td>Sombracelha</td>
									<td class="text-center">15/11/2020</td>

									<td class="text-center">25,00</td>
									<td class="text-center">
										<a class="fas fa-search btn btn-info"></a>
<%-- 										href="${s:mvcUrl('AC#detalheAtendimento').arg(0,atendimento.id).build()}" --%>
									</td>
								</tr>
							</tbody>

						</table>
					</div>
				</div>
			</div>

			<div class="back-to-top" href="#">
				<i class="fas fa-chevron-up"></i>
			</div>

		</section>
	</main>

</tags:pageTemplate>