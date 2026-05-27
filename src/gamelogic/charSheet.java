package gamelogic;
import java.util.*;


public class charSheet {
	/*
	Po means Player only
	*/
	public class CharSheet {
	    private 
	    String name,
	    //Scene location of the character by name
	    location,
	    /*Po. This reads the words put to be sent for the parser and command parser*/
	    fString="", sString="";
	    private int 
	  //no function yet. items on hand. may stay unused at first 
	    mainHand, offHand, 
	    /*Random Stats & Status Effects Placeholder*/
	    energy, aroma,maxEnergy, tired, recovery , bleed, aim, dodge,
	  //id of the character. if the character is NPC or enemy, then the number is negative. if the character is player, then is pulled from the database 
	    charId, 
	  //no function yet, counter of bless 
	    blessCount, 
	  //no function yet, counter of enchantment 
	    enchantmentCount, 
	  //location of the user by id
	    sceneiD 
	    ;
	    boolean player,alive, blessed=false/*player only*/,enchanted=false/*player only*/,firstAction/*player only*/,resting, retreating/*wolf only*/;
	    private List<String> statusEffects = new ArrayList<>();
	    //int inventory
	    int[] belt
	       /*0,//item 1,
	        0,//item 2, 
	        0,//item 3
	        0,//item 4
	        0//item 5
	            /*
	            Amathyst 20
	            sword: 40 normal, 41 silver, 42 amathyst 
	            Silver cross 10, 12 Enchanted cross, garlic bloom: 30 normal, 31 silver,32 amathyst, umbrella: 50 normal, 51 silver, 52 amathyst
	            
	    } */ ;
	    
	    //19 cambiado a Arraylist en lugar de usar arrays, y un hashmap.
	    
	    
	    //private Map<String, List<String>> menuOptions = new HashMap<>();//I knew it. I hate Hashmaps. Ill return to bidimensional, but this time with lists.
	    private List<String>[] menuOptions ;
	    //private String currentMenu = "Inicial"; //19 in consideration
	    private int currentMenu=0,selectedOption = 0;
	    private Map<Integer, String> menuNames = new HashMap<>(); // 19: EXPERIMENTING with hashmap, despite/because of my complications. x → name
	    private Map<String, Integer> nameToInt = new HashMap<>(); //19: name → x
	    // Status effects
	    // Codewords (lexicon expansions)
	    private List<String> knownWords = new ArrayList<>();

	    // NPC/Player distinction

	    public CharSheet() {
	        this.charId = 1;
	        if(charId==1){this.player=true;}
	        this.name = name;
	        this.alive = true;
	        this.energy = 100;
	        this.maxEnergy = 100;
	        this.recovery = 5;
	        this.tired = 0;
	        this.aim=0; dodge=0;
	            this.enchanted=false;
	            this.blessed= false;
	            this.resting= false;
	        this.mainHand = 0;
	        this.offHand = 0;
	        belt=new int[]
	            {
	            0,//item 1,
	            0,//item 2, 
	            0,//item 3
	            0,//item 4
	            0//item 5
	            };
	        
	    }

	    // getters/setters...}
	        //wolf only:
	    
	        private List<String> wolfOptions ;
	   public CharSheet(String beastName) { //19 This is like the seventh or sixth version of this
	       name=beastName;
	       aim=dodge=5;     
	       resting= false; retreating=false;
	            currentState = "";
	            currentScene=5;
	            energy = 150; maxEnergy = 150; recovery = 10; tired = 0;
	            bleed=0;
	            charID=3;
	            alive= true;
	            stepsX=stepsY=0;
	            
	            wolfOptions = new ArrayList<>(Arrays.asList()); // Ir
	   }
	   

	    public int itemIDFromName(String name) {
	    switch (name) {
	        case "Espada": return 40;
	        case "Espada\nCinerea": return 41;
	        case "Espada\nEncantada": return 42;
	        case "Cruz\nPlateada": return 10;
	        case "Cruz\nEncantada": return 12;
	        case "Ajo\nFloreado": return 30;
	        case "Ajo\nPlata": return 31;
	        case "Flor\nAmatista": return 32;
	        case "Sombrilla": return 50;
	        case "Sombrilla\nPlatina": return 51;
	        case "Sombrilla\nEncantada": return 52;
	        case "Amatista": return 20;
	        default: return -1; // unknown item name
	    }
	}
	    
	    public void attack(CharSheet defensor,
	        Item weapon) {
	}

	public void attack(Item targetedItem,
	        Item weapon) {
	}

	public void attack(CharSheet defensor,
	        Item weapon, String specific) {
	}
	    
	public void attack(AbstractTarget abstractTarget,
	        Item weapon) {
	}

	public void attack(CharSheet defensor,
	        Item weapon, List<String> effects) {
	}

	public void attack(Item targetedItem,
	        Item weapon, List<String> effects) {
	}

	public void attack(CharSheet defensor,
	        Item weapon, String specific, List<String> effects) {
	}

	public void attack(AbstractTarget abstractTarget,
	        Item weapon, List<String> effects) {
	}




	    public void attack(CharSheet defensor, int effects) {if (getEnergy()>5){
	   //1=silver
	   //2=purple
	String mensaje = defensor.name;
	    // tirada de ataque: dado + puntería - esquiva del defensor
	    int extra=effects-1;
	    extra=extra*3;
	    if(effects==1 && defensor.getCharID()==3){extra+=8;if(defensor.isResting()){extra+=8;}}
	    int hit = Cinerea.gameMaster.d(6)+Cinerea.gameMaster.d(6) + getAim() - defensor.getDodge()+extra;
	    useEnergy(-6-(Cinerea.gameMaster.d(6)));

	    if (hit < -4) {
	        // esquiva y queda expuesto
	        addDodge(-5);
	        mensaje += " esquiva\ny tu has quedado\nexpuesto!";
	    } else if (hit < 0) {
	        // esquiva
	        mensaje += " bloquea\nel ataque.";
	    } else if (hit < 1) {
	        // golpe leve
	        defensor.useEnergy(-3);
	        defensor.addBleed(1);
	        mensaje += " es rozado\npor\nun\nataque";
	    } else if (hit < 5) {
	        // golpe sólido
	        defensor.useEnergy(-5);
	        defensor.addBleed(Cinerea.gameMaster.d(4));
	        mensaje += " recibe un\nsuper golpe!";
	    } else if (hit < 8) {
	        defensor.useEnergy(-10);
	        defensor.addBleed(4);
	        defensor.addTired(1);
	        mensaje += " ha \nrecibido un Golpe\nCrítico!";
	    } else if (hit < 12) {
	        // crítico devastador
	        defensor.useEnergy(-30);
	        defensor.addBleed(5);
	        defensor.addTired(1);
	        defensor.addDodge(-3);
	        defensor.addAim(-3);
	        mensaje += " ha sido\nherido\nterriblemente!\n!un crtitico\ndevastador!";
	    } else if (hit >= 12) {
	        // muerte
	        defensor.setAlive(false);
	        mensaje += " ha\nsido asesinado por\n" + name;
	    } else {
	        return;
	    }

	    Cinerea.gameMaster.addMessage(mensaje, 500);
	}}

