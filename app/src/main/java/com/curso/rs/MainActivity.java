package com.curso.rs;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button iniciar,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciar = (Button) findViewById(R.id.button);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = (int) ((int)metrics.widthPixels * 0.9);
        int height = (int) ((int)metrics.heightPixels * 0.25);



        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        final Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        final Canvas canvas = new Canvas(bitmap);

        canvas.drawColor(Color.GREEN);
        int margen=5;
        int separacion=5;
        int width_f=width-margen;
        final ArrayList rect = new ArrayList();

        rect.add(new Rect(margen,margen,margen+width_f/9,height-margen));


        for(int i=1;i<=8;i++) {
            rect.add(new Rect(separacion+margen+width_f/9*i,margen,margen+width_f/9*(i+1),height-margen));
        }
        int count=0;
        for (Object r:rect
             ) {
            if(count<4) {

                canvas.drawRect((Rect) r, new Paint(Color.rgb(94, 62, 1)));

            }else if(count!=4){
                canvas.drawRect((Rect) r, new Paint(Color.rgb(96, 119, 1)));
            }else
                canvas.drawRect((Rect) r, new Paint(Color.WHITE));

            count++;
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

// Set this ImageView's bitmap to the one we have drawn to.
        imageView.setImageBitmap(bitmap);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.setColor(Color.BLACK);
                canvas.drawRect((Rect)rect.get(4),paint);
            }
        });

// Create a simple layout and add our image view to it.
//        RelativeLayout layout = (RelativeLayout)findViewById(R.id.principal);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        //params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        layout.addView(imageView,params);
//        layout.setBackgroundColor(Color.WHITE);
//        setContentView(layout);

// Show this layout in our activity.

    }
}
