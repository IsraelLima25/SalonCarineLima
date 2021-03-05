<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<tags:pageTemplate titulo="Detalhe Atendimento">

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

 <main>
 		<span id="contextPage" hidden> ${contextPath}</span>
        <section>
            <div class="container container-space-default">
            	<input id="rowNumberAtual" hidden value="${page.getNumber()}" />
            	<div class="row">
            		<div class="text-left col-8 col-md-8">
		                <h4 class="color-font">Atendimento</h4>
            		</div>
		            <div class="text-right col-4 col-md-4">
						<a type="button" href="../atendimento/listar" 
							class="btn btn-info fas fa-fast-backward loader">
							voltar
						</a>
					</div>
            	</div>
                <hr />
                
                <div class="row">
                	<div class="col-6 col-md-6 text-left">
                		<a	class="btn btn-success fas fa-chevron-left" 
	                	href="${contextPath}/atendimento/previous?idAtendimentoAtual=${atendimento.id}"></a>
                	</div>
                	<div class="col-6 col-md-6 text-right">
	                	<a	class="btn btn-success fas fa-chevron-right" 
	                	href="${contextPath}/atendimento/next?idAtendimentoAtual=${atendimento.id}"></a>
                	</div>
                </div>
 				<div class="row">
 				<div class="col-6 col-md-6 mt-3">
                    <label>Código</label>
                    <input disabled="disabled" style="width: 50px;" class="form-control text-center" 
                    value="${atendimento.id}" />
 				</div>
 				
 				<div class="col-6 col-md-6 mt-3">
 					<label>Status</label>
 					<input id="status" disabled="disabled" class="form-control" 
                    value="${atendimento.status.descricao}" />
 				</div>
 				
 				<div class="col-2 col-md-2">
 					
 				</div>
                </div>
                <hr/>
	           
                <div class="row mt-3">
                	<div class="col-12 col-md-12">
	                    <label>Cliente</label>
                    		<a class="fas fa-search ml-2"
							href="${contextPath}/cliente/${atendimento.cliente.id}">
							</a>
	                    <input disabled="disabled" class="form-control" 
                    	value="${atendimento.cliente.nome}" />
	                </div>
	                <div class="col-12 col-md-12 mt-3">
	                      <label style="display:block;" class="color-font-label" for="data">
	                       		Ponto Referencia
	                      </label>
                		<c:choose>
                			<c:when test="${atendimento.endereco.pontoReferencia != ''}">
                				<input disabled="disabled" class="form-control" 
                    				value="${atendimento.endereco.pontoReferencia}" />
                			</c:when>
                			<c:otherwise>
                					<input disabled="disabled" class="form-control" 
                    				value="-" />
                			</c:otherwise>
                		</c:choose>
	                </div>
                </div>
                
                <div class="row mt-3">
                	<div class="col-4 col-md-4">
		                <label class="color-font-label" for="data">Endereço</label>
		                <input disabled="disabled" class="form-control" 
	                    value="${atendimento.tipoEndereco.descricao}" />
                	</div>
                	<div class="col-4 col-md-4">
	                        <label class="color-font-label" for="data">
	                        	Cep
	                        </label>
	                        <c:choose>
	                        	<c:when test="${atendimento.endereco.cep != ''}">
	                        	<input disabled="disabled" class="form-control" 
                    			value="${atendimento.endereco.cep}" />
	                        	</c:when>
	                        	<c:otherwise>
	                        	<input disabled="disabled" class="form-control" 
                    			value="-" />
	                        	</c:otherwise>
	                        </c:choose>
	                </div>
	                
	               	<div class="col-4 col-md-4">
	                      <label class="color-font-label" for="data">
	                        	Bairro
	                      </label>
	                      <input disabled="disabled" class="form-control" 
                    	  value="${atendimento.endereco.bairro}" />
	                </div>
                </div>
                
                <c:if test="${atendimento.tipoEndereco.descricao == 'outro'}">
				
	                <div id="outroEndereco">
		                <div class="row">
		                	<div class="col-12 col-md-12 mt-3">
		                        <label style="display:block;" class="color-font-label" for="data">
		                        	Logradouro
		                        </label>
		                        <c:choose>
		                        	<c:when test="${atendimento.endereco.logradouro != ''}">
		                       		<input disabled="disabled" class="form-control" 
	                    			value="${atendimento.endereco.logradouro}" />
		                        	</c:when>
		                        	<c:otherwise>
		                      		<input disabled="disabled" class="form-control" 
	                    			value="-" />
		                        	</c:otherwise>
		                        </c:choose>
		                	</div>
		                </div>
		                <div class="row">
			                <div class="col-8 col-md-8 mt-3">
			                        <label style="display:block;" class="color-font-label" for="data">
			                        	Complemento
			                        </label>
			                        <c:choose>
			                        	<c:when test="${atendimento.endereco.complemento != ''}">
			                        		<input disabled="disabled" class="form-control" 
		                    				value="${atendimento.endereco.complemento}" />
			                        	</c:when>
			                        	<c:otherwise>
			                        		<input disabled="disabled" class="form-control" 
		                    				value="-" />
			                        	</c:otherwise>
			                        </c:choose>
			                </div>
			                <div class="col-4 col-md-4 mt-3">
			                   	<label style="display:block;" class="color-font-label" for="data">
			                   		Número
			                   	</label>
		                		<c:choose>
		                			<c:when test="${atendimento.endereco.numero != null}">
			                			<input disabled="disabled" class="form-control" 
		                    			value="${atendimento.endereco.numero}" />
		                			</c:when>
		                			<c:otherwise>
		                				<input disabled="disabled" class="form-control" 
	                    				value="-" />
		                			</c:otherwise>
		                		</c:choose>
			                </div>
		                </div>
	                </div>
				</c:if>
                <hr/>
 				<h5 class="color-font mt-4">Cesta de Serviços</h5>
 				
                <table id="cesta-atendimento-table" class="table">
                    <thead>
                        <tr>
                            <th class="text-left" scope="col">Serviço</th>
                            <th class="text-center" scope="col">Quantidade</th>
                            <th class="text-center" scope="col">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${atendimento.itens}" var="itemAtendimento">
                        <tr>
                            <td>${itemAtendimento.servico.descricao}</td>
                            <td class="text-center">${itemAtendimento.quantidade}</td>
                            <td class="text-center">
                            	<fmt:formatNumber type="currency" pattern="#,##0.00"
                            	 value="${itemAtendimento.getTotal(itemAtendimento.quantidade)}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                    	<td colspan="2" class="text-left"><b>Total cesta</b></td>
                    	<td style="color: green" class="text-center">
                    		<fmt:formatNumber type="currency" pattern="#,##0.00" 
                    			value="${atendimento.totalBruto}" />
                    	</td>
                    </tr>
                    
                    </tbody>
                </table>
           		<hr/>
           		<div class="row text-left">
	                <div class="col-4 col-md-4 mt-3">
	                    <label style="display:block;">Taxa</label>
	                    <c:choose>
	                    	<c:when test="${atendimento.taxa != null}">
	                    	<fmt:formatNumber var="fmtTaxa" value="${atendimento.taxa}" 
	                    		pattern="#,##0.00"/>
	                    		<input disabled="disabled" class="form-control" 
                    			value="${fmtTaxa}" />
	                    	</c:when>
	                    	<c:otherwise>
	                    		<input disabled="disabled" class="form-control" 
                    			value="0,00" />
	                    	</c:otherwise>
	                    </c:choose>
	                </div>
	                
	                <div class="col-4 col-md-4 mt-3">
	                    <label style="display:block;">Desconto</label>
	                    <c:choose>
	                    	<c:when test="${atendimento.desconto != null && atendimento.desconto != 0 }">
	                    		<fmt:formatNumber var="fmtDesconto" value="${atendimento.desconto}"
	                    		pattern="#,##0.00"/>	
	                    		<input disabled="disabled" class="form-control" 
                    			value="${fmtDesconto}" />
	                    	</c:when>
	                    	<c:otherwise>
	                    		<input disabled="disabled" class="form-control" 
                    			value="0,00" />
	                    	</c:otherwise>
	                    </c:choose>
	                </div>
	                
	                <div class="col-4 col-md-4 mt-3">
	                    <label style="display:block;">Total</label>
	                    <fmt:formatNumber var="fmtTotal" value="${atendimento.totalLiquido}"
	                    		pattern="#,##0.00"/>
	                    <input disabled="disabled" class="form-control" 
                    			value="${fmtTotal}" />
	                </div>
	                
           		</div>
           		
           		<hr/>
           		
           		<div class="row">
           			<div class="col-4 col-md-4 text-left mt-3">
	           			<label style="display:block;">Data</label>
	           			<fmt:formatDate var="fmtDate" value="${atendimento.data.getTime()}" pattern="dd/MM/yy"/>
		           			<input disabled="disabled" class="form-control" 
		           				value="${fmtDate}" />
           			</div>
           			<div class="col-4 col-md-4 mt-3">
	                    <label style="display:block;">Hora</label>
	                    <fmt:formatDate var="fmtHora"  value="${atendimento.hora}" pattern="HH:mm" />
	                    <input disabled="disabled" class="form-control" 
                    			value="${fmtHora}" />
           			</div>
           			<div class="col-4 col-md-4  mt-3">
	                    <label style="display:block;">Pagamento</label>
	                     <input disabled="disabled" class="form-control" 
                    			value="${atendimento.pagamento.getClass().getSimpleName()}" />
           			</div>
           		</div>
				<hr/>
				<div class="row mb-5">
					<c:if test="${atendimento.pagamento.getClass().getSimpleName() == 'Debito' ||
								 atendimento.pagamento.getClass().getSimpleName() == 'Credito' }">
						<div class="col-4 col-md-4 text-left mt-3">		 
							<label style="display:block;">Bandeira</label>
							<input disabled="disabled" class="form-control" 
                    			value="${atendimento.pagamento.bandeiraCartao}" />
						</div>		 
					</c:if>
					
					<c:if test="${atendimento.pagamento.getClass().getSimpleName() == 'Credito'}">
						<div class="col-4 col-md-4 mt-3">		 
							<label style="display:block;">Parcelas</label>
							<input disabled="disabled" class="form-control" 
                    			value="${atendimento.pagamento.quantidadeParcelas}" />
	                    </div>
					</c:if>
				</div>
				
				<hr/>
				
				<c:if test="${atendimento.status.descricao == 'Pendente'}">
					<div class="row">
						<div class="col-12 col-md-12 mt-3 text-left" style="margin-bottom: 100px;">
							<button id="btn-confirmar-lancamento" data-toggle="modal"
								data-target="#modalLancar" 
								class="btn btn-success">
								Lançar
							</button>
							<button class="btn btn-danger ml-3" data-toggle="modal"
								data-target="#modalCancelar">
								Cancelar
							</button>
						</div>
					</div>
				</c:if>	
				
			<!-- Modais -->
				<div class="modal modal fade" id="modalLancar" aria-labelledby="exampleModalLabel" aria-hidden="true" tabindex="-1">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title">Atendimento</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        <p>
				        	Deseja realmente lançar esse atendimento nos relatórios financeiros?
							Lembrando que os lançamentos estão programados para acontecerem ás
							00:00 do dia seguinte.
				        </p>
				      </div>
				      <div class="modal-footer">
