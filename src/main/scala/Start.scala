
package main.scala

import scala.collection.mutable.ListBuffer

object Start {
  
  val bedroom = 
    Room("It's a dark bedroom. This was where I woke up.", 
    ListBuffer(
        Item("coffin", desc = "It looks like a big black coffin. The lid is slightly open."), 
        Item("lamp", desc = "It's an older lamp. The lightbulb seems to be missing."), 
        Item("desk", desc = "It's a dusty almost antique working desk, with multiple drawers. There might be something inside.)", unlocksItem = Item("key", true))), 
    Map(('e', "Hallway")))
    
  var inventory: ListBuffer[Item] = ListBuffer.empty
  var currentRoom: Room = bedroom
  
  def main(args: Array[String]) = {
    println("Welcome to 'The Vampire's mansion'!")
    
    if (usrYesOrNo("Do you want to start the game?")) start()
  }
  
  def usrYesOrNo(question: String): Boolean = {
    println(question + " (y/n)")
    while (true) {
      print("You: ")
      var input = scala.io.StdIn.readLine()
    
      if (input == "y") {
        return true
      } else if (input == "n") {
        return false
      }
      else {
        println("Please write 'y' or 'n'.")
      }
    }
    true
  }
  
  def userPromt() = {
    print("You: ")
    var input = scala.io.StdIn.readLine()
    
    if (input.contains("help")) {
      println("Availabe commands: ")
      println("north, west, east, south, up, down, interact, look, inventory, room")
    }
    
    else if (input.contains("interact")) { 
      var itemFound = false
      for (item <- currentRoom.items) {
        if (input.contains(item.name)) {
          item.Interact()
          itemFound = true
        }
      }
      if (!itemFound) {
        println("There is no such thing in this room.")
      }
    }
    
    else if (input.contains("look")) {
      var itemFound = false
      for (item <- currentRoom.items) {
        if (input.contains(item.name)) {
          println(item.desc)
          itemFound = true
        }
      }
      if (!itemFound) {
        println("There is no such thing in this room.")
      }
    }
    
    else if (input.contains("inventory")) {
      if(inventory.length == 0) {
        println("My pockets are empty")
      } else {
        print("My pockets contain... ")
      }

      for (item <- inventory) {
        print(item.name + ". ")
      }
      println("Neat.")
    }
    
    else if (input.contains("room")) {
      currentRoom.enter
    }
    
    else {
      println("I don't know how to do that")
    }
  }
  
  def start() {
    println("\n----------------------Game Start--------------------\n")
    bedroom.enter
    while (true) {
      userPromt
    }
  }
  
  def asciiMansion = {
    println("""               *         .              *            _.---._      
                             ___   .            ___.'       '.   *
        .              _____[LLL]______________[LLL]_____     \
                      /     [LLL]              [LLL]     \     |
                     /____________________________________\    |    .
                      )==================================(    /
     .      *         '|I .-. I .-. I .--. I .-. I .-. I|'  .'
                  *    |I |+| I |+| I |. | I |+| I |+| I|-'`       *
                       |I_|+|_I_|+|_I_|__|_I_|+|_I_|+|_I|      .
              .       /_I_____I_____I______I_____I_____I_\
                       )================================(   *
       *         _     |I .-. I .-. I .--. I .-. I .-. I|          *
                |u|  __|I |+| I |+| I |<>| I |+| I |+| I|    _         .
           __   |u|_|uu|I |+| I |+| I |~ | I |+| I |+| I| _ |U|     _
       .  |uu|__|u|u|u,|I_|+|_I_|+|_I_|__|_I_|+|_I_|+|_I||n|| |____|u|
          |uu|uu|_,.-' /I_____I_____I______I_____I_____I\`'-. |uu u|u|__
          |uu.-'`      #############(______)#############    `'-. u|u|uu|
         _.'`              ~"^"~   (________)   ~"^"^~           `'-.|uu|
      ,''          .'    _                             _ `'-.        `'-.
  ~"^"~    _,'~"^"~    _( )_                         _( )_   `'-.        ~"^"~
      _  .'            |___|                         |___|      ~"^"~     _
    _( )_              |_|_|          () ()          |_|_|              _( )_
    |___|/\/\/\/\/\/\/\|___|/\/\/\/\/\|| ||/\/\/\/\/\|___|/\/\/\/\/\/\/\|___|
    |_|_|\/\/\/\/\/\/\/|_|_|\/\/\/\/\/|| ||\/\/\/\/\/|_|_|\/\/\/\/\/\/\/|_|_|
    |___|/\/\/\/\/\/\/\|___|/\/\/\/\/\|| ||/\/\/\/\/\|___|/\/\/\/\/\/\/\|___|
    |_|_|\/\/\/\/\/\/\/|_|_|\/\/\/\/\/[===]\/\/\/\/\/|_|_|\/\/\/\/\/\/\/|_|_|
    |___|/\/\/\/\/\/\/\|___|/\/\/\/\/\|| ||/\/\/\/\/\|___|/\/\/\/\/\/\/\|___|
    |_|_|\/\/\/\/\/\/\/|_|_|\/\/\/\/\/|| ||\/\/\/\/\/|_|_|\/\/\/\/\/\/\/|_|_|
    |___|/\/\/\/\/\/\/\|___|/\/\/\/\/\|| ||/\/\/\/\/\|___|/\/\/\/\/\/\/\|___|
~""~|_|_|\/\/\/\/\/\/\/|_|_|\/\/\/\/\/|| ||\/\/\/\/\/|_|_|\/\/\/\/\/\/\/|_lc|~""~
   [_____]            [_____]                       [_____]            [_____]
   ---------------------------------------------------------------------------""")
  }
}