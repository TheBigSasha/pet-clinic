package org.quarkus.samples.petclinic.auth;

import io.quarkus.elytron.security.common.BcryptUtil;
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
    public String username;

    @Email
    public String email;

    @Password
    /* "With the default option, passwords are stored and hashed
    with bcrypt under the Modular Crypt Format (MCF).
    While using MCF, the hashing algorithm, iteration count,
    and salt are stored as a part of the hashed value.
    As such, we do not need dedicated columns to keep them."
        - https://quarkus.io/guides/security-jpa#password-storage-and-hashing
    */
    public String password;

    @Roles
    public String role;

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


    /**
     * Adds a new user to the database
     * @param username the username
     * @param password the unencrypted password (it will be encrypted with bcrypt)
     * @param role the comma-separated roles
     * @param email the email
     */
    public static void add(String username, String password, String role, String email) {
        User user = new User();
        user.username = username;
        user.password = BcryptUtil.bcryptHash(password);
        user.role = role;
        user.email = email;
        System.out.println("Adding user: " + user);
        user.persist();
    }
}
