package bbu.com.smartoffice.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by G on 2016/11/16 0016.
 */

public class DrawableUtil {
    public static Drawable resize(Drawable image, int w, int h) {
        Bitmap b = ((BitmapDrawable) image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, w, h, false);
        return new BitmapDrawable(Utils.getContext().getResources(), bitmapResized);
    }

    public static Drawable resize(int image, int w, int h) {
        return resize(Utils.getContext().getResources().getDrawable(image), w, h);
    }
}
