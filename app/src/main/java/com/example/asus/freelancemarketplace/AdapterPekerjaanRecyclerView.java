package com.example.asus.freelancemarketplace;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.asus.freelancemarketplace.R;
import com.example.asus.freelancemarketplace.PekerjaanActivity;

public class AdapterPekerjaanRecyclerView extends RecyclerView.Adapter<AdapterPekerjaanRecyclerView.ViewHolder> {

    private ArrayList<PekerjaanActivity> daftarPekerjaan;
    private Context context;
    FirebaseDataListener listener;

    public AdapterPekerjaanRecyclerView(ArrayList<PekerjaanActivity> pekerjaans, Context ctx){
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarPekerjaan = pekerjaans;
        context = ctx;
        listener = (FirebaseDBReadActivity)ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle;


        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namaPekerjaan);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_pekerjaan, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        final String name = daftarPekerjaan.get(position).getNama();
        System.out.println("BARANG DATA one by one "+position+daftarPekerjaan.size());
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Read detail data
                 */   context.startActivity(FirebaseDBReadSingleActivity.getActIntent((Activity) context).putExtra("data", daftarPekerjaan.get(position)));

            }
        });

        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Delete dan update data
                 */
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);

                //apabila tombol edit diklik
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                context.startActivity(FirebaseDBCreateActivity.getActIntent((Activity) context).putExtra("data", daftarPekerjaan.get(position)));
                            }
                        }
                );

                //apabila tombol delete diklik
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                /**
                                 *  Kodingan untuk tutorial Selanjutnya :p Delete data
                                 */dialog.dismiss();
                                listener.onDeleteData(daftarPekerjaan.get(position), position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada Pekerjaan
         */
        return daftarPekerjaan.size();
    }



    public interface FirebaseDataListener{
        void onDeleteData(PekerjaanActivity pekerjaan, int position);
    }
}
