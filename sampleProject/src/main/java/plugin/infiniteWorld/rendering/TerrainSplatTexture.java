/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package plugin.infiniteWorld.rendering;

import java.util.List;

import com.brainless.alchemist.model.tempImport.RendererPlatform;
import com.jme3.material.Material;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;

import plugin.infiniteWorld.world.terrain.TerrainTexture;
import plugin.infiniteWorld.world.terrain.atlas.Atlas;
import util.LogUtil;

/**
 * @author Benoît
 */
public class TerrainSplatTexture extends Material {

	private final Atlas atlas;
	private final List<TerrainTexture> texturing;

	public boolean transp = false;
	
	public TerrainSplatTexture(Atlas atlas, List<TerrainTexture> texturing) {
		super(RendererPlatform.getAssetManager(), "matdefs/MyTerrainLighting.j3md");
		this.atlas = atlas;
		this.texturing = texturing;
		updateTextures();
		updateAlpha();
	}
	
	public void updateAlpha(){
		setTexture("AlphaMap", new Texture2D(new Image(Image.Format.RGBA8, atlas.getWidth(), atlas.getHeight(), atlas.getBuffer(0))));
		setTexture("AlphaMap_1", new Texture2D(new Image(Image.Format.RGBA8, atlas.getWidth(), atlas.getHeight(), atlas.getBuffer(1))));
	}
	
	public void updateTextures(){
		int index = 0;
		for (TerrainTexture tt : texturing) {
			String indexHint = index == 0? "" : "_" + index;
			Texture diffuse;
			double scale;
			if(tt == null){
				diffuse = RendererPlatform.getAssetManager().loadTexture("textures/trans.png");
				scale = 0;
			} else{
				try{
					diffuse = RendererPlatform.getAssetManager().loadTexture(tt.getDiffuse());
				} catch (Exception e) {
					LogUtil.warning("Diffuse map was not found : " + tt.getDiffuse() + " in TerrainTexture #"+index);
					diffuse = RendererPlatform.getAssetManager().loadTexture("textures/trans.png");
				}
				scale = tt.getScale();
				if(tt.getNormal() != null && !tt.getNormal().isEmpty()){
					Texture normal = RendererPlatform.getAssetManager().loadTexture(tt.getNormal());
					normal.setAnisotropicFilter(8);
					normal.setWrap(Texture.WrapMode.Repeat);
					setTexture("NormalMap" + indexHint, normal);
				}
			}
			diffuse.setWrap(Texture.WrapMode.Repeat);
			diffuse.setAnisotropicFilter(8);
			setTexture("DiffuseMap"+indexHint, diffuse);
			setFloat("DiffuseMap_" + index + "_scale", (float)scale);
			index++;
		}
	}
}
