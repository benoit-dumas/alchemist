package presentation.inspector.propertyEditor;

import java.beans.PropertyDescriptor;

import presenter.InspectorPresenter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import presentation.util.Consumer3;
import util.LogUtil;
import util.geometry.geom2d.Point2D;

import com.simsilica.es.EntityComponent;

public class DoubleEditor extends PropertyEditor{
	
	TextField valueField;
	
	public DoubleEditor(EntityComponent comp, PropertyDescriptor pd, Consumer3<EntityComponent, String, Object> updateCompFunction) {
		super(comp, pd, updateCompFunction);
	}

	@Override
	protected void createEditor() {
		valueField = new TextField();
		valueField.setMaxWidth(100);
		valueField.addEventHandler(ActionEvent.ACTION, e -> applyChange(e));
		valueField.focusedProperty().addListener(e -> setEditionMode());
		setCenter(valueField);
	}

	@Override
	protected Object getPropertyValue() {
		return Double.parseDouble(valueField.getText());  
	}

	@Override
	protected void setPropertyValue(Object o) {
		double v = (Double)o;
		valueField.setText(Double.toString(v));
	}
	
	

}