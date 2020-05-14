package com.example.demo.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Service.TopicServiceImpt;

@SpringBootTest
class TsscTopicTest {

	@Autowired
	private TopicServiceImpt topicServiceImp;

	@Test
	void AgregarUnTema() {

		TsscTopic tsstopic = new TsscTopic();

		tsstopic.setDefaultGroups(3);
		tsstopic.setDefaultSprints(4);
		topicServiceImp.AnadirTopic(tsstopic);
		Long id = tsstopic.getId();

		assertNotNull(topicServiceImp.findTopicById(id));
		assertTrue(topicServiceImp.existeById(id));

	}

	@Test
	void AgregarUnTemaFalla() {

		assertNull(topicServiceImp.AnadirTopic(null));

	}

	@Test
	void editarunTema() {

		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		topicServiceImp.AnadirTopic(tsstopic);

		Long id = tsstopic.getId();
		TsscTopic encontrado = topicServiceImp.findTopicById(id);
		topicServiceImp.ActualizarTopic(encontrado, "Miguel", "Un tema");

		assertEquals("Miguel", encontrado.getName());
		assertEquals("Un tema", encontrado.getDescription());

	}

	@Test
	void editarunTemaNoexiste() {

		TsscTopic tsstopic = new TsscTopic();
		tsstopic.setDefaultGroups(4);
		tsstopic.setDefaultSprints(4);
		tsstopic.setDescription("El mejor tema");


		Long id = tsstopic.getId();

		
		assertNull(topicServiceImp.ActualizarTopic(tsstopic, "Miguel", "Un tema"));
		assertFalse(topicServiceImp.existeById(id));
	}
	
	

}
