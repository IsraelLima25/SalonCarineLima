<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<tags:pageTemplate titulo="Lista Atendimentos">
	<main>
		<section>
			<div class="container">
			  <p hidden id="paginaAtual">${paginas.pageable.pageNumber}</p>
				<h4 class="mt-2 color-font">Atendimentos Marcado</h4>
				<hr />
				<div class="row">
					<div class="col-xs-3 ml-3">
						<label for="rbFilterCliente">Filtrar por cliente</label>
					</div>
					<div class="col-xs-3 ml-4">
						<input class="form-check-input filterCliente" type="radio"
							name="exampleRadios" id="rbFilterCliente" value="option1" checked>
					</div>

					<div class="col-xs-3 ml-4">
						<label for="rbFilterData">Filtrar por data</label>
					</div>
					<div class="col-xs-3 ml-4">
						<input class="form-check-input" type="radio" name="exampleRadios"
							id="rbFilterData" value="option1">
					</div>
				</div>	
				
				<div class="form-group mt-4" id="groupFilterByClient">
					
					<label class="color-font-label" for="cliente-atendimento-filter">Filtrar
						Atendimento por cliente
					</label> 
								
					<input type="text" id="cliente-atendimento-filter" class="form-control" />
					
				</div>		

				<div hidden id="groupFilterByData" class="mt-3">
					<form name="formFiltro">
						<div class="form-group">
							<label class="color-font-label" for="data-inicio">
								Data Ínicio
							</label> 
							<input type="date" name="dataInicio" class="form-control" id="data-inicio">
							<small hidden class="form-text obrigatorio dataInicio"></small>
						</div>
						<div class="form-group">
							<label class="color-font-label" for="data-fim">Data Fim</label> <input
								type="date" name="dataFim" class="form-control" id="data-fim">
								<small hidden class="form-text obrigatorio dataFim"></small>
							<button type="submit" class="btn btn-color-salon btn-sm">Filtrar</button>
						</div>
					</form>
				</div>
				<table id="atendimento-table" class="table mt-2">
					<thead>
						<tr>
							<th scope="col">Cliente</th>
							<th scope="col">Data</th>
							<th scope="col">Hora</th>
							<th class="text-center" scope="col">Detalhe</th>
						</tr>
					</thead>
					<tbody>

						<p id="msg-nenhum-registro-encontrado" hidden>
							<i>Nenhum atendimento encontrado</i>
						</p>
		
						<c:if test="${paginas.totalPages== '0'}">
							<tr>
								<p>
									<i>Nenhum atendimento agendado</i>
								</p>
							</tr>
						</c:if>

						<c:forEach items="${paginas.content}" var="atendimento">
							<tr>
								<td>${atendimento.cliente.nome}</td>
								<td> 
									<fmt:formatDate pattern="dd/MM/yyyy" value="${atendimento.data.getTime()}"/>
								</td>
								<td>
									<fmt:formatDate pattern="HH:mm" value="${atendimento.hora}" />
								</td>
								<td class="text-center"><a
									class="fas fa-search btn btn-info"
									href="${s:mvcUrl('AC#detalheAtendimento').arg(0,atendimento.rowNumber).build()}"></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<!-- Paginação -->
				<c:if test="${paginas.totalPages > 0 }">
					<div class="row pb-5">
						<nav aria-label="..." style="margin: auto;">
							  <ul class="pagination">
							  <li class="page-item">
							    
								  <p hidden id="paginaAtual">${paginas.pageable.pageNumber}</p>
								  
							      <a class="page-link" type="button"
							      	onclick="paginaAnterior(${paginas.totalPages})">
							      		Anterior
							      </a>
							    </li>
							  <c:forEach var="pageNumber" begin="0" end="${paginas.totalPages - 1}" 
							  			varStatus="value">
						    	<c:choose>
						    		<c:when test="${pageNumber == 0}">
								    	<li id="${pageNumber}" class="page-item active">
									      <a class="page-link" type="button" 
									      	onclick="getPage(${pageNumber})" >${pageNumber + 1}</a>
									    </li>
						    		</c:when>
						    		<c:otherwise>
						    			<li id="${pageNumber}" class="page-item">
									      <a class="page-link" type="button"
									      	onclick="getPage(${pageNumber})">${pageNumber + 1}</a>
									    </li>
						    		</c:otherwise>
						    	</c:choose>
							  </c:forEach>
							    <li class="page-item">
							      <a class="page-link" type="button"
							      	onclick="proximaPagina(${paginas.totalPages})">
							      		Próximo
							      </a>
							    </li>
							  </ul>
						</nav>
					</div>
				</c:if>
			</div>
			
			<div class="back-to-top" href="#">
				<i class="fas fa-chevron-up"></i>
			</div>

		</section>
	</main>
	<script src="../resources/js/atendimento/lista.js"></script>
</tags:pageTemplate>
