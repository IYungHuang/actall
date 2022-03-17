package tw.tigercloud2022.youract;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;


public class M_gMyadapter_innerresponse extends RecyclerView.Adapter<M_gMyadapter_innerresponse.ViewHolder> implements View.OnClickListener, Filterable {
    private final M_gMainActivity mcontext;
    private ArrayList<Map<String, Object>> mitemactl;
    public LayoutInflater mlinactf;
    private OnItemClickListener mOnItemClickListener;
    private ButtonInterface buttonInterface;


    @Override
    public void onClick(View v) {
        if(mOnItemClickListener !=null){
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final View m_ginnerreslout;
        private final TextView hideid;
        private ImageView imgView3;
        private TextView txtView01,txtView02,txtView03;
        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            m_ginnerreslout=itemView.findViewById(R.id.m_ginnerreslout);
            imgView3=itemView.findViewById(R.id.avtinnerdlg);
            txtView01=itemView.findViewById(R.id.tx1innerdlgname);
            txtView02=itemView.findViewById(R.id.tx2timestamp);
            txtView03=itemView.findViewById(R.id.tx3innerdlgspeech);
            hideid = itemView.findViewById(R.id.hideid);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.m_glist_inneritem,
                parent,false);
        view.setOnClickListener((View.OnClickListener) this);
        return new ViewHolder(view);
    }



    public M_gMyadapter_innerresponse(M_gMainActivity m_gMainActivity, ArrayList<Map<String, Object>> mList7) {
        mlinactf=(LayoutInflater) m_gMainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mitemactl=mList7;
        mcontext=m_gMainActivity;

    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface ButtonInterface{
        public void onclick( View view,int position);
    }

    public void buttonSetOnclick(ButtonInterface buttonInterface){
        this.buttonInterface=buttonInterface;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Picasso.with(mcontext).load(Uri.parse("https://lh3.googleusercontent.com/a-/AOh14GiKpEwKVlrnhHX7UeCU3Ipr3pSoVMf3u956WbL6dw")).into(holder.imgView3);
        holder.txtView01.setText((CharSequence) mitemactl.get(position).get("txtView01"));
        holder.txtView02.setText((CharSequence) mitemactl.get(position).get("txtView02"));
        holder.txtView03.setText((CharSequence) mitemactl.get(position).get("txtView03"));
        holder.hideid.setText((CharSequence) mitemactl.get(position).get("hide"));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mitemactl.size();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
