package com.example.sursangeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MusicList extends AppCompatActivity {
    ListView lv;
    String [] songs={"kalavaathi","Naatu Naatu","Ramulo Ramula","Srivalli","King Of Kotha","Hukum",
            "Chitthi","Kalyani Vaccha Vaccha","Vachindamma","Yenti Yenti","Butta Boma","Arabic Kuthu",
            "Vaathi Coming","Naa Ready","Na Roja Nuve","Nandanandanna","Badass"};
    int [] path={R.raw.kalavathi,R.raw.naatu,R.raw.ramulo,R.raw.srivalli,R.raw.kok
            ,R.raw.hukum,R.raw.chitthi,R.raw.kalyani,R.raw.vachindama,R.raw.yenti,
            R.raw.buttabomma,R.raw.arabic,R.raw.vaathi,R.raw.naaready,R.raw.naroja,R.raw.nandanna
            ,R.raw.badass};
    int[] images={R.drawable.kalavathi,R.drawable.naatu,R.drawable.ramulo,R.drawable.srivalli,
    R.drawable.kok,R.drawable.hukum,R.drawable.chitthi,R.drawable.kalyani,R.drawable.vachindamma,
    R.drawable.yenti,R.drawable.butta,R.drawable.arabic,R.drawable.vaathi,R.drawable.naaready,
    R.drawable.naroja,R.drawable.nandanna,R.drawable.badass};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        lv=findViewById(R.id.list);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,songs);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(), player.class);
                i.putExtra("music",songs[position]);
                i.putExtra("path",path[position]);
                i.putExtra("img",images[position]);
                startActivity(i);
            }
        });

    }


}