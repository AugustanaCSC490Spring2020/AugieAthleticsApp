package edu.augustanacsc490spring2020.augieathletics.data.user;

import android.util.Log;


import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class User {

    private FirebaseUser firebaseUser;
    private Set<String> favoriteSportsSet;
    private DatabaseReference userdbRef;
    private String child = "Favorite Sports List";

    public User(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
        favoriteSportsSet = new HashSet<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        userdbRef = firebaseDatabase.getReference(firebaseUser.getEmail().replace('.',','));
        setUpDatabaseReference();
    }

    public void addFavoriteSport(String sport) {
        favoriteSportsSet.add(sport);

        userdbRef.child(child).setValue(getFavoriteSportsAsList());
    }

    public void setUpDatabaseReference() {
        userdbRef.child(child).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            favoriteSportsSet.add(child.getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("TAG2: ELECTRIC BOOGALOO", "Failed to read value.", error.toException());
                    }
                });
    }

    public void removeFavoriteSport(String sportName) {
        favoriteSportsSet.remove(sportName);
        ArrayList<String> newList = getFavoriteSportsAsList();
        newList.remove(sportName);
        userdbRef.child(child).setValue(newList);
    }

    public String toString() {
        return firebaseUser.getDisplayName() + ": " + favoriteSportsSet.toString();
    }

    public ArrayList<String> getFavoriteSportsAsList() {
        ArrayList<String> favoriteTeamsAsList = new ArrayList<>();
        favoriteTeamsAsList.addAll(favoriteSportsSet);
        return favoriteTeamsAsList;
    }

}
