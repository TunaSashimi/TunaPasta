package com.tunaPasta15.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tunaPasta15.R;
import com.tunaPasta15.widget.AnimatorProxy;

public class PathAnimationTest extends Activity {
    Button button;
    AnimatorProxy animatorProxy;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathanimatontest);

        button = findViewById(R.id.button);
        animatorProxy = AnimatorProxy.wrap(button);

        // Set up the path we're animating along
        AnimatorPath path = new AnimatorPath();
        path.moveTo(0, 0);
        path.lineTo(0, 300);
        path.curveTo(100, 0, 300, 900, 400, 500);

        // Set up the animation
        final ObjectAnimator anim = ObjectAnimator.ofObject(
                this, "buttonLation",new PathEvaluator(), path.getPoints().toArray());
        anim.setDuration(5000);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.start();
            }
        });
    }

    /**
     * We need this setter to translate between the information the animator
     * produces (a new "PathPoint" describing the current animated location)
     * and the information that the button requires (an xy location). The
     * setter will be called by the ObjectAnimator given the 'buttonLoc'
     * property string.
     */
    public void setButtonLation(PathPoint pathPoint) {
        animatorProxy.setTranslationX(pathPoint.mX);
        animatorProxy.setTranslationY(pathPoint.mY);
    }
}
