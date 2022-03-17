package tw.tigercloud2022.youract;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
//import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class M_gMainActivity extends AppCompatActivity implements Animation.AnimationListener,
        ViewSwitcher.ViewFactory{
    private static final String TAG = "YUNG=>";
    private ArrayList<String> recSet = null;
    private ArrayList<String> recSet2 = null;
    private ArrayList<String> recSet3 = null;
    public static ArrayList<String> recSet4 = null;
    public static ArrayList<String> recSetshareALL = null;
    public static Context mContext;
    private static M_gMainActivity instance;
    private RelativeLayout lout3,lout5,
            plout2,plout3;
    private CoordinatorLayout plout1;
    private Context context;
    private Intent intent = new Intent();
    // ----------------------------------------------------------
    public static Dialog shareDlg,showemotion,shareDlgmodify;;
    //----fab share dialog
    private Button mg4b02, mg4b04, mg4b06,mg4bmedal,mg4myfr;
    public static ArrayList<Map<String, Object>> mList5, mList6 ;

    //    -----------個人帳戶 end
    private CircleImgView avatarsocial;
    private Button active;
    //---------------------------社群動態 end
    private Spinner sp0g21,sp0g21m;
    private CircleImgView avatar;
    public ArrayList<Map<String, Object>> mList3;
    public static ArrayList<Map<String, Object>> mList7,mListfr;
    private ArrayList<M_gPost> mData , myactlist;
    private ImageView im01, im04;
    private Spinner sp01, sp02;
    private ImageView avatarallact;
    private ImageView gavta,gavtaActvity;
    FloatingActionButton fmain;
    private DrawerLayout drawerlot;
    private ImageView drawermenu;
    private NavigationView navigationf;
    private Uri urimap,uriactdetail,uriline,uriinsta;
    public Dialog shareactdetailDlg;

    public static RecyclerView listViewaccount,listviewsocial,listviewmyfriend;
    private HashMap<String, Object> itemaccount;
    public static HashMap<String, Object> iteminnerresponse,myfriend;
    private M_gMyadapter_act adapteralact , adaptermyact;
    public static M_gMyadapter_account adapteraccount,adaptersocial;
    private RecyclerView listviewallact;
    //    private Myadapter_act adapter;
    private String api_key3 = "https://gis.taiwan.net.tw/XMLReleaseALL_public/activity_C_f.json";
    private String m_Response;
    public String mPicture1, mStart,mName,mLocation,mDescription,mWebsite,mMap;
    private Uri uriactmap;
    private Uri urifb;
    private SwipeRefreshLayout sw01;
    private FloatingActionButton fabdl;
    private M_gMyAlertDialog myAlertDialog;
    private ImageView actdetaildlgimg;
    private TextView actdetaildlgstim,actdetaildlgloca,actdetaildlgname,actdetaildlghswd;
    private Button actdetaildlgbtnmap,actdetaildlgbtnshare;
    public static String curD;
    private TextView txtimestamp;
    private SimpleDateFormat sdf,sdfTstamp;
    private Date currentTimestamp;
    public static String currentTimestampsdf;
    private Button m_gLiketx,m_gSharetx,m_gResponsetx;
    public static ImageView imgeoption;
    private ImageView m_gwaittoload;
    public static Dialog m_ginnersharerecyclervw;
    public static RecyclerView m_ginnersharedlgrv;
    public static EditText m_ginneredtx;
    public static ImageView m_ginnersend;
    public static ImageView closediginner;
    public static EditText edtextshare,edtextsharem;
    public static TextView tx3speechself;
    public static TextView firstpesponse;
    private RecyclerView listviewmyact;
    private BottomSheetDialog bottomSheetDialog;
    private View perbtmsheet;
    private Button m_gwobuttonbtmsht,m_gactbuttonbtmsht,m_gmedalbuttonbtmsht,m_gfrbuttonbtmsht;
    private TextView btmsheettitle;
    private BottomSheetBehavior mbottomsheetbehavior;
    private CircleImgView accountavatapic;
    private TextView accountname;
    private SearchView m_gactsearch,m_gmyact_search;
    private CircleImgView m_gactcolectavatapic;
    public static ConstraintLayout m_gnocollectact,m_gpromofirstshare;

    //---------db related --------------

    private M_gSDbHelper dbHper; //宣告class，產生新物件dbHper
    private static final String DB_FILE = "healthy.db";
    private static final int DBversion = 1;

    private TextView GmailAcc,GAccname;
    private View headerview;
    //--------db related -----------------
    private int[] imageemotion = {
            //感覺...
            R.drawable.a1f600, R.drawable.a1f92f, R.drawable.a1f630,R.drawable.a1f62d,
            R.drawable.a1f624, R.drawable.a1f60c,R.drawable.a1f92a, R.drawable.a1f629,
            R.drawable.a1f340,R.drawable.a1f971,R.drawable.a1f616, R.drawable.a1f92c,
            R.drawable.a1f60e, R.drawable.a1f636, R.drawable.a1f498,R.drawable.a1f44d,
            R.drawable.a1f924, R.drawable.a1f922,R.drawable.a1f917, R.drawable.a1f396,
            R.drawable.a1f911, R.drawable.a1f62c,R.drawable.a1f928, R.drawable.a1f635,
            R.drawable.a1f61f, R.drawable.a1f4aa,R.drawable.a1f925,
            //正在...
            R.drawable.a1f371,R.drawable.a1f3ae,R.drawable.a1f6e0, R.drawable.a1f697,
            R.drawable.a1f570, R.drawable.a1f93e, R.drawable.a1f3a7,R.drawable.a1f39e,
            R.drawable.a1f3d5, R.drawable.a1f389,R.drawable.a1f4d6, R.drawable.a1f9d8,
            R.drawable.a2615,R.drawable.a1f4ad,R.drawable.a1f964, R.drawable.a1f6d2,
            R.drawable.a1f399,R.drawable.a1f5e3,R.drawable.a1f440, R.drawable.a1f6cb,
            R.drawable.a1f3ac, R.drawable.a1f4dd,R.drawable.a1f49e,R.drawable.a1f919
    };
    private String[] textemotion = {
            //感覺...
            "感覺開心0x1F600", "感覺瘋狂0x1F92F", "感到難過0x1F630", "無比悲傷0x1F62D",
            "感到生氣0x1F624", "感覺平靜0x1F60C", "做著鬼臉0x1F92A", "無奈地說0x1F629",
            "感覺幸運0x1F340", "感覺無聊0x1F971", "感到頭痛0x1F616", "十分憤怒0x1F92C",
            "有成就感0x1F60E", "空虛的很0x1F636", "我戀愛了0x1F498", "十分推薦0x1F44D",
            "十分好吃0x1F924", "身體不適0x1F922", "神清氣爽0x1F917", "值得獎勵0x1F396",
            "感覺賺到0x1F911", "有點尷尬0x1F62C", "感到懷疑0x1F928", "感到混亂0x1F635",
            "覺得煩惱0x1F61F","充滿幹勁0x1F4AA","言不由衷0x1F925",
            //正在...
            "在進食中0x1F371","在玩電動0x1F3AE", "在作業中0x1F6E0", "在通勤中0x1F697",
            "在等待中0x1F570","在運動中0x1F93E", "在聽音樂0x1F3A7", "在看電影0x1F39E",
            "在旅行中0x1F3D5","在慶祝中0x1F389",
            "在閱讀中0x1F4D6", "在冥想中0x1F9D8", "在喝咖啡0x2615", "在思考中0x1F4AD",
            "在喝手搖0x1F964", "在購物中0x1F6D2", "在歡唱中0x1F399", "在開會中0x1F5E3",
            "在尋找中0x1F440", "在耍廢中0x1F6CB", "在直播中0x1F3AC", "在上課中0x1F4DD",
            "在戀愛中0x1F49E", "在夢想著0x1F9E9"
    };
    private GridView getGridViewemot;
    public static Button diginsertemojioBtnm,digshareBtnm;
    public static ImageView closedigm;
    public static ArrayList<ArrayList<Map<String, Object>>> Allresponseaeylist;
    private int insertemoji;
    private String insertemojistring;
    private int uniemoji;
    private StringBuilder sa;
    private TextView sharedlgfeel;
    public static TextView sharedlgfeel2;
    private String sqlctl;
    private Button m_gfriendadd;
    public static TextView m_ghideid;
    private M_gMyadapter_myfri adaptermyfriend;
    private String ser_msg;
    private int servermsgcolor;
    public static ArrayList<String> shareid = new ArrayList<>();
    private String dbinsert,dbinsert2;
    private String delactid;
    private Handler handler = new Handler();
    public static ArrayList<String> recSetresponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //-------------------遇到需處理大量資料，請加此段-------------------------
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        enableStrictMode(this);
        //---------------------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_gactivity_face);
//        FirebaseMessaging.getInstance().subscribeToTopic("news");
//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
//            @Override
//            public void onComplete(@NonNull Task<String> task) {
//                if (!task.isSuccessful())return;
//                String token = task.getResult();
//                Log.d(TAG, "onComplete: "+token);
//            }
//        });
        context = this;
        initDB();
        setupViewcomponent();
        dbmysql();
        showRec();
        showRecid();
        draweropen();
        dbmysql_lksh();
        showReclike();
        instance = this;
    }

    private void enableStrictMode(Context context) {
        //-------------抓取遠端資料庫設定執行續------------------------------
        StrictMode.setThreadPolicy(new
                StrictMode.
                        ThreadPolicy.Builder().
                detectDiskReads().
                detectDiskWrites().
                detectNetwork().
                penaltyLog().
                build());

        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder()
                        .detectLeakedSqlLiteObjects()
                        .penaltyLog()
                        .build());
    }

    private void showRec() {
        if (recSet.size() != 0) {
            for (int i = 0; i < recSet.size(); i++) {
                String[] fld = recSet.get(i).split("#");
                String[] edtxtsh = fld[1].split(":");
                myactlist.add(new M_gPost(edtxtsh[0],fld[6], fld[7], fld[2],fld[5], fld[3], fld[4],edtxtsh[1]));
                mData.add(new M_gPost(edtxtsh[0], fld[6], fld[7], fld[2], fld[5], fld[3],fld[4],edtxtsh[1]));
//               String mName, String mPicture1, String mLocation, String mStart, String mDescription, String mMap, String mWebsite
                adaptermyact = new M_gMyadapter_act(
                        M_gMainActivity.this,
                        myactlist);
                listviewmyact.setAdapter(adaptermyact);
                adaptermyact.setOnItemClickListener(adaptermyacton);
                recyclerviewmovemyact(listviewmyact, myactlist);
            }
        } else {
            Toast.makeText(getApplicationContext(),"尚無資料哦!",Toast.LENGTH_LONG).show();
        }
    }

    private void showRecid() {
        String[] fld = recSet3.get(0).split("#");
        m_ghideid.setText(fld[0]);
    }

    public void showRecshare() {
        if (recSet2.size() != 0) {
            for (int i = 0; i < recSet2.size(); i++) {
                Map<String, Object> itemreco = new HashMap<String, Object>();
                String[] fld = recSet2.get(i).split("#");
//                if(fld[5].equals(M_gLoginActivity.g_DisplayName)){
//
//                }
                try {
                    String[] edtxtsh = fld[1].split("：");
                    String[] edtxtsh0split = edtxtsh[0].split(":");
//                currentTimestampsdf = sdfTstamp.format(fld[6]);
                    itemreco.put("Picgavata",fld[3]);//頭像變化待撰寫，變數化!!!!!
                    itemreco.put("txtView01", fld[5]);//擷取帳戶名稱
                    itemreco.put("txtView02", edtxtsh0split[1]);//感覺如何待撰寫，變數化!!!!!
                    itemreco.put("txtView03", edtxtsh[1]);
                    itemreco.put("timestamp",getString(R.string.m_gsharetimestamp)+fld[4] );
                    itemreco.put("txtView05",edtxtsh0split[0]);
//       fld[6]         getString(R.string.m_gsharetimestamp)+currentTimestampsdf.substring(5)
//                currentTimestampsdf = sdfTstamp.format(currentTimestamp);
                    mList5.add(itemreco);
//                    Collections.reverse(mList5);

                    adapteraccount = new M_gMyadapter_account(
                            M_gMainActivity.this,
                            mList5);

                    listViewaccount.setAdapter(adapteraccount);
                    adapteraccount.buttonSetOnclick(inneraccountlikeon);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(mList5.size()==0){
                    m_gpromofirstshare.setVisibility(View.VISIBLE);
                }else{
                    m_gpromofirstshare.setVisibility(View.INVISIBLE);
                }
            }
        } else {
            Toast.makeText(getApplicationContext(),"未連線/尚無資料",Toast.LENGTH_LONG).show();
        }
    }

    public void showRecshareAll() {
        if (recSetshareALL.size() != 0) {
            for (int i = 0; i < recSetshareALL.size(); i++) {
                Map<String, Object> itemreco = new HashMap<String, Object>();
                String[] fld = recSetshareALL.get(i).split("#");
                String[] edtxtsh = fld[1].split("：");
                String[] edtxtsh0split = edtxtsh[0].split(":");
//                currentTimestampsdf = sdfTstamp.format(fld[6]);
                itemreco.put("Picgavata",fld[3]);//頭像變化待撰寫，變數化!!!!!
                itemreco.put("txtView01",fld[5]);//擷取帳戶名稱
                itemreco.put("txtView02", edtxtsh0split[1]);//感覺如何待撰寫，變數化!!!!!
                itemreco.put("txtView03", edtxtsh[1]);
                itemreco.put("timestamp",getString(R.string.m_gsharetimestamp)+fld[4] );
                itemreco.put("txtView05",edtxtsh0split[0]);
//       fld[6]         getString(R.string.m_gsharetimestamp)+currentTimestampsdf.substring(5)
//                currentTimestampsdf = sdfTstamp.format(currentTimestamp);
                mList6.add(itemreco);
//                Collections.reverse(mList6);

                adaptersocial = new M_gMyadapter_account(
                        M_gMainActivity.this,
                        mList6);
//                Collections.reverse(mList6);
                listviewsocial.setAdapter(adaptersocial);
                adaptersocial.buttonSetOnclick(innersociallikeon);
            }
        } else {
            Toast.makeText(getApplicationContext(),"未連線/尚無資料",Toast.LENGTH_LONG).show();
        }
    }

    public void showReclike() {
        shareid.clear();
        if (recSet4.size() != 0) {
            for (int i = 0; i < recSet4.size(); i++) {
                String[] fld = recSet4.get(i).split("#");
                shareid.add(fld[2]);
            }
        } else {
        }
//        dbHper.close();
    }

    private void initDB() {

        if (dbHper == null) {
            dbHper = new M_gSDbHelper(this, DB_FILE, null, DBversion);
        }
        recSet = dbHper.getRecSet();
        recSet3 = dbHper.getRecSetuserinfo();
        recSet4 = dbHper.getRecSetlike();
        recSetshareALL = dbHper.getRecSetAllsh();
        recSetresponse = dbHper.getRecSetbbs();
        int a = 0;
    }

    @SuppressLint({"CutPasteId", "DefaultLocale", "NotifyDataSetChanged", "WrongViewCast"})
    public void setupViewcomponent() {
        shareDlg();
        shareDlgmodify();
        shareactdetailDlg();
        m_ginnersharerecyclervw();
        showemotion();
        navigation();
        myactlist = new ArrayList<>();
        mData = new ArrayList<>();
        m_gwaittoload=(ImageView)findViewById(R.id.m_gwaittoload);
        m_gLiketx=(Button)findViewById(R.id.m_gLiketx);
        imgeoption=(ImageView)findViewById(R.id.imgeoption);
        m_gResponsetx=(Button)findViewById(R.id.m_gResponsetx);
        m_gSharetx=(Button)findViewById(R.id.m_gSharetx);
        txtimestamp=(TextView)findViewById(R.id.m_gsharetimestamp);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdfTstamp = new SimpleDateFormat("yyyy年MM月dd日 E '@' HH:mm");
        curD = sdf.format(new Date());

        sw01=findViewById(R.id.laySwipe);
        sw01.setSize(SwipeRefreshLayout.LARGE);
        sw01.setDistanceToTriggerSync(300);//拉動距離
        sw01.setProgressBackgroundColorSchemeColor(getColor(R.color.aqua));
        //變化顏色規矩
        sw01.setColorSchemeResources(
                R.color.colorAccent,
                R.color.navajowhite,
                R.color.darkorange,
                R.color.Purple
        );
        sw01.setProgressViewOffset(true, 0, 0);
//        onSwipeToRefresh.onRefresh();
        sw01.setOnRefreshListener(onSwipeToRefresh);

        Toolbar toolbar1 = (Toolbar)findViewById(R.id.toolbarnew);
        setSupportActionBar(toolbar1);
        //---------------toolbar-----
        gavta = findViewById(R.id.m_g003_im01);
        Picasso.with(context).load(M_gLoginActivity.personPhoto).into(gavta);
        //----fab----
        fmain =findViewById(R.id.fab_main);
        fmain.setOnClickListener(fmainon);
        //----fab----
        //-----navidrawer--
//        我的帳戶------
        m_ghideid = (TextView)findViewById(R.id.m_ghideid);
        m_gfriendadd=(Button)findViewById(R.id.m_gfriendadd);
        mg4b02=(Button)findViewById(R.id.m_g004_b02);
        mg4b04=(Button)findViewById(R.id.m_g004_b04);
        mg4b06=(Button)findViewById(R.id.m_g004_myact06);
        mg4bmedal=(Button)findViewById(R.id.m_g004_bmedal);
        mg4myfr=(Button)findViewById(R.id.m_g004_bmyfriend);
        mg4bmedal.setOnClickListener(mg4b02on);
        mg4b02.setOnClickListener(mg4b02on);
        mg4b04.setOnClickListener(mg4b02on);
        mg4b06.setOnClickListener(mg4b02on);
        mg4myfr.setOnClickListener(mg4b02on);
        m_gfriendadd.setOnClickListener(mg4b02on);
//        社群動態
        active=(Button)findViewById(R.id.m_g001_b02);
        active.setOnClickListener(activeon);
        avatarsocial=(CircleImgView)findViewById(R.id.m_g001_im01);
        avatarsocial.setOnClickListener(avatarsocialon);
        Picasso.with(context).load(M_gLoginActivity.personPhoto).into(avatarsocial);
//        社群動態 end
//        好動活動
        sp01 = (Spinner) findViewById(R.id.m_g003_s01);
        sp02 = (Spinner) findViewById(R.id.m_g003_s02);
        im04 = (ImageView) findViewById(R.id.m_g003_im04);
        im04.setOnClickListener(im04on);

        avatarallact=(ImageView)findViewById(R.id.m_g003_im01);
        avatarallact.setOnClickListener(avatarallacton);
        ArrayAdapter<CharSequence> spls = ArrayAdapter.createFromResource(
                this,
                R.array.m_g003_a001,
                R.layout.m_gspinner_gitem);
        sp01.setAdapter(spls);
        sp01.setOnItemSelectedListener(sp01on);
        ArrayAdapter<CharSequence> splsd = ArrayAdapter.createFromResource(
                this,
                R.array.m_g003_a00d,
                R.layout.m_gspinner_gitem
        );
        sp02.setAdapter(splsd);

//        avataract=(ImageView)findViewById(R.id.m_g005_im01);
//        avataract.setOnClickListener(avataracton);
        mList5 = new ArrayList<Map<String, Object>>();//account
        mList6 = new ArrayList<Map<String, Object>>();//social
        mList7 = new ArrayList<Map<String, Object>>();//innerresponse
        mListfr = new ArrayList<Map<String, Object>>();//friend
        Allresponseaeylist = new ArrayList<ArrayList<Map<String, Object>>>();
        for(int i=0; i<=mList5.size() ; i++){
            Allresponseaeylist.add(mList7);
        }
//        我的活動end
//        我的好友
        avatar = (CircleImgView) findViewById(R.id.m_g005_im01);
        Picasso.with(context).load(M_gLoginActivity.personPhoto).into(avatar);
        avatar.setOnClickListener(avataron);
//        我的好友
        lout5=(RelativeLayout)findViewById(R.id.Lo5);
        lout3=(RelativeLayout)findViewById(R.id.Lo3);
        plout1=(CoordinatorLayout)findViewById(R.id.PLo1);
        plout2=(RelativeLayout)findViewById(R.id.PLo2);
        plout3=(RelativeLayout)findViewById(R.id.PLo3);
        fabdl=(FloatingActionButton)findViewById(R.id.fab_download);
        fabdl.setOnClickListener(fabdlonclick);
        actdetaildlgimg = (ImageView) shareactdetailDlg.findViewById(R.id.m_gactdetaildlgimg);
        actdetaildlgstim = shareactdetailDlg.findViewById(R.id.m_gactdetaildlghoswdT);
        actdetaildlgloca = shareactdetailDlg.findViewById(R.id.m_gactdetaildlghoswdL);
        actdetaildlgname = shareactdetailDlg.findViewById(R.id.m_gactdetaildlghoswdN);
        actdetaildlghswd = shareactdetailDlg.findViewById(R.id.m_gactdetaildlghoswdW);
        actdetaildlgbtnmap = shareactdetailDlg.findViewById(R.id.m_gactmap002_b05);
        actdetaildlgbtnshare = shareactdetailDlg.findViewById(R.id.m_gactdetail002_b04);
        //------我的好友
        listviewmyfriend=(RecyclerView) findViewById(R.id.listfr);
        listviewmyfriend.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //----------------我的帳戶------
        listViewaccount=findViewById(R.id.listmyself);
        listViewaccount.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        listViewaccount.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        listviewsocial = findViewById(R.id.listsocial);
        listviewsocial.setLayoutManager(new LinearLayoutManager(M_gMainActivity.this,
                LinearLayoutManager.VERTICAL,false));
        //-----------------活動----------
        listviewallact = findViewById(R.id.listactall);//recyclerview
        listviewallact.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        listviewallact.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        listviewallact.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        listviewmyact = findViewById(R.id.listmyact);
        listviewmyact.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        listviewmyact.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        listviewmyact.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        m_gmyact_search = (SearchView)findViewById(R.id.act_search);//myactcollect adaptermyact
        m_gactsearch = (SearchView)findViewById(R.id.m_gactsearch);
        m_gactsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapteralact.getFilter().filter(query);
                m_gactsearch.setOnCloseListener(new SearchView.OnCloseListener() {
                    @Override
                    public boolean onClose() {
                        adapteralact.getFilter().filter("");
                        return false;
                    }
                });
                InputMethodManager m=(InputMethodManager) M_gMainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapteralact.getFilter().filter(newText);
                return false;
            }
        });
        m_gmyact_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adaptermyact.getFilter().filter(query);
                m_gmyact_search.setOnCloseListener(new SearchView.OnCloseListener() {
                    @Override
                    public boolean onClose() {
                        adaptermyact.getFilter().filter("");
                        return false;
                    }
                });
                InputMethodManager m=(InputMethodManager) M_gMainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adaptermyact.getFilter().filter(newText);
                return false;
            }
        });
        m_gpromofirstshare = (ConstraintLayout)findViewById(R.id.m_gpromofirstshare);
        m_gnocollectact = (ConstraintLayout)findViewById(R.id.m_gnocollectact);
        m_gactcolectavatapic= findViewById(R.id.m_gact005_im01);
        Picasso.with(context).load(M_gLoginActivity.personPhoto).into(m_gactcolectavatapic);
        perbtmsheet = findViewById(R.id.perbtmsheet);
        mbottomsheetbehavior = BottomSheetBehavior.from(perbtmsheet);
        btmsheettitle = perbtmsheet.findViewById(R.id.btmsheettitle);
        m_gwobuttonbtmsht =perbtmsheet.findViewById(R.id.m_gwobuttonbtmsht);
        m_gactbuttonbtmsht =perbtmsheet.findViewById(R.id.m_gactbuttonbtmsht);
        m_gfrbuttonbtmsht =perbtmsheet.findViewById(R.id.m_gfrbuttonbtmsht);
        m_gwobuttonbtmsht.setOnClickListener(m_gwobuttonbtmshton);
        m_gactbuttonbtmsht.setOnClickListener(m_gwobuttonbtmshton);
        m_gfrbuttonbtmsht.setOnClickListener(m_gwobuttonbtmshton);
