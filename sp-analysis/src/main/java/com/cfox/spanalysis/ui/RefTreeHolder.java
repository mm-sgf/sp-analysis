package com.cfox.spanalysis.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cfox.spanalysis.R;
import com.cfox.spanalysis.database.RefTree;

public class RefTreeHolder extends RecyclerView.ViewHolder {

    public static int getLayoutId() {
        return R.layout.layout_ref_tree;
    }

    private final TextView mRefPath;
    private final TextView mRefContent;
    private final TextView mActivityState;

    public RefTreeHolder(@NonNull View itemView) {
        super(itemView);
        mRefPath = itemView.findViewById(R.id.ref_path);
        mRefContent = itemView.findViewById(R.id.ref_content);
        mActivityState = itemView.findViewById(R.id.activity_state);
    }

    public void onBindView(RefTreeAdapter adapter, RefTree refTree, int position) {
        mRefPath.setText("Activity Path:\n   |-----" + refTree.getClassName());
        mActivityState.setText("Activity State: \n   |-----" + refTree.getActivityState());
        mRefContent.setText(refTree.getRefTree());
    }
}
