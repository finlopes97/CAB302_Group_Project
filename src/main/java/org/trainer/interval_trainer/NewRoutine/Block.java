package org.trainer.interval_trainer.NewRoutine;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public abstract class Block extends VBox {


    public Block parent;


    public Block(Block parent, String path) {
        this.parent = parent;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }


    public void Delete() {
        parent.deleteChild(this);

    }

    public void AddAfter(Block block) {
        parent.addAfterChild(this, block);
    }


    public  void addAfterChild(Block child, Block newBlock) {}

    public  void deleteChild(Node child) {}


}
