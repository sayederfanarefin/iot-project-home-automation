/*
** This sample Arduino sketch uploads telemetry data to Azure Mobile Services
** See the full article here: http://hypernephelist.com/2014/07/11/arduino-uno-azure-mobile-services.html
**
** Thomas Conté @tomconte
*/
 
#include <SPI.h>
#include <Ethernet.h>
 
// Ethernet shield MAC address (sticker in the back)
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
 IPAddress ip(192, 168, 1, 5);
// Azure Mobile Service address
// You can find this in your service dashboard
const char *server = "erfanhome.azure-mobile.net";//"arduinouno.azure-mobile.net";
 
// Azure Mobile Service table name
// The name of the table you created
const char *table_name = "erfanroomservice";
 
// Azure Mobile Service Application Key
// You can find this key in the 'Manage Keys' menu on the dashboard
const char *ams_key = "rctFRmkNiKsYDEltDhlnLxMWXzVWHR12";
String testreturn = "";
  String testreturn2 = ""; 
  String finalresult = "";
  
  bool switch1 = false;
  bool switch2 = false;
  bool switch3 = false;
  bool switch4 = false;
  bool switch5 = false;
  
   bool newSwitch1 = false;
  bool newSwitch2 = false;
  bool newSwitch3 = false;
  bool newSwitch4 = false;
  bool newSwitch5 = false;
  
   int swt1 =  2;
   int swt2 =  3;
   int swt3 =  6;
   int swt4 =  7;
   int swt5 =  8;
  
EthernetClient client(8080);
 
char buffer[64];
 
/*
** Send an HTTP POST request to the Azure Mobile Service data API
*/
 
void send_request(boolean value)
{
  Serial.println("\nconnecting...");
 
  if (client.connect(server, 80)) {
 
    Serial.print("sending ");
    Serial.println(value);
 
    // POST URI
    sprintf(buffer, "GET /tables/%s HTTP/1.1", table_name);
    client.println(buffer);
    sprintf(buffer, "Host: %s", server);
    client.println(buffer);
    sprintf(buffer, "X-ZUMO-APPLICATION: %s", ams_key);
    client.println(buffer);
    client.println("Content-Type: application/json");
 
    // POST body
    //sprintf(buffer, "{\"switch1\": %d}", value);
 
    // Content length
    client.print("Content-Length: ");
    client.println(strlen(buffer));
 
    // End of headers
    client.println();
 
    // Request body
    client.println(buffer);
    
  } else {
    Serial.println("connection failed");
  }
}
 
/*
** Wait for response
*/
 
void wait_response()
{
  while (!client.available()) {
    if (!client.connected()) {
      return;
    }
  }
}
 
/*
** Read the response and dump to serial
*/
 
void read_response()
{
  bool record = false;
  while (client.available()) {
    char c = client.read();
    if (c == '['){
      record = true;
    }else if(c == ']'){
      record = false;
    }else{
    }
      if (record){
        testreturn = testreturn + c;
  }
  
}
}
 
/*
** Close the connection
*/
 
void end_request()
{
  Serial.println(testreturn);
  finalresult = testreturn;
  testreturn = "";
  Serial.println("Stopping connection...");
  client.stop();
}
 
/*
** Arduino Setup
*/
 
void setup()
{
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect.
  }
 
  Serial.println("ethernet");
if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    // no point in carrying on, so do nothing forevermore:
    // try to congifure using IP address instead of DHCP:
    Ethernet.begin(mac, ip);
  }
  delay(1000);
  Serial.println("initializing pins");
  pinMode(swt1, OUTPUT);
  pinMode(swt2, OUTPUT);
  pinMode(swt3, OUTPUT);
  pinMode(swt4, OUTPUT);
  pinMode(swt5, OUTPUT);
  
  digitalWrite(swt1, HIGH); 
  delay(100);
  digitalWrite(swt1, LOW); 
  delay(100);
  
  digitalWrite(swt2, HIGH); 
  delay(100);
  digitalWrite(swt2, LOW); 
  delay(100);
  
  digitalWrite(swt3, HIGH); 
  delay(1000);
  digitalWrite(swt3, LOW); 
  delay(100);
  
  digitalWrite(swt4, HIGH); 
  delay(1000);
  digitalWrite(swt4, LOW); 
  delay(100);
}
 
/*
** Arduino Loop
*/
 
void loop()
{
 
  boolean aboo = true;
  send_request(aboo);
  wait_response();
  read_response();
  end_request();
 disectResult();
 updateRelay();
  delay(1000);
}

void disectResult(){
  
  int i = finalresult.indexOf(',');
  int j = finalresult.lastIndexOf('}');
  String temp = finalresult.substring(i+1 ,j);
  Serial.println(temp);
  
  int ii = temp.indexOf(',');
  String sw1 = temp.substring(0, ii);
  if(sw1.substring(10) == "true"){
      newSwitch1 = true;
  }else{
    newSwitch1 = false;
  }
  
  int jj = temp.indexOf(',', ii+1);
  String sw2 = temp.substring(ii+1, jj);
  if(sw2.substring(10) == "true"){
      newSwitch2 = true;
  }else{
    newSwitch2 = false;
  }
  
  
  ii = temp.indexOf(',', jj+1);
  
  String sw3 = temp.substring(jj+1, ii);
  jj = temp.indexOf(',', ii+1);
  if(sw3.substring(10) == "true"){
      newSwitch3 = true;
  }else{
    newSwitch3 = false;
  }
  
  String sw4 = temp.substring(ii+1, jj);
  if(sw4.substring(10) == "true"){
      newSwitch4 = true;
  }else{
    newSwitch4 = false;
  }
  String sw5 = temp.substring(jj+1);
  if(sw5.substring(10) == "true"){
      newSwitch5 = true;
  }else{
    newSwitch5 = false;
  }
 
 Serial.println(newSwitch1);
 Serial.println(newSwitch2);
 Serial.println(newSwitch3);
 Serial.println(newSwitch4);
 Serial.println(newSwitch5);
}
void updateRelay(){
  if(newSwitch1 != switch1){
    switch1 = newSwitch1;
    if(newSwitch1){
      digitalWrite(swt1, HIGH); 
      Serial.println("swt1 high");
    }else{
      digitalWrite(swt1, LOW); 
      Serial.println("swt1 low");
    }
  }
    
    if(switch2 != newSwitch2){
      switch2 = newSwitch2;
      if(newSwitch2){
      digitalWrite(swt2, HIGH); 
     Serial.println("swt2 high");
    }else{
      digitalWrite(swt2, LOW);
    Serial.println("swt2 low");
    }
    }
    if(newSwitch3 != switch3){
      switch3 = newSwitch3;
    if(newSwitch3){
      digitalWrite(swt3, HIGH); 
     Serial.println("swt3 high");
    }else{
      digitalWrite(swt3, LOW); 
     Serial.println("swt3 low");
    }
}
if(newSwitch4 != switch4){
  switch4 = newSwitch4;
    if(newSwitch4){
        digitalWrite(swt4, HIGH);
     Serial.println("swt4 high");
    }else{
      digitalWrite(swt4, LOW); 
    Serial.println("swt4 low");
    }
}
if(newSwitch5 != switch5){
  switch5 = newSwitch5;
    if(newSwitch5){
      digitalWrite(swt5, HIGH); 
     Serial.println("swt5 high");
    }else{
      digitalWrite(swt5, LOW);
     Serial.println("swt5 low");
    }
}
}
