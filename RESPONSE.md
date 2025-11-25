Cambios hechos por mi:

- En el repository he añadido una clase CustomerInDataBase que se encarga de guardar y encontrar a los clienets en nuestra base de datos. Esta clase utiliza la interfaz de CustomersJAPRespository quien es el que se encarga de proporcionar automáticamente métodos CRUD, independientemente de la base de datos que se vaya a utilizar. Por lo tanto, se podria decir que estamos utilizando una especie de patrón "Adapter" para solucionar el problema de cambio en la base de datos.


-  También habia que poner una anotacion @Profile("InMemory") en la clase CustomerInMemoryRepository porque supongo que el Spring boot no sabía cual implmentación coger para correr el sistema.


- He añadido unas anotaciones @NoArgsConstructor y @AllArgsConstructor en CustomerEntity porque Spring Boot necesitaba un constructor vacío.


- Era neceario definir el valor de Enrollment y Active en el método handle() de la clase CreateCustomerCommand porque cuando se creaba al Customer no se definía la fecha del Enrollment ni si estaba Active.

Patrones de diseño observados:

- Simplemente con ver la clase Customer y CustomerEntity, podemos apreciar @Builder que nos indica que se ha utilizado un patrón Builder.


- Antes de borrar la clase CustomerInMemoryRepository, podiamos ver un @Scope("singleton") que nos inidca que se estaba utilizando un patrón Singleton creacional. Luego este patrón se ha trasladado a la clase CustomerInDataBase.


- Al debuggear el código tambien he visto que el Spring Boot utiliza el patrón Proxy para algunas funcionalidades como @Transactional.


Principios SOLID observados:

- He podido observar que las clases tienen responsabilidad única como el controlador o  create customer command


- Se ha utilizado inversion de dependencias ya que se utilzan interfaces para no depender de las clases directamente


- El principio open/closed se cumple parcialmente ya que esta abierto a extensión como en el caso de las bases de datos. Sin embargo, hay partes del codigo que estan muy acoplados que son difíciles de extender.


Refactorización hecha:


- He visto que en la clase PutCustomerDTO y CreateCustomerCommand se repetían los atributos y se pasaban de una clase a otra por lo que era un poco innecesario. Para solucionar esto, directamente he generado el id en PutCustomerDTO y he borrado todos los atributos que se repetían en la CreateCustomerCommand y lo sustituí por un objeto PutCustomerDTO que ya contiene todos los datos para guardar el cliente.

