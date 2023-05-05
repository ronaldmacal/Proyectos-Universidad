int motor=6;
int entrada=A0;

void setup() {
  // put your setup code here, to run once:
  pinMode(motor,OUTPUT);
  pinMode(entrada,INPUT);
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  int valorpotenciometro=0;
  int valorsalidaPWM=0;
  valorpotenciometro=analogRead(entrada);
  valorsalidaPWM=map(valorpotenciometro,0,1023,0,255);
  analogWrite(motor,valorsalidaPWM);
}
