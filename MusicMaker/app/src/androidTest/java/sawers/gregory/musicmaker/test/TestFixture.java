package sawers.gregory.musicmaker.test;

import android.app.Activity;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;

import sawers.gregory.musicmaker.test.Models.LoadLyricsModel;
import sawers.gregory.musicmaker.test.Models.LyricWriterModel;
import sawers.gregory.musicmaker.test.Models.MainScreenModel;

/**
 * Created by Gregory.Sawers on 2/12/2015.
 */
public class TestFixture<T extends Activity> extends BaseInstrumentTestCase<T> {

    protected LoadLyricsModel loadModel = new LoadLyricsModel();
    protected MainScreenModel mainScreen = new MainScreenModel();
    protected LyricWriterModel lyricWriter = new LyricWriterModel();

    public TestFixture(Class<T> activity){
        super(activity);
    }


}
