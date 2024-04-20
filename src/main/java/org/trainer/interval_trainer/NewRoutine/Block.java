package org.trainer.interval_trainer.NewRoutine;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public abstract class Block extends VBox {


    public Block parent;
    public Block(Block parent) {
        this.parent = parent;
    }

    public void Delete() {
        parent.deleteChild(this);
    }

    public void AddAfter(Block block) {
        parent.addAfterChild(this, block);
    }


    public abstract void addAfterChild(Block child, Block newBlock);

    public abstract void deleteChild(Node child);


}
