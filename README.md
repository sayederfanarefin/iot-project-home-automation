# IoT: Home Automation
## Microsoft Azure + Android +Arduino

More details can be found on: http://hypernephelist.com/2014/07/11/arduino-uno-azure-mobile-services.html

In 2019, when I reviewd this code, Azure stopped providing the cloud service that was used here.

If you want to make something similar, you can do so by using other services now a days. 

The methods used here are not efficient and is pretty old school. 

Methods used in this project:
1. Android app store the switch status in a azure database via azure mobile service.
2. arduino constant checking and applying the database status on its pwm pins.
3. PWM pins status reflected on relay control and thus devices connected to the relay could be controlled.

Problems with this approach:
1. Excessive data usage from arduino part.
2. Huge latency.

What you can do now:
1. Create a socket connection between the arduino and the android by redirecting the addtess from any free cloud service.
2. You can also use stream IoT services to directly pass your data to the arduino device.


New project of home automation will be linked soon. If I forget to add, send me an email: erfanjordison@gmail.com

Snapshot of the Android App, Arduino Setup and Azure Dashboard:

![Arduino](https://raw.githubusercontent.com/sayederfanarefin/iot-project-home-automation/master/1.jpg)


![Azure Dashboard](https://raw.githubusercontent.com/sayederfanarefin/iot-project-home-automation/master/2.jpg)


![Android App](https://raw.githubusercontent.com/sayederfanarefin/iot-project-home-automation/master/3.jpg)
