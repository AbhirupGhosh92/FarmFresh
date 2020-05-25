package com.app.farmfresh.di.appcomponent

import android.app.Activity
import com.app.farmfresh.network.NetworkClient
import com.app.farmfresh.repo.Repository
import com.app.farmfresh.viewmodels.master.AreaFragmentViewModel
import dagger.Component

@Component(modules = [Repository::class,NetworkClient::class,AreaFragmentViewModel::class])
interface ApplicationComponent {

    fun injectActivity(activity: Activity)
}