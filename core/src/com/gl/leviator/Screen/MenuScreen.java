package com.gl.leviator.Screen;


//import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.Tooltip;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
	
	Object[] listEntries = {"This is a list entry1", "And another one1", "The meaning of life1", "Is hard to come by1",
			"This is a list entry2", "And another one2", "The meaning of life2", "Is hard to come by2", "This is a list entry3",
			"And another one3", "The meaning of life3", "Is hard to come by3", "This is a list entry4", "And another one4",
			"The meaning of life4", "Is hard to come by4", "This is a list entry5", "And another one5", "The meaning of life5",
			"Is hard to come by5"};
	
	
	Skin skin;
	Stage stage;
	Texture texture1;
	Texture texture2;
	Label fpsLabel;
	List list;
	
	public MenuScreen() {
		System.out.println("GameScreen Attached");
		
		
		//window skin and texture loading.
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		texture1 = new Texture(Gdx.files.internal("data/badlogicsmall.jpg"));
		texture2 = new Texture(Gdx.files.internal("data/badlogic.jpg"));
		TextureRegion image = new TextureRegion(texture1);
		TextureRegion imageFlipped = new TextureRegion(image);
		imageFlipped.flip(true, true);
		TextureRegion image2 = new TextureRegion(texture2);
		// stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, new PolygonSpriteBatch());
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		//creating button style to give to buttons
		ImageButtonStyle style = new ImageButtonStyle(skin.get(ButtonStyle.class));
		style.imageUp = new TextureRegionDrawable(image);
		style.imageDown = new TextureRegionDrawable(imageFlipped);
		
		//creating our start button, got a picture
		//TODO: change picture to something like "continue" or "choose"
		Button imgButton = new Button(new Image(image), skin);
		
		//just a box with a picut
		Image imageActor = new Image(image2);
		ScrollPane scrollPane = new ScrollPane(imageActor);
		
		//list setup
		//TODO: create a link with .eq files instead of loading dummy list
		list = new List(skin);
		list.setItems(listEntries);
		list.getSelection().setMultiple(false);
		list.getSelection().setRequired(true);
		
		//putting list in scrollable element
		ScrollPane scrollPane2 = new ScrollPane(list, skin);
		scrollPane2.setFlickScroll(false);
		
		//putting picture & list on the same line
		SplitPane splitPane = new SplitPane(scrollPane, scrollPane2, false, skin, "default-horizontal");
		
		
		//label to display FPS
		fpsLabel = new Label("fps:", skin);
		
		
		// Window setup to hold the components
		//TODO: look for better way to do it. Maybe remove the "X" to close it ?
		Window window = new Window("Welcome, please choose a level", skin);
		window.getTitleTable().add(new TextButton("X", skin)).height(window.getPadTop());
		window.defaults().spaceBottom(10);
		window.row().fill().expandX();
		window.add(imgButton);
		window.row();
		window.add(splitPane).fill().expand().colspan(4).maxHeight(200);
		window.row();
		window.add(fpsLabel).colspan(4);
		window.pack();
		
		//putting window in middle of screen
		float x = (Gdx.graphics.getWidth() - window.getWidth()) / 2.0f;
		float y = (Gdx.graphics.getHeight() - window.getHeight()) / 2.0f;
		window.setPosition(x, y);

		//adding window to actors so you can relay events
		stage.addActor(window);

		//adding listener to button to start level
		imgButton.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				new Dialog("Some Dialog", skin, "dialog") {
					protected void result (Object object) {
						System.out.println("Chosen: " + object);
						if ((Boolean) object == true)
						System.out.println(list.getSelected());
						//TODO: send this data to .eq manager, switch to game screen.
					}
				}.text("Are you sure you wanna select this level \"" + list.getSelected() + "\" ?" ).button("Yes", true).button("No", false).key(Keys.ENTER, true)
					.key(Keys.ESCAPE, false).show(stage);
			}
		});
		
	}

	@Override
	public void render(float delta) {

		//drawing gray background
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//sending value to fpsLabel to display fps
		fpsLabel.setText("fps: " + Gdx.graphics.getFramesPerSecond());

		//drawing of the stage (here the window)
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("GameScreen - resizing");
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		System.out.println("GameScreen - show called");
	}

	@Override
	public void hide() {
		System.out.println("GameScreen - hide called");		
	}

	@Override
	public void pause() {
		System.out.println("GameScreen - pause called");		
	}

	@Override
	public void resume() {
		System.out.println("GameScreen - resume called");		
	}

	@Override
	public void dispose() {
		//cleanup of stored data
		stage.dispose();
		skin.dispose();
		texture1.dispose();
		texture2.dispose();
		// Leave blank
	}

}