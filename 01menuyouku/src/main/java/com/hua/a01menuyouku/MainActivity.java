package com.hua.a01menuyouku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rl_level1, rl_level2, rl_level3;
    private ImageView level2_menu, level1_home;
    private boolean isShowLevel3 = true;
    private boolean isShowLevel2 = true;
    private boolean isShowLevel1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        level2_menu.setOnClickListener(this);
        level1_home.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.level2_menu:
                if (isShowLevel3) {
                    isShowLevel3 = false;
                    AnimationTools.hideView(rl_level3);

                } else {
                    isShowLevel3 = true;
                    AnimationTools.showView(rl_level3);

                }
                break;
            case R.id.level1_home:
                if (isShowLevel3) {
                    isShowLevel3 = false;
                    AnimationTools.hideView(rl_level3, 500);
                }
//                 else {
//                    isShowLevel3 = true;
//                    AnimationTools.showView(rl_level3, 500);
//
//                }
                if (isShowLevel2) {
                    isShowLevel2 = false;
                    AnimationTools.hideView(rl_level2);

                } else {
                    isShowLevel2 = true;
                    AnimationTools.showView(rl_level2);

                }

                break;
        }
    }

    private void initView() {
        rl_level1 = findViewById(R.id.rl_level1);
        rl_level2 = findViewById(R.id.rl_level2);
        rl_level3 = findViewById(R.id.rl_level3);

        level2_menu = findViewById(R.id.level2_menu);
        level1_home = findViewById(R.id.level1_home);
    }
}
