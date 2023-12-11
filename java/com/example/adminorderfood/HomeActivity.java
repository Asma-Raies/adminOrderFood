package com.example.adminorderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout layoutD;
    BottomNavigationView bottomNavigationView ;
    FragmentManager fragmentManager ;
    FloatingActionButton fab ;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //fab=findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        layoutD = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,layoutD,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        layoutD.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);
       /* bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId=item.getItemId();
                if(itemId==R.id.nav_cart){
                    openFragment(new CartFragment());

                }else  if(itemId==R.id.nav_menu){
                    openFragment(new MenuFragment());

                }else  if(itemId==R.id.nav_orders){
                    openFragment(new OrderFragment());

                }
                return false;
            }
        });*/


        fragmentManager =getSupportFragmentManager();
        openFragment(new MenuFragment());



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
         if(itemId==R.id.nav_menu){
            openFragment(new MenuFragment());

        }else if (itemId==R.id.logOut){
             Intent i = new Intent(getApplicationContext(),MainActivity.class);
             startActivity(i);
             finish();
         }
        layoutD.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public  void onBackPressed(){
        if( layoutD.isDrawerOpen(GravityCompat.START)){
            layoutD.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();

        }
    }
    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_profil) {
            //  Toast.makeText(this, "this is action !", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this , ProfilActivity.class);
            startActivity(i);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}