<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Relatório Lançamento">

	<main>
		<section>
			<div class="container container-space-default">
				<h4 class="color-font">Relatório Lançamento</h4>
				<hr />
				
				<input hidden id="idLancamento" />

				<div id="groupFilterByData">
				<form:form name="formFiltroRelatorio">
					<div class="form-group">
						<label class="color-font-label" for="data-inicio">Data Ínicio</label> 
						<input type="date" name="dataInicio" class="form-control" id="data-inicio">
						<small hidden class="form-text obrigatorio dataInicio"></small>
					</div>
					<div class="form-group">
						<label class="color-font-label" for="data-fim">Data Fim</label> 
						<input type="date" name="dataFim" class="form-control" id="data-fim">
						<small hidden class="form-text obrigatorio dataFim"></small>
					</div>
					<div class="row">
						<div class="col-6 col-md-6">
							<button id="btnCadastrar"style="margin-bottom: 5px; width: 120px;"
								 type="submit" class="btn btn-color-salon btn-block">
								<i class="fas fa-filter"></i> Filtrar </button>
						</div>
					</div>
				</form:form>
				</div>
				<div id="msg-nenhum-registro-encontrado" class="mt-4" hidden>
					<p>Nenhum lançamento neste período</p>
				</div>
				<div style="margin-bottom: 100px; margin-top: 10px;">
					<div class="mt-5">
						<div class="text-right alert alert-success" role="alert">
							<b>Faturamento: R$ 
								<p id="valor" style="display: inline;">
									<fmt:formatNumber type="currency" pattern="#,##0.00"
										value="${totalPeriodo}" />
								</p>
							</b>
						</div>
						<table id="lancamento-table" class="table table-striped mt-3">
							<thead class="text-center">
								<tr>
									<th scope="col" class="text-left">Valor</th>
									<th scope="col">Data</th>
									<th class="text-center" scope="col">Atendimento</th>
									<th class="text-center" scope="col">Estornar</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${lancamentos}" var="lancamento">
									<tr id="${lancamento.id}">
										<td>R$
										<fmt:formatNumber type="currency" pattern="#,##0.00"
											value="${lancamento.valorTotal}" />
										</td>
										<td class="text-center">
											<fmt:formatDate pattern="dd/MM/yy" 
											value="${lancamento.data.getTime()}"/>
										</td>
									
										<td class="text-center">
										<a class="fas fa-search btn btn-info loader"
										href="${s:mvcUrl('AC#detalheAtendimento').arg(0,lancamento.atendimento.id).build()}">
										</a>
											
										</td>
										<td class="text-center">
											<a class="fas fa-undo btn btn-danger" type="button"
											data-toggle="modal" data-target="#modalEstornar"
											onclick="atualizarIds(${lancamento.id})"></a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
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
					        <button type="button" id="confirmarLancamento" 
					        	class="btn btn-color-salon" data-dismiss="modal"
					        	onclick="estornarLancamento()">
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
	
    <script type="text/javascript" charset="UTF-8" src="../resources/js/lancamento/relatorio.js"></script>

</tags:pageTemplate>