package com.example.inventory.iu.signup;


import com.example.inventory.iu.base.BasePresenter;

public class SignUpPresenter implements SignUpContract.Presenter,SingUpInteractorImpl.SingUpInteractor{

    private SingUpInteractorImpl interactor;

    private SignUpContract.View view;
    public SignUpPresenter(SignUpContract.View view) {
        this.view=view;
        this.interactor= new SingUpInteractorImpl(this);
    }

    /**
     * Este método viene del contrato SignUpContract.Presenter
     * @param user
     * @param password
     * @param confirmPassword
     * @param email
     */
    @Override
    public void addUser(String user, String password, String confirmPassword, String email) {
        view.showProgressDialog();
        interactor.validateUser(user,password,confirmPassword,email);
    }

    @Override
    public void onDestroy() {
        view = null;
        this.interactor=null;
    }

    @Override
    public void onPasswordsNotEqualError() {
        view.hideProgressDialog();
        view.setPasswordsNotEqualError();
    }

    @Override
    public void onUserExistsError() {
        view.hideProgressDialog();
        view.setUserExistsError();
    }

    @Override
    public void onEmailFormatError() {
        view.hideProgressDialog();
        view.setEmailFormatError();
    }

    @Override
    public void onEmailEmptyError() {
        view.hideProgressDialog();
        view.setEmailEmptyError();
    }

    @Override
    public void onUserEmptyError() {
        view.hideProgressDialog();
        view.setUserEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.hideProgressDialog();
        view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordFormatError() {
        view.hideProgressDialog();
        view.setPasswordFormatError();
    }

    @Override
    public void onSuccess() {
        view.hideProgressDialog();
        view.onSuccess();
    }
}
