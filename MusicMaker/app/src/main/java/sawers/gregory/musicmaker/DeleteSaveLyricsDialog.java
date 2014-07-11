package sawers.gregory.musicmaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ChangeKeyDialog#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class DeleteSaveLyricsDialog extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DeleteLyricDialogListener mListener;

    private SoundPool soundPool;

    private View view;

    private int clickPosition;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment ChangeKeyDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteSaveLyricsDialog newInstance(int position) {
        DeleteSaveLyricsDialog fragment = new DeleteSaveLyricsDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        return fragment;
    }
    public DeleteSaveLyricsDialog() {
        // Required empty public constructor
    }




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        clickPosition = getArguments().getInt(ARG_PARAM1, 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater  = getActivity().getLayoutInflater();


        view = inflater.inflate(R.layout.fragment_change_key_dialog, null);

        builder.setView(view)
                .setTitle("Delete Lyrics?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onDeleteLyricPositiveClick(DeleteSaveLyricsDialog.this, clickPosition);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteSaveLyricsDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DeleteLyricDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }




    public interface DeleteLyricDialogListener{

        public void onDeleteLyricPositiveClick(DeleteSaveLyricsDialog dialog, int position);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
