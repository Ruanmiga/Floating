package org.techfreak.floating;

/**
 * Creado por: Antonio Mirabal Garcia(TechFreak)
 * Creado El: 14/01/22
 */

import android.widget.Toast;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.LayoutInflater;

import org.techfreak.floating.R;
import android.widget.TextView;
import android.widget.ImageView;
import android.util.TypedValue;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.os.Build;
import android.content.res.Configuration;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.GradientDrawable;
import android.annotation.NonNull;

public class Floating extends Toast{

    private static final String TAG = Floating.class.getSimpleName();

    private static final Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    private static Typeface currentTypeface = LOADED_TOAST_TYPEFACE;
    private static int textSize = 16; // en SP

    public Floating(Context context){
        super(context);
    }

    public static Floating make(@NonNull Context context,@NonNull CharSequence text,@NonNull int duration){
        return make(context,text,null,duration);
    }
    public static Floating make(@NonNull Context context,@NonNull CharSequence text,@NonNull Drawable icon,@NonNull int duration){
        return supportDarkTheme(context,text,icon,duration);
    }
    public static Floating makeSuccess(@NonNull Context context,@NonNull CharSequence text,@NonNull int duration){
        return makeSuccess(context,text,false,duration);
    }
    public static Floating makeSuccess(@NonNull Context context,@NonNull CharSequence text,@NonNull boolean withIcon,@NonNull int duration){
        if(withIcon)return makeText(context,text,Utils.getColor(context,R.color.defaultTextColor),Utils.getDrawable(context,R.drawable.ic_check_24dp),duration,Utils.getColor(context,R.color.green),true);
        return makeText(context,text,Utils.getColor(context,R.color.defaultTextColor),null,duration,Utils.getColor(context,R.color.green),false);
    }

    public static Floating makeError(@NonNull Context context,@NonNull CharSequence text,@NonNull int duration){
        return makeError(context,text,false,duration);
    }
    public static Floating makeError(@NonNull Context context,@NonNull CharSequence text,@NonNull boolean withIcon,@NonNull int duration){
        if(withIcon)return makeText(context,text,Utils.getColor(context,R.color.defaultTextColor),Utils.getDrawable(context,R.drawable.ic_error_24dp),duration,Utils.getColor(context,R.color.red),true);
        return makeText(context,text,Utils.getColor(context,R.color.defaultTextColor),null,duration,Utils.getColor(context,R.color.red),false);
    }

    public static Floating makeInfo(@NonNull Context context,@NonNull CharSequence text,@NonNull int duration){
        return makeInfo(context,text,false,duration);
    }

    public static Floating makeInfo(@NonNull Context context,@NonNull CharSequence text,@NonNull boolean withIcon,@NonNull int duration){
        if(withIcon)return makeText(context,text,Utils.getColor(context,R.color.defaultTextColor),Utils.getDrawable(context,R.drawable.ic_info_24dp),duration,Utils.getColor(context,R.color.blue),true);
        return makeText(context,text,Utils.getColor(context,R.color.defaultTextColor),null,duration,Utils.getColor(context,R.color.blue),false);
    }

    public static Floating makeWarning(@NonNull Context context,@NonNull CharSequence text,@NonNull int duration){
        return makeWarning(context,text,false,duration);
    }
    public static Floating makeWarning(@NonNull Context context,@NonNull CharSequence text,@NonNull boolean withIcon,@NonNull int duration){
        if(withIcon)return makeText(context,text,Utils.getColor(context,R.color.defaultTextColor),Utils.getDrawable(context,R.drawable.ic_clear_24dp),duration,Utils.getColor(context,R.color.yellow),true);
        return makeText(context,text,Utils.getColor(context,R.color.defaultTextColor),null,duration,Utils.getColor(context,R.color.yellow),false);
    }

    private static Floating supportDarkTheme(@NonNull Context context,@NonNull CharSequence text,@NonNull Drawable icon,@NonNull int duration){
        if (Build.VERSION.SDK_INT >= 29) {
            int uiMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (uiMode == Configuration.UI_MODE_NIGHT_NO) {
                return makeLight(context, text, icon, duration);
            }
            return makeDark(context, text, icon, duration);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                return makeLight(context, text, icon, duration);
            } else {
                return makeDark(context, text, icon, duration);
            }
        }
    }
    public static Floating makeLight(@NonNull Context context,@NonNull CharSequence text,@NonNull int duration){
        return makeLight(context,text,null,duration);
    }

    public static Floating makeDark(@NonNull Context context,@NonNull CharSequence text,@NonNull int duration){
        return makeDark(context,text,null,duration);
    }

    public static Floating makeLight(@NonNull Context context,@NonNull CharSequence text,@NonNull Drawable icon,@NonNull int duration){
        return makeLight(context,text,icon,duration,true);
    }

    public static Floating makeDark(@NonNull Context context,@NonNull CharSequence text,@NonNull Drawable icon,@NonNull int duration){
        return makeDark(context,text,icon,duration,true);
    }

    private static Floating makeLight(@NonNull Context context,@NonNull CharSequence text,@NonNull Drawable icon,@NonNull int duration,@NonNull boolean withIcon){
        return makeText(context,text,Utils.getColor(context,R.color.normalColor),icon,duration,Utils.getColor(context,R.color.defaultTextColor),withIcon);
    }

    private static Floating makeDark(@NonNull Context context,@NonNull CharSequence text,@NonNull Drawable icon,@NonNull int duration,@NonNull boolean withIcon){
        return makeText(context,text,Utils.getColor(context,R.color.defaultTextColor),icon,duration,Utils.getColor(context,R.color.normalColor),withIcon);
    }

    private static Floating makeText(@NonNull Context context, @NonNull CharSequence text,@NonNull int textColor, @NonNull Drawable icon,int duration,@NonNull int bgColor,@NonNull boolean withIcon){
        Floating floating = new Floating(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.floating_layout,null);

        LinearLayout root = v.findViewById(R.id.floating_root);
        TextView textView = v.findViewById(R.id.floating_text);
        ImageView iconImage = v.findViewById(R.id.floating_icon);

		if(withIcon){
			if(icon != null){
				iconImage.setVisibility(View.VISIBLE);
				iconImage.setImageDrawable(icon);
			}else{
				iconImage.setVisibility(View.GONE);
			}
		}

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            GradientDrawable sd = (GradientDrawable)context.getDrawable(R.drawable.floating_background);
            sd.setColor(bgColor);
            root.setBackground(sd);
        }else{
            root.setBackgroundColor(bgColor);
		}


        textView.setText(text);
        textView.setTextColor(textColor);
        textView.setTypeface(currentTypeface);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        floating.setView(v);

        return floating;
    }
    @Override
    public String toString() {
        return TAG;
    }


}
