package bbu.com.smartoffice.utils;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

/**
 * Created by G on 2016/11/17 0017.
 */

public class CalculateColorUtil {

    /**
     * 计算从startColor过度到endColor过程中百分比为franch时的颜色值
     *
     * @param startColor 起始颜色 int类型
     * @param endColor   结束颜色 int类型
     * @param franch     franch 百分比0.5
     * @return 返回int格式的color
     */
    public static int calculateColor(int startColor, int endColor, float franch) {
        String strStartColor = "#" + Integer.toHexString(startColor);
        String strEndColor = "#" + Integer.toHexString(endColor);
        return Color.parseColor(calculateColor(strStartColor, strEndColor, franch));
    }

    /**
     * 计算从startColor过度到endColor过程中百分比为franch时的颜色值
     *
     * @param startColor 起始颜色 （格式#FFFFFFFF）
     * @param endColor   结束颜色 （格式#FFFFFFFF）
     * @param franch     百分比0.5
     * @return 返回String格式的color（格式#FFFFFFFF）
     */
    public static String calculateColor(String startColor, String endColor, float franch) {

        int startAlpha = Integer.parseInt(startColor.substring(1, 3), 16);
        int startRed = Integer.parseInt(startColor.substring(3, 5), 16);
        int startGreen = Integer.parseInt(startColor.substring(5, 7), 16);
        int startBlue = Integer.parseInt(startColor.substring(7), 16);

        int endAlpha = Integer.parseInt(endColor.substring(1, 3), 16);
        int endRed = Integer.parseInt(endColor.substring(3, 5), 16);
        int endGreen = Integer.parseInt(endColor.substring(5, 7), 16);
        int endBlue = Integer.parseInt(endColor.substring(7), 16);

        int currentAlpha = (int) ((endAlpha - startAlpha) * franch + startAlpha);
        int currentRed = (int) ((endRed - startRed) * franch + startRed);
        int currentGreen = (int) ((endGreen - startGreen) * franch + startGreen);
        int currentBlue = (int) ((endBlue - startBlue) * franch + startBlue);

        return "#" + getHexString(currentAlpha) + getHexString(currentRed)
                + getHexString(currentGreen) + getHexString(currentBlue);

    }

    /**
     * 将10进制颜色值转换成16进制。
     */
    public static String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }

    /**
     * 往startColor方向渐变颜色值
     *
     * @param startColor
     * @param endColor
     * @param franch
     * @param view
     */
    public static float changeViewColorToStartDirect(String startColor, String endColor, float franch, View view) throws Exception {
        franch = franchLL(franch);
        String color = CalculateColorUtil.calculateColor(startColor, endColor, franch);
        int colorInt = Color.parseColor(color);
        Log.d("scroll", "color= " + color + " colorInt=" + colorInt + " franch=" + franch);
        view.setBackgroundColor(Color.parseColor(color));
        return franch;
    }

    /**
     * 往endColor方向渐变颜色值
     *
     * @param startColor
     * @param endColor
     * @param franch
     * @param view
     */
    public static float changeViewColorToEndDirect(String startColor, String endColor, float franch, View view) throws Exception {
        franch = franchPP(franch);
        String color = CalculateColorUtil.calculateColor(startColor, endColor, franch);

        int colorInt = Color.parseColor(color);
        Log.d("scroll", "color= " + color + " colorInt=" + colorInt + " franch=" + franch);
        view.setBackgroundColor(Color.parseColor(color));

        return franch;
    }

    /**
     * franch ++ 0.1
     *
     * @param franch
     * @return
     */
    private static float franchPP(float franch) {
        return franch += 0.1;
    }

    /**
     * franch --0.1
     *
     * @param franch
     * @return
     */
    private static float franchLL(float franch) {
        return franch -= 0.1;
    }
}
