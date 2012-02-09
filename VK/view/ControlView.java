package VK.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import VK.actors.Bear;
import VK.actors.Fox;
import VK.actors.Grass;
import VK.actors.Hunter;
import VK.actors.Rabbit;
import VK.model.Model;

@SuppressWarnings("serial")
public class ControlView extends AbstractView implements ActionListener{
	
	//----- De labels en sliders voor de konijnen
	
	private JLabel lblBreedingAgerabbit, lblMaxAgerabbit, lblBreedingProbabilityrabbit, 
					lblMaxLitterSizerabbit, lblFoodValuerabbit;
	private JSlider slBreedingAgerabbit, slMaxAgerabbit, slBreedingProbabilityrabbit, 
					slMaxLitterSizerabbit, slFoodValuerabbit;
	
	//----- De labels en sliders voor de vossen
	
	private JLabel lblBreedingAgefox, lblMaxAgefox, lblBreedingProbabilityfox, 
					lblMaxLitterSizefox, lblFoodValuefox;
	private JSlider slBreedingAgefox, slMaxAgefox, slBreedingProbabilityfox, 
					slMaxLitterSizefox, slFoodValuefox;
	
	//----- De labels en sliders voor de beren
	
	private JLabel lblBreedingAgebear, lblMaxAgebear, lblBreedingProbabilitybear, 
					lblMaxLitterSizebear, lblFoodValuebear;
	private JSlider slBreedingAgebear, slMaxAgebear, slBreedingProbabilitybear, 
					slMaxLitterSizebear, slFoodValuebear;
	
	//----- De labels en sliders voor de jagers
	
	private JLabel lblMaxKillshunter;
	private JSlider slMaxKillshunter;
	
	//----- De labels en sliders voor het gras
	
	private JLabel lblBreedingProbabilitygrass, lblMaxLitterSizegrass;
	private JSlider slBreedingProbabilitygrass, slMaxLitterSizegrass;
	
	//----- De knop om de simulatie te herstarten met de niewe instellingen
	
	private JButton btnReset;

