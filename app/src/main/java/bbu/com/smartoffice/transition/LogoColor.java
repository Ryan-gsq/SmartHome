package bbu.com.smartoffice.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import bbu.com.smartoffice.R;
import bbu.com.smartoffice.utils.CalculateColorUtil;

/**
 * Created by G on 2016/11/17 0017.
 */

public class LogoColor extends Transition {

    public LogoColor() {
    }

    public LogoColor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {

    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {

    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        final ImageView view = (ImageView) endValues.view;
        final int startColor = view.getContext().getResources().getColor(R.color.white);
        final int endColor = view.getContext().getResources().getColor(R.color.accent);

        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float percent = (float) valueAnimator.getAnimatedValue();
                int color = CalculateColorUtil.calculateColor(startColor, endColor, percent);
                view.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            }
        });
        return valueAnimator;
    }
}
