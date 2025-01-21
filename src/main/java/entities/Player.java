package entities;

import lombok.*;
import jakarta.persistence.*;


@Entity
@Table(name = "Player")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, name = "name")
    private String name;

    public Player(String name) {
        this.name = name;
    }
}
