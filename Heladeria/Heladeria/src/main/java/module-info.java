module proyectopoo.heladeria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens proyectopoo.heladeria to javafx.fxml;
    exports proyectopoo.heladeria;
}
