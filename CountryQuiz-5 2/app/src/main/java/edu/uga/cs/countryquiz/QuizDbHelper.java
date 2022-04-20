package edu.uga.cs.countryquiz;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

import org.apache.commons.lang3.ArrayUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.uga.cs.countryquiz.QuizContract.*;

import static edu.uga.cs.countryquiz.QuizStartFragment.getKountry1;


public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CountryQuiz.db";
    private static final int DATABASE_VERSION = 81;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        final String SQL_CREATE_QuizResult_TABLE = "CREATE TABLE IF NOT EXISTS " +
                QuizResultsTable.TABLE_NAME + "(" +
                QuizResultsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizResultsTable.COLUMN_DATE + " TEXT, " +
                QuizResultsTable.COLUMN_SCORE + " INTEGER" +
                ")";


        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        //db.execSQL("DROP TABLE IF EXISTS " + "quiz_results");
        db.execSQL(SQL_CREATE_QuizResult_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + "quiz_results");
        onCreate(db);
    }

    private void fillQuestionsTable() {

        Log.i("Myactivty" ,"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        QuizStartFragment quizStartFragment = new QuizStartFragment();

        int rnd = new Random().nextInt(3);
        int rnd1 = new Random().nextInt(3);
        int rnd2 = new Random().nextInt(3);
        int rnd3 = new Random().nextInt(3);
        int rnd4 = new Random().nextInt(3);
        int rnd5 = new Random().nextInt(3);

        String[] choices = choices(quizStartFragment.getKountry1Ans());
        choices[rnd] = quizStartFragment.getKountry1Ans();
        Question q1 = new Question("What country is " + quizStartFragment.getKountry1() + " located?", choices[0], choices[1], choices[2], quizStartFragment.getKountry1Ans());
        addQuestion(q1);

        String[] choices1 = choices(quizStartFragment.getKountry2Ans());
        choices1[rnd1] = quizStartFragment.getKountry2Ans();
        Question q2 = new Question("What country is " + quizStartFragment.getKountry2() + " located?", choices1[0], choices1[1], choices1[2], quizStartFragment.getKountry2Ans());
        addQuestion(q2);

        String[] choices2 = choices(quizStartFragment.getKountry3Ans());
        choices2[rnd2] = quizStartFragment.getKountry3Ans();
        Question q3 = new Question("What country is " + quizStartFragment.getKountry3() + " located?", choices2[0], choices2[1], choices2[2], quizStartFragment.getKountry3Ans());
        addQuestion(q3);

        String[] choices3 = choices(quizStartFragment.getKountry4Ans());
        choices3[rnd3] = quizStartFragment.getKountry4Ans();
        Question q4 = new Question("What country is " + quizStartFragment.getKountry4() + " located?", choices3[0], choices3[1], choices3[2], quizStartFragment.getKountry4Ans());
        addQuestion(q4);

        String[] choices4 = choices(quizStartFragment.getKountry5Ans());
        choices4[rnd4] = quizStartFragment.getKountry5Ans();
        Question q5 = new Question("What country is " + quizStartFragment.getKountry5() + " located?", choices4[0], choices4[1], choices4[2], quizStartFragment.getKountry5Ans());
        addQuestion(q5);

        String[] choices5 = choices(quizStartFragment.getKountry5Ans());
        choices5[rnd5] = quizStartFragment.getKountry5Ans();
        Question q6 = new Question("What country is " + quizStartFragment.getKountry6() + " located?", choices5[0], choices5[1], choices5[2], quizStartFragment.getKountry6Ans());
        addQuestion(q6);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public void storeResult(int Score){
        SQLiteDatabase mDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());
        values.put(QuizResultsTable.COLUMN_DATE, strDate);
        values.put(QuizResultsTable.COLUMN_SCORE, Score);
        System.out.println("mDatabase: " + mDatabase);
        mDatabase.insert(QuizResultsTable.TABLE_NAME,null,values);
        mDatabase.close();
    }

 public String[] choices(String answer) {
     String[] choices = new String[3];
     String Continents[] = {"Asia", "Africa", "North America", "South America", "Antarctica", "Europe", "Australia"};
     for (int i = 0; i < choices.length; i++) {
         while (choices[i] == null) {
             int rnd = new Random().nextInt(7);
             boolean contains = ArrayUtils.contains(choices,Continents[rnd]);
             if (!answer.equalsIgnoreCase(Continents[rnd]) & (!contains)) {
                 choices[i] = Continents[rnd];
             }
         }
     }
     return choices;

     //System.out.println("choices: " + Arrays.toString(choices));
 }

 public Cursor getQuizResults(SQLiteDatabase db){


        Cursor cursor;
        String [] projections = {
                QuizResultsTable.COLUMN_DATE,
                QuizResultsTable.COLUMN_SCORE
        };
        cursor = db.query(QuizResultsTable.TABLE_NAME, projections,null, null, null, null, null );
        return cursor;
 }

    @SuppressLint("Range")
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
