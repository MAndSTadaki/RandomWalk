# 酔歩

## packages
- `model`: 簡単な酔歩のモデル
    - `Walker.java`:酔歩するWalker
    - `Simulation.java`: 酔歩のシミュレーション
    - `PositionHistogram.java`: 位置のヒストグラム
- `modelExt`: 連続的酔歩への拡張
    - `ContinuousWalker.java`: 連続的変位の酔歩をするWalker
    - `SimulationContinuous.java`: 連続的変位に対応した酔歩シミュレーション
    - `UniformCase.java`: 一様乱数の場合
    - `ExponentialCase.java`: 指数分布の場合
    - `ExponentialCase2.java`: 指数分布の場合 その2