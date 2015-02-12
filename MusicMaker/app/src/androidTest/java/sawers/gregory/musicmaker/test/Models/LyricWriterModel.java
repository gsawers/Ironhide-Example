package sawers.gregory.musicmaker.test.Models;

import android.widget.ListView;
import android.widget.TextView;


import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.DynamicListAdapter;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.TextField;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import sawers.gregory.musicmaker.R;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Gregory.Sawers on 7/16/2014.
 */
public class LyricWriterModel extends PageObject {

    public TextField<LyricWriterModel> editText = new TextField<LyricWriterModel>(LyricWriterModel.class, R.id.editText);

    public TextField<LyricWriterModel> saveDialogText = new TextField<LyricWriterModel>(LyricWriterModel.class,R.id.save_lyric_edit_text);

    public Clickable<LyricWriterModel> saveLyrics = new Clickable<LyricWriterModel>(LyricWriterModel.class, R.id.save_lyrics);

    public Clickable<LyricWriterModel> changeKey = new Clickable<LyricWriterModel>(LyricWriterModel.class, R.id.key_change);

    public Clickable<LyricWriterModel> saveButton = new Clickable<LyricWriterModel>(LyricWriterModel.class, allOf(withId(android.R.id.button1)));

    public Clickable<LyricWriterModel> homeButton = new Clickable<LyricWriterModel>(LyricWriterModel.class, android.R.id.home);

    public DynamicListAdapter<LyricWriterModel> dialogList = new DynamicListAdapter<LyricWriterModel>(TextView.class, ListView.class).goesTo(LyricWriterModel.class);

}
