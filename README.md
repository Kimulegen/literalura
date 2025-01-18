# Literalura - Gestor de Biblioteca Digital

## 🚀 Características

- Búsqueda de libros a través de la API Gutendex
- Gestión completa de biblioteca personal
- Registro y seguimiento de autores
- Filtrado por idiomas
- Análisis temporal de autores
- Interfaz de consola intuitiva

</br>

**ATENCIÓN**

El sistema solo guardará el primer libro encontrado en su búsqueda

## 🛠️ Tecnologías Utilizadas

- Java 17
- Spring Boot 2.7
- PostgreSQL
- Hibernate
- Maven
- API Gutendex

## 📁 Estructura del Proyecto

```
src/
├── cl.com.aframuz.literalura/
    ├── principal/
    │   └── Principal.java    # Clase principal y menú de la aplicación
    ├── model/
    │   ├── Author.java       # Modelo para autores
    │   ├── AuthorDTO.java    # DTO para transferencia de datos de autores
    │   ├── Libro.java        # Modelo para libros
    │   └── BookDTO.java      # DTO para transferencia de datos de libros
    ├── repository/
    │   └── BookRepository.java # Repositorio para operaciones con la base de datos
    └── service/
        ├── ConsumoAPI.java    # Servicio para consumo de API externa
        └── ConvierteDatos.java # Servicio para conversión de datos
```

## ⚙️ Configuración del Proyecto

### Prerequisitos

- Java 17 o superior
- Maven instalado
- PostgreSQL

### Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/tu-usuario/literalura.git
cd literalura
```

2. Configura la base de datos en `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu-usuario
spring.datasource.password=tu-contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. Ejecuta la aplicación:
```bash
mvn spring-boot:run
```

## 💻 Funcionalidades

1. **Búsqueda de Libros**
   - Búsqueda por título en la API Gutendex
   - Almacenamiento automático en base de datos local

2. **Gestión de Biblioteca**
   - Listado de libros registrados
   - Filtrado por idioma (es, en, fr, pt)
   - Prevención de duplicados

3. **Gestión de Autores**
   - Lista completa de autores registrados
   - Búsqueda de autores por año de vida
   - Información detallada de cada autor

## 🤝 Contribuciones

1. Haz fork del repositorio
2. Crea una rama para tu característica (`git checkout -b feature/nueva-caracteristica`)
3. Realiza tus cambios y haz commit (`git commit -m 'Agrega nueva característica'`)
4. Push a la rama (`git push origin feature/nueva-caracteristica`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 🎯 Uso del Sistema

1. Al iniciar, se mostrará un menú con las siguientes opciones:
```
1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma
0 - Salir
```

2. Selecciona la opción deseada ingresando el número correspondiente

3. Para buscar un libro:
   - Selecciona opción 1
   - Ingresa el título del libro
   - El sistema buscará en Gutendex y guardará automáticamente

4. Para filtrar por idioma:
   - Selecciona opción 5
   - Ingresa el código del idioma (es, en, fr, pt)
   - Se mostrarán todos los libros en ese idioma

## ✨ Agradecimientos

- Gutendex API por proporcionar acceso a su base de datos de libros
- Comunidad de Spring Boot por la documentación y soporte

---
⌨️ con ❤️ por Kimulegen 😊