# Proyecto de Java

**Integrantes:** 

- Michael Alexis Chacón Marín
- Duván Camilo Arenas Rodríguez

### Objetivos:

- Desarrollo de consultas SQL eficientes.
- Mejora de la estructura de la base de datos
- Automatización de procesos
- Análisis de datos

Se plantea la siguiente situación:

*Vuelos Globales opera vuelos a nivel internacional y cuenta con una flota de aviones, tripulación variada, múltiples aerolíneas asociadas y una vasta red de aeropuertos y ciudades de destino. La empresa requiere una base de datos robusta para gestionar todos los aspectos de su operación, desde la reserva de vuelos hasta el mantenimiento de los aviones y la administración de la tripulación.*

Requerimientos de la base de datos suministrada:

1. Gestión de aviones y modelos
2. Mantenimiento y revisiones
3. Gestión de tripulación
4. Gestión de rutas y escalas
5. Reservas y clientes
6. Tarifas y tipos de documentos

### Casos de uso del proyecto:

1. Registrar avión
2. Asignar tripulación a trayecto
3. Crear reserva de vuelo
4. Registrar revisión de mantenimiento
5. Consultar información de cliente
6. Consultar reserva de vuelo
7. Registrar cliente
8. Consultar información de avión
9. Consultar información de trayecto
10. Registrar aeropuerto
11. Consultar información de aeropuerto
12. Consultar historial de revisiones de avión
13. Actualizar información de cliente
14. Eliminar reserva de vuelo
15. Actualizar información de avión
16. Eliminar avión
17. Asignar aeronave a trayecto
18. Actualizar información de trayecto
19. Eliminar trayecto
20. Actualizar información de aeropuerto
21. Eliminar aeropuerto
22. Actualizar información de cliente
23. Consultar información de vuelo
24. Actualizar información de revisión
25. Eliminar revisión de mantenimiento
26. Consultar asignación de tripulación
27. Consultar escalas de un trayecto
28. Actualizar información de escala
29. Eliminar escala
30. Registrar tarifa de vuelo
31. Actualizar información de tarifa de vuelo
32. Eliminar tarifa de vuelo
33. Consultar tarifa de vuelo
34. Registrar tipo de documento
35. Actualizar tipo de documento
36. Eliminar tipo de documento
37. Consultar tipo de documento
38. Buscar vuelos
39. Seleccionar vuelo
40. Añadir pasajeros
41. Seleccionar asientos
42. Realizar pago
43. Consultar reserva de vuelo
44. Cancelar reserva de vuelo
45. Modificar reserva de vuelo

_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________

Base de datos empleada:

![](https://raw.githubusercontent.com/Michael-Chacon/airport/main/imgs/DRE.png)

Para llevar a cabo este proyecto el equipo de desarrollo empleó arquitectura hexagonal y vertical slicing:

El proyecto está organizado por actores, los cuales son: Agente de ventas, mantenimiento, administrador del sistema, clientes , donde cada uno tiene un paquete que le corresponde. Adicionalmente, hay un paquete para las clases que no corresponden a un actor en específico llamada "Generals". Así como un paquete para las clases útiles llamado "Shared".

![](https://raw.githubusercontent.com/Michael-Chacon/airport/main/imgs/arquitecturaHexagonal.png)

En el menú principal del programa se muestran estos cuatro actores y las acciones que se pueden realizar con cada uno basado en los requerimientos.

![](https://raw.githubusercontent.com/Michael-Chacon/airport/main/imgs/menuPrincipal.png)

Cada menú del proyecto cumple con los requerimientos presentados y realiza funciones CRUD, como se puede observar a continuación:

![](https://raw.githubusercontent.com/Michael-Chacon/airport/main/imgs/CRUD.png)



El proyecto se llevó a cabo con base en los requerimientos presentados y en los casos de uso solicitados por el docente.