	    public String itemNameFromID(int id) {
	        switch(id) {
	            case 40: return "Espada";
	            case 41: return "Espada\nCinerea";
	            case 42: return "Espada\nEncantada";
	            case 10: return "Cruz\nPlateada";
	            case 12: return "Cruz\nEncantada";
	            case 30: return "Ajo\nFloreado";
	            case 31: return "Ajo\nPlata";
	            case 32: return "Flor\nAmatista";
	            case 50: return "Sombrilla";
	            case 51: return "Sombrilla\nPlatina";
	            case 52: return "Sombrilla\nEncantada";
	            case 20: return "Amatista";
	            default: return "Objeto desconocido ("+id+")";
	        }
	    }
	    public Node PlayerNode(){
	    if (charID==1){return Cinerea.world.player1;}
	    else if(charID==2){return Cinerea.world.player2;}
	    else{return Cinerea.world.wolfBeast;}
	    }
	    public boolean getFirstAction() {
	        return firstAction;
	    }

	    public int getBleed() {
	        return bleed;
	    }

	    public void setBleed(int bleed) {this.bleed = bleed;}
	    public void addBlessed(int l){
	        
	        if(l==0){return;}
	        else if(l>0){this.blessCount=l;}
	        else if(l<0){this.blessCount-=l;}
	        if(this.blessCount>0){setBlessed(true);}
	        if(this.blessCount<=0)
	        {this.blessCount=0;
	        if (this.blessed)
	        {setBlessed(false);
	        Cinerea.gameMaster.addMessage(getName()+"siente\nla bendicion abandonar",700);}
	        }}
	    public void addEnchanted(int l){
	        if(l>0){this.enchantmentCount=l;}
	        if(l<0){this.enchantmentCount-=l;}
	        if(this.enchantmentCount>0){setEnchanted(true);}
	        if(this.enchantmentCount<=0)
	        {this.enchantmentCount=0;
	        setEnchanted(false);
	        Cinerea.gameMaster.addMessage(getName()+"siente\nel encantamiento seder",700);
	        }}
	    
	    public void setFirstAction(boolean a) {this.firstAction=a;}
	    //you know what im realizing? i really like making my own setters and getters.
	    public List<String> getMenuOptions(int menux) {
	        if (menux >= 0 && menux < menuOptions.length) {
	            return menuOptions[menux];
	        }
	        return new ArrayList<>();
	    }
	    
	    public List<String> getMenuOptions(String menuName) {
	        Integer x = nameToInt.get(menuName);
	        if (x != null) {
	            return menuOptions[x];
	        }
	        return new ArrayList<>();
	    }
	    
	    // Get current option
	    public String getOption() {
	      //  List<String> options = menuOptions[currentMenu];
	       // if (selectedOption >= 0 && selectedOption < options.size()) {
	         //   return options.get(selectedOption);
	       // }
	        return "";
	    }
	    // Replace options in a given menu
	    public void setOptions(String menuName, String[] newOptions) {
	        Integer x = nameToInt.get(menuName);
	        if (x != null) {
	            List<String> options = menuOptions[x];
	            options.clear();
	            options.addAll(Arrays.asList(newOptions));
	        }
	    }
	    
	     public void setOptions(String menuName, List<String> newOptions) {
	        Integer x = nameToInt.get(menuName);
	        if (x != null) {
	            List<String> options = menuOptions[x];
	            options.clear();
	            options.addAll(newOptions);
	        }
	    }
	     
	     public void setOptions(List<String> newOptions) {
	            wolfOptions.clear();
	            wolfOptions.addAll(newOptions);
	        
	    }
	     
	    public void recover(){
	        useEnergy(recovery - tired);
	    }
	    
	    
	    
	    
	    public void addOption(String menuName, String option) {
	        Integer x = nameToInt.get(menuName);
	        if (x != null) {
	            menuOptions[x].add(option);
	        }
	    }
	    public void removeOption(String menuName, String option) {
	        Integer x = nameToInt.get(menuName);
	        if (x != null) {
	            menuOptions[x].remove(option);
	        }
	    }
	    
	       public void setCurrentMenu(int x) {
	        if (x >= 0 && x < menuOptions.length) {
	            currentMenu = x;
	            selectedOption = 0;
	        }
	    }
	       public void setCurrentMenu(String menuName) {
	        Integer x = nameToInt.get(menuName);
	if (menuName.equals("Inicial")){
	    fString="";
	    sString="";}
	        if (x != null) {
	            currentMenu = x;
	            selectedOption = 0;
	        }
	    }
	       
	    public int getCurrentMenu() { return currentMenu; }
	    public String getCurrentMenuName() { return menuNames.get(currentMenu); }

	    public void setSelectedOption(int x) { selectedOption = x; }
	    public int getSelectedOption() { return selectedOption; }

	    public String getCurrentH() {
	        return currentState;
	    }

	    public int getMaxEnergy() {
	        return maxEnergy;
	    }

	    public int getTired() {
	        return tired;
	    }

	    public int getRecovery() {
	        return recovery;
	    }
	    public int getRecovery(int x) {
	        return (recovery-tired);
	    }

	    public void setMaxEnergy(int maxEnergy) {
	        this.maxEnergy = maxEnergy;
	    }

