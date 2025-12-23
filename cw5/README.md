# Kompresor (CW_5) - instrukcja kompilacji

W celu skompilowania programu, należy uruchomić polecenie:
```
mvn clean package
```
W wyniku tej operacji w katalogu `target` zostanie
utworzony plik **AiSD2025ZEx5-1.0-SNAPSHOT.jar**.

Plik ten, zgodnie z wymaganiami podanymi w instrukcji, 
można uruchomić używając komendy ``java -jar``
Wymagana do tego jest Java w wersji 17 lub nowszej.

Wymagane opcje uruchomienia to:
 - `-m [comp/decomp]` wybór trybu operacji
 - `-s [input_path]` określenie ścieżki do pliku wejściowego (kompresowanego / dekompresowanego)
 - `-d [output_path]` określenie ścieżki do pliku wyjściowego (skompresowanego / zdekompresowanego)
 - `-l [n]` określenie liczby bajtów przechowywanych w pojedynczym węźle drzewa Huffmana

# Uwagi
- Opcja `-l` nie jest wymagana dla trybu dekompresji (`decomp`), ponieważ informacja o ilości bajtów w węźle jest już przechowywana w każdym węźle drzewa
- Każdy węzeł drzewa (zawierający klucz) w skompresowanym pliku, składa się kolejno z: 
  - `n` bitów oznaczających długość klucza w bajtach,
  - samego klucza o długości `n` bajtów
- Wartość `n` jest przechowywana w nagłówku pliku, jako 5 bitowa liczba - ma to na celu zmniejszyć ilość niewykorzystywanych bitów w węźle drzewa. Przykładowo jeśli długość klucza to 1 bajt, ale przyjętoby stałą wartość `n`, np. 8 bitów, wówczas dla każdego węzła drzewa nieużywanych pozostawałoby 7 bitów, co prowadziłoby do zwiększenia rozmiaru skompresowanych danych