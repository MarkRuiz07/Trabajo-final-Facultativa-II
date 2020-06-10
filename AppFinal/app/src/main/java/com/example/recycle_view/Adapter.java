package com.example.recycle_view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Model> models;
    MenuItem.OnMenuItemClickListener onMenuItemClickListener; //Crear instancia del Menu contextual

    public Adapter(Context c, ArrayList<Model> models, MenuItem.OnMenuItemClickListener onMenuItemClickListener){
        this.c = c;
        this.models = models;
        this.onMenuItemClickListener = onMenuItemClickListener;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
                return new MyHolder(view);
    }
    public void notifyChanged() { this.notifyDataSetChanged(); } //Notifica al adaptor sobre las modificaciones para actualizar los datos
    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position){
       holder.mNombres.setText(models.get(position).getNombres() + ' ' + models.get(position).getApellidos());
       holder.mIdentificacion.setText(models.get(position).getIdentificacion());
       holder.mImageView.setImageResource(models.get(position).getImgEmpleado());

       holder.setItemClickListener(new ItemClickListener() {
           @Override
           public void onItemClickListener(View v, int position) {
               try {
                   String hNombres=models.get(position).getNombres();
                   String hApellidos=models.get(position).getApellidos();
                   String hIdentificacion=models.get(position).getIdentificacion(); //Selecciona los datos del elemento que se seleccionó en el recyclerView
                   String hCargo=models.get(position).getCargo();
                   String hTelefono=models.get(position).getTelefono();
                   String hAreaDeTrabajo=models.get(position).getAreaDeTrabajo();
                   BitmapDrawable bitmapDrawable=(BitmapDrawable)holder.mImageView.getDrawable();
                   Bitmap bitmap=bitmapDrawable.getBitmap();

                   ByteArrayOutputStream stream= new ByteArrayOutputStream();
                   bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                   byte[]bytes=stream.toByteArray();

                   Intent intent=new Intent(c, Activity2.class);

                   intent.putExtra("iNomb", hNombres);
                   intent.putExtra("iApell", hApellidos);
                   intent.putExtra("iImage", bytes);
                   intent.putExtra("iIdent", hIdentificacion); //Se pasan los datos al activity2
                   intent.putExtra("iCargo", hCargo);
                   intent.putExtra("iTelf", hTelefono);
                   intent.putExtra("iATrab", hAreaDeTrabajo);
                   c.startActivity(intent);
               }
               catch(Exception e) {
                   Toast.makeText(c, "Error: " + e.getMessage(), Toast.LENGTH_LONG);
               }

           }
       });


       holder.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
           @Override
           public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { //crear el menu contextual
               menu.setHeaderTitle("Menú");

               menu.add("Eliminar").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) { //Opcion eliminar
                       Repository.Delete(position);
                       notifyChanged();
                       return true;
                   }
               });

               menu.add("Agregar").setOnMenuItemClickListener(onMenuItemClickListener); //Opcion agregar
           }
       });

       
    }
    @Override
    public int getItemCount(){
        return models.size();
    }
}
