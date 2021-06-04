package org.redi.userservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date registrationDate;
    private String password;
    private Boolean locked = false;
    private Boolean enabled = false;

    public User(String firstName, String lastName, String email, Date registrationDate,
                String password, Boolean locked, Boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registrationDate = registrationDate;
        this.password = password;
        this.locked = locked;
        this.enabled = enabled;
    }
}
