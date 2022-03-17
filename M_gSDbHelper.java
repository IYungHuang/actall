package tw.tigercloud2022.youract;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

////----------------------------------------------------------
//建構式參數說明：
//context 可以操作資料庫的內容本文，一般可直接傳入Activity物件。
//name 要操作資料庫名稱，如果資料庫不存在，會自動被建立出來並呼叫onCreate()方法。
//factory 用來做深入查詢用，入門時用不到。
//version 版本號碼。
////-----------------------


public class M_gSDbHelper extends SQLiteOpenHelper {

    String TAG = "YUNG=>";
    public String sCreateTableCommand;
    // 資料庫名稱
    private static final String DB_FILE = "healthy.db";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static final int VERSION = 1;             // 資料表名稱
    private static final String DB_TABLE_mg_member = "mg_member";    // 資料庫物件，固定的欄位變數
    private static final String DB_TABLE_mg_likeshare = "mg_likeshare";
    private static final String DB_TABLE_mg_friend = "mg_friend";
//    private static final String DB_TABLE_mg_medal = "mg_medal";
//    private static final String DB_TABLE_mg_mednmem = "mg_mednmem";
    private static final String DB_TABLE_mg_activity = "mg_activity";
//    private static final String DB_TABLE_mg_actnmem = "mg_actnmem";
    private static final String DB_TABLE_mg_share = "mg_share";
    private static final String DB_TABLE_mg_allshare = "mg_allshare";
    private static final String DB_TABLE_mg_response = "mg_response";
    public static final String suuid = M_gLoginActivity.g_id;
    private static final String crTBsqlmem = "CREATE     TABLE  IF  NOT  EXISTS " + DB_TABLE_mg_member + "   ( "
            +"id  INTEGER  PRIMARY KEY, "+ "UserName  TEXT  NOT  NULL," + "Email  TEXT  NOT  NULL," + "UserPicWeb  TEXT NOT NULL,"
            + "Workouttime  TEXT,"+"Workoutpt  INTEGER,"
            + "Create_time  TEXT DEFAULT CURRENT_TIMESTAMP NOT NULL,"
            + "Update_time  DATETIME ," + "Uuid INTEGER DEFAULT " + suuid + "  NOT NULL UNIQUE );";
    private static final String crTBsqllish = "CREATE     TABLE  IF  NOT  EXISTS  " + DB_TABLE_mg_likeshare + "   ( "
            + "id   INTEGER  PRIMARY KEY ,"+ "likeowner  INTEGER NOT NULL,"+"likeshareid  INTEGER NOT NULL);";
    private static final String crTBsqlfrinmem = "CREATE     TABLE  IF  NOT  EXISTS  " + DB_TABLE_mg_friend + "   ( "
            + "id   INTEGER PRIMARY KEY,"+"friendid   INTEGER NOT NULL," +"frdname   TEXT NOT NULL," +"frdemail   TEXT NOT NULL,"
            + " connecttime  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,"
            + "friownerid  INTEGER NOT NULL);";
//    private static final String crTBsqlmedal = "CREATE     TABLE  IF  NOT  EXISTS  " + DB_TABLE_mg_medal + "   ( "
//            + "Mdid   INTEGER  PRIMARY KEY,"+"MedalName  TEXT NOT NULL," + "Acquired_Time  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL);";
//    private static final String crTBsqlmednmem = "CREATE     TABLE  IF  NOT  EXISTS  " + DB_TABLE_mg_mednmem + "   ( "
//            + "JMdid   INTEGER NOT NULL,"+"MUuid   TEXT NOT NULL,"+" PRIMARY KEY(JMdid, MUuid),"
//            + "FOREIGN KEY(JMdid) REFERENCES mg_medal(Mdid),"+"FOREIGN KEY(MUuid) REFERENCES mg_member(Uuid));";
    private static final String crTBsqlact = "CREATE     TABLE  IF  NOT  EXISTS  " + DB_TABLE_mg_activity + "   ( "
            + "id   INTEGER  PRIMARY KEY,"+ "actname  TEXT NOT NULL,"+ "actlocation  TEXT ,"+"acsttime  TEXT,"
            + "actmap  TEXT,"+"actdescript  TEXT,"+"actweb  TEXT,"
            + "actpicweb   TEXT,"+ " actcollectorid INTEGER  NOT  NULL,"
            + "actcollecttime   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL);";
//    private static final String crTBsqlactnmem = "CREATE     TABLE  IF  NOT  EXISTS  " + DB_TABLE_mg_actnmem + "   ( "
//            + "JAtid   INTEGER NOT NULL,"+"MUuid   TEXT NOT NULL,"+"PRIMARY KEY(JAtid, MUuid),"
//            + "FOREIGN KEY(JAtid) REFERENCES mg_activity(Atid),"+"FOREIGN KEY(MUuid) REFERENCES mg_member(Uuid));";
    private static final String crTBsqlshare = "CREATE     TABLE  IF  NOT  EXISTS  " + DB_TABLE_mg_share + "   ( "
            + "id  INTEGER  PRIMARY KEY,"+ "sharetalk  TEXT NOT NULL,"+ "shownerid  INTEGER  NOT  NULL ," + "sharerpic TEXT NOT NULL , "
            + "sharetime   TEXT  NOT NULL," + "sharername   TEXT  NOT NULL );";
    private static final String crTBsqlallshare = "CREATE     TABLE  IF  NOT  EXISTS  " + DB_TABLE_mg_allshare + "   ( "
            + "id  INTEGER  PRIMARY KEY,"+ "allsharetalk  TEXT NOT NULL,"+ "allshownerid  INTEGER  NOT  NULL ," + "allsharerpic TEXT NOT NULL , "
            + "allsharetime   TEXT  NOT NULL," + "allsharername   TEXT  NOT NULL );";
    private static final String crTBsqlresp = "CREATE     TABLE  IF  NOT  EXISTS  " + DB_TABLE_mg_response + "   ( "
            + "id INTEGER  PRIMARY KEY,"+"reponsetalk  TEXT NOT NULL," + "responsetime   TEXT NOT NULL," + "responseownerid  INTEGER NOT NULL,"
            + "hostid  INTEGER NOT NULL," + "picweburi  TEXT NOT NULL,"+" pesponseownername  TEXT NOT NULL );";
    //更新版本時，視需求修改指令

