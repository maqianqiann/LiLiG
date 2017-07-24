package com.lingling.linglingdating.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.lingling.linglingdating.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * mqq
 * Created by 令令 on 2017/7/21.
 * 这是搜索的界面
 */


public class MySearchActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listviewReSearchLayout;
    @Bind(R.id.listview_ls_search_layout)
    ListView listviewLsSearchLayout;
    @Bind(R.id.but_clear_search)
    Button butClearSearch;
    @Bind(R.id.image_back_search)
    ImageView imageBackSearch;
    @Bind(R.id.edit_search)
    SearchView editSearch;
    @Bind(R.id.but_sure_search)
    Button butSureSearch;
    @Bind(R.id.gridview_re_search)
    GridView gridviewReSearch;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        ButterKnife.bind(this);
        initDatas();
        initListener();
    }

    private void initListener() {
        butClearSearch.setOnClickListener(this);
        //设置适配器
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        gridviewReSearch.setAdapter(adapter);
        gridviewReSearch.setTextFilterEnabled(true);
        editSearch.setIconifiedByDefault(false);

        //设置点击
        editSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //点击搜索的按钮触发该方法
              list.add(query);
                return true;
            }
            //搜索内容改变时出发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                list.add(newText);
                if(!TextUtils.isEmpty(newText)){
                    //过滤器
                    list.add(newText);
                    gridviewReSearch.setFilterText(newText);
                    adapter.notifyDataSetChanged();
                }else{
                    gridviewReSearch.clearTextFilter();
                    adapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        //设置grid的点击事件
        gridviewReSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onSearchListener.getDatas(list.get(position));

            }
        });
    }

    private void initDatas() {
        list=new ArrayList<>();
        list.add("小帅哥");
        list.add("小美女");
        list.add("萌萌");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_clear_search:
                break;

        }
    }

    private SearchListener onSearchListener;
    private interface SearchListener{
        void  getDatas(String text);
        void autoDatas(String text);
    }
    public void getOnSearch(SearchListener onSearchListener){
        this.onSearchListener=onSearchListener;
    }
}
