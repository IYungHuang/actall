package tw.tigercloud2022.youract;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class M_gSDBConnector {
    public static int httpstate =0;
    //--------------------------------------------------------
    private static String postUrl;
    private static String myResponse;
    static String result = null;
    private static OkHttpClient client = new OkHttpClient();
//---------------------------------------------------------
    // -------HOSTING-------
    static String connect_ip = "https://tigercloud2022.com/04/android_mysql_connect/android_all_db.php";
    //第一組
//    static String connect_ip = "https://tigercloud2022.com/100/android_mysql_connect/android_connect_db.php";

    // -------000webhost-------


    //----------------------------------------------------------------------------------------
    public static String executeQuery(ArrayList<String> query_string) {
//        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);
        String query_1 = query_string.get(1);
        FormBody body = new FormBody.Builder()
                .add("selefunc_string",query_0)
                .add("query_string", query_1)
                .build();

//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

//==========================
public static String executeInsert(ArrayList<String> query_string) {
    //        OkHttpClient client = new OkHttpClient();
    postUrl=connect_ip ;
    //--------------
    String query_0 = query_string.get(0);
    String query_1 = query_string.get(1);
    String query_2 = query_string.get(2);
    String query_3 = query_string.get(3);

    FormBody body = new FormBody.Builder()
            .add("selefunc_string","insert")
            .add("UserName", query_0)
            .add("Email", query_1)
            .add("UserPicWeb", query_2)
            .add("Uuid",  query_3)
            .build();
//--------------
    Request request = new Request.Builder()
            .url(postUrl)
            .post(body)
            .build();
    // ======++++++++++++++++++++====================
    // 使用httpResponse的方法取得http 狀態碼設定給httpstate變數
    httpstate = 0;   //設 httpcode初始值
    //---------------------------------------------
    try (Response response = client.newCall(request).execute()) {
        httpstate = response.code(); //內建方法
        return response.body().string();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return result;
}

    public static String executeInsertact(ArrayList<String> query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);
        String query_1 = query_string.get(1);
        String query_2 = query_string.get(2);
        String query_3 = query_string.get(3);
        String query_4 = query_string.get(4);
        String query_5 = query_string.get(5);
        String query_6 = query_string.get(6);
        String query_7 = query_string.get(7);

        FormBody body = new FormBody.Builder()
                .add("selefunc_string","insert_act")
                .add("actname", query_0)
                .add("actlocation", query_1)
                .add("acsttime", query_2)
                .add("actmap",  query_3)
                .add("actdescript", query_4)
                .add("actweb", query_5)
                .add("actpicweb", query_6)
                .add("actcollectorid",  query_7)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        // ======++++++++++++++++++++====================
        // 使用httpResponse的方法取得http 狀態碼設定給httpstate變數
        httpstate = 0;   //設 httpcode初始值
        //---------------------------------------------
        try (Response response = client.newCall(request).execute()) {
            httpstate = response.code(); //內建方法
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executeInsertsha(ArrayList<String> query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);
        String query_1 = query_string.get(1);
        String query_2 = query_string.get(2);

        FormBody body = new FormBody.Builder()
                .add("selefunc_string","insert_share")
                .add("sharetalk", query_0)
                .add("sharetime", query_1)
                .add("shareerid", query_2)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        // ======++++++++++++++++++++====================
        // 使用httpResponse的方法取得http 狀態碼設定給httpstate變數
        httpstate = 0;   //設 httpcode初始值
        //---------------------------------------------
        try (Response response = client.newCall(request).execute()) {
            httpstate = response.code(); //內建方法
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
//
    public static String executeInsertlksh(ArrayList<String> query_string) {
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);
        String query_1 = query_string.get(1);

        FormBody body = new FormBody.Builder()
                .add("selefunc_string","insert_lksh")
                .add("likeowner", query_0)
                .add("likeshareid", query_1)
                .build();
    //--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        // ======++++++++++++++++++++====================
        // 使用httpResponse的方法取得http 狀態碼設定給httpstate變數
        httpstate = 0;   //設 httpcode初始值
        //---------------------------------------------
        try (Response response = client.newCall(request).execute()) {
            httpstate = response.code(); //內建方法
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executeInsertresponse(ArrayList<String> query_string) {
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);
        String query_1 = query_string.get(1);
        String query_2 = query_string.get(2);
        String query_3 = query_string.get(3);
        String query_4 = query_string.get(4);
        String query_5 = query_string.get(5);

        FormBody body = new FormBody.Builder()
                .add("selefunc_string","insert_response")
                .add("responsetalk", query_0)
                .add("responsetime", query_3)
                .add("responseownerid", query_1)
                .add("hostid", query_2)
                .add("picweburi", query_4)
                .add("pesponseownername", query_5)

                .build();
        //--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        // ======++++++++++++++++++++====================
        // 使用httpResponse的方法取得http 狀態碼設定給httpstate變數
        httpstate = 0;   //設 httpcode初始值
        //---------------------------------------------
        try (Response response = client.newCall(request).execute()) {
            httpstate = response.code(); //內建方法
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executeUpdateuser(ArrayList<String>query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);
        String query_1 = query_string.get(1);

        FormBody body = new FormBody.Builder()
                .add("selefunc_string","update_user")
                .add("id", query_0)
                .add("UserName", query_1)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        httpstate = 0;   //設 httpcode初始值
        try (Response response = client.newCall(request).execute()) {
            httpstate = response.code(); //內建方法
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executeUpdate(ArrayList<String>query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);
        String query_1 = query_string.get(1);

        FormBody body = new FormBody.Builder()
                .add("selefunc_string","update")
                .add("shid", query_0)
                .add("sharetalk", query_1)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        httpstate = 0;   //設 httpcode初始值
        try (Response response = client.newCall(request).execute()) {
            httpstate = response.code(); //內建方法
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executeDelet(ArrayList<String> query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);

        FormBody body = new FormBody.Builder()
                .add("selefunc_string","delete")
                .add("id", query_0)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        httpstate = 0;   //設 httpcode初始值
        try (Response response = client.newCall(request).execute()) {
            httpstate = response.code(); //內建方法
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executeDeletshare(ArrayList<String> query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);

        FormBody body = new FormBody.Builder()
                .add("selefunc_string","delete_share")
                .add("id", query_0)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        httpstate = 0;   //設 httpcode初始值
        try (Response response = client.newCall(request).execute()) {
            httpstate = response.code(); //內建方法
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executeDeletact(ArrayList<String> query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);

        FormBody body = new FormBody.Builder()
                .add("selefunc_string","delete_act")
                .add("id", query_0)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        httpstate = 0;   //設 httpcode初始值
        try (Response response = client.newCall(request).execute()) {
            httpstate = response.code(); //內建方法
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executeDeletlksh(ArrayList<String> query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);
        String query_1 = query_string.get(1);
        FormBody body = new FormBody.Builder()
                .add("selefunc_string","delete_lksh")
                .add("likeowner", query_0)
                .add("likeshareid", query_1)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        httpstate = 0;   //設 httpcode初始值
        try (Response response = client.newCall(request).execute()) {
            httpstate = response.code(); //內建方法
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executeDeletresponse(ArrayList<String> query_string) {
        //        OkHttpClient client = new OkHttpClient();
        postUrl=connect_ip ;
        //--------------
        String query_0 = query_string.get(0);
        FormBody body = new FormBody.Builder()
                .add("selefunc_string","delete_response")
                .add("resid", query_0)
                .build();
//--------------
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        httpstate = 0;   //設 httpcode初始值
        try (Response response = client.newCall(request).execute()) {
            httpstate = response.code(); //內建方法
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}