package com.crackncrunch.testingrealmadapter.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crackncrunch.testingrealmadapter.R;
import com.crackncrunch.testingrealmadapter.realm.HumanRealm;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class HumanAdapter extends RealmRecyclerViewAdapter
        <HumanRealm, HumanAdapter.HomeViewHolder> {

    private RealmResults<HumanRealm> mHumans;

    public HumanAdapter(@NonNull Context context, @Nullable
            OrderedRealmCollection<HumanRealm> data, boolean autoUpdate,
                        RealmResults<HumanRealm> humans) {

        super(context, data, autoUpdate);
        mHumans = humans;
    }

    public void setHumans(RealmResults<HumanRealm> humans) {
        mHumans = humans;
        notifyDataSetChanged();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layout = R.layout.item_human;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layout, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        HumanRealm human = mHumans.get(position);
        holder.humanId.setText(String.valueOf(human.getId()));
        holder.humanName.setText(human.getName());
    }

    @Override
    public int getItemCount() {
        return mHumans.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.human_id)
        TextView humanId;
        @BindView(R.id.human_name)
        TextView humanName;

        public HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
