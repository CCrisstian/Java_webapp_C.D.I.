<h1 align="center">C.D.I. (Context Dependency Injection)</h1>
<p>Es una especificación estándar y framework para la inyección de dependencia y manejo de contextos incluido desde Java EE 6 en adelante</p>
<p align="center"><img width="719" alt="image" src="https://github.com/user-attachments/assets/d361a790-efa2-47ce-98ba-8ae7c270fe04"></p>
<h1 align="center">Inyección de dependencia</h1>
<p>Es un patrón de diseño que forma parte de la programación orientada a objetos en la plataforma Java EE, es parte de la especificación JSR-330.</p>
<p>Especifica que se inyectará un componente o variable de contexto en un atributo de otro componente CDI, es decir para inyectar componentes de la aplicación en el componente actual</p>
<p><b>'Inyectar es justamente suministrar a un objeto una referencia de otros que necesite según la relación, tiene que plasmarse mediante la anotación @Inject'</b></p>
<h1 align="center">Características CDI</h1>
<p align="center"><img width="719" alt="image" src="https://github.com/user-attachments/assets/d73fb364-a22b-4b7f-9dc8-1eeb30bab9d6"></p>
<h1 align="center">Registrar e inyectar</h1>
<h2>Registrar o publicar un bean:</h2>

- Se crea de forma automática, no hay que hacer nada especial para publicar un bean en el contexto de CDI:
```java
public interface Repositorio { }

public class Repositoriolmpl implements Repositorio { }
```

- Inyectar un bean existente en otro bean:
```java
public class Servicelmpl implements Service {

@Inject
private Repositorio repositorio;

}
```

<h1 align="center">Manejo de contextos</h1>

Podríamos no definer ningún contexto explícitamente y queda como `@Dependent`

```java
public interface Repositorio { }

public class Repositoriolmpl implements Repositorio { }
```
Pero también podríamos definir un contexto explícitamente
```java
@ApplicationScoped
public class Servicelmpl implements Service {

@Inject
private Repositorio repositorio;

}
```

<h1 align="center">Contextos CDI</h1>
<h2>@Dependent</h2>

- <b>Descripción</b>: El contexto `@Dependent` es el alcance predeterminado de un bean CDI si no se especifica otro alcance.
- <b>Características</b>:
  - Un bean con este contexto no tiene ciclo de vida propio; su ciclo de vida está vinculado al objeto que lo inyecta.
  - Se crea una nueva instancia cada vez que se inyecta el bean.
  - No tiene almacenamiento de estado, lo que significa que no se mantiene entre invocaciones.

<h2>@RequestScoped</h2>

- <b>Descripción</b>: El contexto `@RequestScoped` define un ciclo de vida que abarca una sola solicitud HTTP.
- <b>Características</b>:
  - Una instancia del bean se crea al inicio de una solicitud HTTP y se destruye al final de esa solicitud.
  - Es útil para almacenar datos temporales que solo son relevantes durante la ejecución de una solicitud.
  - Se utiliza frecuentemente en aplicaciones web donde se manejan solicitudes HTTP.
 
<h2>@SessionScoped</h2>

- <b>Descripción</b>: El contexto `@SessionScoped` define un ciclo de vida que abarca la sesión de un usuario en una aplicación web.
- <b>Características</b>:
  - Una instancia del bean se crea cuando se inicia una sesión HTTP y se destruye cuando la sesión expira o se cierra.
  - Es adecuado para almacenar datos del usuario que deben persistir mientras dure su sesión.
  - Los beans en este contexto deben implementar la interfaz `Serializable` para asegurar que pueden ser serializados.

<h2>@ConversationScoped</h2>

- <b>Descripción</b>: El contexto `@ConversationScoped` permite definir un ciclo de vida más largo que una solicitud pero más corto que una sesión.
- <b>Características</b>:
  - Permite dividir una interacción de usuario en varias solicitudes HTTP mientras mantiene el estado.
  - Una conversación puede ser transitoria (dura solo una solicitud) o larga (dura varias solicitudes).
  - Requiere control explícito del ciclo de vida de la conversación usando la API de conversación de CDI.
 
