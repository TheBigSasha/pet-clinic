package org.quarkus.samples.petclinic.auth;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class Startup {
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        System.out.println("Creating test users!");
        User.add("john", "changeme", "admin", "john.appleseed@example.com");
        User.add("jane", "changeme", "admin", "jane.appleseed@example.com");
    }
}
