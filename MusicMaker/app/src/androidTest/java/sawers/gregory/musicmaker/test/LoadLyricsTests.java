package sawers.gregory.musicmaker.test;

import android.test.ActivityInstrumentationTestCase2;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;

import org.junit.Test;

import sawers.gregory.musicmaker.SavedLyricsActivity;
import sawers.gregory.musicmaker.test.Models.LoadLyricsModel;
import sawers.gregory.musicmaker.test.Models.LyricWriterModel;
import sawers.gregory.musicmaker.test.Models.MainScreenModel;

/**
 * Created by Gregory.Sawers on 7/17/2014.
 */
public class LoadLyricsTests extends TestFixture<SavedLyricsActivity> {


    public LoadLyricsTests() {
        super(SavedLyricsActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }
    @Test
    public void testOpenLyrics(){

        loadModel
                .lyricsList.getItemAtPosition(2).goesTo(LyricWriterModel.class).click()
                .editText.clearText()
                .editText.typeText("I'm sorry baby, you were the sun and moon to me. I'll never get over you. You'll never get over me.")
                .saveLyrics.click()
                .saveDialogText.typeText("Sun & Moon")
                .saveButton.click();
    }
    @Test
    public void testCreateNewLyrics(){

        loadModel
                .newLyricButton.click()
                .lyricNameText.clearText()
                .lyricNameText.typeText("YYZ")
                .confirmLyricsButton.click();
    }
}
