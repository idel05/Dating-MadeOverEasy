package com.example.idel2101.datingmadeovereasy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = ProfileActivity.class.getSimpleName();
    public String FirstName;
    public String LastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "about to setContentView");
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Log.i(TAG, "R.id.drawer_layout is " + R.id.drawer_layout);
        Log.i(TAG, "drawer is " + drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
    public String lastNameEditText() {
        EditText lastNameEdit = (EditText) findViewById(R.id.lastNameEdit);
        LastName = lastNameEdit.getText().toString();
        Log.i(TAG, "lastName is " + LastName);
        return LastName;

    }
    public String firstNameEditText() {
        EditText firstNameEdit = (EditText) findViewById(R.id.firstNameEdit);
        FirstName = firstNameEdit.getText().toString();
        return FirstName;
    }
    public void firstNameView() {
        TextView firstNameView = (TextView) findViewById(R.id.text_view);

    }

    public void lastNameView() {
        TextView lastNameView = (TextView) findViewById(R.id.lastNameView);

    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;

    }
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;

    }

    public void submitName() {
        final Button submitName = (Button) findViewById(R.id.SubmitName);

    }

   // public void onSubmitNameClick(View v) {
       // Log.i(TAG, "lastNameEditText returns" + lastNameEditText());
       // Log.i(TAG, "onSubmitNameClick is working");
   // }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.i(TAG, "id is " + id);

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
        int id = item.getItemId();

        if (id == R.id.egg_type) {
            setContentView(R.layout.eggtype);
        }

        Button submitButton = (Button) findViewById(R.id.SubmitName);
        submitButton.setOnClickListener(new SubmitButtonClass());

        Log.i(TAG, "R.id.drawer_layout is " + R.id.drawer_layout);
        //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Log.i(TAG, "drawer is " + drawer);


        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class SubmitButtonClass implements View.OnClickListener {
        public void onClick(View v) {
            firstNameEditText();
            lastNameEditText();
            Log.i(TAG, "LastName is " + LastName);
            Log.i(TAG, "FirstName is " + FirstName);
            HashMap person = new HashMap();
            person.put("firstname", FirstName);
            person.put("lastname", LastName);
            //Backendless.Data.of("Person").save(person);
            //download LastName or FirstName to Backendless
            Backendless.Data.of( "Person" ).save( person,
                    new AsyncCallback<Map>()
                    {
                        @Override
                        public void handleResponse( Map response )
                        {
                         Log.i(TAG, "save successful");
                        }

                        @Override
                        public void handleFault( BackendlessFault fault )
                        {
                         Log.i(TAG, "save error");
                        }
                    });

        }


        }
    }
