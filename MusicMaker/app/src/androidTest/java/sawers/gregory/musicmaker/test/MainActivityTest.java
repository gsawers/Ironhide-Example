package sawers.gregory.musicmaker.test;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;


import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;

import org.junit.Test;

import sawers.gregory.musicmaker.MainActivity;
import sawers.gregory.musicmaker.test.Models.LoadLyricsModel;
import sawers.gregory.musicmaker.test.Models.LyricWriterModel;
import sawers.gregory.musicmaker.test.Models.MainScreenModel;

/**
 * Created by Gregory.Sawers on 7/16/2014.
 */
public class MainActivityTest extends TestFixture<MainActivity> {



    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    @Test
    public void testStartUp(){

        mainScreen
                .HomeButton.isDisplayed()
                .MainList.getItemAtPosition(0).checkChildView(MainScreenModel.lyricImage, ViewMatchers.isDisplayed())
                .MainList.getItemAtPosition(1).isDisplayed()
                .MainList.getItemAtPosition(2).checkChildView(MainScreenModel.lyricImage, ViewMatchers.isDisplayed());

    }

    @Test
    public void testClickOnNavDrawerOption0(){

        mainScreen
                .HomeButton.click()
                .MainDrawerList.getItemAtPosition(0).performOnChildView(MainScreenModel.drawerItem, ViewActions.click());

    }
    @Test
    public void testClickOnNavDrawerOption1(){

        mainScreen
                .HomeButton.click()
                .MainDrawerList.getItemAtPosition(1).performOnChildView(MainScreenModel.drawerItem,ViewActions.click());
    }
    @Test
    public void testNavigateToLyricWriterWriteAndSaveLyrics(){
        mainScreen
                .MainList.getItemAtPosition(0).goesTo(LyricWriterModel.class).click()
                .editText.clearText()
                .editText.typeText("What the land of the free? Whoever told you that is your enemy.")
                .saveLyrics.click()
                .saveDialogText.typeText("Know Your Enemy")
                .saveButton.click();
    }
    @Test
    public void testClickLoadLyricsCreateLyricsWriteLyricsAndSave(){

        mainScreen
                .MainList.getItemAtPosition(2).goesTo(LoadLyricsModel.class).click()
                .newLyricButton.click()
                .lyricNameText.clearText()
                .lyricNameText.typeText("Technologic")
                .confirmLyricsButton.click()
                .lyricsList.getItemAtPosition(2).performOnChildView(LoadLyricsModel.lyricsListItem, ViewActions.click());
        lyricWriter
                .editText.clearText()
                .editText.typeText("Buy it, use it, break it, fix it,\n" +
                "Trash it, change it, mail - upgrade it,\n" +
                "Charge it, point it, zoom it, press it,\n" +
                "Snap it, work it, quick - erase it,\n" +
                "Write it, cut it, paste it, save it,\n" +
                "Load it, check it, quick - rewrite it,\n" )
                .saveLyrics.click();


    }

    @Test
    public void testClickLoadLyricsCreateLyricsWriteLyricsAndSaveDummy(){
    /**
     * Dummy test to eat up the could not launch intent error.
     */
    }
}
