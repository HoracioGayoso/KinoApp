package com.tpintegrador.kinoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.tpintegrador.kinoapp.databinding.MainActivityBinding;
import com.tpintegrador.kinoapp.databinding.ToolBarBinding;

public class MainActivity extends AppCompatActivity {
    private MainActivityBinding maBinding;
    private ToolBarBinding tbBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maBinding = MainActivityBinding.inflate(getLayoutInflater());
        tbBinding = ToolBarBinding.inflate(getLayoutInflater());
        setContentView(maBinding.getRoot());
        LoginFragment loginFragment = new LoginFragment();
        openFragment(loginFragment);
    }
    public void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (currentFragment instanceof LoginFragment) {
            // If the current fragment is the login fragment, minimize the app
            moveTaskToBack(true);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                // If the back stack is empty, show an alert dialog to confirm logout
                new AlertDialog.Builder(this)
                        .setTitle("Salir")
                        .setMessage("Estas seguro que quieres salir?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                performLogout();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            } else {
                super.onBackPressed();
            }
        }
    }
    public void performLogout() {
        FirebaseAuth.getInstance().signOut();
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        LoginFragment loginFragment = new LoginFragment();
        openFragment(loginFragment);
    }
}
