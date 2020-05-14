package ui;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import model.Arraylist;
import threads.ThreadTimekeeper;
import threads.ThreadLinked;
import threads.ThreadAbb;
import threads.ThreadAnimation;
import threads.ThreadArray;

public class RaceGUI {	
	@FXML
	private TextField number;

	@FXML 
	private ToggleGroup algorithm;
	
	@FXML
	private ToggleGroup mode;
	
	@FXML
	private ProgressBar progressArray;
	
	@FXML
	private ProgressBar progressLinked;
	
	@FXML
	private ProgressBar progressAbb;
	
	@FXML
	private TextField arrayTextF;
	
	@FXML
	private TextField linkedTexF;
	
	@FXML
	private TextField abbTextF;
	
	@FXML
	private Label timekeeper;
	
	@FXML
	private Circle circle1;
	
	@FXML
	private Circle circle2;
	
	@FXML
	private Button runButton;
	
	String alg = "";
	String modeAlg = "";
	Arraylist arrayL = new Arraylist(0, 0);
	
	private int generator = 0;
	
	private int hour;
	private int mins;
	private int secs; 
	private int milis;

	
	public void initialize() {
		algorithm.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (algorithm.getSelectedToggle() != null) {
					RadioButton selectedRadioButton = (RadioButton) algorithm.getSelectedToggle();
					alg = selectedRadioButton.getText();
				}
			}
		});

		mode.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (mode.getSelectedToggle() != null) {
					RadioButton selectedRadioButton = (RadioButton) mode.getSelectedToggle();
					modeAlg = selectedRadioButton.getText();
				}
			}
		});
	}
	
	public void circles(int one, int two, boolean change) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				circle1.setRadius(one);
				circle2.setRadius(two);
				if(change == true)
					circle1.toFront();
				else
					circle2.toFront();
			}
		});
	}
	public void timekeeper() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				++milis;
				if (milis == 100) {
					milis = 0;
					++secs;
				}
				if (secs == 60) {
					secs = 0;
					++mins;
				}
				if (mins == 60) {
					mins = 0;
					++hour;
				}
				String time = (hour <= 9 ? "0" : "") + hour + ":" + (mins <= 9 ? "0" : "") + mins + ":" + (secs <= 9 ? "0" : "") + secs + ":" + (milis <= 9 ? "0" : "") + milis;
				timekeeper.setText(time);
			}
		});
	}
	
	@FXML
	public void run(ActionEvent event) {
		try {
			generator = Integer.parseInt(number.getText());
			runButton.setDisable(true);
			
			if (generator < 1) {
				Alert added = new Alert(AlertType.WARNING);
				added.setTitle("INVALID NUMBER");
				added.setHeaderText("Number entered is not valid");
				added.setContentText("Please enter a number higher than 1");
				added.showAndWait();
			} else {
				if (alg.equals("")) {
					Alert added = new Alert(AlertType.WARNING);
					added.setTitle("INVALID SELECTION");
					added.setHeaderText("There is no selected algorithm option");
					added.setContentText("Please select one to start the race");
					added.showAndWait();
				} else {
					if (modeAlg.equals("")) {
						Alert added = new Alert(AlertType.WARNING);
						added.setTitle("INVALID SELECTION");
						added.setHeaderText("There is no selected mode option");
						added.setContentText("Please select one to start the race");
						added.showAndWait();
					} else {
						ArrayList<Long> numbers = numbers(generator);
						ThreadTimekeeper tch = new ThreadTimekeeper(this);
						tch.setDaemon(true);
						tch.start();
						ThreadAnimation tc = new ThreadAnimation(this);
						tc.setDaemon(true);
						tc.start();
						ThreadArray ta = new ThreadArray(this, numbers, alg, modeAlg);
						ta.setDaemon(true);
						ta.start();
						ThreadLinked tl = new ThreadLinked(this, numbers, alg, modeAlg);
						tl.setDaemon(true);
						tl.start();
						ThreadAbb tab = new ThreadAbb(this, numbers, alg, modeAlg);
						tab.setDaemon(true);
						tab.start();	
					}
				}
			}
		} catch (NumberFormatException e) {
			Alert added = new Alert(AlertType.WARNING);
			added.setTitle("INVALID NUMBER");
			added.setHeaderText("Number entered is not valid");
			added.setContentText("Please enter a valid number.");
			added.showAndWait();
		}
	}
	
	public ArrayList<Long> numbers(long data) {
		ArrayList<Long> numbe = new ArrayList<>();
		for (int i = 0; i < data; i++) {
			long num = (long) ((Math.random() * Long.MAX_VALUE) + Long.MIN_VALUE);
			numbe.add(num);
		}
		return numbe;
	}

	public void progressArray(int i) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				progressArray.setProgress((double) i / generator);
			}
		});
	}
	
	public void progressLinked(int i) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				progressLinked.setProgress((double) i / generator);
			}
		});
	}
	
	public void progressAbb(int i) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				progressAbb.setProgress((double) i / generator);
			}
		});
	}

	public double arrayProgress() {
		return progressArray.getProgress();
	}
	
	public double linkedProgress() {
		return progressLinked.getProgress();
	}
	
	public double abbProgress() {
		return progressAbb.getProgress();
	}
	
	public void setTimeArray(long time) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				arrayTextF.setText(String.valueOf(time));
			}
		});
	}
	
	public void setTimeLinked(long time) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				linkedTexF.setText(String.valueOf(time));
			}
		});
	}
	
	public void setTimeAbb(long time) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				abbTextF.setText(String.valueOf(time));
			}
		});
	}

}
