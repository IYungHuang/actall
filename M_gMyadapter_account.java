package tw.tigercloud2022.youract;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class M_gMyadapter_account extends RecyclerView.Adapter<M_gMyadapter_account.ViewHolder> implements View.OnClickListener {
    private final M_gMainActivity mcontext;
    private ArrayList<Map<String, Object>> mitemactl;
    public LayoutInflater mlinactf;
    private OnItemClickListener mOnItemClickListener;
    private ButtonInterface buttonInterface;
    private M_gSDbHelper dbHper; //宣告class，產生新物件dbHper
    private static final String DB_FILE = "healthy.db";
    private ArrayList<String> recSet2 = null;
    private static final int DBversion = 1;
    private String delshare,delshare2;
    private BottomSheetDialog bottomSheetDialogop;
    private String bfmodifyed,ShareID,bfmodifyedemoji;
    protected Context context;
    private String idresponse;
    private M_gMyadapter_innerresponse adapterinnerresponse;
    private String delactid;
    private Handler handler=new Handler();
    private String Sharet;
    private String pstion;
    private String currentTimestampsdf;

    private void initDB() {

        if (dbHper == null) {
            dbHper = new M_gSDbHelper(mcontext, DB_FILE, null, DBversion);
        }
        recSet2 = dbHper.getRecSetbbs();
    }

//   "reponsetalk  TEXT NOT NULL," + "responsetime   TEXT NOT NULL," + "responseownerid  INTEGER NOT NULL,"
//             + "hostid  INTEGER NOT NULL

    public void showRecresponse() {
        if (M_gMainActivity.recSetresponse.size() != 0) {
            for (int i = 0; i < M_gMainActivity.recSetresponse.size(); i++) {
                Map<String, Object> itemreco = new HashMap<String, Object>();
                String[] fld = M_gMainActivity.recSetresponse.get(i).split("#");
                if(fld[2].equals(pstion)){
                    String[] timenid = fld[3].split("-");
                    itemreco.put("Picgavata", fld[5]);//頭像隨回應使用者變化
                    itemreco.put("txtView01", fld[6]);//擷取帳戶名稱
                    itemreco.put("txtView02", timenid[0]);
                    itemreco.put("txtView03", fld[1]);
                    itemreco.put("hide", timenid[1] );
                    M_gMainActivity.mList7.add(itemreco);
                    M_gMyadapter_innerresponse adapterinnerresponse = new M_gMyadapter_innerresponse(
                            mcontext,
                            M_gMainActivity.mList7);
                    M_gMainActivity.m_ginnersharedlgrv.setLayoutManager(new LinearLayoutManager(mcontext,LinearLayoutManager
                            .VERTICAL,false));
                    M_gMainActivity.m_ginnersharedlgrv.setAdapter(adapterinnerresponse);
                    M_gMainActivity.m_ginnersharedlgrv.scrollToPosition(M_gMainActivity.mList7.size()-1);
                    adapterinnerresponse.notifyDataSetChanged();
                    adapterinnerresponse.setOnItemClickListener(adapterinnerresponseon);
                    M_gMainActivity.m_ginnersharedlgrv.scrollToPosition(M_gMainActivity.mList7.size()-1);
                }
            }
        } else {
            M_gMainActivity.firstpesponse.setVisibility(View.VISIBLE);
            M_gMainActivity.m_ginnersharedlgrv.setVisibility(View.INVISIBLE);
            Toast.makeText(mcontext,"尚未有留言喔!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener !=null){
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private Button m_gLiketx,m_gResponsetx,m_gSharetx;
        private ImageView imgView3,imgeoption;
        private TextView txtView01,txtView02,txtView03,txtView04,txtView05;
        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView3=itemView.findViewById(R.id.avt);
            txtView01=itemView.findViewById(R.id.tx1name);
            txtView02=itemView.findViewById(R.id.tx2feelself);
            txtView03=itemView.findViewById(R.id.tx3speechself);
            txtView04=itemView.findViewById(R.id.m_gsharetimestamp);
            txtView05=itemView.findViewById(R.id.m_gshareid);
            m_gLiketx=itemView.findViewById(R.id.m_gLiketx);
            m_gResponsetx=itemView.findViewById(R.id.m_gResponsetx);
            m_gSharetx=itemView.findViewById(R.id.m_gSharetx);
            imgeoption=itemView.findViewById(R.id.imgeoption);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.m_glist_itemaccount,
                parent,false);
        view.setOnClickListener((View.OnClickListener) this);
        return new ViewHolder(view);
    }



    public M_gMyadapter_account(M_gMainActivity m_gMainActivity, ArrayList<Map<String, Object>> asd) {
        mlinactf=(LayoutInflater) m_gMainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mitemactl=asd;
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
        //noinspection ConstantConditions
//        holder.imgView3.setImageResource((Integer) mitemactl.get(position).get("imgView3"));
        Uri picuri = Uri.parse(mitemactl.get(position).get("Picgavata").toString());
        Picasso.with(mcontext).load(picuri).into(holder.imgView3);
        holder.txtView01.setText((CharSequence) mitemactl.get(position).get("txtView01"));
        holder.txtView02.setText((CharSequence) mitemactl.get(position).get("txtView02"));
        holder.txtView03.setText((CharSequence) mitemactl.get(position).get("txtView03"));
        holder.txtView04.setText((CharSequence) mitemactl.get(position).get("timestamp"));
        holder.txtView05.setText((CharSequence) mitemactl.get(position).get("txtView05"));
        String s = mitemactl.get(position).get("txtView05").toString();
        for(int i = 0; i< M_gMainActivity.shareid.size(); i++){
            if(s.equals(M_gMainActivity.shareid.get(i))){
                holder.m_gLiketx.setText("讚!");
                holder.m_gLiketx.setTextColor(Color.RED);
                break;
            }else{
                holder.m_gLiketx.setText("讚");
                holder.m_gLiketx.setTextColor(Color.WHITE);
            }
        }
        holder.imgeoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialogop = new BottomSheetDialog(mcontext);
                bottomSheetDialogop.setContentView(R.layout.m_gbtomshtdialg_option);
                LinearLayout m_goptionmodify = bottomSheetDialogop.findViewById(R.id.m_goptionmodify);
                LinearLayout m_goptiondrl = bottomSheetDialogop.findViewById(R.id.m_goptiondrl);
                LinearLayout m_goptionreport = bottomSheetDialogop.findViewById(R.id.m_goptionreport);
                TextView report =  bottomSheetDialogop.findViewById(R.id.report);
                m_goptiondrl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog();
                        delshare = mitemactl.get(position).get("txtView02").toString()+"："+ mitemactl.get(position).get("txtView03").toString();
//                        delshare2 = M_gMainActivity.mList6.get(position).get("txtView02").toString()+"："+M_gMainActivity.mList6.get(position).get("txtView03").toString();
                        delactid= mitemactl.get(position).get("txtView05").toString();
                        mitemactl.remove(position);
//                        M_gMainActivity.mList6.remove(position);
                        bottomSheetDialogop.cancel();
                    }

                });
                m_goptionmodify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mcontext.setupViewcomponent();
                        initDB();
                        M_gMainActivity.shareDlgmodify.show();
                        bfmodifyed = mitemactl.get(position).get("txtView03").toString();
                        bfmodifyedemoji = mitemactl.get(position).get("txtView02").toString()+"：";
                        ShareID = mitemactl.get(position).get("txtView05").toString();
                        M_gMainActivity.edtextsharem.setText(bfmodifyed);
                        M_gMainActivity.sharedlgfeel2.setText(bfmodifyedemoji);
                        M_gMainActivity.diginsertemojioBtnm.setOnClickListener(diginnerBtnmon);
                        M_gMainActivity.digshareBtnm.setOnClickListener(diginnerBtnmon);
                        M_gMainActivity.closedigm.setOnClickListener(diginnerBtnmon);
                        bottomSheetDialogop.cancel();

                    }
                });
                m_goptionreport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        report.setMovementMethod(LinkMovementMethod.getInstance());
                    }
                });
                bottomSheetDialogop.show();
            }
        });


        holder.m_gLiketx.setOnClickListener(new View.OnClickListener() {
            int x = 0;
            @Override
            public void onClick(View v) {
                initDB();

                Sharet = mitemactl.get(position).get("txtView05").toString();
                buttonInterface.onclick(v,position);
                if(x<1){
                    if(!holder.m_gLiketx.getText().toString().equals("讚!")){

                        holder.m_gLiketx.setText("讚!");
                        holder.m_gLiketx.setTextColor(Color.RED);
                        x=x+1;

                        dbHper.insertReclikeshare(Sharet, "M_gLoginActivity.wID");
                        mysql_insertlksh();
                        dbHper.close();
                        int a = 0 ;
                    }else{
                        holder.m_gLiketx.setText("讚");
                        holder.m_gLiketx.setTextColor(Color.WHITE);
                        x=0;
                        mysql_dellksh();
                        dbHper.deletelikeshRec(Sharet);//需依照引數需求調整.....未完成
                        dbHper.close();

                    }

                }else{
                    holder.m_gLiketx.setText("讚");
                    holder.m_gLiketx.setTextColor(Color.WHITE);
                    x=0;
                    mysql_dellksh();
                    dbHper.deletelikeshRec(Sharet);//需依照引數需求調整.....未完成
                    dbHper.close();
                }
            }
        });

        holder.m_gResponsetx.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                initDB();
                buttonInterface.onclick(v,position);
                M_gMainActivity.m_ginnersharerecyclervw.show();
