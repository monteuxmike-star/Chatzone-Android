
package com.chatzone.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var chatLayout: LinearLayout
    private lateinit var messageInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.rgb(245, 247, 250))
        }

        val header = TextView(this).apply {
            text = "ChatZone"
            textSize = 26f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(35, 100, 220))
            gravity = Gravity.CENTER_VERTICAL
            setPadding(24, 24, 24, 24)
        }
        root.addView(header)

        val scrollView = ScrollView(this)
        chatLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }
        scrollView.addView(chatLayout)

        root.addView(
            scrollView,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val inputRow = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(8, 8, 8, 8)
        }

        messageInput = EditText(this).apply {
            hint = "Type a message..."
            textSize = 16f
            setSingleLine(true)
        }

        inputRow.addView(
            messageInput,
            LinearLayout.LayoutParams(0, 56, 1f)
        )

        val sendButton = Button(this).apply {
            text = "Send"
            setOnClickListener {
                sendMessage()
            }
        }
        inputRow.addView(sendButton)

        root.addView(inputRow)
        setContentView(root)

        addMessage("ChatZone", "Welcome! Your chat starts here.")
    }

    private fun sendMessage() {
        val message = messageInput.text.toString().trim()

        if (message.isEmpty()) {
            Toast.makeText(
                this,
                "Type a message first",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        addMessage("You", message)
        messageInput.text.clear()
    }

    private fun addMessage(sender: String, message: String) {
        val textView = TextView(this).apply {
            text = "$sender: $message"
            textSize = 17f
            setTextColor(Color.rgb(30, 30, 30))
            setPadding(12, 12, 12, 12)
        }

        chatLayout.addView(
            textView,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
    }
}
