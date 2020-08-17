package ceneax.app.hongmiao.listener;

import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class MyRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private int distance;
    private boolean visible = true;

    public MyRecyclerViewScrollListener() {}

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(distance < -ViewConfiguration.getTouchSlop() && !visible) {
            // 下滑
            onScrollStateChanged(State.SCROLL_DOWN);
            distance = 0;
            visible = true;
        } else if(distance > ViewConfiguration.getTouchSlop() && visible) {
            // 上滑
            onScrollStateChanged(State.SCROLL_UP);
            distance = 0;
            visible = false;
        }
        if((dy > 0 && visible) || (dy < 0 && !visible)) {
            distance += dy;
        }

        if(!recyclerView.canScrollVertically(1)) {
            onScrollStateChanged(State.SCROLL_BOTTOM);
        }
        if(!recyclerView.canScrollVertically(-1)) {
            onScrollStateChanged(State.SCROLL_TOP);
        }
    }

    public abstract void onScrollStateChanged(State state);

    public enum State {
        SCROLL_UP,
        SCROLL_DOWN,
        SCROLL_TOP,
        SCROLL_BOTTOM
    }

}
