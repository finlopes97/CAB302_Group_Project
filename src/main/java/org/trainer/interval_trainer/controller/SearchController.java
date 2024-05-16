//
////package org.trainer.interval_trainer.controller;
////
////import javafx.fxml.FXML;
////import javafx.scene.control.Button;
////import javafx.scene.control.Label;
////import javafx.scene.control.TextField;
////import javafx.scene.layout.GridPane;
////import javafx.scene.layout.VBox;
////import org.trainer.interval_trainer.Model.Routine;
////import org.trainer.interval_trainer.Model.SqliteRoutinesDAO;
////import org.trainer.interval_trainer.controller.manage_routines.RoutineListController;
////import org.trainer.interval_trainer.controller.manage_routines.RoutinePreviewController;
////
////import java.io.IOException;
////import java.util.List;
////
/////**
//// * Controls the search functionality within the application.
//// * It manages search operations and updates the UI with search results and suggested routines.
//// */
////public class SearchController extends RoutineListController {
////    @FXML private VBox children;
////    @FXML private Label welcomeText;
////    @FXML private Label header;
////    @FXML private Button homeButton;
////    @FXML private Button createRoutinesButton;
////    @FXML private Button searchPageButton;
////    @FXML private Button searchButton;
////    @FXML private GridPane suggestedRoutinesGrid;
////    @FXML private Label suggestedRoutineLabel;
////    @FXML private Label searchResultLabel;
////    @FXML private TextField searchField;
////
////    private String search;
////
////    /**
////     * Updates the display of routines based on the current search query.
////     * It queries the database for routines that match the search criteria and displays them.
////     */
////    @Override
////    public void updateView() {
////        SqliteRoutinesDAO dao = new SqliteRoutinesDAO();
////        if( search != null){
////            List<Routine> routines = dao.searchRoutine(search);
////            System.out.println(routines);
////            children.getChildren().clear();
////            for (Routine routine : routines) {
////                children.getChildren().add(new RoutinePreviewController(routine, this));
////            }
////        }
////        else {
////            List<Routine> routines = dao.searchRoutine(null);
////            System.out.println(routines);
////            children.getChildren().clear();
////            for (Routine routine : routines) {
////                children.getChildren().add(new RoutinePreviewController(routine, this));
////            }
////        }
////    }
////
////    /**
////     * Handles the action triggered by clicking the search button.
////     * It sets the search query based on the text field input and updates the view to display the results.
////     * @throws IOException if an input/output error occurs.
////     */
////    @FXML
////    protected void onSearchButtonClick() throws IOException {
////        search = searchField.getText();
////        updateView();
////    }
////}
//
//
//        package org.trainer.interval_trainer.controller;
//
//import javafx.concurrent.Task;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.application.Platform;
//import org.trainer.interval_trainer.Model.Routine;
//import org.trainer.interval_trainer.Model.SqliteRoutinesDAO;
//import org.trainer.interval_trainer.controller.manage_routines.RoutineListController;
//import org.trainer.interval_trainer.controller.manage_routines.RoutinePreviewController;
//
//import java.io.IOException;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class SearchController extends RoutineListController {
//    @FXML
//    private Label welcomeText;
//    @FXML
//    private Label header;
//    @FXML
//    private Button homeButton;
//    @FXML
//    private Button createRoutinesButton;
//    @FXML
//    private Button searchPageButton;
//    @FXML
//    private Button searchButton;
//    @FXML
//    private Button profileButton;
//    @FXML
//    private Button settingsButton;
//    @FXML
//    private GridPane suggestedRoutinesGrid;
//    @FXML
//    private Label suggestedRoutineLabel;
//    @FXML
//    private Button cancelButton;
//
//    private boolean isSearchMode = false;
//    @FXML
//    private TextField searchField;
//
//    private static final String DB_URL = "jdbc:sqlite:./src/main/resources/Database.db";
//
//    @FXML
//    protected void initialize() {
//        populateSuggestedRoutinesBox();
//    }
//
//    private void addHeadings(GridPane grid) {
//        String[] headings = {"Name", "Created By", "Description"};
//        for (int i = 0; i < headings.length; i++) {
//            Label headingLabel = new Label(headings[i]);
//            headingLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-weight: bold;");
//            grid.add(headingLabel, i, 0);
//        }
//    }
//
//    private void populateSuggestedRoutinesBox() {
//        addHeadings(suggestedRoutinesGrid);
//        try (Connection connection = DriverManager.getConnection(DB_URL)) {
//            String sql = "SELECT * FROM routines ORDER BY id DESC LIMIT 10";
//            try (PreparedStatement pstmt = connection.prepareStatement(sql);
//                 ResultSet rs = pstmt.executeQuery()) {
//                int row = 1;
//                while (rs.next()) {
//                    int routineId = rs.getInt("id"); // Retrieve the routine ID
//                    String routineName = rs.getString("name");
//                    String createdBy = rs.getString("created_by");
//                    String description = rs.getString("description");
//
//
//                    suggestedRoutinesGrid.add(new Label(routineName), 0, row);
//                    suggestedRoutinesGrid.add(new Label(createdBy), 1, row);
//                    suggestedRoutinesGrid.add(new Label(description), 2, row);
//
//                    Button playButton = new Button("Play");
//                    // Attach the routine ID to the play button
//                    playButton.setUserData(routineId);
//                    playButton.setOnAction(event -> {
//                        int id = (int) ((Button) event.getSource()).getUserData(); // Retrieve the routine ID when clicked
//                        try {
//                            playRoutine(id, event); // Call a method to play the routine with the given ID and pass the ActionEvent
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    });
//
//                    suggestedRoutinesGrid.add(playButton, 5, row);
//
//                    row++;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void playRoutine(int id, ActionEvent event) throws IOException {
//        // Retrieve the routine using the ID
//        SqliteRoutinesDAO dao = new SqliteRoutinesDAO();
//        Routine routine = dao.getRoutine(id);
//        // Create the RoutinePreviewController with the retrieved routine
//        RoutinePreviewController previewController = new RoutinePreviewController(routine, this);
//        // Play the routine
//        previewController.play(event);
//    }
//
//
//    @FXML
//    protected void onSearchButtonClick() throws IOException {
//        String query = searchField.getText();
//
//        if (!isSearchMode) {
//            isSearchMode = true;
//            toggleButtonsVisibility();
//        }
//
//        Task<Void> searchTask = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                List<String> searchResults = new ArrayList<>();
//                try (Connection connection = DriverManager.getConnection(DB_URL)) {
//                    String sql = "SELECT * FROM routines WHERE name LIKE ?";
//                    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//                        pstmt.setString(1, "%" + query + "%");
//                        try (ResultSet rs = pstmt.executeQuery()) {
//                            while (rs.next()) {
//                                String result =
//                                        rs.getString("id")
//                                                + "," + rs.getString("name") +
//                                                "," + rs.getString("created_by") +
//                                                "," + rs.getString("description");
//                                searchResults.add(result);
//                                System.out.println(result);
//                            }
//                        }
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//                Platform.runLater(() -> {
//                    updateSearchResultLabel(searchResults);
//                });
//
//                return null;
//            }
//        };
//
//        new Thread(searchTask).start();
//    }
//
//    @FXML
//    protected void onCancelButtonClick() {
//        searchField.clear();
//        suggestedRoutinesGrid.getChildren().clear();
//        suggestedRoutineLabel.setText("Suggested Routines");
//
//        populateSuggestedRoutinesBox();
//
//        if (isSearchMode) {
//            isSearchMode = false;
//            toggleButtonsVisibility();
//        }
//    }
//
//    private void updateSearchResultLabel(List<String> searchResults) {
//        suggestedRoutinesGrid.getChildren().clear();
//        suggestedRoutineLabel.setText("Search Results");
//        addHeadings(suggestedRoutinesGrid);
//
//        int row = 1;
//        for (String result : searchResults) {
//            String[] resultParts = result.split(",");
//            for (int i = 1; i < resultParts.length; i++) {
//                Label label = new Label(resultParts[i]);
//                suggestedRoutinesGrid.add(label, i-1, row);
//            }
//
//            int routineId = Integer.parseInt(resultParts[0]); // Retrieve the routine ID
////            int routineId = ...; // Retrieve the routine ID from the result
//            Button playButton = new Button("Play");
//            playButton.setUserData(routineId); // Attach the routine ID to the play button
//            playButton.setOnAction(event -> {
//                int id = (int) ((Button) event.getSource()).getUserData(); // Retrieve the routine ID when clicked
//                try {
//                    playRoutine(id, event); // Call a method to play the routine with the given ID and pass the ActionEvent
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//
//            suggestedRoutinesGrid.add(playButton, 3, row); // Adjust the column index accordingly
//
//            row++;
//        }
//    }
//
//
//    private void toggleButtonsVisibility() {
//        searchButton.setVisible(!searchButton.isVisible());
//        cancelButton.setVisible(!cancelButton.isVisible());
//    }
//
//    @Override
//    public void updateView() {
//        if (searchField.getText().isEmpty()) {
//            populateSuggestedRoutinesBox();
//        } else {
//            try {
//                onSearchButtonClick();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
//


