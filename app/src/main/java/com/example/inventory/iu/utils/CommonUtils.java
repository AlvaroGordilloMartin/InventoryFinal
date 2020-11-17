package com.example.inventory.iu.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    /**
     * Metodo estatico que comprueba si la contraseña es valida
     * >= 8 caracteres
     * 1 mayúscula
     * 1 número
     * @param password
     * @return
     */
    public static boolean isPasswordValid(String password){
        //Pattern pattern = Pattern.compile("^(?=.*[A-Z])[a-zA-Z0-9].{7,}$");
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    /**
     * Metodo que comprueba el formato del email
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {

        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Metodo que muestra un cuadro de dialogo con un progressDialog con el mismo estilo y propiedades para toda la aplicacion
     * @return
     */
    public static ProgressDialog showLoadingDialog(Context context){
        ProgressDialog progressDialog= new ProgressDialog(context);
        progressDialog.show();
        if(progressDialog.getWindow()!=null){
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }
}
