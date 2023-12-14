package be.kuleuven.VGHF;

import java.util.*;

import org.hibernate.annotations.Generated;
import org.hibernate.mapping.List;

import be.kuleuven.VGHF.domain.*;
import be.kuleuven.VGHF.domain.Copy.CopyBuilder;
import be.kuleuven.VGHF.enums.*;

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
        Developer gameFreak = new Developer("GameFreak");

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
        database.saveNewDeveloper(gameFreak);

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
        Console nintenoDS = new Console("Nintendo DS");
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
        database.saveNewConsole(nintenoDS);
        database.saveNewConsole(atari2600);
        database.saveNewConsole(pc);
        database.saveNewConsole(nes);

        Game doom = new Game("DOOM", "id Software", "1993-12-10", Arrays.asList(pc), Arrays.asList(idSoftware), Arrays.asList(fps));
        Game theWitcher3 = new Game("The Witcher 3: Wild Hunt", "CD Projekt", "2015-05-19", Arrays.asList(playstation4, xboxOne, pc), Arrays.asList(cdProjekt), Arrays.asList(rpg));
        Game superMarioBros = new Game("Super Mario Bros.", "Nintendo", "1985-09-13", Arrays.asList(nes), Arrays.asList(nintendo), Arrays.asList(platformer));
        Game minecraft = new Game("Minecraft", "Mojang", "2011-11-18", Arrays.asList(pc, xboxOne, playstation4, nintendoSwitch), Arrays.asList(microsoftStudios), Arrays.asList(sandbox, survival));
        Game pokemonPlatinum = new Game("Pokémon Platinum", "Nintendo", "2008-09-13", Arrays.asList(nintenoDS, nintendo3DS), Arrays.asList(nintendo, gameFreak), Arrays.asList(rpg));
    
        database.saveNewGame(doom);
        database.saveNewGame(theWitcher3);
        database.saveNewGame(superMarioBros);
        database.saveNewGame(minecraft);
        database.saveNewGame(pokemonPlatinum);
    
        Customer dries = new Customer("Dries Ruttens", "dries.ruttens@student.uhasselt.be", "wachtwoord", 10);
        Customer bjorn = new Customer("Bjorn Spauwen", "bjorn.spauwen@student.uhasselt.be", "password", 20);
        Customer max = new Customer("Max-émile Meylaerts", "max-emile.meylaerts@student.uhasselt.be", "12345", 30);
        Customer mauro = new Customer("Mauro Vranckx", "mauro.vranckx@student.uhasselt.be", "azerty", 0);
        Customer eddy = new Customer("Edmond Tsampanis", "edmond.tsampanis@student.uhasselt.be", "StrongPass123!", 40);
        Customer jan = new Customer("Jan Alleman", "jan.alleman@fancycustomemail.com", "mijnverjaardag1999",-5);

        database.saveNewCustomer(dries);
        database.saveNewCustomer(bjorn);
        database.saveNewCustomer(max);
        database.saveNewCustomer(mauro);
        database.saveNewCustomer(eddy);
        database.saveNewCustomer(jan);

        CopyBuilder builder = new CopyBuilder();
        Copy doomCopy1 = builder.game(doom)
                                .console(pc)
                                .availability(Availability.AVAILABLE)
                                .warehouse("Genk")
                                .build();

        Copy witcherCopy1 = builder.game(theWitcher3)
                                   .console(playstation4)
                                   .availability(Availability.SOLD)
                                   .warehouse("Hasselt")
                                   .purchasePrice(70)
                                   .build();

        Copy marioCopy1 = builder.game(superMarioBros)
                                 .console(nes)
                                 .availability(Availability.BROKEN)
                                 .warehouse("Los Angeles")
                                 .build();

        Copy minecraftCopy1 = builder.game(minecraft)
                                     .console(pc)
                                     .availability(Availability.RENTED)
                                     .warehouse("Tokyo")
                                     .customer(bjorn)
                                     .purchasePrice(20)
                                     .rentPrice(3)
                                     .dateOfReturn("2024-01-12")
                                     .build();

        Copy minecraftCopy2 = builder.game(minecraft)
                                     .console(playstation5)
                                     .availability(Availability.AVAILABLE)
                                     .warehouse("Diepenbeek")
                                     .build();
        Copy pokemonPatinumCopy1 = builder.game(pokemonPlatinum)
                                          .console(nintenoDS)
                                          .availability(Availability.AVAILABLE)
                                          .warehouse("Genk")
                                          .build();


        database.saveNewCopy(doomCopy1);
        database.saveNewCopy(witcherCopy1);
        database.saveNewCopy(marioCopy1);
        database.saveNewCopy(minecraftCopy1);
        database.saveNewCopy(minecraftCopy2);
        database.saveNewCopy(pokemonPatinumCopy1);

        MonetaryTransaction transaction1 = new MonetaryTransaction(TransactionType.SALE, 60, jan, witcherCopy1, "2022-05-12");
        MonetaryTransaction transaction2 = new MonetaryTransaction(TransactionType.RENTAL, 9, bjorn, minecraftCopy2, "2023-12-12");

        database.saveNewMonetaryTransaction(transaction1);
        database.saveNewMonetaryTransaction(transaction2);
    }

}


