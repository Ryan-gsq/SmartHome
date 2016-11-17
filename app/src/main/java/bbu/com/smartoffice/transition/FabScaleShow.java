package bbu.com.smartoffice.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by G on 2016/11/17 0017.
 */

public class FabScaleShow extends Transition {

    public FabScaleShow() {
    }

    public FabScaleShow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void captureStartValues(TransitionValues transitionValues) {
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        transitionValues.view.setScaleX(0f);
        transitionValues.view.setScaleY(0f);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        View view = endValues.view;
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0, 1f);
        set.playTogether(scaleX, scaleY);
        return set;
    }
}
