#include <MatrizLed.h>

int global_var_in = 0;
int speedInput = A0;
int modeSelect = 2;
int directionSelect = 20;
int dinamicMode = 3;
bool newInput = true;
bool isDinamic = false;
int sd_a = 53;
int sd_b = sd_a - 2;
int sd_c = sd_b - 2;
int sd_d = sd_c - 2;
int sd_e = sd_d - 2;
int sd_f = sd_e - 2;
int sd_g = sd_f - 2;
String defaultMessage = "*P1 – GRUPO #7 - SECCION A*";
String lastInput = "";
String inputLine = "";

int matriz_data_in = 10;
int matriz_load = 9;
int matriz_clock = 8;

MatrizLed matriz_controller;

void setup() {
  Serial1.begin(9600);
  pinMode(sd_a, OUTPUT);
  pinMode(sd_b, OUTPUT);
  pinMode(sd_c, OUTPUT);
  pinMode(sd_d, OUTPUT);
  pinMode(sd_e, OUTPUT);
  pinMode(sd_f, OUTPUT);
  pinMode(sd_g, OUTPUT);
  pinMode(dinamicMode, INPUT);
  attachInterrupt(digitalPinToInterrupt(dinamicMode), changeDinamic, RISING);
  pinMode(modeSelect, INPUT);
  attachInterrupt(digitalPinToInterrupt(modeSelect), modeChange, CHANGE);
  pinMode(directionSelect, INPUT);
  attachInterrupt(digitalPinToInterrupt(directionSelect), directionChange, CHANGE);
  matriz_controller.begin(matriz_data_in, matriz_clock, matriz_load, 2);
  matriz_controller.setSpeedDisplayPins(sd_a, sd_b, sd_c, sd_d, sd_e, sd_f, sd_g);
  matriz_controller.rotar(true);
}

void loop() {
  //Serial1.println("Inicio de Loop");
  if (digitalRead(modeSelect) == 1) {
    //Serial1.println("  -Modo Letra a Letra");
    if (isDinamic) {
      if (Serial1.available() > 0) {
        int ascii_val = Serial1.read();
        if (ascii_val == 13) {
          lastInput = inputLine;
          inputLine = "";
          Serial1.println("");
          matriz_controller.escribirCharPorChar(lastInput.c_str(), 1000, speedInput);
        } else {
          Serial1.print((char)ascii_val);
          inputLine += (char)ascii_val;
        }
      } else {
        if (!newInput) {
          Serial1.print("Reptitiendo ultimo mensaje: ");
          Serial1.println(lastInput);
          newInput = true;
          matriz_controller.escribirCharPorChar(lastInput.c_str(), 1000, speedInput);
        } else {
          delay(100);
        }
      }
    } else {
      matriz_controller.escribirCharPorChar(defaultMessage.c_str(), 1000, speedInput);
    }
  } else {
    //Serial1.println("  -Modo Desplazamiento");
    if (digitalRead(directionSelect) == 1) {
      //Serial1.println("  -Desplazamiento Normal");
      if (isDinamic) {
        if (Serial1.available() > 0) {
          int ascii_val = Serial1.read();
          if (ascii_val == 13) {
            lastInput = inputLine;
            inputLine = "";
            Serial1.println("");
            matriz_controller.escribirFraseScroll(lastInput.c_str(), 1000, speedInput);
          } else {
            Serial1.print((char)ascii_val);
            inputLine += (char)ascii_val;
          }
        } else {
          if (!newInput) {
            Serial1.print("Reptitiendo ultimo mensaje: ");
            Serial1.println(lastInput);
            newInput = true;
            matriz_controller.escribirFraseScroll(lastInput.c_str(), 1000, speedInput);
          } else {
            delay(100);
          }
        }
      } else {
        matriz_controller.escribirFraseScroll(defaultMessage.c_str(), 1000, speedInput);
      }
    } else {
      //Serial1.println("  -Desplazamiento Invertido");
      if (isDinamic) {
        if (Serial1.available() > 0) {
          int ascii_val = Serial1.read();
          if (ascii_val == 13) {
            lastInput = inputLine;
            inputLine = "";
            Serial1.println("");
            matriz_controller.escribirFraseScrollInvertido(lastInput.c_str(), 1000, speedInput);
          } else {
            Serial1.print((char)ascii_val);
            inputLine += (char)ascii_val;
          }
        } else {
          if (!newInput) {
            Serial1.print("Reptitiendo ultimo mensaje: ");
            Serial1.println(lastInput);
            newInput = true;
            matriz_controller.escribirFraseScrollInvertido(lastInput.c_str(), 1000, speedInput);
          } else {
            delay(100);
          }
        }
      } else {
        matriz_controller.escribirFraseScrollInvertido(defaultMessage.c_str(), 1000, speedInput);
      }
    }
  }
  //Serial1.println("Final de Loop");
}

void changeDinamic() {
  isDinamic = !isDinamic;
  if(isDinamic){
    Serial1.println("Entrando en Modo Dinamico");
  }else{
    Serial1.println("Entrando en Modo Estatico");
  }
  inputLine = "";
  newInput = true;
  matriz_controller.interrumpir();
}

void modeChange() {
  Serial1.println(" + Cambio de Modo");
  inputLine = "";
  newInput = false;
  matriz_controller.interrumpir();
}

void directionChange() {
  Serial1.println(" + Cambio de Direccion");
  inputLine = "";
  newInput = false;
  matriz_controller.interrumpir();
}
