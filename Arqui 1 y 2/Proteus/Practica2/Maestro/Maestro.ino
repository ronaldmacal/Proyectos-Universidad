#include <Keypad.h>
#include <LiquidCrystal_I2C.h>
#include <Wire.h>

bool passInput = false;
bool loggedIN = false;
String currentInput = "";
int slaveADD = 10;
int lcd_slaveADD = 0x20;
int temp_slaveADD = 0x48;
const byte rows = 4;
const byte cols = 3;
char keys[rows][cols] = {
  {'1', '2', '3'},
  {'4', '5', '6'},
  {'7', '8', '9'},
  {'*', '0', '#'}
};
byte rowPins[] = {13, 12, 11, 10};
byte colPins[] = {9, 8, 7};
Keypad kp = Keypad(makeKeymap(keys), rowPins, colPins, rows, cols);

LiquidCrystal_I2C lcd(lcd_slaveADD, 20, 4);

void setup() {
  lcd.init();
  lcd.backlight();
  lcd.setCursor(1, 0);
  lcd.print("CASA ACYE1");
  lcd.setCursor(1, 1);
  lcd.print("A-G07-S2");
  setupTempSensor();
  //Serial.begin(9600);
}

void armHouse() {
  Wire.beginTransmission(slaveADD);
  Wire.write("ACTIVAR");
  Wire.endTransmission();
}

void disarmHouse() {
  Wire.beginTransmission(slaveADD);
  Wire.write("APAGAR");
  Wire.endTransmission();
}


void setupTempSensor() {
  Wire.beginTransmission(temp_slaveADD);
  Wire.write(0xAC); // empezar configuracion
  Wire.write(0); // modo conversion continua
  Wire.beginTransmission(temp_slaveADD); // por alguna razon se muere la conexion y hay que comenzarla otra vez
  Wire.write(0xEE); // comando de conversion de temperatura
  Wire.endTransmission();
}

int getCurrentTemp() {
  Wire.beginTransmission(temp_slaveADD);
  Wire.write(0xAA); // codigo para pedir temperatura
  Wire.endTransmission(false); // mantiene la conexion activa
  Wire.requestFrom(temp_slaveADD, 2);
  int valor_msb = Wire.read();
  int valor_lsb = Wire.read();
  int temp = valor_msb << 1 | valor_lsb >> 7;
  temp = temp * 10 / 2;
  return temp;
}

void loop() {
  if (loggedIN) {
    char key = kp.getKey();
    if (key == '#') {
      loggedIN = false;
      passInput = false;
      currentInput = "";
      lcd.setCursor(1, 0);
      lcd.print("CASA ACYE1");
      lcd.setCursor(1, 1);
      lcd.print("A-G07-S2");
      disarmHouse();
    }else{
      String temp = String(getCurrentTemp());
      Wire.beginTransmission(slaveADD);
      Wire.write(temp.c_str());
      Wire.endTransmission();  
      delay(300);
      key = kp.getKey();
      if (key == '#') {
        loggedIN = false;
        passInput = false;
        currentInput = "";
        lcd.setCursor(1, 0);
        lcd.print("CASA ACYE1");
        lcd.setCursor(1, 1);
        lcd.print("A-G07-S2");
        disarmHouse();
      }
    }
  } else {
    //Serial.println("Esperando entrada de el KeyPad");
    char key = kp.waitForKey();
    if (passInput) {
      currentInput += key;
      lcd.setCursor(currentInput.length(), 1);
      lcd.print(key);
      if (key == '*') {
        if (currentInput == "202107*") {
          currentInput = "";
          lcd.clear();
          lcd.setCursor(1, 0);
          lcd.print("BIENVENIDO");
          lcd.setCursor(1, 1);
          lcd.print("A CASA");
          passInput = false;
          loggedIN = true;
          armHouse();
        } else {
          currentInput = "";
          lcd.clear();
          lcd.setCursor(1, 0);
          lcd.print("ERROR EN CONTRASEÑA");
        }
      } else if (key == '#') {
        passInput = false;
        currentInput = "";
        lcd.setCursor(1, 0);
        lcd.print("CASA ACYE1");
        lcd.setCursor(1, 1);
        lcd.print("A-G07-S2");
        disarmHouse();
      }
    } else {
      if (loggedIN) {
        if (key == '#') {
          loggedIN = false;
          lcd.setCursor(1, 0);
          lcd.print("CASA ACYE1");
          lcd.setCursor(1, 1);
          lcd.print("A-G07-S2");
          disarmHouse();
        }
      } else {
        if (key == '*') {
          //Serial.println("  -Modo Password Input");
          lcd.clear();
          lcd.setCursor(1, 0);
          lcd.print("Insert Pass");
          passInput = true;
        } else {
          lcd.setCursor(1, 0);
          lcd.print("CASA ACYE1");
          lcd.setCursor(1, 1);
          lcd.print("A-G07-S2");
        }
      }
    }
  }

}
