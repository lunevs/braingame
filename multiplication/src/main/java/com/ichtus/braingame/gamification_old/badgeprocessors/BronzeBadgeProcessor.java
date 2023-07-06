package com.ichtus.braingame.gamification_old.badgeprocessors;

import com.ichtus.braingame.gamification_old.challenge.ChallengeSolvedDTO;
import com.ichtus.braingame.gamification_old.game.domain.BadgeType;
import com.ichtus.braingame.gamification_old.game.domain.ScoreCard;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BronzeBadgeProcessor implements BadgeProcessor {

    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedDTO solved) {
        return currentScore > 50 ? Optional.of(BadgeType.BRONZE) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.BRONZE;
    }
}
