package view.typeEditor;

import java.lang.reflect.Field;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import util.LogUtil;
import util.event.ComponentPropertyChanged;
import util.event.EventManager;

import com.simsilica.es.EntityComponent;

public class DoubleEditorCtrl {
	private EntityComponent comp;
	private Field field;
	
	@FXML
	private TextField value;

	@FXML
	private void initialize() {
		
	}
	
	public void setField(EntityComponent comp, Field field){
		this.comp = comp;
		this.field = field;
		try {
			value.setText(Double.toString(field.getDouble(comp)));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void changeValue(){
		EventManager.post(new ComponentPropertyChanged(comp, field.getName(), Double.parseDouble(value.getText())));
	}
}
