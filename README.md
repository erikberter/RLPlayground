# Reinforcement Learning Playground



## Instalación



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

## Versiones

### A futuro

- Crear:
    - Nuevos Enviroment
    - Enviroments customizables
    - Crear print Visual
    - SARPA
    - DRL
- Cambiar:
    - Mejorar sistema de representar los parametros
    - Añadir Enum para ordenar los enviroment etc.



## Changelog

- (08/05/2019) 
	- Changed:
		- Los parametros que se veían en el menu principal están siendo cambiados a dos dialogos.
		- Se ha cambiado completamente la estructura del proyecto.
	- Added:
		- Se ha añadido la librería dl4j junto a sus dependencias y se ha creado una versión del DRL.
	- Removed:
		- Se h
- (29/04/2019) 
    - Changed:
        - He cambiado el menu de configuración del mainFrame a un dialog para obtener una configuración más personalizada
- (27/04/2019) 
    - Added:
        - Añadido GridWorld
        - Añadida una versión de Q-Learning
        - Añadidos parametros básicos: Exploration Rate, Learning Rate, Reward on Step, Discount Factor, Epochs