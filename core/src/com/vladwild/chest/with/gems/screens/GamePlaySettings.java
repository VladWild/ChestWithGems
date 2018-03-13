package com.vladwild.chest.with.gems.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vladwild.chest.with.gems.buttons.gameplaysettings.*;
import com.vladwild.chest.with.gems.buttons.gameplaysettings.Start;
import com.vladwild.chest.with.gems.fonts.GamePlaySettingsFontFactory;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;
import com.vladwild.chest.with.gems.location.GamePlaySettingsInformation;
import com.vladwild.chest.with.gems.resources.GamePlaySettingsManager;

import java.util.ArrayList;
import java.util.List;

public class GamePlaySettings implements Screen {
    private static final int COUNT_LEVEL = 3;
    private static final int MAX_SPEED = 128;

    private ChestWithGems game;
    private SpriteBatch batch;

    private OrthographicCamera camera;

    private Texture background;

    private GamePlaySettingsInformation gpsi;
    private GamePlaySettingsManager gpsm;

    private List<Stage> stages;
    private InputMultiplexer inputMultiplexer;

    private int currentLevel;
    private int currentSpeed;

    protected BitmapFont level;
    protected BitmapFont numberLevel;
    protected BitmapFont speed;
    protected BitmapFont valueCurrentSpeed;

    public GamePlaySettings(ChestWithGems game){
        this.game = game;
        batch = game.getSpriteBatch();

        gpsi = new GamePlaySettingsInformation();
        gpsm = new GamePlaySettingsManager();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, gpsi.getWidth(), gpsi.getHight());

        background = new Texture(gpsm.getBackground());

        stages = new ArrayList<Stage>();
        inputMultiplexer = new InputMultiplexer();

        currentLevel = 1;
        currentSpeed = 4;
    }

    @Override
    public void show() {
        ReceiverGamePlaySettings rgps = new ReceiverGamePlaySettings(game, this, gpsi, gpsm);
        InvokerGamePlaySettings igps = new InvokerGamePlaySettings(new Start(rgps),
                new LeftLevel(rgps),
                new RightLevel(rgps),
                new LeftSpeed(rgps),
                new RightSpeed(rgps),
                new Back(rgps));

        stages.add(igps.getStart());
        stages.add(igps.getLeftLevel());
        stages.add(igps.getRightLevel());
        stages.add(igps.getLeftSpeed());
        stages.add(igps.getRightSpeed());
        stages.add(igps.getBack());

        for (Stage stage : stages) {
            inputMultiplexer.addProcessor(stage);
        }

        level = GamePlaySettingsFontFactory.getTypeFont(GamePlaySettingsFontFactory.LEVEL, gpsi, gpsm);
        numberLevel = GamePlaySettingsFontFactory.getTypeFont(GamePlaySettingsFontFactory.NUMBER_LEVEL, gpsi, gpsm);
        speed = GamePlaySettingsFontFactory.getTypeFont(GamePlaySettingsFontFactory.SPEED, gpsi, gpsm);
        valueCurrentSpeed = GamePlaySettingsFontFactory.getTypeFont(GamePlaySettingsFontFactory.VALUE_CURRENT_SPEED, gpsi, gpsm);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.input.setInputProcessor(inputMultiplexer);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, gpsi.getBGRect().x, gpsi.getBGRect().y, gpsi.getBGRect().width, gpsi.getBGRect().height);
        level.draw(batch, gpsi.getLevel(), gpsi.getPositionLevel().x - (gpsi.getLevel().length() * level.getSpaceWidth() / 2), gpsi.getPositionLevel().y);
        numberLevel.draw(batch, String.valueOf(currentLevel), gpsi.getPositionNumberLevel().x - (String.valueOf(currentLevel).length() * numberLevel.getSpaceWidth() / 2), gpsi.getPositionNumberLevel().y);
        speed.draw(batch, gpsi.getSpeed(), gpsi.getPositionSpeed().x - (gpsi.getSpeed().length() * speed.getSpaceWidth() / 2), gpsi.getPositionSpeed().y);
        valueCurrentSpeed.draw(batch, String.valueOf(currentSpeed), gpsi.getPositionValueCurrentSpeed().x - (String.valueOf(currentSpeed).length() * valueCurrentSpeed.getSpaceWidth() / 2), gpsi.getPositionValueCurrentSpeed().y);
        batch.end();

        for (Stage stage : stages) {
            stage.draw();
        }

    }

    public int getCountLevel(){
        return COUNT_LEVEL;
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
