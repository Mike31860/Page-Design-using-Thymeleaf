package com.example.demo.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Repository.StoryRepository;
import com.example.demo.Repository.TopicRepository;
import com.example.demo.Service.GameServiceImpt;
import com.example.demo.Service.StoryServiceImpt;
import com.example.demo.Service.TopicServiceImpt;

@SpringBootTest
class TsscStoryTestMockU {

	@Autowired
	@InjectMocks
	private StoryServiceImpt StoryServiceImp;

	@Mock
	private GameServiceImpt gameImp;

	@Mock
	private StoryRepository Storyrepo;
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void guardarHistoriaValorNegocio_Menor_a_1() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(0));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));
		Long id = nueva.getId();
		
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		when(gameImp.existbyId(gameOne.getId())).thenReturn(true);
		when(gameImp.findGameById(gameOne.getId())).thenReturn(gameOne);
		when(Storyrepo.existsById(id)).thenReturn(false);
		
		
		StoryServiceImp.AnadirStory(nueva, gameOne.getId());
		assertFalse(StoryServiceImp.existbyId(id));


	}

	@Test
	void guardarHistoriaSprintInicialMenor_a_1() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(0));
		nueva.setPriority(BigDecimal.valueOf(1));
		Long id = nueva.getId();
		
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		when(gameImp.existbyId(gameOne.getId())).thenReturn(true);
		when(gameImp.findGameById(gameOne.getId())).thenReturn(gameOne);
		when(Storyrepo.existsById(id)).thenReturn(false);
		
		
		StoryServiceImp.AnadirStory(nueva, gameOne.getId());
		assertFalse(StoryServiceImp.existbyId(id));

	}
//
	@Test
	void guardarHistoriaPrioridadMenor_a_1() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(0));
		Long id = nueva.getId();
		
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		when(gameImp.existbyId(gameOne.getId())).thenReturn(true);
		when(gameImp.findGameById(gameOne.getId())).thenReturn(gameOne);
		when(Storyrepo.existsById(id)).thenReturn(false);
		
		
		StoryServiceImp.AnadirStory(nueva, gameOne.getId());
		assertFalse(StoryServiceImp.existbyId(id));

	}
//
	@Test
	void guardarHistoriaAsociadaJuego() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));
		Long id = nueva.getId();
		
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		when(gameImp.existbyId(gameOne.getId())).thenReturn(true);
		when(gameImp.findGameById(gameOne.getId())).thenReturn(gameOne);
		when(Storyrepo.existsById(id)).thenReturn(true);
		when(gameImp.agregarStory(gameOne, nueva)).thenReturn(nueva);
		
		
		StoryServiceImp.AnadirStory(nueva, gameOne.getId());
		assertTrue(StoryServiceImp.existbyId(id));

	}
//	
//
//	
//	
	@Test
	void guardarHistoriaNoAsociadaJuego() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));
		Long id = nueva.getId();
		
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		when(gameImp.existbyId(gameOne.getId())).thenReturn(false);
		when(gameImp.findGameById(gameOne.getId())).thenReturn(gameOne);
		when(Storyrepo.existsById(id)).thenReturn(false);
		
		
		StoryServiceImp.AnadirStory(nueva, gameOne.getId());
		assertFalse(StoryServiceImp.existbyId(id));

	}
//	
//	
	@Test
	void actualizarHistoriaAltDescriptionNull() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));
		
		nueva.setAltDescripton("AltDescript");
		nueva.setDescription("description");
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		Long id = nueva.getId();
		
		Optional<TsscStory> optional= Optional.of(nueva);
		
	
		//when(gameImp.findGameById(gameOne.getId())).thenReturn(gameOne);
		when(Storyrepo.existsById(id)).thenReturn(true);
		when(Storyrepo.findById(nueva.getId())).thenReturn(optional);

		
		StoryServiceImp.ActualizarStory(nueva, null, "SegundaPr");
		

		assertNotEquals(StoryServiceImp.findStoryById(nueva.getId()).getAltDescripton(), null);
			

	}
