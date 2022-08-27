package com.example.mappings.mappings.notes;


/**
 *
 *
 *      Snake and Ladders
 *
 *
 *
 *      Multi Player game
 *
 *     (Low Level) -- High Level
 *
 *     -    Find entities
 *     -    Find functionalities
 *     -
 *
 *
 *
 *     Find Entites
 *
 *     Dice
 *     1 dice (random 1 to 6)
 *                          -------> roll (Math.random(1, 6))
 *
 *     Player - User (user)
 *     ActiveGame;
 *
 *
 *
 *     Board - (multiple game)
 *     List<Snakes></>
 *     List<Ladders></>
 *     List<Players></>
 *     dice
 *     - currentPlayer
 *      - Player Position
 *
 *
 *     Snake
 *     Start Location
 *     End Location
 *     check (end Location < less Location start)
 *
 *
 *     Ladder
 *     Start Location
 *     End Location
 *     check (start Location < end Location start)
 *
 *
 *
 *      Audit history
 *          -       Specific use case - which interviewer generally ask
 *              lets say there is a lag , and you want and undo option.
 *              IE - give that you have rolled - (there is an option to roll again)
 *              but that too within tag timestamp (10 seconds)
 *
 *
 *
 *
 *
 */