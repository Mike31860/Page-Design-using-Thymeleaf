package com.example.demo.Service;

import java.math.BigDecimal;

import com.example.demo.Model.TsscGame;
import com.example.demo.Model.TsscStory;
import com.example.demo.Model.TsscTopic;

public interface StoryService {
	
	public TsscStory AnadirStory(TsscStory Story, long id);
	public TsscStory ActualizarStory(TsscStory Story, String prority, String descripption);
	public TsscStory findStoryById(Long id);
	public boolean existbyId(Long id);
	
	public Iterable<TsscStory> findAlll();
	public void eliminarStory(TsscStory story);
	


}
