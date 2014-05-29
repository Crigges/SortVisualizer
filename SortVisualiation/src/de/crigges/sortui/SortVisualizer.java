package de.crigges.sortui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.TimerTask;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class SortVisualizer implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private ArrayList<Sprite> tiles = new ArrayList<Sprite>();
	
	private int i1, i2;
	
	@Override
	public void create() {		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Gdx.graphics.setDisplayMode(screen.width, screen.height, true);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h / w);
		camera.setToOrtho(false, w, h);
		batch = new SpriteBatch();
//		int[] arr = new int[1000];
//		for(int i = 1; i <= 1000; i++){
//			arr[i - 1] = i;
//		}
//		shuffleArray(arr);
//		apply(arr);
//		Task t = new Task() {
//			
//			@Override
//			public void run() {
//				i1 =  (int) (Math.random() * 100);
//				i2 = (int) (Math.random() * 100);
//				setColor(i1, 1, 0, 0);
//				setColor(i2, 1, 0, 0);
//				Task t2 = new Task() {
//					
//					@Override
//					public void run() {
//						swap(i1, i2);
//						setColor(i1, 0, 1, 0);
//						setColor(i2, 0, 1, 0);
//					}
//				};
//				new Timer().scheduleTask(t2, 0.1f);
//			}
//		};
//		new Timer().scheduleTask(t, 0.2f, 0.2f);
	}
	
	public static void shuffleArray(int[] ar)
	{
		Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	}
	
	public void setColor(int index, float r, float g, float b){
		tiles.get(index).setColor(r, g, b, 1);
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}
	
	public void apply(int[] arr){
		int c = 0;
		for(int i : arr){
			if(texture == null){
				texture = new Texture(Gdx.files.internal("data/tile.png"));
			}
			if(texture == null){
				System.out.println("rami das ist allein deine schuld");
			}
			Sprite s = new Sprite(texture);
			tiles.add(s);
			s.setColor(0, 1, 0, 1);
			s.setSize(Gdx.graphics.getWidth() / (arr.length + Gdx.graphics.getWidth() / ((float) (arr.length) / 2f)) , ((float) Gdx.graphics.getHeight() / arr.length) * i);
			s.setOrigin(0, 0);
			s.setPosition(0.1f + c * ((float) Gdx.graphics.getWidth() / (float) arr.length), 0.1f);
			c++;
		}
	}
	
	public void swap(int index1, int index2){
		Sprite s1 = tiles.get(index1);
		Sprite s2 = tiles.get(index2);
		float x = s1.getX();
		float y = s1.getY();
		s1.setX(s2.getX());
		s1.setY(s2.getY());
		s2.setX(x);
		s2.setY(y);
		tiles.set(index1, s2);
		tiles.set(index2, s1);
	}
	

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(Sprite s : tiles){
			s.draw(batch);
		}
		batch.end();
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
}
