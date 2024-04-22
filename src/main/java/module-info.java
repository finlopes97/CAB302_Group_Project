module org.trainer.interval_trainer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;

    opens org.trainer.interval_trainer to javafx.fxml;
    exports org.trainer.interval_trainer;
    exports org.trainer.interval_trainer.NewRoutine;
    exports org.trainer.interval_trainer.test to junit;
    opens org.trainer.interval_trainer.NewRoutine to javafx.fxml;

}