package org.trainer.interval_trainer.controller;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.trainer.interval_trainer.Model.*;
import org.trainer.interval_trainer.controller.manage_routines.RoutineListController;
import org.trainer.interval_trainer.controller.manage_routines.RoutinePreviewController;
import org.trainer.interval_trainer.controller.new_routine.BlockController;
import org.trainer.interval_trainer.controller.new_routine.GroupController;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class SearchController extends RoutineListController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label header;
    @FXML
    private Button homeButton;
    @FXML
    private Button createRoutinesButton;
    @FXML
    private Button searchPageButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button settingsButton;
    @FXML
    private GridPane suggestedRoutinesGrid;
    @FXML
    private Label suggestedRoutineLabel;
    @FXML
    private Button cancelButton;
    @FXML
    VBox routinesVBox;

    private boolean isSearchMode = false;
    @FXML
    private TextField searchField;

    private SqliteRoutinesDAO dao;

    @FXML
    protected void initialize() {
        dao = new SqliteRoutinesDAO();
        populateSuggestedRoutinesBox();
    }

//    private void addHeadings(GridPane grid) {
//        String[] headings = {"Name", "Created By", "Description"};
//        for (int i = 0; i < headings.length; i++) {
//            Label headingLabel = new Label(headings[i]);
//            headingLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-weight: bold;");
//            grid.add(headingLabel, i, 0);
//        }
//    }

