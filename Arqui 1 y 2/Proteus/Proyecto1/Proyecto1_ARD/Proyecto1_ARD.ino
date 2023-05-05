int pinTrigger = 13;
int pinEcho = 12;

int speedA = 5;
int ledA = 14;
int speedB = 4;
int ledB = 15;
int speedC = 3;
int ledC = 16;
int speedD = 2;
int ledD = 17;

int stopLed = 6;

int DCDir_A = 11;
int DCDir_B = 10;
int DCDir_C = 9;
int DCDir_D = 8;

int slowVal = 50;
int fastVal = 200;

bool rightBool = false;
bool leftBool = false;
bool stopBool = false;
bool slowBool = true;
bool fastBool = false;
bool metaBool = false;
bool nometaBool = false;

void setup() {
  Serial.begin(9600);
  Serial1.begin(9600);
  Serial1.println("Comunicacion Iniciada");
  
  pinMode(pinTrigger, OUTPUT);
  pinMode(pinEcho, INPUT);
  //pinMode(backLed, OUTPUT);

  pinMode(DCDir_A, OUTPUT);
  pinMode(DCDir_B, OUTPUT);
  pinMode(DCDir_C, OUTPUT);
  pinMode(DCDir_D, OUTPUT);

  pinMode(ledA, OUTPUT);
  pinMode(ledB, OUTPUT);
  pinMode(ledC, OUTPUT);
  pinMode(ledD, OUTPUT);
  pinMode(stopLed, OUTPUT);

  digitalWrite(ledA, LOW);
  digitalWrite(ledB, LOW);
  digitalWrite(ledC, LOW);
  digitalWrite(ledD, LOW);
  digitalWrite(stopLed, LOW);

  analogWrite(speedA, 0);
  analogWrite(speedB, 0);
  analogWrite(speedC, 0);
  analogWrite(speedD, 0);  
}

