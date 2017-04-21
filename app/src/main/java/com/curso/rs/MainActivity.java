package com.curso.rs;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = (int) ((int)metrics.widthPixels * 0.9);
        int height = (int) ((int)metrics.heightPixels * 0.25);



        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        Canvas canvas = new Canvas(bitmap);

        canvas.drawColor(Color.GREEN);
        int margen=5;
        int separacion=5;
        int width_f=width-margen;
        ArrayList rect = new ArrayList();

        rect.add(new Rect(margen,margen,margen+width_f/9,height-margen));


//        for(int i=1;i<=8;i++) {
//            rect.add(new Rect(separacion+margen+width_f/9*i,margen,margen+width_f/9*(i+1),height-margen));
//        }
        for (Object r:rect
             ) {
           canvas.drawRect((Rect)r,paint);
        }
        ImageView imageView = new ImageView(this);

// Set this ImageView's bitmap to the one we have drawn to.
        imageView.setImageBitmap(bitmap);

// Create a simple layout and add our image view to it.
        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(imageView, params);
        layout.setBackgroundColor(Color.WHITE);

// Show this layout in our activity.
        setContentView(layout);
    }
}
