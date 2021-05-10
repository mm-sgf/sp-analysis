package com.cfox.spanalysis.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cfox.spanalysis.R;
import com.cfox.spanalysis.database.DBManager;
import com.cfox.spanalysis.database.RefTree;
import com.cfox.spanalysis.database.RefTreeDao;
import com.cfox.spanalysis.state.SharedPrefTreeRecorder;

import java.util.ArrayList;
import java.util.List;

public class SpAnalysisMainActivity extends Activity implements CompoundButton.OnCheckedChangeListener {
    private RecyclerView mRcTreeView;
    private RefTreeAdapter mAdapter;
    private CheckBox mCheckResume;
    private CheckBox mCheckPause;
    private CheckBox mCheckStopped;
    private CheckBox mCheckDestroyed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_analysis);


        mCheckResume = findViewById(R.id.state_resume);
        mCheckPause = findViewById(R.id.state_pause);
        mCheckStopped = findViewById(R.id.state_stopped);
        mCheckDestroyed = findViewById(R.id.state_destroyed);
        mCheckResume.setOnCheckedChangeListener(this);
        mCheckPause.setOnCheckedChangeListener(this);
        mCheckStopped.setOnCheckedChangeListener(this);
        mCheckDestroyed.setOnCheckedChangeListener(this);

        mRcTreeView = findViewById(R.id.tree_list);
        mRcTreeView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<RefTree> refTreeList = queryTree();
        mAdapter = new RefTreeAdapter(refTreeList);
        mRcTreeView.setAdapter(mAdapter);

    }

    public void onCleanTree(View view) {
        RefTreeDao dao = DBManager.getDatabase().getRefTreeDao();
        dao.deleteAll();
        mAdapter.clean();
    }

    public void onRefreshTree(View view) {
        List<RefTree> refTreeList = queryTree();
        mAdapter.refresh(refTreeList);
    }

    private void refreshTree() {
        List<RefTree> refTreeList = queryTree();
        mAdapter.refresh(refTreeList);
    }


    private List<RefTree> queryTree() {
        RefTreeDao dao = DBManager.getDatabase().getRefTreeDao();
        List<String> stateList = new ArrayList<>();
        if (mCheckResume.isChecked()) {
            stateList.add(SharedPrefTreeRecorder.ActivityState.RESUMED.state);
        }

        if (mCheckPause.isChecked()) {
            stateList.add(SharedPrefTreeRecorder.ActivityState.PAUSED.state);
        }

        if (mCheckStopped.isChecked()) {
            stateList.add(SharedPrefTreeRecorder.ActivityState.STOPPED.state);
        }

        if (mCheckDestroyed.isChecked()) {
            stateList.add(SharedPrefTreeRecorder.ActivityState.DESTROYED.state);
        }

        if (stateList.size() > 0) {
            return dao.query(stateList.toArray(new String[]{}));
        } else {
            return dao.getAll();
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        refreshTree();
    }
}
