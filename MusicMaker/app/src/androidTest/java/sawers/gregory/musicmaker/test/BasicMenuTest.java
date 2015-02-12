//package sawers.gregory.musicmaker.test;
//
//
//import android.test.ActivityInstrumentationTestCase2;
//import android.widget.Button;
//
//import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Clickable;
//import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.ListAdapter;
//
//import sawers.gregory.musicmaker.MainActivity;
//import sawers.gregory.musicmaker.MenuItem;
//import sawers.gregory.musicmaker.NavigationDrawerFragment;
//import sawers.gregory.musicmaker.R;
//
//import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
//import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
//import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
//import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
//import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
//import static com.google.android.apps.common.testing.ui.espresso.contrib.DrawerActions.closeDrawer;
//import static com.google.android.apps.common.testing.ui.espresso.contrib.DrawerActions.openDrawer;
//import static com.google.android.apps.common.testing.ui.espresso.contrib.DrawerMatchers.isClosed;
//import static com.google.android.apps.common.testing.ui.espresso.contrib.DrawerMatchers.isOpen;
//import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
//import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
//import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.core.AllOf.allOf;
//import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsInstanceOf.instanceOf;
//
//
///**
// * Created by Gregory.Sawers on 7/14/2014.
// */
//public class BasicMenuTest extends ActivityInstrumentationTestCase2<MainActivity>{
//
//    public BasicMenuTest() {
//        super("sawers.gregory.musicmaker.MainActivity", MainActivity.class);
//    }
//
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        getActivity();
//    }
//
//    @Override
//    protected void tearDown() throws Exception {
//        super.tearDown();
//    }
//
//    public void testOpenAndCloseDrawer() {
//        // Drawer should not be open to start.
//
////        Clickable navDrawer = new Clickable(NavigationDrawerFragment.class, withId(R.id.drawer_layout));
////        navDrawer.isDisplayed();
////
////        Clickable homeButton = new Clickable(Button.class, withId(android.R.id.home));
////        homeButton.click();
////
////        ListAdapter navAdapter = new ListAdapter(NavigationDrawerFragment.class, String.class, withId(R.id.navigation_drawer));
////
////        homeButton.click();
//        onView(withId(R.id.drawer_layout)).check(matches(isClosed()));
//
//        openDrawer(R.id.drawer_layout);
//
//        // The drawer should now be open.
//        onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
//
//        closeDrawer(R.id.drawer_layout);
//
//        // Drawer should be closed again.
//        onView(withId(R.id.drawer_layout)).check(matches(isClosed()));
//    }
//
//    public void testClickLyricWriter(){
//
//        onData(allOf(is(instanceOf(MenuItem.class))))
//                .inAdapterView(withId(R.id.home_list))
//                .atPosition(0)
//                .perform(click());
//
//        onView(withId(R.id.lyric_writer)).check(matches(isDisplayed()));
//
//    }
//
//    public void testMainScreenNavDrawerOption1(){
//
//        openDrawer(R.id.drawer_layout);
//
//        onData(allOf(is(instanceOf(String.class))))
//                .inAdapterView(withId(R.id.navigation_drawer))
//                .atPosition(1)
//                .perform(click());
//
//        onView(withId(R.id.editText))
//                .check(matches(isDisplayed()));
//
//    }
//
//
//    //WHY YOU FUCKED UP TEST
//    public void testLyricWriterNavDrawerOption0() throws InterruptedException {
//
//        onData(allOf(is(instanceOf(MenuItem.class))))
//                .inAdapterView(withId(R.id.home_list))
//                .atPosition(0)
//                .perform(click());
//
//        onView(withId(R.id.lyric_writer)).check(matches(isDisplayed()));
//
//        onView(withId(android.R.id.home)) //Alternate openDrawer
//                .perform(click());
//        Thread.sleep(1000);
//
//        onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
//
//        onData(allOf(is(instanceOf(String.class))))
//                .inAdapterView(withId(R.id.navigation_drawer))
//                .atPosition(0)
//                .perform(click());
//
//        onView(withId(R.id.home_list))
//                .check(matches(isDisplayed()));
//        Thread.sleep(1000);
//
//    }
//
//    public void testLyricWriterXDummy(){
//
//    }
//    public void testWriteAndSaveLyrics(){
//
//        onData(allOf(is(instanceOf(MenuItem.class))))
//            .inAdapterView(withId(R.id.home_list))
//            .atPosition(0)
//            .perform(click());
//
//        onView(withId(R.id.editText))
//                .perform(click())
//                .perform(typeText("Everydays my birthday, 'cause bitches love cake."));
//        onView(withId(R.id.editText))
//                .check(matches(withText("Everydays my birthday, 'cause bitches love cake.")));
//
//        onView(withId(R.id.save_lyrics))
//                .perform(click());
//
//        onView(withId(R.id.save_lyric_edit_text)).check(matches(isDisplayed()));
//
//        onView(withId(R.id.save_lyric_edit_text))
//                .perform(click())
//                .perform(typeText("Decisions"));
//
//        onView(withId(R.id.save_lyric_edit_text)).check(matches(withText("Decisions")));
//
//        onView(withId(android.R.id.button1))
//                .perform(click());
//
//
//    }
//
//    public void testCreateNewLyrics(){
//
//        onData(allOf(is(instanceOf(MenuItem.class))))
//                .inAdapterView(withId(R.id.home_list))
//                .atPosition(2)
//                .perform(click());
//
//        onView(withId(R.id.saved_lyrics_list)).check(matches(isDisplayed()));
//
//        onView(withId(R.id.new_lyrics)).perform(click());
//
//        onView(withId(R.id.create_lyric_edit_text))
//                .check(matches(isDisplayed()))
//                .perform(click())
//                .perform(typeText("Young Blood"));
//
//        onView(withId(android.R.id.button1))
//                .perform(click());
//
//
//
//    }
//
//}
