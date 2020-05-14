package com.example.demo.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Repository.TopicRepository;
import com.example.demo.Service.TopicServiceImpt;

@SpringBootTest
class TsscTopicTestMockU {

	@Autowired
	@InjectMocks
	private TopicServiceImpt topicServiceImp;

	@Mock
	private TopicRepository repo;

	@Test
	void AgregarUnTemaGrupoMenora1() {

		TsscTopic tsstopic = new TsscTopic();

		tsstopic.setDefaultGroups(0);
		tsstopic.setDefaultSprints(4);
		topicServiceImp.AnadirTopic(tsstopic);
		Long id = tsstopic.getId();
		when(repo.existsById(id)).thenReturn(false);
		assertFalse(topicServiceImp.existeById(id));

	}

	@Test
	void AgregarUnTemaSprintsMenora1() {

		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(0);
		topicServiceImp.AnadirTopic(tsstopic);

		Long id = tsstopic.getId();
		when(repo.existsById(id)).thenReturn(false);
		assertFalse(topicServiceImp.existeById(id));

	}

	@Test
	void AgregarUnTemaSprintsyGrupoMayora0() {

		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("HEY BA");
		topicServiceImp.AnadirTopic(tsstopic);

		Long id = tsstopic.getId();
		when(repo.existsById(id)).thenReturn(true);
		assertTrue(topicServiceImp.existeById(id));

	}

//	
//	
	@Test
	void editarunTema() {

		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("El mejor tema");
		tsstopic.setName("Andres");
		topicServiceImp.AnadirTopic(tsstopic);

		Long id = tsstopic.getId();

		Optional<TsscTopic> este = Optional.of(tsstopic);
		topicServiceImp.ActualizarTopic(este.get(), "Miguel", "Un tema");
		TsscTopic encontrado = topicServiceImp.findTopicById(id);

		when(repo.findById(id)).thenReturn(este);
		assertEquals(encontrado.getName(), tsstopic.getName());
		assertEquals(este.get().getDescription(), encontrado.getDescription());

	}

	@Test
	void editarunTemaNameNull() {

		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setName("Nelson");
		topicServiceImp.AnadirTopic(tsstopic);
		topicServiceImp.ActualizarTopic(tsstopic, null, "Un tema");

		Long id = tsstopic.getId();

		Optional<TsscTopic> este = Optional.of(tsstopic);
		when(repo.findById(id)).thenReturn(este);
		TsscTopic encontrado = topicServiceImp.findTopicById(id);
		assertEquals(encontrado.getName(), "Nelson");
		assertNotEquals(encontrado.getDescription(), "Un tema");

	}

	@Test
	void editarunTemaNameVacio() {
		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("El mejor tema");
		tsstopic.setName("Nelson");
		topicServiceImp.AnadirTopic(tsstopic);
		topicServiceImp.ActualizarTopic(tsstopic, "", "Un tema");
	

		Long id = tsstopic.getId();
		Optional<TsscTopic> este = Optional.of(tsstopic);
		when(repo.findById(id)).thenReturn(este);
		TsscTopic encontrado = topicServiceImp.findTopicById(id);
		

		assertEquals(encontrado.getName(), "Nelson");
		assertNotEquals(encontrado.getDescription(), "Un tema");
	}

	@Test
	void editarunTemaDescripcionVacio() {

		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("El mejor tema");
		tsstopic.setName("Nelson");
		topicServiceImp.AnadirTopic(tsstopic);
		topicServiceImp.ActualizarTopic(tsstopic, "Miguel", "");
	

		Long id = tsstopic.getId();
		Optional<TsscTopic> este = Optional.of(tsstopic);
		when(repo.findById(id)).thenReturn(este);
		TsscTopic encontrado = topicServiceImp.findTopicById(id);
		assertEquals(encontrado.getName(), "Nelson");
		assertEquals(encontrado.getDescription(), "El mejor tema");
	}

	@Test
	void editarunTemaDescripcionNull() {

		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("El mejor tema");
		tsstopic.setName("Nelson");
		topicServiceImp.AnadirTopic(tsstopic);
		topicServiceImp.ActualizarTopic(tsstopic, "Miguel", null);
	

		Long id = tsstopic.getId();
		Optional<TsscTopic> este = Optional.of(tsstopic);
		when(repo.findById(id)).thenReturn(este);
		TsscTopic encontrado = topicServiceImp.findTopicById(id);


		assertEquals(encontrado.getName(), "Nelson");
		assertEquals(encontrado.getDescription(), "El mejor tema");

	}

}
