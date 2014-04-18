package fiddytoo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class GameCode extends ApplicationAdapter {
	float t = (System.nanoTime()/(float)1000000000);
	Stage stage;
//	SpriteBatch batch;
	Texture img;
	List<Card> deck = Card.deck();
	List<Card> pickedUpDeck = new ArrayList<Card>();
	List<ImageButton> deckImage = new ArrayList<ImageButton>();
	ImageButton topCard;
	int i = 1, pos = 0;
	boolean gameStarted = false;
	
	@Override
	public void create () {
		// All the world's a stage
		stage = new Stage();
		System.out.println(1 + (int)(Math.random() * ((500) + 1)));
		
		
		createCards();
		topCard = new ImageButton(new SpriteDrawable(new Sprite(new Texture("red_joker.png"))));
		topCard.setSize(100, 200);
		//topCard.setPosition(0, 0);
		topCard.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
				System.out.println("Explosion!");
				topCard.addAction(Actions.hide());
				explosion();
				return true;
			}
			});
		stage.addActor(topCard);
		i--;
		
		// The stage will respond to input from Gdx (keyboard, mouse, touch, game controller)
		Gdx.input.setInputProcessor(stage);
/*
		// Create a button with two images
		button = new ImageButton(new SpriteDrawable(new Sprite(new Texture("2_of_clubs.png"))));

		// Layout that button
		button.setSize(100, 200);
		button.setPosition(20, 20);*/

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, (float)0.5, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 20, 20, 100, 200);
//		batch.end();
		stage.act();
		stage.draw();
	}
	
	
	public void createCards() {
		for (Card temp : deck) {
			final int j = i;
			deckImage.add(new ImageButton(new SpriteDrawable(new Sprite(new Texture(temp.toString())))));
			deckImage.get(i-1).setSize(100, 200);
			//deckImage.get(i-1).setPosition(0, 0);
			// Add button listener for each card
			deckImage.get(i-1).addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
					System.out.println("Card " + j + " moved");
					// Move button to random location
					deckImage.get(j-1).addAction(Actions.moveTo((0),(0),(float)0.5));
					pickedUpDeck.add(deck.get(j-1));
					deck.remove(j-1);
					return true;
			}
			});
			// Adds button to the stage
			stage.addActor(deckImage.get(i-1));
			
			//pos += 40;
			i++;
		}
	}
	
	public void explosion() {
		for(int x = 0; x < 52; x++) {
			deckImage.get(x).addAction(Actions.moveTo((1+(int)(Math.random()*(1201-200))),(1+(int)(Math.random()*(801-100))),(float)0.35));
		}
	}
}