package mateu.jaume.examenproba.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mateu.jaume.examenproba.R;
import model.Follower;
import model.Tarjeta;

/**
 * Created by root on 16/12/17.
 */

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.ProductViewHolder>{

    private Context mCtx;

    private List<Follower> followerList;

    public FollowersAdapter(Context mCtx, List<Follower> followerList) {
        this.mCtx = mCtx;
        this.followerList = followerList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_view, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Follower follower = followerList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(follower.getLogin());

        Picasso.with(mCtx)
                .load(follower.getAvatar_url())
                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return followerList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);

            imageView = itemView.findViewById(R.id.imageView);

        }
    }

}
