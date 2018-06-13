package EntityPackage;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityUnit {
	
	public void tick(); // ������ �� �Լ��ȿ��� �̷���� 
	public void render(Graphics g); // �̹����� �׷������� ǥ���ϴ°� �� �Լ��ȿ��� �̷����
	public Rectangle getBounds(); // ������ ���(���߿� ���� �����Ҷ� �̿�)
	
	public double getX(); // ������ x��ǥ�� ����
	public double getY(); // ������ y��ǥ�� ����
	
	public int check_identifier(); // �Ʊ����� �������� Ȯ��
	public void change_hp(double num); // ���������ν� hp�� ����

}
