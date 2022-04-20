package edu.uga.cs.countryquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    public static int finalScore;
    private TextView finalScoreView;
    private Button buttonviewQuizResults;
    Button buttonStartQuiz;
    Button buttonHomePage;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        View view = inflater.inflate(R.layout.fragment_result, container, false);

        Bundle extras = this.getArguments();
        if (extras != null)
            finalScore = extras.getInt("score");
        finalScoreView = view.findViewById(R.id.finalscore);
        finalScoreView.setText("Final Score: " + finalScore);

        buttonviewQuizResults = (Button) view.findViewById(R.id.viewQuizResults);

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

        buttonStartQuiz = (Button) view.findViewById(R.id.startNewQuiz);

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
                setTargetFragment(QuizStartFragment, 1);
                getTargetFragment();
                getTargetRequestCode();
            }

        });

        buttonHomePage = (Button) view.findViewById(R.id.homePage);

        buttonHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             *  Action to perform when user clicks view countries button in
             *  this fragment.
             * @param   v   View
             */
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                PromptFragment promptFragment = new PromptFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView1, promptFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("yes")
                        .commit();
                setTargetFragment(promptFragment, 1);
                getTargetFragment();
                getTargetRequestCode();
            }

        });


        return view;
    }





}