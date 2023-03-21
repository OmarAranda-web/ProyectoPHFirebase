#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// Configuramos la base de dato y  la red
#define FIREBASE_HOST "proyectophprueba1-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "gDWfrWdvJjF9VinOMy4pYfoAh5XaaHBcz3n9aSaK"
#define WIFI_SSID "Redmi Note 9S"
#define WIFI_PASSWORD "12345678a"

float valor,voltaje2,ph3; //Declaramos 3 variables para la obtenciol del valor analogico, el voltaje y el ph en flotante

void setup() {
  Serial.begin(9600);

  // Nos conectamos a internet
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}

int n = 0;

void loop() {
  valor = analogRead(A0); //Obtenemos el valor analogico del puerto A0
  voltaje2=valor*3.13/1024; //Obtenemos el voltaje que recibe el puerto A0
  ph3= -17*voltaje2 + 52.3;//Obtenemos el ph el cual multiplicaremos el voltaje obtenido por -17 y eso le sumamos 55.34 para compensar el valor negativo
  int phInt=ph3;
  //int phEntero=ph3;//Transformamos el ph de tipo de dato flotante entero
  //Imprimimos ph 
  Serial.print("Nivel del PH: ");
  Serial.println(phInt);
  Serial.print("Voltaje");
  Serial.println(voltaje2);
  delay(1000);
  Firebase.setInt("ph",phInt); //Enviamos el valor a la base de datos en la nube
  // handle error
  if (Firebase.failed()) {
      Serial.print("setting /number failed1:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(1000);
}
