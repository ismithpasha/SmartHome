char data = 0;                //Variable for storing received data
int buzzer = 9;
int smokeA0 = A5;
int sensorThres = 100;

void setup() 
{
  Serial.begin(9600);         //Sets the data rate in bits per second (baud) for serial data transmission
  pinMode(13, OUTPUT);        //Sets digital pin 13 as output pin
   pinMode(12, OUTPUT);
    pinMode(11, OUTPUT);
     pinMode(10, OUTPUT);   
     digitalWrite(13, HIGH);
     digitalWrite(12, HIGH);
     digitalWrite(11, HIGH);
     digitalWrite(10, HIGH);
     
       pinMode(buzzer, OUTPUT);
  pinMode(smokeA0, INPUT);
}
void loop()
{
 int analogSensor = analogRead(smokeA0);
Serial.print(analogSensor); 
 Serial.print("\n");  
  Serial.print("\n");  
  delay(100);

    if (analogSensor > sensorThres)
  {
    
    tone(buzzer, 1000, 200);
  }
  else
  {
    
    noTone(buzzer);
  }
   
  if(Serial.available() > 0)  // Send data only when you receive data:
  {
    data = Serial.read();      //Read the incoming data and store it into variable data
    Serial.print(data);        //Print Value inside data in Serial monitor
    Serial.print("\n");        //New line 
    if(data == '1')            //Checks whether value of data is equal to 1 
      digitalWrite(13, HIGH);  //If value is 1 then LED turns ON
    else if(data == '0')       //Checks whether value of data is equal to 0
      digitalWrite(13, LOW);   //If value is 0 then LED turns OFF

    else if(data == '3')           
      digitalWrite(12, HIGH);  
    else if(data == '2')       
      digitalWrite(12, LOW);  

    else if(data == '5')           
      digitalWrite(11, HIGH);  
    else if(data == '4')       
      digitalWrite(11, LOW);  


    else if(data == '7')           
      digitalWrite(10, HIGH);  
    else if(data == '6')       
      digitalWrite(10, LOW);  

      
  }                            
 
}                 
