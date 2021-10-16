package com.cont96roller.library.viewmodel

import android.util.Log

object LogMsg {
    const val POSTTAG = "HT_LAUNCHER"
    private const val TAG = "HT"
    const val VERBOSE = 1
    const val DEBUG = 2
    const val INFO = 3
    const val WARN = 4
    const val ERROR = 5
    const val NO_LOG = 6
    private var MSG_LEVEL = WARN
    private var m_bProguardStyle = false
    private const val m_bUseOneTag = false
    fun setLogMsgStyle(nLogMsgLevel: Int, bProguardMsgStyle: Boolean) {
        MSG_LEVEL = nLogMsgLevel
        m_bProguardStyle = bProguardMsgStyle
    }

    /**
     * Do not send a log message.
     */
    fun x(tag: String?, msg: String?) {
        return
    }

    /**
     * Send a [.VERBOSE] log message.
     *
     * @param msg The message you would like logged.
     */
    fun v(tag: String, msg: String) {
        if (VERBOSE < MSG_LEVEL) return
        val ste = Throwable().stackTrace
        val totalMsg = makeMsg(msg, ste)
        if (m_bUseOneTag) {
            Log.v(TAG, totalMsg)
        } else {
            Log.v(tag + POSTTAG, totalMsg)
        }
    }

    /**
     * Send a [.DEBUG] log message.
     *
     * @param msg The message you would like logged.
     */
    fun d(tag: String, msg: String) {
        if (DEBUG < MSG_LEVEL) return
        val ste = Throwable().stackTrace
        val totalMsg = makeMsg(msg, ste)
        if (m_bUseOneTag) {
            Log.d(TAG, totalMsg)
        } else {
            Log.d(tag + POSTTAG, totalMsg)
        }
    }

    /**
     * Send a [.INFO] log message.
     *
     * @param msg The message you would like logged.
     */
    fun i(tag: String, msg: String) {
        if (INFO < MSG_LEVEL) return
        val ste = Throwable().stackTrace
        val totalMsg = makeMsg(msg, ste)
        if (m_bUseOneTag) {
            Log.i(TAG, totalMsg)
        } else {
            Log.i(tag + POSTTAG, totalMsg)
        }
    }

    /**
     * Send a [.WARN] log message.
     *
     * @param msg The message you would like logged.
     */
    fun w(tag: String, msg: String) {
        if (WARN < MSG_LEVEL) return
        val ste = Throwable().stackTrace
        val totalMsg = makeMsg(msg, ste)
        if (m_bUseOneTag) {
            Log.w(TAG, totalMsg)
        } else {
            Log.w(tag + POSTTAG, totalMsg)
        }
    }

    /**
     * Send a [.ERROR] log message.
     *
     * @param msg The message you would like logged.
     */
    fun e(tag: String, msg: String) {
        if (ERROR < MSG_LEVEL) return
        val ste = Throwable().stackTrace
        val totalMsg = makeMsg(msg, ste)
        if (m_bUseOneTag) {
            Log.e(TAG, totalMsg)
        } else {
            Log.e(tag + POSTTAG, totalMsg)
        }
    }

    private fun makeMsg(msg: String, ste: Array<StackTraceElement>): String {
        var strMsg = ""
        if (m_bProguardStyle == true) {
            strMsg += String.format(
                " at %s.%s(%s:%d)\n",
                ste[1].className,
                ste[1].methodName,
                ste[1].fileName,
                ste[1].lineNumber
            )
        }
        strMsg += String.format(
            "      [%s:%d]   [%s()]   [%s]",
            ste[1].fileName,
            ste[1].lineNumber,
            ste[1].methodName,
            msg
        )
        return strMsg
    }
}