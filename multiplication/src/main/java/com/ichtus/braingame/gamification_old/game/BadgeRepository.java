package com.ichtus.braingame.gamification_old.game;

import com.ichtus.braingame.gamification_old.game.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Handles data operations with BadgeCards
 * */
public interface BadgeRepository extends CrudRepository<BadgeCard, Long> {

    /**
     * Retrieves all BadgeCards for a given user.
     *
     * @param userId the id of the user to look for BadgeCards
     * @return the list of BadgeCards, ordered by most recent first.
     * */
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(Long userId);
}
