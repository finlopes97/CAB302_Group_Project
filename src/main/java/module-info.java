module org.trainer.interval_trainer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;

    opens org.trainer.interval_trainer to javafx.fxml;
    exports org.trainer.interval_trainer;
    exports org.trainer.interval_trainer.controller.activity;
    exports org.trainer.interval_trainer.test to junit;
    exports org.trainer.interval_trainer.controller;
    opens org.trainer.interval_trainer.controller to javafx.fxml;
    opens org.trainer.interval_trainer.controller.activity to javafx.fxml;
    exports org.trainer.interval_trainer.controller.new_routine;
    opens org.trainer.interval_trainer.controller.new_routine to javafx.fxml;
    exports org.trainer.interval_trainer.controller.manage_routines;
    opens org.trainer.interval_trainer.controller.manage_routines to javafx.fxml;
}