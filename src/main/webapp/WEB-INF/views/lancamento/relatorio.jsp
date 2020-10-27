<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
						<button id="btnCadastrar"style="margin-bottom: 5px; width: 120px;"
							 type="button" class="btn btn-color-salon btn-block">
							<i class="fas fa-filter"></i> Filtrar </button>
					</div>
				</div>

				<div style="margin-bottom: 100px; margin-top: 10px;">
					<div class="mt-5">
						<div class="text-right alert alert-success" role="alert">
							<b>Valor Total Período: R$ ${totalPeriodo}</b>
						</div>
						<table id="lancamento-table" class="table">
							<thead class="text-center">
								<tr>
									<th scope="col" class="text-left">Valor</th>
									<th scope="col">Data</th>
									<th class="text-center" scope="col">Atendimento</th>
									<th class="text-center" scope="col">Estornar</th>
								</tr>
							</thead>
							<tbody>
								<tr id="msg-nenhum-registro-encontrado" hidden>
									<td>
										<p>
											<i>Nenhum Lançamento encontrado</i>
										</p>
									</td>
								</tr>
								
								<c:if test="${lancamentos.size()==0}">
									<tr>
										<p>
											<i>Nenhum lançamento neste período</i>
										</p>
									</tr>
								</c:if>
								<c:forEach items="${lancamentos}" var="lancamento">
									<tr>
										<td>${lancamento.valorTotal}</td>
										<td class="text-center">
											<fmt:formatDate pattern="dd/MM/yyyy" 
											value="${lancamento.data.getTime()}"/>
										</td>
									
										<td class="text-center">
										<a class="fas fa-search btn btn-info"></a>
	<%-- 										href="${s:mvcUrl('AC#detalheAtendimento').arg(0,atendimento.id).build()}" --%>
										</td>
										<td class="text-center">
											<a class="fas fa-undo btn btn-danger" type="button"
											data-toggle="modal" data-target="#modalEstornar"></a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="back-to-top" href="#">
				<i class="fas fa-chevron-up"></i>
			</div>
			
				<!-- Modais -->
				<div class="modal modal fade" id="modalEstornar" aria-labelledby="exampleModalLabel" aria-hidden="true" tabindex="-1">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title">Lancamento</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        <p>
				        	Deseja realmente lançar estornar esse lançamento dos relatórios financeiros?
							Ao confirmar este lancamento deixara de existir, e o atendimento será devolvido
							para o status de "NÃO ATENDIDO".
				        </p>
				      </div>
				      <div class="modal-footer">
<%-- 				         action="${s:mvcUrl('AC#marcarAtendimento').arg(0,atendimento.id).build()}" --%>
				      <form method="post" name="formLancamento">
				      		<input hidden id="atendimentoId" name="idAtendimento" value="${atendimento.id}" />
					        <button type="submit" id="confirmarLancamento" class="btn btn-color-salon" data-dismiss="modal">
					        	Sim
					        </button>
				      </form>
				        <button type="button" class="btn btn-color-salon" data-dismiss="modal">
				       	 	Não
				        </button>
				      </div>
				    </div>
				  </div>
				</div>

		</section>
	</main>

</tags:pageTemplate>