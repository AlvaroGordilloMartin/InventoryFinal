package com.example.inventory.iu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import com.example.inventory.R;
import com.example.inventory.iu.InventoryActivity;
import com.example.inventory.iu.signup.SingUpActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private LoginContract.Presenter presenter;
    private TextInputLayout tilUser;
    private TextInputLayout tilPassword;
    private ProgressBar progressBar;
    private TextInputEditText tieUser;
    private TextInputEditText tiePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar);
        tilUser = findViewById(R.id.tilUser);
        tilPassword = findViewById(R.id.tilPassword);
        tieUser = findViewById(R.id.tieUser);
        tieUser.addTextChangedListener(new LoginTextWatcher(tieUser));
        tiePassword = findViewById(R.id.tiePassword);
        tiePassword.addTextChangedListener(new LoginTextWatcher(tiePassword));
        presenter = new LoginPresenter(this);
    }

    public void showSignUp(View view){
        Intent intent = new Intent( LoginActivity.this, SingUpActivity.class);
        startActivity(intent);
    }

    /**
     * Metodo que valida si el inicio de sesion es correcto
     * @param view
     */
    public void validateUser(View view)
    {
        presenter.validateCredentials(tieUser.getText().toString(),tiePassword.getText().toString());
    }

    /**
     * Este metodo viene del contrato LoginContract.View
     */
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Este metodo viene del contrato LoginContract.View
     */
    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    /**
     * Este metodo viene del contrato LoginContract.View
     */
    @Override
    public void onAuthenticationError() {

    }

    /**
     * Este metodo viene de la interfaz baseview
      */
    @Override
    public void onSuccess() {
        Intent intent = new Intent(LoginActivity.this, InventoryActivity.class);
        startActivity(intent);
    }

    /**
     * Este metodo viene de la interfaz UserView
     */
    @Override
    public void setUserEmptyError() {
        tilUser.setError(getResources().getString(R.string.err_user_empty));
        showSoftKeyBoard(tieUser);
    }
    /**
     * Este metodo viene de la interfaz UserView
     */
    @Override
    public void setPasswordFormatError() {
        tilPassword.setError(getResources().getString(R.string.err_password_format));
        showSoftKeyBoard(tiePassword);
    }
    /**
     * Este metodo viene de la interfaz UserView
     */
    @Override
    public void setPasswordEmptyError() {
        tilPassword.setError(getResources().getString(R.string.err_password_empty));
        showSoftKeyBoard(tiePassword);
    }

    @Override
    public void setAuthenticationError() {
        Snackbar.make(findViewById(R.id.llcontainer),getResources().getString(R.string.err_authentication),Snackbar.LENGTH_SHORT).show();
        //Snackbar.make(findViewById(android.R.id.content),getResources().getString(R.string.err_authentication),Snackbar.LENGTH_SHORT).show();
        showSoftKeyBoard(tieUser);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    //region claseControlError
    class LoginTextWatcher implements TextWatcher {

        private View view;

        LoginTextWatcher(View view) {
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
                    validateUser(tieUser.getText().toString());
                    break;
                case R.id.tiePassword:
                    validatePassword(tiePassword.getText().toString());
                    break;

            }
        }
    }

    private void validateUser(String password) {
        if (tieUser.getText().toString().trim().isEmpty()) {
            tilUser.setError(getString(R.string.err_user_empty));
            showSoftKeyBoard(tieUser);
        }else {
            tilUser.setErrorEnabled(false);
        }

    }

    private void validatePassword(String password) {
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