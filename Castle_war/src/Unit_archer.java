import java.awt.Graphics;
import java.awt.Rectangle;

import EntityPackage.EntityCastle;
import EntityPackage.EntityUnit;
import animationPackage.Animation;


public class Unit_archer extends GameObject implements EntityUnit{ // �ú��� ���� Ŭ����
	 
	private double attack_point = 5.0; // ���ݷ�
	private double health_point = 100.0; // ü��
	private double speed_point = 100.0; // �ӵ�
	private int identifier = 1; // 1�̸� �Ʊ� ����, 2�̸� ���� ����
	private boolean move = true;
	
	private double enemy_x; // ���콺�� Ŭ���� ������ ���� x��ǥ
	private double enemy_y; // ���콺�� Ŭ���� ������ ���� y��ǥ
	private double ally_x; // ���콺�� Ŭ���� �Ʊ��� ���� x��ǥ
	private double ally_y; // ���콺�� Ŭ���� �Ʊ��� ���� y��ǥ
	private boolean first = true; // ���� �Լ��� tick()���� �ʱ�ȭ �������� �̿��� ����
	
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
	
	
	/* ���� �Լ� */
	public void tick() {
		if(first) {
			ally_x = x;
			ally_y = y;
			first = false;
		}
		
		if(health_point <= 0) {
			controller.removeEntity(this); // hp�� ������ ���� ���������
		}
		
		if(move) { // ������ �̵��ӵ� �˰��� �ʿ�
			x += (enemy_x - ally_x)*speed_point/((Math.pow((enemy_x - ally_x),2) + Math.pow((enemy_y - ally_y),2)));
			y += (enemy_y - ally_y)*speed_point/((Math.pow((enemy_x - ally_x),2) + Math.pow((enemy_y - ally_y),2)));
		}
		
		/* ���� �� ������ ������ */
		if(x <= 0 || x >= 640 || y <= 0 || y >= 480) {
			controller.removeEntity(this); // ���� ������� ����
		}
		
		/* ���ְ� ���� �浹���� ���� �Լ� */
		for(int i = 0; i < game.ea.size(); i++ ) {
			EntityCastle tempEnt = game.ea.get(i);
			if(Physics.Collision(this, tempEnt) && tempEnt.check_identifier() != this.check_identifier()) { // �ش� ���ְ� ������ ���� �浹�������
				game.ea.get(i).change_enemynumber();
				controller.removeEntity(this); // ���� ������� ����
				System.out.println("Collison Detected!");
				
			} else if(Physics.Collision(this, tempEnt) && tempEnt.check_identifier() == this.check_identifier()) { // �ش� ���ְ� �Ʊ� ���� �浹�������
				if(this.x > ally_x + 40.0 || this.x < ally_x - 40.0) {// ������ ��ġ���� ��� �������� ����
					if(this.y > ally_y + 40.0 || this.y < ally_y - 40.0) {
						controller.removeEntity(this);
						tempEnt.change_unit_number(-1.0);
					}
				}
			}
		}
		
		/* ���ְ� ������ �浹���� ���� �Լ� */
		for(int i = 0; i < game.eb.size(); i++ ) {
			EntityUnit tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt) && tempEnt.check_identifier() == 2) { // �ش� ���ְ� ������ ������ �浹�������
				move = false;
				tempEnt.change_hp(attack_point);
			} else {
				move = true;
			}
			
		}
		
		anim.runAnimation();
	}
	
	/* �̹��� ������ �ڵ� */
	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
	}
	
	/* ������ ��� */
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 48);
	}
	
	/* ������ x��ǥ�� ���� */
	public double getX() {
		return x;
	}
	
	/* ������ y��ǥ�� ���� */
	public double getY() {
		return y;
	}
	
	/* ������ �Ʊ����� �������� Ȯ��. �Ʊ��̸� 1, �����̸� 2*/
	public int check_identifier() {
		return identifier;
	}
	
	/* ���ݿ� ���� hp��� �Լ�*/
	public void change_hp(double num) {
		health_point -= num; // ���߿� ���µ� ����ؼ� ���
	}
	
	/*������*/
	void change_attack_point_with_interaction() {
	
	}

}
