<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div id="topHeader">
<nav id="navMenu" class="navbar fixed-top navbar-expand-sm navbar-custom">
    <a href="../dashboard/home" class="navbar-brand far fa-calendar-alt loader"> SAG - Agendamentos</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCustom">
        <i class="fa fa-bars fa-lg py-1 text-white"></i>
    </button>
    <div class="navbar-collapse collapse" id="navbarCustom">
        <ul class="navbar-nav">
            <li id="atendimento-item" class="nav-item ${activeAtendimento}">
                <a class="nav-link" href="../atendimento/listar"> 
                	<i class="fas fa-phone-square"></i>
                	Atendimentos
                </a>
            </li>
            <li class="nav-item dropdown ${activeCliente} ">
                <a id="clientes-dropdown" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="fas fa-users"></i>              
                  Clientes
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a id="cadastrar-cliente" class="dropdown-item" href="../cliente/cadastro">Cadastrar</a>
                    <a id="listar-cliente" class="dropdown-item" href="../cliente/listar">Listar</a>
                    <a id="listar-cliente" class="dropdown-item" href="../cliente/relatorios">Relatórios</a>
                </div>
            </li>
            <li class="nav-item ${activeFinanceiro}">
                <a class="nav-link" href="../lancamento/relatorio">
                	<i class="fas fa-hand-holding-usd"></i>
                	Financeiro
                </a>
            </li>
             <li class="nav-item dropdown ${activeServico}">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="fas fa-concierge-bell"></i>              
                  Serviços
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="../servico/cadastro">Cadastrar</a>
                    <a class="dropdown-item" href="../servico/listar">Listar</a>
                </div>
            </li>
            <li class="nav-item ${activeLoja}">
                <a class="nav-link" href="../loja/itensLoja">
                <i class="fas fa-store"></i>
                	Loja
                </a>
            </li>
            <li class="nav-item ${activeCarrinho}">
                <a class="nav-link" href="../carrinho/itensCarrinho">
                <i class="fas fa-cart-plus"></i>
                	Carrinho
                </a>
            </li>  
            
            <li class="nav-item dropdown profile">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="fas fa-user"></i>
                  	Usuário
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item"
                    	 href="../config/alterarSenha">Alterar Senha</a>
                    <a class="dropdown-item" href="../config/meuPerfil">Meu Perfil</a>
                    <a class="dropdown-item" href="../logout">Sair</a>
                </div>
            </li>

        </ul>
	
    </div>
</nav>

</div>
	    
    