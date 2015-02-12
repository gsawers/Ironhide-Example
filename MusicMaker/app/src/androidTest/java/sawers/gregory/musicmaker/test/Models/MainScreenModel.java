package sawers.gregory.musicmaker.test.Models;



import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.ListAdapter;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import sawers.gregory.musicmaker.MenuItem;
import sawers.gregory.musicmaker.R;

/**
 * Created by Gregory.Sawers on 7/16/2014.
 */
public class MainScreenModel extends PageObject {

    public Clickable<MainScreenModel> HomeButton = new Clickable<MainScreenModel>(MainScreenModel.class,android.R.id.home);

    public ListAdapter<MainScreenModel> MainList = new ListAdapter<MainScreenModel>(MainScreenModel.class,MenuItem.class, R.id.home_list);

    public static Clickable<MainScreenModel> lyricImage = new Clickable<MainScreenModel>(MainScreenModel.class, R.id.icon);

    public ListAdapter<MainScreenModel> MainDrawerList = new ListAdapter<MainScreenModel>(MainScreenModel.class,String.class, R.id.navigation_drawer);

    public static Clickable<MainScreenModel> drawerItem = new Clickable<MainScreenModel>(MainScreenModel.class, R.id.nav_text_item);


}
