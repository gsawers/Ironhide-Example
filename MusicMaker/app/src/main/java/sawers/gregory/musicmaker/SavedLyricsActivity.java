package sawers.gregory.musicmaker;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;

import java.io.FileOutputStream;


public class SavedLyricsActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, CreateNewLyricsDialog.CreateDialogListener
          , DeleteSaveLyricsDialog.DeleteLyricDialogListener, GooglePlayServicesClient.ConnectionCallbacks,
            GooglePlayServicesClient.OnConnectionFailedListener, LocationListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private FragmentManager fm;
    private LocationClient mLocationClient;
    private Location mCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_lyrics);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Load Lyrics");

        fm = getFragmentManager();

        mLocationClient = new LocationClient(this, this, this);

        if(GooglePlayServicesUtil.
                isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {

        }
        else{
            Log.d("Connection service", "Connection failed");
        }

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (position == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (position == 1){
            Intent intent = new Intent(this, LyricWriter.class);
            startActivity(intent);
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.saved_lyrics, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        else if(id == R.id.new_lyrics){
            DialogFragment createLyricsFragment = new CreateNewLyricsDialog();
            createLyricsFragment.show(fm, "createLyrics");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();


        mLocationClient.connect();
    }

    @Override
    protected void onStop() {
        mLocationClient.disconnect();
        super.onStop();
    }

    @Override
    public void onDialogPositiveClick(CreateNewLyricsDialog dialog, String fileName) {
        FileOutputStream outputStream;
        mCurrentLocation = mLocationClient.getLastLocation();
        mCurrentLocation = new Location("Verizon");

        if(mCurrentLocation != null) {
            mCurrentLocation.setAltitude(6723.0);
        }
        else{
            Log.d("Null location", "Shits null");
        }

        try{
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.close();

            SavedLyricListFragment listFragment = (SavedLyricListFragment)fm.findFragmentById(R.id.saved_lyrics_list);
            listFragment.addElement(fileName);
            if(fileName.equals("Rocky Mountain High") && mCurrentLocation.hasAltitude() && mCurrentLocation.getAltitude() > 5280){
                Intent intent = new Intent(this, EasterEggActivity.class);
                startActivity(intent);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void onDeleteLyricPositiveClick(DeleteSaveLyricsDialog dialog, int position){

        SavedLyricListFragment listFragment = (SavedLyricListFragment)fm.findFragmentById(R.id.saved_lyrics_list);
        listFragment.deleteElement(position);

    }

    @Override
    public void onConnected(Bundle dataBundle){
        Toast.makeText(this,"connected", Toast.LENGTH_SHORT).show();
        mCurrentLocation = mLocationClient.getLastLocation();
    }

    @Override
    public void onDisconnected() {
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {


          /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        ConnectionResult.RESOLUTION_REQUIRED);
                /*
                * Thrown if Google Play services canceled the original
                * PendingIntent
                */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
        }

    }

    @Override
    public void onLocationChanged(Location location){

        mCurrentLocation = location;

    }




}
