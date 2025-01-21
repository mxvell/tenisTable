package entities;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "Match")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "player1",nullable = false)
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "player2",nullable = false)
    private Player player2;
    @ManyToOne
    @JoinColumn(name = "winner",nullable = false)
    private Player winner;

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}
