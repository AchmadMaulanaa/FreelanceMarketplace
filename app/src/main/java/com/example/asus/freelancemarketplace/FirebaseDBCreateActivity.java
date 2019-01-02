package com.example.asus.freelancemarketplace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDBCreateActivity extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;
    // variable fields EditText dan Button
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

        // inisialisasi fields EditText dan Button
        etPekerjaan = (EditText) findViewById(R.id.et_pekerjaan);
        etNama = (EditText) findViewById(R.id.et_namaPerusahaan);
        etAlamat = (EditText) findViewById(R.id.et_alamatPerusahaan);
        etGaji = (EditText) findViewById(R.id.et_gajiPerusahaan);
        etDeskripsi = (EditText) findViewById(R.id.et_deskripsi);
        btSubmit = (Button) findViewById(R.id.bt_submit);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        // kode yang dipanggil ketika tombol Submit diklik
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(etPekerjaan.getText().toString()) && !isEmpty(etNama.getText().toString()) && !isEmpty(etAlamat.getText().toString())&& !isEmpty(etGaji.getText().toString())&& !isEmpty(etDeskripsi.getText().toString()))
                    submitPekerjaan(new PekerjaanActivity(etPekerjaan.getText().toString(), etNama.getText().toString(), etAlamat.getText().toString(), etGaji.getText().toString(), etDeskripsi.getText().toString()));
                else
                    Toast.makeText(getApplicationContext(), "Data pekerjaan tidak boleh kosong", Toast.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        etNama.getWindowToken(), 0);
            }
        });

    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updatePekerjaan(PekerjaanActivity pekerjaan) {
        // kodingan untuk next tutorial ya :p
        /**
         * Baris kode yang digunakan untuk mengupdate data barang
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        database.child("pekerjaan") //akses parent index, ibaratnya seperti nama tabel
                .child(pekerjaan.getKey()) //select barang berdasarkan key
                .setValue(pekerjaan) //set value barang yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update barang sukses
                         */
                        Toast.makeText(getApplicationContext(), "Data berhasil diupdatekan", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void submitPekerjaan(PekerjaanActivity pekerjaan) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("pekerjaan").push().setValue(pekerjaan).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etPekerjaan.setText("");
                etNama.setText("");
                etAlamat.setText("");
                etGaji.setText("");
                etDeskripsi.setText("");
                Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, FirebaseDBCreateActivity.class);
    }

}
