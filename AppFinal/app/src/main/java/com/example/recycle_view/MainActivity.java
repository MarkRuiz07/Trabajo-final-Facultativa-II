package com.example.recycle_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Adapter myAdapter;
    Boolean created = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=findViewById(R.id.recyclerView1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter=new Adapter(this, getMyList(), this.handleMenuItemClickListener());
        mRecyclerView.setAdapter(myAdapter);
        created = true;

    }

    private MenuItem.OnMenuItemClickListener handleMenuItemClickListener() { //es una funcion que me permite pasar el evento click al adaptador para personalizarlo en el main activity y no duplicar el codigo
        MenuItem.OnMenuItemClickListener click = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Agregar(); //Mando a llamar la funcion desde el main activity
                return false;
            }
        };

        return click;
    }

    private ArrayList<Model> getMyList(){ //Tres datos(empleados) diferentes ingresados


        if(!created) {

            Repository.ClearAll();

            Model m = new Model();

            m.setNombres("Cristiano");
            m.setApellidos("Ronaldo");
            m.setImgEmpleado(R.drawable.pass);
            m.setIdentificacion("E04785555");
            m.setCargo("Administrador");
            m.setTelefono("74561232");
            m.setAreaDeTrabajo("Administrativo");
            Repository.Add(m);

            m = new Model();
            m.setNombres("Marcelo");
            m.setApellidos("Urbina");
            m.setImgEmpleado(R.drawable.work);
            m.setIdentificacion("E08855555");
            m.setCargo("Recursos TIC");
            m.setTelefono("75666663");
            m.setAreaDeTrabajo("Laboratorio D");
            Repository.Add(m);

            m = new Model();
            m.setNombres("Mark");
            m.setApellidos("Ruiz");
            m.setImgEmpleado(R.drawable.demographic);
            m.setIdentificacion("E00000212");
            m.setCargo("Director");
            m.setTelefono("85213533");
            m.setAreaDeTrabajo("Dirección de la Empresa");
            Repository.Add(m);
        }

        return Repository.List();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Se crea el método del menú de opciones
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salir: {
                this.finish(); // cierra la app
                break;
            }
            case R.id.agregar: { // mostrar el layout en forma de dialogo
                this.Agregar();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void Agregar() //creo el método para ingresar los nuevos datos
    {
        Intent intent = new Intent();
        final Dialog dlg = new Dialog(this);
        dlg.setContentView(R.layout.dialog_add_layout);
        dlg.setTitle("Agregar Empleado");
        dlg.setCancelable(false);

        Button btAddNew = (Button)dlg.findViewById(R.id.btnRegistrar); //Boton que me permite añadir los valores al arreglo
        Button btCancel = (Button)dlg.findViewById(R.id.btncancelar);

        btAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //añadir elementos onClick al boton. Aqui se obtienen los datos y se guardan en el arreglo usando la clase Repository

                EditText editText_ident = (EditText)dlg.findViewById(R.id.editText_Ident);
                EditText editText_nomb = (EditText)dlg.findViewById(R.id.editText_Nomb);
                EditText editText_apell = (EditText)dlg.findViewById(R.id.editText_Apell);
                EditText editText_cargo = (EditText)dlg.findViewById(R.id.editText_Cargo);
                EditText editText_areaTrab = (EditText)dlg.findViewById(R.id.editText_AreaTrab);
                EditText editText_telf = (EditText)dlg.findViewById(R.id.editText_Telf);

                Model model = new Model();
                model.setNombres(editText_ident.getText().toString());
                model.setApellidos(editText_nomb.getText().toString());
                model.setImgEmpleado(R.drawable.work);
                model.setCargo(editText_cargo.getText().toString());
                model.setAreaDeTrabajo(editText_telf.getText().toString());
                model.setTelefono(editText_areaTrab.getText().toString());
                model.setIdentificacion(editText_apell.getText().toString());

                editText_ident.setText(""); //dejar en blanco los cuadros de texto
                editText_nomb.setText("");
                editText_apell.setText("");
                editText_cargo.setText("");
                editText_areaTrab.setText("");
                editText_telf.setText("");

                Repository.Add(model);
                myAdapter.notifyDataSetChanged();

                dlg.cancel();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });

        dlg.show();
    }
}