	    public void setTired(int tired) {
	        this.tired = tired;
	        if (tired<0){
	        this.tired=0;
	    }
	    }

	    public void setRecovery(int recovery) {
	        this.recovery = recovery;
	    }
	    
	    

	    public int getEnergy() {
	        return energy;
	    }

	    public int getCurrentScene() {
	        return currentScene;
	    }

	    public int getCharID() {
	        return charID;
	    }

	    public boolean isAlive() {
	        return alive;
	    }
	    public boolean isResting() {
	        return resting;
	    }
	    


	    public int[] getBelt() {
	        return belt;
	    }

	    public List<String>[] getMenuOptions() {
	        return menuOptions;
	    }

	    public Map<Integer, String> getMenuNames() {
	        return menuNames;
	    }

	    public Map<String, Integer> getNameToInt() {
	        return nameToInt;
	    }

	    public void setCurrentH(String currentH) {
	        this.currentState= currentH;
	    }
	    public void addTired(int tiredness){
	        this.tired+=tiredness;
	        if (tired<0){
	        this.tired=0;
	    }
	    }
	    
	    public void addBleed(int bleeding){
	        this.bleed+=bleeding;
	        if (bleed<0){
	        this.bleed=0;}
	        
	        if (bleed==0){Cinerea.gameMaster.addMessage("El sangrado\nde"+getName()+"\nha parado",700);}
	    }

	public void addAroma(int aroma) {
	    this.aroma += aroma;
	    if (this.aroma < 0) {
	        this.aroma = 0;
	    }
	}

	public void addDodge(int dodge) {
	    this.dodge += dodge;

	}

	public void addAim(int aim) {
	    this.aim += aim;
	}
	    public void setEnergy(int nergy) {
	        this.energy = nergy;
	        if (this.energy <0){
	            this.tired+=1;
	            this.energy = 0;
	        }
	        if (this.maxEnergy <this.energy){
	            this.energy = this.maxEnergy;
	        }
	        if (tired<0){
	        this.tired=0;
	    }
	    }
	    
	    public void useEnergy(int ene) {
	        this.energy += ene;
	        if (this.energy <0){
	            this.tired+=1;
	            this.energy = 0;
	        }
	        if (this.maxEnergy <this.energy){
	            this.energy = this.maxEnergy;
	        }
	        if (tired<0){
	        this.tired=0;
	    }
	    }

	  
	    public boolean isFirstAction() {
	        return firstAction;
	    }

	    public void setWolfOptions(List<String> wolfOptions) {
	        this.wolfOptions = wolfOptions;
	    }
	    
	    

	    public List<String> getWolfOptions() {
	        return wolfOptions;
	    }


	    
	    public void setCurrentScene(int currentScene) {
	        this.currentScene = currentScene;
	    }

	    public void setcharID(int charID) {
	        this.charID = charID;
	    }

	    public void setAlive(boolean alive) {
	        this.alive = alive;
	    }
	    public void setResting(boolean re) {
	        this.resting = re;
	    }

	    public void setBelt(int[] belt) {
	        this.belt = belt;
	    }
	    public boolean addtoBelt(int item) {
	        for(int i=0; i<5 ; i++)
	            if (belt[i]==0){
	                this.belt[i]=item;
	                addOption("Usar",itemNameFromID(item));
	                return true;
	            }
	        return false;
	    }

	    public boolean isBlessed() {
	        return blessed;
	    }

	    public void setBlessed(boolean blessed) {
	        this.blessed = blessed;
	    }
	    
	    
	    public boolean removeFromBelt(int item) {
	    for (int i = 0; i < belt.length; i++) {
	        if (belt[i] == item) {   // found the item in this slot
	            belt[i] = 0;         // clear the slot
	            removeOption("Usar",itemNameFromID(item));
	            return true;         // success
	        }
	    }
	    return false;                // item not found
	}

	    public void setMenuOptions(List<String>[] menuOptions) {
	        this.menuOptions = menuOptions;
	    }

	    public void setMenuNames(Map<Integer, String> menuNames) {
	        this.menuNames = menuNames;
	    }

	    public void setNameToInt(Map<String, Integer> nameToInt) {
	        this.nameToInt = nameToInt;
	    }

	    public String getfString() {
	        return fString;
	    }

	    public String getsString() {
	        return sString;
	    }

	    public void setfString(String fString) {
	        this.fString = fString;
	    }

	    public void setsString(String sString) {
	        this.sString = sString;
	    }

	    public int getAroma() {
	        return aroma;
	    }

	    public void setCurrentState(String currentState) {
	        this.currentState = currentState;
	    }

	    public void setAim(int aim) {
	        this.aim = aim;
	    }

	    public void setDodge(int dodge) {
	        this.dodge = dodge;
	    }

	    public void setEnchanted(boolean enchanted) {
	        this.enchanted = enchanted;
	    }

	    public void setStepsY(int stepsY) {
	        this.stepsY = stepsY;
	    }

	    public void setStepsX(int stepsX) {
	        this.stepsX = stepsX;
	    }

	    public String getCurrentState() {
	        return currentState;
	    }

	    public int getAim() {
	        return aim;
	    }

	    public int getDodge() {
	        return dodge;
	    }

	    public boolean isEnchanted() {
	        return enchanted;
	    }
	    public int getEnchanted() {
	        if(enchanted){return 1;};
	        return 0;
	    }
	    public int getBlessed() {
	        if(blessed){return 1;};
	        return 0;
	    }
	    

	    public int getStepsY() {
	        return stepsY;
	    }
	    public int getStepsX() {
	        return stepsX;
	    }
	    public void setAroma(int aroma) {
	        this.aroma = aroma;
	    }

	    public void setRetreating(boolean retreating) {
	        this.retreating = retreating;
	    }

	    public boolean isRetreating() {
	        return retreating;
	    }
	    
	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getMainHand() {
	        return mainHand;
	    }

	    public int getOffHand() {
	        return offHand;
	    }

	    public int getBlessCount() {
	        return blessCount;
	    }

	    public int getEnchantmentCount() {
	        return enchantmentCount;
	    }

	    public boolean isPlayer() {
	        return player;
	    }

	    public List<String> getStatusEffects() {
	        return statusEffects;
	    }

