package br.com.zupacademy.gian.mercadolivre.produto.imagens;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploaderFake {
	
	//Classe que faria o envio das imagens para algum serviço de armazenamento

	public Set<String> enviar(List<MultipartFile> imagens) {
		return imagens.stream().map(imagem -> "https://s3.amazon.com/" + imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}
}
