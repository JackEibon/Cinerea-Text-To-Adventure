package gamecontentlogic;


import java.io.IOException;
import java.util.*;
/*
 * Author: Eibon
 * added in class of 24/2/2026 at 1056 from New Cinerea Netbeans Project
 * Resume: Used to be  the main in the project beforehand
 * status: requires heavy modifications and simplification
 * */

/*
 * Log 1: 11/11/2025 
 * As of now, im beggining to register most advances here in eclipse today.
 * Listening to Queen´s  Spread your Wings, then ill hear a ton of queen´s songs
 * worldGenesis & maParade logic finished
 * pending:
 * All canvas related
 * * Letters, Strings to letters, Window that displays the strings, pointer.
 * All dynamics related (Items, PlayerState)
 * *PlayerMove Logic (Player is a pointer in the list, will move across the nodes/rooms)
 * All rooms IDS
 * (the BIG dream is at least 100 rooms. Will stay with 25 rooms as of now, maybe even 10.)
 * 
 */

/*
 * Log 2: 12/11/2025 
 * I have paid for others to get original artwork the way i want, and i am
 * slowly losing my mind. I am incredibly anxious, not about my project
but in general. Today i am importing everything to netbeans.
As of 17:40, i had finished updating and importing everything from eclipse, and
updating a lot of methods i was very proud of.
As of 17:56, i have lost all progress, or most of it, of today.
This is a lesson, i will use further Ctrl+s.

 */

/*
 * Log 3: 13/11/2025 
 * As of 3:58 Am, i have finished working with a friend to make the letters and
other chars. i will reward them for their troubles. I also updated partially
all canvas systems, and have advanced GameWindow. now i understand hasmap. I 
also spent 2 good hours designing the logic of items, the enemy and how would
a coop work. I will try to make a 2 player turn cooperative game that uses 2 
windows. It is kind of an rpg or text to adventure. But it will mostly use 6
buttons. Accept, cancel and four arrows. My only problem is the branching
decision pathways! such a headache. i have 65 items planned, which are
variations and combinations 15 items and the actions of 3 npcs.
I dont have the time to make much of this, do i?


 */

/*
 * Log 4: 13/11/2025 
 * As of 12:00, i recurred to copilot in search for wisdom about the canvas.
 * it has scolded me gently about the scope of my game.
* It has also introduced me to the great idea of creating a layering class,
* which i have already circumvented the need of in my designs, and even if on
the surface i think it looks easier, i will handle it in a more standard manner,
i think.
 */

/*
 * Log 5: 14/11/2025 
 * As of 18:12, i am in a hurry, but i  worked a ton with copilot and 
Data Structure teacher on GameThread
 */

/*Log 6: 15/11/2025
As of 10:05, a familiar is driving me to the workplace, so i have around an hour
to fool around and tinker GameThread while im on the car.
1015 did nothing other than read copilot´s suggestions  and explanations.
got extremely dizzy
*/

/*Log 7: 15/11/2025
As of 1140-1250, slight tinkering in GameThread while in work pauses.
Log 8: 15/11/2025
As of 1500-16XX, same
i did manage to print multiline strings with a drawn font by me and my friends!
*/

/*
Log 9: 16/11/2025 0139
my computer broke. I did have a backup of my project as per teacher of data
estructure suggestion, and my friends are kind enougth to gift me a co<mputer
by mondat <3 And i grabbed a tv from my grandma to substitute meanwhile. Truely,
i been blessed by virtous people.
*/

/*
Log 10: 16/11/2025 0759
Couldnt do much yesterday, but lets push today!
Im happy with my menu class, which i fused with the GameMaster class by the end.
It was a very enriching experience, coupled with a pair of draw method
updates and redraws of sprites i had.  1725. I been making the thing for almost 
10 hours, with occasional rest. Only like this i will be satisfied.
*/

/*
Log 11: 16/11/2025 1725
time to back all this up.
1736
backed all up, lets continue
*/

/*
Log 12: 16/11/2025 2000
Man Gamethread and menu got hands
Finished!!
*/

/*
Log 13 17/11/25 0020
The ideas swirl, my head hurts, and i cannot sleep. Lets give our best!
*/

/*
Log 14 17/11/25 0800
seems like i will have to do two new methods in Node: initState, secondState.
Hoy me acorde que mis profesores casi no hablan ingles, asi que empezare a traducir al español
*/

