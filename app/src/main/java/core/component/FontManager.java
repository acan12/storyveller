package core.component;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by arysuryawan on 8/13/16.
 */
public class FontManager {

    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}