package com.example.androidauto.car

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

class MainCarScreen(carContext: CarContext) : Screen(carContext) {

    override fun onGetTemplate(): Template {
        val pane = Pane.Builder()
            .addRow(
                Row.Builder()
                    .setTitle("Car App Library")
                    .addText("Android Auto / DHU can show this template.")
                    .build(),
            )
            .build()

        return PaneTemplate.Builder(pane)
            .setTitle("Hello Android Auto")
            .build()
    }
}
