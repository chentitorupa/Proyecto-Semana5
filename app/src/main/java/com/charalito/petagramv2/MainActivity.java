package com.charalito.petagramv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.charalito.petagramv2.activities.AboutActivity;
import com.charalito.petagramv2.activities.ContactoActivity;
import com.charalito.petagramv2.R;
import com.charalito.petagramv2.activities.RatingActivity;
import com.charalito.petagramv2.adapter.Adaptador;
import com.charalito.petagramv2.adapter.PageAdapter;
import com.charalito.petagramv2.fragment.ListOfPetsFragment;
import com.charalito.petagramv2.fragment.PerfilPetFragment;
import com.charalito.petagramv2.pojo.Mascota;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creo el action bar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);


        if (toolbar != null){
            setSupportActionBar(toolbar);
        }

        setUpViewPager();

    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ListOfPetsFragment());
        fragments.add(new PerfilPetFragment());

        return fragments;
    }

    private void setUpViewPager(){

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_pets);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_perfil);

    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.mRaiting){

            Intent intent = new Intent(this, RatingActivity.class);

            startActivity(intent);
            return true;
        }



        if(id == R.id.mContacto){
            IngresaraFormularioContacto(null);
            return true;
        }

        if(id == R.id.mAbout){
            Intent intent3 = new Intent(this, AboutActivity.class);
            startActivity(intent3);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void IngresaraFormularioContacto(View view){
        Intent intent = new Intent(this, ContactoActivity.class);
        startActivity(intent);
    }



}