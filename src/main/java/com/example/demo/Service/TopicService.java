package com.example.demo.Service;


import com.example.demo.Model.TsscStory;
import com.example.demo.Model.TsscTopic;

public interface TopicService {
	
	public TsscTopic AnadirTopic(TsscTopic topic);
	public TsscTopic ActualizarTopic(TsscTopic topic, String name, String Description );
	public TsscTopic findTopicById(Long id); 
	public boolean existeById(Long id);
	public Iterable<TsscTopic> findAlll();

	public void eliminarTopic(TsscTopic story);

}
