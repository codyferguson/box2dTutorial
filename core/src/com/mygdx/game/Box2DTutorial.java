package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.loader.B2dAssetManager;
import com.mygdx.game.views.*;

// ApplicationAdapter if you want to use your own screen management
// aka the Orchestrator
public class Box2DTutorial extends Game {
    private AppPreferences preferences;
	private LoadingScreen loadingScreen;
	private PreferencesScreen preferencesScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;
	public B2dAssetManager assetManager = new B2dAssetManager();
	private Music playingSong;

	public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int ENDGAME = 3;

	@Override
	public void create () {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
        preferences = new AppPreferences();

        // tells our asset manger that we want to load the images set in loadImages method
        assetManager.queueAddMusic();
        // tells the asset manager to load the images and wait until finished loading.
        assetManager.manager.finishLoading();
        // loads the 2 sounds we use
        playingSong = assetManager.manager.get("music/Rolemusic_-_pl4y1ng.mp3");

        playingSong.play(); // replace with loop to keep going
	}

	@Override //- not sure if it should be there or not
	public void render () {
        super.render(); // from stack overflow answer
	}
	
	@Override
	public void dispose () {
        playingSong.dispose();
        assetManager.manager.dispose();
	}

	public void changeScreen(int screen){
	    switch (screen){
            case MENU:
                if(menuScreen == null)
                    menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                if(preferencesScreen == null)
                    preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case APPLICATION:
                if(mainScreen == null)
                    mainScreen = new MainScreen(this);
                this.setScreen(mainScreen);
                break;
            case ENDGAME:
                if(endScreen == null)
                    endScreen = new EndScreen(this);
                this.setScreen(endScreen);
                break;
        }
    }

    public AppPreferences getPreferences() {
        return this.preferences;
    }
}
