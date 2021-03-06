package sawers.gregory.musicmaker;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import sawers.gregory.musicmaker.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the {@link //Callbacks}
 * interface.
 */
public class MainListViewFragment extends ListFragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int mCurrentSelectedPosition = 0;


    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainListViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mListView = (ListView)inflater.inflate(R.layout.main_list_view, container, false);

        // Set the adapter
       mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               int itm = (Integer)parent.getItemAtPosition(position);
               selectItem(itm);
           }
       });

        String[] mainList = getResources().getStringArray(R.array.main_screen_list);
        String[] descriptions = getResources().getStringArray(R.array.main_screen_description);
        Integer[] images = {R.drawable.ic_action_edit,R.drawable.ic_action_play,R.drawable.ic_action_import_export};

       ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

       for(int i=0; i<mainList.length; i++){
           MenuItem nxtItm = new MenuItem(images[i], mainList[i], descriptions[i]);
           menuItems.add(nxtItm);
       }

        mAdapter = new CustomListAdapter(getActivity(),
                R.layout.menu_item_layout,menuItems);
        mListView.setAdapter(mAdapter);

        return mListView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (MainListListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        Intent intent;
        if (mListView != null) {
            mListView.setItemChecked(position, true);
        }

        if(position == 0){
            intent = new Intent(getActivity(),LyricWriter.class);
            intent = intent.addFlags(69);
            startActivity(intent);
        }

        else if(position == 2) {
            intent = new Intent(getActivity(), SavedLyricsActivity.class);
            startActivity(intent);
        }


    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        selectItem(position);
        super.onListItemClick(l, v, position, id);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }







}