    private static SQLiteDatabase database;

    public M_gSDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
        super(context, DB_FILE, null, VERSION);
        sCreateTableCommand = "";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
           sqLiteDatabase.execSQL(crTBsqlmem);
           sqLiteDatabase.execSQL(crTBsqllish);
//           sqLiteDatabase.execSQL(crTBsqlmedal);
           sqLiteDatabase.execSQL(crTBsqlact);
           sqLiteDatabase.execSQL(crTBsqlshare);
           sqLiteDatabase.execSQL(crTBsqlallshare);
           sqLiteDatabase.execSQL(crTBsqlresp);
//           sqLiteDatabase.execSQL(crTBsqlactnmem);
//           sqLiteDatabase.execSQL(crTBsqlmednmem);
           sqLiteDatabase.execSQL(crTBsqlfrinmem);
    }

    //----------------------------------------------
    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new M_gSDbHelper(context, DB_FILE, null, VERSION)
                    .getWritableDatabase();
        }
        return database;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d(TAG, "onUpgrade()");
        db.execSQL("DROP     TABLE     IF    EXISTS    " + DB_TABLE_mg_member);
        db.execSQL("DROP     TABLE     IF    EXISTS    " + DB_TABLE_mg_likeshare);
        db.execSQL("DROP     TABLE     IF    EXISTS    " + DB_TABLE_mg_activity);
        db.execSQL("DROP     TABLE     IF    EXISTS    " + DB_TABLE_mg_share);
        db.execSQL("DROP     TABLE     IF    EXISTS    " + DB_TABLE_mg_allshare);
        db.execSQL("DROP     TABLE     IF    EXISTS    " + DB_TABLE_mg_response);
        db.execSQL("DROP     TABLE     IF    EXISTS    " + DB_TABLE_mg_friend );
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
                super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    public void insertRec(String uname, String email, Uri picweb) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON;");
        ContentValues rec = new ContentValues();
        rec.put("UserName", uname);
        rec.put("Email", email);
        rec.put("UserPicWeb", String.valueOf(picweb));
        db.insert(DB_TABLE_mg_member, null, rec);  //SQLite 新增語法
        db.close();
    }

    public void insertRecshare(String edtext ,Uri wpic,String wname,String uuid,String time) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rec = new ContentValues();
        rec.put("sharetalk", edtext);
        rec.put("shownerid", uuid);
        rec.put("sharetime",time);
        rec.put("sharerpic", String.valueOf(wpic));
        rec.put("sharername", wname);
        db.insert(DB_TABLE_mg_share, null, rec);  //SQLite 新增語法
        db.close();
    }

    public void insertRecallshare(String edtext ,Uri wpic,String wname,String uuid,String time) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rec = new ContentValues();
        rec.put("allsharetalk", edtext);
        rec.put("allshownerid", uuid);
        rec.put("allsharetime",time);
        rec.put("allsharerpic", String.valueOf(wpic));
        rec.put("allsharername", wname);
        db.insert(DB_TABLE_mg_allshare, null, rec);  //SQLite 新增語法
        db.close();
    }


    public void insertReclikeshare(String Sharet, String id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rec = new ContentValues();
        rec.put("likeshareid", Sharet);
        rec.put("likeowner", id);
        db.insert(DB_TABLE_mg_likeshare, null, rec);  //SQLite 新增語法
        db.close();
    }

    public void insertRecrespon(String reponsetalk, String wid , int shareid ,
                                String responsetime ,Uri puri , String uname) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rec = new ContentValues();
        rec.put("reponsetalk", reponsetalk);
        rec.put("responseownerid", wid);
        rec.put("hostid", shareid);
        rec.put("responsetime",responsetime);
        rec.put("picweburi", String.valueOf(puri));
        rec.put("pesponseownername",uname);

        db.insert(DB_TABLE_mg_response, null, rec);  //SQLite 新增語法
        db.close();
    }


    public void insertRecact(ArrayList<M_gPost> dataactcollect, String uuid) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rec = new ContentValues();
        for(int i=0;i<dataactcollect.size();i++){
            rec.put("actname",dataactcollect.get(i).mName+":"+dataactcollect.get(i).id);
            rec.put("actlocation", dataactcollect.get(i).mLocation);
            rec.put("acsttime",dataactcollect.get(i).mStart);
            rec.put("actmap", dataactcollect.get(i).mMap);
            rec.put("actdescript",dataactcollect.get(i).mDescription);
            rec.put("actweb", dataactcollect.get(i).mWebsite);
            rec.put("actpicweb",dataactcollect.get(i).mPicture);
            rec.put("actcollectorid",uuid);
        }
        db.insert(DB_TABLE_mg_activity, null, rec);  //SQLite 新增語法
        db.close();
    }

    public void insertRecaddfr(String frid , String uuid) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rec = new ContentValues();
        rec.put("friendid", frid);
        rec.put("friownerid", uuid);
        db.insert(DB_TABLE_mg_friend, null, rec);
        db.close();
    }

    public int RecCount() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT    *   FROM   " + DB_TABLE_mg_member;
        Cursor recSet = db.rawQuery(sql, null); //select
        return recSet.getCount();
    }

    public String FindRec(String tname) {
        SQLiteDatabase db = getReadableDatabase();
        String fldSet = "ans=";
        String sql = "SELECT * FROM " + DB_TABLE_mg_member + " WHERE name LIKE ? ORDER BY id ASC ";
        String[] args = {"%" + tname + "%"};
        Cursor recSet = db.rawQuery(sql, args);
        int columnCount = recSet.getColumnCount();
        if (recSet.getCount() != 0) {
            recSet.moveToFirst();
            fldSet = recSet.getString(0) + " "
                    + recSet.getString(1) + " "
                    + recSet.getString(2) + " "
                    + recSet.getString(3) + "\n";

            while (recSet.moveToNext()) {
                for (int i = 0; i < columnCount; i++) {
                    fldSet += recSet.getString(i) + " ";
                }
                fldSet += "\n";
            }
        }
        recSet.close();
        db.close();
        return fldSet;
    }


    public ArrayList<String> getRecSet() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_activity;
        Cursor recSet = db.rawQuery(sql, null);
        ArrayList<String> recAry = new ArrayList<String>();
        //----------------------------
        Log.d(TAG, "recSet=" + recSet);
        int columnCount = recSet.getColumnCount();

        while (recSet.moveToNext()) {
            String fldSet = "";
            for (int i = 0; i < columnCount; i++)
                fldSet += recSet.getString(i) + "#";
            recAry.add(fldSet);
        }
        //------------------------
        recSet.close();
        db.close();
