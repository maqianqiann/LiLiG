package com.lingling.linglingdating.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lingling.linglingdating.R;
import com.lingling.linglingdating.activitys.MyAccountActivity;
import com.lingling.linglingdating.adapter2.PriceAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 令令 on 2017/7/20.
 * 这是支出的frgment
 */
public class PaymentFragment extends Fragment {

    @Bind(R.id.listView_listView)
    ListView listViewListView;
    private View view;
    private MyAccountActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listview_layout, null);
        activity = (MyAccountActivity) getActivity();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listViewListView.setAdapter(new PriceAdapter(activity,1));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
