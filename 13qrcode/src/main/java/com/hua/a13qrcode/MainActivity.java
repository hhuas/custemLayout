package com.hua.a13qrcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.hua.a13qrcode.zxing.android.CaptureActivity;
import com.hua.a13qrcode.zxing.encode.CodeCreator;

public class MainActivity extends AppCompatActivity {
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;

    private TextView main_result;
    private ImageView main_iv_qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_result = findViewById(R.id.main_result);
        main_iv_qrcode = findViewById(R.id.main_iv_qrcode);
    }

    public void goToqr(View view) {
        //动态权限申请
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            goScan();
        }
    }

    private void goScan() {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                //返回的文本内容
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

                main_result.setText("你扫描到的内容是：" + content);
            }
        }
    }

    public void goCreateqr(View view) {
        Bitmap qrCode = null;
        try {
            qrCode = CodeCreator.createQRCode("www.baidu.com");
        } catch (WriterException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Bitmap qrCodeWithLogo = CodeCreator.createQRCodeWithLogo("www.baidu.com", 500,bitmap );


        main_iv_qrcode.setImageBitmap(qrCodeWithLogo);

    }
}