//        m_gmedalbuttonbtmsht.setOnClickListener(m_gwobuttonbtmshton);
        btmsheettitle.setOnClickListener(m_gwobuttonbtmshton);
        accountavatapic = findViewById(R.id.m_g004_im01);
        accountname = findViewById(R.id.m_g004_account);
        accountname.setText(M_gLoginActivity.g_DisplayName);
        Picasso.with(context).load(M_gLoginActivity.personPhoto).into(accountavatapic);

        try {
            dbmysql_selfshare();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            dbmysql_allshare();
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            showRecshare();
        }catch (Exception e){
            e.printStackTrace();
        }
//        dbHper.close();
    }

    final int autotime =120;
    final Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, autotime * 1000);
            mList6.clear();
            dbmysql_allshare();
            showRecshareAll();
            if(lout5.getVisibility()==View.VISIBLE){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(this, autotime * 500);
                        Toast.makeText(getApplicationContext(),"更新中...",Toast.LENGTH_SHORT).show();
                    }
                });

            }else{
                handler.removeCallbacks(updateTimer);
            }
        }
    };


    private void navigation(){
        drawerlot=(DrawerLayout)findViewById(R.id.drawerlout);
        drawermenu=(ImageView)findViewById(R.id.drawermenu);
        drawermenu.setOnClickListener(drawermenuon);
        navigationf=(NavigationView)findViewById(R.id.navidrawerf);
        headerview = navigationf.getHeaderView(0);
        navigationf.setItemIconTintList(null);
        navigationf.bringToFront();
        navigationf.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.m_gnav001:
                        setupViewcomponent();
                        plout1.setVisibility(View.VISIBLE);
                        lout3.setVisibility(View.INVISIBLE);
                        lout5.setVisibility(View.INVISIBLE);
                        plout2.setVisibility(View.INVISIBLE);
                        plout3.setVisibility(View.INVISIBLE);
                        fabdl.setVisibility(View.INVISIBLE);
                        drawerlot.closeDrawers();
                        break;
                    case R.id.m_gnav002:
                        lout3.setVisibility(View.VISIBLE);
                        onSwipeToRefresh.onRefresh();
                        m_gwaittoload.setVisibility(View.INVISIBLE);
                        lout5.setVisibility(View.INVISIBLE);
                        plout1.setVisibility(View.INVISIBLE);
                        plout2.setVisibility(View.INVISIBLE);
                        plout3.setVisibility(View.INVISIBLE);
                        drawerlot.closeDrawers();
                        break;
                    case R.id.m_gnav003:
                        initDB();
                        handler.postDelayed(updateTimer, 500);
                        lout5.setVisibility(View.VISIBLE);
                        lout3.setVisibility(View.INVISIBLE);
                        fabdl.setVisibility(View.INVISIBLE);
                        plout1.setVisibility(View.INVISIBLE);
                        plout2.setVisibility(View.INVISIBLE);
                        plout3.setVisibility(View.INVISIBLE);
                        drawerlot.closeDrawers();
                        break;
                    case R.id.m_gnav004:
                        plout2.setVisibility(View.VISIBLE);
                        lout5.setVisibility(View.INVISIBLE);
                        lout3.setVisibility(View.INVISIBLE);
                        fabdl.setVisibility(View.INVISIBLE);
                        plout1.setVisibility(View.INVISIBLE);
                        plout3.setVisibility(View.INVISIBLE);
                        drawerlot.closeDrawers();

                        break;
//                    case R.id.m_gnav005:
//                        intent.setClass(M_gMainActivity.this, M_gcurve.class);
//                        startActivity(intent);
//                        drawerlot.closeDrawers();
//                        break;
                    case R.id.m_gnav006:
                        myactlist.clear();
                        dbmysql();
                        initDB();
                        showRec();
                        plout3.setVisibility(View.VISIBLE);
                        m_gnocollectact.getVisibility();
                        if(myactlist.size()==0){
                            m_gnocollectact.setVisibility(View.VISIBLE);
                            m_gmyact_search.setVisibility(View.INVISIBLE);
                        }else{
                            m_gnocollectact.setVisibility(View.INVISIBLE);
                            m_gmyact_search.setVisibility(View.VISIBLE);
                        }
                        lout5.setVisibility(View.INVISIBLE);
                        lout3.setVisibility(View.INVISIBLE);
                        fabdl.setVisibility(View.INVISIBLE);
                        plout1.setVisibility(View.INVISIBLE);
                        plout2.setVisibility(View.INVISIBLE);
                        drawerlot.closeDrawers();
                        break;
                    case R.id.m_gnav007:
                        urimap = Uri.parse("http://google.com/maps");
                        Intent it = new Intent(Intent.ACTION_VIEW, urimap);
                        startActivity(it);
                        drawerlot.closeDrawers();
                        break;
                    case R.id.m_gnav008:
                        urifb = Uri.parse("https://www.facebook.com");
                        Intent itfb = new Intent(Intent.ACTION_VIEW, urifb);
                        startActivity(itfb);
                        drawerlot.closeDrawers();
                        break;
                    case R.id.m_gnav009:
                        uriinsta = Uri.parse("http://instagram.com/");
                        Intent itinsta = new Intent(Intent.ACTION_VIEW, uriinsta);
                        startActivity(itinsta);
                        drawerlot.closeDrawers();
                        break;
                    case R.id.m_gnav010:
                        uriline = Uri.parse("https://line.me/R/nv/chat");
                        Intent itline = new Intent(Intent.ACTION_VIEW, uriline);
                        startActivity(itline);
                        drawerlot.closeDrawers();
                        break;
                    case R.id.m_gnav011:
                        Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_LONG).show();

                        break;
