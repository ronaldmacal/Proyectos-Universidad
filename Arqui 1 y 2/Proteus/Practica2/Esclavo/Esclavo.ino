#include <LiquidCrystal.h>
#include <Wire.h>

int slaveADD = 10;
const int rs = 13, en = 12, d4 = 10, d5 = 9, d6 = 8, d7 = 7;
const int dc1 = 6, dc2 = 5;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

void setup() {
  pinMode(dc1, OUTPUT);
  pinMode(dc2, OUTPUT);
  Wire.begin(slaveADD);
  Wire.onReceive(instruccionRecibida);
  //Serial.begin(9600);
  lcd.begin(16, 2);
  lcd.setCursor(1, 1);
  lcd.print("APAGADO");
  // put your setup code here, to run once:
}

void loop() {

}

void instruccionRecibida(int numberBytes) {
  String comando = "";
  while (numberBytes > 0) {
    comando += (char)Wire.read();
    numberBytes--;
  }
  if (comando == "ACTIVAR") {
    //Serial.println("  + Comando recibido: Activar Sistema");
    lcd.clear();
    lcd.setCursor(1, 0);
    lcd.print("TEMP: -- ");
    lcd.setCursor(1, 1);
    lcd.print("NIVEL: -- ");
  } else if (comando == "APAGAR") {
    //Serial.println("  + Comando recibido: Apagar Sistema");
    lcd.clear();
    lcd.setCursor(1, 1);
    lcd.print("APAGADO");
    digitalWrite(dc1, LOW);
    digitalWrite(dc2, LOW);
  } else {
    int current_temp = comando.toInt();
    lcd.setCursor(1, 0);
    lcd.print("TEMP: ");
    lcd.setCursor(7, 0);
    lcd.print(current_temp / 10);
    lcd.setCursor(6 + comando.length(), 0);
    lcd.print(".");
    lcd.setCursor(7 + comando.length(), 0);
    lcd.print(current_temp % 10);
    lcd.setCursor(8 + comando.length(), 0);
    lcd.print(" C  ");
    lcd.setCursor(1, 1);

    if (current_temp / 10 <= 18) {
      lcd.print("NIVEL: 1 ");
      digitalWrite(dc1, LOW);
      digitalWrite(dc2, LOW);
    } else if (current_temp / 10 <= 25) {
      lcd.print("NIVEL: 2 ");
      digitalWrite(dc1, HIGH);
      digitalWrite(dc2, LOW);
    } else {
      lcd.print("NIVEL: 3 ");
      digitalWrite(dc1, HIGH);
      digitalWrite(dc2, HIGH);
    }
  }
}
