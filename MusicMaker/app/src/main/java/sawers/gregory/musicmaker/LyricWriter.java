package sawers.gregory.musicmaker;

import android.app.Activity;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import sawers.gregory.musicmaker.R;

public class LyricWriter extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, SaveLyricsFragment.SaveDialogListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private SaveLyricsFragment mLyricsFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private SharedPreferences sharedPrefs;

    private String lyrics;
    private EditText editText;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric_writer);


        fm = getFragmentManager();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                fm.findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout_writer));

        //TODO: Change over from shared preferences to loading from whatever file was selected
        sharedPrefs = getSharedPreferences(getString(R.string.lyrics), Context.MODE_PRIVATE);


        editText = (EditText) findViewById(R.id.editText);
        if(getIntent().getFlags() != 69) {
            lyrics = sharedPrefs.getString(getString(R.string.lyrics_text), "");
            editText.setText(lyrics);
        }



    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
         if (position == 0) {
             Intent intent = new Intent(this, MainActivity.class);
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
   public void onDestroy(){

       lyrics = editText.getText().toString();
       sharedPrefs.edit()
               .putString(getString(R.string.lyrics_text), lyrics)
               .apply();

       super.onDestroy();
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.lyric_writer, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.save_lyrics){
           DialogFragment lyricsFragment = new SaveLyricsFragment();
           lyricsFragment.show(fm, "lyrics");

        }
        return super.onOptionsItemSelected(item);
    }

    public void onDialogPositiveClick(SaveLyricsFragment dialog, String filename){

        FileOutputStream outputStream;

        lyrics = editText.getText().toString();

        try{
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(lyrics.getBytes());
            outputStream.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



}
