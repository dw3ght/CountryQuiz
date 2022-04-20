package edu.uga.cs.countryquiz;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A simple {@link Fragment} that displays the welcome prompt for
 * the application.
 * This class also contains an onclick listener button allowing the
 * user to view a list of the following countries.
 * Use the {@link PromptFragment} factory method to
 * create an instance of this fragment.
 */
public class PromptFragment extends Fragment {

    //initializing button to view the list of countries
    private Button countryButton;
    Button buttonStartQuiz;
    Button buttonviewQuizResults;
    private static final int REQUEST_CODE_QUIZ = 1;

    public PromptFragment() {
        // Required empty public constructor
    }

    /**
     * Initializing program's ativity.
     *
     * @param savedInstanceState Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    /**
     * Inflating the XML layout for the Fragment in this callback.
     *
     * @param inflater           LayoutInflater
     * @param container          ViewGroup
     * @param savedInstanceState Bundle
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prompt, container, false);

        //initializing the view country button's view
        buttonStartQuiz = (Button) view.findViewById(R.id.button_start_quiz);

            buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                /**
                 *  Action to perform when user clicks view countries button in
                 *  this fragment.
                 * @param   v   View
                 */
                public void onClick(View view) {
                    FragmentManager fragmentManager = getParentFragmentManager();
                    QuizStartFragment QuizStartFragment = new QuizStartFragment();
                    //FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView1, QuizStartFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("yes")
                            .commit();
                    setTargetFragment(QuizStartFragment, REQUEST_CODE_QUIZ);
                    getTargetFragment();
                    getTargetRequestCode();
                }

            });

        buttonviewQuizResults = (Button) view.findViewById(R.id.buttonQuizResults);

        buttonviewQuizResults.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             *  Action to perform when user clicks view countries button in
             *  this fragment.
             * @param   v   View
             */
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                DataListFragment dataListFragment = new DataListFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView1, dataListFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("yes")
                        .commit();
                setTargetFragment(dataListFragment, 1);
                getTargetFragment();
                getTargetRequestCode();
            }

        });


        //returning the view
        return view;
    }

}