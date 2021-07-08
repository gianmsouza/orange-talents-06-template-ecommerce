package br.com.zupacademy.gian.mercadolivre.imagensproduto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.zupacademy.gian.mercadolivre.produto.Produto;

public class NovasImagensRequest {
	
	@NotNull
	@Size(min = 1)
	private List<MultipartFile> imagens = new ArrayList<>();	
	
	public NovasImagensRequest(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}
	
	public List<MultipartFile> getImagens() {
		return imagens;
	}

	public Set<ImagensProduto> toModel(Produto produto, Set<String> links) {
		
		Set<ImagensProduto> listaImagensProduto = new HashSet<>();
		
		for (String link : links) {
			ImagensProduto imagens = new ImagensProduto(link, produto);
			listaImagensProduto.add(imagens);
		}		
		return listaImagensProduto;		
	}	
}
