# Sistema de Gestión de Clínica - TPG Programación III

### **✒️ Autores**
-   **Bellone**, Martín
-   **Garcia Di Martino**, Laureano
-   **Paniagua**, Lucas
-   **Sorrenti**, Enzo
-   **Zuviría**, Nicolás

---

### **## 📄 Descripción del Proyecto**

Este repositorio corresponde al Trabajo Práctico Grupal para la asignatura **Programación III** (Ingeniería en Informática, FI - UNMdP).

El objetivo es el desarrollo de un software en **Java** para la gestión de una clínica privada. El sistema modela y administra las interacciones clave dentro de la clínica, como el flujo de pacientes, la asignación de médicos, los costos de internación y la facturación, poniendo especial énfasis en la aplicación de patrones de diseño para lograr una arquitectura de software robusta y escalable.

---

### **## 🚀 Funcionalidades Implementadas**

-   **Gestión de Pacientes y Médicos:** Alta y registro de entidades en el sistema.
-   **Módulo de Ingreso con Prioridades:** Administración de la llegada de pacientes y asignación de la sala de espera privada mediante una lógica de prioridades.
-   **Ciclo de Atención Completo:** Gestión del paciente desde que es atendido hasta su egreso.
-   **Sistema de Internación:** Asignación de habitaciones y cálculo de costos según el tipo y la duración.
-   **Cálculo Dinámico de Honorarios:** Cómputo de los honorarios médicos basado en múltiples variables (especialidad, posgrado, etc.).
-   **Módulo de Facturación:** Generación automática de facturas al dar de alta a un paciente.
-   **Módulo de Reportes:** Creación de informes de actividad para los médicos.

---

### **## 🏛️ Arquitectura y Patrones de Diseño**

La estructura del software se basa en la implementación de patrones de diseño clave para resolver problemas específicos:

-   **`Facade`**: Provee una interfaz simplificada y unificada para interactuar con los subsistemas de la clínica.
-   **`Decorator`**: Añade dinámicamente los pluses de sueldo a los médicos, permitiendo una gran flexibilidad.
-   **`Singleton`**: Garantiza una única instancia centralizada de la `Clinica`.
-   **`Factory Method`**: Encapsula la lógica de creación de los distintos tipos de `Paciente`, `Medico` y `Habitacion`.
-   **`Double Dispatch`**: Maneja la competencia por la sala de espera de forma elegante, evitando condicionales anidados.

---

### **## 💻 Tecnología**

-   **Lenguaje:** Java
-   **Entorno de desarrollo:** Programación Orientada a Objetos (POO)