	    public List<String> getKnownWords() {
	        return knownWords;
	    }

	    public void setMainHand(int mainHand) {
	        this.mainHand = mainHand;
	    }

	    public void setOffHand(int offHand) {
	        this.offHand = offHand;
	    }

	    public void setCharID(int charID) {
	        this.charID = charID;
	    }

	    public void setBlessCount(int blessCount) {
	        this.blessCount = blessCount;
	    }

	    public void setEnchantmentCount(int enchantmentCount) {
	        this.enchantmentCount = enchantmentCount;
	    }

	    public void setPlayer(boolean player) {
	        this.player = player;
	    }

	    public void setStatusEffects(List<String> statusEffects) {
	        this.statusEffects = statusEffects;
	    }

	    public void setKnownWords(List<String> knownWords) {
	        this.knownWords = knownWords;
	    }

	    @Override
	    public void applyEffect(String effect) {
	    switch (effect) {
	        case "bleed": addBleed(1); break;
	        case "tired": addTired(1); break;
	        case "heal": setEnergy(getEnergy() + 10); break;
	        default: System.out.println(name + " is affected by " + effect);
	    }
	}
	    

	}
	/*Graveyard
	//as of 18 0836 no longer do we use:
	    int[] belt={
	        0,//sword: 3 normal, 4 silver, 5 amathyst 
	        0,//umbrella: 6 normal, 7 silver, 8 amathyst
	        0,//garlic bloom: 9 normal, 10 silver,11 amathyst
	        0,//Silver cross 1
	        0//Amathyst 2
	    } ;
	  // Inicial menu 0
	        menuOptions.add(new ArrayList<>(Arrays.asList("Usar","Tomar","Hablar","Ir","Descansar","Accion")));
	        // Usar menu 1
	        menuOptions.add(new ArrayList<>(Arrays.asList()));
	        // Tomar menu 2
	        menuOptions.add(new ArrayList<>());
	        // Hablar menu 3
	        menuOptions.add(new ArrayList<>());
	        // Ir menu 4
	        menuOptions.add(new ArrayList<>(Arrays.asList("Norte","Sur","Este","Oeste")));
	        // Descansar menu 5
	        menuOptions.add(new ArrayList<>(Arrays.asList("Observar","Dar","Dejar")));
	        // Accion objetivos menu 6
	        menuOptions.add(new ArrayList<>());
	        // Objetivos menu 7

	 static String[][] menuOptions={{"Usar","Tomar", "Hablar", "Ir", "Descansar","Observar"},//0
	    {"","", ""}//1, "Use
	    //"Old Sword","Silver Sword","Enchanted Sword ","Garlic Bloom","Silver Garlic" ,"Enchanted Bloom", "Worn Umbrella", "Silver Umbrella", "Encahnted Umbrella",};
	    ,{"","","", "", "", ""}//2 "Take"
	    ,{"","","","", "", ""}//3 "Talk" 
	    ,{"Norte", "Sur", "Este", "Oeste", "", ""}//4 "Go"
	    ,{"tomar \n respiro", "", "", "", "", ""}//5 "Rest"
	    ,{"", "", "","","","",""}//7 "Targets"
	            //12 2157 what if i make a circular ocatagonal list for this as well and throw it all away
	            /*i states the current menu (0-5) and j states the current option
	            
	           /*10: Debug system to prove the working function of arrays and their sizes
	    public static void main(String[] args) {
	        for(int x=0; x<=5;x++){
	            for(int y=0; y<=5;y++){
	                menuOptions[x][y]="a";
	                System.out.print(menuOptions[x][y]+"\n");
	            
	        }
	            System.out.println("\n");
	        }

	    public CharSheet() {
	        //19 this Initialize menus with flexible lists. I do not know what im doing. going in blind.
	        menuOptions.put("Inicial", new ArrayList<>(Arrays.asList("Usar","Tomar","Hablar","Ir","Descansar","Observar")));
	        menuOptions.put("Usar", new ArrayList<>());//1, "Use" "Old Sword","Silver Sword","Enchanted Sword ","Garlic Bloom","Silver Garlic" ,"Enchanted Bloom", "Worn Umbrella", "Silver Umbrella", "Encahnted Umbrella",};
	        menuOptions.put("Tomar", new ArrayList<>());//2 "Take"
	        menuOptions.put("Hablar", new ArrayList<>());//3 "Talk" 
	        menuOptions.put("Ir", new ArrayList<>(Arrays.asList("Norte","Sur","Este","Oeste")));//4 "Go"
	        menuOptions.put("Descansar", new ArrayList<>(Arrays.asList("Tomar\nrespiro")));//5 "Rest"
	        menuOptions.put("Objetivos", new ArrayList<>());//7 Targets
	    }

	currentMenu used to be an int
	    }

	  public List<String> getMenuOptions(String menuName) {
	        return menuOptions.getOrDefault(menuName, new ArrayList<>());
	    }

	    //19: Get the currently selected option. Copilot has explained to me clearly everything once and twice and thrice. It has told me that i have done far more complicated things on my own, 
	    //and that this is the right path.
	    public String getOption() {
	        List<String> options = menuOptions.getOrDefault(currentMenu, new ArrayList<>());
	        if (selectedOption >= 0 && selectedOption < options.size()) {
	            return options.get(selectedOption);
	        }
	        return "";
	    }

	    //19 Replace options for a given menu
	    public void setOptions(String menuName, String[] newOptions) {
	        List<String> options = menuOptions.get(menuName);
	        if (options != null) {
	            options.clear();
	            options.addAll(Arrays.asList(newOptions));
	        }
	    }
	    
	     //19 Change selected option. The thing that terrifies me the most is the hasmap. Im scared to death of hashmaps.
	    public void setSelectedOption(int selectedOption) {
	        this.selectedOption = selectedOption;
	    }

	    public void setCurrentMenu(int manu) {
	            this.currentMenu = manu;
	          
	    }

	    public int getSelectedOption() {
	        return selectedOption;
	    }

	    public int getCurrentMenu() {
	        return currentMenu;
	    }
	  public String getPreviousSelected() {
	        return previousSelected;
	    }

	    public void setPreviousSelected(String previousSelected) {
	        this.previousSelected = previousSelected;
	    }

	    //19 Get all options for a given menu. This experience is stressful and is making my stomach turn.
	    public List<String> getMenuOptions(String menuName) {
	        return menuOptions.getOrDefault(menuName, new ArrayList<>());
	    }

	    //19: Get the currently selected option. Copilot has explained to me clearly everything once and twice and thrice. It has told me that i have done far more complicated things on my own, 
	    //and that this is the right path.
	    public String getOption() {
	        List<String> options = menuOptions.getOrDefault(currentMenu, new ArrayList<>());
	        if (selectedOption >= 0 && selectedOption < options.size()) {
	            return options.get(selectedOption);
	        }
	        return "";
	    }

	    //19 Replace options for a given menu
	    public void setOptions(String menuName, String[] newOptions) {
	        List<String> options = menuOptions.get(menuName);
	        if (options != null) {
	            options.clear();
	            options.addAll(Arrays.asList(newOptions));
	        }
	    }
	    
	     //19 Change selected option. The thing that terrifies me the most is the hasmap. Im scared to death of hashmaps.
	    public void setSelectedOption(int selectedOption) {
	        this.selectedOption = selectedOption;
	    }

	    public void setCurrentMenu(int manu) {
	            this.currentMenu = manu;
	          
	    }

	    public int getSelectedOption() {
	        return selectedOption;
	    }

	    public int getCurrentMenu() {
	        return currentMenu;
	    }

	*/ 
	    
