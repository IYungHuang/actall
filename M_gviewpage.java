package tw.tigercloud2022.youract;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;


public class M_gviewpage extends AppCompatActivity {

    ViewPager2 viewPager2;
    ArrayList<M_gViewPageItem> viewPagerItemArrayList;
    public static ConstraintLayout viewpagerllout;
    private TextView m_gstartnextvpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_gforviewpage);
        viewpagerllout = (ConstraintLayout)findViewById(R.id.viewpagerllout);
        viewPager2 = findViewById(R.id.viewpager);
        m_gstartnextvpage=viewpagerllout.findViewById(R.id.m_gstartnextvpage);
        int[] images = {R.drawable.drawersow1,R.drawable.drawersow4,R.drawable.drawershow2,R.drawable.drawersow3,R.drawable.drawersow5};
        int[] images2 = {R.drawable.page1,R.drawable.page2,R.drawable.page3,R.drawable.page4,R.drawable.page5};
        String[] heading = {"安排休閒活動? 找同好? 沒問題!","關於活動資訊? 內容細節沒問題!","怕錯過活動? 把它收藏起來~","享受活動樂趣， 完善整理清單。","想找人聊? 想記錄心情?"};
        String[] desc = {getString(R.string.a_desc),
                getString(R.string.b_desc),
                getString(R.string.c_desc),
                getString(R.string.d_desc)
                ,getString(R.string.e_desc)};

        viewPagerItemArrayList = new ArrayList<>();

        for (int i =0; i< images.length ; i++){

            M_gViewPageItem viewPagerItem = new M_gViewPageItem(images[i],heading[i],desc[i],images2[i]);
            viewPagerItemArrayList.add(viewPagerItem);

        }

        M_gVPAdapter vpAdapter = new M_gVPAdapter(viewPagerItemArrayList);

        viewPager2.setAdapter(vpAdapter);

        viewPager2.setClipToPadding(false);

        viewPager2.setClipChildren(false);

        viewPager2.setOffscreenPageLimit(5);

        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        m_gstartnextvpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(M_gviewpage.this, M_gLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}