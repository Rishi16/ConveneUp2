package in.co.convene.www.conveneup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    SessionManager session;
    MainActivity mainActivity;
    TextView tvnUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("rishi", "in main before setting view");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("rishi", "in main after setting view");
        session = new SessionManager(getApplicationContext());
        //Set the fragment initially

        HashMap<String, String> user = session.getUserDetails();
        String Username = user.get(SessionManager.KEY_NAME);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        tvnUsername = (TextView)header.findViewById(R.id.tvnUsername);

        tvnUsername.setText(Username);
        // tvnUsername.setText(Username);

                Toast.makeText(getApplicationContext(), "Welcome back, " + Username, Toast.LENGTH_SHORT).show();
        AddFragment fragment = new AddFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        mainActivity =this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.add_group) {

            AddFragment fragment = new AddFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.change_user) {
            Log.d("rishi","in nav drawer choice");
            UpdateFragment fragment = new UpdateFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
            Log.d("rishi","end in nav drawer choice");

        }else if (id == R.id.participant_details) {
            Intent intent = new Intent(this, PartActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.your_registrations) {
            Intent intent = new Intent(this, ViewActivity.class);
            intent.putExtra("Choice", "Registrant");
            this.startActivity(intent);

        } else if (id == R.id.all_registrations) {
            Intent intent = new Intent(this, ViewActivity.class);
            intent.putExtra("Choice", "All");
            this.startActivity(intent);

//            startActivity(new Intent(this, ViewActivity.class));

        } else if (id == R.id.aboutus) {
            AboutusFragment fragment = new AboutusFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.logout) {
            session.logoutUser(mainActivity);
        }
        else if (id == R.id.teams_registered) {
            Toast.makeText(getApplicationContext(), "Coming soon...", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.teams_unregistered) {
            Toast.makeText(getApplicationContext(), "Coming soon...", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
