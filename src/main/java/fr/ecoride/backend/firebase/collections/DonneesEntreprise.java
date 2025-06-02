package fr.ecoride.backend.firebase.collections;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import fr.ecoride.backend.dto.donneesentreprise.DonneesEntrepriseResponseDTO;
import fr.ecoride.backend.firebase.FirebaseInitializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DonneesEntreprise {

    public List<DonneesEntrepriseResponseDTO> getAllDonneesEntreprise() throws Exception {
        List<DonneesEntrepriseResponseDTO> donneesList = new ArrayList<>();

        FirebaseInitializer.initialize();
        Firestore db = FirebaseInitializer.getFirestore();

        CollectionReference collection = db.collection("donneesEntreprise");
        ApiFuture<QuerySnapshot> querySnapshot = collection.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        for (QueryDocumentSnapshot doc : documents) {
            // Suppose que les documents ont les champs "libelle" et "valeur"
            String libelle = doc.getString("libelle");
            String valeur = doc.getString("valeur");

            DonneesEntrepriseResponseDTO dto = new DonneesEntrepriseResponseDTO(libelle, valeur);
            donneesList.add(dto);
        }

        return donneesList;
    }
}
