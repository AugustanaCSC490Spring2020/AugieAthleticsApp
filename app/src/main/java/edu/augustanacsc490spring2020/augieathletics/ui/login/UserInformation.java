package edu.augustanacsc490spring2020.augieathletics.ui.login;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.augustanacsc490spring2020.augieathletics.R;


public class UserInformation extends AppCompatActivity {
    FirebaseAuth authFirebase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);
        //UserInfo();

    }
    public void UserInfo(){

        FirebaseUser cUser = authFirebase.getCurrentUser();
        String cUserName=  cUser.getDisplayName();
        String cUserEmail = cUser.getEmail();
        System.out.println("The User Name: "+cUserName);
        TextView UserName = findViewById(R.id.userName);
        TextView UserEmail = findViewById(R.id.userEmail);
        UserName.setText(cUserName);
        UserEmail.setText(cUserEmail);

    }
}