	public ControlView(Model newModel) {
		super(newModel);
		
		//----- Het JPanel voor de instellingen van de konijnen
		
		JPanel rabbitPanel = new JPanel();
			rabbitPanel.setBorder(makeBorder("Rabbit"));
			rabbitPanel.setLayout(new GridLayout(0,1));
			
				JPanel rabbitPanel2 = new JPanel();
				rabbitPanel2.setLayout(new GridLayout(0,2));
				
				this.lblBreedingAgerabbit = new JLabel("Breeding age:");
					this.slBreedingAgerabbit = new JSlider(1, 10, Rabbit.breedingAge);
					this.slBreedingAgerabbit.setMajorTickSpacing(1);
					this.slBreedingAgerabbit.setMinorTickSpacing(1);
					this.slBreedingAgerabbit.setPaintTicks(true);
					this.slBreedingAgerabbit.setPaintLabels(true);
				this.lblMaxAgerabbit = new JLabel("Max age:");
					this.slMaxAgerabbit = new JSlider(30, 50, Rabbit.maxAge);
					this.slMaxAgerabbit.setMajorTickSpacing(5);
					this.slMaxAgerabbit.setMinorTickSpacing(1);
					this.slMaxAgerabbit.setPaintTicks(true);
					this.slMaxAgerabbit.setPaintLabels(true);
				this.lblBreedingProbabilityrabbit = new JLabel("Breeding probability:");
					this.slBreedingProbabilityrabbit = new JSlider(0, 100, Rabbit.getBreedingProbabilityInt());
					this.slBreedingProbabilityrabbit.setMajorTickSpacing(20);
					this.slBreedingProbabilityrabbit.setMinorTickSpacing(10);
					this.slBreedingProbabilityrabbit.setPaintTicks(true);
					this.slBreedingProbabilityrabbit.setPaintLabels(true);
				this.lblMaxLitterSizerabbit = new JLabel("Max litter size:");
					this.slMaxLitterSizerabbit = new JSlider(1, 10, Rabbit.maxLitterSize);
					this.slMaxLitterSizerabbit.setMajorTickSpacing(1);
					this.slMaxLitterSizerabbit.setMinorTickSpacing(1);
					this.slMaxLitterSizerabbit.setPaintTicks(true);
					this.slMaxLitterSizerabbit.setPaintLabels(true);
				this.lblFoodValuerabbit = new JLabel("Food value:");
					this.slFoodValuerabbit = new JSlider(1, 20, Rabbit.foodValue);
					this.slFoodValuerabbit.setMajorTickSpacing(2);
					this.slFoodValuerabbit.setMinorTickSpacing(1);
					this.slFoodValuerabbit.setPaintTicks(true);
					this.slFoodValuerabbit.setPaintLabels(true);
				
				rabbitPanel2.add(this.lblBreedingAgerabbit);
				rabbitPanel2.add(this.slBreedingAgerabbit);
				rabbitPanel2.add(this.lblMaxAgerabbit);
				rabbitPanel2.add(this.slMaxAgerabbit);
				rabbitPanel2.add(this.lblBreedingProbabilityrabbit);
				rabbitPanel2.add(this.slBreedingProbabilityrabbit);
				rabbitPanel2.add(this.lblMaxLitterSizerabbit);
				rabbitPanel2.add(this.slMaxLitterSizerabbit);
				rabbitPanel2.add(this.lblFoodValuerabbit);
				rabbitPanel2.add(this.slFoodValuerabbit);
		
			rabbitPanel.add(rabbitPanel2);
			
		//----- Het JPanel voor de instellingen van de vossen
			
		JPanel foxPanel = new JPanel();
			foxPanel.setBorder(makeBorder("Fox"));
			foxPanel.setLayout(new GridLayout(0,1));
			
				JPanel foxPanel2 = new JPanel();
				foxPanel2.setLayout(new GridLayout(0,2));
				
				this.lblBreedingAgefox = new JLabel("Breeding age:");
					this.slBreedingAgefox = new JSlider(1, 10, Fox.breedingAge);
					this.slBreedingAgefox.setMajorTickSpacing(1);
					this.slBreedingAgefox.setMinorTickSpacing(1);
					this.slBreedingAgefox.setPaintTicks(true);
					this.slBreedingAgefox.setPaintLabels(true);
				this.lblMaxAgefox = new JLabel("Max age:");
					this.slMaxAgefox = new JSlider(30, 100, Fox.maxAge);
					this.slMaxAgefox.setMajorTickSpacing(10);
					this.slMaxAgefox.setMinorTickSpacing(5);
					this.slMaxAgefox.setPaintTicks(true);
					this.slMaxAgefox.setPaintLabels(true);
				this.lblBreedingProbabilityfox = new JLabel("Breeding probability:");
					this.slBreedingProbabilityfox = new JSlider(0, 100, Fox.getBreedingProbabilityInt());
					this.slBreedingProbabilityfox.setMajorTickSpacing(20);
					this.slBreedingProbabilityfox.setMinorTickSpacing(10);
					this.slBreedingProbabilityfox.setPaintTicks(true);
					this.slBreedingProbabilityfox.setPaintLabels(true);
				this.lblMaxLitterSizefox = new JLabel("Max litter size:");
					this.slMaxLitterSizefox = new JSlider(1, 10, Fox.maxLitterSize);
					this.slMaxLitterSizefox.setMajorTickSpacing(1);
					this.slMaxLitterSizefox.setMinorTickSpacing(1);
					this.slMaxLitterSizefox.setPaintTicks(true);
					this.slMaxLitterSizefox.setPaintLabels(true);
				this.lblFoodValuefox = new JLabel("Food value:");
					this.slFoodValuefox = new JSlider(1, 20, Fox.foodValue);
					this.slFoodValuefox.setMajorTickSpacing(2);
					this.slFoodValuefox.setMinorTickSpacing(1);
					this.slFoodValuefox.setPaintTicks(true);
					this.slFoodValuefox.setPaintLabels(true);
				
				foxPanel2.add(this.lblBreedingAgefox);
				foxPanel2.add(this.slBreedingAgefox);
				foxPanel2.add(this.lblMaxAgefox);
				foxPanel2.add(this.slMaxAgefox);
				foxPanel2.add(this.lblBreedingProbabilityfox);
				foxPanel2.add(this.slBreedingProbabilityfox);
				foxPanel2.add(this.lblMaxLitterSizefox);
				foxPanel2.add(this.slMaxLitterSizefox);
				foxPanel2.add(this.lblFoodValuefox);
				foxPanel2.add(this.slFoodValuefox);
		
			foxPanel.add(foxPanel2);
			
		//----- Het JPanel voor de instellingen van de beren
				
		JPanel bearPanel = new JPanel();
			bearPanel.setBorder(makeBorder("Bear"));
			bearPanel.setLayout(new GridLayout(0,1));
			
				JPanel bearPanel2 = new JPanel();
				bearPanel2.setLayout(new GridLayout(0,2));
				
				this.lblBreedingAgebear = new JLabel("Breeding age:");
					this.slBreedingAgebear = new JSlider(1, 20, Bear.breedingAge);
					this.slBreedingAgebear.setMajorTickSpacing(2);
					this.slBreedingAgebear.setMinorTickSpacing(1);
					this.slBreedingAgebear.setPaintTicks(true);
					this.slBreedingAgebear.setPaintLabels(true);
				this.lblMaxAgebear = new JLabel("Max age:");
					this.slMaxAgebear = new JSlider(30, 100, Bear.maxAge);
					this.slMaxAgebear.setMajorTickSpacing(10);
					this.slMaxAgebear.setMinorTickSpacing(5);
					this.slMaxAgebear.setPaintTicks(true);
					this.slMaxAgebear.setPaintLabels(true);
				this.lblBreedingProbabilitybear = new JLabel("Breeding probability:");
					this.slBreedingProbabilitybear = new JSlider(0, 100, Bear.getBreedingProbabilityInt());
					this.slBreedingProbabilitybear.setMajorTickSpacing(20);
					this.slBreedingProbabilitybear.setMinorTickSpacing(10);
					this.slBreedingProbabilitybear.setPaintTicks(true);
					this.slBreedingProbabilitybear.setPaintLabels(true);
				this.lblMaxLitterSizebear = new JLabel("Max litter size:");
					this.slMaxLitterSizebear = new JSlider(1, 10, Bear.maxLitterSize);
					this.slMaxLitterSizebear.setMajorTickSpacing(1);
					this.slMaxLitterSizebear.setMinorTickSpacing(1);
					this.slMaxLitterSizebear.setPaintTicks(true);
					this.slMaxLitterSizebear.setPaintLabels(true);
				this.lblFoodValuebear = new JLabel("Food value:");
					this.slFoodValuebear = new JSlider(1, 20, Bear.foodValue);
					this.slFoodValuebear.setMajorTickSpacing(2);
					this.slFoodValuebear.setMinorTickSpacing(1);
					this.slFoodValuebear.setPaintTicks(true);
					this.slFoodValuebear.setPaintLabels(true);
				
				bearPanel2.add(this.lblBreedingAgebear);
				bearPanel2.add(this.slBreedingAgebear);
				bearPanel2.add(this.lblMaxAgebear);
				bearPanel2.add(this.slMaxAgebear);
				bearPanel2.add(this.lblBreedingProbabilitybear);
				bearPanel2.add(this.slBreedingProbabilitybear);
				bearPanel2.add(this.lblMaxLitterSizebear);
				bearPanel2.add(this.slMaxLitterSizebear);
				bearPanel2.add(this.lblFoodValuebear);
				bearPanel2.add(this.slFoodValuebear);
		
			bearPanel.add(bearPanel2);
			
		//----- Het JPanel voor de instellingen van de jagers
					
		JPanel hunterPanel = new JPanel();
			hunterPanel.setBorder(makeBorder("Hunter"));
			hunterPanel.setLayout(new GridLayout(0,1));
			
				JPanel hunterPanel2 = new JPanel();
				hunterPanel2.setLayout(new GridLayout(0,2));
				
				this.lblMaxKillshunter = new JLabel("Max kills:");
					this.slMaxKillshunter = new JSlider(10, 50, Hunter.maxKills);
					this.slMaxKillshunter.setMajorTickSpacing(10);
					this.slMaxKillshunter.setMinorTickSpacing(5);
					this.slMaxKillshunter.setPaintTicks(true);
					this.slMaxKillshunter.setPaintLabels(true);
				
				hunterPanel2.add(this.lblMaxKillshunter);
				hunterPanel2.add(this.slMaxKillshunter);
		
			hunterPanel.add(hunterPanel2);
			
		//----- Het JPanel voor de instellingen van het gras
						
		JPanel grassPanel = new JPanel();
			grassPanel.setBorder(makeBorder("Grass"));
			grassPanel.setLayout(new GridLayout(0,1));
			
				JPanel grassPanel2 = new JPanel();
				grassPanel2.setLayout(new GridLayout(0,2));
				this.lblBreedingProbabilitygrass = new JLabel("Breeding probability:");
					this.slBreedingProbabilitygrass = new JSlider(0, 100, Grass.getBreedingProbabilityInt());
					this.slBreedingProbabilitygrass.setMajorTickSpacing(20);
					this.slBreedingProbabilitygrass.setMinorTickSpacing(10);
					this.slBreedingProbabilitygrass.setPaintTicks(true);
					this.slBreedingProbabilitygrass.setPaintLabels(true);
				this.lblMaxLitterSizegrass = new JLabel("Max litter size:");
					this.slMaxLitterSizegrass = new JSlider(1, 10, Grass.maxLitterSize);
					this.slMaxLitterSizegrass.setMajorTickSpacing(1);
					this.slMaxLitterSizegrass.setMinorTickSpacing(1);
					this.slMaxLitterSizegrass.setPaintTicks(true);
					this.slMaxLitterSizegrass.setPaintLabels(true);
				
				grassPanel2.add(this.lblBreedingProbabilitygrass);
				grassPanel2.add(this.slBreedingProbabilitygrass);
				grassPanel2.add(this.lblMaxLitterSizegrass);
				grassPanel2.add(this.slMaxLitterSizegrass);
		
			grassPanel.add(grassPanel2);
			
		//----- Het JPanel met de reset-button
		
		JPanel panel = new JPanel();
		this.btnReset = new JButton("Reset de simulatie met de nieuwe instellingen.");
		this.btnReset.addActionListener(this);
		panel.add(this.btnReset);
		
		//----- Het JPanel om de instellingen van de jagers en het gras bij elkaar in "één hokje" te krijgen
		
		JPanel hunterAndGrass = new JPanel();
		hunterAndGrass.setLayout(new BorderLayout());
		hunterAndGrass.add(hunterPanel, BorderLayout.NORTH);
		hunterAndGrass.add(grassPanel, BorderLayout.CENTER);
		
		//----- Het JPanel waar alle JPanels met de instellingen in komen
		
		JPanel controlPanel = new JPanel();
			controlPanel.setLayout(new GridLayout(0,2));
				controlPanel.add(rabbitPanel);
				controlPanel.add(foxPanel);
				controlPanel.add(bearPanel);
				controlPanel.add(hunterAndGrass);
		
		//----- Zet alle JPanels n ht algemene JPanel dat de gebruiker ziet
		
		setLayout(new BorderLayout());
		add(controlPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
	}
	
	/**
	 * Een methode om een rand (om bijvoorbeeld een JPanel) te krijgen.
	 * In de bovenste rand komt in het midden de title te staan.
	 * 
	 * @param title - De titel die in de rand komt te staan
	 * @return Border - de border/rand met titel
	 */
	public static Border makeBorder(String title){
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder titledborder = BorderFactory.createTitledBorder(loweredetched, title);
		titledborder.setTitleJustification(TitledBorder.CENTER);
		
		return titledborder;
	}
	
	/**
	 * Voert de acties uit nadat er op een knop is gedrukt waar een ActionListeren aan verbonden is
	 * 
	 * @param ae - Het ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == this.btnReset){
			
			int newValue;
	    	
			//----- Rabbit instellingen wijzigen
			
	    	newValue = this.slBreedingAgerabbit.getValue();
	    		Rabbit.breedingAge = newValue;
	    	newValue = this.slMaxAgerabbit.getValue();
	    		Rabbit.maxAge = newValue;
	    	newValue = this.slBreedingProbabilityrabbit.getValue();
	    		Rabbit.setBreedingProbabilityInt(newValue);
	    	newValue = this.slMaxLitterSizerabbit.getValue();
	    		Rabbit.maxLitterSize = newValue;
	    	newValue = this.slFoodValuerabbit.getValue();
	    		Rabbit.foodValue = newValue;
		    	
			//----- Fox instellingen wijzigen
			
	    	newValue = this.slBreedingAgefox.getValue();
	    		Fox.breedingAge = newValue;
	    	newValue = this.slMaxAgefox.getValue();
	    		Fox.maxAge = newValue;
	    	newValue = this.slBreedingProbabilityfox.getValue();
	    		Fox.setBreedingProbabilityInt(newValue);
	    	newValue = this.slMaxLitterSizefox.getValue();
	    		Fox.maxLitterSize = newValue;
	    	newValue = this.slFoodValuefox.getValue();
	    		Fox.foodValue = newValue;
			    	
			//----- Bear instellingen wijzigen
			
	    	newValue = this.slBreedingAgebear.getValue();
	    		Bear.breedingAge = newValue;
	    	newValue = this.slMaxAgebear.getValue();
	    		Bear.maxAge = newValue;
	    	newValue = this.slBreedingProbabilitybear.getValue();
	    		Bear.setBreedingProbabilityInt(newValue);
	    	newValue = this.slMaxLitterSizebear.getValue();
	    		Bear.maxLitterSize = newValue;
	    	newValue = this.slFoodValuebear.getValue();
	    		Bear.foodValue = newValue;
				    	
			//----- Hunter instellingen wijzigen
			
	    	newValue = this.slMaxKillshunter.getValue();
	    		Hunter.maxKills = newValue;
					    	
			//----- Grass instellingen wijzigen
			
	    	newValue = this.slBreedingProbabilitygrass.getValue();
	    		Grass.setBreedingProbabilityInt(newValue);
	    	newValue = this.slMaxLitterSizegrass.getValue();
	    		Grass.maxLitterSize = newValue;
	    		
	    	//----- Reset de simulatie met de nieuwe instellingen
	    		
	    	if (this.model.run) this.model.stop();
	    	this.model.reset();
		}
	}
	
	@Override
	public void preparePaint() {
		// TODO Auto-generated method stub
	}
}