package br.com.josenildo.dslist.services;

import br.com.josenildo.dslist.dto.GameDTO;
import br.com.josenildo.dslist.dto.GameMinDTO;
import br.com.josenildo.dslist.entities.Game;
import br.com.josenildo.dslist.projections.GameMinProjections;
import br.com.josenildo.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game result = gameRepository.findById(id).get();
        GameDTO dto = new GameDTO(result);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream().map(x -> new GameMinDTO(x)).toList();

    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) {
        List<GameMinProjections> result = gameRepository.searchByList(listId);
        return  result.stream().map(x -> new GameMinDTO(x)).toList();
    }
}





