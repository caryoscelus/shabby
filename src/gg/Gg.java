package gg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Gg extends Game {
    MapScreen mapScreen = new MapScreen();
    
    @Override
    public void create () {
        
        
        
        setScreen(mapScreen);
    }

    @Override
    public void render () {
        getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
        
    }
}
