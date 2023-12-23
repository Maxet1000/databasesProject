package be.kuleuven.VGHF;

import java.util.*;

import be.kuleuven.VGHF.domain.*;
import be.kuleuven.VGHF.domain.Copy.CopyBuilder;
import be.kuleuven.VGHF.enums.*;

public class DbContentScript {

    static void scriptMain(){
        HibernateManager database = ProjectMain.getDatabase();

        Genre action = new Genre("Action");
        Genre adventure = new Genre("Adventure");
        Genre battleRoyale = new Genre("Battle Royale");
        Genre coOp = new Genre("Cooperative (Co-op)");
        Genre educational = new Genre("Educational");
        Genre fighting = new Genre("Fighting");
        Genre fps = new Genre("First-Person Shooter (FPS)");
        Genre historical = new Genre("Historical");
        Genre horror = new Genre("Horror");
        Genre indie = new Genre("Indie");
        Genre mmorpg = new Genre("MMORPG");
        Genre openWorld = new Genre("Open World");
        Genre party = new Genre("Party");
        Genre platformer = new Genre("Platformer");
        Genre puzzle = new Genre("Puzzle");
        Genre racing = new Genre("Racing");
        Genre rpg = new Genre("Role-Playing Game (RPG)");
        Genre rhythm = new Genre("Rhythm");
        Genre sandbox = new Genre("Sandbox");
        Genre sciFi = new Genre("Science Fiction (Sci-Fi)");
        Genre simulation = new Genre("Simulation");
        Genre sportsAndRacing = new Genre("Sports and Racing");
        Genre stealth = new Genre("Stealth");
        Genre strategy = new Genre("Strategy");
        Genre survival = new Genre("Survival");

        Developer activision = new Developer("Activision");
        Developer atlus = new Developer("Atlus");
        Developer bandaiNamco = new Developer("Bandai Namco Entertainment");
        Developer bethesdaGameStudios = new Developer("Bethesda Game Studios");
        Developer bioware = new Developer("Bioware");
        Developer blizzardEntertainment = new Developer("Blizzard Entertainment");
        Developer capcom = new Developer("Capcom");
        Developer cdProjekt = new Developer("CD Projekt");
        Developer dice = new Developer("DICE");
        Developer electronicArts = new Developer("Electronic Arts (EA)");
        Developer epicGames = new Developer("Epic Games");
        Developer fromSoftware = new Developer("FromSoftware");
        Developer gameFreak = new Developer("GameFreak");
        Developer gearboxSoftware = new Developer("Gearbox Software");
        Developer idSoftware = new Developer("id Software");
        Developer insomniacGames = new Developer("Insomniac Games");
        Developer konami = new Developer("Konami");
        Developer microsoftStudios = new Developer("Microsoft Studios / Xbox Game Studios");
        Developer namco = new Developer("Namco");
        Developer naughtyDog = new Developer("Naughty Dog");
        Developer nintendo = new Developer("Nintendo");
        Developer obsidianEntertainment = new Developer("Obsidian Entertainment");
        Developer platinumGames = new Developer("PlatinumGames");
        Developer respawnEntertainment = new Developer("Respawn Entertainment");
        Developer rockstarGames = new Developer("Rockstar Games");
        Developer sega = new Developer("Sega");
        Developer squareEnix = new Developer("Square Enix");
        Developer supergiantGames = new Developer("Supergiant Games");
        Developer theCoalition = new Developer("The Coalition");
        Developer ubisoft = new Developer("Ubisoft");
        Developer valveCorporation = new Developer("Valve Corporation");
        Developer taito = new Developer("Taito");
        Developer atari = new Developer("Atari");

        Console playstation5 = new Console("PlayStation 5");
        Console playstation4 = new Console("PlayStation 4", new ArrayList<> (Arrays.asList(playstation5)));
        Console playstation3 = new Console("PlayStation 3", new ArrayList<> (Arrays.asList(playstation4, playstation5)));
        Console playstation2 = new Console("PlayStation 2", new ArrayList<> (Arrays.asList(playstation3)));
        Console playstation1 = new Console("PlayStation 1");
        Console xboxSeriesX = new Console("Xbox Series X");
        Console xboxOne = new Console("Xbox One", new ArrayList<> (Arrays.asList(xboxSeriesX)));
        Console xbox360 = new Console("Xbox 360");
        Console xbox = new Console("Xbox", new ArrayList<> (Arrays.asList(xbox360)));
        Console wii = new Console("Wii");
        Console nintendoSwitch = new Console("Nintendo Switch");
        Console nintendo3DS = new Console("Nintendo 3DS");
        Console nintenoDS = new Console("Nintendo DS", new ArrayList<> (Arrays.asList(nintendo3DS)));
        Console gameBoyAdvance = new Console("Game Boy Advance", new ArrayList<> (Arrays.asList(nintenoDS)));
        Console gameBoyColor = new Console("Game Boy Color", new ArrayList<> (Arrays.asList(gameBoyAdvance)));
        Console gameBoy = new Console("Game Boy", new ArrayList<> (Arrays.asList(gameBoyColor, gameBoyAdvance)));
        Console atari2600 = new Console("Atari 2600");
        Console pc = new Console("PC");
        Console nes = new Console("Nintendo Entertainment System (NES)");
        Console snes = new Console("Super Nintendo Entertainment System (SNES)");
        Console segaGenesis = new Console("Sega Genesis");
        Console segaDreamcast = new Console("Sega Dreamcast");
        Console segaSaturn = new Console("Sega Saturn");
        Console segaMasterSystem = new Console("Sega Master System");
        Console nintendo64 = new Console("Nintendo 64");
        Console gameCube = new Console("Nintendo GameCube");
        Console sonyPSP = new Console("Sony PSP");
        Console sonyPSVita = new Console("Sony PS Vita");
        Console arcade = new Console("Arcade");

        Game doom = new Game("DOOM", "1993-12-10", new ArrayList<> (Arrays.asList(pc)), new ArrayList<> (Arrays.asList(idSoftware)), new ArrayList<> (Arrays.asList(fps)));
        Game theWitcher3 = new Game("The Witcher 3: Wild Hunt", "2015-05-19", new ArrayList<> (Arrays.asList(playstation4, xboxOne, pc)), new ArrayList<> (Arrays.asList(cdProjekt)), new ArrayList<> (Arrays.asList(rpg, openWorld, adventure)));
        Game superMarioBros = new Game("Super Mario Bros.", "1985-09-13", new ArrayList<> (Arrays.asList(nes)), new ArrayList<> (Arrays.asList(nintendo)), new ArrayList<> (Arrays.asList(platformer)));
        Game minecraft = new Game("Minecraft",  "2011-11-18", new ArrayList<> (Arrays.asList(pc, xboxOne, playstation4, nintendoSwitch, xbox360, xboxSeriesX)), new ArrayList<> (Arrays.asList(microsoftStudios)), new ArrayList<> (Arrays.asList(sandbox, survival)));
        Game pokemonPlatinum = new Game("Pokémon Platinum", "2008-09-13", new ArrayList<> (Arrays.asList(nintenoDS)), new ArrayList<> (Arrays.asList(nintendo, gameFreak)), new ArrayList<> (Arrays.asList(rpg)));
        Game metalGearSolid = new Game("Metal Gear Solid", "1998-10-21", new ArrayList<> (Arrays.asList(playstation1, pc)), new ArrayList<> (Arrays.asList(konami)), new ArrayList<> (Arrays.asList(action, stealth)));
        Game finalFantasyVII = new Game("Final Fantasy VII", "1997-01-31", new ArrayList<> (Arrays.asList(playstation1, pc)), new ArrayList<> (Arrays.asList(squareEnix)), new ArrayList<> (Arrays.asList(rpg)));
        Game haloCombatEvolved = new Game("Halo: Combat Evolved",  "2001-11-15", new ArrayList<> (Arrays.asList(xbox)), new ArrayList<> (Arrays.asList(microsoftStudios)), new ArrayList<> (Arrays.asList(fps, action, sciFi)));
        Game zeldaBreathOfTheWild = new Game("The Legend of Zelda: Breath of the Wild", "2017-03-03", new ArrayList<> (Arrays.asList(nintendoSwitch)), new ArrayList<> (Arrays.asList(nintendo)), new ArrayList<> (Arrays.asList(openWorld, action, adventure)));
        Game darkSouls = new Game("Dark Souls", "2011-09-22", new ArrayList<> (Arrays.asList(playstation3, xbox360, pc)), new ArrayList<> (Arrays.asList(fromSoftware)), new ArrayList<> (Arrays.asList(action, rpg)));
        Game grandTheftAutoV = new Game("Grand Theft Auto V", "2013-09-17", new ArrayList<> (Arrays.asList(playstation4, xboxOne, pc, playstation3, xbox360, playstation5, xboxSeriesX)), new ArrayList<> (Arrays.asList(rockstarGames)), new ArrayList<> (Arrays.asList(action, openWorld)));
        Game theLastOfUs = new Game("The Last of Us", "2013-06-14", new ArrayList<> (Arrays.asList(playstation3, playstation4)), new ArrayList<> (Arrays.asList(naughtyDog)), new ArrayList<> (Arrays.asList(action, adventure)));
        Game worldOfWarcraft = new Game("World of Warcraft", "2004-11-23", new ArrayList<> (Arrays.asList(pc)), new ArrayList<> (Arrays.asList(blizzardEntertainment)), new ArrayList<> (Arrays.asList(mmorpg)));
        Game marioKart8Deluxe = new Game("Mario Kart 8 Deluxe", "2017-04-28", new ArrayList<> (Arrays.asList(nintendoSwitch)), new ArrayList<> (Arrays.asList(nintendo)), new ArrayList<> (Arrays.asList(racing, party)));
        Game assassinsCreedOrigins = new Game("Assassin's Creed Origins", "2017-10-27", new ArrayList<> (Arrays.asList(xboxOne, playstation4, pc)), new ArrayList<> (Arrays.asList(ubisoft)), new ArrayList<> (Arrays.asList(action, openWorld, historical)));
        Game redDeadRedemption2 = new Game("Red Dead Redemption 2", "2018-10-26", new ArrayList<> (Arrays.asList(playstation4, xboxOne, pc, xboxSeriesX, playstation5)), new ArrayList<> (Arrays.asList(rockstarGames)), new ArrayList<> (Arrays.asList(action, openWorld, historical)));
        Game halfLife2 = new Game("Half-Life 2", "2004-11-16", new ArrayList<> (Arrays.asList(pc, xbox360)), new ArrayList<> (Arrays.asList(valveCorporation)), new ArrayList<> (Arrays.asList(fps)));
        Game finalFantasyX = new Game("Final Fantasy X",  "2001-07-19", new ArrayList<> (Arrays.asList(playstation2)), new ArrayList<> (Arrays.asList(squareEnix)), new ArrayList<> (Arrays.asList(rpg)));
        Game uncharted4AThiefsEnd = new Game("Uncharted 4: A Thief's End", "2016-05-10", new ArrayList<> (Arrays.asList(playstation4)), new ArrayList<> (Arrays.asList(naughtyDog)), new ArrayList<> (Arrays.asList(action, adventure)));
        Game overwatch = new Game("Overwatch", "2016-05-24", new ArrayList<> (Arrays.asList(pc, xboxOne, playstation4)), new ArrayList<> (Arrays.asList(blizzardEntertainment)), new ArrayList<> (Arrays.asList(fps)));
        Game theElderScrollsVSkyrim = new Game("The Elder Scrolls V: Skyrim", "2011-11-11", new ArrayList<> (Arrays.asList(pc, xbox360, playstation3, nintendoSwitch, xboxOne, playstation4)), new ArrayList<> (Arrays.asList(bethesdaGameStudios)), new ArrayList<> (Arrays.asList(openWorld, rpg)));
        Game finalFantasyIX = new Game("Final Fantasy IX", "2000-07-07", new ArrayList<> (Arrays.asList(playstation1)), new ArrayList<> (Arrays.asList(squareEnix)), new ArrayList<> (Arrays.asList(rpg)));
        Game pacman = new Game("Pac-Man", "1980-05-22", new ArrayList<> (Arrays.asList(arcade, nes, atari2600)), new ArrayList<> (Arrays.asList(namco)), new ArrayList<> (Arrays.asList(action)));
        Game superMarioBros3 = new Game("Super Mario Bros. 3", "1988-10-23", new ArrayList<> (Arrays.asList(nes)), new ArrayList<> (Arrays.asList(nintendo)), new ArrayList<> (Arrays.asList(platformer)));
        Game theLegendOfZelda = new Game("The Legend of Zelda", "1986-02-21", new ArrayList<> (Arrays.asList(nes)), new ArrayList<> (Arrays.asList(nintendo)), new ArrayList<> (Arrays.asList(action, adventure)));
        Game spaceInvaders = new Game("Space Invaders", "1978-06-10", new ArrayList<> (Arrays.asList(arcade, atari2600)), new ArrayList<> (Arrays.asList(taito)), new ArrayList<> (Arrays.asList(action)));
        Game donkeyKong = new Game("Donkey Kong", "1981-07-09", new ArrayList<> (Arrays.asList(arcade, nes)), new ArrayList<> (Arrays.asList(nintendo)), new ArrayList<> (Arrays.asList(platformer)));
        Game pong = new Game("Pong", "1972-11-29", new ArrayList<> (Arrays.asList(arcade, atari2600)), new ArrayList<> (Arrays.asList(atari)), new ArrayList<> (Arrays.asList(action)));
        Game galaga = new Game("Galaga", "1981-09-23", new ArrayList<> (Arrays.asList(arcade)), new ArrayList<> (Arrays.asList(namco)), new ArrayList<> (Arrays.asList(action)));
        Game metroid = new Game("Metroid", "1986-08-06", new ArrayList<> (Arrays.asList(nes)), new ArrayList<> (Arrays.asList(nintendo)), new ArrayList<> (Arrays.asList(action, adventure, platformer)));

        Customer dries = new Customer("Dries Ruttens", "dries.ruttens@student.uhasselt.be", "wachtwoord", 10);
        Customer bjorn = new Customer("Bjorn Spauwen", "bjorn.spauwen@student.uhasselt.be", "password", 20);
        Customer max = new Customer("Max-émile Meylaerts", "max-emile.meylaerts@student.uhasselt.be", "12345", 30);
        Customer mauro = new Customer("Mauro Vranckx", "mauro.vranckx@student.uhasselt.be", "isuckdick69420", 0);
        Customer eddy = new Customer("Edmond Tsampanis", "edmond.tsampanis@student.uhasselt.be", "StrongPass123!", 40);
        Customer jan = new Customer("Jan Alleman", "jan.alleman@fancycustomemail.com", "mijnverjaardag1999",-5);
        Customer customer1 = new Customer("John Doe", "john.doe@email.com", "password123", 1000);
        Customer customer2 = new Customer("Alice Smith", "alice.smith@email.com", "securePass", 1500);
        Customer customer3 = new Customer("Bob Johnson", "bob.johnson@email.com", "strongPassword", 2000);
        Customer customer4 = new Customer("Eva Brown", "eva.brown@email.com", "pass1234", 1200);
        Customer customer5 = new Customer("Charlie Wilson", "charlie.wilson@email.com", "password", 800);
        Customer customer6 = new Customer("Will E. Paymore", "will.paymore@email.com", "buymoreStuff", 5000);
        Customer customer7 = new Customer("Ivana Spendalot", "ivana.spend@email.com", "splurgeQueen", 700);
        Customer customer8 = new Customer("Cashew Nutt", "cashew.nutt@email.com", "nuttyRiches", 3000);
        Customer customer9 = new Customer("Billie Moola", "billie.moola@email.com", "dollarSigns", 50);
        Customer customer10 = new Customer("Richie McFortune", "richie.fortune@email.com", "wealthyRich", 10000);

        CopyBuilder builder = new CopyBuilder();
        Copy doomCopy1 = builder.game(doom)
                                .console(pc)
                                .availability(Availability.AVAILABLE)
                                .warehouse("Genk")
                                .purchasePrice(30)
                                .purchasePrice(6)
                                .build();

        builder = new CopyBuilder();
        Copy witcherCopy1 = builder.game(theWitcher3)
                                   .console(playstation4)
                                   .availability(Availability.AVAILABLE)
                                   .warehouse("Hasselt")
                                   .purchasePrice(70)
                                   .rentPrice(4)
                                   .build();

        builder = new CopyBuilder();
        Copy marioCopy1 = builder.game(superMarioBros)
                                 .console(nes)
                                 .availability(Availability.AVAILABLE)
                                 .warehouse("Los Angeles")
                                 .purchasePrice(65)
                                 .rentPrice(8)
                                 .build();

        builder = new CopyBuilder();
        Copy minecraftCopy1 = builder.game(minecraft)
                                     .console(pc)
                                     .availability(Availability.RENTED)
                                     .warehouse("Tokyo")
                                     .customer(bjorn)
                                     .purchasePrice(20)
                                     .rentPrice(3)
                                     .dateOfReturn("2024-01-12")
                                     .build();
                                     
        builder = new CopyBuilder();
        Copy minecraftCopy2 = builder.game(minecraft)
                                     .console(playstation5)
                                     .availability(Availability.AVAILABLE)
                                     .warehouse("Diepenbeek")
                                     .purchasePrice(20)
                                     .rentPrice(3)
                                     .build();

        builder = new CopyBuilder();
        Copy pokemonPatinumCopy1 = builder.game(pokemonPlatinum)
                                          .console(nintenoDS)
                                          .availability(Availability.AVAILABLE)
                                          .warehouse("Genk")
                                          .purchasePrice(35)
                                          .rentPrice(6)
                                          .build();

        builder = new CopyBuilder();
        Copy pokemonPlatinumCopy2 = builder.game(pokemonPlatinum)
                                          .console(nintenoDS)
                                          .availability(Availability.AVAILABLE)
                                          .warehouse("Genk")
                                          .purchasePrice(35)
                                          .rentPrice(6)
                                          .build();

        builder = new CopyBuilder();
        Copy metalGearSolidCopy1 = builder.game(metalGearSolid)
                                          .console(playstation1)
                                          .availability(Availability.AVAILABLE)
                                          .warehouse("Brussels")
                                          .build();
        
        builder = new CopyBuilder();
        Copy finalFantasyVIICopy1 = builder.game(finalFantasyVII)
                                             .console(pc)
                                             .availability(Availability.SOLD)
                                             .warehouse("Antwerp")
                                             .purchasePrice(50)
                                             .build();
        
        builder = new CopyBuilder();
        Copy haloCombatEvolvedCopy1 = builder.game(haloCombatEvolved)
                                               .console(xbox)
                                               .availability(Availability.BROKEN)
                                               .warehouse("Ghent")
                                               .build();
        
        builder = new CopyBuilder();
        Copy zeldaBreathOfTheWildCopy1 = builder.game(zeldaBreathOfTheWild)
                                                  .console(nintendoSwitch)
                                                  .availability(Availability.RENTED)
                                                  .warehouse("Paris")
                                                  .customer(customer5)
                                                  .rentPrice(5)
                                                  .dateOfReturn("2024-01-15")
                                                  .build();
                                    
        builder = new CopyBuilder();        
        Copy darkSoulsCopy1 = builder.game(darkSouls)
                                      .console(playstation3)
                                      .availability(Availability.AVAILABLE)
                                      .warehouse("Tokyo")
                                      .purchasePrice(69)
                                      .build();
        
        builder = new CopyBuilder();                                      
        Copy grandTheftAutoVCopy1 = builder.game(grandTheftAutoV)
                                        .console(xboxOne)
                                        .availability(Availability.RENTED)
                                        .warehouse("New York")
                                        .customer(max)
                                        .rentPrice(8)
                                        .dateOfReturn("2024-01-20")
                                        .build();

        builder = new CopyBuilder();        
        Copy theLastOfUsCopy1 = builder.game(theLastOfUs)
                                      .console(playstation4)
                                      .availability(Availability.AVAILABLE)
                                      .warehouse("Los Angeles")
                                      .build();

        builder = new CopyBuilder();        
        Copy worldOfWarcraftCopy1 = builder.game(worldOfWarcraft)
                                         .console(pc)
                                         .availability(Availability.AVAILABLE)
                                         .warehouse("Blizzard")
                                         .build();

        builder = new CopyBuilder();        
        Copy marioKart8DeluxeCopy1 = builder.game(marioKart8Deluxe)
                                           .console(nintendoSwitch)
                                           .availability(Availability.AVAILABLE)
                                           .warehouse("Kyoto")
                                           .build();

        builder = new CopyBuilder();                                           
        Copy doomCopy2 = builder.game(doom)
                                .console(pc)
                                .availability(Availability.AVAILABLE)
                                .warehouse("Ghent")
                                .build();

        builder = new CopyBuilder();        
        Copy minecraftCopy3 = builder.game(minecraft)
                                    .console(xboxOne)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("Antwerp")
                                    .build();

        builder = new CopyBuilder();        
        Copy metalGearSolidCopy2 = builder.game(metalGearSolid)
                                            .console(playstation1)
                                            .availability(Availability.RENTED)
                                            .warehouse("Diepenbeek")
                                            .customer(customer3)
                                            .rentPrice(7)
                                            .dateOfReturn("2024-01-18")
                                            .build();

        builder = new CopyBuilder();        
        Copy finalFantasyVIICopy2 = builder.game(finalFantasyVII)
                                            .console(playstation1)
                                            .availability(Availability.BROKEN)
                                            .warehouse("Genk")
                                            .build();

        builder = new CopyBuilder();        
        Copy zeldaBreathOfTheWildCopy2 = builder.game(zeldaBreathOfTheWild)
                            .console(nintendoSwitch)
                            .availability(Availability.AVAILABLE)
                            .warehouse("Tokyo")
                            .build();

        builder = new CopyBuilder();        
        Copy darkSoulsCopy2 = builder.game(darkSouls)
                                    .console(xbox360)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("Hasselt")
                                    .build();

        builder = new CopyBuilder();                                    
        Copy grandTheftAutoVCopy2 = builder.game(grandTheftAutoV)
                                            .console(playstation3)
                                            .availability(Availability.RENTED)
                                            .warehouse("Genk")
                                            .customer(max)
                                            .rentPrice(8)
                                            .dateOfReturn("2024-01-20")
                                            .build();

        builder = new CopyBuilder();        
        Copy theLastOfUsCopy2 = builder.game(theLastOfUs)
                                        .console(playstation3)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Brussels")
                                        .build();

        builder = new CopyBuilder();        
        Copy worldOfWarcraftCopy2 = builder.game(worldOfWarcraft)
                                            .console(pc)
                                            .availability(Availability.AVAILABLE)
                                            .warehouse("Blizzard")
                                            .build();

        builder = new CopyBuilder();        
        Copy marioKart8DeluxeCopy2 = builder.game(marioKart8Deluxe)
                                            .console(nintendoSwitch)
                                            .availability(Availability.AVAILABLE)
                                            .warehouse("Kyoto")
                                            .build();

        builder = new CopyBuilder();        
        Copy finalFantasyIXCopy1 = builder.game(finalFantasyIX)
                                        .console(playstation1)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Brussels")
                                        .build();

        builder = new CopyBuilder();        
        Copy pacmanCopy1 = builder.game(pacman)
                                .console(arcade)
                                .availability(Availability.AVAILABLE)
                                .warehouse("Los Angeles")
                                .build();

        builder = new CopyBuilder();        
        Copy superMarioBros3Copy1 = builder.game(superMarioBros3)
                                            .console(nes)
                                            .availability(Availability.AVAILABLE)
                                            .warehouse("Kyoto")
                                            .build();

        builder = new CopyBuilder();        
        Copy theLegendOfZeldaCopy1 = builder.game(theLegendOfZelda)
                                            .console(nes)
                                            .availability(Availability.AVAILABLE)
                                            .warehouse("Ghent")
                                            .build();

        builder = new CopyBuilder();        
        Copy spaceInvadersCopy1 = builder.game(spaceInvaders)
                                        .console(arcade)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Tokyo")
                                        .build();

        builder = new CopyBuilder();        
        Copy donkeyKongCopy1 = builder.game(donkeyKong)
                                        .console(arcade)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Paris")
                                        .build();

        builder = new CopyBuilder();        
        Copy pongCopy1 = builder.game(pong)
                                .console(arcade)
                                .availability(Availability.AVAILABLE)
                                .warehouse("Antwerp")
                                .build();

        builder = new CopyBuilder();        
        Copy galagaCopy1 = builder.game(galaga)
                                    .console(arcade)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("Genk")
                                    .build();

        builder = new CopyBuilder();        
        Copy metroidCopy1 = builder.game(metroid)
                                    .console(nes)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("Diepenbeek")
                                    .build();   

        builder = new CopyBuilder();                                    
        Copy assassinsCreedOriginsCopy1 = builder.game(assassinsCreedOrigins)
                                                .console(xboxSeriesX)
                                                .availability(Availability.AVAILABLE)
                                                .warehouse("New York")
                                                .build();

        builder = new CopyBuilder();        
        Copy redDeadRedemption2Copy1 = builder.game(redDeadRedemption2)
                                                .console(playstation5)
                                                .availability(Availability.AVAILABLE)
                                                .warehouse("Los Angeles")
                                                .build();

        builder = new CopyBuilder();                                        
        Copy uncharted4AThiefsEndCopy1 = builder.game(uncharted4AThiefsEnd)
                                                .console(playstation4)
                                                .availability(Availability.AVAILABLE)
                                                .warehouse("Tokyo")
                                                .build();

        builder = new CopyBuilder();        
        Copy overwatchCopy1 = builder.game(overwatch)
                                    .console(xboxOne)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("London")
                                    .build();

        builder = new CopyBuilder();
        Copy theElderScrollsVSkyrimCopy1 = builder.game(theElderScrollsVSkyrim)
                                                    .console(nintendoSwitch)
                                                    .availability(Availability.AVAILABLE)
                                                    .warehouse("Berlin")
                                                    .build();

        builder = new CopyBuilder();                                            
        Copy halfLife2Copy1 = builder.game(halfLife2)
                                    .console(pc)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("Paris")
                                    .build();

        builder = new CopyBuilder();        
        Copy finalFantasyXCopy1 = builder.game(finalFantasyX)
                                        .console(playstation2)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Madrid")
                                        .build();

        builder = new CopyBuilder();        
        Copy finalFantasyIXCopy2 = builder.game(finalFantasyIX)
                                        .console(pc)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Rome")
                                        .build();

        builder = new CopyBuilder();
        Copy donkeyKongCopy2 = builder.game(donkeyKong)
                                        .console(arcade)
                                        .availability(Availability.BROKEN)
                                        .warehouse("Genk")
                                        .build();
                            
                                            
        MonetaryTransaction transaction1 = new MonetaryTransaction(TransactionType.SALE, 60, jan, witcherCopy1, "2022-05-12");
        MonetaryTransaction transaction2 = new MonetaryTransaction(TransactionType.RENTAL, 9, bjorn, minecraftCopy2, "2023-12-12");

        doom.setCopies(new ArrayList<> (Arrays.asList(doomCopy1, doomCopy2)));
        theWitcher3.setCopies(new ArrayList<> (Arrays.asList(witcherCopy1)));
        superMarioBros.setCopies(new ArrayList<> (Arrays.asList(marioCopy1)));
        minecraft.setCopies(new ArrayList<> (Arrays.asList(minecraftCopy1, minecraftCopy2, minecraftCopy3)));
        pokemonPlatinum.setCopies(new ArrayList<> (Arrays.asList(pokemonPatinumCopy1, pokemonPlatinumCopy2)));
        metalGearSolid.setCopies(new ArrayList<> (Arrays.asList(metalGearSolidCopy1, metalGearSolidCopy2)));
        finalFantasyVII.setCopies(new ArrayList<> (Arrays.asList(finalFantasyVIICopy1, finalFantasyVIICopy2)));
        finalFantasyIX.setCopies(new ArrayList<> (Arrays.asList(finalFantasyIXCopy1, finalFantasyIXCopy2)));
        haloCombatEvolved.setCopies(new ArrayList<> (Arrays.asList(haloCombatEvolvedCopy1)));
        zeldaBreathOfTheWild.setCopies(new ArrayList<> (Arrays.asList(zeldaBreathOfTheWildCopy1, zeldaBreathOfTheWildCopy2)));
        darkSouls.setCopies(new ArrayList<> (Arrays.asList(darkSoulsCopy1, darkSoulsCopy2)));
        grandTheftAutoV.setCopies(new ArrayList<> (Arrays.asList(grandTheftAutoVCopy1, grandTheftAutoVCopy2)));
        theLastOfUs.setCopies(new ArrayList<> (Arrays.asList(theLastOfUsCopy1, theLastOfUsCopy2)));
        worldOfWarcraft.setCopies(new ArrayList<> (Arrays.asList(worldOfWarcraftCopy1, worldOfWarcraftCopy2)));
        marioKart8Deluxe.setCopies(new ArrayList<> (Arrays.asList(marioKart8DeluxeCopy1, marioKart8DeluxeCopy2)));
        pacman.setCopies(new ArrayList<> (Arrays.asList(pacmanCopy1)));
        superMarioBros3.setCopies(new ArrayList<> (Arrays.asList(superMarioBros3Copy1)));
        theLegendOfZelda.setCopies(new ArrayList<> (Arrays.asList(theLegendOfZeldaCopy1)));
        spaceInvaders.setCopies(new ArrayList<> (Arrays.asList(spaceInvadersCopy1)));
        donkeyKong.setCopies(new ArrayList<> (Arrays.asList(donkeyKongCopy1)));
        pong.setCopies(new ArrayList<> (Arrays.asList(pongCopy1)));
        galaga.setCopies(new ArrayList<> (Arrays.asList(galagaCopy1)));
        metroid.setCopies(new ArrayList<> (Arrays.asList(metroidCopy1)));
        assassinsCreedOrigins.setCopies(new ArrayList<> (Arrays.asList(assassinsCreedOriginsCopy1)));
        redDeadRedemption2.setCopies(new ArrayList<> (Arrays.asList(redDeadRedemption2Copy1)));
        uncharted4AThiefsEnd.setCopies(new ArrayList<> (Arrays.asList(uncharted4AThiefsEndCopy1)));
        overwatch.setCopies(new ArrayList<> (Arrays.asList(overwatchCopy1)));
        theElderScrollsVSkyrim.setCopies(new ArrayList<> (Arrays.asList(theElderScrollsVSkyrimCopy1)));
        finalFantasyX.setCopies(new ArrayList<> (Arrays.asList(finalFantasyXCopy1)));
        donkeyKong.setCopies(new ArrayList<> (Arrays.asList(donkeyKongCopy1, donkeyKongCopy2)));

        action.setGames(new ArrayList<> (Arrays.asList(metalGearSolid, haloCombatEvolved, pacman, spaceInvaders, donkeyKong, pong, galaga, zeldaBreathOfTheWild, darkSouls, grandTheftAutoV, theLastOfUs, assassinsCreedOrigins, uncharted4AThiefsEnd, pacman, theLegendOfZelda, metroid)));
        adventure.setGames(new ArrayList<> (Arrays.asList(theWitcher3, zeldaBreathOfTheWild, theLastOfUs, uncharted4AThiefsEnd, metroid, theLegendOfZelda)));
        battleRoyale.setGames(new ArrayList<> (Arrays.asList(/* Add your co-op games instances here */)));
        coOp.setGames(new ArrayList<> (Arrays.asList(/* Add your co-op games instances here */)));
        educational.setGames(new ArrayList<> (Arrays.asList(/* Add your co-op games instances here */)));
        fighting.setGames(new ArrayList<> (Arrays.asList(/* Add your fighting games instances here */)));
        fps.setGames(new ArrayList<> (Arrays.asList(doom, haloCombatEvolved, overwatch, halfLife2)));
        historical.setGames(new ArrayList<> (Arrays.asList(assassinsCreedOrigins, redDeadRedemption2)));
        horror.setGames(new ArrayList<> (Arrays.asList(/* Add your co-op games instances here */)));
        indie.setGames(new ArrayList<> (Arrays.asList(/* Add your indie games instances here */)));
        mmorpg.setGames(new ArrayList<> (Arrays.asList(worldOfWarcraft)));
        openWorld.setGames(new ArrayList<> (Arrays.asList(theWitcher3, grandTheftAutoV, theElderScrollsVSkyrim, assassinsCreedOrigins, redDeadRedemption2, zeldaBreathOfTheWild)));
        party.setGames(Collections.singletonList(marioKart8Deluxe));
        platformer.setGames(new ArrayList<> (Arrays.asList(superMarioBros, superMarioBros3, donkeyKong, metroid)));
        puzzle.setGames(new ArrayList<> (Arrays.asList(/* Add your co-op games instances here */)));
        racing.setGames(new ArrayList<> (Arrays.asList(marioKart8Deluxe /* Add your racing games instances here */)));
        rpg.setGames(new ArrayList<> (Arrays.asList(theWitcher3, finalFantasyVII, darkSouls, finalFantasyX, finalFantasyIX, pokemonPlatinum, theElderScrollsVSkyrim)));
        rhythm.setGames(new ArrayList<> (Arrays.asList(/* Add your co-op games instances here */)));
        sandbox.setGames(new ArrayList<> (Arrays.asList(minecraft)));
        sciFi.setGames(new ArrayList<> (Arrays.asList(haloCombatEvolved /* Add your sci-fi games instances here */)));
        simulation.setGames(new ArrayList<> (Arrays.asList(/* Add your co-op games instances here */)));
        sportsAndRacing.setGames(new ArrayList<> (Arrays.asList(/* Add your co-op games instances here */)));
        stealth.setGames(new ArrayList<> (Arrays.asList(metalGearSolid)));
        strategy.setGames(new ArrayList<> (Arrays.asList(/* Add your strategy games instances here */)));
        survival.setGames(new ArrayList<> (Arrays.asList(minecraft)));

        nes.setGames(new ArrayList<> (Arrays.asList(superMarioBros, pacman, superMarioBros3, theLegendOfZelda, donkeyKong, metroid)));
        playstation4.setGames(new ArrayList<> (Arrays.asList(theWitcher3, minecraft, finalFantasyVII, theLastOfUs, assassinsCreedOrigins, redDeadRedemption2, uncharted4AThiefsEnd, overwatch, grandTheftAutoV)));
        xboxOne.setGames(new ArrayList<> (Arrays.asList(theWitcher3, minecraft, finalFantasyVII, assassinsCreedOrigins, theElderScrollsVSkyrim, redDeadRedemption2, overwatch, grandTheftAutoV)));
        pc.setGames(new ArrayList<> (Arrays.asList(doom, minecraft, metalGearSolid, finalFantasyVII, theElderScrollsVSkyrim, halfLife2, darkSouls, grandTheftAutoV, worldOfWarcraft, finalFantasyX, uncharted4AThiefsEnd, overwatch, theElderScrollsVSkyrim, finalFantasyIX, theWitcher3, finalFantasyVII, assassinsCreedOrigins)));
        nintendoSwitch.setGames(new ArrayList<> (Arrays.asList(minecraft, zeldaBreathOfTheWild, marioKart8Deluxe, redDeadRedemption2, theElderScrollsVSkyrim)));
        nintenoDS.setGames(new ArrayList<> (Arrays.asList(pokemonPlatinum)));
        playstation1.setGames(new ArrayList<> (Arrays.asList(metalGearSolid, finalFantasyVII, finalFantasyIX)));
        xbox.setGames(new ArrayList<> (Arrays.asList(haloCombatEvolved)));
        playstation3.setGames(new ArrayList<> (Arrays.asList(darkSouls, theLastOfUs, grandTheftAutoV)));
        xbox360.setGames(new ArrayList<> (Arrays.asList(darkSouls, halfLife2, theElderScrollsVSkyrim, minecraft, grandTheftAutoV)));
        atari2600.setGames(new ArrayList<> (Arrays.asList(pacman, spaceInvaders, pong)));
        arcade.setGames(new ArrayList<> (Arrays.asList(spaceInvaders, donkeyKong, pong, galaga, pacman)));
        xboxSeriesX.setGames(new ArrayList<> (Arrays.asList(minecraft, grandTheftAutoV, redDeadRedemption2)));
        playstation5.setGames(new ArrayList<> (Arrays.asList(minecraft, grandTheftAutoV, redDeadRedemption2)));
        playstation2.setGames(new ArrayList<> (Arrays.asList(finalFantasyX)));

        atari.setGames(new ArrayList<> (Arrays.asList(pong)));
        idSoftware.setGames(new ArrayList<> (Arrays.asList(doom)));
        cdProjekt.setGames(new ArrayList<> (Arrays.asList(theWitcher3)));
        nintendo.setGames(new ArrayList<> (Arrays.asList(superMarioBros, pokemonPlatinum, marioKart8Deluxe, superMarioBros3, theLegendOfZelda, donkeyKong, metroid)));
        microsoftStudios.setGames(new ArrayList<> (Arrays.asList(haloCombatEvolved, minecraft)));
        gameFreak.setGames(new ArrayList<> (Arrays.asList(pokemonPlatinum)));
        konami.setGames(new ArrayList<> (Arrays.asList(metalGearSolid)));
        squareEnix.setGames(new ArrayList<> (Arrays.asList(finalFantasyVII, finalFantasyX, finalFantasyIX)));
        fromSoftware.setGames(new ArrayList<> (Arrays.asList(darkSouls)));
        rockstarGames.setGames(new ArrayList<> (Arrays.asList(grandTheftAutoV, redDeadRedemption2)));
        naughtyDog.setGames(new ArrayList<> (Arrays.asList(theLastOfUs, uncharted4AThiefsEnd)));
        blizzardEntertainment.setGames(new ArrayList<> (Arrays.asList(worldOfWarcraft, overwatch)));
        valveCorporation.setGames(new ArrayList<> (Arrays.asList(halfLife2)));
        ubisoft.setGames(new ArrayList<> (Arrays.asList(assassinsCreedOrigins)));
        bethesdaGameStudios.setGames(new ArrayList<> (Arrays.asList(theElderScrollsVSkyrim)));
        namco.setGames(new ArrayList<> (Arrays.asList(pacman, galaga)));
        taito.setGames(new ArrayList<> (Arrays.asList(spaceInvaders)));
        atari.setGames(new ArrayList<> (Arrays.asList(pong)));

        nes.setCopies(new ArrayList<> (Arrays.asList(marioCopy1, pacmanCopy1, superMarioBros3Copy1, theLegendOfZeldaCopy1, donkeyKongCopy1, metroidCopy1)));
        playstation4.setCopies(new ArrayList<> (Arrays.asList(witcherCopy1, theLastOfUsCopy1, uncharted4AThiefsEndCopy1)));
        xboxOne.setCopies(new ArrayList<> (Arrays.asList(minecraftCopy3, overwatchCopy1, grandTheftAutoVCopy1)));
        pc.setCopies(new ArrayList<> (Arrays.asList(doomCopy1, doomCopy2, minecraftCopy1, finalFantasyVIICopy1, halfLife2Copy1, darkSoulsCopy1, darkSoulsCopy2, worldOfWarcraftCopy1, worldOfWarcraftCopy2, finalFantasyIXCopy1)));
        nintendoSwitch.setCopies(new ArrayList<> (Arrays.asList(zeldaBreathOfTheWildCopy1, zeldaBreathOfTheWildCopy2, marioKart8DeluxeCopy1, marioKart8DeluxeCopy2, theElderScrollsVSkyrimCopy1)));
        nintenoDS.setCopies(new ArrayList<> (Arrays.asList(pokemonPatinumCopy1, pokemonPlatinumCopy2)));
        playstation1.setCopies(new ArrayList<> (Arrays.asList(metalGearSolidCopy1, metalGearSolidCopy2, finalFantasyVIICopy2, finalFantasyIXCopy1)));
        xbox.setCopies(new ArrayList<> (Arrays.asList(haloCombatEvolvedCopy1)));
        playstation3.setCopies(new ArrayList<> (Arrays.asList(darkSoulsCopy1, theLastOfUsCopy2, grandTheftAutoVCopy2)));
        xbox360.setCopies(new ArrayList<> (Arrays.asList(darkSoulsCopy2, minecraftCopy1)));
        atari2600.setCopies(new ArrayList<> (Arrays.asList(pacmanCopy1, spaceInvadersCopy1, pongCopy1)));
        arcade.setCopies(new ArrayList<> (Arrays.asList(spaceInvadersCopy1, donkeyKongCopy2, pongCopy1, galagaCopy1, pacmanCopy1)));
        xboxSeriesX.setCopies(new ArrayList<> (Arrays.asList(assassinsCreedOriginsCopy1)));
        playstation5.setCopies(new ArrayList<> (Arrays.asList(minecraftCopy2, redDeadRedemption2Copy1)));
        playstation2.setCopies(new ArrayList<> (Arrays.asList(finalFantasyXCopy1)));

        database.saveNewObject(action);
        database.saveNewObject(adventure);
        database.saveNewObject(battleRoyale);
        database.saveNewObject(coOp);
        database.saveNewObject(educational);
        database.saveNewObject(fighting);
        database.saveNewObject(fps);
        database.saveNewObject(historical);
        database.saveNewObject(horror);
        database.saveNewObject(indie);
        database.saveNewObject(mmorpg);
        database.saveNewObject(openWorld);
        database.saveNewObject(party);
        database.saveNewObject(platformer);
        database.saveNewObject(puzzle);
        database.saveNewObject(racing);
        database.saveNewObject(rpg);
        database.saveNewObject(rhythm);
        database.saveNewObject(sandbox);
        database.saveNewObject(sciFi);
        database.saveNewObject(simulation);
        database.saveNewObject(sportsAndRacing);
        database.saveNewObject(stealth);
        database.saveNewObject(strategy);
        database.saveNewObject(survival);
        
        database.saveNewObject(nintendo);
        database.saveNewObject(microsoftStudios);
        database.saveNewObject(electronicArts);
        database.saveNewObject(ubisoft);
        database.saveNewObject(activision);
        database.saveNewObject(blizzardEntertainment);
        database.saveNewObject(squareEnix);
        database.saveNewObject(epicGames);
        database.saveNewObject(valveCorporation);
        database.saveNewObject(bethesdaGameStudios);
        database.saveNewObject(cdProjekt);
        database.saveNewObject(rockstarGames);
        database.saveNewObject(naughtyDog);
        database.saveNewObject(bioware);
        database.saveNewObject(idSoftware);
        database.saveNewObject(gameFreak);
        database.saveNewObject(gearboxSoftware);
        database.saveNewObject(obsidianEntertainment);
        database.saveNewObject(platinumGames);
        database.saveNewObject(respawnEntertainment);
        database.saveNewObject(supergiantGames);
        database.saveNewObject(theCoalition);
        database.saveNewObject(atlus);
        database.saveNewObject(bandaiNamco);
        database.saveNewObject(capcom);
        database.saveNewObject(dice);
        database.saveNewObject(fromSoftware);
        database.saveNewObject(insomniacGames);
        database.saveNewObject(konami);
        database.saveNewObject(namco);
        database.saveNewObject(sega);
        database.saveNewObject(atari);
        database.saveNewObject(taito);

        database.saveNewObject(playstation5);
        database.saveNewObject(playstation4);
        database.saveNewObject(playstation3);
        database.saveNewObject(playstation2);
        database.saveNewObject(playstation1);
        database.saveNewObject(xboxSeriesX);
        database.saveNewObject(xboxOne);
        database.saveNewObject(xbox360);
        database.saveNewObject(xbox);
        database.saveNewObject(wii);
        database.saveNewObject(nintendoSwitch);
        database.saveNewObject(nintendo3DS);
        database.saveNewObject(nintenoDS);
        database.saveNewObject(gameBoyAdvance);
        database.saveNewObject(gameBoyColor);
        database.saveNewObject(gameBoy);
        database.saveNewObject(atari2600);
        database.saveNewObject(pc);
        database.saveNewObject(nes);  
        database.saveNewObject(snes);
        database.saveNewObject(segaGenesis);
        database.saveNewObject(segaDreamcast);
        database.saveNewObject(segaSaturn);
        database.saveNewObject(segaMasterSystem);
        database.saveNewObject(nintendo64);
        database.saveNewObject(gameCube);
        database.saveNewObject(sonyPSP);
        database.saveNewObject(sonyPSVita);
        database.saveNewObject(arcade);  
        
        database.saveNewObject(doom);
        database.saveNewObject(theWitcher3);
        database.saveNewObject(superMarioBros);
        database.saveNewObject(minecraft);
        database.saveNewObject(pokemonPlatinum);
        database.saveNewObject(pacman);
        database.saveNewObject(superMarioBros3);
        database.saveNewObject(theLegendOfZelda);
        database.saveNewObject(spaceInvaders);
        database.saveNewObject(donkeyKong);
        database.saveNewObject(pong);
        database.saveNewObject(galaga);
        database.saveNewObject(metroid);
        database.saveNewObject(metalGearSolid);
        database.saveNewObject(finalFantasyVII);
        database.saveNewObject(haloCombatEvolved);
        database.saveNewObject(zeldaBreathOfTheWild);
        database.saveNewObject(darkSouls);
        database.saveNewObject(grandTheftAutoV);
        database.saveNewObject(theLastOfUs);
        database.saveNewObject(worldOfWarcraft);
        database.saveNewObject(marioKart8Deluxe);
        database.saveNewObject(assassinsCreedOrigins);
        database.saveNewObject(redDeadRedemption2);
        database.saveNewObject(halfLife2);
        database.saveNewObject(finalFantasyX);
        database.saveNewObject(uncharted4AThiefsEnd);
        database.saveNewObject(overwatch);
        database.saveNewObject(theElderScrollsVSkyrim);
        database.saveNewObject(finalFantasyIX);

        database.saveNewObject(dries);
        database.saveNewObject(bjorn);
        database.saveNewObject(max);
        database.saveNewObject(mauro);
        database.saveNewObject(eddy);
        database.saveNewObject(jan);
        database.saveNewObject(customer1);
        database.saveNewObject(customer2);
        database.saveNewObject(customer3);
        database.saveNewObject(customer4);
        database.saveNewObject(customer5);
        database.saveNewObject(customer6);
        database.saveNewObject(customer7);
        database.saveNewObject(customer8);
        database.saveNewObject(customer9);
        database.saveNewObject(customer10);

        database.saveNewObject(doomCopy1);
        database.saveNewObject(witcherCopy1);
        database.saveNewObject(marioCopy1);
        database.saveNewObject(minecraftCopy1);
        database.saveNewObject(minecraftCopy2);
        database.saveNewObject(pokemonPatinumCopy1);
        database.saveNewObject(metalGearSolidCopy1);
        database.saveNewObject(finalFantasyVIICopy1);
        database.saveNewObject(haloCombatEvolvedCopy1);
        database.saveNewObject(zeldaBreathOfTheWildCopy1);
        database.saveNewObject(darkSoulsCopy1);
        database.saveNewObject(grandTheftAutoVCopy1);
        database.saveNewObject(theLastOfUsCopy1);
        database.saveNewObject(worldOfWarcraftCopy1);
        database.saveNewObject(marioKart8DeluxeCopy1);
        database.saveNewObject(doomCopy2);
        database.saveNewObject(minecraftCopy3);
        database.saveNewObject(metalGearSolidCopy2);
        database.saveNewObject(finalFantasyVIICopy2);
        database.saveNewObject(zeldaBreathOfTheWildCopy2);
        database.saveNewObject(darkSoulsCopy2);
        database.saveNewObject(grandTheftAutoVCopy2);
        database.saveNewObject(theLastOfUsCopy2);
        database.saveNewObject(worldOfWarcraftCopy2);
        database.saveNewObject(marioKart8DeluxeCopy2);
        database.saveNewObject(pokemonPlatinumCopy2);
        database.saveNewObject(finalFantasyIXCopy1);
        database.saveNewObject(pacmanCopy1);
        database.saveNewObject(superMarioBros3Copy1);
        database.saveNewObject(theLegendOfZeldaCopy1);
        database.saveNewObject(spaceInvadersCopy1);
        database.saveNewObject(donkeyKongCopy1);
        database.saveNewObject(pongCopy1);
        database.saveNewObject(galagaCopy1);
        database.saveNewObject(metroidCopy1);
        database.saveNewObject(overwatchCopy1);
        database.saveNewObject(uncharted4AThiefsEndCopy1);
        database.saveNewObject(redDeadRedemption2Copy1);
        database.saveNewObject(assassinsCreedOriginsCopy1);
        database.saveNewObject(theElderScrollsVSkyrimCopy1);
        database.saveNewObject(halfLife2Copy1);
        database.saveNewObject(finalFantasyXCopy1);
        database.saveNewObject(finalFantasyIXCopy2);
        
        database.saveNewObject(transaction1);
        database.saveNewObject(transaction2);
    }

}




