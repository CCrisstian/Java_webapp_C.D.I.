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
