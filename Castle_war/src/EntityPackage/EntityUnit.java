package EntityPackage;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityUnit {
	
	public void tick(); // 실행이 이 함수안에서 이루어짐 
	public void render(Graphics g); // 이미지를 그래픽으로 표현하는게 이 함수안에서 이루어짐
	public Rectangle getBounds(); // 유닛의 경계(나중에 적을 공격할때 이용)
	
	public double getX(); // 유닛의 x좌표를 받음
	public double getY(); // 유닛의 y좌표를 받음
	
	public int check_identifier(); // 아군인지 적군인지 확인
	public void change_hp(double num); // 공격함으로써 hp를 깎음

}
