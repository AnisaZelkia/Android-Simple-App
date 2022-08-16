package com.anisa.final_181270_f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    Button btnClose, btnSimpan, btnClear ;
    TextView NIDN, Nama, Alamat, Telepon;
    RadioGroup radioJKL, radiostats;
    RadioButton pilihanJKL, rb_lk,   rb1, rb2, rb_pr, pStats;
    CheckBox cb1, cb2, cb6;
    String myJenisKelamin;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = openOrCreateDatabase("Anisa_Zelkia181270", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_dosen(NIDN VARCHAR PRIMARY KEY,nama VARCHAR,alamat VARCHAR,telp VARCHAR,jkel VARCHAR, Stats VARCHAR,jenjang VARCHAR);");


//        menyambungkan variabel dengan id pada button
        btnClose= findViewById(R.id.kembali);
        btnSimpan = findViewById(R.id.btnDftr);

        btnClear = findViewById(R.id.btnView);
        //menyambungkan variabel dengan id pada inputan
        NIDN= findViewById(R.id.txtNIDN);
        Nama = findViewById(R.id.txtNama);
        Alamat = findViewById(R.id.txtAlamat);
        radioJKL = findViewById(R.id.rdjk);
        radiostats= findViewById(R.id.rdjk5);
        Telepon = findViewById(R.id.txthp);

        //menyambungkan variabel pada masing masih checkbox
        cb1 = findViewById(R.id.cmb2);
        cb2 = findViewById(R.id.cmb3);

        cb6 = findViewById(R.id.cmb1);

        //menyambung variabel dengan radiobutton
        rb_lk = findViewById(R.id.rdjk2);
        rb_pr = findViewById(R.id.rdjk1);
        rb1 = findViewById(R.id.rdjk3);
        rb2 = findViewById(R.id.rdjk4);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nidn = NIDN.getText().toString();
                String nama = Nama.getText().toString();
                String alamat = Alamat.getText().toString();
                String tlpn = Telepon.getText().toString();
                String Jenjang = "";
                pilihanJKL = findViewById(radioJKL.getCheckedRadioButtonId());
                pStats = findViewById(radiostats.getCheckedRadioButtonId());
                String pilihan = pilihanJKL.getText().toString();
                String status = pStats.getText().toString();

                if (cb1.isChecked()) {
                    Jenjang += cb1.getText().toString() + ", ";

                    if (cb2.isChecked()) {
                        Jenjang += cb2.getText().toString() + ", ";
                    }


                    if (cb6.isChecked()) {
                        Jenjang += cb6.getText().toString() + ", ";
                    }
                }

                ContentValues values = new ContentValues();
                values.put("nim", nidn);
                values.put("nama", nama);
                values.put("alamat", alamat);
                values.put("telp", tlpn);
                values.put("jkel", pilihan);
                values.put("Stats", status );
                values.put("jenjang", Jenjang);



                db.insert("tbl_dosen", null, values);
                Toast.makeText(getApplicationContext(), "Data " + NIDN + " Berhasil Tersimpan !", Toast.LENGTH_LONG).show();
                clearText();


            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clearText();
            }

        });

        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Exit();
            }

        });

    }

    void clearText() {
        NIDN.setText("");
        Nama.setText("");
        Alamat.setText("");
        Telepon.setText("");

        cb1.setChecked(false);
        cb2.setChecked(false);
        cb6.setChecked(false);
        radioJKL.clearCheck();
        radiostats.clearCheck();

    }

    public void Exit(){
        finish();


    }
}