//                    case R.id.medal_fl001:
//                        intent.setClass(M_gMainActivity.this, M_gmedal.class);
//                        startActivity(intent);
//                        drawerlot.closeDrawers();
//                        finish();
//                        break;
                }
                return false;
            }
        });
    }

    private void myfriendrecycleview() {
        myfriend = new HashMap<String, Object>();
        myfriend.put("Picgavata", M_gLoginActivity.personPhoto);//頭像變化待撰寫，變數化!!!!!
        myfriend.put("txtView01", M_gLoginActivity.g_DisplayName);//擷取帳戶名稱
        myfriend.put("txtView02", "XXX");
        myfriend.put("txtView03", M_gLoginActivity.g_Email);
        mListfr.add(myfriend);
        adaptermyfriend = new M_gMyadapter_myfri(
                this,
                mListfr);
        listviewmyfriend.setAdapter(adaptermyfriend);
        adaptermyfriend.setOnItemClickListener(adaptermyfriendon);
        adaptermyfriend.notifyDataSetChanged();
    }

    private M_gMyadapter_myfri.OnItemClickListener adaptermyfriendon =
            new M_gMyadapter_myfri.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
//            Toast.makeText(getApplicationContext(),"TEST"+position,Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener m_gwobuttonbtmshton = new View.OnClickListener() {
        int x=0;
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.m_gwobuttonbtmsht:
//                    Toast.makeText(getApplicationContext(), "TestQQQ", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.m_gactbuttonbtmsht:
                    myactlist.clear();
                    initDB();
                    showRec();
                    plout3.setVisibility(View.VISIBLE);
                    lout3.setVisibility(View.INVISIBLE);
                    mbottomsheetbehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    break;
                case R.id.m_gfrbuttonbtmsht:
                    plout2.setVisibility(View.VISIBLE);

                    break;
