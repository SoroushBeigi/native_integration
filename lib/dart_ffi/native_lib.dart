import 'package:flutter/services.dart';

class NativeLib {
  static const platform = MethodChannel('com.example.yourapp/native');

  Future<String> getNativeString() async {
    try {
      final result = await platform.invokeMethod('myNativeFunction');
      return result;
    } on PlatformException catch (e) {
      return "Failed to get native string: '${e.message}'.";
    }
  }
}
