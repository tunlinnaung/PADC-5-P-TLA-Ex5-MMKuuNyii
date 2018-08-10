package xyz.tunlinaung.mmkuunyii

import android.app.Application
import xyz.tunlinaung.mmkuunyii.data.models.KuuNyiiModel

public class KuuNyiiApp : Application() {

    companion object {
        const val TAG = "ZeeWaKa"
    }

    override fun onCreate() {
        super.onCreate()
        KuuNyiiModel.initModel(applicationContext)
    }

}