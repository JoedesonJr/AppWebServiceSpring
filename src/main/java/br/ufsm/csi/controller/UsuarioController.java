package br.ufsm.csi.controller;

import br.ufsm.csi.dao.UsuarioDao;
import br.ufsm.csi.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UsuarioController {

	@RequestMapping("/autenticarUsuario")
	public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {

		Usuario usuarioAutenticado = new UsuarioDao().autenticarUsuario(usuario);

		return new ResponseEntity<Usuario>(usuarioAutenticado, HttpStatus.OK);
	}

	@RequestMapping("/cadastrarUsuario")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) throws Exception {

		usuario = new UsuarioDao().cadastrarUsuario(usuario);

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@RequestMapping("/listaUsuarios")
	public ResponseEntity<ArrayList<Usuario>> listar() {

		ArrayList<Usuario> usuarios = new UsuarioDao().listarUsuarios();

		return new ResponseEntity<ArrayList<Usuario>>(usuarios, HttpStatus.OK);
	}

	@RequestMapping("/buscarUsuario")
	public ResponseEntity<Usuario> buscar(@RequestBody Usuario usuario) throws Exception {

		usuario = new UsuarioDao().buscarUsuario(usuario);

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@RequestMapping("/alterarUsuario")
	public ResponseEntity<Usuario> alterar(@RequestBody Usuario usuario) throws Exception {

		usuario = new UsuarioDao().alterarUsuario(usuario);

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
}
