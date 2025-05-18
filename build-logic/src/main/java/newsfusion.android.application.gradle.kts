import com.jdm.app.configureHiltAndroid
import com.jdm.app.configureKotest
import com.jdm.app.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureKotest()
