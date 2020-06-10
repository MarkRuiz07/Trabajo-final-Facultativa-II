package com.example.recycle_view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    TextView mIdentificacionView, mNombresView, mApellidosView, mCargoView, mAreaTrabajoView, mTelefonoView;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);

        ActionBar actionBar=getSupportActionBar();

        mIdentificacionView =findViewById(R.id.identificacionView);
        mNombresView =findViewById(R.id.nombresView);
        mImageView=findViewById(R.id.imageView);
        mApellidosView = findViewById(R.id.apellidosView);
        mCargoView =findViewById(R.id.cargoView);
        mAreaTrabajoView =findViewById(R.id.areaTrabajoView);
        mTelefonoView =findViewById(R.id.telefonoView);


        Intent intent= getIntent();
        String mIdentificacion=intent.getStringExtra("iIdent"); //Se obtiene los datos que fueron enviados desde el MainActivity
        String mNomb=intent.getStringExtra("iNomb");
        String mApell=intent.getStringExtra("iApell");
        String mCargo=intent.getStringExtra("iCargo");
        String mTelf=intent.getStringExtra("iTelf");
        String mAreaTrabajo= intent.getStringExtra("iATrab");

        byte[] mBytes=getIntent().getByteArrayExtra("iImage");
        Bitmap bitmap= BitmapFactory.decodeByteArray(mBytes, 0,mBytes.length);

        actionBar.setTitle(mIdentificacion);
        mIdentificacionView.setText(mIdentificacion);
        mNombresView.setText(mNomb);
        mImageView.setImageBitmap(bitmap);
        mApellidosView.setText(mApell);
        mCargoView.setText(mCargo);
        mAreaTrabajoView.setText(mTelf);
        mTelefonoView.setText(mAreaTrabajo);

    }
}
