package be.kuleuven.VGHF;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.hibernate.annotations.Generated;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.List;

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
        Console nintenoDS = new Console("Nintendo DS", Arrays.asList(nintendo3DS));
        Console gameBoyAdvance = new Console("Game Boy Advance", Arrays.asList(nintenoDS));
        Console gameBoyColor = new Console("Game Boy Color", Arrays.asList(gameBoyAdvance));
        Console gameBoy = new Console("Game Boy", Arrays.asList(gameBoyColor, gameBoyAdvance));
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

        Game doom = new Game("DOOM", "id Software", "1993-12-10", Arrays.asList(pc), Arrays.asList(idSoftware), Arrays.asList(fps));
        Game theWitcher3 = new Game("The Witcher 3: Wild Hunt", "CD Projekt", "2015-05-19", Arrays.asList(playstation4, xboxOne, pc), Arrays.asList(cdProjekt), Arrays.asList(rpg, openWorld, adventure));
        Game superMarioBros = new Game("Super Mario Bros.", "Nintendo", "1985-09-13", Arrays.asList(nes), Arrays.asList(nintendo), Arrays.asList(platformer));
        Game minecraft = new Game("Minecraft", "Mojang", "2011-11-18", Arrays.asList(pc, xboxOne, playstation4, nintendoSwitch, xbox360, xboxSeriesX), Arrays.asList(microsoftStudios), Arrays.asList(sandbox, survival));
        Game pokemonPlatinum = new Game("Pokémon Platinum", "Nintendo", "2008-09-13", Arrays.asList(nintenoDS), Arrays.asList(nintendo, gameFreak), Arrays.asList(rpg));
        Game metalGearSolid = new Game("Metal Gear Solid", "Konami", "1998-10-21", Arrays.asList(playstation1, pc), Arrays.asList(konami), Arrays.asList(action, stealth));
        Game finalFantasyVII = new Game("Final Fantasy VII", "Square Enix", "1997-01-31", Arrays.asList(playstation1, pc), Arrays.asList(squareEnix), Arrays.asList(rpg));
        Game haloCombatEvolved = new Game("Halo: Combat Evolved", "Bungie", "2001-11-15", Arrays.asList(xbox), Arrays.asList(microsoftStudios), Arrays.asList(fps, action, sciFi));
        Game zeldaBreathOfTheWild = new Game("The Legend of Zelda: Breath of the Wild", "Nintendo", "2017-03-03", Arrays.asList(nintendoSwitch), Arrays.asList(nintendo), Arrays.asList(openWorld, action, adventure));
        Game darkSouls = new Game("Dark Souls", "FromSoftware", "2011-09-22", Arrays.asList(playstation3, xbox360, pc), Arrays.asList(fromSoftware), Arrays.asList(action, rpg));
        Game grandTheftAutoV = new Game("Grand Theft Auto V", "Rockstar Games", "2013-09-17", Arrays.asList(playstation4, xboxOne, pc, playstation3, xbox360, playstation5, xboxSeriesX), Arrays.asList(rockstarGames), Arrays.asList(action, openWorld));
        Game theLastOfUs = new Game("The Last of Us", "Naughty Dog", "2013-06-14", Arrays.asList(playstation3, playstation4), Arrays.asList(naughtyDog), Arrays.asList(action, adventure));
        Game worldOfWarcraft = new Game("World of Warcraft", "Blizzard Entertainment", "2004-11-23", Arrays.asList(pc), Arrays.asList(blizzardEntertainment), Arrays.asList(mmorpg));
        Game marioKart8Deluxe = new Game("Mario Kart 8 Deluxe", "Nintendo", "2017-04-28", Arrays.asList(nintendoSwitch), Arrays.asList(nintendo), Arrays.asList(racing, party));
        Game assassinsCreedOrigins = new Game("Assassin's Creed Origins", "Ubisoft", "2017-10-27", Arrays.asList(xboxOne, playstation4, pc), Arrays.asList(ubisoft), Arrays.asList(action, openWorld, historical));
        Game redDeadRedemption2 = new Game("Red Dead Redemption 2", "Rockstar Games", "2018-10-26", Arrays.asList(playstation4, xboxOne, pc, xboxSeriesX, playstation5), Arrays.asList(rockstarGames), Arrays.asList(action, openWorld, historical));
        Game halfLife2 = new Game("Half-Life 2", "Valve Corporation", "2004-11-16", Arrays.asList(pc, xbox360), Arrays.asList(valveCorporation), Arrays.asList(fps));
        Game finalFantasyX = new Game("Final Fantasy X", "Square Enix", "2001-07-19", Arrays.asList(playstation2), Arrays.asList(squareEnix), Arrays.asList(rpg));
        Game uncharted4AThiefsEnd = new Game("Uncharted 4: A Thief's End", "Naughty Dog", "2016-05-10", Arrays.asList(playstation4), Arrays.asList(naughtyDog), Arrays.asList(action, adventure));
        Game overwatch = new Game("Overwatch", "Blizzard Entertainment", "2016-05-24", Arrays.asList(pc, xboxOne, playstation4), Arrays.asList(blizzardEntertainment), Arrays.asList(fps));
        Game theElderScrollsVSkyrim = new Game("The Elder Scrolls V: Skyrim", "Bethesda Game Studios", "2011-11-11", Arrays.asList(pc, xbox360, playstation3, nintendoSwitch, xboxOne, playstation4), Arrays.asList(bethesdaGameStudios), Arrays.asList(openWorld, rpg));
        Game finalFantasyIX = new Game("Final Fantasy IX", "Square Enix", "2000-07-07", Arrays.asList(playstation1), Arrays.asList(squareEnix), Arrays.asList(rpg));
        Game pacman = new Game("Pac-Man", "Namco", "1980-05-22", Arrays.asList(arcade, nes, atari2600), Arrays.asList(namco), Arrays.asList(action));
        Game superMarioBros3 = new Game("Super Mario Bros. 3", "Nintendo", "1988-10-23", Arrays.asList(nes), Arrays.asList(nintendo), Arrays.asList(platformer));
        Game theLegendOfZelda = new Game("The Legend of Zelda", "Nintendo", "1986-02-21", Arrays.asList(nes), Arrays.asList(nintendo), Arrays.asList(action, adventure));
        Game spaceInvaders = new Game("Space Invaders", "Taito", "1978-06-10", Arrays.asList(arcade, atari2600), Arrays.asList(taito), Arrays.asList(action));
        Game donkeyKong = new Game("Donkey Kong", "Nintendo", "1981-07-09", Arrays.asList(arcade, nes), Arrays.asList(nintendo), Arrays.asList(platformer));
        Game pong = new Game("Pong", "Atari", "1972-11-29", Arrays.asList(arcade, atari2600), Arrays.asList(atari), Arrays.asList(action));
        Game galaga = new Game("Galaga", "Namco", "1981-09-23", Arrays.asList(arcade), Arrays.asList(namco), Arrays.asList(action));
        Game metroid = new Game("Metroid", "Nintendo", "1986-08-06", Arrays.asList(nes), Arrays.asList(nintendo), Arrays.asList(action, adventure, platformer));

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

        Copy witcherCopy1 = builder.game(theWitcher3)
                                   .console(playstation4)
                                   .availability(Availability.AVAILABLE)
                                   .warehouse("Hasselt")
                                   .purchasePrice(70)
                                   .rentPrice(4)
                                   .build();

        Copy marioCopy1 = builder.game(superMarioBros)
                                 .console(nes)
                                 .availability(Availability.AVAILABLE)
                                 .warehouse("Los Angeles")
                                 .purchasePrice(65)
                                 .rentPrice(8)
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
                                     .purchasePrice(20)
                                     .rentPrice(3)
                                     .build();
        Copy pokemonPatinumCopy1 = builder.game(pokemonPlatinum)
                                          .console(nintenoDS)
                                          .availability(Availability.AVAILABLE)
                                          .warehouse("Genk")
                                          .purchasePrice(35)
                                          .rentPrice(6)
                                          .build();
        Copy pokemonPlatinumCopy2 = builder.game(pokemonPlatinum)
                                          .console(nintenoDS)
                                          .availability(Availability.AVAILABLE)
                                          .warehouse("Genk")
                                          .purchasePrice(35)
                                          .rentPrice(6)
                                          .build();
        Copy metalGearSolidCopy1 = builder.game(metalGearSolid)
                                          .console(playstation1)
                                          .availability(Availability.AVAILABLE)
                                          .warehouse("Brussels")
                                          .build();
        
        Copy finalFantasyVIICopy1 = builder.game(finalFantasyVII)
                                             .console(pc)
                                             .availability(Availability.SOLD)
                                             .warehouse("Antwerp")
                                             .purchasePrice(50)
                                             .build();
        
        Copy haloCombatEvolvedCopy1 = builder.game(haloCombatEvolved)
                                               .console(xbox)
                                               .availability(Availability.BROKEN)
                                               .warehouse("Ghent")
                                               .build();
        
        Copy zeldaBreathOfTheWildCopy1 = builder.game(zeldaBreathOfTheWild)
                                                  .console(nintendoSwitch)
                                                  .availability(Availability.RENTED)
                                                  .warehouse("Paris")
                                                  .customer(customer5)
                                                  .rentPrice(5)
                                                  .dateOfReturn("2024-01-15")
                                                  .build();
        
        Copy darkSoulsCopy1 = builder.game(darkSouls)
                                      .console(playstation3)
                                      .availability(Availability.AVAILABLE)
                                      .warehouse("Tokyo")
                                      .purchasePrice(69)
                                      .build();
        
        Copy grandTheftAutoVCopy1 = builder.game(grandTheftAutoV)
                                        .console(xboxOne)
                                        .availability(Availability.RENTED)
                                        .warehouse("New York")
                                        .customer(max)
                                        .rentPrice(8)
                                        .dateOfReturn("2024-01-20")
                                        .build();
        
        Copy theLastOfUsCopy1 = builder.game(theLastOfUs)
                                      .console(playstation4)
                                      .availability(Availability.AVAILABLE)
                                      .warehouse("Los Angeles")
                                      .build();
        
        Copy worldOfWarcraftCopy1 = builder.game(worldOfWarcraft)
                                         .console(pc)
                                         .availability(Availability.AVAILABLE)
                                         .warehouse("Blizzard")
                                         .build();
        
        Copy marioKart8DeluxeCopy1 = builder.game(marioKart8Deluxe)
                                           .console(nintendoSwitch)
                                           .availability(Availability.AVAILABLE)
                                           .warehouse("Kyoto")
                                           .build();
        Copy doomCopy2 = builder.game(doom)
                                .console(pc)
                                .availability(Availability.AVAILABLE)
                                .warehouse("Ghent")
                                .build();
        
        Copy minecraftCopy3 = builder.game(minecraft)
                                    .console(xboxOne)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("Antwerp")
                                    .build();
        
        Copy metalGearSolidCopy2 = builder.game(metalGearSolid)
                                            .console(playstation1)
                                            .availability(Availability.RENTED)
                                            .warehouse("Diepenbeek")
                                            .customer(customer3)
                                            .rentPrice(7)
                                            .dateOfReturn("2024-01-18")
                                            .build();
        
        Copy finalFantasyVIICopy2 = builder.game(finalFantasyVII)
                                            .console(playstation1)
                                            .availability(Availability.BROKEN)
                                            .warehouse("Genk")
                                            .build();
        
        Copy zeldaBreathOfTheWildCopy2 = builder.game(zeldaBreathOfTheWild)
                            .console(nintendoSwitch)
                            .availability(Availability.AVAILABLE)
                            .warehouse("Tokyo")
                            .build();
        
        Copy darkSoulsCopy2 = builder.game(darkSouls)
                                    .console(xbox360)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("Hasselt")
                                    .build();
        Copy grandTheftAutoVCopy2 = builder.game(grandTheftAutoV)
                                            .console(playstation3)
                                            .availability(Availability.RENTED)
                                            .warehouse("Genk")
                                            .customer(max)
                                            .rentPrice(8)
                                            .dateOfReturn("2024-01-20")
                                            .build();
        
        Copy theLastOfUsCopy2 = builder.game(theLastOfUs)
                                        .console(playstation3)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Brussels")
                                        .build();
        
        Copy worldOfWarcraftCopy2 = builder.game(worldOfWarcraft)
                                            .console(pc)
                                            .availability(Availability.AVAILABLE)
                                            .warehouse("Blizzard")
                                            .build();
        
        Copy marioKart8DeluxeCopy2 = builder.game(marioKart8Deluxe)
                                            .console(nintendoSwitch)
                                            .availability(Availability.AVAILABLE)
                                            .warehouse("Kyoto")
                                            .build();
        
        Copy finalFantasyIXCopy1 = builder.game(finalFantasyIX)
                                        .console(playstation1)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Brussels")
                                        .build();
        
        Copy pacmanCopy1 = builder.game(pacman)
                                .console(arcade)
                                .availability(Availability.AVAILABLE)
                                .warehouse("Los Angeles")
                                .build();
        
        Copy superMarioBros3Copy1 = builder.game(superMarioBros3)
                                            .console(nes)
                                            .availability(Availability.AVAILABLE)
                                            .warehouse("Kyoto")
                                            .build();
        
        Copy theLegendOfZeldaCopy1 = builder.game(theLegendOfZelda)
                                            .console(nes)
                                            .availability(Availability.AVAILABLE)
                                            .warehouse("Ghent")
                                            .build();
        
        Copy spaceInvadersCopy1 = builder.game(spaceInvaders)
                                        .console(arcade)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Tokyo")
                                        .build();
        
        Copy donkeyKongCopy1 = builder.game(donkeyKong)
                                        .console(arcade)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Paris")
                                        .build();
        
        Copy pongCopy1 = builder.game(pong)
                                .console(arcade)
                                .availability(Availability.AVAILABLE)
                                .warehouse("Antwerp")
                                .build();
        
        Copy galagaCopy1 = builder.game(galaga)
                                    .console(arcade)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("Genk")
                                    .build();
        
        Copy metroidCopy1 = builder.game(metroid)
                                    .console(nes)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("Diepenbeek")
                                    .build();   
                                    
        Copy assassinsCreedOriginsCopy1 = builder.game(assassinsCreedOrigins)
                                                .console(xboxSeriesX)
                                                .availability(Availability.AVAILABLE)
                                                .warehouse("New York")
                                                .build();
        
        Copy redDeadRedemption2Copy1 = builder.game(redDeadRedemption2)
                                                .console(playstation5)
                                                .availability(Availability.AVAILABLE)
                                                .warehouse("Los Angeles")
                                                .build();
                                        
        Copy uncharted4AThiefsEndCopy1 = builder.game(uncharted4AThiefsEnd)
                                                .console(playstation4)
                                                .availability(Availability.AVAILABLE)
                                                .warehouse("Tokyo")
                                                .build();
        
        Copy overwatchCopy1 = builder.game(overwatch)
                                    .console(xboxOne)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("London")
                                    .build();

        Copy theElderScrollsVSkyrimCopy1 = builder.game(theElderScrollsVSkyrim)
                                                    .console(nintendoSwitch)
                                                    .availability(Availability.AVAILABLE)
                                                    .warehouse("Berlin")
                                                    .build();
                                            
        Copy halfLife2Copy1 = builder.game(halfLife2)
                                    .console(pc)
                                    .availability(Availability.AVAILABLE)
                                    .warehouse("Paris")
                                    .build();
        
        Copy finalFantasyXCopy1 = builder.game(finalFantasyX)
                                        .console(playstation2)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Madrid")
                                        .build();
        
        Copy finalFantasyIXCopy2 = builder.game(finalFantasyIX)
                                        .console(pc)
                                        .availability(Availability.AVAILABLE)
                                        .warehouse("Rome")
                                        .build();

        Copy donkeyKongCopy2 = builder.game(donkeyKong)
                                        .console(arcade)
                                        .availability(Availability.BROKEN)
                                        .warehouse("Genk")
                                        .build();
                            
                                            
        MonetaryTransaction transaction1 = new MonetaryTransaction(TransactionType.SALE, 60, jan, witcherCopy1, "2022-05-12");
        MonetaryTransaction transaction2 = new MonetaryTransaction(TransactionType.RENTAL, 9, bjorn, minecraftCopy2, "2023-12-12");

        doom.setCopies(Arrays.asList(doomCopy1, doomCopy2));
        theWitcher3.setCopies(Arrays.asList(witcherCopy1));
        superMarioBros.setCopies(Arrays.asList(marioCopy1));
        minecraft.setCopies(Arrays.asList(minecraftCopy1, minecraftCopy2, minecraftCopy3));
        pokemonPlatinum.setCopies(Arrays.asList(pokemonPatinumCopy1, pokemonPlatinumCopy2));
        metalGearSolid.setCopies(Arrays.asList(metalGearSolidCopy1, metalGearSolidCopy2));
        finalFantasyVII.setCopies(Arrays.asList(finalFantasyVIICopy1, finalFantasyVIICopy2));
        finalFantasyIX.setCopies(Arrays.asList(finalFantasyIXCopy1, finalFantasyIXCopy2));
        haloCombatEvolved.setCopies(Arrays.asList(haloCombatEvolvedCopy1));
        zeldaBreathOfTheWild.setCopies(Arrays.asList(zeldaBreathOfTheWildCopy1, zeldaBreathOfTheWildCopy2));
        darkSouls.setCopies(Arrays.asList(darkSoulsCopy1, darkSoulsCopy2));
        grandTheftAutoV.setCopies(Arrays.asList(grandTheftAutoVCopy1, grandTheftAutoVCopy2));
        theLastOfUs.setCopies(Arrays.asList(theLastOfUsCopy1, theLastOfUsCopy2));
        worldOfWarcraft.setCopies(Arrays.asList(worldOfWarcraftCopy1, worldOfWarcraftCopy2));
        marioKart8Deluxe.setCopies(Arrays.asList(marioKart8DeluxeCopy1, marioKart8DeluxeCopy2));
        pacman.setCopies(Arrays.asList(pacmanCopy1));
        superMarioBros3.setCopies(Arrays.asList(superMarioBros3Copy1));
        theLegendOfZelda.setCopies(Arrays.asList(theLegendOfZeldaCopy1));
        spaceInvaders.setCopies(Arrays.asList(spaceInvadersCopy1));
        donkeyKong.setCopies(Arrays.asList(donkeyKongCopy1));
        pong.setCopies(Arrays.asList(pongCopy1));
        galaga.setCopies(Arrays.asList(galagaCopy1));
        metroid.setCopies(Arrays.asList(metroidCopy1));
        assassinsCreedOrigins.setCopies(Arrays.asList(assassinsCreedOriginsCopy1));
        redDeadRedemption2.setCopies(Arrays.asList(redDeadRedemption2Copy1));
        uncharted4AThiefsEnd.setCopies(Arrays.asList(uncharted4AThiefsEndCopy1));
        overwatch.setCopies(Arrays.asList(overwatchCopy1));
        theElderScrollsVSkyrim.setCopies(Arrays.asList(theElderScrollsVSkyrimCopy1));
        finalFantasyX.setCopies(Arrays.asList(finalFantasyXCopy1));
        donkeyKong.setCopies(Arrays.asList(donkeyKongCopy1, donkeyKongCopy2));

        action.setGames(Arrays.asList(metalGearSolid, haloCombatEvolved, pacman, spaceInvaders, donkeyKong, pong, galaga, zeldaBreathOfTheWild, darkSouls, grandTheftAutoV, theLastOfUs, assassinsCreedOrigins, uncharted4AThiefsEnd, pacman, theLegendOfZelda, metroid));
        adventure.setGames(Arrays.asList(theWitcher3, zeldaBreathOfTheWild, theLastOfUs, uncharted4AThiefsEnd, metroid, theLegendOfZelda));
        battleRoyale.setGames(Arrays.asList(/* Add your co-op games instances here */));
        coOp.setGames(Arrays.asList(/* Add your co-op games instances here */));
        educational.setGames(Arrays.asList(/* Add your co-op games instances here */));
        fighting.setGames(Arrays.asList(/* Add your fighting games instances here */));
        fps.setGames(Arrays.asList(doom, haloCombatEvolved, overwatch, halfLife2));
        historical.setGames(Arrays.asList(assassinsCreedOrigins, redDeadRedemption2));
        horror.setGames(Arrays.asList(/* Add your co-op games instances here */));
        indie.setGames(Arrays.asList(/* Add your indie games instances here */));
        mmorpg.setGames(Arrays.asList(worldOfWarcraft));
        openWorld.setGames(Arrays.asList(theWitcher3, grandTheftAutoV, theElderScrollsVSkyrim, assassinsCreedOrigins, redDeadRedemption2, zeldaBreathOfTheWild));
        party.setGames(Collections.singletonList(marioKart8Deluxe));
        platformer.setGames(Arrays.asList(superMarioBros, superMarioBros3, donkeyKong, metroid));
        puzzle.setGames(Arrays.asList(/* Add your co-op games instances here */));
        racing.setGames(Arrays.asList(marioKart8Deluxe /* Add your racing games instances here */));
        rpg.setGames(Arrays.asList(theWitcher3, finalFantasyVII, darkSouls, finalFantasyX, finalFantasyIX, pokemonPlatinum, theElderScrollsVSkyrim));
        rhythm.setGames(Arrays.asList(/* Add your co-op games instances here */));
        sandbox.setGames(Arrays.asList(minecraft));
        sciFi.setGames(Arrays.asList(haloCombatEvolved /* Add your sci-fi games instances here */));
        simulation.setGames(Arrays.asList(/* Add your co-op games instances here */));
        sportsAndRacing.setGames(Arrays.asList(/* Add your co-op games instances here */));
        stealth.setGames(Arrays.asList(metalGearSolid));
        strategy.setGames(Arrays.asList(/* Add your strategy games instances here */));
        survival.setGames(Arrays.asList(minecraft));

        nes.setGames(Arrays.asList(superMarioBros, pacman, superMarioBros3, theLegendOfZelda, donkeyKong, metroid));
        playstation4.setGames(Arrays.asList(theWitcher3, minecraft, finalFantasyVII, theLastOfUs, assassinsCreedOrigins, redDeadRedemption2, uncharted4AThiefsEnd, overwatch, grandTheftAutoV));
        xboxOne.setGames(Arrays.asList(theWitcher3, minecraft, finalFantasyVII, assassinsCreedOrigins, theElderScrollsVSkyrim, redDeadRedemption2, overwatch, grandTheftAutoV));
        pc.setGames(Arrays.asList(doom, minecraft, metalGearSolid, finalFantasyVII, theElderScrollsVSkyrim, halfLife2, darkSouls, grandTheftAutoV, worldOfWarcraft, finalFantasyX, uncharted4AThiefsEnd, overwatch, theElderScrollsVSkyrim, finalFantasyIX, theWitcher3, finalFantasyVII, assassinsCreedOrigins));
        nintendoSwitch.setGames(Arrays.asList(minecraft, zeldaBreathOfTheWild, marioKart8Deluxe, redDeadRedemption2, theElderScrollsVSkyrim));
        nintenoDS.setGames(Arrays.asList(pokemonPlatinum));
        playstation1.setGames(Arrays.asList(metalGearSolid, finalFantasyVII, finalFantasyIX));
        xbox.setGames(Arrays.asList(haloCombatEvolved));
        playstation3.setGames(Arrays.asList(darkSouls, theLastOfUs, grandTheftAutoV));
        xbox360.setGames(Arrays.asList(darkSouls, halfLife2, theElderScrollsVSkyrim, minecraft, grandTheftAutoV));
        atari2600.setGames(Arrays.asList(pacman, spaceInvaders, pong));
        arcade.setGames(Arrays.asList(spaceInvaders, donkeyKong, pong, galaga, pacman));
        xboxSeriesX.setGames(Arrays.asList(minecraft, grandTheftAutoV, redDeadRedemption2));
        playstation5.setGames(Arrays.asList(minecraft, grandTheftAutoV, redDeadRedemption2));
        playstation2.setGames(Arrays.asList(finalFantasyX));

        atari.setGames(Arrays.asList(pong));
        idSoftware.setGames(Arrays.asList(doom));
        cdProjekt.setGames(Arrays.asList(theWitcher3));
        nintendo.setGames(Arrays.asList(superMarioBros, pokemonPlatinum, marioKart8Deluxe, superMarioBros3, theLegendOfZelda, donkeyKong, metroid));
        microsoftStudios.setGames(Arrays.asList(haloCombatEvolved, minecraft));
        gameFreak.setGames(Arrays.asList(pokemonPlatinum));
        konami.setGames(Arrays.asList(metalGearSolid));
        squareEnix.setGames(Arrays.asList(finalFantasyVII, finalFantasyX, finalFantasyIX));
        fromSoftware.setGames(Arrays.asList(darkSouls));
        rockstarGames.setGames(Arrays.asList(grandTheftAutoV, redDeadRedemption2));
        naughtyDog.setGames(Arrays.asList(theLastOfUs, uncharted4AThiefsEnd));
        blizzardEntertainment.setGames(Arrays.asList(worldOfWarcraft, overwatch));
        valveCorporation.setGames(Arrays.asList(halfLife2));
        ubisoft.setGames(Arrays.asList(assassinsCreedOrigins));
        bethesdaGameStudios.setGames(Arrays.asList(theElderScrollsVSkyrim));
        namco.setGames(Arrays.asList(pacman, galaga));
        taito.setGames(Arrays.asList(spaceInvaders));
        atari.setGames(Arrays.asList(pong));

        nes.setCopies(Arrays.asList(marioCopy1, pacmanCopy1, superMarioBros3Copy1, theLegendOfZeldaCopy1, donkeyKongCopy1, metroidCopy1));
        playstation4.setCopies(Arrays.asList(witcherCopy1, theLastOfUsCopy1, uncharted4AThiefsEndCopy1));
        xboxOne.setCopies(Arrays.asList(minecraftCopy3, overwatchCopy1, grandTheftAutoVCopy1));
        pc.setCopies(Arrays.asList(doomCopy1, doomCopy2, minecraftCopy1, finalFantasyVIICopy1, halfLife2Copy1, darkSoulsCopy1, darkSoulsCopy2, worldOfWarcraftCopy1, worldOfWarcraftCopy2, finalFantasyIXCopy1));
        nintendoSwitch.setCopies(Arrays.asList(zeldaBreathOfTheWildCopy1, zeldaBreathOfTheWildCopy2, marioKart8DeluxeCopy1, marioKart8DeluxeCopy2, theElderScrollsVSkyrimCopy1));
        nintenoDS.setCopies(Arrays.asList(pokemonPatinumCopy1, pokemonPlatinumCopy2));
        playstation1.setCopies(Arrays.asList(metalGearSolidCopy1, metalGearSolidCopy2, finalFantasyVIICopy2, finalFantasyIXCopy1));
        xbox.setCopies(Arrays.asList(haloCombatEvolvedCopy1));
        playstation3.setCopies(Arrays.asList(darkSoulsCopy1, theLastOfUsCopy2, grandTheftAutoVCopy2));
        xbox360.setCopies(Arrays.asList(darkSoulsCopy2, minecraftCopy1));
        atari2600.setCopies(Arrays.asList(pacmanCopy1, spaceInvadersCopy1, pongCopy1));
        arcade.setCopies(Arrays.asList(spaceInvadersCopy1, donkeyKongCopy2, pongCopy1, galagaCopy1, pacmanCopy1));
        xboxSeriesX.setCopies(Arrays.asList(assassinsCreedOriginsCopy1));
        playstation5.setCopies(Arrays.asList(minecraftCopy2, redDeadRedemption2Copy1));
        playstation2.setCopies(Arrays.asList(finalFantasyXCopy1));

        database.saveNewGenre(action);
        database.saveNewGenre(adventure);
        database.saveNewGenre(battleRoyale);
        database.saveNewGenre(coOp);
        database.saveNewGenre(educational);
        database.saveNewGenre(fighting);
        database.saveNewGenre(fps);
        database.saveNewGenre(historical);
        database.saveNewGenre(horror);
        database.saveNewGenre(indie);
        database.saveNewGenre(mmorpg);
        database.saveNewGenre(openWorld);
        database.saveNewGenre(party);
        database.saveNewGenre(platformer);
        database.saveNewGenre(puzzle);
        database.saveNewGenre(racing);
        database.saveNewGenre(rpg);
        database.saveNewGenre(rhythm);
        database.saveNewGenre(sandbox);
        database.saveNewGenre(sciFi);
        database.saveNewGenre(simulation);
        database.saveNewGenre(sportsAndRacing);
        database.saveNewGenre(stealth);
        database.saveNewGenre(strategy);
        database.saveNewGenre(survival);
        
        database.saveNewDeveloper(nintendo);
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
        database.saveNewDeveloper(gearboxSoftware);
        database.saveNewDeveloper(obsidianEntertainment);
        database.saveNewDeveloper(platinumGames);
        database.saveNewDeveloper(respawnEntertainment);
        database.saveNewDeveloper(supergiantGames);
        database.saveNewDeveloper(theCoalition);
        database.saveNewDeveloper(atlus);
        database.saveNewDeveloper(bandaiNamco);
        database.saveNewDeveloper(capcom);
        database.saveNewDeveloper(dice);
        database.saveNewDeveloper(fromSoftware);
        database.saveNewDeveloper(insomniacGames);
        database.saveNewDeveloper(konami);
        database.saveNewDeveloper(namco);
        database.saveNewDeveloper(sega);
        database.saveNewDeveloper(atari);
        database.saveNewDeveloper(taito);

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
        database.saveNewConsole(gameBoyAdvance);
        database.saveNewConsole(gameBoyColor);
        database.saveNewConsole(gameBoy);
        database.saveNewConsole(atari2600);
        database.saveNewConsole(pc);
        database.saveNewConsole(nes);  
        database.saveNewConsole(snes);
        database.saveNewConsole(segaGenesis);
        database.saveNewConsole(segaDreamcast);
        database.saveNewConsole(segaSaturn);
        database.saveNewConsole(segaMasterSystem);
        database.saveNewConsole(nintendo64);
        database.saveNewConsole(gameCube);
        database.saveNewConsole(sonyPSP);
        database.saveNewConsole(sonyPSVita);
        database.saveNewConsole(arcade);  
        
        database.saveNewGame(doom);
        database.saveNewGame(theWitcher3);
        database.saveNewGame(superMarioBros);
        database.saveNewGame(minecraft);
        database.saveNewGame(pokemonPlatinum);
        database.saveNewGame(pacman);
        database.saveNewGame(superMarioBros3);
        database.saveNewGame(theLegendOfZelda);
        database.saveNewGame(spaceInvaders);
        database.saveNewGame(donkeyKong);
        database.saveNewGame(pong);
        database.saveNewGame(galaga);
        database.saveNewGame(metroid);
        database.saveNewGame(metalGearSolid);
        database.saveNewGame(finalFantasyVII);
        database.saveNewGame(haloCombatEvolved);
        database.saveNewGame(zeldaBreathOfTheWild);
        database.saveNewGame(darkSouls);
        database.saveNewGame(grandTheftAutoV);
        database.saveNewGame(theLastOfUs);
        database.saveNewGame(worldOfWarcraft);
        database.saveNewGame(marioKart8Deluxe);
        database.saveNewGame(assassinsCreedOrigins);
        database.saveNewGame(redDeadRedemption2);
        database.saveNewGame(halfLife2);
        database.saveNewGame(finalFantasyX);
        database.saveNewGame(uncharted4AThiefsEnd);
        database.saveNewGame(overwatch);
        database.saveNewGame(theElderScrollsVSkyrim);
        database.saveNewGame(finalFantasyIX);


        database.saveNewCustomer(dries);
        database.saveNewCustomer(bjorn);
        database.saveNewCustomer(max);
        database.saveNewCustomer(mauro);
        database.saveNewCustomer(eddy);
        database.saveNewCustomer(jan);
        database.saveNewCustomer(customer1);
        database.saveNewCustomer(customer2);
        database.saveNewCustomer(customer3);
        database.saveNewCustomer(customer4);
        database.saveNewCustomer(customer5);
        database.saveNewCustomer(customer6);
        database.saveNewCustomer(customer7);
        database.saveNewCustomer(customer8);
        database.saveNewCustomer(customer9);
        database.saveNewCustomer(customer10);

        database.saveNewCopy(doomCopy1);
        database.saveNewCopy(witcherCopy1);
        database.saveNewCopy(marioCopy1);
        database.saveNewCopy(minecraftCopy1);
        database.saveNewCopy(minecraftCopy2);
        database.saveNewCopy(pokemonPatinumCopy1);
        database.saveNewCopy(metalGearSolidCopy1);
        database.saveNewCopy(finalFantasyVIICopy1);
        database.saveNewCopy(haloCombatEvolvedCopy1);
        database.saveNewCopy(zeldaBreathOfTheWildCopy1);
        database.saveNewCopy(darkSoulsCopy1);
        database.saveNewCopy(grandTheftAutoVCopy1);
        database.saveNewCopy(theLastOfUsCopy1);
        database.saveNewCopy(worldOfWarcraftCopy1);
        database.saveNewCopy(marioKart8DeluxeCopy1);
        database.saveNewCopy(doomCopy2);
        database.saveNewCopy(minecraftCopy3);
        database.saveNewCopy(metalGearSolidCopy2);
        database.saveNewCopy(finalFantasyVIICopy2);
        database.saveNewCopy(zeldaBreathOfTheWildCopy2);
        database.saveNewCopy(darkSoulsCopy2);
        database.saveNewCopy(grandTheftAutoVCopy2);
        database.saveNewCopy(theLastOfUsCopy2);
        database.saveNewCopy(worldOfWarcraftCopy2);
        database.saveNewCopy(marioKart8DeluxeCopy2);
        database.saveNewCopy(pokemonPlatinumCopy2);
        database.saveNewCopy(finalFantasyIXCopy1);
        database.saveNewCopy(pacmanCopy1);
        database.saveNewCopy(superMarioBros3Copy1);
        database.saveNewCopy(theLegendOfZeldaCopy1);
        database.saveNewCopy(spaceInvadersCopy1);
        database.saveNewCopy(donkeyKongCopy1);
        database.saveNewCopy(pongCopy1);
        database.saveNewCopy(galagaCopy1);
        database.saveNewCopy(metroidCopy1);
        database.saveNewCopy(overwatchCopy1);
        database.saveNewCopy(uncharted4AThiefsEndCopy1);
        database.saveNewCopy(redDeadRedemption2Copy1);
        database.saveNewCopy(assassinsCreedOriginsCopy1);
        database.saveNewCopy(theElderScrollsVSkyrimCopy1);
        database.saveNewCopy(halfLife2Copy1);
        database.saveNewCopy(finalFantasyXCopy1);
        database.saveNewCopy(finalFantasyIXCopy2);
        
        database.saveNewMonetaryTransaction(transaction1);
        database.saveNewMonetaryTransaction(transaction2);
    }

}