//	
	@Test
	void actualizarHistoriaAltDescription() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));
		
		nueva.setAltDescripton("AltDescript");
		nueva.setDescription("description");
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		Long id = nueva.getId();
		
		Optional<TsscStory> optional= Optional.of(nueva);
		
	
		//when(gameImp.findGameById(gameOne.getId())).thenReturn(gameOne);
		when(Storyrepo.existsById(id)).thenReturn(true);
		when(Storyrepo.findById(nueva.getId())).thenReturn(optional);

		
		StoryServiceImp.ActualizarStory(nueva, "NuevaAltDes", "SegundaPri");
		

		assertEquals(StoryServiceImp.findStoryById(nueva.getId()).getAltDescripton(), "NuevaAltDes");
		assertEquals(StoryServiceImp.findStoryById(nueva.getId()).getDescription(),"SegundaPri");
			

	}
	
	
	
	
//	
	@Test
	void actualizarHistoriaAltDescriptionVacio() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));
		
		nueva.setAltDescripton("AltDescript");
		nueva.setDescription("description");
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		Long id = nueva.getId();
		
		Optional<TsscStory> optional= Optional.of(nueva);
		
	
		//when(gameImp.findGameById(gameOne.getId())).thenReturn(gameOne);
		when(Storyrepo.existsById(id)).thenReturn(true);
		when(Storyrepo.findById(nueva.getId())).thenReturn(optional);

		
		StoryServiceImp.ActualizarStory(nueva, "", "NuevaDescription");
		

		assertNotEquals(StoryServiceImp.findStoryById(nueva.getId()).getAltDescripton(), "");
		assertEquals(StoryServiceImp.findStoryById(nueva.getId()).getDescription(),"description");
			

	
			

	}
//	
	@Test
	void actualizarHistoriaDescriptionVacia() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));
		
		nueva.setAltDescripton("AltDescript");
		nueva.setDescription("description");
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		Long id = nueva.getId();
		
		Optional<TsscStory> optional= Optional.of(nueva);
		
	
		//when(gameImp.findGameById(gameOne.getId())).thenReturn(gameOne);
		when(Storyrepo.existsById(id)).thenReturn(true);
		when(Storyrepo.findById(nueva.getId())).thenReturn(optional);

		
		StoryServiceImp.ActualizarStory(nueva, "nueva AltDescription", "");
		

		assertNotEquals(StoryServiceImp.findStoryById(nueva.getId()).getAltDescripton(), "nueva AltDescription");
		assertNotEquals(StoryServiceImp.findStoryById(nueva.getId()).getDescription(),"");
			
			

	}
	
	@Test
	void actualizarHistoriaDescriptionNull() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));
		
		nueva.setAltDescripton("AltDescript");
		nueva.setDescription("description");
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		Long id = nueva.getId();
		
		Optional<TsscStory> optional= Optional.of(nueva);
		
	
		//when(gameImp.findGameById(gameOne.getId())).thenReturn(gameOne);
		when(Storyrepo.existsById(id)).thenReturn(true);
		when(Storyrepo.findById(nueva.getId())).thenReturn(optional);

		
		StoryServiceImp.ActualizarStory(nueva, "nueva AltDescription", null);
		

		assertNotEquals(StoryServiceImp.findStoryById(nueva.getId()).getAltDescripton(), "nueva AltDescription");
		assertNotEquals(StoryServiceImp.findStoryById(nueva.getId()).getDescription(),null);
			
			

	}
//	
	@Test
	void actualizarHistoriaDescriptionyAltDescription() {

		TsscStory nueva = new TsscStory();
		nueva.setBusinessValue(BigDecimal.valueOf(1));
		nueva.setInitialSprint(BigDecimal.valueOf(1));
		nueva.setPriority(BigDecimal.valueOf(1));
		
		nueva.setAltDescripton("AltDescript");
		nueva.setDescription("description");
		
		TsscGame gameOne = new TsscGame();
		gameOne.setNGroups(1);
		gameOne.setNSprints(1);
		
		Long id = nueva.getId();
		
		Optional<TsscStory> optional= Optional.of(nueva);
		
	
		//when(gameImp.findGameById(gameOne.getId())).thenReturn(gameOne);
		when(Storyrepo.existsById(id)).thenReturn(true);
		when(Storyrepo.findById(nueva.getId())).thenReturn(optional);

		
		StoryServiceImp.ActualizarStory(nueva, "nueva AltDescription", "nuevaDescription");
		

		assertEquals(StoryServiceImp.findStoryById(nueva.getId()).getAltDescripton(), "nueva AltDescription");
		assertEquals(StoryServiceImp.findStoryById(nueva.getId()).getDescription(),"nuevaDescription");
			

	}
//	
	
	

}
