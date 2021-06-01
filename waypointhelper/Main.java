package waypointhelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.src.BaseMod;

@Mod(name="WaypointHelper", version="1.0", modid="WaypointHelper")
public class Main extends BaseMod{
	
	public static Logger wphLog = Logger.getLogger("WaypointHelper");
	
	@Override
	public String getVersion() {
		return "1.0";
	}
	

	@Override
	public void load() {

	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		wphLog.setParent(FMLLog.getLogger());
		wphLog.info("Waypoint Helper v1.0 initialized");		
		File f = new File(event.getModConfigurationDirectory() + "/../mods/rei_minimap/kamino.a-centauri.com..DIM0.points");
		if(!f.exists() && !f.isDirectory()) { 
			try (BufferedInputStream in = new BufferedInputStream(new URL("https://cdn.rgbcraft.com/utilities/waypointhelper/kamino.a-centauri.com..DIM0.points").openStream());
					  FileOutputStream fileOutputStream = new FileOutputStream(event.getModConfigurationDirectory() + "/../mods/rei_minimap/kamino.a-centauri.com..DIM0.points")) {
					    byte dataBuffer[] = new byte[1024];
					    int bytesRead;
					    while ((bytesRead = in.read(dataBuffer, 0, 1024)) >=  0) {
					        fileOutputStream.write(dataBuffer, 0, bytesRead);
					    }
						wphLog.info("Waypoint file downloaded!");	
					} catch (IOException e) {
						wphLog.severe("ERROR DOWNLOADING FILE: " + e);
					}
		} else {
			wphLog.info("Waypoints already downloaded!");	
		}
	}
	
	
	
	@Init
	public void init(FMLInitializationEvent event) {
	}
	
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
	}
}
