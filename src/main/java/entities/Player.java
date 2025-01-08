package entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Players")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, name = "Name")
    private String name;
}
