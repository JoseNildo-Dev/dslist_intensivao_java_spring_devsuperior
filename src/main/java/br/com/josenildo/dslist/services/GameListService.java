package br.com.josenildo.dslist.services;

import br.com.josenildo.dslist.dto.GameListDTO;
import br.com.josenildo.dslist.entities.GameList;
import br.com.josenildo.dslist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findall() {
        List<GameList> result = gameListRepository.findAll();
        return  result.stream().map(x -> new GameListDTO(x)).toList();
    }

}