//                case R.id.m_gmedalbuttonbtmsht:
//                    intent.setClass(M_gMainActivity.this, M_gmedal.class);
//                    startActivity(intent);
//                    finish();
//                    break;
                case R.id.btmsheettitle:
                    if(x<1){
                        mbottomsheetbehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        x=x+1;
                    }else{
                        mbottomsheetbehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        x=0;
                    }
                    break;
            }
        }
    };

    private void showBottomsheet() {
        final  BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.m_gbottomsheetdialg);
        LinearLayout m_gshareactinside = bottomSheetDialog.findViewById(R.id.m_gshareactinside);
        LinearLayout m_gsharetoline = bottomSheetDialog.findViewById(R.id.m_gsharetoline);
        LinearLayout m_gsharefromemail = bottomSheetDialog.findViewById(R.id.m_gsharefromemail);
        m_gsharefromemail.setOnClickListener(m_gsharefromemailon);
        m_gsharetoline.setOnClickListener(m_gsharefromemailon);
        m_gshareactinside.setOnClickListener(m_gsharefromemailon);
        bottomSheetDialog.show();
    }

    View.OnClickListener m_gsharefromemailon = new View.OnClickListener() {
        private String actnamemessage,actlocamessage,actwebsitemessage,acttimemessage;

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.m_gshareactinside:
                    int unicodea = 0x1F600;
                    int unicodeb = 0x1F44B;
                    int unicodec = 0x1F4F7;
                    StringBuilder sa = new StringBuilder();
                    StringBuilder sb = new StringBuilder();
                    sa.append(Character.toChars(unicodea));
                    sa.append(Character.toChars(unicodec));
                    sb.insert(0,Character.toChars(unicodeb));
                    actnamemessage=actdetaildlgname.getText().toString();
                    actlocamessage=actdetaildlgloca.getText().toString();
                    acttimemessage=actdetaildlgstim.getText().toString();
                    actwebsitemessage = uriactdetail.toString();
                    shareDlg.show();
                    edtextshare.setText(sb+"走吧!我們一起去參加"+actnamemessage+sa+"\n"
                            +actlocamessage+"\n"
                            +acttimemessage+actwebsitemessage);
                    break;
                case R.id.m_gsharetoline:
                    int unicode4 = 0x1F600;
                    int unicode5 = 0x1F44B;
                    int unicode6 = 0x1F4F7;
                    StringBuilder s = new StringBuilder();
                    StringBuilder s2 = new StringBuilder();
                    s.append(Character.toChars(unicode4));
                    s.append(Character.toChars(unicode6));
                    s2.insert(0,Character.toChars(unicode5));
                    actnamemessage=actdetaildlgname.getText().toString();
                    actlocamessage=actdetaildlgloca.getText().toString();
                    acttimemessage=actdetaildlgstim.getText().toString();
                    actwebsitemessage = uriactdetail.toString();
                    Intent it2 = new Intent(Intent.ACTION_SEND);
                    it2.putExtra(Intent.EXTRA_EMAIL, M_gLoginActivity.g_Email);
                    it2.putExtra(Intent.EXTRA_SUBJECT,s2+"'Let's go!我們一起去參加~'"+actnamemessage+"'~  "+s);
//                    it2.putExtra(Intent.EXTRA_SUBJECT,);
                    it2.putExtra(Intent.EXTRA_TEXT, s2+"'Let's go!我們一起去參加~'"+actnamemessage+"'~  "+s+"\r\n"+actlocamessage+"\r\n"
                            +acttimemessage+"\r\n"+actwebsitemessage);
                    it2.setType("text/html");
                    startActivity(Intent.createChooser(it2,"分享至LINE"));
//                    onShareClick(v);
                    break;
                case R.id.m_gsharefromemail:
//                    Toast.makeText(getApplicationContext(), "sharebyemail", Toast.LENGTH_SHORT).show();
                    int unicode = 0x1F600;
                    int unicode2 = 0x1F44B;
                    int unicode3 = 0x1F4F7;
                    StringBuilder s3 = new StringBuilder();
                    StringBuilder s4 = new StringBuilder();
                    s3.append(Character.toChars(unicode));
                    s3.append(Character.toChars(unicode3));
                    s4.insert(0,Character.toChars(unicode2));
                    actnamemessage=actdetaildlgname.getText().toString();
                    actlocamessage=actdetaildlgloca.getText().toString();
                    acttimemessage=actdetaildlgstim.getText().toString();
                    actwebsitemessage = uriactdetail.toString();

                    Intent it = new Intent(Intent.ACTION_SEND);
                    it.putExtra(Intent.EXTRA_EMAIL, M_gLoginActivity.g_Email);
                    it.putExtra(Intent.EXTRA_SUBJECT,s4+"'Let's go!我們一起去參加~'"+actnamemessage+"'~  "+s3);
                    it.putExtra(Intent.EXTRA_TEXT, actnamemessage+"\r\n"+actlocamessage+"\r\n"
                            +acttimemessage+"\r\n"+actwebsitemessage);
//                    <a href="連結網址" target="連結目標" title="替代文字">顯示內容</a> Html.fromHtml
//                    "<a href=\""+actwebsitemessage+"\" target=\"_blank\" >前往活動官網</a>"
                    it.setType("text/html");
                    startActivity(Intent.createChooser(it, "從Gmail分享"));
//                    bottomSheetDialog.dismiss();
                    break;
            }
        }
    };


    private SwipeRefreshLayout.OnRefreshListener onSwipeToRefresh =
            new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    myAlertDialog =new M_gMyAlertDialog(M_gMainActivity.this);
                    myAlertDialog.setTitle(getString(R.string.activity));
                    myAlertDialog.setMessage(getString(R.string.dialog_t001)+getString(R.string.dialog_b001));
                    myAlertDialog.setIcon(android.R.drawable.ic_dialog_info);
                    myAlertDialog.setCancelable(false);
                    myAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE,getString(R.string.dialog_positive),altPsbt);
                    myAlertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,getString(R.string.dialog_neutral),altNebt);
                    if(findViewById(R.id.Lo3).getVisibility()==View.VISIBLE){
                        myAlertDialog.show();
                    }

                    sw01.setRefreshing(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sw01.setRefreshing(false);
                        }
                    }, 50);
                }
            };



    private final DialogInterface.OnClickListener altPsbt = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            fabdl.setVisibility(View.INVISIBLE);
            //按下確定下子資料後的動作，貼在Dialoginterface確定按鈕的程式內
            sw01.setRefreshing(true);//呼叫下拉刷新方法

            //---------------
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    OkHttpClient client = new OkHttpClient();
                    String url = api_key3;
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    client.newCall(request).enqueue(new Callback() {


                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            e.printStackTrace();
//                Toast.makeText(getApplicationContext(), "connect failure", Toast.LENGTH_SHORT).show();
//              ------ 連線失敗,做甚麼事------
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            if (response.isSuccessful()) {
                                m_Response = response.body().string();
                                int a = 0;
                                M_gMainActivity.this.runOnUiThread(new Runnable() {
                                    private int dcur, d1,d2,endy,cury;
                                    private String Start, End;
                                    private String Description;
                                    private String Picture1;
                                    @Override
                                    public void run() {

                                        try {
                                            JSONObject jsonData = new JSONObject(m_Response);
                                            JSONObject jsondata2 = jsonData.getJSONObject("XML_Head");
                                            JSONObject jsondata3 = jsondata2.getJSONObject("Infos");
                                            JSONArray m_JSONArry = jsondata3.getJSONArray("Info");
                                            //可針對m_JSONArry進行sorting......待續
                                            mList3 = new ArrayList<Map<String, Object>>();

                                            Integer x = m_JSONArry.length();
                                            for (int i = 0; i < m_JSONArry.length(); i++) {
                                                JSONObject jsonData3 = m_JSONArry.getJSONObject(i);
                                                String Py = jsonData3.getString("Py");
                                                String Px = jsonData3.getString("Px");
                                                String Name = jsonData3.getString("Name");
                                                String Location = jsonData3.getString("Add");
                                                String Region = jsonData3.getString("Region");
                                                Start = jsonData3.getString("Start");
                                                End = jsonData3.getString("End");

                                                if (jsonData3.getString("Picture1").length() > 0) {
                                                    Picture1 = jsonData3.getString("Picture1");
                                                }
                                                String Website = jsonData3.getString("Website");
                                                if (jsonData3.getString("Description").length() > 40 && !Website.isEmpty()) {
                                                    Description = jsonData3.getString("Description").substring(0, 41) + "...";
                                                } else {
                                                    Description = jsonData3.getString("Description");
                                                }

                                                Map<String, Object> data = new HashMap<String, Object>();
                                                data.put("Name", Name);
                                                data.put("Location", Region+"/"+Location);
                                                data.put("Picture1", Picture1);
                                                data.put("Description", Description);
                                                data.put("Start", Start.substring(0, 11));
                                                data.put("End", End.substring(0, 11));
                                                data.put("Website", Website);
                                                data.put("Map", Py + "," + Px);
                                                mList3.add(data);

                                                mData = new ArrayList<>();
                                                for (Map<String, Object> m : mList3) {
                                                    if (m != null) {
                                                        Date curDint = null;
                                                        try {
                                                            curDint = sdf.parse(curD);
                                                        }catch ( ParseException e){
                                                            e.printStackTrace();
                                                        }

                                                        Date strD = null;
                                                        try {
                                                            strD = sdf.parse(m.get("Start").toString());
                                                        }catch ( ParseException e){
                                                            e.printStackTrace();
                                                        }
                                                        Date endD = null;
                                                        try{
                                                            endD = sdf.parse(m.get("End").toString());
                                                        }catch ( ParseException e){
                                                            e.printStackTrace();
                                                        }
                                                        Calendar st = Calendar.getInstance();
                                                        st.setTime(strD);
                                                        Calendar ed = Calendar.getInstance();
                                                        ed.setTime(endD);
                                                        endy=ed.get(Calendar.YEAR);
                                                        Calendar curd = Calendar.getInstance();
                                                        curd.setTime(curDint);
                                                        cury=curd.get(Calendar.YEAR);
                                                        dcur = curd.get(Calendar.DAY_OF_YEAR);
                                                        d1 = st.get(Calendar.DAY_OF_YEAR);
                                                        d2 = ed.get(Calendar.DAY_OF_YEAR);
                                                        String replaceemptypic = "https://iyung0926.000webhostapp.com/opendata/nopic1.jpg";
                                                        if ( (endy-cury)>=0  && d2 - dcur > 0) {
                                                            mWebsite = m.get("Website").toString().trim();
                                                            mName = m.get("Name").toString().trim(); //名稱
                                                            mMap = m.get("Map").toString().trim();
                                                            mPicture1 = m.get("Picture1").toString().trim(); //圖片

                                                            //-----------------
                                                            if (mPicture1.isEmpty() || mPicture1.length()<5 || mPicture1==null){
                                                                mPicture1 =replaceemptypic.trim();
                                                            }
                                                            //-------------------------------------------------------------------------
                                                            mLocation = "地點@" + m.get("Location").toString().trim(); //描述

                                                            int duration = 0;
                                                            if((d2 - d1 + 1)>0){
                                                                duration = d2 - d1 + 1;
                                                            }else{ duration = 366-d1+d2; }

                                                            mStart = m.get("Start").toString().trim().substring(5, 7) + "月" +
                                                                    m.get("Start").toString().trim().substring(8, 10) + "日起，為期" + duration + "天";
                                                            mDescription = m.get("Description").toString().trim();
                                                            mData.add(new M_gPost(mName, mPicture1, mLocation, mStart, mDescription, mMap,mWebsite ,"default"));

                                                        }

                                                    } else {
                                                        return;
                                                    }
                                                }

                                                adapteralact = new M_gMyadapter_act(
                                                        M_gMainActivity.this,
                                                        mData
                                                );

                                                listviewallact.setAdapter(adapteralact);//

                                                recyclerviewmove(listviewallact, mList3);

                                                adapteralact.setOnItemClickListener(adapteralacton);

                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                    });

                    sw01.setRefreshing(false);
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout),
                            "下載完成，更新畫面中，請稍候...", Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(Color.BLUE);
                    snackbar.getView().setBackgroundColor(Color.WHITE);
                    TextView tv = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
                    Drawable dw = ContextCompat.getDrawable(M_gMainActivity.this, R.drawable.ic_edit_note_blue);
                    dw.setBounds(0, 0, dw.getMinimumHeight(), dw.getMinimumHeight());
                    tv.setCompoundDrawables(dw, null, null, null);
                    tv.setGravity(Gravity.CENTER);

                    snackbar.show();

                }
            }, 3000);  //讓圓圈轉幾秒
        }

    };

    private M_gMyadapter_act.OnItemClickListener adapteralacton = new M_gMyadapter_act.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            shareactdetailDlg.show();

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    if (mData.get(position).mPicture.contains("http")) {
                        Glide.with(M_gMainActivity.this).load(mData.get(position).mPicture).into(actdetaildlgimg);
                    } else {
                        Glide.with(M_gMainActivity.this).
                                load("https://iyung0926.000webhostapp.com/opendata/nopic1.jpg").into(actdetaildlgimg);
                    }
                }
            });

            actdetaildlgbtnmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uriactmap = Uri.parse("http://maps.google.com/maps?q=" + mData.get(position).mMap+
                            "(" + mData.get(position).mLocation + ")&iwloc=A&hl=h&l=zh-TW");

