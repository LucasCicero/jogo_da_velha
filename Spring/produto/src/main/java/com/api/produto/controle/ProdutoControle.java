package com.api.produto.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.produto.modelo.ProdutoModelo;
import com.api.produto.modelo.RespostaModelo;
import com.api.produto.repositorio.ProdutoRepositorio;

@RestController
@RequestMapping("/api")
public class ProdutoControle {
	
	@Autowired
	private ProdutoRepositorio acoes;
	
	//lista produtos
	@RequestMapping(value="/produtos", method=RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:80")
	public @ResponseBody List<ProdutoModelo> listar() {
		return acoes.findAll();
	}
	
	//Cadastro produtos
	@RequestMapping(value="/produtos", method=RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:80")
	public @ResponseBody ProdutoModelo cadastrar(@RequestBody ProdutoModelo produto) {
	return acoes.save(produto);	
	}

	//filtrar produtos
	@RequestMapping(value="/produtos/{codigo}", method=RequestMethod.GET)
	public @ResponseBody ProdutoModelo filtrar(@PathVariable Integer codigo) {
		return acoes.findByCodigo(codigo);
	}	
	
	//Alterar produtos
	@RequestMapping(value="/produtos",method=RequestMethod.PUT)
	public @ResponseBody ProdutoModelo alterar(@RequestBody ProdutoModelo produto) {
		return acoes.save(produto);
	}
	
	//deletar produtos
	@RequestMapping(value="/produtos/{codigo}", method=RequestMethod.DELETE)
	public @ResponseBody RespostaModelo remover(@PathVariable Integer codigo) {
		
		RespostaModelo resposta = new RespostaModelo();
	 try {
		ProdutoModelo produto= filtrar(codigo);
		this.acoes.delete(produto);
		resposta.setMensagem("Produto removido com sucesso");
	}catch(Exception erro) {
		resposta.setMensagem("Falha ao remover:"+ erro.getMessage());
	}
	 return resposta;
}
}
