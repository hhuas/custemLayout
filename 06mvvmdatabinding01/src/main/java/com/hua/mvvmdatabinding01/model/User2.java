package com.hua.mvvmdatabinding01.model;

import android.databinding.ObservableField;

public class User2 {

    public ObservableField<String> name;
    public ObservableField<String> password;

    public User2(String name, String password) {
        this.name = new ObservableField<>(name);
        this.password = new ObservableField<>(password);
    }
}
