package sawers.gregory.musicmaker.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import android.widget.TextView;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;

import org.junit.Test;

import sawers.gregory.musicmaker.LyricWriter;
import sawers.gregory.musicmaker.test.Models.LyricWriterModel;

import static org.hamcrest.Matchers.allOf;

/**
 * Created by Gregory.Sawers on 7/16/2014.
 */
public class LyricWriterTest extends TestFixture<LyricWriter>{


    public LyricWriterTest() {
        super (LyricWriter.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    @Test
    public void test1StartUp(){
       lyricWriter
                .editText.isDisplayed()
                .saveLyrics.isDisplayed()
                .homeButton.isDisplayed()
                .changeKey.isDisplayed();
    }
    @Test
    public void testWriteLyrics(){

        lyricWriter
                .editText.clearText()
                .editText.typeText("I bomb atomically, Socrates' philosophies\n" +
                "And hypotheses can't define how I be droppin' these\n" +
                "Mockeries, lyrically perform armed robbery")
                .editText.withText("I bomb atomically, Socrates' philosophies\n" +
                "And hypotheses can't define how I be droppin' these\n" +
                "Mockeries, lyrically perform armed robbery");
    }

    @Test
    public void testWriteAndSaveLyrics(){

        lyricWriter
                .editText.clearText()
                .editText.typeText("They broke the walls we guarded, but we don't care about it. We'll finish what we started")
                .editText.withText("They broke the walls we guarded, but we don't care about it. We'll finish what we started")
                .saveLyrics.click();
    }
    @Test
    public void testChangeKey() throws Exception{

        lyricWriter
                .changeKey.click()
                .dialogList.getItemAt(1).click()
                .changeKey.pause();
    }




}
