package fr.ecoride.backend.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FirebaseInitializer {

    private static Firestore firestore;

    public static synchronized Firestore initialize() throws Exception {
        if (firestore != null) return firestore;

        try {
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options;
                String firebaseConfig = System.getenv("FIREBASE_CONFIG");

                if (firebaseConfig != null) {
                    System.out.println("✅ FIREBASE_CONFIG detected. Using Heroku environment variable.");
                    InputStream serviceAccount = new ByteArrayInputStream(firebaseConfig.getBytes(StandardCharsets.UTF_8));

                    options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .build();
                } else {
                    System.out.println("⚠️ FIREBASE_CONFIG not set. Using local credentials file.");
                    String localCredentialsPath = "src/main/resources/ecoride-nosql-firebase-adminsdk-fbsvc-0865113869.json";

                    File file = new File(localCredentialsPath);
                    if (!file.exists()) {
                        System.err.println("❌ Local Firebase credentials file not found: " + localCredentialsPath);
                        throw new FileNotFoundException("Firebase credentials file not found: " + localCredentialsPath);
                    }

                    FileInputStream serviceAccount = new FileInputStream(file);

                    options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .build();
                }

                FirebaseApp.initializeApp(options);
                System.out.println("✅ FirebaseApp initialized successfully.");
            }

            firestore = FirestoreClient.getFirestore();
            System.out.println("✅ Firestore instance created.");
            return firestore;

        } catch (Exception e) {
            System.err.println("❌ Error initializing Firebase: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw to fail fast if needed
        }
    }



    public static Firestore getFirestore() {
        return firestore;
    }
}
