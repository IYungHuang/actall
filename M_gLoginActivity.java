package tw.tigercloud2022.youract;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class M_gLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "googlelogin";
    private static final int RC_SIGN_IN = 9001;
    private static M_gLoginActivity instance;

    private TextView mStatusTextView;
    private GoogleSignInClient mGoogleSignInClient;
    private Intent intent=new Intent();
    public static ImageView logooravatar;
    public static Uri personPhoto;
    public static String g_Email,g_DisplayName,g_id;
//    private M_gLoginActivity m_gLoginActivity;
    private TextView about_ugu;
    private M_gSDbHelper dbHper;
    private static final String DB_FILE = "healthy.db";
    private static final int DBversion = 1;
    private ArrayList<String> recSet = null;
    private Handler handler = new Handler();
    private String sqlctl;
    private String wEmail="_";
    public static String wID="_";
    private String wUserName="_";
    private String wpicweb="_";
    private Uri uwpicweb;
    private static final String[] permissionsArray = new String[]{

            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,

    };
    private List<String> permissionsList = new ArrayList<String>();
    //申請權限後的返回碼
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //-------------------遇到需處理大量資料，請加此段-------------------------
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        enableStrictMode(this);
        //---------------------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_gactivity_login);
        setupViewComponent();
        handler.postDelayed(udnet,60000);
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

    private void checkRequiredPermission(final Activity activity){ //
        for (String permission : permissionsArray) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
            }
        }
        if (permissionsList.size()!=0) {
            ActivityCompat.requestPermissions(activity, permissionsList.toArray(new
                    String[permissionsList.size()]), REQUEST_CODE_ASK_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int i=0; i<permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(getApplicationContext(), permissions[i]+"權限申請成功!", Toast.LENGTH_LONG).show();
//                        p_ok=1;
                    } else {
//                        Toast.makeText(getApplicationContext(), "權限被拒絕： "+permissions[i], Toast.LENGTH_LONG).show();
//                        p_ok=0;
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private int autotime = 120;
    private Runnable udnet = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, autotime * 1000);
            checkConnection();
        }
    };

    private void checkConnection() {
        // initialize intent filter
        IntentFilter intentFilter = new IntentFilter();
        // add action
        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");
        // register receiver
        registerReceiver(new ConnectionReceiver(), intentFilter);
        // Initialize listener
        ConnectionReceiver.Listener = this::onNetworkChange;
        // Initialize connectivity manager
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        // Initialize network info
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        // get connection status
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        // display snack bar
        showSnackBar(isConnected);
    }

    private void showSnackBar(boolean isConnected) {
        // initialize color and message
        String message;
        // check condition
        if (isConnected) {
            message = "已連線至網路。";
            Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), message, Snackbar.LENGTH_LONG);
