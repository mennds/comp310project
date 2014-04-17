package fiddytoo;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class GameCode extends ApplicationAdapter {
	Stage stage;
//	SpriteBatch batch;
	Texture img;
	List<Card> deck = Card.deck();
	List<ImageButton> deckImage = new ArrayList<ImageButton>();
	int i = 1;
	
	@Override
	public void create () {
		// All the world's a stage
		stage = new Stage();
		
		
		createCards();
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
		stage.draw();
	}
	
	
	public void createCards() {
		for (Card temp : deck) {
			deckImage.add(new ImageButton(new SpriteDrawable(new Sprite(new Texture(temp.toString())))));
			deckImage.get(i-1).setSize(100, 100);
			// Add button listener for each card
			deckImage.get(i-1).addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int mouseButton) {
					int j = i-1;
					System.out.println("Card " + i + " moved");
					// Move button to random location
					deckImage.get(j).setPosition(100,100);
					return true;
				}
			});
			
			// Adds button to the stage
			stage.addActor(deckImage.get(i-1));
			
			i++;
		}
	}
	
}