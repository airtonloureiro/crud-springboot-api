package br.com.springboot.curso_api_rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_api_rest.model.Usuario;
import br.com.springboot.curso_api_rest.repository.UsuarioRepository;




@RestController
public class GreetingsController
{
	
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
	
  
    @GetMapping(value="listartodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	List<Usuario> listaUsuario = usuarioRepository.findAll(); 
    	
    	return new ResponseEntity<List<Usuario>>(listaUsuario, HttpStatus.OK); 
    }
    
    @PostMapping(value="salva")
    @ResponseBody
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario) {
    	Usuario user = usuarioRepository.save(usuario); 
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED); 
    }
    
    @PutMapping(value="atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario) {
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("O id não foi informado.", HttpStatus.OK); 
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario); 
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK); 
    }
    
    @DeleteMapping(value="delete")
    @ResponseBody
    public ResponseEntity<String> deletar (@RequestParam Long idUser) {
    	usuarioRepository.deleteById(idUser); 
    	
    	return new ResponseEntity<String>("User deletado com sucesso!!!", HttpStatus.OK); 
    }
    
    @GetMapping(value="buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscarUsuarioId (@RequestParam (value = "idUser") Long idUser) {
    	Usuario usuario  = usuarioRepository.findById(idUser).get();  
    		
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK); 
    }
    
    
    @GetMapping(value="buscarpornome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarUsuarioNome (@RequestParam (value = "nomeUser") String nomeUser) {
    	List<Usuario> usuario  = usuarioRepository.buscarPorNome(nomeUser);
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK); 
    }
    
    
}
	