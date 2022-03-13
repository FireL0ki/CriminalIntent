package com.example.criminalintent

import java.util.*

// UUID is a utility class included with Android framework, provides easy way to generate
// universally unique ID values

class Crime(val id: UUID = UUID.randomUUID(),
            var title: String = "",
            var date: Date = Date(),
            var isSolved: Boolean = false) {
}