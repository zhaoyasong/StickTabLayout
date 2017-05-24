package com.cnpc.hyxt.zys.simpleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * ${END}
 * <p>
 * created by song on 2017/5/23.17:06
 */

public class CategoryFragment extends Fragment {

    private final Activity activity;
    private final List<String> itemList;

    public CategoryFragment(Activity activity, List<String> itemList) {
        this.activity = activity;
        this.itemList = itemList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_fragment, null,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SimpleAdapter simpleAdapter = new SimpleAdapter(activity, itemList);
        recyclerView.setAdapter(simpleAdapter);
        return view;
    }

    public class SimpleAdapter extends RecyclerView.Adapter{
        private final List<String> itemList;
        private final Activity activity;

        public SimpleAdapter(Activity activity,List<String> itemList) {
            this.activity = activity;
            this.itemList = itemList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(activity, R.layout.common_item, null);
            return new SimpleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView itemText = (TextView) holder.itemView.findViewById(R.id.item_text);
            itemText.setText(itemList.get(position));
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder{

        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
