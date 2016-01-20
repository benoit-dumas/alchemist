package view.controls.toolEditor;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.world.PencilToolPresenter;
import view.controls.custom.IconToggleButton;

public class PencilEditor extends BorderPane {
	private final PencilToolPresenter tool;
	private final ToggleGroup shapeGroup = new ToggleGroup();
	private final ToggleGroup modeGroup = new ToggleGroup();
	
	public PencilEditor(PencilToolPresenter tool) {
		this.tool = tool;
		setLeft(new VBox(getCircleButton(),
				getSquareButton(),
				getDiamondButton(),
				getAirbrushButton(),
				getRoughButton(),
				getNoiseButton()));
		
		setCenter(getSizeSlider());
		setRight(getStrengthSlider());

	}
	private ToggleButton getCircleButton(){
		IconToggleButton res = new IconToggleButton("assets/textures/editor/circle_icon.png", "Circle");
		res.selectedProperty().bindBidirectional(tool.getCircleProperty());
		res.setToggleGroup(shapeGroup);
		return res;
	}
	private ToggleButton getSquareButton(){
		IconToggleButton res = new IconToggleButton("assets/textures/editor/square_icon.png", "Square");
		res.selectedProperty().bindBidirectional(tool.getSquareProperty());
		res.setToggleGroup(shapeGroup);
		return res;
	}
	private ToggleButton getDiamondButton(){
		IconToggleButton res = new IconToggleButton("assets/textures/editor/diamond_icon.png", "Diamond");
		res.selectedProperty().bindBidirectional(tool.getDiamondProperty());
		res.setToggleGroup(shapeGroup);
		return res;
	}
	
	private ToggleButton getAirbrushButton(){
		IconToggleButton res = new IconToggleButton("assets/textures/editor/airbrush_icon.png", "Airbrush");
		res.selectedProperty().bindBidirectional(tool.getAirbrushProperty());
		res.setToggleGroup(modeGroup);
		return res;
	}
	private ToggleButton getRoughButton(){
		IconToggleButton res = new IconToggleButton("assets/textures/editor/rough_icon.png", "Rough");
		res.selectedProperty().bindBidirectional(tool.getRoughProperty());
		res.setToggleGroup(modeGroup);
		return res;
	}
	private ToggleButton getNoiseButton(){
		IconToggleButton res = new IconToggleButton("assets/textures/editor/noise_icon.png", "Noise");
		res.selectedProperty().bindBidirectional(tool.getNoiseProperty());
		res.setToggleGroup(modeGroup);
		return res;
	}
	
	private Node getSizeSlider(){
		VBox res = new VBox();
		res.setMaxWidth(30);
		res.setMaxHeight(Double.MAX_VALUE);
		res.getChildren().add(new Label("Size"));
		Slider slider = new Slider(1, PencilToolPresenter.MAX_SIZE, 4);
		slider.setOrientation(Orientation.VERTICAL);
		slider.valueProperty().bindBidirectional(tool.getSizeProperty());
		res.getChildren().add(slider);
		return res;
	}
	
	private Node getStrengthSlider(){
		VBox res = new VBox();
		res.setMaxWidth(30);
		res.setMaxHeight(Double.MAX_VALUE);
		res.getChildren().add(new Label("Strength"));
		Slider slider = new Slider(0, 1, 0.3);
		slider.setOrientation(Orientation.VERTICAL);
		slider.valueProperty().bindBidirectional(tool.getStrengthProperty());
		res.getChildren().add(slider);
		return res;
	}	
	
}
