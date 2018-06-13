import java.awt.Graphics;
import java.awt.Rectangle;

import EntityPackage.EntityCastle;
import EntityPackage.EntityUnit;
import animationPackage.Animation;


public class Unit_archer extends GameObject implements EntityUnit{ // 궁병에 대한 클래스
	 
	private double attack_point = 5.0; // 공격력
	private double health_point = 100.0; // 체력
	private double speed_point = 100.0; // 속도
	private int identifier = 1; // 1이면 아군 유닛, 2이면 상대방 유닛
	private boolean move = true;
	
	private double enemy_x; // 마우스를 클릭한 상대방의 성의 x좌표
	private double enemy_y; // 마우스를 클릭한 상대방의 성의 y좌표
	private double ally_x; // 마우스를 클릭한 아군의 성의 x좌표
	private double ally_y; // 마우스를 클릭한 아군의 성의 y좌표
	private boolean first = true; // 실행 함수인 tick()에서 초기화 조건으로 이용할 변수
	
	private Textures tex;
	private Game game;
	private Controller controller;
	
	Animation anim;
	
	public Unit_archer(double x, double y, double enemy_x, double enemy_y, Textures tex, Controller controller, Game game)
	{
		super(x, y);
		this.enemy_x = enemy_x;
		this.enemy_y = enemy_y;
		this.tex = tex;
		this.controller = controller;
		this.game = game;
		
		anim = new Animation(3, tex.archer[0], tex.archer[1]);
	}
	
	
	/* 실행 함수 */
	public void tick() {
		if(first) {
			ally_x = x;
			ally_y = y;
			first = false;
		}
		
		if(health_point <= 0) {
			controller.removeEntity(this); // hp가 없으면 유닛 사라지도록
		}
		
		if(move) { // 유닛의 이동속도 알고리즘 필요
			x += (enemy_x - ally_x)*speed_point/((Math.pow((enemy_x - ally_x),2) + Math.pow((enemy_y - ally_y),2)));
			y += (enemy_y - ally_y)*speed_point/((Math.pow((enemy_x - ally_x),2) + Math.pow((enemy_y - ally_y),2)));
		}
		
		/* 만약 맵 밖으로 나가면 */
		if(x <= 0 || x >= 640 || y <= 0 || y >= 480) {
			controller.removeEntity(this); // 유닛 사라지게 만듬
		}
		
		/* 유닛과 성이 충돌했을 때의 함수 */
		for(int i = 0; i < game.ea.size(); i++ ) {
			EntityCastle tempEnt = game.ea.get(i);
			if(Physics.Collision(this, tempEnt) && tempEnt.check_identifier() != this.check_identifier()) { // 해당 유닛과 적군의 성이 충돌했을경우
				game.ea.get(i).change_enemynumber();
				controller.removeEntity(this); // 유닛 사라지게 만듬
				System.out.println("Collison Detected!");
				
			} else if(Physics.Collision(this, tempEnt) && tempEnt.check_identifier() == this.check_identifier()) { // 해당 유닛과 아군 성이 충돌했을경우
				if(this.x > ally_x + 40.0 || this.x < ally_x - 40.0) {// 생성된 위치에서 벗어난 성에서만 적용
					if(this.y > ally_y + 40.0 || this.y < ally_y - 40.0) {
						controller.removeEntity(this);
						tempEnt.change_unit_number(-1.0);
					}
				}
			}
		}
		
		/* 유닛과 유닛이 충돌했을 때의 함수 */
		for(int i = 0; i < game.eb.size(); i++ ) {
			EntityUnit tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt) && tempEnt.check_identifier() == 2) { // 해당 유닛과 적군의 유닛이 충돌했을경우
				move = false;
				tempEnt.change_hp(attack_point);
			} else {
				move = true;
			}
			
		}
		
		anim.runAnimation();
	}
	
	/* 이미지 렌더링 코드 */
	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
	}
	
	/* 유닛의 경계 */
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 48);
	}
	
	/* 유닛의 x좌표를 받음 */
	public double getX() {
		return x;
	}
	
	/* 유닛의 y좌표를 받음 */
	public double getY() {
		return y;
	}
	
	/* 유닛이 아군인지 적군인지 확인. 아군이면 1, 적군이면 2*/
	public int check_identifier() {
		return identifier;
	}
	
	/* 공격에 따른 hp계산 함수*/
	public void change_hp(double num) {
		health_point -= num; // 나중에 방어력도 고려해서 계산
	}
	
	/*뭐였지*/
	void change_attack_point_with_interaction() {
	
	}

}
