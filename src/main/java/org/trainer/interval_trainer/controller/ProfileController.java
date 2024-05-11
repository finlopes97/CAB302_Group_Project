package org.trainer.interval_trainer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.trainer.interval_trainer.HelloApplication;
import org.trainer.interval_trainer.Model.User;
import org.trainer.interval_trainer.Model.Session;
import java.io.IOException;
import org.trainer.interval_trainer.Model.User;

public class ProfileController {
    public Label nameLabel;
    public Label emailLabel;
    public Label fitnessGoalLabel;

    private final User currentUser = Session.getInstance().getCurrentUser();

    @FXML
    private TextField editableNameField;
    @FXML
    private TextField editableEmailField;
    @FXML
    private TextArea editableFitnessGoalField;
    @FXML
    private Button confirmChangesButton;
    @FXML
    private Button revertChangesButton;
    @FXML
    private Button editProfileButton;
    @FXML
    private HBox confirmRevertBox;

    @FXML
    protected void initialize() throws IOException {
        SetFieldsOfCurrentUser();
    }

    private void SetFieldsOfCurrentUser()
    {
        if (currentUser != null) {
            editableNameField.setText(currentUser.getName());
            editableEmailField.setText(currentUser.getEmail());
            // Assuming the User model has a getFitnessGoal method
            if (currentUser.getFitnessGoal() != null) {
                editableFitnessGoalField.setText(currentUser.getFitnessGoal());
            }
            else {
                editableFitnessGoalField.setText("No current fitness goal");
            }
        }

    }



    @FXML
    private void onEditProfileButton() {
        // Make the text fields editable
        editableNameField.setEditable(true);
        editableEmailField.setEditable(true);
        editableFitnessGoalField.setEditable(true);

        // Show the confirm and revert changes buttons
        confirmChangesButton.setVisible(true);
        revertChangesButton.setVisible(true);
        confirmRevertBox.setManaged(true);
        confirmRevertBox.setMouseTransparent(false);

        // Hide the edit profile button
        editProfileButton.setVisible(false);
    }

    @FXML
    private void onConfirmChangesButton() {
        // Update the user details
        currentUser.setName(editableNameField.getText());
        currentUser.setEmail(editableEmailField.getText());
        currentUser.setFitnessGoal(editableFitnessGoalField.getText());

        // Make the text fields uneditable
        editableNameField.setEditable(false);
        editableEmailField.setEditable(false);
        editableFitnessGoalField.setEditable(false);

        // Hide the confirm and revert changes buttons
        confirmChangesButton.setVisible(false);
        revertChangesButton.setVisible(false);
        confirmRevertBox.setManaged(false);
        confirmRevertBox.setMouseTransparent(true);

        // Show the edit profile button
        editProfileButton.setVisible(true);


    }

    @FXML
    private void onRevertChangesButton() {
        SetFieldsOfCurrentUser();

        // Make the text fields uneditable
        editableNameField.setEditable(false);
        editableEmailField.setEditable(false);
        editableFitnessGoalField.setEditable(false);

        // Hide the confirm and revert changes buttons
        confirmChangesButton.setVisible(false);
        revertChangesButton.setVisible(false);
        confirmRevertBox.setManaged(false);
        confirmRevertBox.setMouseTransparent(true);

        // Show the edit profile button
        editProfileButton.setVisible(true);
    }
}