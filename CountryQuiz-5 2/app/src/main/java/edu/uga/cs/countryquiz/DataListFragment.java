package edu.uga.cs.countryquiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    QuizDbHelper quizDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    public DataListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataListFragment newInstance(String param1, String param2) {
        DataListFragment fragment = new DataListFragment();
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
        View view = inflater.inflate(R.layout.fragment_data_list, container, false);

        listView = view.findViewById(R.id.list_view);
        listDataAdapter = new ListDataAdapter(getActivity(), R.layout.row_layout);
        listView.setAdapter(listDataAdapter);
        quizDbHelper = new QuizDbHelper(getActivity());
        sqLiteDatabase = quizDbHelper.getReadableDatabase();
        cursor = quizDbHelper.getQuizResults(sqLiteDatabase);
        if(cursor.moveToFirst()){
            do {

                String date,score;
                date = cursor.getString(0);
                score = cursor.getString(1);
                DataProvider dataProvider = new DataProvider(date,score);
                listDataAdapter.add(dataProvider);

            }while (cursor.moveToNext());
        }
        // Inflate the layout for this fragment
        return view;
    }
}