//                  mData.get(position).mMap+"("+mData.get(position).mLocation+")&iwloc=A&hl=h&l=zh-TW");
                    Intent itmap = new Intent(Intent.ACTION_VIEW, uriactmap);
                    startActivity(itmap);
                }
            });
            actdetaildlgbtnshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showBottomsheet();
                }
            });
            actdetaildlgname.setText(mData.get(position).mName);
            actdetaildlgloca.setText(mData.get(position).mLocation);
            actdetaildlgstim.setText(mData.get(position).mStart);
            actdetaildlghswd.setText(mData.get(position).mDescription);
            actdetaildlgimg.setScaleType(ImageView.ScaleType.FIT_XY);
            TextView actdetaildlghoswd04 = shareactdetailDlg
                    .findViewById(R.id.m_gactdetaildlgredmore);
            uriactdetail = Uri.parse(mData.get(position).mWebsite);
            if (mData.get(position).mWebsite.length() > 0) {
//                                                            urlweb.get(position).toString().length() > 0
                actdetaildlghoswd04.setText(getString(R.string.m_gmoreinfo));
                actdetaildlghoswd04.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        uriactdetail = Uri.parse(mData.get(position).mWebsite);
                        Intent itfb = new Intent(Intent.ACTION_VIEW, uriactdetail);
                        startActivity(itfb);
                    }
                });
            } else {
                actdetaildlghoswd04.setText("");
            }
        }
    };



    private DialogInterface.OnClickListener altNebt=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
//            u_loading.setVisibility(View.GONE);
            sw01.setRefreshing(false);

            if(mList3 == null){
                fabdl.setVisibility(View.VISIBLE);
                m_gwaittoload.setVisibility(View.VISIBLE);
            }else{
                fabdl.setVisibility(View.INVISIBLE);
                m_gwaittoload.setVisibility(View.INVISIBLE);
            }
        }
    };



    private void recyclerviewmove(RecyclerView recyclerView, ArrayList<Map<String, Object>> xxx){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
            }
            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
//                int position_dragged = viewHolder.getBindingAdapterPosition();
//                int position_target = target.getBindingAdapterPosition();
//                if(position_dragged<position_target){
//                    for(int i=position_dragged;i<position_target;i++)
//                    {Collections.swap(mList3,i,i+1);}
//                }else {
//                    for (int i=position_dragged;i>position_target;i--)
//                    {Collections.swap(mList3,i,i-1);}
//                }
////                Collections.swap(mList3, position_dragged, position_target);
//                adapteralact.notifyItemMoved(position_dragged, position_target);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getBindingAdapterPosition();

                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        AlertDialog.Builder builderS=new AlertDialog.Builder(M_gMainActivity.this);
                        builderS.setTitle(getString(R.string.m_g999_storeact));
                        builderS.setIcon(R.drawable.ic_edit_note_blue);
                        builderS.setMessage(getString(R.string.m_g999_confirmstoreact));
                        builderS.setCancelable(false);

                        builderS.setPositiveButton("確定收藏", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //------還要加到收藏之中-----------!!!!!
                                mName=mData.get(position).mName;
                                mLocation=mData.get(position).mLocation;
                                mPicture1=mData.get(position).mPicture;
                                mStart= mData.get(position).mStart;
                                mDescription = mData.get(position).mDescription;
                                mMap =mData.get(position).mMap;
                                mWebsite=mData.get(position).mWebsite;
                                myactlist.add(new M_gPost( mName,mPicture1,mLocation,mStart,mDescription,
                                        mMap,mWebsite,"default"));
                                adapteralact.notifyItemRemoved(position);
                                adapteralact.notifyItemRangeChanged(0, adapteralact.getItemCount());

                                adaptermyact = new M_gMyadapter_act(
                                        M_gMainActivity.this,
                                        myactlist);
                                listviewmyact.setAdapter(adaptermyact);
                                adaptermyact.setOnItemClickListener(adaptermyacton);
                                recyclerviewmovemyact(listviewmyact, myactlist);
                                mData.remove(position);
                                dbHper.insertRecact(myactlist, M_gSDbHelper.suuid);
//                                dbHper.close();
                                mysql_insertact();
                            }
                        });
                        builderS.setNeutralButton("不收藏", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapteralact.notifyItemChanged(viewHolder.getAbsoluteAdapterPosition());
                            }
                        });
                        AlertDialog alertDialog=builderS.create();
                        alertDialog.show();
                        alertDialog.getWindow().getAttributes();
                        TextView textView=(TextView) alertDialog.findViewById(android.R.id.message);
                        textView.setTextSize(20);
                        textView.setTextColor(Color.parseColor("#071F68"));
                        break;
                    case ItemTouchHelper.RIGHT:
                        AlertDialog.Builder builder=new AlertDialog.Builder(M_gMainActivity.this);
                        builder.setTitle(getString(R.string.m_g999_delact));
                        builder.setIcon(R.drawable.ic_edit_note);
                        builder.setMessage(getString(R.string.m_g999_confirmdelact));
                        builder.setCancelable(false);
                        builder.setPositiveButton("確定刪除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mData.remove(position);
                                adapteralact.notifyItemRemoved(position);

                                //-----如何UNDO回復/後悔-----未完待續
                            }
                        });
                        builder.setNeutralButton("不刪除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapteralact.notifyItemChanged(viewHolder.getAbsoluteAdapterPosition());
                            }
                        });
                        AlertDialog alertDialogdel=builder.create();
                        alertDialogdel.show();
                        alertDialogdel.getWindow().getAttributes();
                        TextView textViewdel=(TextView) alertDialogdel.findViewById(android.R.id.message);
                        textViewdel.setTextSize(20);
                        textViewdel.setTextColor(Color.parseColor("#DC1515"));
                        break;
                }
//                initDB();
//                myactlist.clear();
//                showRec();
            }
            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    // Get RecyclerView item from the ViewHolder
                    View itemView = viewHolder.itemView;
                    Paint p = new Paint();
                    Bitmap bitmap;
                    if (dX > 0) {
                        /* Set your color for positive displacement */
                        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.trashcan);
                        p.setARGB(200, 255, 0, 0);
//                         Draw Rect with varying right side, equal to displacement dX
                        c.drawBitmap(bitmap,
                                (float) itemView.getLeft() +convertDpToPx(15),
                                (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - bitmap.getHeight())/2,
                                p);
                        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                                (float) itemView.getBottom(), p);
                    } else {
                        /* Set your color for negative displacement */
                        p.setARGB(200, 0, 100, 10);
                        // Draw Rect with varying left side, equal to the item's right side plus negative displacement dX
                        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.save);
                        c.drawBitmap(bitmap,
                                (float) itemView.getRight() -convertDpToPx(15)-bitmap.getWidth(),
                                (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - bitmap.getHeight())/2,
                                p);
                        c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                                (float) itemView.getRight(), (float) itemView.getBottom(), p);
                    } super.onChildDraw(c, recyclerView, viewHolder, dX/3, dY, actionState, isCurrentlyActive);
                }
            }
