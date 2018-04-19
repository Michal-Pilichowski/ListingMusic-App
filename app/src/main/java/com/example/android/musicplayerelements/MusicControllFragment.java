package com.example.android.musicplayerelements;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
/*
Fragment controlling music control buttons and changing their appearance on click.
 */
public class MusicControllFragment extends Fragment {
    //Variable indicating if music is stopped
    private boolean isStopped;

    public MusicControllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_music_controll, container, false);

        //At the beginning nothing is playing
        isStopped = true;

        //Adding method for start/stop button, stopping and resuming playing and changing icon accordingly
        Button startStop = rootView.findViewById(R.id.button_start_stop);
        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Changing the button appearance
                if (isStopped){
                    Button start = (Button) view;
                    start.setBackground(getResources().getDrawable(R.drawable.button_stop_src));
                    isStopped = false;
                } else {
                    Button start = (Button) view;
                    start.setBackground(getResources().getDrawable(R.drawable.button_play_src));
                    isStopped = true;
                }

            }
        });

        return rootView;
    }

}
