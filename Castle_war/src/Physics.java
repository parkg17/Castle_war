import EntityPackage.EntityCastle;
import EntityPackage.EntityUnit;

public class Physics {
	
	public static boolean Collision(EntityUnit entu, EntityCastle entc) {
		if(entu.getBounds().intersects(entc.getBounds())) {
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityUnit entu, EntityUnit ente) {
		if(entu.getBounds().intersects(ente.getBounds())) {
			return true;
		}
		return false;
	}
}