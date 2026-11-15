package com.stevfoundation.chatme

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {
    private val purple = Color.rgb(77, 0, 190)
    private val darkPurple = Color.rgb(35, 0, 92)
    private val gold = Color.rgb(255, 190, 0)
    private lateinit var root: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showWelcome()
    }

    private fun base(title: String) {
        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(22, 26, 22, 20)
            setBackgroundColor(Color.rgb(249, 248, 255))
        }
        val header = TextView(this).apply {
            text = title
            textSize = 27f
            setTextColor(purple)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
            setPadding(8, 12, 8, 22)
        }
        root.addView(header)
        setContentView(root)
    }

    private fun text(value: String, size: Float = 16f): TextView =
        TextView(this).apply {
            text = value
            textSize = size
            setTextColor(Color.rgb(35, 30, 55))
            setPadding(8, 10, 8, 10)
        }

    private fun field(hint: String, password: Boolean = false): EditText =
        EditText(this).apply {
            this.hint = hint
            textSize = 16f
            setSingleLine(true)
            if (password) inputType = 129
            setPadding(14, 8, 14, 8)
            backgroundTintList = android.content.res.ColorStateList.valueOf(purple)
        }

    private fun button(label: String, action: () -> Unit): Button =
        Button(this).apply {
            text = label
            isAllCaps = false
            setTextColor(Color.WHITE)
            setBackgroundColor(purple)
            setOnClickListener { action() }
        }

    private fun showWelcome() {
        base("CHAT me")
        root.setBackgroundColor(darkPurple)
        root.addView(text("Connect  •  Chat  •  Call", 20f).apply {
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
        })
        root.addView(text("Powered by\nSTEV FOUNDATION", 17f).apply {
            gravity = Gravity.CENTER
            setTextColor(gold)
            setPadding(8, 36, 8, 40)
        })
        root.addView(button("Create account") { showRegister() })
        root.addView(button("Sign in") { showLogin() })
        root.addView(text("Trial preview • Internet required for live calls", 13f).apply {
            gravity = Gravity.CENTER
            setTextColor(Color.LTGRAY)
        })
    }

    private fun showRegister() {
        base("Create account")
        root.addView(text("Join the CHAT me community"))
        val phone = field("Phone number")
        val name = field("Full name")
        val email = field("Email address")
        val pass = field("Password", true)
        listOf(phone, name, email, pass).forEach { root.addView(it) }
        root.addView(button("Register") {
            Toast.makeText(this, "Demo only: connect Firebase to enable registration.", Toast.LENGTH_LONG).show()
            showVerify()
        })
        root.addView(button("Back") { showWelcome() })
    }

    private fun showVerify() {
        base("Check your email")
        root.addView(text("A verification link will be sent to your email when the backend is connected.", 17f))
        root.addView(text("After email verification, your account can be submitted to STEV FOUNDATION for approval."))
        root.addView(button("Continue") { showPending() })
        root.addView(button("Back to login") { showLogin() })
    }

    private fun showPending() {
        base("Account pending approval")
        root.addView(text("Your account will be reviewed by STEV FOUNDATION."))
        root.addView(text("Support: stevmgd454@gmail.com"))
        root.addView(button("OK") { showLogin() })
    }

    private fun showLogin() {
        base("Sign in")
        val email = field("Email address")
        val pass = field("Password", true)
        root.addView(email); root.addView(pass)
        root.addView(button("Sign in") { showHome() })
        root.addView(button("Create account") { showRegister() })
    }

    private fun showHome() {
        base("CHAT me")
        root.addView(text("Chats     Contacts     Calls", 18f).apply {
            setTextColor(purple); typeface = Typeface.DEFAULT_BOLD
        })
        val chats = listOf("Grace       Online       Hello! 👋",
            "David       Great idea 👍",
            "Sarah       Call you later",
            "Michael     Thanks!",
            "Family Group       Photo")
        chats.forEach { item ->
            val row = button(item) { showConversation(item.substringBefore(" ")) }
            row.gravity = Gravity.START
            root.addView(row)
        }
        root.addView(button("Open a conversation") { showConversation("Grace") })
        root.addView(button("Voice call") { showCall("Voice call") })
        root.addView(button("Video call") { showCall("Video call") })
        root.addView(button("Settings") { showSettings() })
        root.addView(text("Offline preview: saved screens only. Messages and calls need backend/internet.", 13f))
    }

    private fun showConversation(person: String) {
        base(person)
        root.addView(text("Hello! 👋", 18f))
        root.addView(text("How are you?", 18f).apply { gravity = Gravity.END; setTextColor(purple) })
        root.addView(text("I'm good, thanks!", 18f))
        val message = field("Type a message…")
        root.addView(message)
        root.addView(button("Send (demo)") {
            Toast.makeText(this, "Messaging will work after backend setup.", Toast.LENGTH_SHORT).show()
        })
        root.addView(button("Back to chats") { showHome() })
    }

    private fun showCall(kind: String) {
        base(kind)
        root.addView(text("CHAT me $kind screen", 22f).apply { gravity = Gravity.CENTER })
        root.addView(text("Live calling is a planned feature and requires internet plus call-service integration.", 16f))
        root.addView(button("End call / Back") { showHome() })
    }

    private fun showSettings() {
        base("Settings")
        root.addView(text("STEV FOUNDATION"))
        listOf("Account settings", "Notifications", "Privacy & security", "Calls & messages",
            "About CHAT me", "Support: stevmgd454@gmail.com").forEach { root.addView(text("•  $it")) }
        root.addView(button("Log out") { showWelcome() })
    }
}