void loop() {
  // Se envia un pulso de 2 o mas microsegundos para activar el sensor
  digitalWrite(pinTrigger, LOW); // Primero se coloca en LOW para preparar el pulso (que debe ser en high)
  delayMicroseconds(2);
  digitalWrite(pinTrigger, HIGH); // Se activa el pulso de HIGH por 5 microsegundos 
  delayMicroseconds(5);
  digitalWrite(pinTrigger, LOW); // Se detiene el pulso para activar el sensor

  // Se cuenta la duracion del pulso recibido desde el sensor en microsegundos
  long duration = pulseIn(pinEcho, HIGH);
  // Se calcula la distancia y se corrige la distancia
  long cm = getCMFromUSS(duration)-4;

  if(cm <= 20){
    if(!metaBool){
      Serial.print("MMM");
    }
    rightBool = false;
    leftBool = false;
    stopBool = true;
    analogWrite(speedA, 0);
    analogWrite(speedB, 0);
    analogWrite(speedC, 0);
    analogWrite(speedD, 0);

    digitalWrite(ledA, LOW);
    digitalWrite(ledB, LOW);
    digitalWrite(ledC, LOW);
    digitalWrite(ledD, LOW);
    digitalWrite(stopLed, HIGH);
    nometaBool = false;
    metaBool = true;
  }else{
    metaBool = false;
    if(!nometaBool){
      Serial.print("NNN");
      nometaBool = true;
    }
    digitalWrite(stopLed, LOW);
  }

  Serial1.print("Distancia: ");
  Serial1.print(cm);
  Serial1.println(" cm"); 

  if(Serial.available() >0){
    char blue_input = Serial.read();
    Serial1.print("Recibido: ");
    Serial1.println(blue_input);

    if(blue_input == 'o'){ // on = Adelante
      rightBool = false;
      leftBool = false;
      stopBool = false;
      digitalWrite(DCDir_A, HIGH);
      digitalWrite(DCDir_B, LOW);
      digitalWrite(DCDir_C, HIGH);
      digitalWrite(DCDir_D, LOW);

      digitalWrite(ledA, HIGH);
      digitalWrite(ledB, HIGH);
      digitalWrite(ledC, HIGH);
      digitalWrite(ledD, HIGH);
      
      if(fastBool){
        analogWrite(speedA, fastVal);
        analogWrite(speedB, fastVal);
        analogWrite(speedC, fastVal);
        analogWrite(speedD, fastVal);  
      }else{
        analogWrite(speedA, slowVal);
        analogWrite(speedB, slowVal);
        analogWrite(speedC, slowVal);
        analogWrite(speedD, slowVal);  
      }
    }

    if(blue_input == 's'){ // stop = Detenerse
      rightBool = false;
      leftBool = false;
      stopBool = true;
      analogWrite(speedA, 0);
      analogWrite(speedB, 0);
      analogWrite(speedC, 0);
      analogWrite(speedD, 0);

      digitalWrite(ledA, LOW);
      digitalWrite(ledB, LOW);
      digitalWrite(ledC, LOW);
      digitalWrite(ledD, LOW);
    }

    if(blue_input == 'L'){ // Left = Izquierda
      analogWrite(speedB, 0);
      if(fastBool){
        analogWrite(speedA, fastVal);  
      }else{
        analogWrite(speedA, slowVal);  
      }

      digitalWrite(ledA, HIGH);
      digitalWrite(ledB, LOW);
      digitalWrite(ledC, HIGH);
      digitalWrite(ledD, HIGH);
      
      rightBool = false;
      leftBool = true;  
      stopBool =false;    
    }

    if(blue_input == 'R'){ // Right = Derecha
      analogWrite(speedA, 0);
      if(fastBool){
        analogWrite(speedB, fastVal);  
      }else{
        analogWrite(speedB, slowVal);  
      }

      digitalWrite(ledA, LOW);
      digitalWrite(ledB, HIGH);
      digitalWrite(ledC, HIGH);
      digitalWrite(ledD, HIGH);
      
      rightBool = true;
      leftBool = false;
      stopBool =false;
    }

    if(blue_input == 'B'){ // Back = Reversa
      rightBool = false;
      leftBool = false;
      stopBool =false;
      digitalWrite(DCDir_A, LOW);
      digitalWrite(DCDir_B, HIGH);
      digitalWrite(DCDir_C, LOW);
      digitalWrite(DCDir_D, HIGH);

      digitalWrite(ledA, HIGH);
      digitalWrite(ledB, HIGH);
      digitalWrite(ledC, HIGH);
      digitalWrite(ledD, HIGH);

      if(fastBool){
        analogWrite(speedA, fastVal);
        analogWrite(speedB, fastVal);
        analogWrite(speedC, fastVal);
        analogWrite(speedD, fastVal);  
      }else{
        analogWrite(speedA, slowVal);
        analogWrite(speedB, slowVal);
        analogWrite(speedC, slowVal);
        analogWrite(speedD, slowVal);  
      }
    }

    if(blue_input == 'S'){ // Slow = Velocidad Baja
      fastBool = false;
      slowBool = true;
      if(!rightBool){
        analogWrite(speedA, slowVal);
      }
      if(!leftBool){
        analogWrite(speedB, slowVal);  
      }
      if(!stopBool){
        analogWrite(speedC, slowVal);  
      }
      if(!stopBool){
        analogWrite(speedD, slowVal);
      }
    }

    if(blue_input == 'F'){ // Fast = Velocidad Alta
      fastBool = true;
      slowBool = false;
      if(!rightBool){
        analogWrite(speedA, fastVal);
      }
      if(!leftBool){
        analogWrite(speedB, fastVal);
      }
      if(!stopBool){
        analogWrite(speedC, fastVal);  
      }
      if(!stopBool){
        analogWrite(speedD, fastVal);
      }
    }
  }
}

long getCMFromUSS(long microseconds)
{
  // 29 microsegundos equivalen a 1 cm de ida y vuelta o 14.5 microsegundos de ida (la distancia)
  // el tiempo que le toma al sonido ir y volver dividido por 29 y luego por dos devuelve la distancia en centimetros
  return microseconds / 29 / 2;
}
