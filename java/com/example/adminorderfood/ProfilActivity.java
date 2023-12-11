package com.example.adminorderfood;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilActivity extends AppCompatActivity {

    TextView ProfilLastName , ProfilEmail , PorfilPhone , ProfilPass ;
    TextView textName ,profilName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        profilName = findViewById(R.id.name);
        ProfilLastName = findViewById(R.id.lastname);
        ProfilEmail = findViewById(R.id.Email);
        PorfilPhone = findViewById(R.id.phone);

        textName = findViewById(R.id.textName);


        // Récupération de l'ID de l'utilisateur connecté
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
       // Toast.makeText(this, userId, Toast.LENGTH_SHORT).show();
        // Référence à la base de données pour l'utilisateur actuel
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("users").child(userId);

// Ajoutez un écouteur pour lire les données de l'utilisateur à partir de la base de données
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Vérifie si l'utilisateur existe dans la base de données
                if (snapshot.exists()) {
                    // Récupération des données de l'utilisateur
                    String mail = snapshot.child("email").getValue(String.class);

                    String firstName = snapshot.child("firstName").getValue(String.class);
                    String lastName = snapshot.child("lastName").getValue(String.class);
                    String userPhoneNumber = snapshot.child("phoneNumber").getValue(String.class);
                    //PorfilPhone.setText(userPhoneNumber);
                    ProfilEmail.setText(mail);
                    profilName.setText(firstName);
                    ProfilLastName.setText(lastName);
                    PorfilPhone.setText(userPhoneNumber);
                    textName.setText("Hello " + firstName);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }}