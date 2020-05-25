package com.app.farmfresh

import androidx.multidex.MultiDexApplication
import com.app.farmfresh.di.appcomponent.ApplicationComponent
import com.app.farmfresh.di.appcomponent.DaggerApplicationComponent

class FarmFreshApplication : MultiDexApplication() {

    private lateinit var applicationComponent: ApplicationComponent

    fun initialiseDagger() : ApplicationComponent
    {
       return DaggerApplicationComponent.create()
    }

}