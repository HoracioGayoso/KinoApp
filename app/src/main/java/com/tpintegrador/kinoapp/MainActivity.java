package com.tpintegrador.kinoapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.tpintegrador.kinoapp.databinding.MainActivityBinding;
import com.tpintegrador.kinoapp.databinding.ToolBarBinding;
import com.tpintegrador.kinoapp.model.Pelicula;

public class MainActivity extends AppCompatActivity implements ListaPeliculasFragment.OnPeliculaClickListener {
    private MainActivityBinding maBinding;
    private ToolBarBinding tbBinding;
    private AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getInstance(this);
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
    public void openFragmentAddStack(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
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

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    public void OnPeliculaClick(Pelicula pelicula) {
        Toast.makeText(this, "llego a activity", Toast.LENGTH_SHORT).show();
        ForoFragment foroFragment = new ForoFragment();
        Bundle args = new Bundle();
        args.putSerializable("pelicula", pelicula);
        foroFragment.setArguments(args);
        openFragmentAddStack(foroFragment);
    }
}
