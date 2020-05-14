package com.example.demo.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Repository.GameRepository;
import com.example.demo.Service.GameServiceImpt;
import com.example.demo.Service.TopicServiceImpt;

@SpringBootTest
class TsscGameTestMockU {

	
	@Autowired
	@InjectMocks
	private GameServiceImpt gameImp;
	@Mock
	private TopicServiceImpt topicServiceImp;
	@Mock
	private GameRepository Gamerepo;
	
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
	@AfterAll
	public static void afterTest() {
		System.out.println("-----> FINISH <-----");
	}

	@Test
	void testAnadirGame2GrupoMenorAUno() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(0);
		gameOne.setNSprints(1);
		gameImp.AnadirGameJuego2(gameOne, 2);
		Long id = gameOne.getId();
		when(Gamerepo.existsById(id)).thenReturn(false);
		assertFalse(gameImp.existbyId(id));

	}

	@Test
	void testAnadirGame2SprintsMenorAUno() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(0);
		gameImp.AnadirGameJuego2(gameOne, 2);
		Long id = gameOne.getId();
		when(Gamerepo.existsById(id)).thenReturn(false);
		assertFalse(gameImp.existbyId(id));

	}

	@Test
	void testAnadirGameConTemaJuego2() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(1);
		tema.setDefaultSprints(1);
		
		Long id = tema.getId();
		when(topicServiceImp.existeById(id)).thenReturn(true);
		when(topicServiceImp.findTopicById(id)).thenReturn(tema);
		when(Gamerepo.existsById(gameOne.getId())).thenReturn(true);
		
		gameImp.AnadirGameJuego2(gameOne, id);
		assertTrue(gameImp.existbyId(id));
	

	}

	@Test
	void testAnadirGameGrupoMenorAUno() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(0);
		gameOne.setNSprints(1);
		gameImp.AnadirGameConTema(gameOne, 2);
		Long id = gameOne.getId();
		when(Gamerepo.existsById(id)).thenReturn(false);
		assertFalse(gameImp.existbyId(id));

	}

	@Test
	void testAnadirGameSprintsMenorAUno() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(0);
		gameImp.AnadirGameConTema(gameOne, 2);
		Long id = gameOne.getId();
		when(Gamerepo.existsById(id)).thenReturn(false);
		assertFalse(gameImp.existbyId(id));

	}

	@Test
	void testAnadirGameConTema() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(1);
		tema.setDefaultSprints(1);
		Long id = tema.getId();
	
		
		when(topicServiceImp.existeById(id)).thenReturn(true);
		when(topicServiceImp.findTopicById(id)).thenReturn(tema);
		
		gameImp.AnadirGameConTema(gameOne, id);
		

		
		when(Gamerepo.existsById(gameOne.getId())).thenReturn(true);

	
		assertTrue(gameImp.existbyId(gameOne.getId()));

	}

	@Test
	void testAnadirGameConTemaFalla() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(0);
		tema.setDefaultSprints(0);

		topicServiceImp.AnadirTopic(tema);

		Long id = tema.getId();
		gameImp.AnadirGameConTema(gameOne, tema.getId());
		when(topicServiceImp.existeById(id)).thenReturn(false);
		when(topicServiceImp.findTopicById(id)).thenReturn(null);
		when(Gamerepo.existsById(gameOne.getId())).thenReturn(false);

		
		assertNull(gameImp.findGameById(gameOne.getId()));

	}

	@Test
	void testAnadirGameSinTema() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		Optional<TsscGame> game= Optional.of(gameOne);

		when(Gamerepo.findById(gameOne.getId())).thenReturn(game);
		when(Gamerepo.existsById(gameOne.getId())).thenReturn(true);

		gameImp.AnadirGameSinTema(gameOne);
		assertNotNull(gameImp.findGameById(gameOne.getId()));

	}
	
	@Test
	void testAnadirGameSinTemaGrupoMenor1() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(0);
		gameOne.setNSprints(1);
		
		Optional<TsscGame> game= Optional.of(gameOne);

		when(Gamerepo.findById(gameOne.getId())).thenReturn(game);
		when(Gamerepo.existsById(gameOne.getId())).thenReturn(true);

		gameImp.AnadirGameSinTema(gameOne);
		assertNotNull(gameImp.findGameById(gameOne.getId()));

	}

	@Test
	void testAnadirGameSinTemaSprintsMenor1() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(0);
		
		Optional<TsscGame> game= Optional.of(gameOne);

		when(Gamerepo.findById(gameOne.getId())).thenReturn(game);
		when(Gamerepo.existsById(gameOne.getId())).thenReturn(true);

		gameImp.AnadirGameSinTema(gameOne);
		assertNotNull(gameImp.findGameById(gameOne.getId()));

	}

	
	
	
	@Test
	void testActualizarGameGrupoMenorA1() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameOne.setName("Cambiando Nombre");
		
	   Optional<TsscGame> game= Optional.of(gameOne);
		
		when(Gamerepo.existsById(gameOne.getId())).thenReturn(true);
		gameImp.ActualizarGame(gameOne, 0, "Historia");
		
		when(Gamerepo.findById(gameOne.getId())).thenReturn(game);
	
		assertNotEquals(gameImp.findGameById(gameOne.getId()).getNGroups(), 0);

	}
	


	@Test
	void testActualizarGameNombreVacio() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameOne.setName("Cambiando Nombre");
		
	
         Optional<TsscGame> game= Optional.of(gameOne);
		
		when(Gamerepo.existsById(gameOne.getId())).thenReturn(true);

		gameImp.ActualizarGame(gameOne, 2, "");
		when(Gamerepo.findById(gameOne.getId())).thenReturn(game);
	
		assertNotEquals(gameImp.findGameById(gameOne.getId()).getName(), "");

	}

	@Test
	void testActualizarGameNombreNull() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameOne.setName("Cambiando Nombre");
		
	
         Optional<TsscGame> game= Optional.of(gameOne);
		
		when(Gamerepo.existsById(gameOne.getId())).thenReturn(true);

		gameImp.ActualizarGame(gameOne, 2, null);
		when(Gamerepo.findById(gameOne.getId())).thenReturn(game);
	
		assertNotEquals(gameImp.findGameById(gameOne.getId()).getName(), null);
		assertNotNull(gameImp.findGameById(gameOne.getId()).getName());

	}

	@Test
	void testActualizarGame() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameOne.setName("Cambiando Nombre");
		
	
         Optional<TsscGame> game= Optional.of(gameOne);
		
		when(Gamerepo.existsById(gameOne.getId())).thenReturn(true);

		gameImp.ActualizarGame(gameOne, 2, "Historia");
		when(Gamerepo.findById(gameOne.getId())).thenReturn(game);
	
		assertNotEquals(gameImp.findGameById(gameOne.getId()).getName(), "");
		assertNotEquals(gameImp.findGameById(gameOne.getId()).getName(), null);
		assertNotEquals(gameImp.findGameById(gameOne.getId()).getNGroups(), 0);
		assertEquals(gameImp.findGameById(gameOne.getId()).getNGroups(), 2);
		assertEquals(gameImp.findGameById(gameOne.getId()).getName(), "Historia");

	}

}
