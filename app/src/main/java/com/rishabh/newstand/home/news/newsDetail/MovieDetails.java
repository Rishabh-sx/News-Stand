package com.rishabh.newstand.home.news.newsDetail;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.rishabh.newstand.R;
import com.rishabh.newstand.base.BaseActivity;
import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.utils.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetails extends BaseActivity implements MovieDetailView {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.rv_trailers)
    RecyclerView rvTrailers;
    @BindView(R.id.card_trailers)
    CardView cardTrailers;
    @BindView(R.id.rv_reviews)
    RecyclerView rvReviews;
    @BindView(R.id.card_reviews)
    CardView cardReviews;
    @BindView(R.id.mark_fav_unfave)
    ImageView markFavUnfave;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Article article;
    private MovieDetailPresenter movieDetailPresenter;
    private boolean isReviewQueried;
    private boolean isTrailerQueried;
    private boolean isSearchedArticle;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityTransitions();
        article = getArticle();
        isSearchedArticle = isSearched();
        ButterKnife.bind(this);
        movieDetailPresenter = new MovieDetailPresenter(this);
        movieDetailPresenter.initView();

    }

    private boolean isSearched() {
        return getIntent().getBooleanExtra(AppConstants.KEY_SEARCHED_ARTICLE,false);
    }

    private Article getArticle() {
        return getIntent().getParcelableExtra(AppConstants.KEY_ARTICLE);
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_details;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);

        }
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.colorPrimaryDark);
        int primary = getResources().getColor(R.color.colorPrimary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        //      updateBackground((FloatingActionButton) findViewById(R.id.share_fab), palette);
        supportStartPostponedEnterTransition();
        setTransparentStatusBar(palette);
    }

    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.colorAccent));

        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }

    private void setTransparentStatusBar(Palette palette) {
        int color = palette.getMutedColor(getResources().getColor(R.color.colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (getWindow() != null)
                getWindow().setStatusBarColor(color);
        }
    }

    public float Lightness(int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        float hsl[] = new float[3];
        ColorUtils.RGBToHSL(red, green, blue, hsl);
        return hsl[2];
    }

    @Override
    public void initViews() {


        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String itemTitle = article.getTitle();
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        if(isSearchedArticle){
            markFavUnfave.setVisibility(View.GONE);
        }else {
            markFavUnfave.setVisibility(View.VISIBLE);
        }
        Glide.with(image.getContext())
                .load(article.getUrlToImage())
                .asBitmap().listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }


            @Override
            public boolean onResourceReady(Bitmap resource, String model,
                                           Target<Bitmap> target,
                                           boolean isFromMemoryCache,
                                           boolean isFirstResource) {

                image.setImageBitmap(resource);
                if (image.getDrawable() != null) {
                    Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                        public void onGenerated(Palette palette) {
                            applyPalette(palette);
                        }
                    });
                }
                return true;
            }
        }).into(image);

        title.setText(article.getTitle());
        if (article.getContent()!=null) {
            String content[]= article.getContent().split("\\s+(?=\\[)");
            if(content.length >=2) {
                String viewMore = content[1];
                String message = content[0];
            description.setText(String.format("%s %s",message,viewMore.replace(viewMore,
                    getString(R.string.s_view_more))));
            addClickToViewMore();
            }
        }

    }

    private void addClickToViewMore() {
        Spannable span = Spannable.Factory.getInstance().newSpannable(description.getText().toString());
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View v) {
                try {
                    String url = article.getUrl();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(MovieDetails.this, getString(R.string.s_brwoser_not_found), Toast.LENGTH_SHORT).show();
                }
            } }, description.getText().toString().indexOf(getString(R.string.s_view_more)),
                description.getText().toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

       span.setSpan(new ForegroundColorSpan(getColor(R.color.colorAccent)),description.getText().toString().indexOf(getString(R.string.s_view_more)),
               description.getText().toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        description.setText(span);
        description.setMovementMethod(LinkMovementMethod.getInstance());
        updateArticleSaveIcon(article.isSaved());
    }

    @OnClick(R.id.mark_fav_unfave)
    public void onViewClicked() {
        article.setSaved(!article.isSaved());
        movieDetailPresenter.updateArticle(article);
        updateArticleSaveIcon(article.isSaved());
    }

    private void updateArticleSaveIcon(boolean saved) {

        if (saved) {
            markFavUnfave.setImageResource(R.drawable.ic_mark_saved);
        } else {
            markFavUnfave.setImageResource(R.drawable.ic_saved);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
