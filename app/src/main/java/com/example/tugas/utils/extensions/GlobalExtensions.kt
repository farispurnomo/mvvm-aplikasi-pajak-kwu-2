package com.example.tugas.utils.extensions

import android.text.Editable
import android.widget.EditText
import androidx.core.os.postDelayed
import androidx.core.widget.doAfterTextChanged
import java.text.DecimalFormat

fun EditText.debounce(delay: Long, action: (Editable?) -> Unit) {
    doAfterTextChanged { text ->
        var counter = getTag(id) as? Int ?: 0
        handler.removeCallbacksAndMessages(counter)
        handler.postDelayed(delay, ++counter) { action(text) }
        setTag(id, counter)
    }
}
fun Int.formatRibuan(): String {
    return DecimalFormat("###,###,##0").format(this)
}