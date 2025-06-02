package fr.ecoride.backend.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FirebaseInitializer {

    private static Firestore firestore;

    public static synchronized Firestore initialize() throws Exception {
        if (firestore == null) {
            if (FirebaseApp.getApps().isEmpty()) {
                String firebaseConfig = System.getenv("FIREBASE_CONFIG");
                if (firebaseConfig == null) {
                    throw new IllegalStateException("FIREBASE_CONFIG env var is not set");
                }

                InputStream serviceAccount = new ByteArrayInputStream(firebaseConfig.getBytes(StandardCharsets.UTF_8));

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
            }

            firestore = FirestoreClient.getFirestore();
        }

        return firestore;
    }



    public static Firestore getFirestore() {
        return firestore;
    }
}
