//module org.trainer.interval_trainer {
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires java.sql;
//
//
//    opens org.trainer.interval_trainer to javafx.fxml;
//    exports org.trainer.interval_trainer;
//    exports org.trainer.interval_trainer.NewRoutine;
//    exports org.trainer.interval_trainer.Activity;
//    opens org.trainer.interval_trainer.NewRoutine to javafx.fxml;
//    opens org.trainer.interval_trainer.Activity to javafx.fxml;
//}

module org.trainer.interval_trainer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;

    opens org.trainer.interval_trainer to javafx.fxml;
    exports org.trainer.interval_trainer;
    exports org.trainer.interval_trainer.NewRoutine;
    exports org.trainer.interval_trainer.Activity;
    exports org.trainer.interval_trainer.test to junit;
    exports org.trainer.interval_trainer.controller;
    opens org.trainer.interval_trainer.NewRoutine to javafx.fxml;
    opens org.trainer.interval_trainer.controller to javafx.fxml;
    opens org.trainer.interval_trainer.Activity to javafx.fxml;
    exports org.trainer.interval_trainer.controller.new_routine;
    opens org.trainer.interval_trainer.controller.new_routine to javafx.fxml;
}