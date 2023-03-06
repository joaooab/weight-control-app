package com.br.weightcontrol.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseMigrations {

    fun getMigrations() = arrayOf(
        MIGRATION_1_2,
    )

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                """
                    CREATE TABLE IF NOT EXISTS `track` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `weight` REAL NOT NULL, `createdAt` TEXT NOT NULL)
                    INSERT INTO Track SELECT `id`, `weight`, substr(w.date , 7, 4) || '-' || substr(w.date , 4, 2) || '-' || substr(w.date , 1, 2) FROM Weight w
                    DROP TABLE Weight 
                  
                    CREATE TABLE IF NOT EXISTS `GoalBackup` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `start` REAL NOT NULL, `desire` REAL NOT NULL, `current` REAL NOT NULL, `createdAt` TEXT NOT NULL, `completedAt` TEXT)
                    INSERT INTO GoalBackup SELECT `id`,`begin`,`end`,`current`,substr(g.date_start, 7, 4) || '-' || substr(g.date_start, 4, 2) || '-' || substr(g.date_start, 1, 2),
	                CASE WHEN g.finished = 1
                    	THEN substr(g.date_finish, 7, 4) || '-' || substr(g.date_finish, 4, 2) || '-' || substr(g.date_finish, 1, 2)
                    ELSE null
                    END FROM Goal g;
                    DROP TABLE Goal
                    CREATE TABLE IF NOT EXISTS `Goal` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `start` REAL NOT NULL, `desire` REAL NOT NULL, `current` REAL NOT NULL, `createdAt` TEXT NOT NULL, `completedAt` TEXT)
                    INSERT INTO Goal SELECT `id`,`start`,`desire`,`current`,`createdAt`,`completedAt` FROM GoalBackup
                """
            )
        }
    }
}
