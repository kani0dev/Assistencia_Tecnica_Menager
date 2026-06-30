package Kani0dev.ATM.Model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Entity
@Table(name = "technicians")
@SuperBuilder
public class TechnicianUser extends User {
}
