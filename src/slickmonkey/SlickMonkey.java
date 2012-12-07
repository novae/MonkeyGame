
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
    private Polygon MonkeyPolygon;
    public BlockMap mapblock;
    
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
       //map = new TiledMap("resources/mapa.tmx");
       mapblock = new BlockMap("resources/mapa.tmx");
       Monkey = new Animation();
       Monkey.setAutoUpdate(true);
       moveMonkey(steps,0);
       
       MonkeyPolygon = new Polygon(new float[]{
				monkeyX,monkeyY,
				monkeyX+30,monkeyY,
				monkeyX+30,monkeyY+30,
				monkeyX,monkeyY+30
		});
       
    }
    
    public void moveMonkey(int [] steps,int y){
        for (int frame=0; frame < steps.length-1 ;frame++){
            Monkey.addFrame(sheet.getSprite(steps[frame],y),150);
        }
        
    }
    
    public boolean entityCollisionWith()throws SlickException{
        for(int i = 0; i < BlockMap.entities.size(); i ++){
            Block entity1 = (Block)BlockMap.entities.get(i);
            if(MonkeyPolygon.intersects(entity1.poly)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            monkeyX--;
            moveMonkey(steps,1);
            MonkeyPolygon.setX(monkeyX);
            if(entityCollisionWith()){
                monkeyX++;
                MonkeyPolygon.setX(monkeyX);
            }
        }
	if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            monkeyX++;
            moveMonkey(steps,2);
            MonkeyPolygon.setX(monkeyX);
            if(entityCollisionWith()){
                monkeyX--;
                MonkeyPolygon.setX(monkeyX);
            }
            
        }
	if (gc.getInput().isKeyDown(Input.KEY_UP)) {
            monkeyY--;
            moveMonkey(steps,3);
            MonkeyPolygon.setY(monkeyY);
            if(entityCollisionWith()){
                monkeyX++;
                MonkeyPolygon.setY(monkeyY);
            }
        }
	if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            monkeyY++;
            moveMonkey(steps,0);
            MonkeyPolygon.setY(monkeyY);
            if(entityCollisionWith()){
                monkeyX--;
                MonkeyPolygon.setY(monkeyY);
            }
        }
    }
    
   

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        BlockMap.tmap.render(0, 0);
        grphcs.drawAnimation(Monkey, monkeyX, monkeyY);
        grphcs.draw(MonkeyPolygon);
    }
    
    
}
