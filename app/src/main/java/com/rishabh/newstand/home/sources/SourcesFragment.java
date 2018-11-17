package com.rishabh.newstand.home.sources;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rishabh.newstand.R;
import com.rishabh.newstand.base.BaseFragment;
import com.rishabh.newstand.data.database.viewmodel.MainViewModel;
import com.rishabh.newstand.home.news.headlines.ArticlesAdapter;
import com.rishabh.newstand.home.news.headlines.HeadlinesPresenter;
import com.rishabh.newstand.home.news.headlines.HeadlinesView;
import com.rishabh.newstand.home.news.newsDetail.MovieDetails;
import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.pojo.sources.Source;
import com.rishabh.newstand.utils.AppConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends BaseFragment implements SourcesView, ArticlesAdapter.ArticleListener, SourceAdapter.SourceListener {


    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private SourcesPresenter presenter;
    private SourceAdapter sourceAdapter;

    public SourcesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_head_lines, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new SourcesPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean(AppConstants.KEY_SAVED)) {
            presenter.fragmentConfigChanged();
        } else {
            presenter.onViewCreated();
        }
    }

    /**
     * Method to return instance of {@link SourcesFragment}
     *
     * @return instance of {@link SourcesFragment}
     */
    public static SourcesFragment getInstance() {
        return new SourcesFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(AppConstants.KEY_SAVED_INSTANCE, true);
    }

    @Override
    public void itemClicked(Article result) {
        //    getActivity().startActivity(new Intent(getActivity(), MovieDetails.class).putExtra(AppConstants.KEY_ARTICLE, result));
    }

    @Override
    public void initView() {
        setupAdapter();
    }

    private void setupAdapter() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        sourceAdapter = new SourceAdapter(this);
        rv.setAdapter(sourceAdapter);
    }

    @Override
    public void initListeners() {
        setSourcesViewModelListener();
    }

    private void setSourcesViewModelListener() {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getSources().observe(this, new Observer<List<Source>>() {
            @Override
            public void onChanged(@Nullable List<Source> sources) {
                sourceAdapter.setArticleList(sources);
            }
        });
    }

    @Override
    public void itemClicked(Source result) {
        try {
            String url = result.getUrl();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Browser not found", Toast.LENGTH_SHORT).show();
        }
    }
}
