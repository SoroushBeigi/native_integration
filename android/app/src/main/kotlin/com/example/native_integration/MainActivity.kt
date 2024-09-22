package com.example.native_integration

import io.flutter.embedding.android.FlutterActivity

class MainActivity : FlutterActivity() {
    private val CHANNEL = "com.example.native/methods"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        MethodChannel(
                flutterEngine.dartExecutor.binaryMessenger,
                CHANNEL
        ).setMethodCallHandler { call, result ->
            if (call.method == "getBatteryLevel") {
                val batteryLevel = getBatteryLevel()
                if (batteryLevel != null) {
                    result.success(batteryLevel)
                } else {
                    result.error(
                            "UNAVAILABLE",
                            "Battery level not available.",
                            null
                    )
                }
            } else {
                result.notImplemented()
            }
        }
    }

    private fun getBatteryLevel(): Int? {
        val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }
}
