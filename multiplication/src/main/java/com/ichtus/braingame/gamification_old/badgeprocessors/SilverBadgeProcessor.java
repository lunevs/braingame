package com.ichtus.braingame.gamification_old.badgeprocessors;

import com.ichtus.braingame.gamification_old.challenge.ChallengeSolvedDTO;
import com.ichtus.braingame.gamification_old.game.domain.BadgeType;
import com.ichtus.braingame.gamification_old.game.domain.ScoreCard;

import java.util.List;
import java.util.Optional;

public class SilverBadgeProcessor implements BadgeProcessor {
    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedDTO solved) {
        return currentScore > 150 ? Optional.of(BadgeType.SILVER) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.SILVER;
    }
}
