package sk.kasv.ferencak.SportReservation.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // Definovanie vzťahu One-to-Many: Jeden používateľ môže mať veľa rezervácií.
    // `mappedBy = "user"` hovorí Hibernate, že vzťah je už spravovaný na strane `Reservation` entity v poli `user`.
    // `cascade = CascadeType.ALL` znamená, že ak zmažeme používateľa, zmažú sa aj všetky jeho rezervácie.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Ignorujeme toto pole pri serializácii do JSON, aby sme predišli nekonečnej slučke.
    private Set<Reservation> reservations;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}