package com.example.privlok;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList arrayList_title,arrayList_username,arrayList_account,arrayList_password;

    CustomAdapter( Activity activity, Context context, ArrayList arrayList_title, ArrayList arrayList_username, ArrayList arrayList_account, ArrayList arrayList_password)
    {
        this.context= context;
        this.activity=activity;
        this.arrayList_title=arrayList_title;
        this.arrayList_username=arrayList_username;
        this.arrayList_account=arrayList_account;
        this.arrayList_password=arrayList_password;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list_layout_data,parent,false);
        return new MyViewHolder(view);
    }
    //@RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title_txt.setText(String.valueOf(arrayList_title.get(position)));
        holder.username_txt.setText(String.valueOf(arrayList_username.get(position)));
        holder.account_txt.setText(String.valueOf(arrayList_account.get(position)));
        holder.password_txt.setText(String.valueOf(arrayList_password.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                //intent.putExtra("id", String.valueOf(arrayList_id.get(position)));
                intent.putExtra("title", String.valueOf(arrayList_title.get(position)));
                intent.putExtra("username", String.valueOf(arrayList_username.get(position)));
                intent.putExtra("account", String.valueOf(arrayList_account.get(position)));
                intent.putExtra("password", String.valueOf(arrayList_password.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList_title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mainLayout;
        TextView title_txt, username_txt, account_txt, password_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_txt = itemView.findViewById(R.id.textViewTitle);
            username_txt = itemView.findViewById(R.id.textViewUsername);
            account_txt = itemView.findViewById(R.id.textViewAccountNo);
            password_txt = itemView.findViewById(R.id.textViewPassword);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
