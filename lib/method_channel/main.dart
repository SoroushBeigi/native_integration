import 'package:flutter/material.dart';

import 'battery_level.dart';


void main()async {
  WidgetsFlutterBinding.ensureInitialized();
  final batteryLevel = await BatteryLevel.getBatteryLevel();
  print("Battery Level: $batteryLevel%");

}


