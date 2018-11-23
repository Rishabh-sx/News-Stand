package com.rishabh.newstand.home.news.headlines;


import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabh.newstand.R;
import com.rishabh.newstand.base.BaseFragment;
import com.rishabh.newstand.data.database.viewmodel.MainViewModel;
import com.rishabh.newstand.home.BaseHost;
import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.utils.AppConstants;
import com.rishabh.newstand.widget.NewsStandWidget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeadLinesFragment extends BaseFragment implements HeadlinesView, ArticlesAdapter.ArticleListener {


    @BindView(R.id.rv)
    RecyclerView rv;
    private Unbinder unbinder;
    private HeadlinesPresenter presenter;
    private String fragmentType;
    private ArticlesAdapter articlesAdapter;
    private IHeadlinesFragmentHost mHost;
    private Parcelable savedRecyclerLayoutState;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IHeadlinesFragmentHost) {
            mHost = (IHeadlinesFragmentHost) context;
        } else {
            throw new IllegalStateException("Host must Implement IHeadlinesFragmentHost");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_head_lines, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new HeadlinesPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            presenter.initView();
        } else {
            savedRecyclerLayoutState = savedInstanceState.getParcelable(AppConstants.STATE);
            fragmentType = savedInstanceState.getString(AppConstants.KEY);
            presenter.savedInstanceStateFlow(fragmentType);
        }
    }

    /**
     * Method to return instance of {@link HeadLinesFragment}
     *
     * @param fragmentKey is used to put in bundle
     *                    for further deciding to
     *                    fetch data according to it.
     * @return instance of {@link HeadLinesFragment}
     */
    public static HeadLinesFragment getInstance(String fragmentKey) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.KEY, fragmentKey);
        HeadLinesFragment headLinesFragment = new HeadLinesFragment();
        headLinesFragment.setArguments(bundle);
        return headLinesFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void getHeadlinesType() {
        if (getArguments() != null && getArguments().containsKey(AppConstants.KEY)) {
            fragmentType = getArguments().getString(AppConstants.KEY);
            presenter.fragmentTypeFetched(fragmentType);
        }
    }

    @Override
    public void setAdapters() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        articlesAdapter = new ArticlesAdapter(this);
        rv.setAdapter(articlesAdapter);
    }

    @Override
    public void initListeners(final String fragmentType) {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        if (!fragmentType.equals(AppConstants.KEY_SAVED)) {
            //Listener for normal fragment
            viewModel.getArticlesByHeadlines(this.fragmentType).observe(this, new Observer<List<Article>>() {
                @Override
                public void onChanged(@Nullable List<Article> articles) {
                    articlesAdapter.setArticleList(articles);
                    if(savedRecyclerLayoutState!=null && rv.getLayoutManager()!=null){
                        rv.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
                        savedRecyclerLayoutState=null;
                    }
                }
            });
        } else {
            //Listener for saved fragments
            viewModel.getSavedArticlesByHeadlines().observe(this, new Observer<List<Article>>() {
                @Override
                public void onChanged(@Nullable List<Article> articles) {
                    articlesAdapter.setArticleList(articles);
                    if(savedRecyclerLayoutState!=null && rv.getLayoutManager()!=null){
                        rv.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
                        savedRecyclerLayoutState=null;
                    }
                }
            });

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(AppConstants.KEY, fragmentType);
        if (rv.getLayoutManager() != null)
            outState.putParcelable(AppConstants.STATE, rv.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void itemClicked(Article result) {
        mHost.openArticleDetail(result);
    }

    public interface IHeadlinesFragmentHost extends BaseHost {

    }

    @Override
    public void share(String url) {
        mHost.share(url);
    }

    @Override
    public void refreshWidget() {
        if (getActivity() != null) {
            Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.setComponent(new ComponentName(getActivity(), NewsStandWidget.class));
            getActivity().sendBroadcast(intent);
        }
    }
}
