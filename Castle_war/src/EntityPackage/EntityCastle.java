package EntityPackage;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityCastle {

	public void tick(); // 실행이 이 함수 안에서 이루어짐
	public void render(Graphics g); // 이미지를 그래픽으로 표현하는게 이 함수안에서 이루어짐
	public Rectangle getBounds(); // 유닛의 경계(나중에 적을 공격할때 이용)
	
	public double getX(); // 유닛의 x좌표를 받음
	public double getY(); // 유닛의 y좌표를 받음
	
	public int check_identifier(); // 아군인지 적군인지 확인
	public int check_enemynumber(); // 성안에 존재하는 적군의 수 파악
	public int check_unit_number(); // 성안에 대기중인 아군의 수 파악
	public String check_unit_number_String(); // 성 안에 존재하는 아군의 수 출력 시 사용 
	public void change_enemynumber(); // 성안에 존재하는 적군의 수 변경 - 추가
	public void change_unit_number(double num); // 아군이 빠져나가면서 성안에 존재하는 아군의 수 변경
	
}
