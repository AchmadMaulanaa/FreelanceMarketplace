package com.example.asus.freelancemarketplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class FirebaseDBReadSingleActivity extends AppCompatActivity {
    private Button btSubmit;
    private EditText etPekerjaan;
    private EditText etNama;
    private EditText etAlamat;
    private EditText etGaji;
    private EditText etDeskripsi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_create);
        etPekerjaan = (EditText) findViewById(R.id.et_pekerjaan);
        etNama = (EditText) findViewById(R.id.et_namaPerusahaan);
        etAlamat = (EditText) findViewById(R.id.et_alamatPerusahaan);
        etGaji = (EditText) findViewById(R.id.et_gajiPerusahaan);
        etDeskripsi = (EditText) findViewById(R.id.et_deskripsi);
        btSubmit = (Button) findViewById(R.id.bt_submit);

        etNama.setEnabled(false);
        etPekerjaan.setEnabled(false);
        etAlamat.setEnabled(false);
        etGaji.setEnabled(false);
        etDeskripsi.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        PekerjaanActivity pekerjaan = (PekerjaanActivity) getIntent().getSerializableExtra("data");
        if(pekerjaan!=null){
            etNama.setText(pekerjaan.getNama());
            etAlamat.setText(pekerjaan.getAlamat());
            etPekerjaan.setText(pekerjaan.getPekerjaan());
            etGaji.setText(pekerjaan.getGaji());
            etDeskripsi.setText(pekerjaan.getDeskripsi());

        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, FirebaseDBReadSingleActivity.class);
    }
}
