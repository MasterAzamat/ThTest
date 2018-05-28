package com.example.azaat.testfotth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetileActivity extends AppCompatActivity {

    ImageView img;
    TextView name,mi,mail,ip,empname,pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detile);

        img = (ImageView) findViewById(R.id.detile_img);
        name = (TextView) findViewById(R.id.detile_name);
        mi = (TextView) findViewById(R.id.detile_mi);
        mail = (TextView) findViewById(R.id.detile_mail);
        ip = (TextView) findViewById(R.id.detile_ip);
        empname = (TextView) findViewById(R.id.detile_empname);
        pos = (TextView) findViewById(R.id.detile_pos);

        Person person = (Person)getIntent().getSerializableExtra("mess");

        Glide.with(this).load(person.getPhoto())
                .into(img);
        name.setText(person.getName()+" "+person.getSurname());
        mi.setText(person.getMi());
        mail.setText(person.getMail());
        ip.setText(person.getIp_address());
        empname.setText(person.getEmployment().getName());
        pos.setText(person.getEmployment().getPosition());
    }
}