	    //int i=0, j=0;
	    //String a=menuOptions[i][j];

}
/*OLD NETBEANS CHARSHEET*/

/*
 * 
19: 2030 Pushing things with arraylists, betting on them making the work easier. I, however, do fear them tremendously. I gotta learn more, yes, im pushing myself forward, yes, but
dont have that much time.

public class CharSheet implements Target{
    private String name, currentState, fString=""/*player only, sString=""/*player only;
    private int mainHand, offHand, energy, aroma,maxEnergy, tired, recovery, currentScene , bleed, aim, dodge;
    private int charID,blessCount, enchantmentCount;
    boolean player,alive, blessed=false/*player only,enchanted=false/*player only,firstAction/*player only,resting, retreating/*wolf only;
    private List<String> statusEffects = new ArrayList<>();
    int stepsY, stepsX; // path memory
    int[] belt*/
       /*0,//item 1,
        0,//item 2, 
        0,//item 3
        0,//item 4
        0//item 5
            /*
            Amathyst 20
            sword: 40 normal, 41 silver, 42 amathyst 
            Silver cross 10, 12 Enchanted cross, garlic bloom: 30 normal, 31 silver,32 amathyst, umbrella: 50 normal, 51 silver, 52 amathyst
            
    }  ;
    
     //14 traducido al español   y a las 1141 movido de GameMaster a Char1Sheet
    //19 cambiado a Arraylist en lugar de usar arrays, y un hashmap.
    
    
    //private Map<String, List<String>> menuOptions = new HashMap<>();//I knew it. I hate Hashmaps. Ill return to bidimensional, but this time with lists.
    private List<String>[] menuOptions ;
    //private String currentMenu = "Inicial"; //19 in consideration
    private int currentMenu=0,selectedOption = 0;
    private Map<Integer, String> menuNames = new HashMap<>(); // 19: EXPERIMENTING with hashmap, despite/because of my complications. x → name
    private Map<String, Integer> nameToInt = new HashMap<>(); //19: name → x
    // Status effects
    // Codewords (lexicon expansions)
    private List<String> knownWords = new ArrayList<>();

    // NPC/Player distinction

    public CharSheet() {
        this.charID = 1;
        if(charID==1){this.player=true;}
        this.name = name;
        this.alive = true;
        this.energy = 100;
        this.maxEnergy = 100;
        this.recovery = 5;
        this.tired = 0;
        this.aim=0; dodge=0;
            this.enchanted=false;
            this.blessed= false;
            this.resting= false;
        this.mainHand = 0;
        this.offHand = 0;
        belt=new int[]
            {
            0,//item 1,
            0,//item 2, 
            0,//item 3
            0,//item 4
            0//item 5
            };
        
    }

    // getters/setters...}
        //wolf only:
    
        private List<String> wolfOptions ;
   public CharSheet(String beastName) { //19 This is like the seventh or sixth version of this
       name=beastName;
       aim=dodge=5;     
       resting= false; retreating=false;
            currentState = "";
            currentScene=5;
            energy = 150; maxEnergy = 150; recovery = 10; tired = 0;
            bleed=0;
            charID=3;
            alive= true;
            stepsX=stepsY=0;
            
            wolfOptions = new ArrayList<>(Arrays.asList()); // Ir
   }
   

    public int itemIDFromName(String name) {
    switch (name) {
        case "Espada": return 40;
        case "Espada\nCinerea": return 41;
        case "Espada\nEncantada": return 42;
        case "Cruz\nPlateada": return 10;
        case "Cruz\nEncantada": return 12;
        case "Ajo\nFloreado": return 30;
        case "Ajo\nPlata": return 31;
        case "Flor\nAmatista": return 32;
        case "Sombrilla": return 50;
        case "Sombrilla\nPlatina": return 51;
        case "Sombrilla\nEncantada": return 52;
        case "Amatista": return 20;
        default: return -1; // unknown item name
    }
}
    
    public void attack(CharSheet defensor,
        Item weapon) {
}

public void attack(Item targetedItem,
        Item weapon) {
}

public void attack(CharSheet defensor,
        Item weapon, String specific) {
}
    
public void attack(AbstractTarget abstractTarget,
        Item weapon) {
}

public void attack(CharSheet defensor,
        Item weapon, List<String> effects) {
}

public void attack(Item targetedItem,
        Item weapon, List<String> effects) {
}

public void attack(CharSheet defensor,
        Item weapon, String specific, List<String> effects) {
}

public void attack(AbstractTarget abstractTarget,
        Item weapon, List<String> effects) {
}




    public void attack(CharSheet defensor, int effects) {if (getEnergy()>5){
   //1=silver
   //2=purple
String mensaje = defensor.name;
    // tirada de ataque: dado + puntería - esquiva del defensor
    int extra=effects-1;
    extra=extra*3;
    if(effects==1 && defensor.getCharID()==3){extra+=8;if(defensor.isResting()){extra+=8;}}
    int hit = Cinerea.gameMaster.d(6)+Cinerea.gameMaster.d(6) + getAim() - defensor.getDodge()+extra;
    useEnergy(-6-(Cinerea.gameMaster.d(6)));

    if (hit < -4) {
        // esquiva y queda expuesto
        addDodge(-5);
        mensaje += " esquiva\ny tu has quedado\nexpuesto!";
    } else if (hit < 0) {
        // esquiva
        mensaje += " bloquea\nel ataque.";
    } else if (hit < 1) {
        // golpe leve
        defensor.useEnergy(-3);
        defensor.addBleed(1);
        mensaje += " es rozado\npor\nun\nataque";
    } else if (hit < 5) {
        // golpe sólido
        defensor.useEnergy(-5);
        defensor.addBleed(Cinerea.gameMaster.d(4));
        mensaje += " recibe un\nsuper golpe!";
    } else if (hit < 8) {
        defensor.useEnergy(-10);
        defensor.addBleed(4);
        defensor.addTired(1);
        mensaje += " ha \nrecibido un Golpe\nCrítico!";
    } else if (hit < 12) {
        // crítico devastador
        defensor.useEnergy(-30);
        defensor.addBleed(5);
        defensor.addTired(1);
        defensor.addDodge(-3);
        defensor.addAim(-3);
        mensaje += " ha sido\nherido\nterriblemente!\n!un crtitico\ndevastador!";
    } else if (hit >= 12) {
        // muerte
        defensor.setAlive(false);
        mensaje += " ha\nsido asesinado por\n" + name;
    } else {
        return;
    }

    Cinerea.gameMaster.addMessage(mensaje, 500);
}}

    public String itemNameFromID(int id) {
        switch(id) {
            case 40: return "Espada";
            case 41: return "Espada\nCinerea";
            case 42: return "Espada\nEncantada";
            case 10: return "Cruz\nPlateada";
            case 12: return "Cruz\nEncantada";
            case 30: return "Ajo\nFloreado";
            case 31: return "Ajo\nPlata";
            case 32: return "Flor\nAmatista";
            case 50: return "Sombrilla";
            case 51: return "Sombrilla\nPlatina";
            case 52: return "Sombrilla\nEncantada";
            case 20: return "Amatista";
            default: return "Objeto desconocido ("+id+")";
        }
    }
    public Node PlayerNode(){
    if (charID==1){return Cinerea.world.player1;}
    else if(charID==2){return Cinerea.world.player2;}
    else{return Cinerea.world.wolfBeast;}
    }
    public boolean getFirstAction() {
        return firstAction;
    }

    public int getBleed() {
        return bleed;
    }

    public void setBleed(int bleed) {this.bleed = bleed;}
    public void addBlessed(int l){
        
        if(l==0){return;}
        else if(l>0){this.blessCount=l;}
        else if(l<0){this.blessCount-=l;}
        if(this.blessCount>0){setBlessed(true);}
        if(this.blessCount<=0)
        {this.blessCount=0;
        if (this.blessed)
        {setBlessed(false);
        Cinerea.gameMaster.addMessage(getName()+"siente\nla bendicion abandonar",700);}
        }}
    public void addEnchanted(int l){
        if(l>0){this.enchantmentCount=l;}
        if(l<0){this.enchantmentCount-=l;}
        if(this.enchantmentCount>0){setEnchanted(true);}
        if(this.enchantmentCount<=0)
        {this.enchantmentCount=0;
        setEnchanted(false);
        Cinerea.gameMaster.addMessage(getName()+"siente\nel encantamiento seder",700);
        }}
    
    public void setFirstAction(boolean a) {this.firstAction=a;}
    //you know what im realizing? i really like making my own setters and getters.
    public List<String> getMenuOptions(int menux) {
        if (menux >= 0 && menux < menuOptions.length) {
            return menuOptions[menux];
        }
        return new ArrayList<>();
    }
    
    public List<String> getMenuOptions(String menuName) {
        Integer x = nameToInt.get(menuName);
        if (x != null) {
            return menuOptions[x];
        }
        return new ArrayList<>();
    }
    
    // Get current option
    public String getOption() {
      //  List<String> options = menuOptions[currentMenu];
       // if (selectedOption >= 0 && selectedOption < options.size()) {
         //   return options.get(selectedOption);
       // }
        return "";
    }
    // Replace options in a given menu
    public void setOptions(String menuName, String[] newOptions) {
        Integer x = nameToInt.get(menuName);
        if (x != null) {
            List<String> options = menuOptions[x];
            options.clear();
            options.addAll(Arrays.asList(newOptions));
        }
    }
    
     public void setOptions(String menuName, List<String> newOptions) {
        Integer x = nameToInt.get(menuName);
        if (x != null) {
            List<String> options = menuOptions[x];
            options.clear();
            options.addAll(newOptions);
        }
    }
     
     public void setOptions(List<String> newOptions) {
            wolfOptions.clear();
            wolfOptions.addAll(newOptions);
        
    }
     
    public void recover(){
        useEnergy(recovery - tired);
    }
    
    
    
    
    public void addOption(String menuName, String option) {
        Integer x = nameToInt.get(menuName);
        if (x != null) {
            menuOptions[x].add(option);
        }
    }
    public void removeOption(String menuName, String option) {
        Integer x = nameToInt.get(menuName);
        if (x != null) {
            menuOptions[x].remove(option);
        }
    }
    
       public void setCurrentMenu(int x) {
        if (x >= 0 && x < menuOptions.length) {
            currentMenu = x;
            selectedOption = 0;
        }
    }
       public void setCurrentMenu(String menuName) {
        Integer x = nameToInt.get(menuName);
if (menuName.equals("Inicial")){
    fString="";
    sString="";}
        if (x != null) {
            currentMenu = x;
            selectedOption = 0;
        }
    }
       
    public int getCurrentMenu() { return currentMenu; }
    public String getCurrentMenuName() { return menuNames.get(currentMenu); }

    public void setSelectedOption(int x) { selectedOption = x; }
    public int getSelectedOption() { return selectedOption; }

    public String getCurrentH() {
        return currentState;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getTired() {
        return tired;
    }

    public int getRecovery() {
        return recovery;
    }
    public int getRecovery(int x) {
        return (recovery-tired);
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public void setTired(int tired) {
        this.tired = tired;
        if (tired<0){
        this.tired=0;
    }
    }

    public void setRecovery(int recovery) {
        this.recovery = recovery;
    }
    
    

    public int getEnergy() {
        return energy;
    }

    public int getCurrentScene() {
        return currentScene;
    }

    public int getCharID() {
        return charID;
    }

    public boolean isAlive() {
        return alive;
    }
    public boolean isResting() {
        return resting;
    }
    


    public int[] getBelt() {
        return belt;
    }

    public List<String>[] getMenuOptions() {
        return menuOptions;
    }

    public Map<Integer, String> getMenuNames() {
        return menuNames;
    }

    public Map<String, Integer> getNameToInt() {
        return nameToInt;
    }

    public void setCurrentH(String currentH) {
        this.currentState= currentH;
    }
    public void addTired(int tiredness){
        this.tired+=tiredness;
        if (tired<0){
        this.tired=0;
    }
    }
    
    public void addBleed(int bleeding){
        this.bleed+=bleeding;
        if (bleed<0){
        this.bleed=0;}
        
        if (bleed==0){Cinerea.gameMaster.addMessage("El sangrado\nde"+getName()+"\nha parado",700);}
    }

public void addAroma(int aroma) {
    this.aroma += aroma;
    if (this.aroma < 0) {
        this.aroma = 0;
    }
}

public void addDodge(int dodge) {
    this.dodge += dodge;

}

public void addAim(int aim) {
    this.aim += aim;
}
    public void setEnergy(int nergy) {
        this.energy = nergy;
        if (this.energy <0){
            this.tired+=1;
            this.energy = 0;
        }
        if (this.maxEnergy <this.energy){
            this.energy = this.maxEnergy;
        }
        if (tired<0){
        this.tired=0;
    }
    }
    
    public void useEnergy(int ene) {
        this.energy += ene;
        if (this.energy <0){
            this.tired+=1;
            this.energy = 0;
        }
        if (this.maxEnergy <this.energy){
            this.energy = this.maxEnergy;
        }
        if (tired<0){
        this.tired=0;
    }
    }

  
    public boolean isFirstAction() {
        return firstAction;
    }

    public void setWolfOptions(List<String> wolfOptions) {
        this.wolfOptions = wolfOptions;
    }
    
    

    public List<String> getWolfOptions() {
        return wolfOptions;
    }


    
    public void setCurrentScene(int currentScene) {
        this.currentScene = currentScene;
    }

    public void setcharID(int charID) {
        this.charID = charID;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public void setResting(boolean re) {
        this.resting = re;
    }

    public void setBelt(int[] belt) {
        this.belt = belt;
    }
    public boolean addtoBelt(int item) {
        for(int i=0; i<5 ; i++)
            if (belt[i]==0){
                this.belt[i]=item;
                addOption("Usar",itemNameFromID(item));
                return true;
            }
        return false;
    }

    public boolean isBlessed() {
        return blessed;
    }

    public void setBlessed(boolean blessed) {
        this.blessed = blessed;
    }
    
    
    public boolean removeFromBelt(int item) {
    for (int i = 0; i < belt.length; i++) {
        if (belt[i] == item) {   // found the item in this slot
            belt[i] = 0;         // clear the slot
            removeOption("Usar",itemNameFromID(item));
            return true;         // success
        }
    }
    return false;                // item not found
}

    public void setMenuOptions(List<String>[] menuOptions) {
        this.menuOptions = menuOptions;
    }

    public void setMenuNames(Map<Integer, String> menuNames) {
        this.menuNames = menuNames;
    }

    public void setNameToInt(Map<String, Integer> nameToInt) {
        this.nameToInt = nameToInt;
    }

    public String getfString() {
        return fString;
    }

    public String getsString() {
        return sString;
    }

    public void setfString(String fString) {
        this.fString = fString;
    }

    public void setsString(String sString) {
        this.sString = sString;
    }

    public int getAroma() {
        return aroma;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public void setAim(int aim) {
        this.aim = aim;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public void setEnchanted(boolean enchanted) {
        this.enchanted = enchanted;
    }

    public void setStepsY(int stepsY) {
        this.stepsY = stepsY;
    }

    public void setStepsX(int stepsX) {
        this.stepsX = stepsX;
    }

    public String getCurrentState() {
        return currentState;
    }

    public int getAim() {
        return aim;
    }

    public int getDodge() {
        return dodge;
    }

    public boolean isEnchanted() {
        return enchanted;
    }
    public int getEnchanted() {
        if(enchanted){return 1;};
        return 0;
    }
    public int getBlessed() {
        if(blessed){return 1;};
        return 0;
    }
    

    public int getStepsY() {
        return stepsY;
    }
    public int getStepsX() {
        return stepsX;
    }
    public void setAroma(int aroma) {
        this.aroma = aroma;
    }

    public void setRetreating(boolean retreating) {
        this.retreating = retreating;
    }

    public boolean isRetreating() {
        return retreating;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMainHand() {
        return mainHand;
    }

    public int getOffHand() {
        return offHand;
    }

    public int getBlessCount() {
        return blessCount;
    }

    public int getEnchantmentCount() {
        return enchantmentCount;
    }

    public boolean isPlayer() {
        return player;
    }

    public List<String> getStatusEffects() {
        return statusEffects;
    }

    public List<String> getKnownWords() {
        return knownWords;
    }

    public void setMainHand(int mainHand) {
        this.mainHand = mainHand;
    }

    public void setOffHand(int offHand) {
        this.offHand = offHand;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    public void setBlessCount(int blessCount) {
        this.blessCount = blessCount;
    }

    public void setEnchantmentCount(int enchantmentCount) {
        this.enchantmentCount = enchantmentCount;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public void setStatusEffects(List<String> statusEffects) {
        this.statusEffects = statusEffects;
    }

    public void setKnownWords(List<String> knownWords) {
        this.knownWords = knownWords;
    }

    @Override
    public void applyEffect(String effect) {
    switch (effect) {
        case "bleed": addBleed(1); break;
        case "tired": addTired(1); break;
        case "heal": setEnergy(getEnergy() + 10); break;
        default: System.out.println(name + " is affected by " + effect);
    }
}
    

}
/*Graveyard
//as of 18 0836 no longer do we use:
    int[] belt={
        0,//sword: 3 normal, 4 silver, 5 amathyst 
        0,//umbrella: 6 normal, 7 silver, 8 amathyst
        0,//garlic bloom: 9 normal, 10 silver,11 amathyst
        0,//Silver cross 1
        0//Amathyst 2
    } ;
  // Inicial menu 0
        menuOptions.add(new ArrayList<>(Arrays.asList("Usar","Tomar","Hablar","Ir","Descansar","Accion")));
        // Usar menu 1
        menuOptions.add(new ArrayList<>(Arrays.asList()));
        // Tomar menu 2
        menuOptions.add(new ArrayList<>());
        // Hablar menu 3
        menuOptions.add(new ArrayList<>());
        // Ir menu 4
        menuOptions.add(new ArrayList<>(Arrays.asList("Norte","Sur","Este","Oeste")));
        // Descansar menu 5
        menuOptions.add(new ArrayList<>(Arrays.asList("Observar","Dar","Dejar")));
        // Accion objetivos menu 6
        menuOptions.add(new ArrayList<>());
        // Objetivos menu 7

 static String[][] menuOptions={{"Usar","Tomar", "Hablar", "Ir", "Descansar","Observar"},//0
    {"","", ""}//1, "Use
    //"Old Sword","Silver Sword","Enchanted Sword ","Garlic Bloom","Silver Garlic" ,"Enchanted Bloom", "Worn Umbrella", "Silver Umbrella", "Encahnted Umbrella",};
    ,{"","","", "", "", ""}//2 "Take"
    ,{"","","","", "", ""}//3 "Talk" 
    ,{"Norte", "Sur", "Este", "Oeste", "", ""}//4 "Go"
    ,{"tomar \n respiro", "", "", "", "", ""}//5 "Rest"
    ,{"", "", "","","","",""}//7 "Targets"
            //12 2157 what if i make a circular ocatagonal list for this as well and throw it all away
            /*i states the current menu (0-5) and j states the current option
            
           /*10: Debug system to prove the working function of arrays and their sizes
    public static void main(String[] args) {
        for(int x=0; x<=5;x++){
            for(int y=0; y<=5;y++){
                menuOptions[x][y]="a";
                System.out.print(menuOptions[x][y]+"\n");
            
        }
            System.out.println("\n");
        }

    public CharSheet() {
        //19 this Initialize menus with flexible lists. I do not know what im doing. going in blind.
        menuOptions.put("Inicial", new ArrayList<>(Arrays.asList("Usar","Tomar","Hablar","Ir","Descansar","Observar")));
        menuOptions.put("Usar", new ArrayList<>());//1, "Use" "Old Sword","Silver Sword","Enchanted Sword ","Garlic Bloom","Silver Garlic" ,"Enchanted Bloom", "Worn Umbrella", "Silver Umbrella", "Encahnted Umbrella",};
        menuOptions.put("Tomar", new ArrayList<>());//2 "Take"
        menuOptions.put("Hablar", new ArrayList<>());//3 "Talk" 
        menuOptions.put("Ir", new ArrayList<>(Arrays.asList("Norte","Sur","Este","Oeste")));//4 "Go"
        menuOptions.put("Descansar", new ArrayList<>(Arrays.asList("Tomar\nrespiro")));//5 "Rest"
        menuOptions.put("Objetivos", new ArrayList<>());//7 Targets
    }

currentMenu used to be an int
    }

  public List<String> getMenuOptions(String menuName) {
        return menuOptions.getOrDefault(menuName, new ArrayList<>());
    }

    //19: Get the currently selected option. Copilot has explained to me clearly everything once and twice and thrice. It has told me that i have done far more complicated things on my own, 
    //and that this is the right path.
    public String getOption() {
        List<String> options = menuOptions.getOrDefault(currentMenu, new ArrayList<>());
        if (selectedOption >= 0 && selectedOption < options.size()) {
            return options.get(selectedOption);
        }
        return "";
    }

    //19 Replace options for a given menu
    public void setOptions(String menuName, String[] newOptions) {
        List<String> options = menuOptions.get(menuName);
        if (options != null) {
            options.clear();
            options.addAll(Arrays.asList(newOptions));
        }
    }
    
     //19 Change selected option. The thing that terrifies me the most is the hasmap. Im scared to death of hashmaps.
    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }

    public void setCurrentMenu(int manu) {
            this.currentMenu = manu;
          
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public int getCurrentMenu() {
        return currentMenu;
    }
  public String getPreviousSelected() {
        return previousSelected;
    }

    public void setPreviousSelected(String previousSelected) {
        this.previousSelected = previousSelected;
    }

    //19 Get all options for a given menu. This experience is stressful and is making my stomach turn.
    public List<String> getMenuOptions(String menuName) {
        return menuOptions.getOrDefault(menuName, new ArrayList<>());
    }

    //19: Get the currently selected option. Copilot has explained to me clearly everything once and twice and thrice. It has told me that i have done far more complicated things on my own, 
    //and that this is the right path.
    public String getOption() {
        List<String> options = menuOptions.getOrDefault(currentMenu, new ArrayList<>());
        if (selectedOption >= 0 && selectedOption < options.size()) {
            return options.get(selectedOption);
        }
        return "";
    }

    //19 Replace options for a given menu
    public void setOptions(String menuName, String[] newOptions) {
        List<String> options = menuOptions.get(menuName);
        if (options != null) {
            options.clear();
            options.addAll(Arrays.asList(newOptions));
        }
    }
    
     //19 Change selected option. The thing that terrifies me the most is the hasmap. Im scared to death of hashmaps.
    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }

    public void setCurrentMenu(int manu) {
            this.currentMenu = manu;
          
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public int getCurrentMenu() {
        return currentMenu;
    }

*/ 
    
 /*   //int i=0, j=0;
    //String a=menuOptions[i][j];
 */
 