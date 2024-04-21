module org.trainer.interval_trainer {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens org.trainer.interval_trainer to javafx.fxml;
    exports org.trainer.interval_trainer;
    exports org.trainer.interval_trainer.NewRoutine;
    opens org.trainer.interval_trainer.NewRoutine to javafx.fxml;
}