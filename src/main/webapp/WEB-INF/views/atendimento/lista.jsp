<%@ page contentType="text/html; charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<tags:pageTemplate titulo="Lista Atendimentos">
	    <main>
        <section>
            <div class="container">
                <h4 class="mt-4 color-font">Atendimentos Marcado</h4>
                <hr />
                <div class="row">
                    <div class="col-xs-3 ml-3">
                        <label for="rbFilterCliente">Filtrar por cliente</label>
                    </div>
                    <div class="col-xs-3 ml-4">
                        <input class="form-check-input filterCliente" type="radio" name="exampleRadios"
                            id="rbFilterCliente" value="option1" checked>
                    </div>

                    <div class="col-xs-3 ml-4">
                        <label for="rbFilterData">Filtrar por data</label>
                    </div>
                    <div class="col-xs-3 ml-4">
                        <input class="form-check-input" type="radio" name="exampleRadios" id="rbFilterData"
                            value="option1">
                    </div>
                </div>
                <div class="form-group mt-4" id="groupFilterByClient">
                    <label class="color-font-label" for="cliente-atendimento-filter">Filtrar Atendimento por
                        cliente</label>
                    <input type="text" onkeyup="filtrar()" class="form-control" id="cliente-atendimento-filter">
                    <button type="button" onclick="atualizarPagina()" class="btn btn-color-salon btn-sm mt-3 fas fa-sync-alt">
                    </button>
                    
                </div>

                <div hidden id="groupFilterByData" class="mt-3">
	                <form name="formFiltro">
	                    <div class="form-group">
	                        <label class="color-font-label" for="data-inicio">Data Ínicio</label>
	                        <input type="date" name="dataInicio" class="form-control" id="data-inicio">
	                    </div>
	                    <div class="form-group">
	                        <label class="color-font-label" for="data-fim">Data Fim</label>
	                        <input type="date" name="dataFim" class="form-control" id="data-fim">
	                    	<button type="submit" class="btn btn-color-salon btn-sm">Filtrar</button>
	                    </div>
	                </form>
                </div>
                
	            <table id="atendimento-table" class="table mt-5">
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

				<c:if test="${lista.size()==0}">
					<tr>
						<p>
							<i>Nenhum atendimento agendado</i>
						</p>
					</tr>
				</c:if>

				<c:forEach items="${lista}" var="atendimento">
					<tr>
						<td>${atendimento.cliente.nome}</td>
						<td> 
							${atendimento.data}
						</td>
						<td> 
							${atendimento.hora}
						</td>
						<td class="text-center">
							<a type="button" class="fas fa-search btn btn-info" 
								onclick="detalharCliente(${atendimento.id});"></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
	            </table>
            </div>

            <div class="back-to-top" href="#"><i class="fas fa-chevron-up"></i></div>

        </section>
    </main>
    <script src="../resources/js/atendimento/lista.js"></script>
</tags:pageTemplate>
