/*
 * Polykat - Next-level high-performance Minecraft-Discord rank bridge
 *
 * Copyright (C) 2019  Yannick Seeger & Michael Rittmeister
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 */

plugins {
    checkstyle
    pmd
    id("com.github.spotbugs") version "1.7.1"
    java
}

group = "asia.devs-from.polykat"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "pmd")
    apply(plugin = "checkstyle")
    apply(plugin = "com.github.spotbugs")

    group = project.group
    version = project.version

    tasks {
        checkstyle {
            checkstyleTest.get().enabled = false
            toolVersion = "8.19"
        }

        checkstyleMain {
            configFile = file("$rootDir/config/checkstyle/google_checks.xml")
            configProperties = mapOf("config_loc" to "${rootProject.projectDir}/config/checkstyle")
        }

        spotbugs {
            toolVersion = "4.0.0-beta1"
        }

        spotbugsMain {
            reports {
                html.isEnabled = true
                xml.isEnabled = false
            }
        }

        pmd {
            pmdTest.get().enabled = false
        }

        pmdMain {
            ignoreFailures = true
            ruleSetConfig = this@subprojects.resources.text.fromFile(file("${rootProject.projectDir}/config/pmd/ruleset.xml"))
        }
    }
}