//                //---------------------------
                 pstion = mitemactl.get(position).get("txtView05").toString();
                 M_gMainActivity.getInstance().dbmysql_response();
//                Toast.makeText(mcontext,s+"shareid",Toast.LENGTH_LONG).show();
//                //---------------------------
                if(M_gMainActivity.recSetresponse.size()!=0){
                    M_gMainActivity.mList7.clear();
                    M_gMainActivity.firstpesponse.setVisibility(View.GONE);
                    M_gMainActivity.m_ginnersharedlgrv.setVisibility(View.VISIBLE);
                    showRecresponse();
                }
                M_gMainActivity.m_ginnersend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonInterface.onclick(v,position);
                        if(!M_gMainActivity.m_ginneredtx.getText().toString().equals("")){
                            Date currentTimestamp = Calendar.getInstance().getTime();
                            SimpleDateFormat sdfTstamp = new SimpleDateFormat("yyyy年MM月dd日 E '@' HH:mm");
                            currentTimestampsdf = sdfTstamp.format(currentTimestamp);
                            M_gMainActivity.m_ginnersharedlgrv.setVisibility(View.VISIBLE);
                            M_gMainActivity.m_ginnersharedlgrv.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager
                                    .VERTICAL,false));
                            //----------------------------------------------------------------------------
                            int hostid = Integer.parseInt(M_gMainActivity.m_ghideid.getText().toString());
                            String s = mitemactl.get(position).get("txtView05").toString();
