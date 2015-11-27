package com.example.eliseo.guardarentablamicrosd;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void Escribir(View v) {
        File extDir = Environment.getExternalStorageDirectory();
        String FILE_NAME = extDir.getAbsolutePath() + "/Datitos.txt"; //"/storage/extSdCard/Datos.txt";
        //String FILE_NAME=extDir.getAbsolutePath()+"/storage/extSdCard/OtrosDatos.txt";

        try {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                FileOutputStream fos = new FileOutputStream(new File(FILE_NAME), true);
                Calendar cal = Calendar.getInstance();

                String cadena = cal.get(cal.YEAR) +
                        "-" + (cal.get(cal.MONTH) + 1) +
                        "-" + cal.get(cal.DAY_OF_MONTH) +
                        " " + cal.get(cal.HOUR) +
                        "-" + cal.get(cal.MINUTE) +
                        "-" + cal.get(cal.SECOND) +
                        "\r\n";
                fos.write(cadena.getBytes());
                fos.close();
                Toast.makeText(getBaseContext(), "Ok", Toast.LENGTH_LONG).show();

            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
                Toast.makeText(getBaseContext(), "Solo lectura", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getBaseContext(), "No es posible acceder a la Micro SD", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    public void Leer(View v) {
        File extDir = Environment.getExternalStorageDirectory();
        String FILE_NAME = extDir.getAbsolutePath() + "/Datitos.txt";

        try {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                FileInputStream in = new FileInputStream(new File(FILE_NAME));
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String linea;
                while ((linea = bufferedReader.readLine()) !=null){
                    sb.append(linea + "\r\n");


                    Toast.makeText(getBaseContext(),linea , Toast.LENGTH_LONG).show();
                }
                bufferedReader.close();
                Toast.makeText(getBaseContext(), sb.toString(), Toast.LENGTH_LONG).show();
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
                Toast.makeText(getBaseContext(), "Solo lectura", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getBaseContext(), "No es posible acceder a la Micro SD", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }
}
