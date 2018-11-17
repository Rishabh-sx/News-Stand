package com.rishabh.newstand.home.sources;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabh.newstand.R;
import com.rishabh.newstand.pojo.sources.Source;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceViewHolder> {



    private List<Source> sourceList;
    private SourceListener sourceListener;

    public SourceAdapter(SourceListener sourceListener) {
        this.sourceList = new ArrayList<>();
        this.sourceListener = sourceListener;
    }

    @NonNull
    @Override
    public SourceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_sources, viewGroup, false);
        return new SourceViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SourceViewHolder viewHolder, int i) {

        viewHolder.bind(viewHolder, sourceList.get(i));

    }


    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    public void setArticleList(List<Source> sources) {
        sourceList.clear();
        sourceList.addAll(sources);
        notifyDataSetChanged();
    }

    class SourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.desc)
        TextView desc;

        public SourceViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(SourceViewHolder holder, Source source) {
            holder.itemView.setOnClickListener(this);
            holder.title.setText(source.getName());
            holder.desc.setText(source.getDescription());
        }

        @Override
        public void onClick(View view) {
            sourceListener.itemClicked(sourceList.get(getAdapterPosition()));
        }
    }

    public interface SourceListener {
        void itemClicked(Source result);
    }

}
