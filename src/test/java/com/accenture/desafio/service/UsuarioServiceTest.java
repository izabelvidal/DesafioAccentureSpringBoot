package com.accenture.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.desafio.domain.Usuario;
import com.accenture.desafio.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario;
	
	@Before
	public void init() {
		usuario = new Usuario();
		usuario.setId(1L);
	}
	
	@Test
    public void buscarUsuarioPorCodigoComSucesso () {
        // cenario
        given(usuarioRepository.findById(anyLong()))
                .willReturn(Optional.of(usuario));

        // execucao
        var result = usuarioService.find(1L);

        // validação
        assertNotNull(result);
        assertEquals(Optional.of(1l), Optional.of(result.getId()));

        verify(usuarioRepository, times(1)).findById(anyLong());
    }
}