/*
Log 15 17/11/25 1200-2151
Me siento muy orgulloso de mi "SceneManager" pero a copilot le desespera y 
ciertamente es muy batalloso leer entre tantos switchcases. Respaldare e 
intentare la propuesta de copilot. Todo el dia batalle arreglando todo tipo 
de detalles, creo que aprendi mucho. Si la propuesta funciona, podran
ver los anteriores metodos en "Node" con el Log 15 o el 16.
*/
/*
Log 16 18/11/25 0100, 0942
Despues de arreglar el texto cuando me desperte en la mañana, recogere mi nueva
laptop y pasare la información con mi respaldo. Aprovechare para dar vueltas
del trabajo tambien.

*/

/*Log 17 18/11/25 1545
escribiendo escenarios en el cel mientras se carga mi compu
estaba en WhatsApp pero instale un programa muy bueno*/

/*Log 18 19/11/25 0715
Apenas ahora pude terminar de instalar netbeans*/
public class Cinerea {
    public static L_Octagonal world= new L_Octagonal();//14.
    public static GameMaster gameMaster= new GameMaster();//15
    //static int  xsc=(gameMaster.d(4)+1),ysc=0,zsc=0,wsc=1; made in 23, discarded as of 24/11/2025
   
    

    
  //  public static void main(String[] args) throws IOException {
       /* do{
            ysc=(gameMaster.d(4)+1);
        }while(ysc==xsc);
        do{
            zsc=(gameMaster.d(4)+1);
        }while(zsc==xsc||zsc==ysc);
        do{
            wsc++;
        }while(zsc==wsc||wsc==xsc||wsc==ysc);*/
       
        
        L_Octagonal w= new L_Octagonal();
        gameMaster.addScene(new Scene(
        1, //id
        "Mooncrash",//Scene name
        "En el comodo crater,"
                + "una luz cinerea resplandece "
                + "tenuemente.",
        "una tenue y \ntitinante luz cinerea\nresplandece",
        "prado",
                "normal",
                "normal",
        new String[]{}, // Go options
        new String[]{}, // take options
        new String[]{"Viento"}, // talk options
        new String[]{"Acunarse\nen el\ncrater","Tomar un\n respiro"},
        "",
        "No hay nada para recoger;"
                + "\nno trajiste nada contigo"
                + "\n¿o perdiste todo?",
        "Puedes hablar solo "
                + "\no con el viento;"
                + "\npero no servira de nada",
        "Puedes cerrar los ojos "
                + "\nAcostarte en la tierra;"
                + "\nmirar el cielo en nostalgia"
         + "\npero recuerda que hay algo tras de ti"
                
));
        //gameMaster.loadScenes("C:\\Users\\jacke\\Documents\\NetBeansProjects\\Cisnerea\\src\\scenes");
        gameMaster.addScene(new Scene(
        3, //id
        "Old Battlefield",//Scene name
        "espadas y lanzas oxidadas, quebradizas\n "
                            + "el humano no abandona \nel concepto de la guerra.\n "
                            + "pero si su pena y su amor.\n ",
        "armas \noxidadas y restos antiguos"
                + "\nde batalla empiezan \na ser mas recurrentes",
        "prado",
                "normal",
                "embrujado",
        new String[]{""}, // Go options
        new String[]{"Arma"}, // take options
        new String[]{""}, // talk options
        new String[]{"Acunarse\n  en\n el hierro","Tomar un\n respiro"},
        "",
        "Las armas ya no "
                + "\nresplandecen"
                + "\npero una espada"
                + "\ncapta tu atención"
                + "\nsobre el prado"
                + "\nno se ha oxidado mucho"
                + "\ny resplandece con la luz"
                + "\nde la luna que suele cautivar",
        "",
        "Los campos de guerra "
                + "\nno son acogedores;"
                + "\npero puedes fingir ser"
         + "\nuno de los muchos cadaveres"
                + "\nque alguna vez descansaron"
                + "\naqui tambien."
        
        
));

gameMaster.addScene(new Scene(
        4, //id
        "Gardenheart",//Scene name
        "Flores hermosas de\n"
                + "colores vividos,\n"+
        "adornando los alrededores."
                + "\nLa fragancia es dulce\n"
                + "e intensa como pocas\n"
                + "verdaderamente, un lindo\nlugar",
        "un camino de\nflores llevando a una\ncampina colorida",
        "prado",
                "normal",
                "normal",
        new String[]{}, // Go options
        new String[]{}, // take options
        new String[]{"A las\n"
                + "Flores"}, // talk options
        new String[]{"Acunarse\nen el\njardin","Tomar un\n respiro"},
        "",
        "No hay nada para recoger;"
                + "\nno trajiste nada contigo"
                + "\n¿o perdiste todo?",
        "Puedes hablar solo "
                + "\no con el viento;"
                + "\npero no servira de nada",
        "Puedes cerrar los ojos "
                + "\nAcostarte en la tierra;"
                + "\nmirar el cielo en nostalgia"
         + "\npero recuerda que hay algo tras de ti"
                
));


gameMaster.addScene(new Scene(
        5, //id
        "Abandoned House",//Scene name
        "Una cabana\n"
                + "con huecos y sin puerta,\n"+
        "destruida y descuidada,"
                + "\nno hay nada dentro\n"
                + "pero puede dar parcial\n"
                + "refugio, a decir verdad,\nno mucho",
        "hay una \ncabana en mal estado",
        "prado",
                "normal",
                "normal",
        new String[]{}, // Go options
        new String[]{}, // take options
        new String[]{}, // talk options
        new String[]{"Dentro\nde la cabana","Tomar un\n respiro"},//rest options
        "",
        "No hay nada para recoger;"
                + "\nno trajiste nada contigo"
                + "\n¿o perdiste todo?",
        "Puedes hablar solo "
                + "\no con el viento;"
                + "\npero no servira de nada",
        "Puedes cerrar los ojos "
                + "\nAcostarte en la tierra;"
                + "\nmirar el cielo en nostalgia"
         + "\npero recuerda que hay algo tras de ti"
));

gameMaster.addScene(new Scene(
        6, //id
        "Offering Statue",//Scene name
        "Un gran  espejo hacia el\ncielo"
                + " sobre un pilar de\npiedra"+
        "brilla resplandeciente"
                + "\ncon un unico pedestal\n"
                + "debajo de el.\n"
                + "Una ofrenda\n"
                + "es esperada",
        "ya hace un \ngran "
                + "reflejo de luz",
        "prado",
                "normal",
                "normal",
        new String[]{}, // Go options
        new String[]{}, // take options
        new String[]{}, // talk options
        new String[]{"Tomar un\n respiro"},
        "",
        "No hay nada para recoger;"
                + "\nno trajiste nada contigo"
                + "\n¿o perdiste todo?",
        "Puedes hablar solo "
                + "\no con el viento;"
                + "\npero no servira de nada",
        "Puedes cerrar los ojos "
                + "\nAcostarte en la tierra;"
                + "\nmirar el cielo en nostalgia"
         + "\npero recuerda que hay algo tras de ti"
                
));

gameMaster.addScene(new Scene(
        7, // id
        "Rope Bridge", // Scene name
        "Un puente de madera y cuerdas\n"
        + "se extiende sobre un abismo inmenso.\n"
        + "El viento sopla fuerte,\n"
        + "y cada paso hace crujir la madera.",
        " un puente\n"
        + "suspendido sobre un\nacantilado profundo y\nsin fondo",
        "acantilado",
        "normal",
        "normal",
        new String[]{"Norte","Sur"}, // Go options (but restricted in play)
        new String[]{}, // take options (cutting rope)
        new String[]{"Gritar al\nabismo"}, // talk options
        new String[]{"Tomar un\nrespiro"},
        "",
        "No hay nada que recoger;\n",
        "Tu voz se pierde en el vacío,\n"
        + "el eco responde.",
        "Puedes descansar un momento,\n"
        + "pero el puente nunca deja de moverse."
));

gameMaster.addScene(new Scene(
        27, // id
        "Rope Bridge", // EW Scene name
        "Un puente de madera y cuerdas\n"
        + "se extiende sobre un abismo inmenso.\n"
        + "El viento sopla fuerte,\n"
        + "y cada paso hace crujir la madera.",
        " un puente\n"
        + "suspendido sobre un\nacantilado profundo y\nsin fondo, de este a oeste",
        "acantilado",
        "normal",
        "normal",
        new String[]{"Norte","Sur"}, // Go options (but restricted in play)
        new String[]{}, // take options (cutting rope)
        new String[]{"Gritar al\nabismo"}, // talk options
        new String[]{"Tomar un\nrespiro"},
        "",
        "No hay nada que recoger;\n",
        "Tu voz se pierde en el vacío,\n"
        + "el eco responde.",
        "Puedes descansar un momento,\n"
        + "pero el puente nunca deja de moverse."
));
        for (int i=0;i<=w.getMapSize();i++){
            if(gameMaster.getScene(i)==null&& i==(w.getMapSize())){
                gameMaster.addScene(new Scene(
        i, //id
        "WolfCave",//Scene name
        "Una enorme cueva\n"
                + "de la cual un gelido vientp\n"+
        "brota ritmico"
                + "\ncomo una respiracion\n"
                + "o un aliento\n",
        "un gelido viento proviene\n"
                + "como el invierno\n"
                + "anunciandose",
        "prado",
                "normal",
                "normal",
        new String[]{}, // Go options
        new String[]{""}, // take options
        new String[]{""}, // talk options
        new String[]{"Tomar un\n respiro"},
        "La cueva invita\nhacia sus adentros",
        "",
        "",
        ""             
));
            }
            else if(gameMaster.getScene(i)==null){
                gameMaster.addScene(new Scene(
        i, //id
        "old road",//Scene name
        "Es un largo camino",
        "no hay nada\nespecial",
        "prado",
                "normal",
                "normal",
        new String[]{""}, // Go options
        new String[]{""}, // take options
        new String[]{""}, // talk options
        new String[]{"Tomar un\n respiro"},
        "",
        "",
        "",
        "recuerda que hay algo tras de ti" 
));
                
            }   
        }        
        GameWindow game = new GameWindow();
    }

    
}
/*Game Design
as of 12/11/2025 at 10:12 pm, i am debating a lot of the design of the game
as far i as i have understood, we make games already designed in concept
as to only worry about the programming and else.
that said, i live to create, and to make new ideas possible, so:

key items:8 (as of log 10, it has been decided that 3 items will be enought)
there will be 8 different items. 
if the game ever is able to be played by two 
players, each will be able to hold 5 alone.

ingredients: 5 (as of log 2, it has been decided that 3 items will be enought)
5 different base ingredients, 1-3 of each found in the world.
each ingredient can be made into other products. for this scale of the game,
5 base ingredients will suffice. Only 10 in total can be found in the world.
one of each, then is up to players decisions and luck.

The ingredients and items will be combined to generate new items.
Each key item can only be combined with one ingredient.


//SPOILERS//
if you want to play the game as designed, do not read the next lines:
goal of the game: find safety from the wolf.
You gain safety from the wolf by doing one of three things:
surviving 3 nights, repairing the moon, or killing the wolf.
The next facts of the wolf are true:
the wolf is afraid of fire
the wolf can be killed by two hits of silver alone
the wolf is stomped by ligthning
the wolf has fast reflexes, he will dodge sword attacks and 50% of bullets. 
the wolf will flee when hurt, and recover in 3 rounds.
Each "surprise" stack will further any chance or action against the wolf
by 12.5% of success and 12.5% of critical success.
Wolf cannot smell you past garlic.

Key ITEMS:
Umbrella(21)
Bell(31)
Gun(41)
Shield(51)
Sword(61)
3/2/1 Bullets(91, 81, 71)
Garlic(101)
Flask(111)
Flask(111)
Flask(111)



Ingredients:
Silver cross(1)
Ruby(3)
Zaphire(6)
Amethyst(9)
Emerald(12)

NPCs:
Blasmith
Mage
Crusher

Process
Forge into
Enchantment
Crush
Potion

Crush ingredient: ingredient+1
Enchant ingredient: ingredient+2
Forge into: item+ingredient
Potion: item(111)+ingredient
so silver dust is ID 3, while enchanted silver is 2 Crushed and enchanted
does not exist without a flask.

The blacksmith can:
enchant some items <102 with enchanted gems
enhance weapons with raw silver
cannot work with dust, flasks or garlic

work with dust and enchanted materials but not raw
the mage can:
enchant key items/make potions with dust
enchant gems
does not work with the raw silver cross

Crusher makes everything dust.

As of log 4, at 4;21 pm,

I have established that i will throw away most items and i will make 5 base 
items and 11 in total. being two ingredients (the cross and the amethyst) and
the umbrella (which will work as a shield), the sword and the garlic bloom, and 
the blacksmith alone will be the only NPC in existance.



credits:
Designer, Director and composer:
Eibon

Text Font:
Eibon & Maritza Castellon

Animations and Items:
Eibon & KaoriDraws

Teachers & guides
Richi
Arce
Copilot
*/
