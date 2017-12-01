package com.mathieukh.tutopsychop13

import android.content.Context

/**
 * Classe permettant de stocker des fonctions utilitaires qui pourraient être adaptés
 * dans plusieurs classes
 */
object Util {

    fun getStatusBarHeight(cxt: Context): Int {
        var result = 0
        val resourceId = cxt.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0)
            result = cxt.resources.getDimensionPixelSize(resourceId)
        return result
    }
}