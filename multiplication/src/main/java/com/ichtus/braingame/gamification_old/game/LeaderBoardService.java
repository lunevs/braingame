package com.ichtus.braingame.gamification_old.game;

import com.ichtus.braingame.gamification_old.game.domain.LeaderBoardRow;

import java.util.List;

public interface LeaderBoardService {
    /**
     * @return the current leader board ranked from high to low score
     * */
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
