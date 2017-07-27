package com.crackncrunch.testingrealmadapter.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.crackncrunch.testingrealmadapter.R;
import com.crackncrunch.testingrealmadapter.realm.HumanRealm;
import com.crackncrunch.testingrealmadapter.realm.RealmManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.add_btn)
    Button mAddBtn;

    RealmResults<HumanRealm> mRealmResults;
    HumanAdapter mAdapter;

    RealmManager mRealmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Realm.init(this);
        mRealmManager = new RealmManager();

        fillDummyData();

        setListener();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new HumanAdapter(this, mRealmResults, true, mRealmResults);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setListener() {
        mRealmResults = mRealmManager.getHumansFromRealm();
        mRealmResults.addChangeListener(new RealmChangeListener<RealmResults<HumanRealm>>() {
            @Override
            public void onChange(RealmResults<HumanRealm> element) {
                mAdapter.setHumans(element);
            }
        });
    }

    private void fillDummyData() {
        mRealmManager.saveHumanToRealm(new HumanRealm(1, "Вася"));
        mRealmManager.saveHumanToRealm(new HumanRealm(2, "Петя"));
        mRealmManager.saveHumanToRealm(new HumanRealm(3, "Жора"));
    }

    @OnClick(R.id.add_btn)
    void clickOnAddButton(){
        int id = new Random().nextInt(100);
        HumanRealm newHuman = new HumanRealm(id, "Витя-" + id);
        mRealmManager.saveHumanToRealm(newHuman);
    }
}
