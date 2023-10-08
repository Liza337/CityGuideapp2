package com.example.cityguideapp.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cityguideapp.Common.LoginSignup.RetailerStartUpScreen;
import com.example.cityguideapp.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.cityguideapp.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.cityguideapp.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.cityguideapp.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.cityguideapp.HelperClasses.HomeAdapter.MostViewedAdpater;
import com.example.cityguideapp.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.example.cityguideapp.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;;
    RecyclerView.Adapter adapter;
    static final float END_SCALE = 0.7f;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board);

        featuredRecycler=findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);


        //Menu Hooks
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);


        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();
        naviagtionDrawer();
    }

    private void naviagtionDrawer(){
        //Naviagtion Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else
                  {
                      drawerLayout.openDrawer(GravityCompat.START);
                  }
            }
        });
        animateNavigationDrawer();
    }
    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    private void categoriesRecycler() {
        //All Gradients

        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.school_image, "Education","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.hospital_image, "HOSPITAL","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.restaurant_image, "Restaurant","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.shopping_image, "Shopping","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.transport_image, "Transport","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);
    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredLocations =new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.mcdonald_img,"McDonalds","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.city_1,"Edenrobe","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.city_2,"Sweets and Bakes","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));


        adapter= new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1= new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400,0xffaff600});

    }

    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.mcdonald_img, "McDonald's","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.city_2, "Edenrobe","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.city_1, "J.","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.restaurant_image, "Walmart","asbkd asudhlasn saudnas jasdjasl hisajdl asjdlnas"));
        adapter = new MostViewedAdpater(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);
        GradientDrawable gradient2= new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400,0xffaff600});

    }

//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return true;
//    }





    public void callRetailerScreens(View view){
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
    }

}