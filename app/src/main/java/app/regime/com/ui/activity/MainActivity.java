package app.regime.com.ui.activity;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import app.regime.com.R;
import app.regime.com.helper.BottomNavigationViewHelper;
import app.regime.com.ui.FragmentContact;
import app.regime.com.ui.fragment.AnalysisFragment;
import app.regime.com.ui.fragment.FullDayMealsFragment;
import app.regime.com.ui.fragment.FullMealDetailFragment;
import app.regime.com.ui.fragment.HomeFragment;
import app.regime.com.ui.fragment.LocationFragment;
import app.regime.com.ui.fragment.MenuFragment;
import app.regime.com.ui.fragment.OfferDayFragment;
import app.regime.com.ui.fragment.OfferDealsFragment;
import app.regime.com.ui.fragment.RegisterFragment;
import app.regime.com.ui.fragment.SignInFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener, FragmentContact {
    Fragment fragment;

    String currentfragment = "";
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
// replace the FrameLayout with new Fragment
        String name = getIntent().getExtras().getString("Fragment");
        if (name != null) {
            ChangeFragment(name, null);
        } else {
            Fragment workFragment = new HomeFragment();
            fragmentTransaction.replace(R.id.main_fragment_container, workFragment);
            fragmentTransaction.commit(); // save the changes
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentfragment.equals("FullMealDetailFragment")) {
                ChangeFragment("FullDayMealsFragment", null);
            } else if (currentfragment.equals("FullDayMealsFragment")) {
                ChangeFragment("OfferDayFragment", null);
            } else if (currentfragment.equals("OfferDayFragment")) {
                ChangeFragment("OfferDealsFragment", null);
            } else if (currentfragment.equals("OfferDealsFragment")) {
                ChangeFragment("LocationFragment", null);
            } else {
                super.onBackPressed();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        OpenFragment(item.getItemId());
       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void OpenFragment(int id) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        if (id == R.id.action_home) {
            fragment = new HomeFragment();

        } else if (id == R.id.action_menu) {
            fragment = new MenuFragment();

        } else if (id == R.id.action_analysis) {
            fragment = new AnalysisFragment();

        } else if (id == R.id.action_more) {
            fragment = new LocationFragment(this);
        }

        fragmentTransaction.replace(R.id.main_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void ChangeFragment(String name, Bundle bundle) {
        currentfragment = name;
        if (name.equals("RegisterFragment")) {
            fragment = new RegisterFragment(this);
        } else if (name.equals("LocationFragment")) {
            fragment = new LocationFragment(this);
        } else if (name.equals("OfferDealsFragment")) {
            fragment = new OfferDealsFragment(this);
        } else if (name.equals("OfferDayFragment")) {
            fragment = new OfferDayFragment(this);
        } else if (name.equals("FullDayMealsFragment")) {
            fragment = new FullDayMealsFragment(this);
        } else if (name.equals("FullMealDetailFragment")) {
            fragment = new FullMealDetailFragment(this);
        }else if (name.equals("SigninFragment")) {
            fragment = new SignInFragment(this);
        }
        else if (name.equals("HomeFragment")) {
            fragment = new HomeFragment();
        }
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, fragment);
        fragmentTransaction.commit();
/*
        fragmentTransaction.addToBackStack(null);
*/
    }

}
