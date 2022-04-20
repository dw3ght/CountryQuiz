package edu.uga.cs.countryquiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} that displays the list of countries.
 * This class also contains an onclick listener allowing the user
 * to interact with the list.
 * Use the {@link QuizStartFragment} factory method to
 * create an instance of this fragment.
 */
public class QuizStartFragment extends Fragment {

    public static final String EXTRA_SCORE = "extra score";

    public static String[] countries = new String[5];
    public static String kountry1 = "";
    public static  String kountry2 = "";
    public static  String kountry3 = "";
    public static  String kountry4 = "";
    public static String kountry5 = "";
    public static String kountry6 = "";
    public static String kountry1Ans = "";
    public static  String kountry2Ans = "";
    public static  String kountry3Ans = "";
    public static  String kountry4Ans = "";
    public static String kountry5Ans = "";
    public static String kountry6Ans = "";


    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton radiobutton1;
    private RadioButton radiobutton2;
    private RadioButton radiobutton3;
    private Button buttonComfirmNext;

    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;



    private List<Question> questionList;

    //initilizing variable for selected item on the list
    public String selectedItem;

    public QuizStartFragment() {
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

        try {
            readQuestion();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_start, container, false);

        textViewQuestion = view.findViewById(R.id.text_view_question);
        textViewScore = view.findViewById(R.id.text_view_score);
        textViewQuestionCount = view.findViewById(R.id.text_view_question_count);
        rbGroup = view.findViewById(R.id.radio_group);
        radiobutton1 = view.findViewById(R.id.radio_button1);
        radiobutton2 = view.findViewById(R.id.radio_button2);
        radiobutton3 = view.findViewById(R.id.radio_button3);
        buttonComfirmNext = view.findViewById(R.id.button_comfirm_next);

        QuizDbHelper dbHelper = new QuizDbHelper(getContext());
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);
        RadioButton radioButtonSelected = view.findViewById(rbGroup.getCheckedRadioButtonId());


        showNextQuestion();

        buttonComfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered){
                    if (radiobutton1.isChecked() || radiobutton2.isChecked() || radiobutton3.isChecked()) {
                        checkAnswer();
                    }
                    else {
                        Toast.makeText(getActivity(),"Please select an answer.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
        //initializing array for the list list of countries



        //return the view
        return view;
    }

    private void showNextQuestion() {
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            radiobutton1.setText(currentQuestion.getOption1());
            radiobutton2.setText(currentQuestion.getOption2());
            radiobutton3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonComfirmNext.setText("Submit");
        }
        else {
            finishQuiz();
        }
    }



    public void readQuestion() throws IOException {

        InputStream is = getContext().getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        List<String[]> questionSamples = new ArrayList<>();
        String line = "";
        int i = 0;

        int counter = 0;
        int counter1 = new Random().nextInt(43) + 2;
        int counter2 = new Random().nextInt(43) + 2;
        int counter3 = new Random().nextInt(43) + 2;
        int counter4 =   new Random().nextInt(43) + 2;
        int counter5 =   new Random().nextInt(43) + 2;
        int counter6 =   new Random().nextInt(43) + 2;

//        while ( i != 5) {
            while ((line = reader.readLine()) != null) {
                counter++;
                if (counter == counter1 ) {
                    String[] tokens = line.split(",");
                    setKountry1(tokens[1]);
                    setKountry1Ans(tokens[0]);
                }
                else if (counter == counter2){
                    String[] tokens = line.split(",");
                    //countries[i] = tokens[1];
                    setKountry2(tokens[1]);
                    setKountry2Ans(tokens[0]);
                }
                else if (counter == counter3){
                    String[] tokens = line.split(",");
                    //countries[i] = tokens[1];
                    setKountry3(tokens[1]);
                    setKountry3Ans(tokens[0]);
                }
                else if (counter4 == counter){
                    String[] tokens = line.split(",");
                    setKountry4(tokens[1]);
                    setKountry4Ans(tokens[0]);
                }
                else if (counter5 == counter){
                    String[] tokens = line.split(",");
                    setKountry5(tokens[1]);
                    setKountry5Ans(tokens[0]);
                }
                else if (counter6 == counter){
                    String[] tokens = line.split(",");
                    setKountry6(tokens[1]);
                    setKountry6Ans(tokens[0]);
                }
            }
 }

    public static void setCountries(String[] countries) {
        QuizStartFragment.countries = countries;
    }

    public static String[] getCountries() {
        return countries;
    }

    public static void setKountry1(String kountry1) {
        QuizStartFragment.kountry1 = kountry1;
    }

    public static String getKountry1() {
        return kountry1;
    }

    public static void setKountry2(String kountry2) {
        QuizStartFragment.kountry2 = kountry2;
    }

    public static String getKountry2() {
        return kountry2;
    }

    public static void setKountry3(String kountry3) {
        QuizStartFragment.kountry3 = kountry3;
    }

    public static String getKountry3() {
        return kountry3;
    }

    public static void setKountry4(String kountry4) {
        QuizStartFragment.kountry4 = kountry4;
    }

    public static String getKountry4() {
        return kountry4;
    }

    public static void setKountry5(String kountry5) {
        QuizStartFragment.kountry5 = kountry5;
    }

    public static String getKountry5() {
        return kountry5;
    }

    public static void setKountry6(String kountry6) {
        QuizStartFragment.kountry6 = kountry6;
    }

    public static String getKountry6() {
        return kountry6;
    }

    public static void setKountry1Ans(String kountry1Ans) {
        QuizStartFragment.kountry1Ans = kountry1Ans;
    }

    public static void setKountry2Ans(String kountry2Ans) {
        QuizStartFragment.kountry2Ans = kountry2Ans;
    }

    public static void setKountry3Ans(String kountry3Ans) {
        QuizStartFragment.kountry3Ans = kountry3Ans;
    }

    public static void setKountry4Ans(String kountry4Ans) {
        QuizStartFragment.kountry4Ans = kountry4Ans;
    }

    public static void setKountry6Ans(String kountry6Ans) {
        QuizStartFragment.kountry6Ans = kountry6Ans;
    }

    public static void setKountry5Ans(String kountry5Ans) {
        QuizStartFragment.kountry5Ans = kountry5Ans;
    }

    public static String getKountry1Ans() {
        return kountry1Ans;
    }

    public static String getKountry2Ans() {
        return kountry2Ans;
    }

    public static String getKountry3Ans() {
        return kountry3Ans;
    }

    public static String getKountry4Ans() {
        return kountry4Ans;
    }

    public static String getKountry5Ans() {
        return kountry5Ans;
    }

    public static String getKountry6Ans() {
        return kountry6Ans;
    }

    private void checkAnswer() {
        answered = true;

        RadioButton radioButtonSelected = getActivity().findViewById(rbGroup.getCheckedRadioButtonId());
         int answerNr = rbGroup.indexOfChild(radioButtonSelected);

        if (radioButtonSelected.getText().equals(currentQuestion.getAnswerNr())) {
            score++;
            textViewScore.setText("Score: " + score);
        }
        System.out.println("selected: " + radioButtonSelected.getText());
        System.out.println("actual " + currentQuestion.getAnswerNr());
        questionfinished();
    }

    public void questionfinished() {
        if (questionCounter < questionCountTotal) {
            buttonComfirmNext.setText("Next");
        }
        else{
            buttonComfirmNext.setText("Finish");
        }
    }

    private void finishQuiz() {
        QuizDbHelper dbHelper = new QuizDbHelper(getContext());
        dbHelper.storeResult(score);
        Bundle bundle = new Bundle();
        bundle.putInt("score", score);
        ResultFragment resultFragment  = new ResultFragment();
        resultFragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView1, resultFragment)
                .setReorderingAllowed(true)
                .addToBackStack("yes")
                .commit();

    }

}
