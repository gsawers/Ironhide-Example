package sawers.gregory.musicmaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ChangeKeyDialog#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ChangeKeyDialog extends DialogFragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ChangeKeyDialogListener mListener;

    private SoundPool soundPool;

    private View view;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangeKeyDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangeKeyDialog newInstance(String param1, String param2) {
        ChangeKeyDialog fragment = new ChangeKeyDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public ChangeKeyDialog() {
        // Required empty public constructor
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater  = getActivity().getLayoutInflater();

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        view = inflater.inflate(R.layout.fragment_change_key_dialog, null);

        builder.setView(view)
                .setTitle("Pick Song Key")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onChangeKeyPositiveClick(ChangeKeyDialog.this);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ChangeKeyDialog.this.getDialog().cancel();
                    }
                })
                .setItems(R.array.list_of_major_keys, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        playSound(which);
                    }
                });

        return builder.create();

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ChangeKeyDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    public void playSound(int position){

        int soundId;

        Context context = getActivity();
        AudioManager audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
        float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = actualVolume/maxVolume;

        MediaPlayer mPlayer;
//        mPlayer.start();




        switch(position){

            case 0:
                mPlayer = MediaPlayer.create(context, R.raw.aflat);
                break;
            case 1:
                mPlayer = MediaPlayer.create(context, R.raw.anatural);
                break;
            case 2:
                mPlayer = MediaPlayer.create(context, R.raw.bflat);
                break;
            case 3:
                mPlayer = MediaPlayer.create(context, R.raw.bnatural);
                break;
            case 4:
                mPlayer = MediaPlayer.create(context, R.raw.cnatural);
                break;
            case 5:
                mPlayer = MediaPlayer.create(context, R.raw.csharp);
                break;
            case 6:
                mPlayer = MediaPlayer.create(context, R.raw.dnatural);
                break;
            case 7:
                mPlayer = MediaPlayer.create(context, R.raw.eflat);
                break;
            case 8:
                mPlayer = MediaPlayer.create(context, R.raw.enaturallow);
                break;
            case 9:
                mPlayer = MediaPlayer.create(context, R.raw.fnatural);
                break;
            case 10:
                mPlayer = MediaPlayer.create(context, R.raw.fsharp);
                break;
            case 11:
                mPlayer = MediaPlayer.create(context, R.raw.gnatural);
                break;

            default:
                mPlayer = new MediaPlayer();
                break;

        }

        mPlayer.start();
    }



    public interface ChangeKeyDialogListener{

        public void onChangeKeyPositiveClick(ChangeKeyDialog dialog);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
