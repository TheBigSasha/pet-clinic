package org.quarkus.samples.petclinic.auth;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import io.quarkus.runtime.StartupEvent;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Arrays;

@Singleton
public class KeycloakStartup {
    @Inject
    Keycloak keycloak;

    String realm = "quarkus";


    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        System.out.println("Loading users into keycloak");

        // Configure demo users

        var changeme = new CredentialRepresentation();
        changeme.setType(CredentialRepresentation.PASSWORD);
        changeme.setValue("changeme");
        changeme.setTemporary(false); //TODO: should be true for production

        var testUser1 = new UserRepresentation();
        testUser1.setEmail("john.appleseed@me.com");
        testUser1.setUsername("JohnAppleseed");
        testUser1.setFirstName("John");
        testUser1.setLastName("Appleseed");
        testUser1.setEnabled(true);
        testUser1.setRealmRoles(Arrays.asList(new String[]{"user", "admin"}));
        testUser1.setCredentials(Arrays.asList(new CredentialRepresentation[]{changeme}));

        var testUser2 = new UserRepresentation();
        testUser2.setEmail("jebediah.kerman@fake.website");
        testUser2.setUsername("JebediahKerman");
        testUser2.setFirstName("Jebediah");
        testUser2.setLastName("Kerman");
        testUser2.setEnabled(true);
        testUser2.setRealmRoles(Arrays.asList(new String[]{"user", "admin"}));
        testUser2.setCredentials(Arrays.asList(new CredentialRepresentation[]{changeme}));

        // post the users
        try{
            keycloak.realm(realm).users().create(testUser1);
            keycloak.realm(realm).users().create(testUser2);
        } catch (Exception e){
            System.out.println("Error creating users: " + e.getMessage());
        }



        // Configure the realm's settings
        var realmSettings  = new RealmRepresentation();
        realmSettings.setRegistrationAllowed(true);
        realmSettings.setVerifyEmail(false); //TODO: set to true if enabling an email server
        realmSettings.setResetPasswordAllowed(true);

        // post the realm settings
        keycloak.realm(realm).update(realmSettings);
    }

}
