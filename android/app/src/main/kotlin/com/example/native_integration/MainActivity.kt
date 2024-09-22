package com.example.native_integration

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.os.BatteryManager
import android.content.Context

class MainActivity : FlutterActivity() {
    init {
        System.loadLibrary("hello_world")
    }

    private val CHANNEL = "com.example.native/methods"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        MethodChannel(
                flutterEngine.dartExecutor.binaryMessenger,
                CHANNEL
        ).setMethodCallHandler { call, result ->
            when (call.method) {
                "getBatteryLevel" -> {
                    val batteryLevel = getBatteryLevel()
                    if (batteryLevel != null) {
                        result.success(batteryLevel)
                    } else {
                        result.error("UNAVAILABLE", "Battery level not available.", null)
                    }
                }
                "myNativeFunction" -> {
                    val nativeResult = myNativeFunction()
                    result.success(nativeResult)
                }
                else -> result.notImplemented()
            }
        }
    }

    private fun getBatteryLevel(): Int? {
        val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }
    external fun myNativeFunction(): String
}
