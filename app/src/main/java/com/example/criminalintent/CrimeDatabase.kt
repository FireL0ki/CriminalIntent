package com.example.criminalintent

import CrimeDao
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// to hold database specific code

// @Database tells Room that this class represents database in app
// parameters -- first: list of entity classes, tells Room which entity classes to use when creating
// & managing tables for the database
// second: the version fo the database -- when first created = version 1.

@Database(entities = [ Crime::class ], version=1, exportSchema = true)
@TypeConverters(CrimeTypeConverters::class) // adding converters to database class
abstract class CrimeDatabase : RoomDatabase() {

    abstract fun crimeDao(): CrimeDao
}