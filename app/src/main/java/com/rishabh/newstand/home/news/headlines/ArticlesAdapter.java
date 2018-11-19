package com.rishabh.newstand.home.news.headlines;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rishabh.newstand.R;
import com.rishabh.newstand.pojo.headlinesresponse.Article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.HeadlinesViewHolder> {


    public List<Article> getArticleList() {
        return articleList;
    }

    private final List<Article> articleList;

    private ArticleListener articleListener;

    public ArticlesAdapter(ArticleListener articleListener) {
        this.articleList = new ArrayList<>();
        this.articleListener = articleListener;
    }

    @NonNull
    @Override
    public HeadlinesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_headlines, viewGroup, false);
        return new HeadlinesViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HeadlinesViewHolder viewHolder, int i) {

        viewHolder.bind(viewHolder, articleList.get(i));

    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setArticleList(List<Article> articles) {
        articleList.clear();
        articleList.addAll(articles);
        notifyDataSetChanged();
    }

    public void clearSearchList() {
        articleList.clear();
        notifyDataSetChanged();
    }

    class HeadlinesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.share)
        ImageView share;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;

        HeadlinesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(HeadlinesViewHolder holder, final Article article) {

            Glide.with(holder.image.getContext()).load(article.getUrlToImage()).asBitmap().into(holder.image);
            holder.title.setText(article.getTitle());
            holder.time.setText(formatDate(article.getPublishedAt()));
            holder.itemView.setOnClickListener(this);
            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                articleListener.share(article.getUrl());
                }
            });
        }

        @Override
        public void onClick(View view) {
            articleListener.itemClicked(articleList.get(getAdapterPosition()));
        }
    }


    public String formatDate(String createdAt) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        form.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = form.parse(createdAt);
            Calendar cal = Calendar.getInstance();
            TimeZone tz = cal.getTimeZone();
            form.setTimeZone(tz);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            simpleDateFormat.setTimeZone(tz);//HH:mm
            return simpleDateFormat.format(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;

    }


    public interface ArticleListener {
        void itemClicked(Article result);

        void share(String url);
    }


}