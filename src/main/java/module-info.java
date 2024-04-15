module org.trainer.interval_trainer {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.trainer.interval_trainer to javafx.fxml;
    exports org.trainer.interval_trainer;
}