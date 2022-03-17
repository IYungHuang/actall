package tw.tigercloud2022.youract;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Collection;


public class M_gMyadapter_act extends RecyclerView.Adapter<M_gMyadapter_act.ViewHolder> implements View.OnClickListener, View.OnLongClickListener , Filterable {
    private ArrayList<M_gPost> arrayListFilter;
    private M_gMainActivity mcontext;
    private ArrayList<M_gPost> mitemactl;
    private OnItemClickListener mOnItemClickListener;



    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;
        public TextView Name,Location,Start,hideid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.m_glist_item2,parent,false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        ViewHolder holder=new ViewHolder(view);
        holder.imgView=(ImageView) view.findViewById(R.id.viwpic);
        holder.Name=(TextView)view.findViewById(R.id.tx1viwname);
        holder.Location=(TextView)view.findViewById(R.id.tx2loca);
        holder.Start=(TextView) view.findViewById(R.id.tx3datim);
        holder.hideid=(TextView) view.findViewById(R.id.hideactid_recycle);
        return holder;
    }
    public M_gMyadapter_act(M_gMainActivity m_gMainActivity, ArrayList<M_gPost> mData) {
        this.mitemactl=mData;
        this.mcontext=m_gMainActivity;
        arrayListFilter = new ArrayList<>();
        arrayListFilter.addAll(mitemactl);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.imgView.setImageResource((Integer) mitemactl.get(position).get("ImgView"));
        final M_gPost post = mitemactl.get(position);

        Glide.with(mcontext)
                .load(post.mPicture)
                .placeholder(R.drawable.loading)
                .fallback(R.drawable.nopic1)
                .error(R.drawable.nopic1)
                .override(80, 80)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transition(withCrossFade())
                .into(holder.imgView);
        holder.Name.setText(post.mName);
        holder.Location.setText(post.mLocation);
        holder.Start.setText(post.mStart);
        holder.hideid.setText(post.id);
        holder.itemView.setTag(position);
    }
//   "ImgView", "Name", "Location", "Start"
    @Override
    public void onClick(View v) {
        if(mOnItemClickListener !=null){
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return true;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

    @Override
    public int getItemCount() {
        return mitemactl.size();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }
    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<M_gPost> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(arrayListFilter);
            }else{
                String filterpattern = constraint.toString().toLowerCase().trim();
                for(M_gPost item:arrayListFilter){
                    if(item.mLocation.toLowerCase().contains(filterpattern)||item.mName.toLowerCase().contains(filterpattern)||
                    item.mStart.toLowerCase().contains(filterpattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mitemactl.clear();
            mitemactl.addAll((Collection<? extends M_gPost>) results.values);
            notifyDataSetChanged();
        }
    };
}
