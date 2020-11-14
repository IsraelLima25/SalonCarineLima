package br.com.salon.carine.lima.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salon.carine.lima.converters.ConvertersUsuario;
import br.com.salon.carine.lima.dto.EmailDTO;
import br.com.salon.carine.lima.dto.UsuarioDTO;
import br.com.salon.carine.lima.models.Role;
import br.com.salon.carine.lima.models.Usuario;
import br.com.salon.carine.lima.repositoriessdp.UsuarioRepositorySJPA;
import br.com.salon.carine.lima.security.BCriptyHashImpl;
import br.com.salon.carine.lima.utils.GeneratorPassword;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioRepositorySJPA usuarioRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCriptyHashImpl bCriptyHashImpl;
	
	public void registrarUsuarioService(UsuarioDTO usuarioDTO) throws Exception {
		
		Optional<Usuario> optionalUsuario = usuarioRepository.
				findByEmail(usuarioDTO.getEmail());
		
		if(optionalUsuario.isPresent()) {
			throw new Exception();
		}
		
		Usuario usuario = ConvertersUsuario
				.deUsuarioDTOParaUsuario(usuarioDTO);
		usuario.getRoles().add(getRole());
		
		Usuario usuarioEncripty = bCriptyHashImpl.usuarioEncripty(usuario);
		
		usuarioRepository.save(usuarioEncripty);
		
		enviarEmailBoasVindas(usuarioEncripty, gerarLink(usuarioEncripty));
	}
	
	private String gerarLink(Usuario usuarioEncripty) {
		return "http://localhost:8080/SalonCarineLima/desbloquearUsuario?identity="+usuarioEncripty.getSalt();
	}

	private void enviarEmailBoasVindas(Usuario usuario, String link) {
		EmailDTO email = new EmailDTO();
		email.setAssunto("Bem Vindo ao SAG");
		email.setDestinatario(usuario.getEmail());
		email.setMensagem("Olá, " + usuario.getNome() + " seja bem vindo(a) ao SAG. \n"
				+ "Estamos muito contentes por ter você aqui!!\n"
				+ "Acesse o link abaixo para confirmar seu cadastro. \n"
				+ link +"\n"
				+ "Atenciosamente \n"
				+ "@DevLima");
		
		emailService.enviarEmail(email);
	}

	public void recuperarSenhaUsuario(String email) {
		 Usuario usuario = usuarioRepository.findByEmail(email).get();
		 String newPassword = GeneratorPassword.generate();
		 usuario.setSenha(newPassword);
		 Usuario usuarioEncripty = bCriptyHashImpl.usuarioEncripty(usuario);
		 
		 usuarioRepository.save(usuarioEncripty);
		 
		 enviarEmailSendNewPassword(usuario,newPassword);
		 
	}

	private void enviarEmailSendNewPassword(Usuario usuario, String newPassword) {
		
		EmailDTO email = new EmailDTO();
		email.setAssunto("Nova Senha");
		email.setDestinatario(usuario.getEmail());
		email.setMensagem("Sag - Agendamentos Informa \n"
				+ "Nova senha gerada com sucesso !! \n"
				+ "Nova Senha: "+newPassword+"\n"
				+ "Atenciosamente \n"
				+ "@DevLima");
		
		emailService.enviarEmail(email);
	}

	private Role getRole() {
		Role role = new Role("'ROLE_ADM'");
		return role;
	}
	
	public void updateUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
}