//        Log.d(TAG, "recAry=" + recAry);
        return recAry;
    }
    public ArrayList<String> getRecSetuid() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_share +" WHERE shownerid = '"+ suuid+"';";
        Cursor recSet = db.rawQuery(sql, null);
        ArrayList<String> recAry = new ArrayList<String>();
        //----------------------------
        Log.d(TAG, "recSet=" + recSet);
        int columnCount = recSet.getColumnCount();
        while (recSet.moveToNext()) {
            String fldSet = "";
            for (int i = 0; i < columnCount; i++)
                fldSet += recSet.getString(i) + "#";
            recAry.add(fldSet);
        }
        //------------------------
        recSet.close();
        db.close();
//        Log.d(TAG, "recAry=" + recAry);
        return recAry;
    }

    public ArrayList<String> getRecSetAllsh() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_allshare +" ;";
        Cursor recSet = db.rawQuery(sql, null);
        ArrayList<String> recAry = new ArrayList<String>();
        //----------------------------
        Log.d(TAG, "recSet=" + recSet);
        int columnCount = recSet.getColumnCount();
        while (recSet.moveToNext()) {
            String fldSet = "";
            for (int i = 0; i < columnCount; i++)
                fldSet += recSet.getString(i) + "#";
            recAry.add(fldSet);
        }
        //------------------------
        recSet.close();
        db.close();
