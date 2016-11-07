package com.philipgreen.dmwizard.utils;

import android.support.v4.app.Fragment;

/**
 * Created by pgreen on 11/6/16.
 */

public class UnitConverter {

    public static int convertPixelsToDP(Fragment fragment, int dp) {
        float scale = fragment.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
