package com.vladwild.chest.with.gems.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vladwild.chest.with.gems.buttons.start.Author;
import com.vladwild.chest.with.gems.buttons.start.Exit;
import com.vladwild.chest.with.gems.buttons.start.InvokerStart;
import com.vladwild.chest.with.gems.buttons.start.Methods;
import com.vladwild.chest.with.gems.buttons.start.Play;
import com.vladwild.chest.with.gems.buttons.start.ReceiverStart;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;
import com.vladwild.chest.with.gems.location.StartInformation;
import com.vladwild.chest.with.gems.resources.StartManager;

import java.util.ArrayList;
import java.util.List;

public class Start implements Screen {
    private ChestWithGems game;
    private SpriteBatch batch;

    private StartInformation si;
    private StartManager sm;

    private OrthographicCamera camera;

    private Texture background;
    private Texture name;
    private Texture cheat;

    private List<Stage> stages;
    private InputMultiplexer inputMultiplexer;

    public Start(ChestWithGems game){
        this.game = game;
        batch = game.getSpriteBatch();

        si = new StartInformation();
        sm = new StartManager();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, si.getWidth(), si.getHight());

        background = new Texture(sm.getBackground());
        name = new Texture(sm.getName());
        cheat = new Texture(sm.getCheat());

        stages = new ArrayList<Stage>();
        inputMultiplexer = new InputMultiplexer();
    }

    @Override
    public void show() {
        ReceiverStart rs = new ReceiverStart(game, si, sm);
        InvokerStart is = new InvokerStart(new Play(rs),
                new Methods(rs),
                new Author(rs),
                new Exit(rs));

        stages.add(is.getPlay());
        stages.add(is.getMethods());
        stages.add(is.getAuthor());
        stages.add(is.getExit());

        for (Stage stage : stages) {
            inputMultiplexer.addProcessor(stage);
        }

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.input.setInputProcessor(inputMultiplexer);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, si.getBGRect().x, si.getBGRect().y, si.getBGRect().width, si.getBGRect().height);
        batch.draw(name, si.getNameRect().x, si.getNameRect().y, si.getNameRect().width, si.getNameRect().height);
        batch.draw(cheat, si.getCheatRect().x, si.getCheatRect().y, si.getCheatRect().width, si.getCheatRect().height);
        batch.end();

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
