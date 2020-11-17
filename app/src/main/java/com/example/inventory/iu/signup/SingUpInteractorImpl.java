package com.example.inventory.iu.signup;


import android.os.Handler;
import android.text.TextUtils;

import com.example.inventory.data.model.User;
import com.example.inventory.data.repository.UserRepository;
import com.example.inventory.iu.utils.CommonUtils;

public class SingUpInteractorImpl {


    private SingUpInteractor presenter;



    /**
     * Definicion de las inte4rfaces que debe implementar cualquier presentador que haga uso de este interactor
     */
    interface SingUpInteractor{
        void onPasswordsNotEqualError();

        //Metodo que viene de la alternativa 1.4 Usuario ya existe
        void onUserExistsError();

        //Metodo que viene de RN: el email tiene que tener un formato correcto
        void onEmailFormatError();

        //
        void onEmailEmptyError();

        void onUserEmptyError(); //RN-U1 y Alternativa 1.1

        void onPasswordEmptyError(); //RN-U1 y Alternativa 1.1

        void onPasswordFormatError(); //RN-U2 y Alternativa 1.2

        void onSuccess();

    }

    public SingUpInteractorImpl(SingUpInteractorImpl.SingUpInteractor presenter) {
        this.presenter = presenter;

    }

    public void validateUser(final String user, final String password, final String confirmPassword, final String email) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(user)) {
                    presenter.onUserEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    presenter.onPasswordEmptyError();
                    return;
                }
                if (!CommonUtils.isPasswordValid(password))
                {
                    presenter.onPasswordFormatError();
                    return;
                }
                if(!password.equals((confirmPassword))){
                presenter.onPasswordsNotEqualError();
                return;}

                if (TextUtils.isEmpty(email)) {
                    presenter.onEmailEmptyError();
                    return;
                }

                if (!CommonUtils.isEmailValid(email))
                {
                    presenter.onEmailFormatError();
                    return;
                }
                if(UserRepository.getInstance().userExists(user)){
                    presenter.onUserExistsError();
                    return;
                }
                UserRepository.getInstance().add(user,password,email);
                presenter.onSuccess();
            }
        },2000);
    }
}
