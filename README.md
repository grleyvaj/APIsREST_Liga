# APIsREST_Liga
Este proyecto expone una API REST de la Liga Nacional de Fútbol. La API REST fue desarrollada con `Spring Boot`, `Spring Data`, `JPA` y `MySQL`. Se implementan como buenas prácticas la paginación, versionado y validaciones. Para escribir un código más simple y legible se aplican anotaciones, implementan validadores y emplea `Lombok`.  Para el manejo de recursos e hipertexto empleo `Spring HATEOAS`, para la seguridad `Spring Security` y seguiridad basada en tokens (`JWT`). Para gestionar migraciones `flyway`. El proyecto cuenta con una API versión FREE y una API versión PAID. Empleo `guava` e implemento una anotación de validación `Throttling` para limitar la cantidad de peticiones que se realizan a la versión FREE.