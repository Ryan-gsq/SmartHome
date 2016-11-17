package bbu.com.smartoffice.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import bbu.com.smartoffice.R;
import bbu.com.smartoffice.base.BaseFragment;
import bbu.com.smartoffice.utils.ConditionTaskUtil;
import bbu.com.smartoffice.utils.TransitionUtil;
import butterknife.Bind;
import butterknife.ButterKnife;

import static bbu.com.smartoffice.ManageActivity.manageActivity;

/**
 * Created by G on 2016/11/15 0015.
 */

public class Launch extends BaseFragment {
    @Bind(R.id.imageView)
    ImageView logo;

    private View rootView;
    private ConditionTaskUtil conditionTaskUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_launch, container, false);
        ButterKnife.bind(this, rootView);
        anim();
        //条件任务
        //eg.完成3次某种请求后加载下一个UI
        conditionTaskUtil = new ConditionTaskUtil(5, new LoadNextUI());
        LoadData();
        return rootView;
    }

    private void anim() {
        setEnterTransition(new Slide(Gravity.BOTTOM));
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int width = logo.getWidth();
                int height = logo.getHeight();
                int minRadius = (int) Math.sqrt(width * width + height * height);

                logo.setAlpha(0f);
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(logo, width / 2, height / 2, 10, minRadius);
                circularReveal
                        .setDuration(4000L).setStartDelay(500L);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(logo, "alpha", 0f, 0.87f);
                alpha.setDuration(4000L).setStartDelay(500L);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(alpha, circularReveal);
                set.start();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        manageActivity.setDrawerLayoutAllow(false);
    }

    private void LoadData() {
        //TODO Load something   eg: 延时3s后进入Main
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        conditionTaskUtil.excute();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class LoadNextUI implements Runnable {
        @Override
        public void run() {
            logo.setTransitionName("logo");
            manageActivity.setDrawerLayoutAllow(true);

            BaseFragment instance = BaseFragment.getInstance(Main.class);
            instance.setSharedElementEnterTransition(TransitionUtil.getTransition(R.transition.main_share_entry));

            instance.setEnterTransition(TransitionUtil.getTransition(R.transition.main_entry));

            setExitTransition(TransitionUtil.getTransition(R.transition.launch_exit));

            manageActivity.replaceFragment(instance).addSharedElement(logo, "logo").commitAllowingStateLoss();
        }
    }

}
