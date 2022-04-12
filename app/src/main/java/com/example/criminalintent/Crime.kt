package com.example.criminalintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// UUID is a utility class included with Android framework, provides easy way to generate
// universally unique ID values

@Entity  // indicates the class defines the structure of a table / set of tables in the database
// @PrimaryKey specifies which column is the primary key
data class Crime(@PrimaryKey val id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var date: Date = Date(),
                 var isSolved: Boolean = false) {
}