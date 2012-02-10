package VK.saver;

import java.awt.Toolkit;

import VK.model.actors.Bear;
import VK.model.actors.Fox;
import VK.model.actors.Grass;
import VK.model.actors.Hunter;
import VK.model.actors.Rabbit;
// "C:/Documents and Settings/studxp/My Documents/henkie.txt"
/**
 * Write a description of class Values here.
 * 
 * @author Ole Vrijenhoek
 */
public class Values
{
    // instancevariables holding data about the animals and hunters
    private byte[] foxes;
    private byte[] rabbits;
    private byte[] bears;
    private byte[] hunters;
    
    // instancevariables holding the values of the sliders
    private byte[][] sliders;
    
    // instancevariable holding data about the loaded counts
    private int[] counts = new int[4];

    /**
     * Constructor voor het aanmaken van een instantie
     * @param foxInput Het aantal vossen
     * @param rabbitInput Het aantal konijnen
     * @param bearInput Het aantal beren
     * @param hunterInput Het aantal hunters
     */
    public Values(int foxInput, int rabbitInput, int bearInput, int hunterInput)
    {
       this.foxes   = this.build(foxInput/128, foxInput%128);
       this.rabbits = this.build(rabbitInput/128, rabbitInput%128);
       this.bears   = this.build(bearInput/128, bearInput%128);
       this.hunters = this.build(hunterInput/128, hunterInput%128);
       
       this.sliders = this.buildSliders();
    }
    
    /**
     * Parameterloze constructor voor laden.
     */
    public Values()
    {
        this.foxes      = new byte[] {0};
        this.rabbits    = new byte[] {0};
        this.bears      = new byte[] {0};
        this.hunters    = new byte[] {0};
        
        this.sliders    = new byte[][] {
            new byte[] {-5,0,0,0,0,0},
            new byte[] {-6,0,0,0,0,0},
            new byte[] {-7,0,0,0,0,0},
            new byte[] {-8,0},
            new byte[] {-9,0,0,0}
        };
    }
    
    public void save(String path)
    {
        byte[][] newArray = new byte[8][];
        
        // Build up file in newArray
        newArray[0] = new byte[] {-1};
        newArray[1] = this.foxes;
        newArray[2] = new byte[] {-2};
        newArray[3] = this.rabbits;
        newArray[4] = new byte[] {-3};
        newArray[5] = this.bears;
        newArray[6] = new byte[] {-4};
        newArray[7] = this.hunters;
        
        Write data = new Write(path);
        try {
            data.writeToFile(newArray);
            data.writeToFile(this.sliders);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @SuppressWarnings("null")
	public void load(String path, boolean print)
    {
        Read data = null;
        try {
            data = new Read(path);
            data.readFromFile();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        this.loadInVariables(data.getData());
        
        if(print) {
            this.print();
        }
    }
    
    @SuppressWarnings("static-method")
	private byte[] build(int fullBytes, int remainder)
    {
        if(remainder>0) fullBytes++;
        byte[] buildUp = new byte[fullBytes];
        
        for(int i=0;i<fullBytes-1;i++) {
            buildUp[i] = 127;
        }
        buildUp[buildUp.length-1] = (byte) remainder;
        return buildUp;
    }
    
    
    @SuppressWarnings("static-method")
	private byte[][] buildSliders()
    {
        byte[][] sliderVals = new byte[5][];
        sliderVals[0] = new byte[] {
            -5,
            (byte) Bear.breedingAge,
            (byte) Bear.maxAge,
            (byte) (Bear.breedingProbability * 100),
            (byte) Bear.maxLitterSize,
            (byte) Bear.foodValue
        };
        sliderVals[1] = new byte[] {
            -6,
            (byte) Fox.breedingAge,
            (byte) Fox.maxAge,
            (byte) (Fox.breedingProbability * 100),
            (byte) Fox.maxLitterSize,
            (byte) Fox.foodValue
        };
        sliderVals[2] = new byte[] {
            -7,
            (byte) Rabbit.breedingAge,
            (byte) Rabbit.maxAge,
            (byte) (Rabbit.breedingProbability * 100),
            (byte) Rabbit.maxLitterSize,
            (byte) Rabbit.foodValue
        };
        sliderVals[3] = new byte[] {
            -8,
            (byte) Hunter.maxKills
        };
        sliderVals[4] = new byte[] {
            -9,
            (byte) Grass.breedingAge,
            (byte) (Grass.breedingProbability * 100),
            (byte) Grass.maxLitterSize
        };
            
        return sliderVals;
    }
    
    private void loadInVariables(byte[] values)
    {
        for(int i=0, j=0, h=-1;i<values.length;i++) {
            
            if(values[i] < 0 && values[i] > -5) {
                h++;
                j = i+1;
                
                while(j<values.length && values[j]>=0) {
                    this.counts[h] += values[j];
                    if(values[j]==127) this.counts[h]++;
                    j++;
                } 
            } else if(values[i] < -4) {
                switch(values[i]) {
                    case -5:
                        Bear.breedingAge               = values[i+1];
                        Bear.maxAge                    = values[i+2];
                        Bear.breedingProbability       = new Integer(values[i+3]).doubleValue() / 100;
                        Bear.maxLitterSize            = values[i+4];
                        Bear.foodValue                 = values[i+5];
                    break;
                    case -6:
                        Fox.breedingAge                = values[i+1];
                        Fox.maxAge                     = values[i+2];
                        Fox.breedingProbability        = new Integer(values[i+3]).doubleValue() / 100;
                        Fox.maxLitterSize             = values[i+4];
                        Fox.foodValue                  = values[i+5];
                    break;
                    case -7:
                        Rabbit.breedingAge             = values[i+1];
                        Rabbit.maxAge                  = values[i+2];
                        Rabbit.breedingProbability     = new Integer(values[i+3]).doubleValue() / 100;
                        Rabbit.maxLitterSize          = values[i+4];
                        Rabbit.foodValue               = values[i+5];
                    break;
                    case -8:
                        Hunter.maxKills                = values[i+1];
                    break;
                    case -9:
                        Grass.breedingAge              = values[i+1];
                        Grass.breedingProbability      = new Integer(values[i+2]).doubleValue() / 100;
                        Grass.maxLitterSize           = values[i+3];
                    break;
                }
            }
        }
    }
    
    private void print()
    {
        System.out.println("Vossen: " + this.counts[0]);
        System.out.println("Konijnen: " + this.counts[1]);
        System.out.println("Beren: " + this.counts[2]);
        System.out.println("Jagers: " + this.counts[3]);
    }
    
    public int[] getCountArray()
    {
        return this.counts;
    }
}
