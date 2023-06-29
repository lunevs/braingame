package com.ichtus.braingame.multiplication.challenge;

import com.ichtus.braingame.multiplication.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "challenge_attempt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeAttempt {

    @Id
    @SequenceGenerator(name="challenge_attempt_generator", sequenceName="challenge_attempt_seq", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="challenge_attempt_generator")
    private Long id;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "USER_ID", referencedColumnName="ID")
//    private User user;

    private Long user_id;

    @Column(name = "factora")
    private int factorA;

    @Column(name = "factorb")
    private int factorB;

    @Column(name = "result_attempt")
    private int resultAttempt;

    private boolean correct;
}
