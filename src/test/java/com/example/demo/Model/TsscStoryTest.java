package com.example.demo.Model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Service.GameServiceImpt;
import com.example.demo.Service.StoryServiceImpt;
import com.example.demo.Service.TopicServiceImpt;

@SpringBootTest
class TsscStoryTest {

	@Autowired
	private StoryServiceImpt StoryServiceImp;

	@Autowired
	private GameServiceImpt gameImp;

	
	@Test
	void guardarHistoriaFallaSprintMenor1() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(0));
		nueva.setPriority(BigDecimal.valueOf(1));

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameImp.AnadirGameSinTema(gameOne);
		
		StoryServiceImp.AnadirStory(nueva, gameOne.getId());

		assertFalse(StoryServiceImp.existbyId(nueva.getId()));

		

	}


	@Test
	void guardarHistoriaAsociadaJuego() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));

		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameImp.AnadirGameSinTema(gameOne);

		StoryServiceImp.AnadirStory(nueva, gameOne.getId());
		
		
		assertNotNull(StoryServiceImp.findStoryById(nueva.getId()));
		assertTrue(StoryServiceImp.existbyId(nueva.getId()));

	}
	

	@Test
	void guardarHistoriaAsociadaJuegoFalla() {

		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameImp.AnadirGameSinTema(gameOne);

		
		
		assertNull(StoryServiceImp.AnadirStory(null, gameOne.getId()));
		

	}
	
	
	
	
	@Test
	void actualizarHistoriaNoDescriptionFalla() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameImp.AnadirGameSinTema(gameOne);
		StoryServiceImp.AnadirStory(nueva, gameOne.getId());
		
		StoryServiceImp.ActualizarStory(nueva, "Primero", "");
		

		assertNotNull(StoryServiceImp.findStoryById(nueva.getId()));
		assertNotEquals(StoryServiceImp.findStoryById(nueva.getId()).getDescription(), "" );
			

	}
	
	
	
	@Test
	void actualizarHistoriaDescriptionyAltDescription() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));
		nueva.setAltDescripton("Probar");
		nueva.setDescription("probar2");
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		gameImp.AnadirGameSinTema(gameOne);
		StoryServiceImp.AnadirStory(nueva, gameOne.getId());
		

		StoryServiceImp.ActualizarStory(nueva, "Primero", "Segundo");
		Long id=nueva.getId();
		
		assertEquals("Primero", StoryServiceImp.findStoryById(id).getAltDescripton());
		assertEquals("Segundo", StoryServiceImp.findStoryById(id).getDescription());
			

	}
	
	
	

}
