package com.ichtus.braingame.multiplication_old.challenge;

import com.ichtus.braingame.multiplication_old.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "challenge_attempts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeAttempt {

    @Id
//    @SequenceGenerator(name="challenge_attempts_generator", sequenceName="challenge_attempts_seq", allocationSize = 10)
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="challenge_attempts_generator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //*@ManyToOne(fetch = FetchType.EAGER)
    //*@JoinColumn(name = "USER_ID", referencedColumnName="ID")
    @ManyToOne
    private User user;

//    private Long user_id;

    //*@Column(name = "factora")
    private int factorA;

    //*@Column(name = "factorb")
    private int factorB;

    //*@Column(name = "result_attempt")
    private int resultAttempt;

    private boolean correct;
}
