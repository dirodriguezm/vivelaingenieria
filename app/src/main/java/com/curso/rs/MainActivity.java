package com.curso.rs;


import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ImageButton aux;
    public ImageButton rs[];
    public Button accion,reset;
    private Integer posicion=4;
    public String r,s,b;
    int count=0;
    HashMap <ImageButton,Integer> map;
    String quehayen[];
    RadioGroup rdgGroup;
    private boolean tactil=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rs = new ImageButton[9];

        r = new String("R");
        s = new String("S");
        b= new String("B");
        quehayen=new String[9];
        map = new HashMap<ImageButton, Integer>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       iniciar();
        accion = (Button) findViewById(R.id.accion);
        reset = (Button )findViewById(R.id.reset);
        rdgGroup= (RadioGroup) findViewById(R.id.rdgGrupo);

        rdgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i==R.id.tactil){
                    tactil = true ;
                    accion.setVisibility(View.INVISIBLE);
                }else if(i==R.id.boton){
                    tactil=false;
                    accion.setVisibility(View.VISIBLE);
                }
            }
        });



        for (final ImageButton im:rs
             ) {

            im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    aux=im;
                    posicion =map.get(im);
                    if(tactil) {
                        if (quehayen[posicion] == "R") {
                            if (posicion+2<9 && quehayen[posicion + 2] == "B") {
                                im.setImageResource(R.drawable.blanco);
                                rs[posicion + 2].setImageResource(R.drawable.sapo);
                                quehayen[posicion] = "B";
                                quehayen[posicion + 2] = "R";
                                sonar(1);
                                count++;
                            } else if (posicion+1<9 && quehayen[posicion + 1] == "B") {
                                im.setImageResource(R.drawable.blanco);
                                rs[posicion + 1].setImageResource(R.drawable.sapo);
                                quehayen[posicion] = "B";
                                quehayen[posicion + 1] = "R";
                                sonar(1);
                                count++;
                            }
                        } else if (quehayen[posicion] == "S") {
                            if (posicion - 2 >= 0 && quehayen[posicion - 2] == "B") {
                                im.setImageResource(R.drawable.blanco);
                                rs[posicion - 2].setImageResource(R.drawable.rana);
                                quehayen[posicion] = "B";
                                quehayen[posicion - 2] = "S";
                                sonar(0);
                                count++;
                            } else if (posicion - 1 >= 0 &&quehayen[posicion - 1] == "B") {
                                im.setImageResource(R.drawable.blanco);
                                rs[posicion - 1].setImageResource(R.drawable.rana);
                                quehayen[posicion] = "B";
                                quehayen[posicion - 1] = "S";
                                sonar(0);
                                count++;
                            }
                        }
                        if(ganar()){
                            compartir();
                        }else if(perder()){
                            Toast.makeText(getApplicationContext(),"Perdió intentelo nuevamente",Toast.LENGTH_SHORT).show();
                            iniciar();
                        }
                    }
                }
            });
        }

        accion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tactil  ) {
                    if (quehayen[posicion] == "R") {
                        if (posicion+2<9 && quehayen[posicion + 2] == "B") {
                            aux.setImageResource(R.drawable.blanco);
                            rs[posicion + 2].setImageResource(R.drawable.sapo);
                            quehayen[posicion] = "B";
                            quehayen[posicion + 2] = "R";
                            sonar(1);
                            count++;
                        } else if (posicion+1<9 && quehayen[posicion + 1] == "B") {
                            aux.setImageResource(R.drawable.blanco);
                            rs[posicion + 1].setImageResource(R.drawable.sapo);
                            quehayen[posicion] = "B";
                            quehayen[posicion + 1] = "R";
                            sonar(1);
                            count++;
                        }
                    } else if (quehayen[posicion] == "S") {
                        if (posicion - 2 >= 0 && quehayen[posicion - 2] == "B") {
                            aux.setImageResource(R.drawable.blanco);
                            rs[posicion - 2].setImageResource(R.drawable.rana);
                            quehayen[posicion] = "B";
                            quehayen[posicion - 2] = "S";
                            sonar(0);
                            count++;
                        } else if (posicion - 1 >= 0 &&quehayen[posicion - 1] == "B") {
                            aux.setImageResource(R.drawable.blanco);
                            rs[posicion - 1].setImageResource(R.drawable.rana);
                            quehayen[posicion] = "B";
                            quehayen[posicion - 1] = "S";
                            sonar(0);
                            count++;
                        }
                    }
                    if(ganar()){
                        compartir();
                    }else if(perder()){
                        Toast.makeText(getApplicationContext(),"Perdió intentelo nuevamente",Toast.LENGTH_SHORT).show();
                        iniciar();
                    }
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar();
            }
        });




    }

    private boolean perder() {
        boolean perder=true;
        for(int i=0;i<9;i++) {

            if (quehayen[i] == "R") {
                if (i + 2 < 9 && quehayen[i + 2] == "B") {
                    perder = false;
                } else if (i + 1 < 9 && quehayen[i + 1] == "B") {
                    perder = false;
                }
            }
            else if (quehayen[i] == "S") {
                    if (i - 2 >= 0 && quehayen[i - 2] == "B") {
                        perder = false;
                    } else if (i - 1 >= 0 && quehayen[i - 1] == "B") {
                        perder = false;
                    }
                }
            }

        return perder;
    }

    private void compartir() {
        Toast.makeText(getApplicationContext(),"Felicidades,ganó",Toast.LENGTH_SHORT).show();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,"Gane en 24 pasos el juego Ranas y Sapos! \n Descarga el juego en el siguiente link!:"+"https://www.dropbox.com/s/a7eowgedsjio66w/Ranas%20y%20Sapos.apk?dl=0");
        startActivity(shareIntent);
    }

    private void iniciar(){
        count=0;
        rs[0] = (ImageButton)findViewById(R.id.r1);
        rs[1] = (ImageButton) findViewById(R.id.r2);
        rs[2] = (ImageButton) findViewById(R.id.r3);
        rs[3] = (ImageButton) findViewById(R.id.r4);
        rs[5] = (ImageButton) findViewById(R.id.s1);
        rs[6] = (ImageButton) findViewById(R.id.s2);
        rs[7] = (ImageButton) findViewById(R.id.s3);
        rs[8] = (ImageButton) findViewById(R.id.s4);
        rs[4] = (ImageButton) findViewById(R.id.blanco);
        rs[4].setImageResource(R.drawable.blanco);
        quehayen[4]="B";

        for (int i = 0;i<4;i++){
            rs[i].setImageResource(R.drawable.sapo);
            quehayen[i]="R";
        }
        for (int i = 5;i<9;i++){
            rs[i].setImageResource(R.drawable.rana);
            quehayen[i]="S";
        }
        for (int i = 0;i<9;i++){
            map.put(rs[i],i);
        }
        posicion=4;

    }

    public boolean ganar(){
        boolean ganar=true;
        for (int i = 0;i<4;i++){
            if(quehayen[i]!="S"){
                ganar=false;
            }
        }for (int i = 5;i<9;i++){
            if(quehayen[i]!="R"){
                ganar=false;
            }
        }
        if(quehayen[4]!="B") ganar = false;
        return ganar;
    }
    private void sonar(int i){
        if(i==0){
        MediaPlayer rana = MediaPlayer.create(this, R.raw.rana);
        rana.start();
        rana.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {

                mp.release();
            }
        });
        }else{
            MediaPlayer sapo = MediaPlayer.create(this, R.raw.sapo);
            sapo.start();
            sapo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {

                    mp.release();
                }
            });
        }
    }

}
