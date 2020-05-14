package com.example.demo.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Repository.GameRepository;
import com.example.demo.Service.GameServiceImpt;
import com.example.demo.Service.TopicServiceImpt;

@SpringBootTest
class TsscGameTest {

	@Autowired
	private TopicServiceImpt topicServiceImp;
	@Autowired
	private GameServiceImpt gameImp;

	@BeforeAll
	public static void beforeTest() {

	}

	@AfterAll
	public static void afterTest() {
		System.out.println("-----> FINISH <-----");
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

		topicServiceImp.AnadirTopic(tema);
		gameImp.AnadirGameJuego2(gameOne, tema.getId());
		assertTrue(gameImp.existbyId(gameOne.getId()));
		assertNotNull(gameImp.AnadirGameJuego2(gameOne, tema.getId()));
		assertNotNull(gameImp.findGameById(gameOne.getId()).getTsscStories());
		assertNotNull(gameImp.findGameById(gameOne.getId()).getTsscTimecontrols());

	}

	@Test
	void testAnadirGameConTemaJuego2Error() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(0);
		tema.setDefaultSprints(1);

		topicServiceImp.AnadirTopic(tema);

		assertNull(gameImp.AnadirGameJuego2(gameOne, tema.getId()));
		assertFalse(gameImp.existbyId(gameOne.getId()));
		assertNull(gameImp.findGameById(gameOne.getId()));

	}
	
	
	@Test
	void testAnadirGameConTemaJuego2Falla() {
      
		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(1);
		tema.setDefaultSprints(1);

		topicServiceImp.AnadirTopic(tema);
		assertNull(gameImp.AnadirGameJuego2(null, tema.getId()));

	}
	
	

	

	@Test
	void testAnadirGame1ConTema() {

		TsscGame gameOne = new TsscGame();

		gameOne.setNGroups(1);
		gameOne.setNSprints(1);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(1);
		tema.setDefaultSprints(1);

		topicServiceImp.AnadirTopic(tema);
		gameImp.AnadirGameConTema(gameOne, tema.getId());

		assertTrue(gameImp.existbyId(gameOne.getId()));
		assertNotNull(gameImp.findGameById(gameOne.getId()));

	}

	@Test
	void testAnadirGame1ConTemaFalla() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);

		TsscTopic tema = new TsscTopic();
		tema.setDefaultGroups(0);
		tema.setDefaultSprints(1);

		topicServiceImp.AnadirTopic(tema);
		gameImp.AnadirGameConTema(gameOne, tema.getId());

		assertFalse(topicServiceImp.existeById(tema.getId()));
		assertFalse(gameImp.existbyId(gameOne.getId()));
		assertNull(gameImp.findGameById(gameOne.getId()));
	}

	@Test
	void testAnadirGame1SinTema() {

		TsscGame gameOne = new TsscGame();
		// duda
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);

		gameImp.AnadirGameSinTema(gameOne);
		assertTrue(gameImp.existbyId(gameOne.getId()));
		assertNotNull(gameImp.findGameById(gameOne.getId()));

	}

	@Test
	void testAnadirGame1SinTemaFalla() {
//
//		TsscGame gameOne = new TsscGame();
//		// duda
//		gameOne.setNGroups(1);
//		gameOne.setNSprints(1);
//
//		gameImp.AnadirGameSinTema(gameOne);
//		assertFalse(gameImp.existbyId(gameOne.getId()));
//		assertNull(gameImp.findGameById(gameOne.getId()));
		
		assertNull(gameImp.AnadirGameSinTema(null));

	}

	@Test
	void testActualizarGame() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameOne.setName("Cambiando Nombre");
		gameImp.AnadirGameSinTema(gameOne);

		gameImp.ActualizarGame(gameOne, 2, "Historia");

		assertEquals("Historia", gameImp.findGameById(gameOne.getId()).getName());
		assertEquals(2, gameOne.getNGroups());

	}

	@Test
	void testActualizarGameFalla() {

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameOne.setName("Cambiando Nombre");

		assertNull(gameImp.ActualizarGame(gameOne, 2, "Historia"));

	}

}
