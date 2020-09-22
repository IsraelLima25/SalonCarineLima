package br.com.salon.carine.lima.converters;

import br.com.salon.carine.lima.dto.EnderecoDTO;
import br.com.salon.carine.lima.models.Endereco;

public class ConvertersEndereco {
	
	public static EnderecoDTO deEnderecoParaEnderecoDTO(Endereco endereco) {

		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setIdEndereco(endereco.getId());
		enderecoDTO.setBairro(endereco.getBairro());
		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setComplemento(endereco.getComplemento());
		enderecoDTO.setLogradouro(endereco.getLogradouro());
		enderecoDTO.setNumero(endereco.getNumero());
		enderecoDTO.setPontoReferencia(endereco.getPontoReferencia());

		return enderecoDTO;

	}
	
	public static Endereco deEnderecoDTOParaEndereco(EnderecoDTO enderecoDTO) {

		Endereco endereco = new Endereco();
		endereco.setId(enderecoDTO.getIdEndereco());
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setCep(enderecoDTO.getCep());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setLogradouro(enderecoDTO.getLogradouro());
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setPontoReferencia(enderecoDTO.getPontoReferencia());

		return endereco;

	}

}