// --------------  add here  --------------------------

        });
        helper.attachToRecyclerView(recyclerView);
    }

    private void recyclerviewmovemyact(RecyclerView recyclerView, ArrayList<M_gPost> xxx){
        ItemTouchHelper helper2 = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0,ItemTouchHelper.RIGHT);
            }
            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getBindingAdapterPosition();

                switch (direction) {

                    case ItemTouchHelper.RIGHT:
                        AlertDialog.Builder builder=new AlertDialog.Builder(M_gMainActivity.this);
                        builder.setTitle(getString(R.string.m_g999_delact));
                        builder.setIcon(R.drawable.ic_edit_note);
                        builder.setMessage(getString(R.string.m_g999_confirmdelact));
                        builder.setCancelable(false);
                        builder.setPositiveButton("確定刪除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHper.deleteRec(myactlist.get(position).mName);
                                delactid = myactlist.get(position).id;
                                myactlist.remove(position);
                                adaptermyact.notifyItemRemoved(position);
                                if(myactlist.size()==0){
                                    m_gnocollectact.setVisibility(View.VISIBLE);
                                    m_gmyact_search.setVisibility(View.INVISIBLE);
                                }else{
                                    m_gnocollectact.setVisibility(View.INVISIBLE);
                                    m_gmyact_search.setVisibility(View.VISIBLE);
                                }
                                mysql_delact();
                                //-----如何UNDO回復/後悔-----未完待續

                            }
                        });
                        builder.setNeutralButton("不刪除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adaptermyact.notifyItemChanged(viewHolder.getAbsoluteAdapterPosition());
                            }
                        });
                        AlertDialog alertDialogdel=builder.create();
                        alertDialogdel.show();
                        alertDialogdel.getWindow().getAttributes();
                        TextView textViewdel=(TextView) alertDialogdel.findViewById(android.R.id.message);
                        textViewdel.setTextSize(20);
                        textViewdel.setTextColor(Color.parseColor("#DC1515"));
                        break;
                }
            }
            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    // Get RecyclerView item from the ViewHolder
                    View itemView = viewHolder.itemView;
                    Paint p = new Paint();
                    Bitmap bitmap;
                    if (dX > 0) {
                        /* Set your color for positive displacement */
                        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.trashcan);
                        p.setARGB(200, 255, 0, 0);
//                         Draw Rect with varying right side, equal to displacement dX
                        c.drawBitmap(bitmap,
                                (float) itemView.getLeft() +convertDpToPx(15),
                                (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - bitmap.getHeight())/2,
                                p);
                        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                                (float) itemView.getBottom(), p);
                    } else {
                        /* Set your color for negative displacement */
                        p.setARGB(200, 0, 100, 10);
                        // Draw Rect with varying left side, equal to the item's right side plus negative displacement dX
                        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.save);
                        c.drawBitmap(bitmap,
                                (float) itemView.getRight() -convertDpToPx(15)-bitmap.getWidth(),
                                (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - bitmap.getHeight())/2,
                                p);
                        c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                                (float) itemView.getRight(), (float) itemView.getBottom(), p);
                    } super.onChildDraw(c, recyclerView, viewHolder, dX/3, dY, actionState, isCurrentlyActive);
                }
            }

        });
        helper2.attachToRecyclerView(recyclerView);
    }

    private M_gMyadapter_act.OnItemClickListener adaptermyacton = new M_gMyadapter_act.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            shareactdetailDlg.show();

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    if (myactlist.get(position).mPicture.contains("http")) {
                        Glide.with(M_gMainActivity.this).load(myactlist.get(position).mPicture).into(actdetaildlgimg);
                    } else {
                        Glide.with(M_gMainActivity.this).
                                load("https://iyung0926.000webhostapp.com/opendata/nopic1.jpg").into(actdetaildlgimg);
                    }
                }
            });

            actdetaildlgbtnmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uriactmap = Uri.parse("http://maps.google.com/maps?q=" + myactlist.get(position).mMap+
                            "(" + myactlist.get(position).mLocation + ")&iwloc=A&hl=h&l=zh-TW");

//                  mData.get(position).mMap+"("+mData.get(position).mLocation+")&iwloc=A&hl=h&l=zh-TW");
                    Intent itmap = new Intent(Intent.ACTION_VIEW, uriactmap);
                    startActivity(itmap);
                }
            });
            actdetaildlgbtnshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showBottomsheet();
                }
            });
            actdetaildlgname.setText(myactlist.get(position).mName);
            actdetaildlgloca.setText(myactlist.get(position).mLocation);
            actdetaildlgstim.setText(myactlist.get(position).mStart);
            actdetaildlghswd.setText(myactlist.get(position).mDescription);
            actdetaildlgimg.setScaleType(ImageView.ScaleType.FIT_XY);
            uriactdetail = Uri.parse(mData.get(position).mWebsite);
            TextView actdetaildlghoswd04 = shareactdetailDlg
                    .findViewById(R.id.m_gactdetaildlgredmore);
            if (myactlist.get(position).mWebsite.length() > 0) {
//                                                            urlweb.get(position).toString().length() > 0
                actdetaildlghoswd04.setText(getString(R.string.m_gmoreinfo));
                actdetaildlghoswd04.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uriactdetail = Uri.parse(myactlist.get(position).mWebsite);
                        Intent itfb = new Intent(Intent.ACTION_VIEW, uriactdetail);
                        startActivity(itfb);
                    }
                });
            } else {
                actdetaildlghoswd04.setText("");
            }
        }

    };

    private float convertDpToPx(int i) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round((float) i * density);
    }

    private View.OnClickListener fabdlonclick =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            myAlertDialog.show();
            fabdl.setVisibility(View.INVISIBLE);
            m_gwaittoload.setVisibility(View.INVISIBLE);
        }
    };


    private void shareactdetailDlg(){
        shareactdetailDlg = new Dialog(M_gMainActivity.this);
        shareactdetailDlg.setCancelable(false);
        shareactdetailDlg.setContentView(R.layout.m_gactidetail);
        shareactdetailDlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //重點加這一行!!
        Button digcollectactBtn = (Button) shareactdetailDlg.findViewById(R.id.m_gactdetail002_b04);
        Button digactshareBtn = (Button) shareactdetailDlg.findViewById(R.id.m_gactmap002_b05);
        ImageView closeactdig = (ImageView) shareactdetailDlg.findViewById(R.id.closeactdetaildialog);
        digcollectactBtn.setOnClickListener(digcollectactBtnon);
        digactshareBtn.setOnClickListener(digcollectactBtnon);
        closeactdig.setOnClickListener(digcollectactBtnon);

    }

    private View.OnClickListener digcollectactBtnon = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.m_gactdetail002_b04:
//                    Toast.makeText(getApplicationContext(), "Testcollect", Toast.LENGTH_SHORT).show();
                    shareactdetailDlg.cancel();
                    break;

                case R.id.m_gactmap002_b05:

                    shareactdetailDlg.cancel();
                    break;
                case R.id.closeactdetaildialog:
                    shareactdetailDlg.cancel();
                    break;
            }
        }
    };

    private void m_ginnersharerecyclervw(){
        m_ginnersharerecyclervw = new Dialog(M_gMainActivity.this);
        m_ginnersharerecyclervw.setCancelable(false);
        m_ginnersharerecyclervw.setContentView(R.layout.m_ginnerlislout);
        m_ginnersharerecyclervw.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        m_ginneredtx=m_ginnersharerecyclervw.findViewById(R.id.m_ginneredtx);
        closediginner = (ImageView) m_ginnersharerecyclervw.findViewById(R.id.closeactdetaildialog2);
        firstpesponse=(TextView) m_ginnersharerecyclervw.findViewById(R.id.firstpesponsetx);
        m_ginnersharedlgrv = m_ginnersharerecyclervw.findViewById(R.id.m_ginnersharedlgrv);
        m_ginnersend=m_ginnersharerecyclervw.findViewById(R.id.m_ginnersend);
    }

    private View.OnClickListener im04on = new View.OnClickListener() {
        @Override //好動活動
        public void onClick(View v) {
            im04.setVisibility(View.INVISIBLE);
            sp01.setVisibility(View.VISIBLE);
            sp02.setVisibility(View.INVISIBLE);
            sp01.setSelection(0);
        }
    };
    //    --------------------------------
    private AdapterView.OnItemSelectedListener sp01on = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    sp01.setVisibility(View.VISIBLE);
                    sp02.setVisibility(View.INVISIBLE);
                    im04.setVisibility(View.INVISIBLE);
                    break;
                case 1:

                    sp01.setVisibility(View.INVISIBLE);
                    sp02.setVisibility(View.VISIBLE);
                    im04.setVisibility(View.VISIBLE);

                    break;
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };
    private View.OnClickListener avatarallacton=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener avataron=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    //
    private View.OnClickListener avataracton=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    //    我的活動 動作監聽 end
//    社群動態動作監聽
    AdapterView.OnItemClickListener listviewsocialOnItemClkLis = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
//            Toast.makeText(getApplicationContext(), "聊天測試中", Toast.LENGTH_SHORT).show();
            // When clicked, show a toast with the TextView text
//            mTxtResult.setText(getText(R.string.ans)+listFromResource01[position]);
        }
    };

    private View.OnClickListener avatarsocialon=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
    private View.OnClickListener activeon=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            intent.setClass(M_listviw4.this,M_listviw3.class);
//            startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener mg4b02on=new View.OnClickListener() {
        int x = 0;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.m_g004_b02:
                    Toast.makeText(getApplicationContext(),"你已加入好友",Toast.LENGTH_LONG).show();
                    break;
                case R.id.m_g004_b04:

                    break;
                case R.id.m_g004_myact06:
                    myactlist.clear();
                    showRec();
                    plout3.setVisibility(View.VISIBLE);
                    lout3.setVisibility(View.INVISIBLE);
                    break;
//                case R.id.m_g004_bmedal:
//                    intent.setClass(M_gMainActivity.this, M_gmedal.class);
//                    startActivity(intent);
//                    finish();
//                    break;
                case R.id.m_g004_bmyfriend:
                    plout2.setVisibility(View.VISIBLE);
                    lout3.setVisibility(View.INVISIBLE);
                    break;
                case R.id.m_gfriendadd:
//                    myfriendrecycleview();
                    if(x<1){
                        m_gfriendadd.setText("送出邀請");
                        x=x+1;
                        dbHper.insertRecaddfr(m_ghideid.getText().toString(),
                                M_gLoginActivity.g_id);
                        myfriendrecycleview();
//                        plout2.setVisibility(View.VISIBLE);
                    }else{
                        m_gfriendadd.setText("加好友");
                        x=0;
                        mListfr.clear();
                        adaptermyfriend.notifyItemRangeChanged(0, adaptermyfriend.getItemCount());
//                        adaptermyfriend.notifyDataSetChanged();
                        plout2.setVisibility(View.VISIBLE);
                    }

                    break;

            }
        }
    };

    //    我的帳戶 監聽 end
