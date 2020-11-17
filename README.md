3/11

1º
Creacion proyecto Inventory y 2 clases java Login y Splash

Añadimos al activity de splash un textview y un imageview

Añadimos en la carpeta values dimens.xml para poner las dimensiones del diseño

Vamos a android Asset y reescalamos la imagen del logo ya que es demasiado grande y creando una nueva
carpeta img cambiamos la imagen

Añadimos un nuevo font a la aplicacion

2º
Creamos en splash el metodo OnStart() y ahi creamos un Handler.

Creamos una constante para que la aplicacion se pare 2 segunods

Dentro del metodo run del Handler llamamos a nuestro metodo InitLogin.

En nuestro método initLogin forzamos a que la activity se destruya para que no se pueda volver a ella

3º
Vamos a hacer un login con un LinearLayout (muy ineficiente)

En la activity de login añadimos 2 TextInputLayout

Hacemos el login de Inventory (moodle)

4/11

1º
Añadimos un TextView como título del login

Damos id a todos los widget de la activity

El linearlayout de los botones de google y facebook le ponemos un peso para que ocupe lo que sobre
de la pantalla


2º
Hacemos que los botones vayan abajo de la aplicacion

Hacemos una segunda forma para adaptar los botones de abajo (no funciona en este ejemplo)

3º
Le ponemos un estilo a los botones de la aplicacion

4º
Cambiamos los botones de google y facebook a imagebutton y le ponemos imagenes y le quitamos
la parte gris

(Arreglado error donde salia to.do en la misma linea soy imbecil y tenia puesto el LinearLayout
principal en horizontal)


5º
Hacemos la pantalla de registro con ConstraintLayout

Añadimos dos guías al activity (click derecho en el constraint -> Helpers -> Vertical guideline)

Solución error de match parent (al tilUser hay que ponerle que se adapte a las guias solo poniendo 0dp
si no se adapta al padre y tiene 2 tamaños)

6º
Añadimos todos los text a una cadena vertical (click derecho con todos seleccionados -> chains)
(no funciona sin meter to.do asi que lo quitamos)

7º
Añadimos nuestro propio style en styles.xml

8º
Añadimos un nuevo activity y fragments

Añadimos la carpeta navigation

Hacemos que el boton Login vaya a dashboardfragment


Actualizacion 6/11

1º Arreglamos bug visual en dashboard (A los view hay que poner un tamaño predefinido)

2º Hacemos que los botones se centren para ello ponemos un layout gravity en  TableLayout y también
que no ocupe to.do el padre sino que ocupe solo lo necesario con wrap_content

3º Hacemos que los botones de google y facebook no tengan fondo

4º
Creamos 2 nuevos paquetes ui y data

Dentro de data creamos model y repository

Dentro de model creamos la clase user y la rellenamos

En repository creamos UserRepository

(El UserRepository es la clase que se encargara de manejar todas las operaciones del usuario)

5º
Vamos a SignUp y hacemos la RN (estan en la moodle en guia inventory)

Creamos el package presenter y dentro la clase UserPresenter

En la clase UserpResenter creamos el meto addUser y validateUser

(La clase UserPresenter controla las RN de user)

6º
Creamos 2 interfaces las cuales haran que solo se podrán implementar los métodos necesarios para
comprobar los datos

Añadimos la interfaz AddUserContract.View a SignUp

7º
Creamos una nueva interfaz UserView