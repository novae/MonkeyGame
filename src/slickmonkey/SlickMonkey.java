
package slickmonkey;


import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class SlickMonkey extends BasicGame{
    private TiledMap map;
    private  float monkeyX,monkeyY=150;
    private Animation Monkey;
    SpriteSheet sheet;
    int [] steps={0,1,2};
    
    public SlickMonkey(String title) throws SlickException {
     super(title);
    }


    public static void main(String[] args) {
        try{
            AppGameContainer app = new AppGameContainer(new SlickMonkey("Monkey"));
            app.setDisplayMode(300, 300, false);
            app.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
       gc.setVSync(true);
       sheet = new SpriteSheet("resources/monkey.png",30,30);
       map = new TiledMap("resources/map.tmx");
       Monkey = new Animation();
       Monkey.setAutoUpdate(true);
       moveMonkey(steps,0);
       
    }
    
    public void moveMonkey(int [] steps,int y){
        for (int frame=0; frame < steps.length-1 ;frame++){
            Monkey.addFrame(sheet.getSprite(steps[frame],y),150);
        }
        
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            monkeyX--;
            moveMonkey(steps,1);
           
        }
	if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            monkeyX++;
            moveMonkey(steps,2);
            
        }
	if (gc.getInput().isKeyDown(Input.KEY_UP)) {
            monkeyY--;
            moveMonkey(steps,3);
            
            
        }
	if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            monkeyY++;
            moveMonkey(steps,0);
            
        }
    }
    
   

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        map.render(0, 0);
        grphcs.drawAnimation(Monkey, monkeyX, monkeyY);
    }
    
    
}
