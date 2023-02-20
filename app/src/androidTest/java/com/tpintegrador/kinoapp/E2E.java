package com.tpintegrador.kinoapp;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.viewbinding.ViewBinding;


import com.tpintegrador.kinoapp.databinding.LoginBinding;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


public final class E2E {

    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test.
     */
    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void intentsInit() {
        // initialize Espresso Intents capturing
        Intents.init();
    }

    @After
    public void intentsTeardown() {
        // release Espresso Intents capturing
        Intents.release();
    }

    @Test
    public void appThroughout() {
        // Type text and then press the button.
        onView(isDisplayed());
        onView(withId(R.id.usuario_textImputLayout))
                .perform(click());
        onView(withId(R.id.usuario_textInputEditText))
                .perform(typeText("joaco.fernandez.2212@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.contrasena_textImputLayout))
                .perform(click());
        onView(withId(R.id.contrasena_textInputEditText))
                .perform(typeText("joacof17"), closeSoftKeyboard());
        onView(withId(R.id.recordar_usuario_checkbox)).perform(click());
        onView(withId(R.id.ingresar_button)).perform(click());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}