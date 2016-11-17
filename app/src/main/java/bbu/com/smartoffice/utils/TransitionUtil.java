package bbu.com.smartoffice.utils;

import android.transition.Transition;
import android.transition.TransitionInflater;

import static bbu.com.smartoffice.ManageActivity.manageActivity;

/**
 * Created by G on 2016/11/17 0017.
 */

public class TransitionUtil {

    public static Transition getTransition(int res) {
        return TransitionInflater.from(manageActivity).inflateTransition(res);
    }
}
