/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.worshipass.service;

import api.worshipass.domain.Usuario;
import api.worshipass.repository.UsuarioRepository;
import api.worshipass.utils.HashUtil;
import com.nimbusds.jose.JOSEException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David
 */
@Service
public class UsuarioService {
    private final String userNotFound = "Usuario ou senha invalidos!";
    private final UsuarioRepository usuarioRepository;
    private final JweService jweService;

    public UsuarioService(UsuarioRepository usuarioRepository, JweService jweService) {
        this.usuarioRepository = usuarioRepository;
        this.jweService = jweService;
    }

    @Transactional
    public Map<String, Object> login(Usuario usuario) {
        Usuario found = usuarioRepository.findByUsuario(usuario.getUsuario())
                .orElseThrow(() -> new RuntimeException(userNotFound));
        
        String inputPassword = HashUtil.sha256(usuario.getSenha());
        
        String foundPassword = found.getSenha();
        
        if (!inputPassword.equals(foundPassword))
            throw new RuntimeException(userNotFound);
        
        String token = jweService.createToken(usuario.getUsuario());
        
        found.setToken(token);
        
        update(found.getId(), found);
        
        Map<String, Object> user = new HashMap<>();
        user.put("id", found.getId());
        user.put("nome", found.getNome());
        user.put("usuario", found.getUsuario());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        
        return response;
    }
    
    @Transactional
    public boolean validateToken(String token){
        boolean isTokenValid = false;
        
        try{
            String user = jweService.validateToken(token);
            
            Usuario usuario = usuarioRepository.findByUsuario(user)
                    .orElseThrow(() -> new RuntimeException());
            
            isTokenValid = true;
        }
        catch (Exception e){}
        
        return isTokenValid;
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário nao encontrado: " + id));
    }

    @Transactional
    public Usuario create(Usuario usuario) {
        usuario.setId(null);
        
        String password = usuario.getSenha();
        
        usuario.setSenha(HashUtil.sha256(password));
        
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Integer id, Usuario dados) {
        Usuario existente = findById(id);
        
        existente.setNome(dados.getNome());
        existente.setUsuario(dados.getUsuario());
        existente.setSenha(dados.getSenha());
        existente.setToken(dados.getToken());
        
        return usuarioRepository.save(existente);
    }

    @Transactional
    public void delete(Integer id) {
        Usuario existente = findById(id);
        
        usuarioRepository.delete(existente);
    }
}