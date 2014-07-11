package sawers.gregory.musicmaker;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import sawers.gregory.musicmaker.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class SavedLyricListFragment extends ListFragment implements AbsListView.OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<String> lyricList;

    private SharedPreferences sharedPrefs;



    /**
     * The fragment's ListView/GridView.
     */
    private ListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ArrayAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static SavedLyricListFragment newInstance(String param1, String param2) {
        SavedLyricListFragment fragment = new SavedLyricListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SavedLyricListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }

        sharedPrefs = getActivity().getSharedPreferences(getString(R.string.lyrics), Context.MODE_PRIVATE);


        String[] files = getActivity().fileList();

        lyricList = new ArrayList<String>();
        for(int i=0; i<files.length;i++)
            lyricList.add(files[i]);

        // Log.d("First saved lyric", lyricList[0]);
        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, lyricList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mListView = (ListView) inflater.inflate(R.layout.saved_lyrics_list_view, container, false);

        mListView.setAdapter(mAdapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                DialogFragment deleteLyricsDialog = DeleteSaveLyricsDialog.newInstance(position);
                deleteLyricsDialog.show(getFragmentManager(), "selectKey");
                return true;
            }
        });

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        return mListView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        String fileToOpen = lyricList.get(position);
        byte[] byteInput;
        String lyrics;

        try {
            FileInputStream input = getActivity().openFileInput(fileToOpen);
            byteInput = new byte[input.available()];
            input.read(byteInput);

           lyrics = new String(byteInput);

            sharedPrefs.edit()
                    .putString(getString(R.string.lyrics_text), lyrics)
                    .apply();

            sharedPrefs.edit()
                    .putString("Lyrics File Name", fileToOpen)
                    .apply();

            Intent intent = new Intent(getActivity(), LyricWriter.class);
            intent.addFlags(69);
            startActivity(intent);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        super.onListItemClick(l, v, position, id);
    }




    //TODO: Dynamically Update the screen after a new element is added
    public void addElement(String name){
        lyricList.add(name);
        mAdapter.notifyDataSetChanged();
        mListView.invalidate();

    }

    public void deleteElement(int position){
        String fileToRmv = lyricList.get(position);
        File dir = getActivity().getFilesDir();
        File file = new File(dir, fileToRmv);

        file.delete();

        lyricList.remove(position);
        mAdapter.notifyDataSetChanged();
        mListView.invalidate();
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
