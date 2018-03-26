package com.vladwild.chest.with.gems.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vladwild.chest.with.gems.buttons.gameplaysettings.FactoryGamePlaySettingsButtons;
import com.vladwild.chest.with.gems.fonts.Director;
import com.vladwild.chest.with.gems.fonts.Font;
import com.vladwild.chest.with.gems.fonts.gameplaysettings.Level;
import com.vladwild.chest.with.gems.fonts.gameplaysettings.LevelValue;
import com.vladwild.chest.with.gems.fonts.gameplaysettings.Speed;
import com.vladwild.chest.with.gems.fonts.gameplaysettings.SpeedValue;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;
import com.vladwild.chest.with.gems.location.Information;
import com.vladwild.chest.with.gems.resources.GamePlaySettingsManager;
import com.vladwild.chest.with.gems.utilities.Converter;

import java.util.ArrayList;
import java.util.List;

public class GamePlaySettings implements Screen {
    private static final int MAX_LEVEL = 3;
    private static final int MAX_SPEED = 128;

    private ChestWithGems game;
    private SpriteBatch batch;

    private OrthographicCamera camera;

    private Texture background;

    private Information info;
    private GamePlaySettingsManager gpsm;

    private List<Stage> buttons;
    private InputMultiplexer inputMultiplexer;

    private Converter<Integer, String> intToStr;

    private int currentLevel = 1;
    private int currentSpeed = 4;

    private Font level;
    private Font levelValue;
    private Font speed;
    private Font speedValue;

    public GamePlaySettings(ChestWithGems game){
        this.game = game;
        batch = game.getSpriteBatch();

        info = new Information(){};
        gpsm = new GamePlaySettingsManager();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, info.getWidth(), info.getHight());

        background = new Texture(gpsm.getBackground());

        buttons = new ArrayList<Stage>();
        inputMultiplexer = new InputMultiplexer();

        intToStr = String::valueOf;
    }

    @Override
    public void show() {
        buttons.addAll(FactoryGamePlaySettingsButtons.getButtons(game, this));

        buttons.forEach(inputMultiplexer::addProcessor);

        level = Director.getFont(new Level());
        levelValue = Director.getFont(new LevelValue());
        speed = Director.getFont(new Speed());
        speedValue = Director.getFont(new SpeedValue());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.input.setInputProcessor(inputMultiplexer);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, info.getBGRect().x, info.getBGRect().y, info.getBGRect().width, info.getBGRect().height);
        level.draw(batch, level.getText(), level.getPosition().x, level.getPosition().y);
        levelValue.draw(batch, intToStr.convert(currentLevel), speedValue.getCalculatedX(intToStr.convert(currentLevel)), levelValue.getPosition().y);
        speed.draw(batch, speed.getText(), speed.getPosition().x, speed.getPosition().y);
        speedValue.draw(batch, intToStr.convert(currentSpeed), speedValue.getCalculatedX(intToStr.convert(currentSpeed)), speedValue.getPosition().y);
        batch.end();

        buttons.forEach(Stage::draw);
    }

    public int getMaxLevel(){
        return MAX_LEVEL;
    }

    public int getMaxSpeed(){
        return MAX_SPEED;
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    public int getCurrentSpeed(){
        return currentSpeed;
    }

    public void setCurrentLevel(int currentLevel){
        this.currentLevel = currentLevel;
    }

    public void setCurrentSpeed(int currentSpeed){
        this.currentSpeed = currentSpeed;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