//        Log.d(TAG, "recAry=" + recAry);
        return recAry;
    }

    public ArrayList<String> getRecSetuserinfo() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_member;
        Cursor recSet = db.rawQuery(sql, null);
        ArrayList<String> recAry = new ArrayList<String>();
        //----------------------------
        Log.d(TAG, "recSet=" + recSet);
        int columnCount = recSet.getColumnCount();
        while (recSet.moveToNext()) {
            String fldSet = "";
            for (int i = 0; i < columnCount; i++)
                fldSet += recSet.getString(i) + "#";
            recAry.add(fldSet);
        }
        //------------------------
        recSet.close();
        db.close();
//        Log.d(TAG, "recAry=" + recAry);
        return recAry;
    }

    public ArrayList<String> getRecSetlike() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_likeshare;
        Cursor recSet = db.rawQuery(sql, null);
        ArrayList<String> recAry = new ArrayList<String>();
        //----------------------------
        Log.d(TAG, "recSet=" + recSet);
        int columnCount = recSet.getColumnCount();

        while (recSet.moveToNext()) {
            String fldSet = "";
            for (int i = 0; i < columnCount; i++)
                fldSet += recSet.getString(i) + "#";
            recAry.add(fldSet);
        }
        //------------------------
        recSet.close();
        db.close();
//        Log.d(TAG, "recAry=" + recAry);
        return recAry;
    }

    public ArrayList<String> getRecSetbbs() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_response;
        Cursor recSet = db.rawQuery(sql, null);
        ArrayList<String> recAry = new ArrayList<String>();
        //----------------------------
        Log.d(TAG, "recSet=" + recSet);
        int columnCount = recSet.getColumnCount();

        while (recSet.moveToNext()) {
            String fldSet = "";
            for (int i = 0; i < columnCount; i++)
                fldSet += recSet.getString(i) + "#";
            recAry.add(fldSet);
        }
        //------------------------
        recSet.close();
        db.close();
