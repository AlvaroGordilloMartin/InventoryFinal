package com.example.inventory.iu.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.inventory.R;
import com.example.inventory.iu.InventoryActivity;
import com.example.inventory.iu.login.LoginPresenter;
import com.example.inventory.iu.utils.CommonUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SingUpActivity extends AppCompatActivity implements SignUpContract.View {

    private TextInputLayout tilUser;
    private TextInputLayout tilPassword;
    private TextInputLayout tilEmail;
    private TextInputLayout tilConfirmPassword;
    private SignUpContract.Presenter presenter;

    private TextInputEditText tieUser;
    private TextInputEditText tiePassword;
    private TextInputEditText tieEmail;
    private TextInputEditText tieConfirmPassword;

    private ProgressDialog progressDialog;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        //Recogemos el nombre del usuario
         tieUser = findViewById(R.id.tieUser);

        //Recogemos la primera contraseña
         tiePassword = findViewById(R.id.tiePassword);

        //Recogemos la contraseña de confirmacion
         tieConfirmPassword = findViewById(R.id.tieConfirmPassword);

        //Recogemos email
         tieEmail = findViewById(R.id.tieEmail);

        //Empieza los inputLayout

        //Recogemos el nombre del usuario
         tilUser = findViewById(R.id.tilUser);

        //Recogemos la primera contraseña
         tilPassword = findViewById(R.id.tilPassword);

        //Recogemos la contraseña de confirmacion
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword);

        //Recogemos email
         tilEmail = findViewById(R.id.tilEmail);

         //Añadir Listener
        tieUser.addTextChangedListener(new SingUpTextWatcher(tieUser));
        tiePassword.addTextChangedListener(new SingUpTextWatcher(tiePassword));
        tieConfirmPassword.addTextChangedListener(new SingUpTextWatcher(tieConfirmPassword));
        tieEmail.addTextChangedListener(new SingUpTextWatcher(tieEmail));

        presenter = new SignUpPresenter(this);
    }

    /**
     * Metodo que comprueba si es valido el usuario, la contraseña y el email
     */
    public void signUp(View view){
        presenter.addUser(tieUser.toString(),tiePassword.toString(),tieConfirmPassword.toString(),tieEmail.toString());
    }

    /**
     * Se da de alta el usuario y pasamos a la pantalla Login
     */
    @Override
    public void onSuccess() {
        Intent intent = new Intent( SingUpActivity.this, InventoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPasswordsNotEqualError() {
        tilConfirmPassword.setError(getString(R.string.err_confirmpassword));
        showSoftKeyBoard(tieConfirmPassword);
    }

    @Override
    public void setUserExistsError() {
        Snackbar.make(findViewById(R.id.llcontainer),getResources().getString(R.string.err_user_exists),Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void setEmailFormatError() {
        tilEmail.setError(getString(R.string.err_email_format));
        showSoftKeyBoard(tieEmail);
    }

    @Override
    public void showProgressDialog() {
        progressDialog= CommonUtils.showLoadingDialog(this);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void setEmailEmptyError() {
        tilEmail.setError(getString(R.string.err_email_empty));
        showSoftKeyBoard(tieEmail);
    }

    @Override
    public void setUserEmptyError() {
        tilUser.setError(getString(R.string.err_user_empty));
        showSoftKeyBoard(tieUser);
    }

    @Override
    public void setPasswordFormatError() {
        tilPassword.setError(getString(R.string.err_password_format));
        showSoftKeyBoard(tiePassword);
    }

    @Override
    public void setPasswordEmptyError() {
        tilPassword.setError(getString(R.string.err_password_empty));
        showSoftKeyBoard(tiePassword);
    }

    @Override
    public void setAuthenticationError() {

    }

    //TODO Error al pulsar el registro ARREGLAR

    /**
     * Metodo que destruye las instancias del Acitivty
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    class SingUpTextWatcher implements TextWatcher {

        private View view;

        SingUpTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.tieUser:
                    validateUser();
                    break;
                case R.id.tiePassword:
                    validatePassword();
                    break;
                case R.id.tieConfirmPassword:
                    validateConfirmPassword();
                    break;
                case R.id.tieEmail:
                    validateEmail();
                    break;

            }
        }
    }

    private void validateUser() {
        if (tieUser.getText().toString().trim().isEmpty()) {
            tilUser.setError(getString(R.string.err_user_empty));
            showSoftKeyBoard(tieUser);
        }else {
            tilUser.setErrorEnabled(false);
        }

    }

    private void validatePassword() {
        if (tiePassword.getText().toString().trim().isEmpty()) {
            tilPassword.setError(getString(R.string.err_password_empty));
            showSoftKeyBoard(tiePassword);

        }else if(tiePassword.getText().toString().length()<3) {
            tilPassword.setError(getString(R.string.err_password_format));
            showSoftKeyBoard(tiePassword);
        }else {
            tilPassword.setErrorEnabled(false);
        }

    }

    private  void validateConfirmPassword() {
        if(tiePassword.getText().toString().trim().equals(tieConfirmPassword.getText().toString())) {
            tilConfirmPassword.setError(getString(R.string.err_confirmpassword));
            showSoftKeyBoard(tieConfirmPassword);
        }
        else{
            tilConfirmPassword.setErrorEnabled(false);
        }
    }

    private void validateEmail() {
        if (tieEmail.getText().toString().trim().isEmpty()) {
            tieEmail.setError(getString(R.string.err_email_empty));
            showSoftKeyBoard(tieEmail);
        }else {
            tilEmail.setErrorEnabled(false);
        }

    }
    //endregion

    /**
     * Este método pone el foco en la vista y habilita el teclado virtual
     * @param view
     */
    public void showSoftKeyBoard(View view){
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * este método oculta el teclado virtual
     * @param view
     */
    public void hideSoftKeyBoard(View view){
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
