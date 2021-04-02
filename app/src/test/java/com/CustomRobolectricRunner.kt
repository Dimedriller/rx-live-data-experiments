package com

import org.junit.runners.model.FrameworkMethod
import org.robolectric.RobolectricTestRunner
import org.robolectric.internal.bytecode.InstrumentationConfiguration
import org.robolectric.util.inject.Injector
import javax.annotation.Nonnull


class CustomRobolectricRunner : RobolectricTestRunner {
    constructor(testClass: Class<*>?) : super(testClass) {}
    constructor(testClass: Class<*>?, injector: Injector?) : super(testClass, injector) {}

    @Nonnull
    override fun createClassLoaderConfig(method: FrameworkMethod?): InstrumentationConfiguration {
        val config = super.createClassLoaderConfig(method)
        val builder = InstrumentationConfiguration.Builder(config)
        builder.doNotInstrumentPackage("androidx.fragment")
        return builder.build()
    }
}