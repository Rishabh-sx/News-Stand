package com.rishabh.newstand.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabh.newstand.R;
import com.rishabh.newstand.base.BaseActivity;
import com.rishabh.newstand.home.news.NewsFragment;
import com.rishabh.newstand.home.news.headlines.HeadLinesFragment;
import com.rishabh.newstand.home.sources.SourcesFragment;
import com.rishabh.newstand.utils.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements HomeView {

    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private HomePresenter presenter;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter = new HomePresenter(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        addFragment(R.id.fl_container, NewsFragment.getInstance(), NewsFragment.class.getName());
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    tvTitle.setText(getString(R.string.s_headlines));
                    replaceFragment(R.id.fl_container, NewsFragment.getInstance(), NewsFragment.class.getName());
                    return true;
                case R.id.navigation_saved:
                    tvTitle.setText(getString(R.string.s_saved_news));
                    replaceFragment(R.id.fl_container, HeadLinesFragment.getInstance(AppConstants.KEY_SAVED),
                            HeadLinesFragment.class.getName());
                    return true;
                case R.id.navigation_sources:
                    tvTitle.setText(getString(R.string.s_sources));
                    replaceFragment(R.id.fl_container, SourcesFragment.getInstance(), SourcesFragment.class.getName());
                    return true;
            }
            return false;
        }
    };

    @OnClick(R.id.iv_search)
    public void onViewClicked() {

    }
}
