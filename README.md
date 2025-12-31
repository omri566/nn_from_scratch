# nn_from_scratch

Small Java neural network from scratch (no external libraries). Trains on MNIST-formatted CSV.

Quick start

- Build: `javac -d bin src/*.java`
- Train: `java -cp bin App`
- Visualize predictions: `java -cp bin VisualizePredictions [epochs] [batch] [samples]`
- Smoke test: `java -cp bin QuickTest`

Notes

- Data: place `train.csv` in `raw_data/` (CSV with header: `label,pixel0,...`).
- Educational code — not optimized for production.
