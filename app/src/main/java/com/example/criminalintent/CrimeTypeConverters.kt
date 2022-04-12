package com.example.criminalintent

import androidx.room.TypeConverter
import java.util.*

// Room stores primitive types in the underlying SQLite database tables, but it doesn't know
// how to store Date & UUID objects. This class converts the types for Room

// Declaring the converter functions does not enable your database to use them. You must explicitly add
// the converters to your database class

class CrimeTypeConverters {

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }
    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }
    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

}