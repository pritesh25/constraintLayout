package com.example.constraintlayout;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout root;
    ConstraintSet constraint1, constraint2;
    boolean set = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //addAnimationOperations(); //for activity_main3 layout in setContentView() method

        //clickAnimation();..for activity_main2
    }

    private void clickAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(findViewById(R.id.button3), "translationX", 0, 25, 0);
        animator.setInterpolator(new OvershootInterpolator());
        animator.setStartDelay(500);
        animator.setDuration(1500);
        animator.start();
    }

    private void addAnimationOperations() {


        root = findViewById(R.id.root);

        constraint1 = new ConstraintSet();
        constraint1.clone(root);
        constraint2 = new ConstraintSet();
        constraint2.clone(this, R.layout.activity_main3_alt);

        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                    Transition changeBound = new ChangeBounds();
                    changeBound.setInterpolator(new OvershootInterpolator());

                    TransitionManager.beginDelayedTransition(root, changeBound);

                    ConstraintSet constraint;

                    if (set)
                        constraint = constraint1;
                    else
                        constraint = constraint2;

                    constraint.applyTo(root);
                    set = !set;
                } else {
                    Log.d(this.getClass().getSimpleName(), "animation not supported");
                }
            }
        });
    }
}