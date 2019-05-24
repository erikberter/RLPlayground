# Reinforcement Learning Playground


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
