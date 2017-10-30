package com.example.isystem.vistafirma;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.kyanogen.signatureview.SignatureView;

public class MainActivity extends AppCompatActivity {

    private SignatureView signatureView;
    private  MagicalCamera magicalCamera;

    private final int REDIMENCIONAR_IMAGEN_PORCENTAGE = 40;
    MagicalPermissions magicalPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signatureView = (SignatureView) findViewById(R.id.signature_view);

        String[] permissions = new String[] {
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        magicalPermissions = new MagicalPermissions(this, permissions);
        magicalCamera = new MagicalCamera(this, REDIMENCIONAR_IMAGEN_PORCENTAGE, magicalPermissions);

    }

    public void clickGuardarFirma(View v){

        /*Guarda la foto en la memoria interna del dispositivo, si no tiene espacio, pasa a
                    * guardarla en la SD card, retorna la ruta en la cual almacenó la foto */
        String rutaImg = magicalCamera.savePhotoInMemoryDevice(
                signatureView.getSignatureBitmap(),// bitmap de la foto a guardar
                "imgFirma",// nombre con el que se guardará la imgImagen
                "prueba_imagenes",// nombre de la carpeta donde se guardarán las fotos
                MagicalCamera.PNG,// formato de compresion
                true // true: le agrega la fecha al nombre de la foto para no replicarlo
        );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        magicalPermissions.permissionResult(requestCode, permissions, grantResults);
    }
}
