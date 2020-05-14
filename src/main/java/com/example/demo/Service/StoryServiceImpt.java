package com.example.demo.Service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.TsscGame;
import com.example.demo.Model.TsscStory;
import com.example.demo.Model.TsscTopic;
import com.example.demo.Repository.GameRepository;
import com.example.demo.Repository.StoryRepository;

@Service
public class StoryServiceImpt implements StoryService {

	@Autowired
	private StoryRepository story;
	@Autowired
	private GameService game;
	

	@Autowired
	public StoryServiceImpt(StoryRepository story, GameService game) {
		super();
		this.story = story;
		this.game = game;
	}

	@Override
	public TsscStory AnadirStory(TsscStory StoryOne, long id) {

		if (StoryOne != null && StoryOne.getBusinessValue().compareTo(BigDecimal.valueOf(0)) == 1
				&& StoryOne.getInitialSprint().compareTo(BigDecimal.valueOf(0)) == 1
				&& StoryOne.getPriority().compareTo(BigDecimal.valueOf(0)) == 1 && game.existbyId(id)) {
			TsscGame juego = game.findGameById(id);

			game.agregarStory(juego, StoryOne);
			story.save(StoryOne);
			return StoryOne;
		}

		return null;
	}

	@Override
	public TsscStory ActualizarStory(TsscStory StoryOne, String altDescripton, String Description) {

		if (StoryOne != null && altDescripton != null && !altDescripton.equals("") && Description != null
				&& !Description.equals("")) {
			StoryOne.setAltDescripton(altDescripton);
			StoryOne.setDescription(Description);
			story.save(StoryOne);
			return StoryOne;
		}

		return null;
	}
	
	
	@Override
	public TsscStory findStoryById(Long id) {
		if (story.findById(id).get() != null){
			 return story.findById(id).get();
		}
		return null;
	}
	

	@Override
	public boolean existbyId(Long id)
	{
		return story.existsById(id);
	}

	@Override
	public Iterable<TsscStory> findAlll() {
		// TODO Auto-generated method stub
		return story.findAll();
	}

	@Override
	public void eliminarStory(TsscStory storyE) {
		story.delete(storyE);
		
		
	}
}
