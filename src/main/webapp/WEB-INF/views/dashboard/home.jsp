<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tags:pageTemplate titulo="Home">
	
	<main>
		<section>
			<div class="container" style="margin-top:100px;">
                <div class="pb-4">
                    <h4 class="text-center"><i class="fas fa-clock"></i> Hoje ${dataAtual}</h4>
                </div>

                <h3 class="color-font mt-4">Atendimentos</h3>
                <hr />
                
                <c:choose>
                	<c:when test="${atendimentos.size() == 0}">
                		<p class="text-center mt-4">Nenhum atendimento marcado para hoje.</p>
                	</c:when>
                	
                	<c:otherwise>
						<c:forEach items="${atendimentos}" var="atendimento">
			                <div class="card bg-light mt-4 mb-3" style="align-content: center;" style="max-width: 18rem;">
			                    <div class="card-header"><b>Código Atendimento</b>: ${atendimento.id}</div>
			                    <div class="card-body">
			                        <ul>
			                    		<li>
			                        		<b>Cliente</b>:
			                        		<p>${atendimento.cliente.nome}</p>
			                        	</li>
			                        	<li>
			                        		<b>Bairro</b>:
			                        		<p>${atendimento.endereco.bairro}</p>
			                        	</li>
			                            <li>
			                                <b>Hora</b>:
			                                	<fmt:formatDate pattern="HH:mm" value="${atendimento.hora}" />
			                            </li>
			                        </ul>
			
			                        <a id="btnCadastrar" style="margin-bottom: 16px; width:120px;" 
			                        	type="button" class="btn btn-color-salon btn-block"
			                        	href="${s:mvcUrl('AC#buscarPaginaAtendimentoPorId')
			                        			.arg(0,atendimento.id).build()}">
			                        			
			                        		Detalhes
			                        </a>
			                    </div>
			
			                </div>
			            </c:forEach>
                	</c:otherwise>
                </c:choose>
            </div>

            <div class="back-to-top" href="#"><i class="fas fa-chevron-up"></i></div>
		</section>
	</main>
	
</tags:pageTemplate>