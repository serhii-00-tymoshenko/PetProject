package com.example.petproject

import android.app.Application
import com.google.android.material.color.DynamicColors

class PetProjectApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}