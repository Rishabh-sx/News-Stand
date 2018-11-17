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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.HeadlinesViewHolder> {


    private List<Article> articleList;
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

    class HeadlinesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;

        public HeadlinesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(HeadlinesViewHolder holder, Article article) {

            Glide.with(holder.image.getContext()).load(article.getUrlToImage()).asBitmap().into(holder.image);
            holder.title.setText(article.getTitle());
            holder.time.setText(article.getPublishedAt());
            holder.itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            articleListener.itemClicked(articleList.get(getAdapterPosition()));
        }
    }

    public interface ArticleListener {
        void itemClicked(Article result);
    }
}