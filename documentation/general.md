# Contenidos


## Tabla de Paquetes
1. [Optimizadores](#Optimizadores)
2. [Enviroment](#Enviroment)
3. [Exploration](#Exploration)

4. [Manager](#Manager)

5. [Visual](#Visual)
 
***

## Algorithms
### Optimizadores
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
* **OptimizationNames**(_Enum_): Enum de los algoritmos de optimización que hay  
    1. **DQN**(_class_): Algoritmo DQN  
    2. **QLearning**(_class_): Algoritmo QLearning

### Exploration
Paquete:`com.rlgym.RLPlayground.algorithms.exploration`
Contenido:
* **explorationAlgorithms**(_Class_): Clase con métodos estáticos para cada función de exploración.
* **explorationFunction**(_Enum_): Lista con las diferentes funciones de degradado del factor de exploración.

### Miscelania
Paquete:`com.rlgym.RLPlayground.algorithms.miscelanea`
Contenido:
* **helpers**(_Class_): Clase con varios métodos ad hoc.

## Configuration

Paquete:`com.rlgym.RLPlayground.configurations`
Contenido:
* **config**(_Class_): Clase con mapas estaticos como configuración.


## Enviroment   

Paquete:`com.rlgym.RLPlayground.enviroment`  
Contenido:
* **Enviroment**(_Interface_): Interfaz con las funciones genéricas de un enviroment.
* **EnviromentType**(_Enum_): Lista de los tipos de enviroment que se pueden usar.

***
Paquete:`com.rlgym.RLPlayground.enviroment.types`
Contenido:
* **ScreenBasedEnviroment**(_Interfaz_): Interfaz de los entornos que tienen como output la pantalla donde se proyectan.
* **StateBasedEnviroment**(_Interfaz_): Interfaz de los entornos que tienen como output un estado.

***
Paquete:`com.rlgym.RLPlayground.enviroment.games`
Contenido:
* **GameName**(_Enum_): Lista de los juegos en el proyecto.

## Manager

Paquete:`com.rlgym.RLPlayground.manager`
Contenido:
* **Log**(_Class_): Clase que hostea el archivo de configuración.
* **Manager**(_Class_): Clase encarda de ejecutar y conducir el runtime de los algoritmos de RL.



## Visual

Paquete:`com.rlgym.RLPlayground.visual.window`
Contenido:
* **dialogHiperParametersDQN**(_Class_): Dialogo de la configuración de los parametros.
* **dialogParameters**(_Class_): Dialogo de la configuración de los hiperparametros para el algoritmo DQN.
* **mainFrame**(_Class_): Frame principal.
    