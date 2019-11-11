# IoT: Home Automation
## Microsoft Azure + Android +Arduino

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/dwyl/esta/issues)

More details can be found on: http://hypernephelist.com/2014/07/11/arduino-uno-azure-mobile-services.html

In 2019, when I reviewd this code, Azure stopped providing the cloud service that was used here.

If you want to make something similar, you can do so by using other services now a days. 

Components:
1. [Arduino Uno](https://www.amazon.com/Arduino-Development-Microcontroller-ATmega328-ATMEGA16U2/dp/B07V9VGXFS/ref=sr_1_8?keywords=arduino+uno&qid=1573430486&sr=8-8)
2. [Arduino Ethernet shield](https://www.amazon.com/SunFounder-Ethernet-Shield-W5100-Arduino/dp/B00HG82V1A/ref=sxin_2_ac_d_rm?ac_md=0-0-YXJkdWlubyBldGhlcm5ldCBzaGllbGQ%3D-ac_d_rm&keywords=arduino+ethernet+shield&pd_rd_i=B00HG82V1A&pd_rd_r=678a0531-d2f8-42b2-9854-71eee4433457&pd_rd_w=Kb3Mj&pd_rd_wg=3sqZV&pf_rd_p=39892eb5-25ed-41d8-aff1-b659c9b73760&pf_rd_r=ZY76G48AD81J5FYM9BH9&psc=1&qid=1573430568)
3. [Relays](https://www.amazon.com/Youngneer-Raspberry-Arduino-Channel-Opto-Isolated/dp/B07M88JRFY/ref=sr_1_6?keywords=relay&qid=1573430472&sr=8-6)

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
