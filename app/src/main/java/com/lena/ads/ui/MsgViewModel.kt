package com.lena.ads.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lena.ads.R
import com.lena.ads.data.Author
import com.lena.ads.data.Message

class MsgViewModel(application: Application) : AndroidViewModel(application) {

    private var authors : Array<Author> = arrayOf(
        Author(0, application.getDrawable(R.drawable.ic_sentiment_satisfied_black_24dp)!!),
        Author(1, application.getDrawable(R.drawable.ic_pool_black_24dp)!!)
    )

    var messages : MutableList<Message> = arrayListOf()

    init {

        val timestamp = 1462233517000L

        loop@ for (i in 0..14) {
            when {
                i % 2 == 0 -> {
                    messages.add(
                        Message(
                            i,
                            application.resources.getString(R.string.tv_message) + " $i",
                            timestamp + i,
                            authors[0]
                        )
                    )
                    continue@loop
                }
                i != 3 -> messages.add(
                    Message(
                        i,
                        application.resources.getString(R.string.tv_message) + " $i",
                        timestamp + i,
                        authors[1]
                    )
                )
                else -> messages.add(Message(i, "", 0, Author(-1, null)))
            }
        }
    }
}