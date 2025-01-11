package entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Matches")
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
    @JoinColumn(name = "Player1",nullable = false)
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "Player2",nullable = false)
    private Player player2;
    @ManyToOne
    @JoinColumn(name = "Winner",nullable = false)
    private Player winner;


}
