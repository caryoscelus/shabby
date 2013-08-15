/*
 *  Copyright (C) 2013 caryoscelus
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Additional permission under GNU GPL version 3 section 7:
 *  If you modify this Program, or any covered work, by linking or combining
 *  it with Clojure (or a modified version of that library), containing parts
 *  covered by the terms of EPL 1.0, the licensors of this Program grant you
 *  additional permission to convey the resulting work. {Corresponding Source
 *  for a non-source form of such a combination shall include the source code
 *  for the parts of Clojure used as well as that of the covered work.}
 */

package gg;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Stage for displaying story
 */
class StoryStage extends Stage {
    public boolean show = false;
    
    /**
     * Setup storyStage from dialogue
     */
    void setupUi (final StoryDialog dialogue) {
        final Skin skin = UiManager.instance().skin;
        final Table table = new Table();
        table.setFillParent(true);
        
        final Window winDialog = new Window("----", skin);
        table.add(winDialog).width(600).height(400);
        
        String labelText = dialogue.text;
        // remove superflous white space
        labelText = labelText.replace("\t", " ");
        labelText = labelText.replace("\n", " ");
        while (labelText.matches("  ")) {
            labelText = labelText.replace("  ", " ");
        }
        
        // now add some line-breaks for paragraphs
        labelText = labelText.replace("^", "\n");
        
        // now check if we should display a picture
        String[] t = labelText.split("(\\<img\\:|\\>)");
        String img = null;
        if (t.length > 1) {
            img = t[1];
            if (t.length > 2) {
                labelText = t[0] + t[2];
            } else {
                labelText = t[0];
            }
        }
        
        if (img != null) {
            final Image image = new Image(new Texture(Gdx.files.internal(img)));
            winDialog.add(image);
            winDialog.row();
        }
        
        final Label label = new Label(labelText, skin);
        label.setWrap(true);
        winDialog.add(label).space(6).pad(2).expand().fillX().top().left();
        
        // dialogue options
        for (StoryDialogLine line : dialogue.options) {
            if (line.visible) {
                final String text = line.text;
                final StoryEvent event = line.event;
                
                final TextButton button = new TextButton(text, skin);
                button.addListener(new ChangeListener() {
                    public void changed (ChangeEvent cevent, Actor actor) {
                        boolean result = event.trigger();
                        if (!result) {
                            if (Story.instance().checkExit(dialogue)) {
                                show = false;
                            }
                        }
                    }
                });
                winDialog.row();
                winDialog.add(button).pad(2);
            }
        }
        
        winDialog.top();
        winDialog.pack();
        
        table.top();
        
        addActor(table);
    }
}
