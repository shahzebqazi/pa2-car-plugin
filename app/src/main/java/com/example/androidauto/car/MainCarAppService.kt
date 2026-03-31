package com.example.androidauto.car

import android.content.Intent
import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator

class MainCarAppService : CarAppService() {

    override fun createHostValidator(): HostValidator {
        return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
    }

    override fun onCreateSession(): Session = MainCarSession()
}

class MainCarSession : Session() {

    override fun onCreateScreen(intent: Intent) = MainCarScreen(carContext)
}
