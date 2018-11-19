package com.rishabh.newstand.home.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rishabh.newstand.R;
import com.rishabh.newstand.base.BaseActivity;
import com.rishabh.newstand.home.news.newsDetail.MovieDetails;
import com.rishabh.newstand.home.search.searched.SearchFragment;
import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.utils.AppConstants;
import com.rishabh.newstand.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchActivityView,SearchFragment.ISearchFragmentHost {


    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_toolbar)
    RelativeLayout rlToolbar;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.container)
    ConstraintLayout container;
    private SearchActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new SearchActivityPresenter(this);
        mPresenter.initView();
        if(savedInstanceState == null || !savedInstanceState.containsKey(AppConstants.KEY))
            addFragment(R.id.fl_container, SearchFragment.getInstance(), SearchFragment.class.getName());

    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_search;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public void initViewsandVariables() {
        tvTitle.setText(R.string.s_search);
        ivSearch.setImageResource(R.drawable.ic_arrow_back_black);
    }

    @OnClick(R.id.iv_search)
    public void onViewClicked() {
       onBackPressed();
    }

    @Override
    public void openArticleDetail(Article article) {
        startActivity(
                new Intent(this, MovieDetails.class)
                .putExtra(AppConstants.KEY_ARTICLE, article)
                .putExtra(AppConstants.KEY_SEARCHED_ARTICLE,true));

    }

    @Override
    public void share(String url) {
        AppUtils.shareText(this,url);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(AppConstants.KEY,getSupportFragmentManager().findFragmentById(R.id.fl_container).getClass().getSimpleName());
    }
}
