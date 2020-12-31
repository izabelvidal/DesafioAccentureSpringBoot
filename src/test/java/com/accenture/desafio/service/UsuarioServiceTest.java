package com.accenture.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.desafio.domain.User;
import com.accenture.desafio.repository.UserRepository;
import com.accenture.desafio.service.exceptions.ObjectNotFoundException;

@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService;
	
	@Mock
	private UserRepository usuarioRepository;
	
	private User usuario;
	
	@Before
	public void init() {
		usuario = new User();
		usuario.setId(1L);
	}
	
	@Test
    public void buscarUsuarioPorIdComSucesso () {
        given(usuarioRepository.findById(anyLong()))
                .willReturn(Optional.of(usuario));

        var result = usuarioService.find(1L);

        assertNotNull(result);
        assertEquals(Optional.of(1l), Optional.of(result.getId()));

        verify(usuarioRepository, times(1)).findById(anyLong());
    }
	
	@SuppressWarnings("unused")
	@Test(expected = ObjectNotFoundException.class)
    public void buscarPessoaPorIdQueNaoExiste() {
        given(usuarioRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        var result = usuarioService.find(1L);

        verify(usuarioRepository, times(1))
                .findById(anyLong());
    }
	
	@Test
    public void deletarPessoaUsuarioPorIdComSucesso() {
        given(usuarioRepository.existsById(anyLong()))
                .willReturn(Boolean.TRUE);

        doNothing().when(usuarioRepository).deleteById(anyLong());

        usuarioService.delete(1L);

        verify(usuarioRepository, times(1))
                .existsById(anyLong());
        verify(usuarioRepository, times(1))
                .deleteById(anyLong());
    }
	
}
