package ar.com.zsoft.client.api.service;

import ar.com.zsoft.client.api.entity.Document;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DocumentService {

    public Document findOneDocument(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("documents").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Document documentDTO;
        if(document.exists()){
            documentDTO = document.toObject(Document.class);
            return documentDTO;
        }
        return null;
    }

    public String create(Document document) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("documents").document(document.getName()).set(document);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String update(Document document) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("documents").document(document.getName()).set(document);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String delete(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("documents").document(id).delete();
        return "Successfully deleted " + id;
    }

}