<h2>@ApplicationScoped</h2>

- <b>Descripción</b>: El contexto `@ApplicationScoped` define un ciclo de vida que abarca toda la aplicación.
- <b>Características</b>:
  - Una única instancia del bean se crea cuando se inicia la aplicación y se destruye al detenerla.
  - Es ideal para almacenar datos o configuraciones que deben ser compartidos entre todos los usuarios y sesiones.
  - Proporciona un comportamiento similar a un singleton pero gestionado por el contenedor CDI.

<h1 align="center">Anotación @Named</h1>

CDI también nos permite dar nombres a los beans y realizar la inyección mediante el nombre con la anotación `@Named`.

```java
public interface Repositorio { }

@Named("jdbcRepositorio")
public class Repositoriolmpl implements Repositorio { }
```

Luego en el Service lo inyectamos vía nombre del beans
```java
public class Servicelmpl implements Service {

@Inject
@Named("jdbcRepositorio")
private Repositorio repositorio;
```

<h1 align="center">Anotación @Produces</h1>

Otra forma para registrar un bean mediante método, el objeto que devuelve este método (anotado con `@Produces`) queda registrado en el contenedor CDI.
```java
@Produces
public Conexion produceConexion() {
return new Conexion();
```
Opcionalmente también puede tener un nombre y contexto
```java
@Produces
@RequestScoped
@Named("conn")
public Conexion produceConexion() {
return new Conexion();
```

<h1 align="center">Anotación @Qualifier</h1>

Cuando se tiene varias implementaciones de una misma interfaz o clase, y se desea inyectar una de esas implementaciones específicas en un punto particular del código, se utiliza un `@Qualifier` para indicar cuál de las implementaciones debe ser inyectada. Esto ayuda a evitar ambigüedades cuando el contenedor de C.D.I. tiene que decidir qué `bean` inyectar.

```java
@Qualifier
@Retention(RetentionPolicy.RUNTIME) /*Se aplica en tiempo de ejecución*/
@Target({METHOD, FIELD, PARAMETER, TYPE})   /*Donde se aplicará*/
public @interface ProductoServicePrincipal {
}
```

- `@Qualifier`: La anotación que se está creando se utilizará para distinguir entre diferentes implementaciones de un bean a la hora de realizar inyecciones.
- `@Retention(RetentionPolicy.RUNTIME)`: Especifica que la anotación estará disponible en tiempo de ejecución. Esto es necesario para que el contenedor de CDI pueda leer y procesar la anotación cuando se ejecuta la aplicación.
- `@Target({METHOD, FIELD, PARAMETER, TYPE})`: Indica los elementos del código en los que esta anotación puede aplicarse. En este caso, puede ser aplicada a métodos (`METHOD`), campos (`FIELD`), parámetros (`PARAMETER`), y tipos de clase (`TYPE`).

<h1 align="center">Integración con 'EL' (Lenguaje de Expresión)</h1>

También se integra con la librería EL de JSP donde nos permite acceder a métodos y atributos de los beans o componentes CDI mediante el nombre definido con la anotación `@Named`, es decir son asignaciones (o mapping) hacia estos objetos.

```java
@SessionScoped
@Named
public class Carro implements Serializable {
```

Accedemos al carro en las vistas JSP mediante EL:
```jsp
<c:forEach items="${carro.items}" var="item">

</c:forEach>

Total: ${carro.total}
```

<h1 align="center">pom.xml</h1>

```xml
        <dependency>
            <groupId>org.jboss.weld.servlet</groupId>
            <artifactId>weld-servlet-core</artifactId>
            <version>5.1.1.SP1</version>
        </dependency>
```
Al incluir esta dependencia en el `pom.xml`, la aplicación web puede aprovechar las capacidades de CDI para gestionar la inyección de dependencias y el ciclo de vida de los beans. Esto facilita el desarrollo de aplicaciones más modulares, mantenibles y escalables, ya que se puede desacoplar componentes y dejar que el contenedor de CDI gestione sus dependencias.
