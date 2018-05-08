package com.example.angelraymundo.memorama;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Random;
import java.util.stream.IntStream;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView[] images = new ImageView[12];
    int[] pri,sec;
    int[] cache={-1,-1};
    int pos=-1,cont=0,num=-1,per=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images[0] = findViewById(R.id.imageView);
        images[1] = findViewById(R.id.imageView2);
        images[2] = findViewById(R.id.imageView3);
        images[3] = findViewById(R.id.imageView4);
        images[4] = findViewById(R.id.imageView5);
        images[5] = findViewById(R.id.imageView6);
        images[6] = findViewById(R.id.imageView7);
        images[7] = findViewById(R.id.imageView8);
        images[8] = findViewById(R.id.imageView9);
        images[9] = findViewById(R.id.imageView10);
        images[10] = findViewById(R.id.imageView11);
        images[11] = findViewById(R.id.imageView12);
        Config();
        for (int i=0;i<12;i++){
            images[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        for(int i=0;i<12;i++){
            if(view==images[i]&&per<2){
                per++;
                num=i;
                    if(i<6){
                        SetImage(pri[i],images[i]);
                        pos=pri[i];
                    }else{
                        SetImage(sec[i-6],images[i]);
                        pos=sec[i-6];
                    }
                    if(pos==cache[0]&&i!=cache[1]){
                        //Crouton.makeText(this,"Correcto", Style.CONFIRM).show();
                        cache[0]=-1;
                        pos=-1;
                        cache[1]=-1;
                        cont++;
                        per=0;
                    }else if(cache[0]==-1){
                        cache[0] = pos;
                        cache[1] = i;
                    }else{
                        //Crouton.makeText(this,"Intentalo de nuevo", Style.ALERT).show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                SetImage(6,images[num]);
                                SetImage(6,images[cache[1]]);
                                cache[0]=-1;
                                cache[1]=-1;
                                pos=-1;
                                per=0;
                            }
                        }, 2000);
                    }
                    if(cont==6){
                        Crouton.makeText(this,"Felicidades", Style.CONFIRM).show();
                        Crouton.makeText(this,"Reiniciando", Style.CONFIRM).show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for(int j=0;j<12;j++){
                                    SetImage(6,images[j]);
                                    Config();
                                }
                                cache[0]=-1;
                                cache[1]=-1;
                                pos=-1;
                                per=0;
                                cont=0;
                            }
                        }, 7000);

                        Crouton.makeText(this,"Comienza", Style.CONFIRM).show();
                    }

            }
        }
    }
    public void SetImage(int num,ImageView v){
        switch(num){
            case 0:
                Picasso.with(getApplicationContext()).load(R.drawable.ima1).into(v);
                break;
            case 1:
                Picasso.with(getApplicationContext()).load(R.drawable.ima2).into(v);
                break;
            case 2:
                Picasso.with(getApplicationContext()).load(R.drawable.ima3).into(v);
                break;
            case 3:
                Picasso.with(getApplicationContext()).load(R.drawable.ima4).into(v);
                break;
            case 4:
                Picasso.with(getApplicationContext()).load(R.drawable.ima5).into(v);
                break;
            case 5:
                Picasso.with(getApplicationContext()).load(R.drawable.ima6).into(v);
                break;
            default:
                Picasso.with(getApplicationContext()).load(R.drawable.question).into(v);
                break;
        }
    }
    public void Config(){
        pri = Desordenar(new int[]{0, 1, 2, 3, 4, 5});
        sec = Desordenar(new int[]{0, 1, 2, 3, 4, 5});
    }
    public int[] Desordenar(int[] numerosAleatorios){
        Random r = new Random();
        for (int i = numerosAleatorios.length; i > 0; i--) {
            int posicion = r.nextInt(i);
            int tmp = numerosAleatorios[i-1];
            numerosAleatorios[i - 1] = numerosAleatorios[posicion];
            numerosAleatorios[posicion] = tmp;
        }
        return numerosAleatorios;
    }
}
