<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<tags:pageTemplate titulo="Lista Serviços">

	<div class="container">
		<h4 class="mt-4 color-font">Lista de Servicos</h4>
		<hr />

		<div class="form-group">
			<label class="mt-3 color-font-label" for="nome-filter">
				Filtrar por descricao
			</label> 
			<input type="text"class="form-control" id="servico-filter">
		</div>

		<table id="servico-table" class="mt-5 table table-striped">
			<thead>
				<tr>
					<th scope="col">Descricao</th>
					<th scope="col">Preço</th>
					<th class="text-center" scope="col">Detalhe</th>
				</tr>
			</thead>
			<tbody>

				<tr id="msg-nenhum-registro-encontrado" hidden>
					<td>
						<p>
							<i>Nenhum serviço encontrado</i>
						</p>
					</td>
				</tr>

				<c:if test="${paginas.content.size()==0}">
					<tr>
						<p>
							<i>Nenhum serviço cadastrado</i>
						</p>
					</tr>
				</c:if>

				<c:forEach items="${paginas.content}" var="servico">
					<tr>
						<td>${servico.descricao}</td>
						<td>${servico.preco}</td>
						<td class="text-center">
							<a class="fas fa-search btn btn-info" 
							href="${s:mvcUrl('SC#detalheServico').arg(0,servico.id).build()}"></a>
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


	<script src="../resources/js/servico/catalogo.js"></script>

</tags:pageTemplate>
