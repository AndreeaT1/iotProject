#include <WiFiNINA.h>
#include <SPI.h>
#include <LoRa.h>
#include "ThingSpeak.h"

float temp = 0;
int light = 0;
int soil = -1;
int soilPin = A2;
int soilPower = 7;

char ssid[] = "Andreea ";//hotspot
char pass[] = "ciao1234";
int status; //for wifi
WiFiClient client;

const unsigned long channel_id = 1622305;
const char write_api_key[] = "L2CTYUWSPWKC5ECJ";

int counter=0;

void setup (){
  
  status = WL_IDLE_STATUS;

  Serial.begin(9600);
  
  getWiFi();
  
  while (!Serial);

  //iniazialize LoRa radio
  if (!LoRa.begin(868E6)){
    Serial.println("Starting LoRa failed!");
    while (1);
  }
  
  ThingSpeak.begin(client);
  
  pinMode(soilPower, OUTPUT);
  digitalWrite(soilPower, LOW);
  
}

void loop(){
  findTemp();
  displayTemp();
  
  //find light
  light = analogRead(A1);
  displayLight();
  
  displaySoil();

  Serial.println("Posting " + String(soil) + " to ThingSpeak");
  Serial.println("Posting " + String(temp, 2) + " to ThingSpeak");
  Serial.println("Posting " + String(light) + " to ThingSpeak");
  
  ThingSpeak.setField(1, String(soil));
  ThingSpeak.setField(2, String(temp, 2));
  ThingSpeak.setField(3, String(light));
  
  ThingSpeak.writeFields(channel_id, write_api_key);
  
  Serial.println("Transmitting to LoRA " + (String)soil);
  //transmit LoRa packet
  LoRa.beginPacket();
  LoRa.print(soil);
  LoRa.endPacket();

  delay(5000);

}

void getWiFi(){
  Serial.println("Attempting to connect to WPA network ...");
  
  status = WiFi.begin(ssid, pass);
  
  if (status != WL_CONNECTED){
    Serial.println("Couldn't get a wifi connection");

    while(true);
  }
  else {
    Serial.print("Connected to ");
    Serial.println(ssid);
    Serial.print("with IP address: ");
    Serial.println(WiFi.localIP());
    
  }
}

void findTemp(){
  float voltage = 0;
  float sensor = 0;
  sensor = analogRead(A0);
  voltage = (sensor * 5000) / 1024; // convert the raw sensor value to millivolts
  voltage = voltage - 500; // remove the voltage offset
  temp = voltage / 10; // convert millivolts to Celsius
}

void displayTemp(){
  Serial.print("Temperature is ");
  Serial.print(temp, 2);
  Serial.println(" deg. C");
}
void displayLight(){
  Serial.print("Light is ");
  Serial.println(light);
}

int readSoil(){
  digitalWrite(soilPower, HIGH);
  delay(10);
  soil = analogRead(soilPin);
  digitalWrite(soilPower, LOW);
  return soil;
}

void displaySoil(){
  Serial.print("Soil moisture is ");
  Serial.println(readSoil());
}
