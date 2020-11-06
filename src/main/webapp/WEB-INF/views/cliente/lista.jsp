<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<tags:pageTemplate titulo="Lista Cliente">

	<div class="container container-space-default">
		<h4 class="mt-4 color-font">Lista de Clientes</h4>
		<hr />

		<div class="form-group">
			<label class="color-font-label" for="nome-filter">Filtrar por nome</label> 
			<input type="text" class="form-control" id="nome-filter">
			<!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
		</div>

		<table id="cliente-table" class="mt-5 table table-striped">
			<thead>
				<tr>
					<th scope="col">Código</th>
					<th scope="col">Nome</th>
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

				<c:if test="${paginas.content.size()==0}">
					<tr>
						<p>
							<i>Nenhum cliente cadastrado</i>
						</p>
					</tr>
				</c:if>

				<c:forEach items="${paginas.content}" var="cliente">
					<tr>
						<td>${cliente.id}</td>
						<td> 
							${cliente.nome}
						</td>
						<td class="text-center"><a
							class="fas fa-search btn btn-info loader"
							href="${s:mvcUrl('detalharCliente').arg(0,cliente.id).build()}"></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- Paginação -->
		<c:if test="${paginas.totalPages > 0 }">
				<div class="row pb-5">
						<nav aria-label="..." style="margin: auto;">
							  <ul class="pagination flex-wrap ">
							  <li class="page-item">
								  <p hidden id="paginaAtual">${paginas.pageable.pageNumber}</p>
								  
							      <a class="page-link fas fa-chevron-left" type="button"
							      	onclick="paginaAnterior(${paginas.totalPages})">
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
							      <a class="page-link fas fa-chevron-right" type="button"
							      	onclick="proximaPagina(${paginas.totalPages})">
							      </a>
							    </li>
							  </ul>
						</nav>
					</div>
				</c:if>
	</div>
	
	<script src="../resources/js/cliente/catalogo.js"></script>

</tags:pageTemplate>


