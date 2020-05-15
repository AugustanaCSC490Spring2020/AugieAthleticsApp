package edu.augustanacsc490spring2020.augieathletics.data.user;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User {

    private FirebaseUser firebaseUser;
    private ArrayList<String> favoriteSportsList;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public User(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
        favoriteSportsList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(firebaseUser.getEmail().toString().replace('.',','));
    }

    public void addFavoriteSport(String sport) {
        favoriteSportsList.add(sport);
        databaseReference.setValue(sport);
    }
    public void setUpDatabaseReference() {

        databaseReference.child(firebaseUser.getEmail().toString().replace('.',','))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            favoriteSportsList.add(child.getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("TAG2: ELECTRIC BOOGALOO", "Failed to read value.", error.toException());
                    }
                });
    }
    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public ArrayList<String> getFavoriteSportsList() {
        return favoriteSportsList;
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
