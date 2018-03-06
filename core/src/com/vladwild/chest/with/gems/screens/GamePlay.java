package com.vladwild.chest.with.gems.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.StringBuilder;
import com.vladwild.chest.with.gems.InputProcessors.GamePlayInputProcessor;
import com.vladwild.chest.with.gems.InputProcessors.InputProcessorKeyboard;
import com.vladwild.chest.with.gems.InputProcessors.InputProcessorScreen;
import com.vladwild.chest.with.gems.buttons.gameplay.Back;
import com.vladwild.chest.with.gems.buttons.gameplay.Home;
import com.vladwild.chest.with.gems.buttons.gameplay.InvokerGamePlay;
import com.vladwild.chest.with.gems.buttons.gameplay.ReceiverGamePlay;
import com.vladwild.chest.with.gems.fonts.FactoryGamePlay;
import com.vladwild.chest.with.gems.gameplay.Arrow;
import com.vladwild.chest.with.gems.gameplay.Chest;
import com.vladwild.chest.with.gems.gameplay.DynamicObject;
import com.vladwild.chest.with.gems.gameplay.Field;
import com.vladwild.chest.with.gems.gameplay.Human;
import com.vladwild.chest.with.gems.gameplay.Key;
import com.vladwild.chest.with.gems.gameplay.StaticObject;
import com.vladwild.chest.with.gems.gameplay.StaticObjectField;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;
import com.vladwild.chest.with.gems.location.GamePlayInformation;
import com.vladwild.chest.with.gems.resources.GamePlayManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GamePlay implements Screen {
    private ChestWithGems game;
    protected SpriteBatch batch;

    protected String level;
    protected int speed;

    protected GamePlayInformation gpi;
    protected GamePlayManager gpm;

    protected OrthographicCamera camera;

    private InputMultiplexer inputMultiplexer;
    protected GamePlayInputProcessor gmip;
    protected List<Stage> stages;

    protected Texture background;

    protected StaticObjectField field;
    private DynamicObject human;
    protected Set<StaticObject> keys;
    private Iterator<StaticObject> iterKeys;
    protected StaticObject chest;
    protected StaticObject arrow;

    protected StringBuilder outCountAllKeys;
    private final int countAllKeys;

    protected BitmapFont nameLevel;
    protected BitmapFont numberLevel;
    protected BitmapFont nameKeys;
    protected BitmapFont countKeys;

    public GamePlay(ChestWithGems game, int level, int speed) {
        this.game = game;
        batch = game.getSpriteBatch();

        this.level = String.valueOf(level);
        this.speed = speed;

        gpi = new GamePlayInformation();
        gpm = new GamePlayManager();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, gpi.getWidthGamePlay(), gpi.getHeightGamePlay());

        inputMultiplexer = new InputMultiplexer();
        gmip = new InputProcessorScreen(gpi.getOffset(), gpi.getDefaultDirection());
        inputMultiplexer.addProcessor((InputProcessorScreen) gmip);
        inputMultiplexer.addProcessor(new InputProcessorKeyboard(gpi.getDefaultDirection()));
        stages = new ArrayList<Stage>();

        background = new Texture(gpm.getBackground());

        field = new Field(gpi, gpm, level);
        human = new Human(gpi, gpm, field.getHumanPoint(), gmip, field.getMatrixLogic());
        keys = new HashSet<StaticObject>(field.getKeysPoints().size());
        for (GridPoint2 keyPoint : field.getKeysPoints()) {
            keys.add(new Key(gpi, gpm, keyPoint));
        }
        chest = new Chest(gpi, gpm, field.getChestPoint());
        arrow = new Arrow(gpi, gpm, gmip);

        countAllKeys = keys.size();

        outCountAllKeys = new StringBuilder();
        outCountAllKeys.append(gpi.getDefaultCountKeys());
        outCountAllKeys.append(gpi.getDelimiter());
        outCountAllKeys.append(keys.size());

    }

    @Override
    public void show() {
        ReceiverGamePlay rgp = new ReceiverGamePlay(game, gpi, gpm);
        InvokerGamePlay igp = new InvokerGamePlay(new Home(rgp),
                new Back(rgp));

        stages.add(igp.getHome());
        stages.add(igp.getBack());

        for (Stage stage : stages) {
            inputMultiplexer.addProcessor(stage);
        }

        nameLevel = FactoryGamePlay.getTypeFont(FactoryGamePlay.NAME_LEVEL, gpi, gpm);
        numberLevel = FactoryGamePlay.getTypeFont(FactoryGamePlay.NUMBER_LEVEL, gpi, gpm);
        nameKeys = FactoryGamePlay.getTypeFont(FactoryGamePlay.NAME_KEYS, gpi, gpm);
        countKeys = FactoryGamePlay.getTypeFont(FactoryGamePlay.COUNT_KEYS, gpi, gpm);

    }

    protected boolean isAttemptRemoveKey(){
        return !keys.isEmpty() && human.isCenterLogicalSquare();
    }

    private void changeCountAllKeys(){
        outCountAllKeys.delete(0, outCountAllKeys.length());
        outCountAllKeys.append(countAllKeys - keys.size());
        outCountAllKeys.append(gpi.getDelimiter());
        outCountAllKeys.append(countAllKeys);
    }

    protected void removeKey(){
        iterKeys = keys.iterator();
        while (iterKeys.hasNext()){
            if (human.getPositionPixel().equals(iterKeys.next().getPositionPixel())){
                iterKeys.remove();
                changeCountAllKeys();
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.input.setInputProcessor(inputMultiplexer);

        batch.begin();
        batch.draw(background, gpi.getBGRect().x, gpi.getBGRect().y, gpi.getBGRect().width, gpi.getBGRect().height);
        batch.draw(field.getTexture(), field.getPositionPixel().x, field.getPositionPixel().y);
        for (StaticObject key : keys) {
            batch.draw(key.getTexture(), key.getPositionPixel().x, gpi.getBlank() + key.getPositionPixel().y);
        }
        batch.draw(chest.getTexture(), chest.getPositionPixel().x, gpi.getBlank() + chest.getPositionPixel().y);
        batch.draw(human.getTexture(), human.getPositionPixel().x, gpi.getBlank() + human.getPositionPixel().y);
        batch.draw(arrow.getTexture(), arrow.getPositionPixel().x, arrow.getPositionPixel().y);
        nameLevel.draw(batch, gpi.getLevel(), gpi.getPositionNameLevel().x, gpi.getPositionNameLevel().y);
        numberLevel.draw(batch, level, gpi.getPositionNumberLevel().x, gpi.getPositionNumberLevel().y);
        nameKeys.draw(batch, gpi.getKeys(), gpi.getPositionNameKeys().x, gpi.getPositionNameKeys().y);
        countKeys.draw(batch, outCountAllKeys.toString(), gpi.getPositionCountKeys().x, gpi.getPositionCountKeys().y);
        batch.end();

        human.move(gmip.getDirection(), speed);

        if (isAttemptRemoveKey()) removeKey();

        for (Stage stage : stages) {
            stage.draw();
        }

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
