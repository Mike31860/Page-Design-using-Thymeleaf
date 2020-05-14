package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.TsscStory;
import com.example.demo.Model.TsscTimecontrol;
import com.example.demo.Model.TsscTopic;
import com.example.demo.Repository.TopicRepository;

@Service
public class TopicServiceImpt implements TopicService {

	@Autowired
	private TopicRepository repositorio;

	@Autowired
	public TopicServiceImpt(TopicRepository repositorio) {
		super();
		this.repositorio = repositorio;
	}

	@Override
	public TsscTopic AnadirTopic(TsscTopic topicOne) {

		TsscTopic as = null;

		if (topicOne!=null&&topicOne.getDefaultSprints() > 0 && topicOne.getDefaultGroups() > 0) {
			repositorio.save(topicOne);
			as = topicOne;
		}

		return as;
	}

	@Override
	public TsscTopic ActualizarTopic(TsscTopic topic, String name, String Description) {

		if (topic != null && name != null && !name.equals("") && Description != null && !Description.equals("")&&repositorio.existsById(topic.getId())) {
			topic.setName(name);
			topic.setDescription(Description);
			repositorio.save(topic);
			return topic;
		}

		return null;
	}

	@Override
	public TsscTopic findTopicById(Long id) {
		try {
			TsscTopic encontrado = repositorio.findById(id).get();
			return encontrado;

		}

		catch (Exception a) {
			return null;
		}

	}

	@Override
	public boolean existeById(Long id) {
		return repositorio.existsById(id);
		 

	}
	
	public List<TsscStory> getTsscStory(long id)
	{
		try {
			TsscTopic encontrado = repositorio.findById(id).get();
			return encontrado.getTsscStories();

		}

		catch (Exception a) {
			return null;
		}
	}
	
	public List<TsscTimecontrol> getTsscTimecontrol(long id)
	{
		try {
			TsscTopic encontrado = repositorio.findById(id).get();
			return encontrado.getTsscCronograma();

		}

		catch (Exception a) {
			return null;
		}
	}
	
	
	@Override
	public Iterable<TsscTopic> findAlll() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public void eliminarTopic(TsscTopic tema) {
		repositorio.delete(tema);
		
	}
	
	
	
	

}