//------------fab onclick--------
    private View.OnClickListener fmainon = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fmain.setImageResource(R.drawable.ic_expand_less_24);
            shareDlg.show();
        }
    };

    public void shareDlg(){
        shareDlg = new Dialog(M_gMainActivity.this);
        shareDlg.setCancelable(false);
        shareDlg.setContentView(R.layout.m_gshareresent);
        shareDlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        sp0g21 = (Spinner)shareDlg.findViewById(R.id.m_g002_s01);
        sharedlgfeel = (TextView)shareDlg.findViewById(R.id.sharedlgfeel);
        sharedlgfeel.setText("感到/正在：");
        ArrayAdapter<CharSequence> splsg2 = ArrayAdapter.createFromResource(M_gMainActivity.this,
                R.array.m_g002_a001,
                R.layout.m_gspinner_gitem);
        sp0g21.setAdapter(splsg2);
        edtextshare = (EditText)shareDlg.findViewById(R.id.m_g002_em01);
        Button diginsertemojioBtn = (Button) shareDlg.findViewById(R.id.m_g002_b04);
        Button digshareBtn = (Button) shareDlg.findViewById(R.id.m_g002_b05);
        ImageView closedig = (ImageView) shareDlg.findViewById(R.id.closesharedialog);
        diginsertemojioBtn.setOnClickListener(diginnerBtnon);
        digshareBtn.setOnClickListener(diginnerBtnon);
        closedig.setOnClickListener(diginnerBtnon);
    }

    public void shareDlgmodify(){
        shareDlgmodify = new Dialog(M_gMainActivity.this);
        shareDlgmodify.setCancelable(false);
        shareDlgmodify.setContentView(R.layout.m_gsharemodify);
        shareDlgmodify.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        sp0g21m = (Spinner)shareDlgmodify.findViewById(R.id.m_g002_s01m);
        sharedlgfeel2 = (TextView)shareDlgmodify.findViewById(R.id.sharedlgfeel2);
        sharedlgfeel2.setText("感到/正在：");
        ArrayAdapter<CharSequence> splsg2 = ArrayAdapter.createFromResource(M_gMainActivity.this,
                R.array.m_g002_a001,
                R.layout.m_gspinner_gitem);
        sp0g21m.setAdapter(splsg2);
        edtextsharem = (EditText)shareDlgmodify.findViewById(R.id.m_g002_em01m);
        diginsertemojioBtnm = (Button) shareDlgmodify.findViewById(R.id.m_g002_b04m);
        digshareBtnm = (Button) shareDlgmodify.findViewById(R.id.m_g002_b05m);
        closedigm = (ImageView) shareDlgmodify.findViewById(R.id.closesharedialogm);
    }

    private View.OnClickListener diginnerBtnon = new View.OnClickListener() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.m_g002_b04:
                    showemotion.show();
                    break;
                case R.id.m_g002_b05:
                    if(edtextshare.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),getString(R.string.checkwhattosay),Toast.LENGTH_SHORT).show();
                    }else{
                        currentTimestamp = Calendar.getInstance().getTime();
                        currentTimestampsdf = sdfTstamp.format(currentTimestamp);
                        StringBuilder sash = new StringBuilder();
                        int uniemojish = 0x1F4E3;
                        sash.append(Character.toChars(uniemojish));
                        String sharefeelinsert;
                        if(sharedlgfeel.getText().toString().equals("感到/正在：")){
                            sharefeelinsert = sash+"分享了:：";
                        }else{
                            sharefeelinsert = sharedlgfeel.getText().toString();
                        }
                        dbinsert = sharefeelinsert+edtextshare.getText().toString();
                        dbinsert2 = edtextshare.getText().toString();
                        dbHper.insertRecshare(dbinsert, M_gLoginActivity.personPhoto , M_gLoginActivity.g_DisplayName,
                                M_gSDbHelper.suuid, currentTimestampsdf.substring(5));
                        String[] edtxtsh = dbinsert.split("：");
                        itemaccount = new HashMap<String, Object>();
                        itemaccount.put("Picgavata", M_gLoginActivity.personPhoto);//頭像變化待撰寫，變數化!!!!!
                        itemaccount.put("txtView01", M_gLoginActivity.g_DisplayName);//擷取帳戶名稱
                        itemaccount.put("txtView02", edtxtsh[0]);//感覺如何待撰寫，變數化!!!!!
                        itemaccount.put("txtView03", edtxtsh[1]);
                        itemaccount.put("timestamp", getString(R.string.m_gsharetimestamp)+currentTimestampsdf.substring(5));
                        itemaccount.put("txtView05","shareid");
                        mList5.add(itemaccount);
                        mList6.add(itemaccount);
                        adapteraccount = new M_gMyadapter_account(
                                M_gMainActivity.this,
                                mList5);

                        listViewaccount.setAdapter(adapteraccount);
//-----------------------------------------------------------------------
                        adaptersocial = new M_gMyadapter_account(
                                M_gMainActivity.this,
                                mList6);

                        listviewsocial.setAdapter(adaptersocial);
//                        adaptersocial.notifyDataSetChanged();
//                        adapteraccount.notifyDataSetChanged();
                        if(mList5.size()==0){
                            m_gpromofirstshare.setVisibility(View.VISIBLE);
                        }else{
                            m_gpromofirstshare.setVisibility(View.INVISIBLE);
                        }
                        adaptersocial.notifyItemRangeChanged(0, adaptersocial.getItemCount());
                        adapteraccount.notifyItemRangeChanged(0, adapteraccount.getItemCount());

//------------------------------------------------------------------------
                        adaptersocial.buttonSetOnclick(innersociallikeon);
                        adapteraccount.buttonSetOnclick(inneraccountlikeon);
                        edtextshare.setText("");
                        sharedlgfeel.setText("感到/正在：");
                        mysql_insertshare();

//                        dbHper.close();
                        Collections.reverse(mList5);
                        Collections.reverse(mList6);
                        shareDlg.cancel();
                    }
                    break;
                case R.id.closesharedialog:
                    shareDlg.cancel();
                    break;
            }
            fmain.setImageResource(R.drawable.ic_sharemessage_24);
        }
    };
    public void showemotion(){
        showemotion = new Dialog(M_gMainActivity.this);
        showemotion.setCancelable(false);
        showemotion.setContentView(R.layout.m_gshowemotion);
        showemotion.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView closeemodig = (ImageView) showemotion.findViewById(R.id.closeshemodialog);
        closeemodig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showemotion.cancel();
            }
        });
        Button m_gemotionconfirm = (Button)showemotion.findViewById(R.id.m_gemotionconfirm);
        m_gemotionconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtxt = edtextshare.getText().toString();
                sa = new StringBuilder();
                sa.append(Character.toChars(uniemoji));
                sharedlgfeel.setText(sa+insertemojistring+"：");
                sharedlgfeel2.setText(sa+insertemojistring+"：");
                edtextshare.setText(edtxt);
                showemotion.cancel();
            }
        });
        List<Map<String, Object>> itemacts = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < imageemotion.length; i++) {
            Map<String, Object> itememt = new HashMap<String, Object>();
            itememt.put("imageact", imageemotion[i]);
            itememt.put("textact", textemotion[i]);
            itemacts.add(itememt);
        }
        SimpleAdapter adapteract = new SimpleAdapter(M_gMainActivity.this,
                itemacts, R.layout.m_ggridworkoutmedal_item, new String[]{"imageact", "textact"},
                new int[]{R.id.gridwomdl, R.id.gridwomdltx});
        getGridViewemot = (GridView)showemotion.findViewById(R.id.m_gemogv);
        getGridViewemot.setNumColumns(3);
        getGridViewemot.setAdapter(adapteract);
        getGridViewemot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),  textemotion[position].substring(0,4), Toast.LENGTH_SHORT).show();
                insertemojistring = textemotion[position].substring(0,4);
                String emjx=textemotion[position].substring(6);
                uniemoji = Integer.parseInt(emjx,16);
                int a=0;
            }
        });
    }

    private M_gMyadapter_account.ButtonInterface innersociallikeon = new M_gMyadapter_account.ButtonInterface() {
        //innerbutton
        @Override
        public void onclick(View view, int position) {
            switch(view.getId()){
                case R.id.m_gLiketx:
//                   Toast.makeText(getApplicationContext(), "like+", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.m_gResponsetx:
//                   Toast.makeText(getApplicationContext(), "response", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.m_gSharetx:
//                   Toast.makeText(getApplicationContext(), "share", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imgeoption:
//                   Toast.makeText(getApplicationContext(), "share", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private M_gMyadapter_account.ButtonInterface inneraccountlikeon = new M_gMyadapter_account.ButtonInterface() {
        //innerbutton
        @Override
        public void onclick(View view, int position) {
            switch(view.getId()){
                case R.id.m_gLiketx:
//                    Toast.makeText(getApplicationContext(), "like", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.m_gResponsetx:
//                    Toast.makeText(getApplicationContext(), "response", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.m_gSharetx:
//                    Toast.makeText(getApplicationContext(), "share", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //-------fab click end -------
    //---drawer------
    private View.OnClickListener drawermenuon = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            draweropen();
            drawerlot.openDrawer(GravityCompat.START);
        }
    };
    private void draweropen(){
        gavtaActvity= headerview.findViewById(R.id.navidraweravatar);
        GmailAcc =headerview.findViewById(R.id.mailacc);
        GAccname =headerview.findViewById(R.id.name);
        Picasso.with(M_gMainActivity.this).load(M_gLoginActivity.personPhoto).into(gavtaActvity);
        GmailAcc.setText(M_gLoginActivity.g_Email);
        GAccname.setText(M_gLoginActivity.g_DisplayName);
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "請正常關閉程式", Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.m_ga01_right_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.m_g001:
                Toast.makeText(getApplicationContext(),"離開",Toast.LENGTH_SHORT).show();
                M_gLoginActivity.getInstance().signOut();
                intent.setClass(M_gMainActivity.this, M_gLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.m_g002:
                Toast.makeText(getApplicationContext(),"中斷連結",Toast.LENGTH_SHORT).show();
                M_gLoginActivity.getInstance().revokeAccess();
                intent.setClass(M_gMainActivity.this, M_gLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mureport:
                Uri urirep = Uri.parse("https://forms.gle/LrvrGQTgieQq2Kbt5");
                Intent itrep = new Intent(Intent.ACTION_VIEW, urirep);
                startActivity(itrep);
                break;
            case R.id.close_settings:
//
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0x00000000);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return v;
    }
    //---------------loading animation
    @Override
    public void onAnimationStart(Animation animationloading) {
        Toast.makeText(getApplicationContext(), "載入您的資料", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onAnimationEnd(Animation animationloading) {
        Toast.makeText(context, "資料處理完畢", Toast.LENGTH_LONG).show();
//        intent.setClass(MainActivity.this, M_g101.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animationloading) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(updateTimer);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(updateTimer);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dbHper == null){
            dbHper = new M_gSDbHelper(this,DB_FILE,null,DBversion);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        dbHper.close();
    }

    private void mysql_delact() {  //not yet
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(delactid);
        try {
            Thread.sleep(500); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//-----------------------------------------------
        String result = M_gSDBConnector.executeDeletact(nameValuePairs);   //執行刪除
//-----------------------------------------------
    }

    public void mysql_insertact() {
        //        sqlctl = "SELECT * FROM member ORDER BY id ASC";
        ArrayList<String> nameValuePairs = new ArrayList<>();
//        nameValuePairs.add(sqlctl);
        nameValuePairs.add(mName);
        nameValuePairs.add(mLocation);
        nameValuePairs.add(mStart);
        nameValuePairs.add(mMap);
        nameValuePairs.add(mDescription);
        nameValuePairs.add(mWebsite);
        nameValuePairs.add(mPicture1);
        nameValuePairs.add(M_gSDbHelper.suuid);
        try {
            Thread.sleep(500); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//-----------------------------------------------
        String result = M_gSDBConnector.executeInsertact(nameValuePairs);  //真正執行新增
//-----------------------------------------------
    }

    public void mysql_insertshare() {
        //        sqlctl = "SELECT * FROM member ORDER BY id ASC";
        ArrayList<String> nameValuePairs = new ArrayList<>();
//        nameValuePairs.add(sqlctl);
        nameValuePairs.add(dbinsert);
        nameValuePairs.add(currentTimestampsdf.substring(5));
        nameValuePairs.add(M_gSDbHelper.suuid);
        try {
            Thread.sleep(500); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//-----------------------------------------------
        String result = M_gSDBConnector.executeInsertsha(nameValuePairs);  //真正執行新增
//-----------------------------------------------
    }

    private void dbmysql() {
       String sqlctl = " SELECT *  FROM  mg_activity  INNER JOIN  mg_member  ON ( mg_activity.actcollectorid = mg_member.Uuid )  WHERE  mg_member.Uuid =  "
                +  M_gLoginActivity.g_id+ " ; ";

        //join 語法在此修正調整，載入使用者專屬資料 14-16參考
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add("query");
        nameValuePairs.add(sqlctl);
//        query
        try {
            String result = M_gSDBConnector.executeQuery(nameValuePairs);
            JSONArray jsonArray = new JSONArray(result);
            // -------------------------------------------------------
            String wName="",wPicture1="",wLocation="",wStart="",wDescription="",
                    wMap="",wWebsite="",wid="";
            ArrayList<M_gPost> webdata = new ArrayList<>();
            if (jsonArray.length() > 0) { // MySQL 連結成功有資料
//                int rowsAffected = dbHper.clearRec();                 // 匯入前,刪除所有SQLite資料
                // 處理JASON 傳回來的每筆資料
                dbHper.clearRec();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    wid = jsonData.getString("actid");
                    wName =  jsonData.getString("actname");
                    wLocation = jsonData.getString("actlocation");
                    wStart = jsonData.getString("acsttime");
                    wMap = jsonData.getString("actmap");
                    wDescription = jsonData.getString("actdescript");
                    wWebsite = jsonData.getString("actweb");
                    wPicture1 = jsonData.getString("actpicweb");
                    webdata.add(new M_gPost(wName,wLocation,wStart,wMap,wDescription,wWebsite,wPicture1,wid
                            ));
                    // -------------------加入SQLite---------------------------------------
                    dbHper.insertRecact(webdata, M_gSDbHelper.suuid);
//                    Toast.makeText(getApplicationContext(), "共匯入 " + Integer.toString(jsonArray.length()) + " 筆資料", Toast.LENGTH_SHORT).show();
                }
                // ---------------------------
            } else {
                Toast.makeText(getApplicationContext(), "主機資料庫無資料", Toast.LENGTH_LONG).show();
            }
            initDB();
            recSet = dbHper.getRecSet();  //重新載入SQLite
            // --------------------------------------------------------
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    public void dbmysql_selfshare() {
        String sqlctl = " SELECT * FROM mg_share RIGHT  JOIN  mg_member  ON " +
                " (mg_share.shareerid = mg_member.Uuid)   WHERE  mg_member.Email =  '"
                +  M_gLoginActivity.g_Email+ "' ORDER BY shid DESC ; ";
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add("query_selfshare");
        nameValuePairs.add(sqlctl);
//        query
        try {
            String result = M_gSDBConnector.executeQuery(nameValuePairs);
            JSONArray jsonArray = new JSONArray(result);
            // -------------------------------------------------------
            String wShtalk="",wShtime="",wid="" , wpic="", wname = "", wshareerid="";
            Uri Uriwpic;
            if (jsonArray.length() > 0) { // MySQL 連結成功有資料
//                int rowsAffected = dbHper.clearRec();                 // 匯入前,刪除所有SQLite資料
                // 處理JASON 傳回來的每筆資料
                dbHper.clearRecsha();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    wid = jsonData.getString("shid");
                    wShtalk =  jsonData.getString("sharetalk");
                    wShtime = jsonData.getString("sharetime");
                    wpic = jsonData.getString("UserPicWeb");
                    wshareerid =jsonData.getString("shareerid");
                    Uriwpic = Uri.parse(wpic);
                    wname = jsonData.getString("UserName");
                    // -------------------加入SQLite---------------------------------------
                    dbHper.insertRecshare(wid+":"+wShtalk,Uriwpic,wname,wshareerid,wShtime);
                }
                // ---------------------------
            } else {
                Toast.makeText(getApplicationContext(), "主機資料庫無資料", Toast.LENGTH_LONG).show();
            }
            initDB();
            recSet2 = dbHper.getRecSetuid();  //重新載入SQLite
            // --------------------------------------------------------
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    public void dbmysql_allshare() {
        String sqlctl2 = " SELECT * FROM mg_share LEFT JOIN  mg_member  ON "
                +" (mg_share.shareerid = mg_member.Uuid)  ORDER BY shid DESC  ; ";
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add("query_allshare");
        nameValuePairs.add(sqlctl2);
//        query
        try {
            String result = M_gSDBConnector.executeQuery(nameValuePairs);
            JSONArray jsonArray = new JSONArray(result);
            // -------------------------------------------------------
            String wShtalk="",wShtime="",wid="" , wpic="", wname = "", wshareerid="";;
            Uri Uriwpic;
            if (jsonArray.length() > 0) { // MySQL 連結成功有資料
//                int rowsAffected = dbHper.clearRec();                 // 匯入前,刪除所有SQLite資料
                // 處理JASON 傳回來的每筆資料
                dbHper.clearRecshaall();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    wid = jsonData.getString("shid");
                    wShtalk =  jsonData.getString("sharetalk");
                    wShtime = jsonData.getString("sharetime");
                    wpic = jsonData.getString("UserPicWeb");
                    Uriwpic = Uri.parse(wpic);
                    wname = jsonData.getString("UserName");
                    wshareerid =jsonData.getString("shareerid");
                    // -------------------加入SQLite---------------------------------------
                    dbHper.insertRecallshare(wid+":"+wShtalk,Uriwpic,wname,wshareerid,wShtime);
                }
                // ---------------------------
            } else {
                Toast.makeText(getApplicationContext(), "主機資料庫無資料", Toast.LENGTH_LONG).show();
            }
            initDB();
            recSetshareALL = dbHper.getRecSetAllsh();  //重新載入SQLite
            // --------------------------------------------------------
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    private void dbmysql_lksh() {
        String sqlctl = " SELECT * FROM mg_likeshare  LEFT  JOIN  mg_member  ON " +
                " (mg_likeshare.likeowner = mg_member.id)   WHERE  mg_member.id =  '"
                +  "M_gLoginActivity.wID"+ "' ; ";
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add("query_selfshare");
        nameValuePairs.add(sqlctl);
        try {
            String result = M_gSDBConnector.executeQuery(nameValuePairs);
            JSONArray jsonArray = new JSONArray(result);
            // -------------------------------------------------------
            String wShtalkid="",wid="" ;
            if (jsonArray.length() > 0) { // MySQL 連結成功有資料
                dbHper.clearReclksh();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    wid = jsonData.getString("likeowner");
                    wShtalkid =  jsonData.getString("likeshareid");
                    // -------------------加入SQLite---------------------------------------
                    dbHper.insertReclikeshare(wShtalkid,wid);
                }
            } else {
                Toast.makeText(getApplicationContext(), "主機資料庫無資料", Toast.LENGTH_LONG).show();
            }
            initDB();
            recSet4 = dbHper.getRecSetlike();  //重新載入SQLite
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    public void dbmysql_response() {
        String sqlctl = " SELECT * FROM mg_response  LEFT  JOIN  mg_member  ON " +
                " (mg_response.responseownerid = mg_member.id) ; ";
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add("query_selfshare");
        nameValuePairs.add(sqlctl);
        try {
            String result = M_gSDBConnector.executeQuery(nameValuePairs);
            JSONArray jsonArray = new JSONArray(result);
            // -------------------------------------------------------
            String  wreponsetalk="",wresponsetime="",wresponseownerid="",whostid="",wpicweburi="",wpesponseownername="", wid=""  ;
            if (jsonArray.length() > 0) { // MySQL 連結成功有資料
                dbHper.clearRecresponse();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    wid = jsonData.getString("resid");
                    wreponsetalk = jsonData.getString("reponsetalk");
                    wresponsetime =  jsonData.getString("responsetime");
                    wresponseownerid = jsonData.getString("responseownerid");
                    whostid =  jsonData.getString("hostid");
                    wpicweburi = jsonData.getString("picweburi");
                    wpesponseownername =  jsonData.getString("pesponseownername");
                    // -------------------加入SQLite---------------------------------------
                    dbHper.insertRecrespon(wreponsetalk,wresponsetime+"-"+wid,
                            Integer.parseInt(wresponseownerid),whostid, Uri.parse(wpicweburi),wpesponseownername);
                }
            } else {
                Toast.makeText(getApplicationContext(), "主機資料庫無資料", Toast.LENGTH_LONG).show();
            }
            initDB();
            recSetresponse = dbHper.getRecSetbbs();  //重新載入SQLite
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    public static M_gMainActivity getInstance(){
        return instance;
    }

}