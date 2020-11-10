
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulo" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport" charset="UTF-8"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>${titulo}-SalãoCarine</title>

<c:url value="/resources" var="rsc" />

<link rel="stylesheet" href="${rsc}/bootstrap-4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" href="${rsc}/css/style.css" />

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

<script src="${rsc}/jquery-3.5.1/jquery-3.5.1.js"></script>
<script src="${rsc}/jquery-3.5.1/jquery.mask.min.js"></script>

</head>

<body>
	<%@ include file="/WEB-INF/views/header.jsp"%>

	<!-- Conteudo da pagina -->
	
	<!--Botão voltar para o topo -->
	<div class="back-to-top" href="#" style="margin-bottom: 50px;">
		<i class="fas fa-chevron-up"></i>
	</div>

	<!-- Modal Global -->
	<div class="modal fade" id="modalPage" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
						<h5 class="modal-title" id="title"></h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
				</div>
				<div id="body" class="modal-body"></div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="modalLoading" tabindex="-1" data-backdrop="static">
		  <div class="modal-dialog modal-loading">
		    <div class="modal-content">
		      <div class="modal-body text-center">
		        <div class="modal-body text-center">
					 Carregando..
					 <div class="spinner-border text-success" role="status">
		  				<span class="sr-only">Loading...</span>
					 </div>
		       </div>
		      </div>
		    </div>
		  </div>
	</div>

	<jsp:doBody />

<!-- Footer -->
<footer class="page-footer font-small blue">

  <!-- Copyright -->
  <div class="footer-copyright fixed-bottom text-center" style="background-color: #B84BFF">
   <div class="container text-center mt-2 pb-3">
		  <div class="row">
		  <div class="col-4">
		  		©Copyright
		  </div>
		   <div class="col-4">
		   		<a> V.1.0.0</a>
		  </div>
		   <div class="col-4">
		   		<a style="color:white;" href="https://israellima25.github.io/PageProfile/">@DevLima</a>
		  </div>
		  </div>
	</div>
  </div>
  <!-- Copyright -->

</footer>
<!-- Footer -->

	<%-- 	<script charset="UTF-8" src="${rsc}/js/global.js"></script> --%>
	<script src="../resources/js/global.js"></script>
	<script src="${rsc}/bootstrap-4.5.2/js/bootstrap.min.js"></script>
	<script src="${rsc}/popper/popper.js"></script>
	<script src="${rsc}/js/filter-table.js"></script>

</body>
</html>


