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


// We'll be creating an image that is 100 pixels wide and 200 pixels tall.
        int width = (int) ((int)metrics.widthPixels * 0.9);
        int height = (int) ((int)metrics.heightPixels * 0.25);


// Create a bitmap with the dimensions we defined above, and with a 16-bit pixel format. We'll
// get a little more in depth with pixel formats in a later post.
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

// Create a paint object for us to draw with, and set our drawing color to blue.
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

// Create a new canvas to draw on, and link it to the bitmap that we created above. Any drawing
// operations performed on the canvas will have an immediate effect on the pixel data of the
// bitmap.
        Canvas canvas = new Canvas(bitmap);

// Fill the entire canvas with a red color.
        canvas.drawColor(Color.GREEN);
        int margen=5;
        int separacion=5;
        int width_f=width-margen;
// Draw a rectangle inside our image using the paint object we defined above. The rectangle's
// upper left corner will be at (25,50), and the lower left corner will be at (75,150). Since we set
// the paint object's color above, this rectangle will be blue.
        ArrayList rect = new ArrayList();

        rect.add(new Rect(margen,margen,margen+width_f/9,height-margen));


        for(int i=1;i<=8;i++) {
            rect.add(new Rect(separacion+margen+width_f/9*i,margen,margen+width_f/9*(i+1),height-margen));
        }
// In order to display this image in our activity, we need to create a new ImageView that we
// can display.
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
