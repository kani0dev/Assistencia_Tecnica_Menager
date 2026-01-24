package Kani0dev.ATM.Model.User;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Data
@SuperBuilder
public abstract class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private @Column(name = "user_id") long id;

    @Column(nullable = false
    )
    private String name;

    @Column(nullable = false)
    private String password;

    private String telefone;
    private Boolean isActive;
}
