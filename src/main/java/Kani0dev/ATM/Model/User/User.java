package Kani0dev.ATM.Model.User;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Data
@Table(name = "users")
@SuperBuilder
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String password;

    private String telefone;
    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Role role;
}
