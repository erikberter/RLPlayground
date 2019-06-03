# Reinforcement Learning Playground

RLP es una librería que pretende dar las herramientas para poder simular los entornos que queramos aplicando algoritmos de Reinforcement Learning. Uno de los objetivos que creo que he cumplido bastante bien es la de abstraer lo suficiente las clases para que en las futuras versiones no haya que tocar demasiado el código ya escrito, solo añadir y modificar sectores muy concretos.

El objetivo de este trabajo es el de experimentar a crear un software siguiendo los principios KISS y SOLID que sea viable para un uso futuro.


Los **requisitos** para el trabajo eran los siguientes:
- _OOP_: Todo el código.
- _Error Handling_: En múltiples zonas(manejo de archivos, manejo de tipos de objetos,...)
- _Threads_: Se utiliza a la hora de llamar a las funciones del manager(entrenar y ejecutar).
- _Manejo de Archivos_: A la hora de guardar la salida de la ultima ejecución se ha hecho creando un archivo. He pensado en guardar el log sin borrar lo anterior, pero no creo que sea necesario por ahora.
- _Aplicación Gráfica_: Toda la aplicación funciona desde una ventana principal y algunos JDialogs para temas de parametros.
- _Tipos de Datos_: Se utilizan mapas hash para guardar los parámetros debido a que no son muchos y es muy optimo. Por otro lado, se utilizan varios ArrayList, LinkedList y NDArray.

## Ejemplos

Ahora mismo existen dos posibles combinaciones de juegos(el proyecto como tal no era implementar los juegos, así no le he dedicado demasiado tiempo).

1. DQN+AppleFall
2. QLearning+Gridworld

Para ponerlo solo hay que cambiar los JSliders.

El Gridworld funciona bien con 200 epochs, llegando a la solución con más reward.

El Applefall no es especialmente estable. En las pruebas que he hecho por mi cuenta pasa del -66, que seia el expected value, a (-20,0), lo cual no es óptimo pero es decente. Las causas exactas de que no converga como debería no las conozco, pero creo que tiene que ver con que en el paper de "Playing Atari with Deep Reinforcement Learning" de DeepMind se muestra como utilizan dos redes neuronales, utilizando una para predecir y acualizando el error en la otra. Despues de cada memory replay igualan la red que estaba siendo actualizada por la que predecia, para aumentar de esta forma la estabilidad. Por otro lado, el máximo de tiempo que la he llegado ha dejar entrenando son 20 minutos a CPU y con una arquitectura de red que no creo que sea la más óptima, lo cual tampoco creo que ayude.


## Funcionamiento

La idea principal del software es la de ofrecer una plataforma para crear entornos simulables de forma sencilla. Estos entornos actualmente se dividen entre Screen Based  Stated Based. Los screen based se basan en los algoritmos de DQN dados por DeepMind. Los state based se basan en aquellos entornos que surgen de una cantidad finita de estados y un movimiento sencillo entre ellos.

Para crear un enviroment lo primero que hacemos es crear una clase en el paquete 

`com.rlgym.RLPlaygrounds.enviroment.games`

a continuación, añadimos ese nombre en el enum de la siguiente forma

`GAMENAME(new CLASSNAME())`

donde _GAMENAME_ es el nombre se le quiere dar en el enum y _CLASSNAME_ es el nombre que le hemos dado a la clase.


Una vez creada la clase, pasaremos a implementar alguno de los estilos predefinidos de Enviroment. Actualmente existen los siguientes:

- ScreenBasedEnviroment
- StateBasedEnviroment

los cuales son interfaces que implementan la interfaz global _Enviroment_. A partir de aquí bastaría con rellenar los métodos teniendo en cuenta los outputs que deberían salir.

Para más información ver la [documentación](documentation/general.md)

## Posibles Mejoras

- Crear:
    - Guia de creación de enviroments
    - Crear printResult Visual
    - SARPA y otros algoritmos
    - Modificación de entornos dentro de la aplicación
- Cambiar:
    - Mejorar sistema de representar los parametros
