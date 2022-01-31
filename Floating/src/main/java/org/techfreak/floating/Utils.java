package org.techfreak.floating;
import android.graphics.drawable.Drawable;
import android.annotation.NonNull;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;

public class Utils {
    
    public static final String TAG = Utils.class.getSimpleName();

    public static Drawable tintIcon(@NonNull Context context,int resId, int tintColor) {
        Drawable drawable = context.getDrawable(resId);
        return tintIcon(drawable,tintColor);
    }

    public static Drawable tintIcon(@NonNull Drawable drawable, int tintColor) {
        drawable.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    public static int getColor(@NonNull Context context,@NonNull int resId){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? context.getColor(resId) : context.getResources().getColor(resId);
    }

    public static Drawable getDrawable(@NonNull Context context,@NonNull int resId){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? context.getDrawable(resId) : context.getResources().getDrawable(resId);
    }

  /**  public static float getPixelValue(@NonNull Context context,float dip) {
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, metrics);
    }
**/
    @Override
    public String toString() {
        return TAG;
    }

    
    
}