//        Log.d(TAG, "recAry=" + recAry);
        return recAry;
    }

    public int clearRec() { //清除SQLite資料
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_activity;
        Cursor recSet = db.rawQuery(sql, null); //null選擇全部
        if(recSet.getCount()!=0){
                int rowsAffected=db.delete(DB_TABLE_mg_activity,"1",null);
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            db.close();
            return rowsAffected;
        }else
            {
            db.close();
                return -1;
        }
    }

    public int clearRecsha() { //清除SQLite資料
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_share;
        Cursor recSet = db.rawQuery(sql, null); //null選擇全部
        if(recSet.getCount()!=0){
            int rowsAffected=db.delete(DB_TABLE_mg_share,"1",null);
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            db.close();
            return rowsAffected;
        }else
        {
            db.close();
            return -1;
        }
    }

    public int clearRecshaall() { //清除SQLite資料
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_allshare;
        Cursor recSet = db.rawQuery(sql, null); //null選擇全部
        if(recSet.getCount()!=0){
            int rowsAffected=db.delete(DB_TABLE_mg_allshare,"1",null);
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            db.close();
            return rowsAffected;
        }else
        {
            db.close();
            return -1;
        }
    }

    public int clearRecuser() { //清除SQLite資料
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_member;
        Cursor recSet = db.rawQuery(sql, null); //null選擇全部
        if(recSet.getCount()!=0){
            int rowsAffected=db.delete(DB_TABLE_mg_member,"1",null);
            int d = 0;
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            db.close();
            return rowsAffected;
        }else
        {
            db.close();
            return -1;
        }
    }

    public int clearReclksh() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_likeshare;
        Cursor recSet = db.rawQuery(sql, null); //null選擇全部
        if(recSet.getCount()!=0){
            int rowsAffected=db.delete(DB_TABLE_mg_likeshare,"1",null);
            int d = 0;
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            db.close();
            return rowsAffected;
        }else
        {
            db.close();
            return -1;
        }
    }

    public int clearRecresponse() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_response;
        Cursor recSet = db.rawQuery(sql, null); //null選擇全部
        if(recSet.getCount()!=0){
            int rowsAffected=db.delete(DB_TABLE_mg_response,"1",null);
            int d = 0;
            // From the documentation of SQLiteDatabase delete method:
            // To remove all rows and get a count pass "1" as the whereClause.
            db.close();
            return rowsAffected;
        }else
        {
            db.close();
            return -1;
        }
    }


    public void deleteRec(String acname) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_activity;
        Cursor recSet = db.rawQuery(sql, null);
        if (recSet.getCount() != 0) {
            String whereClause = "actname='" + acname + "'";
            db.delete(DB_TABLE_mg_activity, whereClause, null);
            db.close();
        } else {
            db.close();
        }
    }

    public void deletelikeshRec(String likeShareid) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_likeshare;
        Cursor recSet = db.rawQuery(sql, null);
        if (recSet.getCount() != 0) {
            String whereClause = "likeshareid='" + likeShareid + "'";
            db.delete(DB_TABLE_mg_likeshare, whereClause, null);
            db.close();
        } else {
            db.close();
        }
    }

    public void deleteshareRec(String sharet) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_share;
        Cursor recSet = db.rawQuery(sql, null);
        if (recSet.getCount() != 0) {
            String whereClause = "sharetalk='" + sharet + "'";
            db.delete(DB_TABLE_mg_share, whereClause, null);
            db.close();
        } else {
            db.close();
        }
    }

    public void deleteresponseRec(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_response;
        Cursor recSet = db.rawQuery(sql, null);
        if (recSet.getCount() != 0) {
            String whereClause = "id='" + id + "'";
            db.delete(DB_TABLE_mg_response, whereClause, null);
            db.close();
        } else {
            db.close();
        }
    }

    public void updateRec(String mshare , String ShareID) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM " + DB_TABLE_mg_share;
        Cursor recSet = db.rawQuery(sql, null);
        if (recSet.getCount() != 0){
            ContentValues rec = new ContentValues();
            rec.put("sharetalk",mshare);
            String whereClause = "id ='" + ShareID + "'";
            db.update(DB_TABLE_mg_share, rec, whereClause, null);
//            int rowsAffected = db.update(DB_TABLE_mg_share, rec, whereClause, null);
            recSet.close();
            db.close();
//            return rowsAffected;
        } else    {
            recSet.close();
            db.close();
//            return -1;
        }
    }


//    public void insertRecact_m(ContentValues rec) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.insert(DB_TABLE_mg_activity, null, rec);
//        db.close();
//    }

}
