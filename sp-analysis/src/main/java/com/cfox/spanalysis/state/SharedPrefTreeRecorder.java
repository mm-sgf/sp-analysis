package com.cfox.spanalysis.state;

import android.util.Log;

import com.cfox.spanalysis.database.DBManager;
import com.cfox.spanalysis.database.RefTree;
import com.cfox.spanalysis.database.RefTreeDao;
import com.cfox.spanalysis.proxy.ProxyCallbackListener;
import com.cfox.spanalysis.proxy.ProxyWorkFinishersManager;

public class SharedPrefTreeRecorder implements ProxyCallbackListener<Runnable> {
    private static final String TAG = "SharedPrefTreeRecorder";

    public enum ActivityState {
        NONE("none"),
        CREATED("created"),
        STARTED("started"),
        RESUMED("resume"),
        PAUSED("pause"),
        STOPPED("stopped"),
        DESTROYED("destroyed");

        public String state;
        ActivityState(String state) {
            this.state = state;
        }
    }

    private final ProxyWorkFinishersManager mPwfManager;
    private ActivityState mState = ActivityState.NONE;
    private String mFlag;

    public SharedPrefTreeRecorder(String flag, ProxyWorkFinishersManager manager) {
        this.mFlag = flag;
        this.mPwfManager = manager;
    }

    public void onActivityStateChange(ActivityState state) {
        this.mState = state;
        setListener();
    }


    @Override
    public void add(Runnable runnable) {
        if (mState == ActivityState.NONE
                || mState == ActivityState.CREATED
                || mState == ActivityState.STARTED) {
            return;
        }
        StackTraceElement[] stack = new Throwable().getStackTrace();
        if (stack.length > 4) {
            StringBuilder buffer = new StringBuilder("SP-Ref-Tree :");
            buffer.append("===== Tree ======").append("\n");

            for (int i = 5 ; i < stack.length ; i ++ ) {
                buffer.append("   |----").append(stack[i].toString()).append("\n");
                Log.d(TAG, "   |----" + stack[i].toString());
            }
            RefTreeDao dao = DBManager.getDatabase().getRefTreeDao();
            RefTree refTree = new RefTree(buffer.toString().hashCode(), mFlag, mState.state, buffer.toString());
            dao.insertRefTree(refTree);
        }
    }

    @Override
    public void remove(Object o) {
        if (mState == ActivityState.NONE) {
            return;
        }
    }

    private void setListener() {
        this.mPwfManager.setProxyCallbackListener(this);
    }
}
