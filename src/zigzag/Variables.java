/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package zigzag;

import Texture.TextureReader;

import java.util.ArrayList;

/**
 * @author Alaa Sayed
 */
public interface Variables {

    int maxWidth = 100;
    int maxHeight = 100;
    ArrayList<Tile> tiles = new ArrayList<>();
    double x = 50, y = 50;

    String[] textureNames = {
            "main.png",
            "button-play.png",
            "button-multiplayer.png",
            "button-instructions.png",
            "button-Exit.png",
            "Hard.png",
            "instruction.png",
            "Back.png",

           // "orangeBall.png",
           // "button.png",
            "gameBackground.png"
    };

    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int[] textures = new int[textureNames.length];
}

