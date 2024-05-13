package org.trainer.interval_trainer.controller.new_routine;

import javafx.scene.layout.VBox;
import org.trainer.interval_trainer.Model.BaseItem;

/**
 * Abstract base controller class that extends VBox.
 * This class is designed to be extended by other controllers that manage different
 * types of data items related to the application's routines.
 * Each controller extending this base must implement the getData method to return
 * the specific type of data item it manages.
 */
abstract public class BaseController extends VBox {

    /**
     * Retrieves the data associated with this controller.
     * The specific type of BaseItem returned can vary depending on the implementation.
     * @return BaseItem representing the data managed by this controller.
     */
    abstract public BaseItem getData();
}
