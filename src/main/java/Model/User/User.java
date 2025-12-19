package Model.User;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;



@NoArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "Users")

public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String password;
    private String telefone;
    private Boolean isActive;
}
