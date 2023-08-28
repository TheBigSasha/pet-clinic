package org.quarkus.samples.petclinic.auth;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Objects;


@Entity
@UserDefinition
@Table(name = "users")
public class User extends PanacheEntity {

    @Username
    private String username;

    @Email
    private String email;

    @Password
    /* "With the default option, passwords are stored and hashed
    with bcrypt under the Modular Crypt Format (MCF).
    While using MCF, the hashing algorithm, iteration count,
    and salt are stored as a part of the hashed value.
    As such, we do not need dedicated columns to keep them."
        - https://quarkus.io/guides/security-jpa#password-storage-and-hashing
    */
    private String password;

    @Roles
    private String role;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", id=" + id +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, role);
    }
}
