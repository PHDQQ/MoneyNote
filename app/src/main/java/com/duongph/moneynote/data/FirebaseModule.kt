package com.duongph.moneynote.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

object FirebaseModule {


    fun provideFireStoreInstance(): FirebaseFirestore {
        val sett = FirebaseFirestoreSettings.Builder()
            .build()
        return FirebaseFirestore.getInstance().apply {
            firestoreSettings = sett
        }
    }

}