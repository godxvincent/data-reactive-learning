# data-reactive-learning

Este proyecto fue creado para hacer unos ejercicios sencillos de como se usan 
las librerias reactivas de springboot.

El material aqui consignado esta basado en el curso de oreilly **_Reactive Spring Boot, 2nd Edition_**

Notas importantes
* Al trabajar con R2DBC se debe tener en consideración que este no crea la entidad, porque es una implementación agena a hibernate.
* Basicamente hay dos formas de manejar las transacciones una explicita que es usando un `TransactionalOperator` el cual nos 
  permite controlar que partes del pipeline hacen parte de la transacción y cuales no. Sin embargo, también esta la forma 
  implicita que es a través de notaciones `@transactional` en el servicio o metodo que 
  se encarga de la construcción del pipeline. Adicionalmente debe habilitarse a nivel de la app que se
  manejara este modelo transaccional con `@EnableTransactionManagement` 