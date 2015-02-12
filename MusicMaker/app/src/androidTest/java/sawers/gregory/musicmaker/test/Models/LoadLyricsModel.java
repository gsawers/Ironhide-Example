package sawers.gregory.musicmaker.test.Models;



import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.ListAdapter;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.TextField;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import sawers.gregory.musicmaker.R;


public class LoadLyricsModel extends PageObject {

    public ListAdapter<LoadLyricsModel> lyricsList = new ListAdapter<LoadLyricsModel>(LoadLyricsModel.class, String.class, R.id.saved_lyrics_list);

    public static Clickable<LoadLyricsModel> lyricsListItem = new Clickable<LoadLyricsModel>(LoadLyricsModel.class, R.id.load_lyric_text_item);

    public TextField<LoadLyricsModel> lyricNameText = new TextField<LoadLyricsModel>(LoadLyricsModel.class, R.id.create_lyric_edit_text);

    public Clickable<LoadLyricsModel> loadHomeButton = new Clickable<LoadLyricsModel>(LoadLyricsModel.class, android.R.id.home);

    public Clickable<LoadLyricsModel> newLyricButton = new Clickable<LoadLyricsModel>(LoadLyricsModel.class, R.id.new_lyrics);

    public Clickable<LoadLyricsModel> confirmLyricsButton = new Clickable<LoadLyricsModel>(LoadLyricsModel.class, android.R.id.button1);
}
