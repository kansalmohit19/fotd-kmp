package com.indemand.fotd.notification

import com.google.firebase.messaging.FirebaseMessaging


internal class FirebaseMessagingManager {

    fun getToken(onResult: (String?) -> Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(task.result)
            } else {
                onResult(null)
            }
        }
    }
}