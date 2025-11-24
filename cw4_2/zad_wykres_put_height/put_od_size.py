import re
from pathlib import Path
import matplotlib.pyplot as plt
from matplotlib.ticker import FormatStrFormatter

# ---- Konfiguracja ----
INPUT_FILE = Path("rbtRandDataTreeHeightResults.txt")  # podaj swój plik
OUTPUT_PNG = Path("AISD_cw4.2_wykres_put_size_RBT_rand.png")

# ---- Parsowanie danych ----
sizes = []
nputs = []

line_re = re.compile(r"size:\s*(\d+)\s*\|\s*nOfPuts:\s*(\d+)")
with INPUT_FILE.open("r", encoding="utf-8") as f:
    for line in f:
        m = line_re.search(line)
        if m:
            sizes.append(int(m.group(1)))
            nputs.append(int(m.group(2)))

if not sizes:
    raise RuntimeError(f"Brak danych w pliku: {INPUT_FILE}")

# ---- Rysowanie wykresu ----
plt.figure(figsize=(10, 6))
plt.plot(
    sizes, nputs,
    marker='o', linestyle='-',
    color='tab:blue',
    linewidth=0.8,   # cieńsza linia
    markersize=3,    # mniejsze markery
    label='Ilość put() na wielkość'
)

plt.title("Liczba wywołań put() w zależności od wysokości RBT")
plt.xlabel("Wysokość RBT")
plt.ylabel("Ilość wywołań put()")
plt.legend()
plt.grid(True, alpha=0.3)

# ---- Czytelna oś X z liczbami całkowitymi ----
# Próbkuj etykiety, aby nie było ich za dużo (np. ~10–15 etykiet)
max_labels = 12
step_idx = max(1, len(sizes) // max_labels)
tick_positions = sizes[::step_idx]
plt.xticks(tick_positions, rotation=0)

# Formatowanie osi X jako liczby całkowite
plt.gca().xaxis.set_major_formatter(FormatStrFormatter('%d'))

# Ustaw zakres osi X na pełny zakres danych
plt.xlim(min(sizes), max(sizes))

# ---- Zapis do PNG i pokaz ----
plt.tight_layout()
plt.savefig(OUTPUT_PNG, dpi=300)
plt.show()

print(f"Zapisano wykres do: {OUTPUT_PNG}")
