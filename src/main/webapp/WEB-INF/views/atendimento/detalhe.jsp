<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Detalhe Atendimento">

 <main>
        <section>
            <div class="container">
                <h4 class="mt-4 color-font">Dados Atendimento</h4>
                <hr />
 
                <div class="form-group">
                    <label style="display:block;">Código Atendimento</label>
                    <label><b>${atendimento.id}</b></label>
                </div>
                <div class="form-group">
                    <label style="display:block;">Status Atendimento</label>
                    <label id="status"><b>${atendimento.status.descricao}</b></label>
                </div>
                <div class="form-group">
                    <label style="display:block;">Cliente</label>
                    <label><b>${atendimento.cliente.nome}</b></label>
                </div>
                
 				<h5 class="color-font">Serviços Prestados</h5>
 				
                <table id="cesta-atendimento-table" class="table mt-4">
                    <thead>
                        <tr>
                            <th class="text-center" scope="col">Serviço</th>
                            <th class="text-center" scope="col">Quantidade</th>
                            <th class="text-center" scope="col">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${atendimento.itens}" var="itemAtendimento">
                        <tr>
                            <td>${itemAtendimento.servicoDTO.descricao}</td>
                            <td class="text-center">${itemAtendimento.quantidade}</td>
                            <td class="text-center">${itemAtendimento.precoTotal}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
           
                <div class="form-group">
                    <label style="display:block;">Valor Total</label>
                    <label style="display: inline;"><b>R$ ${atendimento.valorTotal}</b></label>
                </div>

                <div class="form-group">
                    <label style="display:block;">Desconto</label>
                    <label><b>R$ ${atendimento.desconto}</b></label>
                </div>

                <div class="form-group">
                    <label style="display:block;">Data</label>
                    <label><b>${atendimento.data}</b></label>
                </div>

                <div class="form-group">
                    <label style="display:block;">Hora</label>
                    <label><b>${atendimento.hora}</b></label>
                </div>

                <div class="form-group">
                    <label style="display:block;">Forma Pagamento</label>
                    <label><b>${atendimento.pagamento.getClass().getSimpleName()}</b></label>
                </div>
				
				<c:if test="${atendimento.pagamento.getClass().getSimpleName() == 'Debito' ||
							 atendimento.pagamento.getClass().getSimpleName() == 'Credito' }">
							 
					<label style="display:block;">Bandeira Cartão</label>
                    <label><b>${atendimento.pagamento.bandeiraCartao}</b></label>
							 
				</c:if>
				
				<c:if test="${atendimento.pagamento.getClass().getSimpleName() == 'Credito'}">
							 
					<label style="display:block;">Quantidade Parcelas</label>
                    <label><b>${atendimento.pagamento.quantidadeParcelas}</b></label>
							 
				</c:if>

                <label style="display:block;" class="color-font-label" for="data">Endereço</label>
                <label><b>${atendimento.tipoEndereco.descricao}</b></label>
				
				<c:if test="${atendimento.tipoEndereco.descricao == 'outro-endereco'}">
				
	                <div id="outroEndereco">
	                    <div class="form-group mt-3">
	                        <label style="display:block;" class="color-font-label" for="data">Cep</label>
                			<label><b>${atendimento.endereco.cep}</b></label>
	                    </div>
	                    <div class="form-group">
	                        <label style="display:block;" class="color-font-label" for="data">Logradouro</label>
                			<label><b>${atendimento.endereco.logradouro}</b></label>
	                    </div>
	                    <div class="form-group">
	                       	<label style="display:block;" class="color-font-label" for="data">Numero</label>
                			<label><b>${atendimento.endereco.numero}</b></label>
	                    </div>
	                    <div class="form-group">
	                       	<label style="display:block;" class="color-font-label" for="data">Ponto Referencia</label>
                			<label><b>${atendimento.endereco.pontoReferencia}</b></label>
	                    </div>
	                    <div class="form-group">
	                        <label style="display:block;" class="color-font-label" for="data">Complemento</label>
                			<label><b>${atendimento.endereco.complemento}</b></label>
	                    </div>
	                    <div class="form-group">
	                        <label style="display:block;" class="color-font-label" for="data">Bairro</label>
                			<label><b>${atendimento.endereco.bairro}</b></label>
	                    </div>
	                </div>
				</c:if>

                <div class="text-center alinhamento">
                    <button id="btn-confirmar-lancamento" style="margin-bottom: 16px;" data-toggle="modal"
                        data-target="#modalConfirmarPagamento" type="button"
                        class="btn btn-color-salon btn-block">Lançar Pagamento</button>
                </div>
                <div class="back-to-top" href="#"><i class="fas fa-chevron-up"></i></div>
  			</div>
        </section>
    </main>	
    <script type="text/javascript" charset="UTF-8" src="../resources/js/atendimento/detalhe.js"></script>
</tags:pageTemplate>
