package com.academiadecodigo.hashtronauts.inputProcessors;

import com.academiadecodigo.hashtronauts.EndGameStates;
import com.academiadecodigo.hashtronauts.MrDuckHunt;
import com.academiadecodigo.hashtronauts.components.GameObjects.targets.Target;
import com.academiadecodigo.hashtronauts.exceptions.MissedShoot;
import com.academiadecodigo.hashtronauts.exceptions.NotEnoughAmmo;
import com.academiadecodigo.hashtronauts.helpers.ControlHelper;
import com.academiadecodigo.hashtronauts.helpers.EnemyHelper;
import com.academiadecodigo.hashtronauts.helpers.GameHelpers;
import com.academiadecodigo.hashtronauts.helpers.PlayerHelper;
import com.academiadecodigo.hashtronauts.screens.EndScreen;
import com.academiadecodigo.hashtronauts.screens.GameScreen;
import com.academiadecodigo.hashtronauts.screens.PauseScreen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;

public class GameInputProcessor implements InputProcessor {
    private GameScreen gameScreen = GameHelpers.getScreenHelper().getGameScreen();
    private MrDuckHunt game = MrDuckHunt.getInstance();

    private PlayerHelper playerHelper = GameHelpers.getPlayerHelper();

    public GameInputProcessor(){

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (gameScreen.isPaused())
            return true;

        switch (character){
            case ControlHelper.RELOAD_WEAPON:
                try {
                    playerHelper.reload();
                } catch (NotEnoughAmmo noAmmo) {
                    gameScreen.dispose();
                    game.setScreen(new EndScreen(game, EndGameStates.OUTOFAMMO));
                }

                return true;
            case ControlHelper.SPAWN_ENEMIES:
                GameHelpers.getEnemyHelper().spawnRandomEnemy().setInitialPos(50, 50);
                return true;
            case ControlHelper.PAUSE_GAME:
                gameScreen.pauseGame();
                game.setScreen(new PauseScreen(game, gameScreen));
                return true;
            case ControlHelper.FORCE_QUIT_KEY:
                gameScreen.dispose();
                game.setScreen(new EndScreen(game, EndGameStates.EXITGAME));
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (gameScreen.isPaused())
            return true;

        if (button == Input.Buttons.LEFT) {

            Target targetHit = GameHelpers.getEnemyHelper().checkMouseClick(new Vector2(screenX, Math.abs(screenY - Gdx.graphics.getHeight())));

            try {

                if (playerHelper.shoot(targetHit)) {
                    GameHelpers.getEnemyHelper().destroyTarget(targetHit);
                }


                return true;
            } catch (MissedShoot ignored) {
            } catch (NotEnoughAmmo ignored) {
            }
        }


        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
