package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import java.util.ArrayList;
//Reference: https://androidwave.com/android-recyclerview-example-best-practices/
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    LuggageCollectAdapter mSportAdapter;

    LinearLayoutManager mLayoutManager;

    ArrayList<LuggageCollectModel> mLuggage = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 15; i++) {
            mLuggage.add(new LuggageCollectModel("Dhananjay", "19524929", "971603024"+i, "0"+i));
        }

        ButterKnife.bind(this);
        setUp();
    }



    private void setUp() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mSportAdapter = new LuggageCollectAdapter(mLuggage);
        mRecyclerView.setAdapter(mSportAdapter);
    }

}