//            snackbar.getView().setBackgroundColor(Color.WHITE);
            snackbar.getView().setBackgroundResource(R.drawable.snbarbgwb);
            snackbar.setTextColor(Color.BLUE);
            TextView tv = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
            Drawable dw = ContextCompat.getDrawable(M_gLoginActivity.this, R.drawable.ic_import_export_24);
            dw.setBounds(0, 0, dw.getMinimumHeight(), dw.getMinimumHeight());
            tv.setCompoundDrawables(dw, null, null, null);
            tv.setGravity(Gravity.CENTER);
            snackbar.show();

        } else {

            message = "未有網路連線，建議連線以使用完整功能!";
            Snackbar snackbar2 = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
            snackbar2.getView().setBackgroundResource(R.drawable.snbarbgsm);
            snackbar2.setTextColor(Color.RED);
            TextView tv = (TextView) snackbar2.getView().findViewById(R.id.snackbar_text);
            Drawable dw = ContextCompat.getDrawable(M_gLoginActivity.this, R.drawable.ic_mobiledata_off_24);
            dw.setBounds(0, 0, dw.getMinimumHeight(), dw.getMinimumHeight());
            tv.setCompoundDrawables(dw, null, null, null);
            tv.setGravity(Gravity.CENTER);

            // set text color
            snackbar2.show();
        }

    }

    public void onNetworkChange(boolean isConnected) {
        setupViewComponent();
        showSnackBar(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // call method
//        checkConnection();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(udnet);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // call method
        handler.removeCallbacks(udnet);

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        checkConnection();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        handler.removeCallbacks(udnet);
//        dbHper.close();
    }

    private void initDB() {

        if (dbHper == null) {
            dbHper = new M_gSDbHelper(this, DB_FILE, null, DBversion);
        }
        recSet = dbHper.getRecSetuserinfo();
    }

    private void setupViewComponent() {
        mStatusTextView = findViewById(R.id.status);
        // Button listeners
        TextView about_ugu = findViewById(R.id.about_ugc);
        about_ugu.setMovementMethod(LinkMovementMethod.getInstance());

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.disconnect_button).setOnClickListener(this);
        findViewById(R.id.to_app).setOnClickListener(this);
        findViewById(R.id.btn_leave).setOnClickListener(this);
        // START configure_signin
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                .requestIdToken(getString(R.string.server_client_id))
                .requestProfile()
                .requestEmail()
                .build();
        //END configure_signin

        //START build_client
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //END build_client

        // START customize_button
        // Set the dimensions of the sign-in button.
        logooravatar = (ImageView)findViewById(R.id.google_icon);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT);
        // END customize_button

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                try{
                    signIn();
                }catch (Exception e){
                    e.printStackTrace();
                }
                setupViewComponent();
                break;
            case R.id.sign_out_button:
                try{
                    signOut();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.disconnect_button:
                try{
                    revokeAccess();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.to_app:
//                if (recSet.size() != 0) {
//                    for (int i = 0; i < recSet.size(); i++) {
//                        String[] fld = recSet.get(i).split("#");
//                        String checkemail = fld[2];
//                        String checkusername = fld[1];
//                        int a=0;
//                        if(!g_Email.equals(checkemail) && !g_DisplayName.equals(checkusername))
//                        {
//                            dbHper.insertRec(g_DisplayName,g_Email,personPhoto);
//                            dbHper.close();
//                        }
//                    }
//                    intent.setClass(M_gLoginActivity.this, M_gMainActivity.class);
//                    startActivity(intent);
//                }else{
//                    dbHper.insertRec(g_DisplayName,g_Email,personPhoto);
//                    dbHper.close();
//                    intent.setClass(M_gLoginActivity.this, M_gMainActivity.class);
//                    startActivity(intent);
//                }
                intent.setClass(M_gLoginActivity.this, M_gMainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_leave:
                finish();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    public void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // START_EXCLUDE
                        updateUI(null);
                        // END_EXCLUDE
                    }
                });
    }
     public void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // START_EXCLUDE
                        updateUI(null);
                        // END_EXCLUDE
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        checkConnection();
        // START on_start_sign_in
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        try{
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            updateUI(account);
        }catch (Exception e){
            e.printStackTrace();
        }

        // END on_start_sign_in
    }

    // START onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    // END onActivityResult

    public void mysql_insert() {
        //        sqlctl = "SELECT * FROM member ORDER BY id ASC";
        ArrayList<String> nameValuePairs = new ArrayList<>();
//        nameValuePairs.add(sqlctl);
        nameValuePairs.add(g_DisplayName);
        nameValuePairs.add(g_Email);
        nameValuePairs.add(String.valueOf(personPhoto));
        nameValuePairs.add(g_id);
        try {
            Thread.sleep(500); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//-----------------------------------------------
       String result = M_gSDBConnector.executeInsert(nameValuePairs);  //真正執行新增
//-----------------------------------------------
        checkupdate(result);
    }

    private void mysql_updateshareuser() {
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(wID);
        nameValuePairs.add(g_DisplayName);
        try {
            Thread.sleep(500); //  延遲Thread 睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//-----------------------------------------------
       String result = M_gSDBConnector.executeUpdateuser(nameValuePairs);
        int a =0;
//-----------------------------------------------
        checkupdate(result);
    }

    private void dbmysql() {
        sqlctl = " SELECT * FROM mg_member  WHERE  mg_member.Email =  '"
                +  g_Email+ "' ; ";
        ArrayList<String> nameValuePairs = new ArrayList<>();
        nameValuePairs.add("query_selfshare");
        nameValuePairs.add(sqlctl);
//        query
        try {
            String result = M_gSDBConnector.executeQuery(nameValuePairs);
            JSONArray jsonArray = new JSONArray(result);
            if (jsonArray.length() > 0) { // MySQL 連結成功有資料
                dbHper.clearRecuser();  //??
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonData = jsonArray.getJSONObject(i);
                    wpicweb = jsonData.getString("UserPicWeb");
                    uwpicweb = Uri.parse(wpicweb);
                    wUserName = jsonData.getString("UserName");
                    wEmail = jsonData.getString("Email");
                    wID = jsonData.getString("id");
                    dbHper.insertRec(wID+":"+wUserName,wEmail,uwpicweb);
                    int s = 0;
                }
            }else{
                Toast.makeText(getApplicationContext(), "主機資料庫無資料", Toast.LENGTH_LONG).show();
            }
            initDB();
            recSet = dbHper.getRecSetuserinfo();
            // --------------------------------------------------------
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    // START handleSignInResult
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }
    // END handleSignInResult

    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            g_id=account.getId();  //ID
            initDB();
            mStatusTextView.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));//使用者名稱
            g_DisplayName=account.getDisplayName(); //暱稱
             g_Email=account.getEmail();  //信箱
            String g_GivenName=account.getGivenName(); //Firstname
            String g_FamilyName=account.getFamilyName(); //Last name
            personPhoto = account.getPhotoUrl(); //大頭貼照
            Picasso.with(this).load(personPhoto).into(logooravatar);
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_leave).setVisibility(View.GONE);
            dbmysql();
            if(g_Email.equals(wEmail)){
                mysql_updateshareuser();
            }else{
                mysql_insert();
                try {
                    Thread.sleep(500); //  延遲Thread 睡眠0.5秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dbmysql();
            }
        } else {
            mStatusTextView.setText(R.string.logout);
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
            findViewById(R.id.btn_leave).setVisibility(View.VISIBLE);
            Picasso.with(this).load(R.drawable.googleg_standard_color_18).into(logooravatar);
        }
    }

    private void checkupdate(String result) {
        if(result.length()>3)
        Toast.makeText(getApplicationContext(),"更新/登入成功!",Toast.LENGTH_SHORT).show();
    }

    public static M_gLoginActivity getInstance(){
        return instance;
    }

}


