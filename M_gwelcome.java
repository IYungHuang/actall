package tw.tigercloud2022.youract;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;


//----------------------------------------welcome-------------------------downward---------------
public class M_gwelcome extends AppCompatActivity implements Animation.AnimationListener{

    private View imageView;
    private MediaPlayer startmusic;
    private Intent intent=new Intent();
    private String loginornt;
//    private welcome context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_gactivity_welcome);
//        context = this;
        imageView = findViewById(R.id.im01);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.welcome);
        animation.setFillEnabled(true);
        animation.setFillAfter(true);
        animation.setAnimationListener(this);
        imageView.setAnimation(animation);
        setupViewcomponet();

    }

    private void setupViewcomponet() {
    }

    @Override
    public void onAnimationStart(Animation animation) {
        startmusic = MediaPlayer.create(getApplication(), R.raw.open2);
        startmusic.start();

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent i= new Intent(getApplicationContext(), M_gviewpage.class);
//        i.putExtra("login","member");
                startActivity(i);
                finish();
//        intent.setClass(welcome.this,MainActivity.class);

        if (startmusic.isPlaying()) {
            startmusic.stop();
            finish();
        };

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}