<%-- 				         action="${s:mvcUrl('AC#marcarAtendimento').arg(0,atendimento.id).build()}" --%>
				      <form:form method="POST" name="formLancamento">
				      		<input hidden id="atendimentoId" name="idAtendimento" value="${atendimento.id}" />
					        <button type="submit" id="confirmarLancamento" 
					        	class="btn btn-color-salon" data-dismiss="modal">
					        	Sim
					        </button>
				      </form:form>
				        <button type="button" class="btn btn-color-salon" data-dismiss="modal">
				       	 Não
				        </button>
				      </div>
				    </div>
				  </div>
				</div>
				
				<div class="modal modal fade" id="modalSucesso" aria-labelledby="exampleModalLabel" aria-hidden="true" tabindex="-1">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title">Atendimento</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        <p>
				        	Atendimento Lançado com sucesso.
				        </p>
				      </div>
				    </div>
				  </div>
				</div>
				
			<div class="modal modal fade" id="modalCancelar" aria-labelledby="exampleModalLabel" aria-hidden="true" tabindex="-1">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title">Atendimento</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        <p>
				        	Deseja realmente cancelar esse atendimento?
							Após a confirmação este atendimento será excluido.
				        </p>
				      </div>
				      <div class="modal-footer">
<%-- 				         action="${s:mvcUrl('AC#marcarAtendimento').arg(0,atendimento.id).build()}" --%>
				      <form:form method="post" name="formCancelamento">
				      		<input hidden id="atendimentoIdCancelamento" name="idAtendimentoCancelado" value="${atendimento.id}" />
					        <button type="submit" id="cancelarAtendimento"
					        	 class="btn btn-color-salon" data-dismiss="modal">
					        	Sim
					        </button>
				      </form:form>
					        <button type="button" class="btn btn-color-salon" data-dismiss="modal">
					       		Não
					        </button>
				      </div>
				    </div>
				  </div>
			</div>
			
			<div class="modal modal fade" id="modalCancelado" aria-labelledby="exampleModalLabel" aria-hidden="true" tabindex="-1">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title">Atendimento</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        <p>
				        	Atendimento cancelado com sucesso.
				        </p>
				      </div>
				    </div>
				  </div>
			</div>
				
  			</div>
        </section>
        
    </main>	
    <script type="text/javascript" charset="UTF-8" src="../resources/js/atendimento/detalhe.js"></script>
</tags:pageTemplate>
