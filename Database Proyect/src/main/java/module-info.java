module com.proyectopractica.proyectopractica {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.proyectopractica.proyectopractica to javafx.fxml;
    exports com.proyectopractica.proyectopractica;
    exports com.proyectopractica.proyectopractica.modelo;
    opens com.proyectopractica.proyectopractica.modelo to javafx.fxml;
    exports com.proyectopractica.proyectopractica.controller;
    opens com.proyectopractica.proyectopractica.controller to javafx.fxml;
    exports com.proyectopractica.proyectopractica.controller.insertar;
    opens com.proyectopractica.proyectopractica.controller.insertar to javafx.fxml;
    exports com.proyectopractica.proyectopractica.controller.eliminar;
    opens com.proyectopractica.proyectopractica.controller.eliminar to javafx.fxml;
    exports com.proyectopractica.proyectopractica.controller.modificar;
    opens com.proyectopractica.proyectopractica.controller.modificar to javafx.fxml;
}