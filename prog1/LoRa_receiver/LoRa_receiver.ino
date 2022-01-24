#include <SPI.h>
#include <LoRa.h>
#include <stdio.h>
#include <stdlib.h>

int messageComp;
//set RGB pins
int LEDR = 9;
int LEDG = 10;
int LEDB = 11;
 
void setup() {
 Serial.begin(9600);

 while (!Serial);

 pinMode(LEDR, OUTPUT); // Set LED pins as output
 pinMode(LEDG, OUTPUT);
 pinMode(LEDB, OUTPUT);
 
 if (!LoRa.begin(868E6)) {
    Serial.println("Starting LoRa failed!");
    while(1);
 }

 Serial.println("LoRa is looking for packets...");
 
}

void loop() {
 
 // see if a packet was received
 int packetSize = LoRa.parsePacket();//don't change
 
 if (packetSize) {
   
   // if a packet size is defined, a packet was received
   Serial.println("");
   Serial.println("Received packet!\nMoisture value: ");
   
   String message = "";

   // read the packet
   while (LoRa.available()) {
      message += (char)LoRa.read();
      
   }

  
   Serial.println(message);
   // print the Packet and RSSI
   Serial.print("RSSI: ");
   Serial.println(LoRa.packetRssi());
   Serial.println("-------------------------------------------------");

   int value = 0;
   value = message.toInt();
   
   
   //light up RGB according to moisture level
  if(value<=600){
      //red = Water your orchid
      setColor(255, 0, 0); 
      Serial.println("test 1");
  }
  else if( (value>601) && (value<=680) ){
      // orange = The soil is getting dry
      setColor(255, 40, 120);
      Serial.println("test 2");
  }
  else if( (value>681) && (value<=750) ){
      // yellow = The soil is pretty moist
      setColor(240, 110, 70);
      Serial.println("test 3");
  }
  
  else {
      // green = The soil is moist
      setColor(0, 255, 0);
      Serial.println("test 4");
  }
  Serial.println("end test");
 }
}

void setColor(int red, int green, int blue)
{
  red = 255 - red;
  green = 255 - green;
  blue = 255 - blue;
  analogWrite(LEDR, red);
  analogWrite(LEDG, green);
  analogWrite(LEDB, blue);  
}
