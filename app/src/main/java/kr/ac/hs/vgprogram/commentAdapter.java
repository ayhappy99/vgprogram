package kr.ac.hs.vgprogram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.MyViewHolder> {
    private List<commentData> mDataset;
    private String myNicKName;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView TextView_nickname;
        public TextView TextView_mag;
        public View rootView;

        public MyViewHolder(View v){
            super(v);
            TextView_nickname=v.findViewById(R.id.TextView_nickname);
            TextView_mag=v.findViewById(R.id.TextView_msg);
            rootView=v;

            v.setClickable(true);
            v.setEnabled(true);
        }
    }
    public commentAdapter(List<commentData> myDataset, Context context, String myNickName){
        mDataset=myDataset;
        this.myNicKName=myNickName;

    }

    @Override
    public commentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType){
        LinearLayout v=(LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_recycleer, parent,false); //오류


        MyViewHolder vh =new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){

        commentData com=mDataset.get(position);

        holder.TextView_nickname.setText(com.getNicname());
        holder.TextView_mag.setText(com.getMsg());

        if(com.getNicname().equals(myNicKName)){
            holder.TextView_mag.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

        }
        else{

        }
    }

    @Override
    public int getItemCount(){

        return mDataset==null?0:mDataset.size();
    }

    public commentData getcom(int position){
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addCom(commentData com){
        mDataset.add(com);
        notifyItemInserted(mDataset.size()-1);
    }
}