package com.vladwild.chest.with.gems.screens.methods;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.StringBuilder;
import com.vladwild.chest.with.gems.gameplay.Direction;
import com.vladwild.chest.with.gems.gameplay.DynamicObject;
import com.vladwild.chest.with.gems.gameplay.StaticObject;
import com.vladwild.chest.with.gems.gameplay.methods.DynamicObjectMethods;
import com.vladwild.chest.with.gems.gameplay.methods.HumanMethods;
import com.vladwild.chest.with.gems.gamestarter.ChestWithGems;
import com.vladwild.chest.with.gems.methods.algorithms.Algorithm;
import com.vladwild.chest.with.gems.methods.algorithms.function.FactoryFunction;
import com.vladwild.chest.with.gems.methods.tasks.FactorySearchFunctionTasks;
import com.vladwild.chest.with.gems.screens.GamePlay;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FunctionsScreen extends GamePlay implements Screen {
    private List<List> variants;

    private DynamicObjectMethods humanMethods;

    private GridPoint2 startPositionLogic;
    private GridPoint2 startPositionPixel;

    private Direction currentDiriction;

    private InputMultiplexer inputMultiplexer;

    private int count = 0;          //временно

    private Set<StaticObject> keysBuffer;

    private StringBuilder outCountAllKeysBuffer;
    private final StringBuilder CONST = new StringBuilder(outCountAllKeys);

    @Override
    protected void removeKey(DynamicObject human) {
        iterKeys = keysBuffer.iterator();
        while (iterKeys.hasNext()) {
            if (human.getPositionPixel().equals(iterKeys.next().getPositionPixel())) {
                iterKeys.remove();
                changeCountAllKeys();
            }
        }
    }

    public FunctionsScreen(ChestWithGems game, int level, int speed) {
        super(game, level, speed);

        //функции

        //евклидово расстояние
        //сундук
        //Algorithm algorithm = FactoryFunction.getTypeFunction(FactoryFunction.EUCLIDEAN,
        //        FactorySearchFunctionTasks.getTypeTask(FactorySearchFunctionTasks.CHEST, field));
        //все ключи и сундук
        Algorithm algorithm = FactoryFunction.getTypeFunction(FactoryFunction.EUCLIDEAN,
                FactorySearchFunctionTasks.getTypeTask(FactorySearchFunctionTasks.KEYS, field));

        algorithm.start();
        variants = algorithm.getVariants();

        humanMethods = new HumanMethods(gpi, gpm, field.getHumanPoint(), gmip, field.getMatrixLogic());

        startPositionLogic = new GridPoint2(humanMethods.getPositionLogic().x, humanMethods.getPositionLogic().y);
        startPositionPixel = new GridPoint2(humanMethods.getPositionPixel().x, humanMethods.getPositionPixel().y);

        inputMultiplexer = new InputMultiplexer();

        keysBuffer = new HashSet<>(super.keys);

        outCountAllKeysBuffer = new StringBuilder(CONST);
    }

    @Override
    protected void changeCountAllKeys() {
        outCountAllKeysBuffer.delete(0, outCountAllKeys.length());
        outCountAllKeysBuffer.append(countAllKeys - keysBuffer.size());
        outCountAllKeysBuffer.append(gpi.getDelimiter());
        outCountAllKeysBuffer.append(countAllKeys);
    }

    @Override
    public void show() {
        super.show();

        for (Stage stage : stages) {
            inputMultiplexer.addProcessor(stage);
        }
    }

    //движение человека по направлениям из очереди
    private void moveHumanMethods() {                       //отличие от DeepAndWidth только тут по сути
        //проходимся по 1 списку всех направлений, полученных из алгоритма
        if (!variants.isEmpty()) {
            if (variants.get(0).size() != 0) {
                currentDiriction = (Direction) variants.get(0).get(0);
                variants.get(0).remove(0);
            } else {
                variants.remove(0);
                if (variants.size() != 0) {
                    humanMethods.setPositionLogic(startPositionLogic);
                    humanMethods.setPositionPixel(startPositionPixel);
                }
                System.out.println(++count);  //количество итераций

                keysBuffer = new HashSet<>(super.keys);

                outCountAllKeysBuffer = new StringBuilder(CONST);

                humanMethods.move(Direction.STOP, 0);
                return;
            }
        } else {
            return;
        }
        humanMethods.move(currentDiriction, speed);
    }

    @Override
    public void render(float delta) {
        //super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.input.setInputProcessor(inputMultiplexer);

        if (humanMethods.isCenterLogicalSquare()) {
            moveHumanMethods();
        } else {
            humanMethods.move(currentDiriction, speed);
        }

        batch.begin();
        batch.draw(background, gpi.getBGRect().x, gpi.getBGRect().y, gpi.getBGRect().width, gpi.getBGRect().height);
        batch.draw(field.getTexture(), field.getPositionPixel().x, field.getPositionPixel().y);
        for (StaticObject key : keysBuffer) {
            batch.draw(key.getTexture(), key.getPositionPixel().x, gpi.getBlank() + key.getPositionPixel().y);
        }
        batch.draw(chest.getTexture(), chest.getPositionPixel().x, gpi.getBlank() + chest.getPositionPixel().y);
        batch.draw(humanMethods.getTexture(), humanMethods.getPositionPixel().x, gpi.getBlank() + humanMethods.getPositionPixel().y);
        batch.draw(arrow.getTexture(), arrow.getPositionPixel().x, arrow.getPositionPixel().y);
        nameLevel.draw(batch, gpi.getLevel(), gpi.getPositionNameLevel().x, gpi.getPositionNameLevel().y);
        numberLevel.draw(batch, level, gpi.getPositionNumberLevel().x, gpi.getPositionNumberLevel().y);
        nameKeys.draw(batch, gpi.getKeys(), gpi.getPositionNameKeys().x, gpi.getPositionNameKeys().y);
        countKeys.draw(batch, outCountAllKeysBuffer.toString(), gpi.getPositionCountKeys().x, gpi.getPositionCountKeys().y);
        batch.end();

        if (isAttemptRemoveKey()) removeKey(humanMethods);

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
