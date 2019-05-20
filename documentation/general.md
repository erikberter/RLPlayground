# Tabla de Clases

## Optimizadores
Paquete:`com.rlgym.RLPlayground.algorithms.optimization`  
Contenido:
* **Optimization** (_Interface_): Interfaz que define la forma genérica de las clases de optimización.
* **OptimizationType** (_Enum_): Enum de los tipos de optimización que hay.
    1. **EnviromentOptimization** (_Interface_): Interfaz de los algoritmos basados en los entornos dinamicos.
    2. **StateOptimization** (_Interface_): Interfaz de los algoritmos basados en los entornos de estados.

***
Paquete:`com.rlgym.RLPlayground.algorithms.optimization.categories`  
Contenido:
* **GenericOptimizators**(_Abstract Class_): Clase abtracta que tiene los métodos base de el resto de clases de optimización.
* **OptimizationNames**(_enum_): Enum de los algoritmos de optimización que hay 
    1. **DQN**(_class_): Algoritmo DQN
    2. **QLearning**(_class_): Algoritmo QLearning
    
    
    