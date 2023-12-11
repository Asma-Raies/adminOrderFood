package com.example.adminorderfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class SignUpActivity extends AppCompatActivity {
    EditText name, lastName, phone, password, email;
    Button signup;
    FirebaseAuth mAuth;
    ProgressBar progress ;
    FirebaseDatabase data ;
    DatabaseReference reference ;
    /*  @Override
      public void onStart() {
          super.onStart();
          // Check if user is signed in (non-null) and update UI accordingly.
          FirebaseUser currentUser = mAuth.getCurrentUser();
          if (currentUser != null) {
              Intent i = new Intent(getApplicationContext(), MainActivity.class);
              startActivity(i);
              finish();
          }
      }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase before using any Firebase services
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        signup = findViewById(R.id.btnSignUp);
        progress=findViewById(R.id.progress);


        mAuth = FirebaseAuth.getInstance();

       /* signup.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress.setVisibility(View.VISIBLE);

               /* String nom = name.getText().toString();
                String prenom = lastName.getText().toString();
                String phon = phone.getText().toString();
                String pass = password.getText().toString();
                String mail = email.getText().toString();


                mAuth.createUserWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progress.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(SignUpActivity.this, "Authentication success.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(SignUpActivity.this, "Authentication failed: " + errorMessage,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });*/
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Affiche la barre de progression
                progress.setVisibility(View.VISIBLE);
                // Appelle la méthode signUp() pour le processus d'inscription

                signUp();
            }
        });
    }
    private void signUp() {
        progress.setVisibility(View.VISIBLE);
        // Récupération des données des champs de saisie
        String mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String firstName = name.getText().toString().trim();
        String lastname = lastName.getText().toString().trim();

        String phoneNumber = phone.getText().toString().trim();


        // Vérification si tous les champs sont remplis
        if (mail.isEmpty() || pass.isEmpty() || firstName.isEmpty() || lastname.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
            // Masque la barre de progression
            return;
        }

        // Vérification de la longueur du mot de passe
        if (pass.length() < 6) {
            Toast.makeText(SignUpActivity.this, "The password must contain at least 6 characters", Toast.LENGTH_SHORT).show();
            // Masque la barre de progression
            return;
        }

        // Création de l'utilisateur avec Firebase Authentification
        mAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Masque la barre de progression
                        progress.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            // Enregistre les informations supplémentaires dans la base de données en temps réel
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("users")
                                        .child(user.getUid());


                                userReference.child("email").setValue(mail);
                                userReference.child("firstName").setValue(firstName);
                                userReference.child("lastName").setValue(lastname);
                                userReference.child("phoneNumber").setValue(phoneNumber);

                            }

                            // Affiche un message de succès
                            Toast.makeText(SignUpActivity.this, "Account created with success", Toast.LENGTH_SHORT).show();
                            // Redirige vers l'interface de connexion
                            Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // Vérifier si l'exception est due à une adresse e-mail non valide
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(SignUpActivity.this, "Invalid e-mail address", Toast.LENGTH_SHORT).show();
                            } else {
                                // Affiche un message en cas d'échec d'authentification
                                Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
