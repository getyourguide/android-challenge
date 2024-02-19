package com.getyourguide.interview.base.presetation

import android.content.Context
import com.getyourguide.interview.R
import com.getyourguide.interview.base.data.ConnectionException

fun Context.getErrorMessage(e: Exception): String {
    return if (e is ConnectionException)
        getString(R.string.msg_connection_error)
    else
         getString(R.string.msg_unknown_error)
}
