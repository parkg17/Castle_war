import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import animationPackage.Animation;
import EntityPackage.EntityCastle;
import EntityPackage.EntityUnit;

public class Castle extends GameObject implements EntityCastle{

	private int enemy_unit_number_in = 0;
	private int identifier = 1;
	
	private double creating_unit_number = 0.05;
	private double unit_number = 10.0;
	private double limit_number_of_unit = 50.0;
	
	
	private Textures tex;
	private Controller controller;
	private Game game;
	Animation anim;
	
	public Castle(double x, double y, Textures tex, Controller controller, Game game)
	{
		super(x, y);
		this.tex = tex;
		this.controller = controller;
		this.game = game;
		
		anim = new Animation(3 , tex.castle[0], tex.castle[1]);
	}
	public void tick() {
		for(int i = 0; i < game.ea.size(); i++) {
			EntityCastle tempEnt = game.ea.get(i);
		}

		if(unit_number < limit_number_of_unit) {
			unit_number += creating_unit_number;
		}
		
		anim.runAnimation();
	}
	
	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 64);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int check_identifier() {
		return identifier;
	}
	
	public int check_enemynumber() {
		return enemy_unit_number_in;
	}
	
	public int check_unit_number() {
		return (int)unit_number;
	}
	
	public String check_unit_number_String() {
		int num = (int)(unit_number);
		if(num < 0)
			num = 0;
		String s = String.valueOf(num);
		return s;
	}
	
	public void change_enemynumber() {
		enemy_unit_number_in +=1;
	}
	
	public void change_unit_number(double num) {
		unit_number -= num;
	}
}
