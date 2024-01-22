package az.unitech.auth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", schema = "security")
public class User extends Base<Long> {

    @Column(length = 50, nullable = false, unique = true)
    private String pin;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$",
            message = "Password must have at least 1 uppercase letter, 1 number, 1 symbol, and be at least 6 characters long")
    @Column(length = 512)
    private String password;


    @Column(name = "first_name", length = 100, nullable = false)
    private String name;

    @Column(name = "last_name", length = 150)
    private String surname;

    @Column(name = "father_name", length = 100)
    private String fatherName;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile")
    private String mobile;

    @Email
    private String email;

    @Column(name = "note")
    private String note;

    @Column(name = "status", length = 1)
    private Character status = '1';

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public User(Long id) {
        this.setId(id);
    }


}
