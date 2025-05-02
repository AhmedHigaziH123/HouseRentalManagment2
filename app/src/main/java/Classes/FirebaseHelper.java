package Classes;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseHelper {
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public FirebaseHelper() {
        this.db = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
    }


    public void addUser(User user) {
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = auth.getCurrentUser().getUid();
                    SaveUserData(userId, user);
                }

            }
        });


    }

    public void SaveUserData(String userId, User user) {
        db.collection("Users").document(userId).set(user);

    }
    public void addHouse(House house){
        db.collection("Houses").add(house);
    }
    public void  addRequest(Request request){
        db.collection("Requests").add(request);
    }


}


