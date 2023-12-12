package be.kuleuven.VGHF;

import java.util.*;

import org.hibernate.annotations.Generated;
import org.hibernate.mapping.List;

import be.kuleuven.VGHF.domain.Console;
import be.kuleuven.VGHF.domain.Developer;
import be.kuleuven.VGHF.domain.Game;
import be.kuleuven.VGHF.domain.Genre;
import be.kuleuven.VGHF.domain.HibernateManager;

public class DbContentScript {

    static void scriptMain(){
        HibernateManager database = ProjectMain.getDatabase();

        Genre action = new Genre("Action");
        Genre fps = new Genre("First-Person Shooter (FPS)");
        Genre rpg = new Genre("Role-Playing Game (RPG)");
        Genre simulation = new Genre("Simulation");
        Genre strategy = new Genre("Strategy");
        Genre sportsAndRacing = new Genre("Sports and Racing");
        Genre horror = new Genre("Horror");
        Genre fighting = new Genre("Fighting");
        Genre mmorpg = new Genre("MMORPG");
        Genre puzzle = new Genre("Puzzle");
        Genre platformer = new Genre("Platformer");
        Genre battleRoyale = new Genre("Battle Royale");
        Genre survival = new Genre("Survival");
        Genre sandbox = new Genre("Sandbox");

        database.saveNewGenre(action);
        database.saveNewGenre(fps);
        database.saveNewGenre(rpg);
        database.saveNewGenre(simulation);
        database.saveNewGenre(strategy);
        database.saveNewGenre(sportsAndRacing);
        database.saveNewGenre(horror);
        database.saveNewGenre(fighting);
        database.saveNewGenre(mmorpg);
        database.saveNewGenre(puzzle);
        database.saveNewGenre(platformer);
        database.saveNewGenre(battleRoyale);
        database.saveNewGenre(survival);
        database.saveNewGenre(sandbox);

        Developer nintendo = new Developer("Nintendo");
        Developer sonyInteractiveEntertainment = new Developer("Sony Interactive Entertainment");
        Developer microsoftStudios = new Developer("Microsoft Studios / Xbox Game Studios");
        Developer electronicArts = new Developer("Electronic Arts (EA)");
        Developer ubisoft = new Developer("Ubisoft");
        Developer activision = new Developer("Activision");
        Developer blizzardEntertainment = new Developer("Blizzard Entertainment");
        Developer squareEnix = new Developer("Square Enix");
        Developer epicGames = new Developer("Epic Games");
        Developer valveCorporation = new Developer("Valve Corporation");
        Developer bethesdaGameStudios = new Developer("Bethesda Game Studios");
        Developer cdProjekt = new Developer("CD Projekt");
        Developer rockstarGames = new Developer("Rockstar Games");
        Developer naughtyDog = new Developer("Naughty Dog");
        Developer bioware = new Developer("Bioware");
        Developer idSoftware = new Developer("id Software");

        database.saveNewDeveloper(nintendo);
        database.saveNewDeveloper(sonyInteractiveEntertainment);
        database.saveNewDeveloper(microsoftStudios);
        database.saveNewDeveloper(electronicArts);
        database.saveNewDeveloper(ubisoft);
        database.saveNewDeveloper(activision);
        database.saveNewDeveloper(blizzardEntertainment);
        database.saveNewDeveloper(squareEnix);
        database.saveNewDeveloper(epicGames);
        database.saveNewDeveloper(valveCorporation);
        database.saveNewDeveloper(bethesdaGameStudios);
        database.saveNewDeveloper(cdProjekt);
        database.saveNewDeveloper(rockstarGames);
        database.saveNewDeveloper(naughtyDog);
        database.saveNewDeveloper(bioware);
        database.saveNewDeveloper(idSoftware);

        Console playstation5 = new Console("PlayStation 5");
        Console playstation4 = new Console("PlayStation 4", Arrays.asList(playstation5));
        Console playstation3 = new Console("PlayStation 3", Arrays.asList(playstation4, playstation5));
        Console playstation2 = new Console("PlayStation 2", Arrays.asList(playstation3));
        Console playstation1 = new Console("PlayStation 1");
        Console xboxSeriesX = new Console("Xbox Series X");
        Console xboxOne = new Console("Xbox One", Arrays.asList(xboxSeriesX));
        Console xbox360 = new Console("Xbox 360");
        Console xbox = new Console("Xbox", Arrays.asList(xbox360));
        Console wii = new Console("Wii");
        Console nintendoSwitch = new Console("Nintendo Switch");
        Console nintendo3DS = new Console("Nintendo 3DS");
        Console atari2600 = new Console("Atari 2600");
        Console pc = new Console("PC");
        Console nes = new Console("Nintendo Entertainment System (NES)");
        
        database.saveNewConsole(playstation5);
        database.saveNewConsole(playstation4);
        database.saveNewConsole(playstation3);
        database.saveNewConsole(playstation2);
        database.saveNewConsole(playstation1);
        database.saveNewConsole(xboxSeriesX);
        database.saveNewConsole(xboxOne);
        database.saveNewConsole(xbox360);
        database.saveNewConsole(xbox);
        database.saveNewConsole(wii);
        database.saveNewConsole(nintendoSwitch);
        database.saveNewConsole(nintendo3DS);
        database.saveNewConsole(atari2600);
        database.saveNewConsole(pc);
        database.saveNewConsole(nes);

        Game doom = new Game("DOOM", "id Software", "1993-12-10", Arrays.asList(pc), Arrays.asList(idSoftware), Arrays.asList(fps));
        Game theWitcher3 = new Game("The Witcher 3: Wild Hunt", "CD Projekt", "2015-05-19", Arrays.asList(playstation4, xboxOne, pc), Arrays.asList(cdProjekt), Arrays.asList(rpg));
        Game superMarioBros = new Game("Super Mario Bros.", "Nintendo", "1985-09-13", Arrays.asList(nes), Arrays.asList(nintendo), Arrays.asList(platformer));
        Game minecraft = new Game("Minecraft", "Mojang", "2011-11-18", Arrays.asList(pc, xboxOne, playstation4, nintendoSwitch), Arrays.asList(microsoftStudios), Arrays.asList(sandbox, survival));
    
        database.saveNewGame(doom);
        database.saveNewGame(theWitcher3);
        database.saveNewGame(superMarioBros);
        database.saveNewGame(minecraft);
    
    }

}