//    private void populateSuggestedRoutinesBox() {
//        addHeadings(suggestedRoutinesGrid);
//        List<Routine> routines = dao.searchRoutine("");
//        updateRoutineGrid(routines);
//    }
//
//    private void updateRoutineGrid(List<Routine> routines) {
//        suggestedRoutinesGrid.getChildren().clear();
//        int row = 1;
//        for (Routine routine : routines) {
//            Label nameLabel = new Label(routine.getName().get());
//            Label createdByLabel = new Label(routine.getCreatedBy());
//            Label descriptionLabel = new Label(routine.getDescription().get());
//
//            suggestedRoutinesGrid.add(nameLabel, 0, row);
//            suggestedRoutinesGrid.add(createdByLabel, 1, row);
//            suggestedRoutinesGrid.add(descriptionLabel, 2, row);
//
//            Button playButton = new Button("Play");
//            playButton.setOnAction(event -> {
//                try {
//                    playRoutine(routine.getId().get(), event);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            suggestedRoutinesGrid.add(playButton, 5, row);
//
//            row++;
//        }
//    }
    private void populateSuggestedRoutinesBox() {
        updateView(); // Call updateView to display recent routines
    }

    private void updateRoutineList(List<Routine> routines) {
        routinesVBox.getChildren().clear();
        for (Routine routine : routines) {
            routinesVBox.getChildren().add(new RoutinePreviewController(routine, this));
//            if (!Objects.equals(Session.getInstance().getCurrentUser().getName(), routine.getCreatedBy())) {
//                routinesVBox.getChildren().remove(delete);
//                routinesVBox. getChildren().remove(edit);
//            }
        }

    }


//    private void playRoutine(int id, ActionEvent event) throws IOException {
//        Routine routine = dao.getRoutine(id);
//        RoutinePreviewController previewController = new RoutinePreviewController(routine, this);
//        previewController.play(event);
//    }

    @FXML
    protected void onSearchButtonClick() {
        String query = searchField.getText();

        if (!isSearchMode) {
            isSearchMode = true;
            toggleButtonsVisibility();
        }

        Task<List<Routine>> searchTask = new Task<>() {
            @Override
            protected List<Routine> call() throws Exception {
                return dao.searchRoutine(query);
            }
        };

        searchTask.setOnSucceeded(event -> {
            updateRoutineList(searchTask.getValue());
        });

        new Thread(searchTask).start();
    }

    @FXML
    protected void onCancelButtonClick() {
        searchField.clear();
        populateSuggestedRoutinesBox();

        if (isSearchMode) {
            isSearchMode = false;
            toggleButtonsVisibility();
        }
    }

    private void toggleButtonsVisibility() {
        searchButton.setVisible(!searchButton.isVisible());
        cancelButton.setVisible(!cancelButton.isVisible());
    }

//    @Override
//    public void updateView() {
//        SqliteRoutinesDAO dao = new SqliteRoutinesDAO();
//        List<Routine> routines = dao.getAllRoutines(Optional.ofNullable(Session.getInstance().getCurrentUser().getName()));
//        System.out.println(routines);
//
//        routinesVBox.getChildren().clear();
//        for (Routine routine : routines) {
//            routinesVBox.getChildren().add(new RoutinePreviewController(routine, this));
//        }
//    }
    @Override
    public void updateView() {
//        List<Routine> routines = dao.getAllRoutines();
//        updateRoutineList(routines); // Update routine list directly in the VBox
        int limit = 5; // Set the limit for the number of routines to retrieve
        List<Routine> routines = dao.getSomeRoutines(Optional.empty(), limit); // Fetch routines without specifying a user
        updateRoutineList(routines); // Update routine list directly in the VBox
    }

}
