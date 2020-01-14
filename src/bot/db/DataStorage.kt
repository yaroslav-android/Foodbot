package team.uptech.food.bot.bot.db

import org.litote.kmongo.*

class DataStorage : Storage {
  val client = KMongo.createClient()
  val database = client.getDatabase("test")

}