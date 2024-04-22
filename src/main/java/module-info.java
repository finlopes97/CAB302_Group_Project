module org.trainer.interval_trainer {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.trainer.interval_trainer to javafx.fxml;
    exports org.trainer.interval_trainer;
    exports org.trainer.interval_trainer.NewRoutine;
    exports org.trainer.interval_trainer.Activity;
    opens org.trainer.interval_trainer.NewRoutine to javafx.fxml;
    opens org.trainer.interval_trainer.Activity to javafx.fxml;
}