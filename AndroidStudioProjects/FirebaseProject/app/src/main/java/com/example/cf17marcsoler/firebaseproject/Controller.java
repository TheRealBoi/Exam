package com.example.cf17marcsoler.firebaseproject;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Controller extends AppCompatActivity implements InitialFragment.FragmentInterface{

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        mAuth = FirebaseAuth.getInstance();







    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void addUser(final String Email, String Pwd) {
        mAuth.createUserWithEmailAndPassword(Email, Pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("SignInGut", "createUserWithEmail:success");
                            Toast.makeText(Controller.this, "Success!",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();


                            Map<String, String> userMap = new HashMap<>();
                            userMap.put("email", user.getEmail());

                            FirebaseDatabase
                                    .getInstance()
                                    .getReference()
                                    .child("users")
                                    .child(user.getUid())
                                    .setValue(userMap);



                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SignInBad", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Controller.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void addToFirebase(String mail, String pwd) {

    }

    public void signIn(String email, String pwd) {
        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(Controller.this, user.toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Controller.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void updateUI(FirebaseUser user) {
        if(user == null) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = new InitialFragment();
            fm.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, "FRAGMENTSIGNUP")
                    .commit();
        }else {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = new SessionFragment();
            fm.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, "SESSIONFRAGMENT")
                    .commit();
        }
    }


}
