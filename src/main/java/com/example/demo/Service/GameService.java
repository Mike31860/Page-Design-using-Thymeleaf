package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Model.TsscGame;
import com.example.demo.Model.TsscStory;
import com.example.demo.Model.TsscTopic;


public interface GameService {

	
	
	public TsscGame ActualizarGame(TsscGame game, int Grupos, String name);
	public TsscGame AnadirGameConTema(TsscGame gameOne, long id) ;
	public TsscGame AnadirGameSinTema(TsscGame gameOne);
	public TsscGame AnadirGameJuego2(TsscGame gameOne, long id) ;
	public TsscGame findGameById(Long id);  
	public boolean existbyId(Long id);
	public TsscStory agregarStory(TsscGame game, TsscStory Story);
	public Iterable<TsscGame> findAlll();
	public void eliminarGame(TsscGame juego);
	
	
	
}
