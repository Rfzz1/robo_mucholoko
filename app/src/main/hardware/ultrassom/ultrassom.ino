// Arduino Uno: TRIG = 13, ECHO = 12
const int trigPin = 13;
const int echoPin = 12;

long duration;
float distanceCm;

const float thresholdCm = 100.0; // 1.00 m

bool lastState = false; // para evitar envio contínuo

void setup() {
  Serial.begin(9600);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  digitalWrite(trigPin, LOW);
  delay(50);
}

void loop() {
  // Gera pulso de trigger
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  // Lê duração do pulso de echo (microsegundos)
  duration = pulseIn(echoPin, HIGH, 30000); // timeout 30 ms

  if (duration == 0) {
    // sem eco detectado (objeto muito longe ou timeout)
    distanceCm = 999;
  } else {
    // velocidade do som ~0.034 cm/us; divide por 2 (ida e volta)
    distanceCm = (duration * 0.034) / 2.0;
  }

  bool currentState = (distanceCm <= thresholdCm);

  // envia apenas quando houver mudança de estado (evita flood)
  if (currentState && !lastState) {
    Serial.println("1");
  } else if (!currentState && lastState) {
    Serial.println("0");
  }

  lastState = currentState;

  delay(200); // ajuste a taxa de leitura conforme necessário
}