//                            Toast.makeText(mcontext,s+"/:/"+hostid+"/:/"+ "M_gLoginActivity.wID",Toast.LENGTH_LONG).show();
                            //-----------------------------------------------------------------------------
                            dbHper.insertRecrespon(M_gMainActivity.m_ginneredtx.getText().toString(),
                                    "M_gLoginActivity.wID",Integer.parseInt(s),currentTimestampsdf.substring(5),
                                    M_gLoginActivity.personPhoto , M_gLoginActivity.g_DisplayName);
                            M_gMainActivity.mList7.clear();
                            mysql_insertresponse();
//                            initDB();
                            try {
                                Thread.sleep(1000); //  延遲Thread 睡眠1秒
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            M_gMainActivity.getInstance().dbmysql_response();

                            showRecresponse();
                            dbHper.close();

                            M_gMainActivity.m_ginneredtx.setText("");
                            M_gMainActivity.firstpesponse.setVisibility(View.GONE);
                            InputMethodManager m=(InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        }else{
                            Toast.makeText(mcontext.getApplicationContext(),R.string.checkwhattosay,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                M_gMainActivity.closediginner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        M_gMainActivity.m_ginnersharerecyclervw.cancel();
                    }
                });
            }
        });


        holder.m_gSharetx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonInterface.onclick(v,position);
                String message=holder.txtView03.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                intent.setType("text/plain");
                mcontext.startActivity(Intent.createChooser(intent,"LINE"));
            }
        });
        holder.itemView.setTag(position);
    }

    private int xposition;
    private String delx;
    private M_gMyadapter_innerresponse.OnItemClickListener adapterinnerresponseon =
            new M_gMyadapter_innerresponse.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            delrespodlg();
            xposition = position;
            delx = M_gMainActivity.mList7.get(position).get("hide").toString();
            int a = 0;
            //有bug 待修 !!! 0228
        }
    };
    private DialogInterface.OnClickListener Psbt2 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //有bug 待修 !!! 0228

            M_gMainActivity.mList7.clear();
            mysql_delresponse();//------
            try {
                Thread.sleep(500); //  延遲Thread 睡眠0.5秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            M_gMainActivity.getInstance().dbmysql_response();
            showRecresponse();
            dbHper.close();
        }
    };
    private DialogInterface.OnClickListener Nebt2 = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(mcontext.getApplicationContext(),"取消刪除",Toast.LENGTH_SHORT).show();
        }
    };

    private void delrespodlg() {
        M_gMyAlertDialog dialog = new M_gMyAlertDialog(mcontext);
        dialog.setTitle("刪除確認");
        dialog.setCancelable(false);
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setMessage("確定要刪除此留言?");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"確定",Psbt2);
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL,"取消",Nebt2);
        dialog.show();
    }

    private DialogInterface.OnClickListener Psbt = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mysql_delshare();
            if(M_gMainActivity.mList5.size()==0){
                M_gMainActivity.m_gpromofirstshare.setVisibility(View.VISIBLE);
            }else{
                M_gMainActivity.m_gpromofirstshare.setVisibility(View.INVISIBLE);
            }

            M_gMainActivity.getInstance().setupViewcomponent();
            initDB();
            M_gMainActivity.mList6.clear();
            M_gMainActivity.getInstance().dbmysql_allshare();
            M_gMainActivity.getInstance().showRecshareAll();

        }
    };
    private DialogInterface.OnClickListener Nebt = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(mcontext.getApplicationContext(),"取消刪除",Toast.LENGTH_SHORT).show();
        }
    };
    private void dialog(){
        M_gMyAlertDialog dialog = new M_gMyAlertDialog(mcontext);
        dialog.setTitle("刪除確認");
        dialog.setCancelable(false);
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setMessage("確定要刪除此留言?");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"確定",Psbt);
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL,"取消",Nebt);
        dialog.show();
    }

    private String modifiedshare;
    private View.OnClickListener diginnerBtnmon = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.m_g002_b04m:
                    M_gMainActivity.showemotion.show();
                    break;
                case R.id.m_g002_b05m:
                    initDB();
                    String modifyededx = M_gMainActivity.edtextsharem.getText().toString();
                    String modifyedfel = M_gMainActivity.sharedlgfeel2.getText().toString();
                    modifiedshare = modifyedfel+modifyededx;
                    dbHper.updateRec(modifiedshare,ShareID);
                    M_gMainActivity.edtextsharem.setText("");
                    M_gMainActivity.shareDlgmodify.cancel();
                    mysql_updateshare();
                    M_gMainActivity.getInstance().setupViewcomponent();
                    M_gMainActivity.mList6.clear();
                    M_gMainActivity.getInstance().dbmysql_allshare();
                    M_gMainActivity.getInstance().showRecshareAll();

                    break;
                case R.id.closesharedialogm:
                    M_gMainActivity.shareDlgmodify.cancel();
                    break;
            }
        }
    };

    private void mysql_updateshare() {
       String s_id = ShareID;
       String tname = modifiedshare;
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(s_id);
        nameValuePairs.add(tname);

        try {
            Thread.sleep(500); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//-----------------------------------------------
        String result = M_gSDBConnector.executeUpdate(nameValuePairs);
//-----------------------------------------------
    }

    private void mysql_delshare() {
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(delactid);
        try {
            Thread.sleep(100); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//-----------------------------------------------
        String result = M_gSDBConnector.executeDeletshare(nameValuePairs);   //執行刪除
//-----------------------------------------------
    }

    public void mysql_insertlksh() {
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add("M_gLoginActivity.wID");
        nameValuePairs.add( String.valueOf(Sharet));
        try {
            Thread.sleep(500); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//-----------------------------------------------
        String result = M_gSDBConnector.executeInsertlksh(nameValuePairs);  //真正執行新增
//-----------------------------------------------
//        Toast.makeText(mcontext,result,Toast.LENGTH_SHORT).show();
    }

    private void mysql_dellksh() {  //not yet
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add("M_gLoginActivity.wID");
        nameValuePairs.add( String.valueOf(Sharet));
        try {
            Thread.sleep(100); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//-----------------------------------------------
        String result = M_gSDBConnector.executeDeletlksh(nameValuePairs);   //執行刪除
//        Toast.makeText(mcontext,result,Toast.LENGTH_SHORT).show();
//-----------------------------------------------
    }

//        rec.put("picweburi", String.valueOf(puri));
//        rec.put("pesponseownername",uname); M_gLoginActivity.personPhoto ,M_gLoginActivity.g_DisplayName

    public void mysql_insertresponse() {
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(M_gMainActivity.m_ginneredtx.getText().toString());
        nameValuePairs.add("M_gLoginActivity.wID");
        nameValuePairs.add(pstion);
        nameValuePairs.add(currentTimestampsdf.substring(5));
        nameValuePairs.add(String.valueOf(M_gLoginActivity.personPhoto));
        nameValuePairs.add(M_gLoginActivity.g_DisplayName);

        try {
            Thread.sleep(500); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//-----------------------------------------------
        String result = M_gSDBConnector.executeInsertresponse(nameValuePairs);  //真正執行新增
//-----------------------------------------------
//        Toast.makeText(mcontext,result,Toast.LENGTH_SHORT).show();
    }

    private void mysql_delresponse() {  //not yet
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(delx);
        try {
            Thread.sleep(200); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//-----------------------------------------------
        String result = M_gSDBConnector.executeDeletresponse(nameValuePairs);   //執行刪除
//        Toast.makeText(mcontext,result,Toast.LENGTH_SHORT).show();
//-----------------------------------------------
    }

    @Override
    public int getItemCount() {
        return mitemactl.size();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
