package model.ES.component.hierarchy;

import util.geometry.geom3d.Point3D;
import util.math.Angle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simsilica.es.EntityComponent;
import com.simsilica.es.EntityId;

public class PlanarStanceControl implements EntityComponent {
	public final Point3D localPosition;
	public final Angle localOrientation;
	
	public PlanarStanceControl() {
		localPosition = Point3D.ORIGIN;
		localOrientation = new Angle(0);
	}
	
	public PlanarStanceControl(@JsonProperty("localPosition")Point3D localPosition,
			@JsonProperty("localOrientation")Angle localOrientation) {
		this.localPosition = localPosition;
		this.localOrientation = localOrientation;
	}

	public Point3D getLocalPosition() {
		return localPosition;
	}

	public Angle getLocalOrientation() {
		return localOrientation;
	}
}