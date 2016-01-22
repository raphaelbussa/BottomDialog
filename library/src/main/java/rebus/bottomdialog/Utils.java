package rebus.bottomdialog;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.TypedValue;

/**
 * Created by raphaelbussa on 19/01/16.
 */
public class Utils {

    public static ColorStateList colorStateListIcon(Context context) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled},
                new int[]{-android.R.attr.state_enabled},
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_pressed}
        };
        int[] colors = new int[]{
                getTextColorSecondary(context),
                getPrimaryColor(context),
                getPrimaryColor(context),
                getPrimaryColor(context)};
        return new ColorStateList(states, colors);
    }

    public static ColorStateList colorStateListText(Context context) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled},
                new int[]{-android.R.attr.state_enabled},
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_pressed}
        };
        int[] colors = new int[]{
                getTextColorPrimary(context),
                getPrimaryColor(context),
                getPrimaryColor(context),
                getPrimaryColor(context)};
        return new ColorStateList(states, colors);
    }

    public static int getTextColorPrimary(Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray typedArray = context.obtainStyledAttributes(typedValue.data, new int[] { 16842806 });
        int accent = typedArray.getColor(0, 0);
        typedArray.recycle();
        return accent;
    }

    public static int getTextColorSecondary(Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray typedArray = context.obtainStyledAttributes(typedValue.data, new int[] { 16842808 });
        int accent = typedArray.getColor(0, 0);
        typedArray.recycle();
        return accent;
    }

    public static int getPrimaryColor(Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray typedArray = context.obtainStyledAttributes(typedValue.data, new int[] { 16843827 });
        int accent = typedArray.getColor(0, 0);
        typedArray.recycle();
        return accent;
    }


    public static int getAccentColor(Context context) {
        TypedValue typedValue = new TypedValue();
        TypedArray typedArray = context.obtainStyledAttributes(typedValue.data, new int[] { 16843829 });
        int accent = typedArray.getColor(0, 0);
        typedArray.recycle();
        return accent;
    }

}
