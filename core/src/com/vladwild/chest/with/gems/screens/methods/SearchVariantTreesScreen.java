package com.vladwild.chest.with.gems.screens.methods;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.StaticObject;
import com.vladwild.chest.with.gems.gameplay.methods.DynamicObjectMethods;
import com.vladwild.chest.with.gems.gameplay.methods.HumanMethods;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;
import com.vladwild.chest.with.gems.screens.GamePlay;
import com.vladwild.chest.with.gems.methods.SearchVariantTrees;

import java.util.ArrayDeque;
import java.util.Deque;

public class SearchVariantTreesScreen extends GamePlay implements Screen{
    private Deque<Deque> deque;

    private DynamicObjectMethods humanMethods;

    private GridPoint2 startPositionLogic;
    private GridPoint2 startPositionPixel;

    private Direction currentDiriction;

    private InputMultiplexer inputMultiplexer;

    private int count = 0; //временно

    public SearchVariantTreesScreen(ChestWithGems game, int level, int speed, int limit, boolean all) {
        super(game, level, speed);

        SearchVariantTrees svt = new SearchVariantTrees(field.getMatrixLogic(),
                field.getHumanPoint(), field.getChestPoint(), field.getKeysPoints(), limit);
        if (all){
            deque = svt.function();
        } else {
            deque = new ArrayDeque<Deque>();
            deque.offer((Deque<Direction>) svt.function().getLast());
            System.out.println(svt.function().size());
        }

        humanMethods = new HumanMethods(gpi, gpm, field.getHumanPoint(), gmip, field.getMatrixLogic());

        startPositionLogic = new GridPoint2(humanMethods.getPositionLogic().x, humanMethods.getPositionLogic().y);
        startPositionPixel = new GridPoint2(humanMethods.getPositionPixel().x, humanMethods.getPositionPixel().y);

        inputMultiplexer = new InputMultiplexer();
    }

    @Override
    public void show() {
        super.show();

        for (Stage stage : stages) {
            inputMultiplexer.addProcessor(stage);
        }
    }

    //движение человека по направлениям из очереди
    private void moveHumanMethods(){
        if(humanMethods.isNodePoint()){
            if (deque.getFirst().size() != 0) {
                currentDiriction = (Direction) deque.getFirst().pollFirst();
                //humanMethods.move(currentDiriction, speed);
            } else {
                deque.pollFirst();
                if (deque.size() != 0){
                    humanMethods.setPositionLogic(startPositionLogic);
                    humanMethods.setPositionPixel(startPositionPixel);
                }
                System.out.println(++count);  //временно
                return;
            }
        }
        humanMethods.move(currentDiriction, speed);
    }

    //сравнение координат методного человека и сундука
    private boolean isEqualHumamMethodsChest(){
        return humanMethods.getPositionPixel().equals(chest.getPositionPixel());
    }

    @Override
    public void render(float delta) {
        //super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.input.setInputProcessor(inputMultiplexer);

        if (!isEqualHumamMethodsChest()) {
            if (humanMethods.isCenterLogicalSquare()){
                moveHumanMethods();
            } else {
                humanMethods.move(currentDiriction, speed);
            }
        }

        batch.begin();
        batch.draw(background, gpi.getBGRect().x, gpi.getBGRect().y, gpi.getBGRect().width, gpi.getBGRect().height);
        batch.draw(field.getTexture(), field.getPositionPixel().x, field.getPositionPixel().y);
        for (StaticObject key : keys) {
            batch.draw(key.getTexture(), key.getPositionPixel().x, gpi.getBlank() + key.getPositionPixel().y);
        }
        batch.draw(chest.getTexture(), chest.getPositionPixel().x, gpi.getBlank() + chest.getPositionPixel().y);
        batch.draw(humanMethods.getTexture(), humanMethods.getPositionPixel().x, gpi.getBlank() + humanMethods.getPositionPixel().y);
        batch.draw(arrow.getTexture(), arrow.getPositionPixel().x, arrow.getPositionPixel().y);
        nameLevel.draw(batch, gpi.getLevel(), gpi.getPositionNameLevel().x, gpi.getPositionNameLevel().y);
        numberLevel.draw(batch, level, gpi.getPositionNumberLevel().x, gpi.getPositionNumberLevel().y);
        nameKeys.draw(batch, gpi.getKeys(), gpi.getPositionNameKeys().x, gpi.getPositionNameKeys().y);
        countKeys.draw(batch, outCountAllKeys.toString(), gpi.getPositionCountKeys().x, gpi.getPositionCountKeys().y);
        batch.end();

        if (isAttemptRemoveKey()) removeKey();

        for (Stage stage : stages) {
            stage.draw();
        }

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
