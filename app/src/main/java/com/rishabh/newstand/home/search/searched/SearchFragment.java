


package com.rishabh.newstand.home.search.searched;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabh.newstand.R;
import com.rishabh.newstand.base.BaseFragment;
import com.rishabh.newstand.home.news.headlines.ArticlesAdapter;
import com.rishabh.newstand.pojo.headlinesresponse.Article;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment implements SearchView, TextWatcher, ArticlesAdapter.ArticleListener {


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.cv_search)
    CardView cvSearch;
    @BindView(R.id.rv_all_bookings)
    RecyclerView rvSearchList;
    @BindView(R.id.tv_no_list)
    TextView tvNoList;
    Unbinder unbinder;
    private SearchPresenter mPresenter;
    private String mSearch;
    private ArticlesAdapter articlesAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        mPresenter = new SearchPresenter(this);
        mPresenter.initView();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().trim().equals(""))
            ivCancel.setVisibility(View.GONE);
        else
            ivCancel.setVisibility(View.VISIBLE);
        mSearch = editable.toString();
        if (editable.toString().length() > 3)
            mPresenter.getSearchedNews(mSearch);
    }

    @Override
    public void initViewsAndVariables() {
        mSearch = "";
        etSearch.addTextChangedListener(this);

    }

    @Override
    public void setUpAdapter() {
        rvSearchList.setLayoutManager(new LinearLayoutManager(getActivity()));
        articlesAdapter = new ArticlesAdapter(this);
        rvSearchList.setAdapter(articlesAdapter);
    }

    public static SearchFragment getInstance() {
        return new SearchFragment();
    }

    @Override
    public void itemClicked(Article result) {

    }
}