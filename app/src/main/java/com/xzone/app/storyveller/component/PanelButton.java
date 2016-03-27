package com.xzone.app.storyveller.component;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.xzone.app.storyveller.R;

/**
 * Created by arysuryawan on 3/20/16.
 */
public class PanelButton {
    public static boolean showPanel(View a, int element, boolean toShow){
        View function = (View) a.findViewById(element);
        Animation fadeout = AnimationUtils.loadAnimation(a.getContext(), R.anim.fadeout);
        function.setAnimation(fadeout);

        // buttons panel
        Animation bottomUp = AnimationUtils.loadAnimation(a.getContext(), R.anim.bottom_up);
        function.startAnimation(bottomUp);

        if(toShow){
            function.setVisibility(View.VISIBLE);
            return true;
        }else{
            function.setVisibility(View.GONE);
            return false;
        }
    }
}
