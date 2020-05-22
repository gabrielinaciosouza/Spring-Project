package com.gabriel.projetospring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabriel.projetospring.domain.Categoria;
import com.gabriel.projetospring.domain.Cidade;
import com.gabriel.projetospring.domain.Cliente;
import com.gabriel.projetospring.domain.Endereco;
import com.gabriel.projetospring.domain.Estado;
import com.gabriel.projetospring.domain.Produto;
import com.gabriel.projetospring.domain.enums.TipoCliente;
import com.gabriel.projetospring.repositories.CategoriaRepository;
import com.gabriel.projetospring.repositories.CidadeRepository;
import com.gabriel.projetospring.repositories.ClienteRepository;
import com.gabriel.projetospring.repositories.EnderecoRepository;
import com.gabriel.projetospring.repositories.EstadoRepository;
import com.gabriel.projetospring.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetospringApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProduto().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProduto().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Gabriel", "gabriel@email.com", "12345678900", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("9912345678", "9998765432"));

		Endereco e1 = new Endereco(null, "Rua Antonio", "123", "Ap300", "Jardim", "12345678", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua João", "321", "Sala 800", "Bairro", "87654321", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));

	}

}
