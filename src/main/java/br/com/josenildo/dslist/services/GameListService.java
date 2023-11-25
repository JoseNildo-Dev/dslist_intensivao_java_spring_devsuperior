package br.com.josenildo.dslist.services;

import br.com.josenildo.dslist.dto.GameListDTO;
import br.com.josenildo.dslist.entities.GameList;
import br.com.josenildo.dslist.projections.GameMinProjections;
import br.com.josenildo.dslist.repositories.GameListRepository;
import br.com.josenildo.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findall() {
        List<GameList> result = gameListRepository.findAll();
        return  result.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {

        List<GameMinProjections> list = gameRepository.searchByList(listId);

        GameMinProjections obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }

    }
}








