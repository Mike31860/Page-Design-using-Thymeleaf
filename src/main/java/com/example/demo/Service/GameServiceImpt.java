package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.TsscGame;
import com.example.demo.Model.TsscStory;
import com.example.demo.Model.TsscTopic;
import com.example.demo.Repository.GameRepository;
import com.example.demo.Repository.TopicRepository;

@Service
public class GameServiceImpt implements GameService {

	@Autowired
	private GameRepository game;
	@Autowired
	private TopicServiceImpt repo;

	@Autowired
	public GameServiceImpt(GameRepository game, TopicServiceImpt repo) {
		super();
		this.game = game;
		this.repo = repo;
	}

	@Override
	public TsscGame AnadirGameConTema(TsscGame gameOne, long ids) {
		if (gameOne.getNGroups() > 0 && gameOne.getNSprints() > 0) {
			TsscTopic encontrado = repo.findTopicById(ids);
			if (encontrado != null) {

				gameOne.setTsscTopic(encontrado);
				game.save(gameOne);
				return gameOne;
			}

			else {

			//	game.save(gameOne);
				return null;
			}

		}

		return null;
	}

	@Override
	public TsscGame AnadirGameSinTema(TsscGame gameOne) {
		if (gameOne!=null&&gameOne.getNGroups() > 0 && gameOne.getNSprints() > 0) {

			game.save(gameOne);
			return gameOne;
		}

		return null;

	}

	@Override
	public TsscGame ActualizarGame(TsscGame gameOne, int Grupos, String name) {

		if (gameOne != null && Grupos > 0 && name != null && !name.equals("")&&game.existsById(gameOne.getId())) {
			gameOne.setName(name);
			gameOne.setNGroups(Grupos);
			game.save(gameOne);
			return gameOne;
		}

		return null;
	}

	@Override
	public TsscGame AnadirGameJuego2(TsscGame gameOne, long id) {

		if (gameOne!=null&&gameOne.getNGroups() > 0 && gameOne.getNSprints() > 0) {
			TsscTopic encontrado = repo.findTopicById(id);

			if (encontrado != null) {

				gameOne.setTsscStories(encontrado.getTsscStories());
				gameOne.setTsscTimecontrol(encontrado.getTsscCronograma());
				gameOne.setTsscTopic(encontrado);
				game.save(gameOne);
				return gameOne;

			}

		}
		return null;
	}

	@Override
	public TsscGame findGameById(Long id) {

		try {
			TsscGame encontrado = game.findById(id).get();
			return encontrado;

		}

		catch (Exception a) {
			return null;
		}

	}
	

	
	
	
	@Override
	public boolean existbyId(Long id)
	{
		return game.existsById(id);
	}

	@Override
	public TsscStory agregarStory(TsscGame game, TsscStory Story) {
		// TODO Auto-generated method stub
		return game.addTsscStory(Story);
	}

	@Override
	public Iterable<TsscGame> findAlll() {
		// TODO Auto-generated method stub
		return game.findAll();
	}

	@Override
	public void eliminarGame(TsscGame juego) {
		game.delete(juego);
		
		
	}

}
