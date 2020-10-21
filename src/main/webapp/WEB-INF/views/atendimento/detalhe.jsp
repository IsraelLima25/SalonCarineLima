<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<tags:pageTemplate titulo="Detalhe Atendimento">

 <main>
        <section>
            <div class="container">
                <h4 class="mt-4 color-font">Atendimento</h4>
                <hr />
                <div class="row">
                	<div class="col-6 col-md-6 text-left">
                		<a	class="btn btn-success fas fa-arrow-left" 
	                	href="/SalonCarineLima/atendimento/previous?isFirst=${page.isLast()}&number=${page.getNumber()}"></a>
                	</div>
                	<div class="col-6 col-md-6 text-right">
	                	<a	class="btn btn-success fas fa-arrow-right" 
	                	href="/SalonCarineLima/atendimento/next?isLast=${page.isLast()}&number=${page.getNumber()}"></a>
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
 					<input disabled="disabled" class="form-control" 
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
							href="/SalonCarineLima/cliente/${atendimento.cliente.id}">
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
                            <td class="text-center">${itemAtendimento.getTotal(itemAtendimento.quantidade)}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
           		<hr/>
           		<div class="row text-left">
	                <div class="col-4 col-md-4 mt-3">
	                    <label style="display:block;">Taxa</label>
	                    <c:choose>
	                    	<c:when test="${atendimento.taxa != null}">
	                    		<input disabled="disabled" class="form-control" 
                    			value=" ${atendimento.taxa}" />
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
	                    		<input disabled="disabled" class="form-control" 
                    			value="${atendimento.desconto}" />
	                    	</c:when>
	                    	<c:otherwise>
	                    		<input disabled="disabled" class="form-control" 
                    			value="0,00" />
	                    	</c:otherwise>
	                    </c:choose>
	                </div>
	                
	                <div class="col-4 col-md-4 mt-3">
	                    <label style="display:block;">Total</label>
	                    <input disabled="disabled" class="form-control" 
                    			value="${atendimento.valorTotal}" />
	                </div>
	                
           		</div>
           		
           		<hr/>
           		
           		<div class="row">
           			<div class="col-4 col-md-4 text-left mt-3">
	           			<label style="display:block;">Data</label>
	           			<fmt:formatDate var="fmtDate" value="${atendimento.data.getTime()}" pattern="dd/MM/yyyy"/>
		           			<input disabled="disabled" class="form-control" style="font-size: 13px;" 
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
				<div class="row">
					<c:if test="${atendimento.pagamento.getClass().getSimpleName() == 'Debito' ||
								 atendimento.pagamento.getClass().getSimpleName() == 'Credito' }">
						<div class="col-4 col-md-4 text-left mt-3">		 
							<label style="display:block;">Bandeira</label>
							<input disabled="disabled" class="form-control" 
                    			value="${atendimento.pagamento.bandeiraCartao}" />
						</div>		 
					</c:if>
					
					<c:if test="${atendimento.pagamento.getClass().getSimpleName() == 'Credito'}">
						<div class="col-4 col-md-4 text-center mt-3">		 
							<label style="display:block;">Quantidade Parcelas</label>
							<input disabled="disabled" class="form-control" 
                    			value="${atendimento.pagamento.quantidadeParcelas}" />
	                    </div>
					</c:if>
				</div>
				
				<hr/>
				
				<div class="row">
					<div class="col-12 col-md-12 mt-3 mb-5 text-left">
						<button id="btn-confirmar-lancamento" 
							class="btn btn-success">
							Lançar
						</button>
						<button class="btn btn-danger ml-3">
							Cancelar
						</button>
					</div>
				</div>
				
				
<!-- 				<div class="text-center alinhamento"> -->
<!--                     <button id="btn-confirmar-lancamento" style="margin-bottom: 16px;" data-toggle="modal" -->
<!--                         data-target="#modalConfirmarPagamento" type="button" -->
<!--                         class="btn btn-color-salon btn-block">Lançar Pagamento</button> -->
<!--                 </div> -->
				
                <div class="back-to-top" href="#"><i class="fas fa-chevron-up"></i></div>
  			</div>
        </section>
    </main>	
    <script type="text/javascript" charset="UTF-8" src="../resources/js/atendimento/detalhe.js"></script>
</tags:pageTemplate>
