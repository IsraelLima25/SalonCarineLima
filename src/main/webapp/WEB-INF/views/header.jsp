<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

	 <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
	 
	        <a class="navbar-brand far fa-calendar-alt loader" href="../dashboard/home" 
	        	style="margin-left: 15px;">
	       		SAG - Agendamentos 
	        </a>
	
	        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
	            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	            <span class="navbar-toggler-icon"></span>
	        </button>
	        <div class="collapse navbar-collapse" id="navbarNav">
	            <ul class="navbar-nav">
	
	                <li id="dpdwAtendimento" class="nav-item dropdown">
	                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAtendimento" role="button"
	                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        <i class="fas fa-phone-square"></i>
	                        Atendimentos
	                    </a>
	                    <div id="divAtendimento" class="dropdown-menu" aria-labelledby="navbarDropdownAtendimento">
	                        <a class="dropdown-item" href="../atendimento/listar"><i style="font-size: 10px;" class="fas fa-play"></i>
	                            Agenda</a>
	                    </div>
	                </li>
	
	                <li class="nav-item dropdown dpdwCliente">
	                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCliente" role="button"
	                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        <i class="fas fa-user"></i>
	                        Clientes
	                    </a>
	                    <div id="divCliente" class="dropdown-menu" aria-labelledby="navbarDropdownCliente">
	                        <a class="dropdown-item" href="../cliente/cadastro"><i style="font-size: 10px;"
	                                class="fas fa-play"></i> Cadastrar</a>
	                        <a class="dropdown-item" href="../cliente/listar"><i style="font-size: 10px;"
	                                class="fas fa-play"></i> Listar</a>
	                    </div>
	                </li>
	
	                <li class="nav-item dropdown dpdwLancamento">
	                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownLancamento" role="button"
	                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        <i class="fas fa-hand-holding-usd"></i>
	                        	Lan�amentos
	                    </a>
	                    <div id="divLancamento" class="dropdown-menu" aria-labelledby="navbarDropdownLancamento">
	                        <a class="dropdown-item" href="../lancamento/relatorio"><i style="font-size: 10px;" class="fas fa-play"></i>
	                            Relat�rio
	                         </a>
	                    </div>
	                </li>
	
	                <li class="nav-item dropdown dpdwServico">
	                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownServico" role="button"
	                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        <i class="fas fa-concierge-bell"></i>
	                        Servi�os
	                    </a>
	                    <div id="divServico" class="dropdown-menu" aria-labelledby="navbarDropdownServico">
	                        <a class="dropdown-item" href="../servico/cadastro"><i style="font-size: 10px;"
	                                class="fas fa-play"></i> Cadastrar</a>
	                        <a class="dropdown-item" href="../servico/listar"><i style="font-size: 10px;"
	                                class="fas fa-play"></i> Listar</a>
	                    </div>
	                   
	                </li>
	                
	               	<li>
	                    <a class="nav-link" href="../loja/itensLoja" aria-expanded="false">
	                    <i class="fas fa-store"></i>
	                        Loja
	                    </a>
	             	</li>
	                
	               	<li>
	                    <a class="nav-link" href="../carrinho/itensCarrinho" aria-expanded="false">
	                    <i class="fas fa-cart-plus"></i>
	                        Carrinho
	                    </a>
	             	</li>
	             	
	               	<li>
	                    <a class="nav-link" href="../signin/login.html" aria-expanded="false">
	                        <i class="fas fa-sign-out-alt"></i>
	                        Sair
	                    </a>
	             	</li>
	
	            </ul>
	            <ul class="nav navbar-nav navbar-right">
	            	<li>
	            		Usuario:
	            		<a href="#"><security:authentication property="principal.username"></security:authentication></a>
	            	</li>
	            </ul>
	        </div>
	    </nav>
    