package com.br.weightcontrol.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseMigrations {

    fun getMigrations() = arrayOf(
        MIGRATION_1_2,
    )

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS `Track` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `weight` REAL NOT NULL, `createdAt` TEXT NOT NULL)")
            db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_Track_createdAt` ON `Track` (`createdAt`)")
            db.execSQL("INSERT INTO Track SELECT `id`, `weight`, substr(w.date  , 7, 4) || '-' || substr(w.date , 4, 2) || '-' || substr(w.date , 1, 2) FROM Weight w")
            db.execSQL("DROP TABLE IF EXISTS Weight")
            db.execSQL("CREATE TABLE IF NOT EXISTS `GoalBackup` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `start` REAL NOT NULL, `desire` REAL NOT NULL, `createdAt` TEXT NOT NULL, `completedAt` TEXT)")
            db.execSQL("INSERT INTO GoalBackup SELECT `id`,`begin`,`end`,substr(g.date_start, 7, 4) || '-' || substr(g.date_start, 4, 2) || '-' || substr(g.date_start, 1, 2), CASE WHEN g.finished = 1 THEN substr(g.date_finish, 7, 4) || '-' || substr(g.date_finish, 4, 2) || '-' || substr(g.date_finish, 1, 2) ELSE null END FROM Goal g")
            db.execSQL("DROP TABLE IF EXISTS Goal")
            db.execSQL("CREATE TABLE IF NOT EXISTS `Goal` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `start` REAL NOT NULL, `desire` REAL NOT NULL,`createdAt` TEXT NOT NULL, `completedAt` TEXT)")
            db.execSQL("INSERT INTO Goal SELECT `id`,`start`,`desire`,`createdAt`,`completedAt` FROM GoalBackup")
            db.execSQL("DROP TABLE IF EXISTS GoalBackup")
        }
    }
}
