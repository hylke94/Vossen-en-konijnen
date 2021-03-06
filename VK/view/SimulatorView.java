package VK.view;

import java.awt.Color;

public interface SimulatorView {
	@SuppressWarnings("rawtypes")
	void setColor(Class cl, Color color);
	boolean isViable(Field field);
	void showStatus(int step, Field field);
}